package controller;

public interface IHabitacionController {

    public String listar(boolean ordenar, String orden);
    
    public String devolver(int id, String username);

     public String sumarCantidad(int id);

    public String reservar(int id, String username);

    public String modificar(int id);
}