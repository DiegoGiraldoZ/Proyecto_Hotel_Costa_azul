
package test;
import beans.Habitaciones;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBD {
    public static void main(String[] args) {
      //actualizarHabitacion(1,"Habitacion1");
        listarHabitacion();  
    }
    public static void actualizarHabitacion(int id, String categoria){
        DBConnection con = new DBConnection();
        String sql = "UPDATE habitacion SET categoria =' "+categoria+" 'WHERE id="+id;
        try {
           Statement st = con.getConnection().createStatement();
           st.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        } 
   } 
    public static void listarHabitacion(){
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM habitacion";
        try {
           Statement st = con.getConnection().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
               int id = rs.getInt("id");
               String categoria = rs.getString("categoria");
               String descripcion = rs.getString("descripcion");
               int cantidad = rs.getInt("cantidad");
               boolean novedad = rs.getBoolean("novedad");
               
               
               Habitaciones habitaciones = new Habitaciones(id, categoria, descripcion, cantidad,novedad);
               System.out.println(habitaciones.toString());
             }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        } 
    }
}
