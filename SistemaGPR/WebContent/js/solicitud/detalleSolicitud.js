

function mostrarOperacion(){
	$( "#valOperaciones" ).dialog( "open" );
}
function mostrarMensajes(){
	$( "#valMensajes" ).dialog( "open" );
}

optionDialogOperaciones = {
		width: 900,
		heigth: 200,
		autoOpen: false,
	    modal: true,
	    buttons: {
	        "Cerrar": function() {
	        	$(this).dialog("close");
	        }
	    },
	    close: function() {
	    }
	}; 
optionDialogMensajes = {
		width: 900,
		heigth: 200,
		autoOpen: false,
	    modal: true,
	    buttons: {
	       
	        "Cerrar": function() {
	        	$(this).dialog("close");
	        }
	    },
	    close: function() {
	    }
	}; 

$(function(){
	$("#valOperaciones").dialog(optionDialogOperaciones);
	$("#valMensajes").dialog(optionDialogMensajes);
});

var myColSolicitudOperaciones  =      [ 'Nro Operaci\xf3n ', 'Nro Solicitud', 'Des Operaci\xf3n', 'Cod. Central',  'Fecha Ingreso', 'Cod. Usuario', 'Nombre Usuario'];
var myDataModelSolicitudOperaciones = [ 
                                    {name : 'codSolicitudOperacion',    index : 'codSolicitudOperacion',    width : 90      ,sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'nroSolicitud',      		index : 'nroSolicitud',       		width : 90      ,sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'desOperacion',  			index : 'desOperacion',  			width : 190     ,sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'codCentral',        		index : 'codCentral',         		width : 100     ,sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'strFechaIngreso',    		index : 'strFechaIngreso',      	width : 90      ,sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'codUsuario',    			index : 'codUsuario',      			width : 90      ,sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'nomUsuario',    			index : 'nomUsuario',      			width : 180     ,sortable:false, editoptions: {style: 'text-align: center'}}
                                 
                                    ]; 
                                   
var myColSolicitudMensaje  =      [ 'Nro Mensaje', 'Nro Solicitud','Cod. Central', 'Mensaje', 'Fecha Ingreso',  'Cod. Usuario', 'Nombre Usuario'];
var myDataModelSolicitudMensaje = [ {name : 'codMensaje',      	index : 'codMensaje',       	width : 90,		sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'nroSolicitud',     index : 'nroSolicitud',       	width : 90,		sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'codCentral',  		index : 'codCentral',  			width : 90,		sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'desMensaje',  		index : 'desMensaje',      		width : 190,	sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'strFechaIngreso',  index : 'strFechaIngreso',      width : 90,		sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'codUsuario',    	index : 'codUsuario',      		width : 90,		sortable:false, editoptions: {style: 'text-align: center'}},
                                    {name : 'nomUsuario',    	index : 'nomUsuario',      		width : 180,	sortable:false, editoptions: {style: 'text-align: center'}}
                                    ];
function mostrarTablaOperaciones(data){
	
	$('body').append('<div id="paginador_listPlazos" class="grid"></div>'); 
	
	jQuery("#listOperations").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Operaciones",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 660,
		colNames 	: myColSolicitudOperaciones,
		colModel 	: myDataModelSolicitudOperaciones,
		rowList 	: [5,10,15,20],
		rowNum 		: 5, 
		viewrecords : true,
		multiselect : false,			
		subGrid    	: false,
		jsonReader : { repeatitems: false },
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});

}

function mostrarTablaMensajes(data){
	
	$('body').append('<div id="paginador_listMessages" class="grid"></div>'); 
	
	jQuery("#listMessages").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Mensajes",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 660,
		colNames 	: myColSolicitudMensaje,
		colModel 	: myDataModelSolicitudMensaje,
		rowList 	: [5,10,15,20],
		rowNum 		: 10, 
		viewrecords : true,
		multiselect : false,			
		subGrid    	: false,
		jsonReader : { repeatitems: false },
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
	
}

function listarOperaciones(){
	var formulario = document.getElementById('formSolicitudIngreso');
	var nroSolicitud = formulario.nroSolicitud.value;
	jQuery("#listOperations").GridUnload();
	BusquedaSolicitudAction.listOperationsAjax(nroSolicitud, function(data){
		mostrarTablaOperaciones(data);
	});
}

function listarMensajes(){
	var formulario = document.getElementById('formSolicitudIngreso');
	var nroSolicitud = formulario.nroSolicitud.value;
	jQuery("#listMessages").GridUnload();
	BusquedaSolicitudAction.listMessagesAjax(nroSolicitud, function(data){
		mostrarTablaMensajes(data);
	});
}
