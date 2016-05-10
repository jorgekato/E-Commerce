/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.controle.web;

import e_commer.controle.web.command.ICommand;
import e_commer.controle.web.command.impl.ConsultarCommand;
import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.controle.Fachada;
import e_commer.dominio.Cliente;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Login;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Henrique
 */
public class ServletAutenticacao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operacao = request.getParameter("operacao");
        Cliente cli = null;
        //Login log = null;
        Resultado resultado = null;

        ICommand command = null;

        if (operacao.equals("LOGAR")) {

            cli = new Cliente();
            command = new ConsultarCommand();

            String txtEmail = request.getParameter("txtEmail");
            String password = request.getParameter("password");

            if (txtEmail != null && !txtEmail.trim().equals("")) {
                cli.setEmail(txtEmail);
            }

            resultado = command.execute(cli);

            if (resultado.getEntidades() != null && resultado.getEntidades().size() > 0 ) {
                for (EntidadeDominio e : resultado.getEntidades()) {

                    cli = (Cliente) e;
                }

                if (cli.getLogin().getPassword().equals(password)) {
                    
                    Login log = new Login();
                    log.setPassword("");
                    cli.setLogin(log);
                    request.getSession().setAttribute("usuario", cli);

                    if (cli.getNivel() != Cliente.Nivel.ADMINISTRADOR && cli.getNivel() != Cliente.Nivel.COLABORADOR) {
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("LadoAdmin/adminprinc.jsp").forward(request, response);
                    }
                } else {
                    resultado.setMsg("Usuário e/ou Senha inválidos!");
                    request.setAttribute("resultado", resultado);
                    request.getRequestDispatcher("account.jsp").forward(request, response);
                }
            } else {
                resultado.setMsg("Usuário e/ou Senha inválidos!");
                request.setAttribute("resultado", resultado);
                request.getRequestDispatcher("account.jsp").forward(request, response);
            }

        } else if (operacao.equals("SAIR")) {
            //request.getSession().setAttribute("usuario", null);
            request.getSession().invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
