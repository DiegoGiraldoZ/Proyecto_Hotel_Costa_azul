package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.Habitaciones;
import connection.DBConnection;

public class HabitacionController implements IHabitacionController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from habitacion";

        if (ordenar == true) {
            sql += " order by categoria " + orden;
        }

        List<String> habitaciones = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String categoria = rs.getString("categoria");
                String descripcion = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                boolean novedad = rs.getBoolean("novedad");

                Habitaciones habitacion = new Habitaciones(id, categoria, descripcion, cantidad,novedad);

                habitaciones.add(gson.toJson(habitacion));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return gson.toJson(habitaciones);

    }
    
      @Override
    public String devolver(int id, String username) {

        DBConnection con = new DBConnection();
        String sql = "Delete from reserva where id= " + id + " and username = '" 
                + username + "' limit 1";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);

            this.sumarCantidad(id);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String sumarCantidad(int id) {

        DBConnection con = new DBConnection();

        String sql = "Update habitacion set cantidad = (Select cantidad from habitacion where id= " 
                + id + ") + 1 where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    
    @Override
    public String reservar(int id, String username) {

        Timestamp fecha_ingreso = new Timestamp(new Date().getTime());
        DBConnection con = new DBConnection();
        String sql = "Insert into reserva values ('" + id + "', '" + username + "', '" + fecha_ingreso + "')";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            String modificar = modificar(id);

            if (modificar.equals("true")) {
                return "true";
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }
        return "false";
    }

     @Override
    public String modificar(int id) {

        DBConnection con = new DBConnection();
        String sql = "Update habitacion set cantidad = (cantidad - 1) where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
}
    