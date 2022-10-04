
package connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    Connection connection;
    static String bd = "railway";
    static String port = "6697";
    static String login = "root";
    static String password = "P7b8nzmu1bl7lY7JO72F";
    static String ip = "containers-us-west-33.railway.app"; 

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://"+ DBConnection.ip +":" + DBConnection.port + "/" + DBConnection.bd;
            connection = DriverManager.getConnection(url, this.login, this.password);
                System.out.println("conexion establecida");
                       
        } catch (Exception ex) {
            System.out.println("error en la conexion");
        }
    }
    public Connection getConnection(){
        return connection;
    }
    
    public void desconectar(){
        connection = null;
    }
     
}