
package beans;


public class Habitaciones {
   private int id;
   private String categoria;
   private String descripcion;
   private int cantidad;       
   private boolean novedad;

    public Habitaciones(int id, String categoria, String descripcion, int cantidad, boolean novedad) {
        this.id = id;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.novedad = novedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isNovedad() {
        return novedad;
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
    }

    @Override
    public String toString() {
        return "Habitaciones{" + "id=" + id + ", categoria=" + categoria + ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", novedad=" + novedad + '}';
    }
   
   

}