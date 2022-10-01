package controller;

import java.util.Map;

public interface IUsuarioController {

    public String login(String username, String contrasena);

    public String register(String username, String contrasena,
            String nombre, String apellido, String email, String celular, String direccion, double saldo, boolean premium);
    
    public String pedir(String username);
    
     public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevoApellido, String nuevoEmail, String nuevoCelular,String nuevaDireccion,
            double nuevoSaldo, boolean nuevoPremium);

    public String verCantidad(String username);

    public String devolverHabitaciones(String username, Map<Integer, Integer> cantidad);

    public String eliminar(String username);

   public String restarDinero(String username, double nuevoSaldo);

}    

