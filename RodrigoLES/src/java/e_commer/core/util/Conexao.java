package e_commer.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Henrique
 */
public class Conexao {
    
    public static Connection getConnection()
            throws ClassNotFoundException,
            SQLException {
        String driver = "org.postgresql.Driver";
        //String url = "jdbc:postgresql://localhost:5432/E_CommerceArt_J";
        String url = "jdbc:postgresql://localhost:5432/E_Commerce_H";
        String user = "postgres";
        String password = "kato";
        Class.forName(driver);
        Connection conn
                = DriverManager.getConnection(url, user, password);

        return conn;
    }
    
    
}
