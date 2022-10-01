var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {
        
        $("#mi-perfil-btn").attr("href","profile.html?username=" + username);
        
        $("#user-saldo").html(user.saldo.toFixed(2) + "$");

        getHabitaciones(false, "ASC");

        $("#ordenar-categoria").click(ordenarHabitaciones);
    });
});


async function getUsuario() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });

}
function getHabitaciones(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletHabitacionListar",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
           
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarHabitaciones(parsedResult);
            } else {
                console.log("Error recuperando los datos de las habitaciones");
            }
        }
    });
}
function mostrarHabitaciones(habitaciones) {

    let contenido = "";

    $.each(habitaciones, function (index, habitacion) {

        habitacion = JSON.parse(habitacion);
        let precio;

        if (habitacion.cantidad > 0) {

            if (user.premium) {

                if (habitacion.novedad) {
                    precio = (2 - (2 * 0.1));
                } else {
                    precio = (1 - (1 * 0.1));
                }
            } else {
                if (habitacion.novedad) {
                    precio = 2;
                } else {
                    precio = 1;
                }
            }

            contenido += '<tr><th scope="row">' + habitacion.id + '</th>' +
                    '<td>' + habitacion.categoria + '</td>' +
                    '<td>' + habitacion.descripcion + '</td>' +
                    '<td>' + habitacion.cantidad + '</td>' +
//                    '<td><input type="checkbox" name="novedad" id="novedad' + habitacion.id + '" disabled ';
//            if (habitacion.novedad) {
//                contenido += 'checked';
//            }
//            contenido += '></td>' +
                    '<td>' + precio + '</td>' +
                    '<td><button onclick="reservarHabitacion(' + habitacion.id + ',' + precio + ');" class="btn btn-success" ';
            if (user.saldo < precio) {
                contenido += ' disabled ';
            }

            contenido += '>Reservar</button></td></tr>'

        }
    });
    $("#habitaciones-tbody").html(contenido);
    
}   
function ordenarHabitaciones() {

    if ($("#icono-ordenar").hasClass("fa-sort")) {
        getHabitaciones(true, "ASC");
        $("#icono-ordenar").removeClass("fa-sort");
        $("#icono-ordenar").addClass("fa-sort-down");
    } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
        getHabitaciones(true, "DESC");
        $("#icono-ordenar").removeClass("fa-sort-down");
        $("#icono-ordenar").addClass("fa-sort-up");
    } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
        getHabitaciones(false, "ASC");
        $("#icono-ordenar").removeClass("fa-sort-up");
        $("#icono-ordenar").addClass("fa-sort");
    }
}
function reservarHabitacion(id, precio) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletHabitacionReservar",
        data: $.param({
            id: id,
            username: username

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                restarDinero(precio).then(function () {
                    location.reload();
                })
            } else {
                console.log("Error en la reserva de la habitación");
            }
        }
    });
}


async function restarDinero(precio) {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioRestarDinero",
        data: $.param({
            username: username,
            saldo: parseFloat(user.saldo - precio)

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                console.log("Saldo actualizado");
            } else {
                console.log("Error en el proceso de pago");
            }
        }
    });
}  