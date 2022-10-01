package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Reserva;
import connection.DBConnection;

public class ReservaController implements IReservaController {

    @Override
    public String listarReservas(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select h.id, h.categoria, h.descripcion, h.novedad, r.fecha_ingreso from habitacion h "
                + "inner join reserva r on h.id = r.id inner join usuario u on r.username = u.username "
                + "where r.username = '" + username + "'";

        List<String> reservas = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String categoria = rs.getString("categoria");
               String descripcion = rs.getString("descripcion");
                boolean novedad = rs.getBoolean("novedad");
                Date fecha_ingreso = rs.getDate("fecha_ingreso");

                Reserva reserva = new Reserva(id, descripcion, fecha_ingreso, novedad, categoria);
                

                reservas.add(gson.toJson(reserva));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return gson.toJson(reservas);
    }
}