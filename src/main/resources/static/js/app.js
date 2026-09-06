/**
 * app.js
 * --------------------------------------------------------
 * Consume la API REST expuesta por el backend de Spring Boot:
 *   GET  /api/ambiente  -> muestra la insignia de ambiente y,
 *                          si corresponde, el panel de depuración.
 *   POST /api/contacto  -> envía el formulario y muestra el
 *                          mensaje de confirmación o los errores.
 *
 * La validación aquí es solo de experiencia de usuario (UX);
 * la validación real y definitiva siempre ocurre en el servidor
 * (ContactoRequest.java + ManejadorErroresGlobal.java), por lo
 * que el formulario nunca depende únicamente de JavaScript.
 * --------------------------------------------------------
 */
document.addEventListener('DOMContentLoaded', function () {
    cargarInformacionAmbiente();

    var formulario = document.getElementById('formulario-contacto');
    var botonEnviar = document.getElementById('boton-enviar');
    var alertaExito = document.getElementById('alerta-exito');
    var alertaError = document.getElementById('alerta-error');
    var listaErrores = document.getElementById('lista-errores');

    var regexCorreo = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    var campos = {
        nombre: document.getElementById('nombre'),
        correo: document.getElementById('correo'),
        asunto: document.getElementById('asunto'),
        mensaje: document.getElementById('mensaje')
    };

    function mostrarErrorCampo(nombreCampo, texto) {
        var input = campos[nombreCampo];
        var contenedor = input.closest('.campo');
        var etiquetaError = formulario.querySelector('.mensaje-error[data-para="' + nombreCampo + '"]');
        if (texto) {
            contenedor.classList.add('con-error');
            etiquetaError.textContent = texto;
        } else {
            contenedor.classList.remove('con-error');
            etiquetaError.textContent = '';
        }
    }

    function validarCampo(nombreCampo) {
        var valor = campos[nombreCampo].value.trim();

        if (valor === '') {
            mostrarErrorCampo(nombreCampo, 'Este campo es obligatorio.');
            return false;
        }
        if (nombreCampo === 'correo' && !regexCorreo.test(valor)) {
            mostrarErrorCampo(nombreCampo, 'Ingresa un correo electrónico válido.');
            return false;
        }
        mostrarErrorCampo(nombreCampo, '');
        return true;
    }

    Object.keys(campos).forEach(function (nombreCampo) {
        campos[nombreCampo].addEventListener('blur', function () {
            validarCampo(nombreCampo);
        });
    });

    function ocultarAlertas() {
        alertaExito.hidden = true;
        alertaError.hidden = true;
        listaErrores.innerHTML = '';
    }

    function mostrarErroresDelServidor(errores) {
        listaErrores.innerHTML = '';
        errores.forEach(function (error) {
            var li = document.createElement('li');
            li.textContent = error;
            listaErrores.appendChild(li);
        });
        alertaError.hidden = false;
    }

    formulario.addEventListener('submit', function (evento) {
        evento.preventDefault();
        ocultarAlertas();

        var esValido = true;
        Object.keys(campos).forEach(function (nombreCampo) {
            if (!validarCampo(nombreCampo)) {
                esValido = false;
            }
        });
        if (!esValido) {
            return;
        }

        var datos = {
            nombre: campos.nombre.value.trim(),
            correo: campos.correo.value.trim(),
            asunto: campos.asunto.value.trim(),
            mensaje: campos.mensaje.value.trim()
        };

        botonEnviar.disabled = true;
        botonEnviar.textContent = 'Enviando...';

        fetch('/api/contacto', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datos)
        })
            .then(function (respuesta) {
                return respuesta.json().then(function (cuerpo) {
                    return { status: respuesta.status, cuerpo: cuerpo };
                });
            })
            .then(function (resultado) {
                if (resultado.status === 200 && resultado.cuerpo.exito) {
                    alertaExito.textContent = '✅ ' + resultado.cuerpo.mensaje;
                    alertaExito.hidden = false;
                    formulario.reset();
                    cargarInformacionAmbiente();
                } else {
                    var errores = (resultado.cuerpo && resultado.cuerpo.errores)
                        ? resultado.cuerpo.errores
                        : ['Ocurrió un problema al enviar el formulario. Intenta nuevamente.'];
                    mostrarErroresDelServidor(errores);
                }
            })
            .catch(function () {
                mostrarErroresDelServidor(['No fue posible conectar con el servidor. Verifica tu conexión e intenta nuevamente.']);
            })
            .finally(function () {
                botonEnviar.disabled = false;
                botonEnviar.textContent = 'Enviar mensaje';
            });
    });

    function cargarInformacionAmbiente() {
        fetch('/api/ambiente')
            .then(function (respuesta) { return respuesta.json(); })
            .then(function (info) {
                var badge = document.getElementById('badge-ambiente');
                badge.textContent = 'Ambiente activo: ' + info.etiqueta;
                badge.className = 'badge-ambiente badge-' + info.ambiente;
                badge.hidden = false;

                var panelDebug = document.getElementById('panel-debug');
                if (info.debug) {
                    document.getElementById('debug-ambiente').textContent = info.ambiente;
                    document.getElementById('debug-total').textContent = info.totalContactos;
                    panelDebug.hidden = false;
                } else {
                    panelDebug.hidden = true;
                }
            })
            .catch(function () {
                // Si falla la llamada de informacion de ambiente, el formulario
                // sigue siendo utilizable con normalidad.
            });
    }
});
