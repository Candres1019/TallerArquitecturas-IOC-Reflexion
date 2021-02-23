
app = (function () {
    function getUsuarios() {
        const promise = $.get({
            url: "/usuarios/ver",
            contentType: "application/json"
        });
        promise.then(function (data) {
            showUsuarios(data);
        }, function (error) {
            alert("Error al insertar el usuario " + data);
        });
    }

    function showUsuarios(data) {
        for (let i = 0; i < data.split("%").length; i++) {
            $("#usuarios").append("<h4 class='temporal'>" + data + "<h4/>")
        }
    }

    function insertUsuario() {
        const name = $("#name").val();
        const promise = $.get({
            url: "/usuarios/add?name=" + name,
            contentType: "application/json"
        });
        promise.then(function (data) {
            alert("Usuario " + data + "ingresado");
        }, function (error) {
            alert("Error al insertar el usuario " + data);
        });
    }

    return {
        getUsuarios: getUsuarios,
        insertUsuario: insertUsuario
    }
})();