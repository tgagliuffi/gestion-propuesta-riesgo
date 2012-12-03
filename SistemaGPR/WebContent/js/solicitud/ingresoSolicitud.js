function loadInfoClient(){
	var formulario = document.getElementById('formSolicitudIngreso');
	formulario.action = rutaContexto+'/ingresoSolicitud.do?method=init&param=continuar';;
	formulario.submit();
}

function enviarRiesgos(){
	submitEnviarRiesgos();

}

function rechazar(){
	submitRechazar();
	
}
function obtener(){
	return document.getElementById(strMensajePopUP).value;
}

function cancelar(){
	$(this).dialog("close");
}

optionDialogVCC = {
	width: 420,
	autoOpen: false,
    modal: true,
    buttons: {
        "Continuar": function() {
        	$(this).dialog("close");
        },
        "Cancelar": function() {
        	var formulario = document.getElementById('formSolicitudIngreso');
        	formulario.action = rutaContexto+'/ingresoSolicitud.do?method=init&param=continuar';;
        	formulario.submit();
        	$(this).dialog("close");
        }
    },
    close: function() {
    	
    }
}; 
optionDialogVMP = {
		width: 420,
		autoOpen: false,
	    modal: true,
	    buttons: {
	        "Enviar a Riesgos": function() {
	        	enviarRiesgos();
	        	$(this).dialog("close");
	        },
	        "Rechazar": function() {
	        	rechazar();
	        	$(this).dialog("close");
	        },
	        "Cancelar": function() {
	        	$(this).dialog("close");
	        }
	    },
	    close: function() {
	    }
	}; 


$(function(){
	$("#valCondicionCliente").dialog(optionDialogVCC);
	$("#valMontoPlazos").dialog(optionDialogVMP);
});


