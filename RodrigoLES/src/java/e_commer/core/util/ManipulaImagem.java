/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.util;

import e_commer.dominio.Imagem;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 *
 * @author Henrique
 */
public class ManipulaImagem {

    private static BufferedImage buffer;
    private static byte[] imageInByteArray;
    private static ByteArrayOutputStream baos;

    /*
    Converte uma imagem do tipo InputStream em byte[] para salvar no BD com o tipo bytea.
     */
    public static byte[] convertImagemBytes(InputStream imagem) {
        if (imagem == null) {
            return null;
        }

        try {
            //Converte InputStream em BufferedImage
            buffer = ImageIO.read(imagem);
            //Instancia objeto do tipo ByteArrayOutputStream
            baos = new ByteArrayOutputStream();
            baos.reset();
            //Usa o método write da classe ImageIO para gravar no atributo baos o conteudo convertido 
            //do atributobuffer            
            ImageIO.write(buffer, "jpg", baos);
            baos.flush();
            //Converte o valor do atributo baos em byte[]
            imageInByteArray = baos.toByteArray();
            baos.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (imageInByteArray != null) {
            return imageInByteArray;
        } else {
            return null;
        }
    }

    //Converte uma imagem do tipo byte[] em valor do tipo Base64
    public static String convertImagemBase64(byte[] imagem) {
        if (imagem != null) {
            return javax.xml.bind.DatatypeConverter.printBase64Binary(imagem);
        } else {
            return null;
        }
    }

    /*
    Altera as dimensões de uma imagem para melhor exibição na página JSP
     */
    public static String setImagemDimensao(byte[] caminhoImg, Integer imgLargura, Integer imgAltura) {
        Double novaImgLargura = null;
        Double novaImgAltura = null;
        Double imgProporcao = null;
        Graphics2D g2d = null;
        BufferedImage imagem = null, novaImagem = null;

        //Converte uma imagem do tipo byte[] em um tipo InputStream
        InputStream input = new ByteArrayInputStream(caminhoImg);
        try {
            //--- Obtém a imagem a ser redimensionada ---
            imagem = ImageIO.read(input);
        } catch (IOException ex) {
            //Tratar o erro ou exibir msg para o usuário
        }

        //--- Obtém a largura da imagem ---  
        novaImgLargura = (double) imagem.getWidth();

        //--- Obtám a altura da imagem ---  
        novaImgAltura = (double) imagem.getHeight();

        //--- Verifica se a altura ou largura da imagem recebida é maior do que os ---  
        //--- parâmetros de altura e largura recebidos para o redimensionamento   ---  
        if (novaImgLargura >= imgLargura) {
            imgProporcao = (novaImgAltura / novaImgLargura);//calcula a proporção  
            novaImgLargura = (double) imgLargura;

            //--- altura deve <= ao parâmetro imgAltura e proporcional a largura ---  
            novaImgAltura = (novaImgLargura * imgProporcao);

            //--- se altura for maior do que o parâmetro imgAltura, diminui-se a largura de ---  
            //--- forma que a altura seja igual ao parâmetro imgAltura e proporcional a largura ---  
            while (novaImgAltura > imgAltura) {
                novaImgLargura = (double) (--imgLargura);
                novaImgAltura = (novaImgLargura * imgProporcao);
            }
        } else if (novaImgAltura >= imgAltura) {
            imgProporcao = (novaImgLargura / novaImgAltura);//calcula a proporção  
            novaImgAltura = (double) imgAltura;

            //--- se largura for maior do que o parâmetro imgLargura, diminui-se a altura de ---  
            //--- forma que a largura seja igual ao parâmetro imglargura e proporcional a altura ---  
            while (novaImgLargura > imgLargura) {
                novaImgAltura = (double) (--imgAltura);
                novaImgLargura = (novaImgAltura * imgProporcao);
            }
        }

        novaImagem = new BufferedImage(novaImgLargura.intValue(), novaImgAltura.intValue(), BufferedImage.TYPE_INT_RGB);
        g2d = novaImagem.createGraphics();
        g2d.drawImage(imagem, 0, 0, novaImgLargura.intValue(), novaImgAltura.intValue(), null);

        //Converte a nova imagem para tipo byte[]
        byte[] novaImg = convertBufferedImageEmBytes(novaImagem);
        //retorna a nova imagem na Base64 para exibição
        return convertImagemBase64(novaImg);
    }

    /*
    Converte imagem do tipo BufferedImage em byte[]
    */
    public static byte[] convertBufferedImageEmBytes(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", baos);
        } catch (IOException ex) {
            //handle it here.... not implemented yet...
        }

        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        return baos.toByteArray();
    }
}
