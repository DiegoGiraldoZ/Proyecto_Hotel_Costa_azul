
package beans;

import java.sql.Date;


public class Reserva {
  
    private int id;
    private String username;
    private Date fecha_ingreso;  
    private boolean novedad;
    private String categoria;

    public Reserva(int id, String username, Date fecha_ingreso, boolean novedad, String categoria) {
        this.id = id;
        this.username = username;
        this.fecha_ingreso = fecha_ingreso;
        this.novedad = novedad;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public boolean isNovedad() {
        return novedad;
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", username=" + username + ", fecha_ingreso=" + fecha_ingreso + ", novedad=" + novedad + ", categoria=" + categoria + '}';
    }

  

}