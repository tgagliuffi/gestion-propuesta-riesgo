<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />

	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>	
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util/formatters.js" type="text/javascript"></script>

<script type="text/javascript">

$(function() {
    $( "#fechaHoy" ).datepicker({dateFormat: 'dd/mm/yy'});
    $( "#fecVencimiento" ).datepicker({dateFormat: 'dd/mm/yy'});
    $( "#fechaIngresoOfic" ).datepicker({dateFormat: 'dd/mm/yy'});
});

//frk: para el caso de IExplorer en el que no 
//funciona bien el "getElementsByName" 

if (jQuery.browser.msie){
	document.getElementsByName = function(name, tag){
	    return getElementsByName_iefix(name, tag);
	};
}

//frk: variable global en el que se almacenara 
//el ID seleccionado de la grilla

var lastSelAnalisis = '';

//frk: si damos agregar y luego ESC se debe eliminar la fila agregada
$(document).keyup(function(e) {
  //frk: se presiono el ESC
  var tecla = (document.all) ? e.keyCode : e.which; 
  if (tecla == 27) {
	  lastSelAnalisis = '';
	  deleteTblRowAdded('listAnalisis');
  }
});

var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColNames  = ['','Producto', 'Contrato Vinculado', 'Cod. Pre Evaluador', 'Campaña', 'Tipo', 'Monto', 'Plazo (Meses)', 'Garantia'];
var myDataModel = [ {name : 'codigo',				width : VAL_WIDTH.SMALL, hidden : true	},
                    {name : 'desProducto',			index : 'desProducto', 			width : VAL_WIDTH.SMALL		},
                    {name : 'contratoVinculado',	index : 'contratoVinculado',	width : VAL_WIDTH.SMALL		},
                    {name : 'codPreEvaluador',		index : 'codPreEvaluador', 		width : VAL_WIDTH.SMALL		},
                    {name : 'campania',				index : 'campania', 			width : VAL_WIDTH.SMALL		},
                    {name : 'tipo',					index : 'tipo', 				width : VAL_WIDTH.XLSMALL	},
                    {name : 'monto',				index : 'monto', 				width : VAL_WIDTH.XLSMALL	},
                    {name : 'plazo',				index : 'plazo', 				width : VAL_WIDTH.XLSMALL	},
                    {name : 'garantia',				index : 'garantia', 			width : VAL_WIDTH.SMALL		}
                   ];

var myColNamesLogProc  = ['','Fecha', 'Hora', 'Proceso', 'Registro', 'Nombres completos'];
var myDataModelLogProc = [ {name : 'codigo',				width : VAL_WIDTH.SMALL, hidden : true	},
                    {name : 'fecha',			index : 'fecha', 		width : VAL_WIDTH.SMALL		},
                    {name : 'hora',				index : 'hora',			width : VAL_WIDTH.SMALL		},
                    {name : 'proceso',			index : 'proceso', 		width : VAL_WIDTH.SMALL		},
                    {name : 'registro',			index : 'registro', 	width : VAL_WIDTH.SMALL		},
                    {name : 'nomCompleto',		index : 'nomCompleto', 	width : VAL_WIDTH.MED		}
                   ];

var myColNamesAnalisis  = ['','Proceso', 'Motivo', 'Comentario'];
var myDataModelAnalisis = [ 
					{name : 'codProceso',	index : 'codProceso',	width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true, required:true}, hidden:true, formatter: codProcesoFormat, unformat: genericUnFormat},
                    {name : 'desProceso',	index : 'desProceso', 	width : VAL_WIDTH.SMALL,	editable:true,	edittype:'text', editoptions: {size:20, maxlength: 255}, editrules: {required: true}},
                    {name : 'motivo',		index : 'motivo',		width : VAL_WIDTH.SMALL,	editable:true,	edittype:'text', editoptions: {size:20, maxlength: 255}, editrules: {required: true}},
                    {name : 'comentario',	index : 'comentario', 	width : VAL_WIDTH.SMALL,	editable:true,	edittype:'text', editoptions: {size:20, maxlength: 255}, editrules: {required: false}}
                   ];

function showBodyForm(){
	loadInfoClient();
}

function loadInfoClient(){
		
}

function consultarProductos(){

	jQuery("#listProducts").GridUnload();
	/* ProductoAction.consultarAjax(function(data){
		mostrarTabla(data);
	}); */
	
	var dataTable = [
	{"codigo":"1", "desProducto":"Conticasa", "contratoVinculado":"320213", "codPreEvaluador":"87324783", "campania":"Campaña Mi Vivienda", "tipo":"Referenciado", "monto":"34696.78", "plazo":"90", "garantia":""},
	{"codigo":"2", "desProducto":"Conticasa", "contratoVinculado":"320213", "codPreEvaluador":"87324783", "campania":"Campaña Mi Vivienda", "tipo":"Referenciado", "monto":"34696.78", "plazo":"90", "garantia":""},
	{"codigo":"3", "desProducto":"Conticasa", "contratoVinculado":"320213", "codPreEvaluador":"87324783", "campania":"Campaña Mi Vivienda", "tipo":"Referenciado", "monto":"34696.78", "plazo":"90", "garantia":""},
	{"codigo":"4", "desProducto":"Conticasa", "contratoVinculado":"320213", "codPreEvaluador":"87324783", "campania":"Campaña Mi Vivienda", "tipo":"Referenciado", "monto":"34696.78", "plazo":"90", "garantia":""},
	{"codigo":"5", "desProducto":"Conticasa", "contratoVinculado":"320213", "codPreEvaluador":"87324783", "campania":"Campaña Mi Vivienda", "tipo":"Referenciado", "monto":"34696.78", "plazo":"90", "garantia":""},
	{"codigo":"6", "desProducto":"Conticasa", "contratoVinculado":"320213", "codPreEvaluador":"87324783", "campania":"Campaña Mi Vivienda", "tipo":"Referenciado", "monto":"34696.78", "plazo":"90", "garantia":""}
	];
	
	mostrarTabla(dataTable);
}

function mostrarTabla(data){
	
	$('body').append('<div id="paginador_listProducts" class="grid"></div>'); 
	var paginador = "paginador_listProducts";
	
	jQuery("#listProducts").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Productos",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNames,
		colModel 	: myDataModel,
		rowList 	: [5,10,15,20,25],
		rowNum 		: 15, 
		pager 		: paginador,
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
}

function consultarLogProcesos(){

	jQuery("#listLogProceso").GridUnload();
	/* ProductoAction.consultarAjax(function(data){
		mostrarTablaLogProcesos(data);
	}); */
	
	var dataTable = [
	{"codigo":"1", "fecha":"03/12/2011", "hora":"05:47 PM", "proceso":"SOLICITUD", "registro":"P017073", "nomCompleto":"Richard Claude De los Reyes Principe"},
	{"codigo":"2", "fecha":"03/12/2011", "hora":"05:47 PM", "proceso":"SOLICITUD", "registro":"P017073", "nomCompleto":"Richard Claude De los Reyes Principe"},
	{"codigo":"3", "fecha":"03/12/2011", "hora":"05:47 PM", "proceso":"SOLICITUD", "registro":"P017073", "nomCompleto":"Richard Claude De los Reyes Principe"},
	{"codigo":"4", "fecha":"03/12/2011", "hora":"05:47 PM", "proceso":"SOLICITUD", "registro":"P017073", "nomCompleto":"Richard Claude De los Reyes Principe"},
	{"codigo":"5", "fecha":"03/12/2011", "hora":"05:47 PM", "proceso":"SOLICITUD", "registro":"P017073", "nomCompleto":"Richard Claude De los Reyes Principe"},
	{"codigo":"6", "fecha":"03/12/2011", "hora":"05:47 PM", "proceso":"SOLICITUD", "registro":"P017073", "nomCompleto":"Richard Claude De los Reyes Principe"}
	];
	
	mostrarTablaLogProcesos(dataTable);
}

function mostrarTablaLogProcesos(data){
	
	$('body').append('<div id="paginador_listLogProceso" class="grid"></div>'); 
	var paginador = "paginador_listLogProceso";
	
	jQuery("#listLogProceso").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado del Log de Procesos",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesLogProc,
		colModel 	: myDataModelLogProc,
		rowList 	: [5,10,15,20,25],
		rowNum 		: 15, 
		pager 		: paginador,
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
}

function consultarAnalisis(operation, message){

	jQuery("#listAnalisis").GridUnload();
	/* AnalisisAction.consultarAjax(function(data){
		mostrarTablaAnalisis(data);
		if(operation == 'logicDelete'){
			alert(message);
		}
	}); */
	
	if(operation == 'logicDelete'){
		alert(message);
	}
	
	var dataTable = [
	{"codProceso":"1", "desProceso":"Proceso 1", "motivo":"Conticasa", "comentario":""},
	{"codProceso":"2", "desProceso":"Proceso 2", "motivo":"Conticasa", "comentario":""},
	{"codProceso":"3", "desProceso":"Proceso 3", "motivo":"Conticasa", "comentario":""},
	{"codProceso":"4", "desProceso":"Proceso 4", "motivo":"Conticasa", "comentario":""},
	{"codProceso":"5", "desProceso":"Proceso 5", "motivo":"Conticasa", "comentario":""},
	{"codProceso":"6", "desProceso":"Proceso 6", "motivo":"Conticasa", "comentario":""}
	];
	
	mostrarTablaAnalisis(dataTable);
}

function mostrarTablaAnalisis(data){
	
	var idTableForm = 'listAnalisis';
	$('body').append('<div id="paginador_'+idTableForm+'" class="grid"></div>'); 
	var paginador = "paginador_"+idTableForm;
	
	jQuery("#"+idTableForm).jqGrid(
	{
		beforeSelectRow: function(){},
		afterInsertRow: function(id, rowdata, rowelem){
			
			if(id == undefined){
				jQuery("#"+idTableForm).jqGrid('editRow', id,
		        {
	       		    keys : true,
	       		    restoreAfterError : true,
	       		    errorfunc: function(rowid, response) {
	       		    	
	      		    	/* var mensaje = response.responseText;
	      		    	deleteTblRowAdded(idTableForm);
	      		    	
	   		    		if (mensaje != "" && mensaje != undefined) {
	   		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
  		    		    }else{
  		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
  		    		    }  */
  		    		    
  		    		    /////////////////frk-INI: codigo para el caso de pruebas
  		    		    var tabla = document.getElementById(idTableForm);
					  	var tablaBody = tabla.getElementsByTagName("tbody")[0];
					  	var filas = tablaBody.getElementsByTagName("tr");
						var contador = 1;
						var idRowEdit = 0;
					
					  	for(var i=0; i<filas.length; i++){
					  		if(filas[i].id == 'undefined'){
					  			
					  			idRowEdit = i;
					  			if(i > 0){
					  				contador = parseFloat(filas[i-1].id);	
					  			}
					  			
					  			if(isNaN(contador)){
					  				contador = 1;
					  			}else{
					  				contador = contador+1;
					  			}
					  		}
					  	}
					  	
					  	lastSelAnalisis = '';
	       		    	var idGenerado = contador;
	       		    	flushTblRowAdded(idTableForm, '', 0, 1, idGenerado);
	       		    	
	       		    	var celdas = filas[idRowEdit].getElementsByTagName("td");
	       		    	for(var i=0; i<celdas.length; i++){
	       		    		if(i != 0){
	       		    			var valorInput = $('input', filas[idRowEdit].getElementsByTagName("td")[i]).attr('value');
	       		    			if(valorInput != undefined){
		       		    			if(valorInput == ""){
		       		    				filas[idRowEdit].getElementsByTagName("td")[i].title = "";
			       		    			filas[idRowEdit].getElementsByTagName("td")[i].innerHTML = "&nbsp;";
		       		    			}else{
		       		    				filas[idRowEdit].getElementsByTagName("td")[i].title = valorInput;
			       		    			filas[idRowEdit].getElementsByTagName("td")[i].innerHTML = valorInput;	
		       		    			}
	       		    			}
	       		    		}
	       		    	}
	  	  				
	    				/////////////////frk-FIN: codigo para el caso de pruebas
	       		    },
	       		    afterrestorefunc: function(rowid) {
	       		    	lastSelAnalisis = '';
	       		    },
	       		    aftersavefunc: function(rowid, response){
	       		    	lastSelAnalisis = '';
	       		    	var idGenerado = response.responseText;
	       		    	flushTblRowAdded(idTableForm, '', 0, 1, idGenerado);
	       		    }
	       		});
			}		
		},
		ondblClickRow: function(id, indexRow, indexCol, e){
			
			if(id && id!==lastSelAnalisis){
				
				if(lastSelAnalisis != ''){
		        	jQuery('#'+idTableForm).restoreRow(lastSelAnalisis,true);
		        }
				
		        var desProceso = document.getElementsByName("desProceso")[0];
		    	if(desProceso != undefined){
		    		if($.trim(desProceso.value) == ''){
		    			
		    			deleteTblRowAdded(idTableForm);
		    			jQuery('#'+idTableForm).jqGrid('editRow',id,
    			        { 
    	        		    keys : true,
    	        		    restoreAfterError : true,
    	        		    errorfunc: function(rowid, response) {
    	        		    	
    	       		    		/* var mensaje = response.responseText;
    	    		    		if (mensaje != "" && mensaje != undefined) {
    	    		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
    	   		    		    }else{
    	   		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
    	   		    		    } */
    	   		    		    
    	   						/////////////////frk-INI: codigo para el caso de pruebas
    	  		    		    lastSelAnalisis = '';
    		    				/////////////////frk-FIN: codigo para el caso de pruebas
    	        		    },
    	        		    afterrestorefunc: function(rowid) {
    	        		    	lastSelAnalisis = '';
    	        		    },
    	        		    aftersavefunc: function(rowid, response){
    	        		    	debugger;
    	        		    	lastSelAnalisis = '';
    	        		    }
    	        		});
    			        
		    			lastSelAnalisis=id;
    			        if(lastSelAnalisis == 'undefined'){
    			        	lastSelAnalisis = '';
    			        }    			        
		    			        
		    		}else{
		    			jQuery("#"+idTableForm).jqGrid('saveRow', "undefined",
		    			{
			       		    restoreAfterError : false,
			       		    errorfunc: function(rowid, response) {
			       		    	debugger;
			      		    	/* var mensaje = response.responseText;
			      		    	deleteTblRowAdded(idTableForm);
			      		    	
			   		    		if (mensaje != "" && mensaje != undefined) {
			   		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
		  		    		    }else{
		  		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
		  		    		    } */
		  		    		    
		  						/////////////////frk-INI: codigo para el caso de pruebas
		  		    		    var tabla = document.getElementById(idTableForm);
							  	var tablaBody = tabla.getElementsByTagName("tbody")[0];
							  	var filas = tablaBody.getElementsByTagName("tr");
								var contador = 1;
							
							  	for(var i=0; i<filas.length; i++){
							  		if(filas[i].id == 'undefined'){
							  			
							  			if(i > 0){
							  				contador = parseFloat(filas[i-1].id);	
							  			}
							  			
							  			if(isNaN(contador)){
							  				contador = 1;
							  			}else{
							  				contador = contador+1;
							  			}
							  		}
							  	}
							  	
							  	lastSelAnalisis = '';
			       		    	var idGenerado = contador;
			       		    	flushTblRowAdded(idTableForm, '', 0, 1, idGenerado);
			       		    	
			       		    	var celdas = filas[idRowEdit].getElementsByTagName("td");
			       		    	for(var i=0; i<celdas.length; i++){
			       		    		if(i != 0){
			       		    			var valorInput = $('input', filas[idRowEdit].getElementsByTagName("td")[i]).attr('value');
			       		    			if(valorInput != undefined){
				       		    			if(valorInput == ""){
				       		    				filas[idRowEdit].getElementsByTagName("td")[i].title = "";
					       		    			filas[idRowEdit].getElementsByTagName("td")[i].innerHTML = "&nbsp;";
				       		    			}else{
				       		    				filas[idRowEdit].getElementsByTagName("td")[i].title = valorInput;
					       		    			filas[idRowEdit].getElementsByTagName("td")[i].innerHTML = valorInput;	
				       		    			}
			       		    			}
			       		    		}
			       		    	}
			    				/////////////////frk-FIN: codigo para el caso de pruebas
			       		    },
			       		    aftersavefunc: function(rowid, response){
			       		    	debugger;
			       		    	lastSelAnalisis = '';
			       		    	var idGenerado = response.responseText;
			       		    	flushTblRowAdded(idTableForm, '', 0, 1, idGenerado);
			       		    }
			       		});
		    		}			        
		    	}else{
		    		
		    		jQuery('#'+idTableForm).jqGrid('editRow',id,
    		        { 
            		    keys : true,
            		    restoreAfterError : true,
            		    errorfunc: function(rowid, response) {
            		    	debugger;
           		    		/* var mensaje = response.responseText;
        		    		if (mensaje != "" && mensaje != undefined) {
        		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
       		    		    }else{
       		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
       		    		    } */
       		    		    
       						/////////////////frk-INI: codigo para el caso de pruebas
						  	lastSelAnalisis = '';
    	    				/////////////////frk-FIN: codigo para el caso de pruebas
            		    },
            		    afterrestorefunc: function(rowid) {
            		    	lastSelAnalisis = '';
            		    },
            		    aftersavefunc: function(rowid, response){
            		    	lastSelAnalisis = '';
            		    }
            		});
    		        
		    		lastSelAnalisis=id;
    		        if(lastSelAnalisis == 'undefined'){
    		        	lastSelAnalisis = '';
			        }
		    	}
		    }
		},
		onPaging: function(pgButton) {
			deleteTblRowAdded(idTableForm);
        },
        
        //frk: descomentar esta url cuando tengan un action para el 
        //guardado de informacion despues de la edicion del registro
        
		//editurl		: rutaContexto+'/adminForm.do?method=guardarRegAnalisisAjax',
		caption		: "Listado de Analisis de los Procesos",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesAnalisis,
		colModel 	: myDataModelAnalisis,
		rowList 	: [5,10,15,20,25],
		rowNum 		: 15, 
		pager 		: paginador,
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
}

function addRegAnalisis(){
	var rowid = "-1";
	var mydataadd = 
		  [{
			codProceso	: rowid,			
			desProceso 	: "",
			motivo  	: "",
			comentario  : ""
		  }];
	
	if(lastSelAnalisis != ''){
		jQuery('#listAnalisis').restoreRow(lastSelAnalisis,true);
	}
	
	lastSelAnalisis = '';
	
	var codProceso = $("#undefined_codProceso");
	if(codProceso.length == 0){
		jQuery("#listAnalisis").addRowData(rowid, mydataadd, 'first');
	}
}

function deleteRegAnalisis(){
	var selecciones = buscarSelecciones("listAnalisis");
	if (selecciones.length == 0){
		alert('No ha seleccionado ningún elemento para la eliminación.');
	}else{
		
		var ans = confirm('¿Esta seguro que desea eliminar los registros seleccionados?');
		if(ans){
			
			/* AdminAction.eliminarRegAnalisisAjax(selecciones, function(msg){
				
				var paramDelete = '';
				if(msg != ''){
					paramDelete = 'logicDelete';
				}
				
				consultarAnalisis(paramDelete, msg);
			}); */
			consultarAnalisis('logicDelete', 'Se elimino satisfactoriamente');
		}
	}
}

</script>
	
</head>
<body onload="consultarProductos();consultarAnalisis('','');consultarLogProcesos();">

<form name="formAnalisisDictamen" method="post">

	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Módulo de Análisis y Dictamen</b></font>
	</div>

	<br/>
	<table width="740px" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<font face="Arial Narrow" size="3" color="#000080"><b>Código Central</b></font>&nbsp;
			<input type="text" name="codigoCentral" class="cajaTexto" id="codigoCentral" size="10" maxlength="8">
			&nbsp;<a href="javascript:showBodyForm();"><img src="imagenes/lupa.gif" border="0" height="18"></a>
		</td>
	</tr>
	</table>
	<br/>
	
	<fieldset style="width: 830px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	Datos del Cliente
   	</font></legend>
	<table>	
	<tr>
		<td colspan="4" valign="middle">
        	<font face="Arial Narrow" size="3" color="#000080"><b>Nro. Solicitud</b></font>&nbsp;
			<input type="text" name="nroSolicitud" class="cajaTexto" id="nroSolicitud" size="19" maxlength="19">&nbsp;&nbsp;
			
			<font face="Arial Narrow" size="3" color="#000080"><b>Tipo Persona *</b></font>&nbsp;
			<input type="text" name="tipoPersona" class="cajaTexto" id="tipoPersona" size="10" maxlength="8">&nbsp;&nbsp;
			
			<font face="Arial Narrow" size="3" color="#000080"><b>Ruc / DNI *</b></font>&nbsp;
			<input type="text" name="rucDni" class="cajaTexto" id="rucDni" size="27" maxlength="27">
			
       </td>
    </tr>
    <tr>
       <td colspan="4" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Razon Social / Apellidos y Nombres *</b></font>&nbsp;
			<input type="text" name="razonSocial" class="cajaTexto" id="razonSocial" size="72" maxlength="72">
       </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Oficina Principal *</b></font>
       </td>
       <td align="left" valign="middle">
			<input type="text" name="codOficina" class="cajaTexto" id="codOficina" size="10" maxlength="10">
			<input type="text" name="desOficina" class="cajaTexto" id="desOficina" size="20" maxlength="20">&nbsp;&nbsp;
	   </td>
	   <td align="left" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Gestor *</b></font>
	   </td>
	   <td align="left" valign="middle">	
			<input type="text" name="codGestor" class="cajaTexto" id="codGestor" size="10" maxlength="10">
			<input type="text" name="desGestor" class="cajaTexto" id="desGestor" size="20" maxlength="20">
       </td>
	</tr>
	<tr>
	   <td align="left" valign="middle">
	   		<font face="Arial Narrow" size="3" color="#000080"><b>Evaluador</b></font>
	   		&nbsp;<a href="javascript:showBodyForm();"><img src="imagenes/rojo.gif" border="0" height="18"></a>
	   </td>
	   <td align="left" valign="middle">
			<input type="text" name="codEvaluador" class="cajaTexto" id="codEvaluador" size="10" maxlength="10">
			<input type="text" name="desEvaluador" class="cajaTexto" id="desEvaluador" size="20" maxlength="20">&nbsp;&nbsp;
	   </td>
       <td align="left" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Empleador *</b></font>
	   </td>
	   <td align="left" valign="middle">
			<input type="text" name="codEmpleador" class="cajaTexto" id="codEmpleador" size="10" maxlength="10">
			<input type="text" name="desEmpleador" class="cajaTexto" id="desEmpleador" size="20" maxlength="20">
       </td>
	</tr>
	<tr>
	   <td align="left" valign="middle">
	   		<font face="Arial Narrow" size="3" color="#000080"><b>Jefe Inmediato</b></font>
	   </td>
	   <td align="left" valign="middle">
			<input type="text" name="codJefe" class="cajaTexto" id="codJefe" size="10" maxlength="10">
			<input type="text" name="desJefe" class="cajaTexto" id="desJefe" size="20" maxlength="20">&nbsp;&nbsp;
	   </td>
       <td align="left" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Fecha Hoy</b></font>
	   </td>
	   <td align="left" valign="middle">
			<input type="text" name="fechaHoy" class="cajaTexto" id="fechaHoy" size="20" maxlength="20">
       </td>
	</tr>
	</table>
	</fieldset>
	
	<fieldset style="width: 830px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	Datos del Análisis
   	</font></legend>
	
	<a href="javascript:deleteRegAnalisis();" class="buttonOHC">ELIMINAR</a>&nbsp;
	<a href="javascript:addRegAnalisis();" class="buttonOHC">AGREGAR</a>
	<br><br>
	
	<table id="listAnalisis" class="grid">
	</table>
	
	</fieldset>
	
	<fieldset style="width: 830px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	Datos de la Dictaminación
   	</font></legend>
	
	<br/>
	<table style="width: 800px" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Dictamen</b></font>
		</td>
		<td align="left" valign="middle">
			<select id="slctDictamen" name="slctDictamen">
				<option value="-1">----</option>			
			</select>
		</td>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Nivel Aprobación</b></font>
		</td>
		<td valign="middle">
			<select id="slctNivAprob" name="slctNivAprob">
				<option value="-1">----</option>			
			</select>
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Moneda Solicitud</b></font>
		</td>
		<td align="left" valign="middle">
			<select id="tipoMoneda" name="tipoMoneda">
				<option value="SL">SOLES</option>
				<option value="DL">DÓLARES</option>
			</select>
		</td>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Riesgo Total</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="riesgoTotal" class="cajaTexto" id="riesgoTotal" size="15" maxlength="15">
			&nbsp;<a href="javascript:showBodyForm();"><img src="imagenes/rojo.gif" border="0" height="18"></a>
		</td>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Monto Aprobado</b></font>
		</td>
		<td align="left" valign="middle">
			<input type="text" name="montoAprobado" class="cajaTexto" id="montoAprobado" size="15" maxlength="15">
		</td>	
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Ctrl Rating (C/O/I)</b></font>
		</td>
		<td align="left" valign="middle">
			<input type="text" name="montoAprobado" class="cajaTexto" id="montoAprobado" size="15" maxlength="15">
			&nbsp;<font face="Arial Narrow" size="3" color="#000080"><b>Cual</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="montoAprobado" class="cajaTexto" id="montoAprobado" size="15" maxlength="15">
			&nbsp;<font face="Arial Narrow" size="3" color="#000080"><b>Cuant</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="montoAprobado" class="cajaTexto" id="montoAprobado" size="15" maxlength="15">
			&nbsp;<font face="Arial Narrow" size="3" color="#000080"><b>Icom</b></font>
		</td>
		<td colspan="2" valign="middle">
			<input type="text" name="montoAprobado" class="cajaTexto" id="montoAprobado" size="15" maxlength="15">
			&nbsp;<font face="Arial Narrow" size="3" color="#000080"><b>CAlertas</b></font>
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Proactividad</b></font>
		</td>
		<td colspan="2" align="left" valign="middle">
			<select id="slctProactividad" style="width: 250px" name="slctProactividad">
				<option value="-1">----</option>			
			</select>
		</td>
		<td align="right" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Otr</b></font>&nbsp;&nbsp;
		</td>
		<td colspan="2" align="left" valign="middle">
			<input type="text" name="valOtr" class="cajaTexto" id="valOtr" size="15" maxlength="15">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Ctrl Scoring (C/O/I)</b></font>
		</td>
		<td colspan="2" align="left" valign="middle">
			<input type="text" name="ctrlScoring" class="cajaTexto" id="ctrlScoring" size="47" maxlength="47">
		</td>
		<td colspan="2" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Fecha Vencimiento de la Solicitud dictaminada</b></font>&nbsp;&nbsp;
		</td>
		<td colspan="2" align="left" valign="middle">
			<input type="text" name="fecVencimiento" class="cajaTexto" id="fecVencimiento" size="15" maxlength="15">
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td colspan="5" valign="middle">
			<input type="button" class="buttonGPR"  name="btnDictaminar" id="btnDictaminar" value="Dictaminar / Dictamen Superior">		
		</td>
	</tr>
	</table>
	</fieldset>
	
	<fieldset style="width: 1000px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	Datos del Producto
   	</font></legend>
	<table style="width: 740px">	
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Banca</b></font>&nbsp;       
	   		<select id="bancaCliente" name="bancaCliente">
				<option value="BP">Banca Personas</option>
				<option value="BC">Banca Corporativa</option>			
				<option value="BM">Banca Mayorista</option>
				<option value="BE">Banca Empresas</option>
			</select>
	   </td>
	   <td align="center" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Moneda</b></font>&nbsp;       
	   		<select id="tipoMoneda" name="tipoMoneda">
				<option value="SL">SOLES</option>
				<option value="DL">DÓLARES</option>
			</select>
       </td>
       <td align="right" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Monto total</b></font>&nbsp;
	   		<input type="text" name="montoTotal" class="cajaTexto" id="montoTotal" size="20" maxlength="20">
	   		
	   		&nbsp;<input type="button" class="buttonGPR"  name="btnBuscar" id="btnBuscar" value="Buscar">
       </td>
	</tr>
	</table>
	
	<br/>
	<table id="listProducts" class="grid">
	</table>	
	</fieldset>
	
	<fieldset style="width: 1000px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	Datos de Riesgo del Cliente
   	</font></legend>
   	
   	<table>
   	<tr>
   	
   	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font face="Arial Narrow" size="3" color="#000080"><b>Rating / Scorating / Scoring *</b></font>
        </td>
        <td valign="middle">
			<input type="text" name="rating" class="cajaTexto" id="rating" size="19" maxlength="19">
        </td>
    </tr>
    <tr>
       <td valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Clasificación del Cliente *</b></font>
       </td>
       <td valign="middle">
			<input type="text" name="clasifCliente" class="cajaTexto" id="clasifCliente" size="19" maxlength="19">
       </td>
	</tr>
	</table>
	</td>
	
	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font face="Arial Narrow" size="3" color="#000080"><b>Relevancia Pública *</b></font><br/>
        	
			<input type="text" name="relevPublica1" class="cajaTexto" id="relevPublica1" size="19" maxlength="19"><br/>
			<input type="text" name="relevPublica2" class="cajaTexto" id="relevPublica2" size="19" maxlength="19"><br/>
			<input type="text" name="relevPublica3" class="cajaTexto" id="relevPublica3" size="19" maxlength="19"><br/>
			<input type="text" name="relevPublica4" class="cajaTexto" id="relevPublica4" size="19" maxlength="19"><br/>
			<input type="text" name="relevPublica5" class="cajaTexto" id="relevPublica5" size="19" maxlength="19">
			
       </td>
    </tr>
	</table>
	</td>
	
	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font face="Arial Narrow" size="3" color="#000080"><b>Deuda Directa *</b></font>
        </td>
        <td valign="middle">
			<input type="text" name="deudaDirecta" class="cajaTexto" id="deudaDirecta" size="19" maxlength="19">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Deuda Indirecta *</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="deudaIndirecta" class="cajaTexto" id="deudaIndirecta" size="19" maxlength="19">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Deuda Castigo *</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="deudaCastigo" class="cajaTexto" id="deudaCastigo" size="19" maxlength="19">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Deuda en el Sistema Financiero *</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="deudaSF" class="cajaTexto" id="deudaSF" size="19" maxlength="19">
       </td>
    </tr>
	</table>
	</td>
	
	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font face="Arial Narrow" size="3" color="#000080"><b>Otros Riesgos</b></font>
        </td>
        <td valign="middle">
			<input type="text" name="otrosRiesgos" class="cajaTexto" id="otrosRiesgos" size="19" maxlength="19">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Riesgo Grupal</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="riesgoGrupal" class="cajaTexto" id="riesgoGrupal" size="19" maxlength="19">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Riesgo Actual</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="riesgoActual" class="cajaTexto" id="riesgoActual" size="19" maxlength="19">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Riesgo Total</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="riesgoTotal" class="cajaTexto" id="riesgoTotal" size="19" maxlength="19">
       </td>
    </tr>
	</table>
	</td>
	
	</tr>
	</table>
	</fieldset>
	
	<fieldset style="width: 830px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	Datos de la Oficina y Ejecutivo
   	</font></legend>
	<table>	
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Ejecutivo de Cuenta *</b></font>
       </td>
       <td align="left" valign="middle">
			<input type="text" name="codCuentaEjecutivo" class="cajaTexto" id="codCuentaEjecutivo" size="10" maxlength="10">
			<input type="text" name="desCuentaEjecutivo" class="cajaTexto" id="desCuentaEjecutivo" size="20" maxlength="20">
	   </td>
	   <td align="right" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Fecha de Ingreso Oficina *</b></font>&nbsp;
			<input type="text" name="fechaIngresoOfic" class="cajaTexto" id="fechaIngresoOfic" size="20" maxlength="20">
       </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Oficina de Alta *</b></font>
       </td>
       <td align="left" valign="middle">
			<input type="text" name="codOficinaAlta" class="cajaTexto" id="codOficinaAlta" size="10" maxlength="10">
			<input type="text" name="desOficinaAlta" class="cajaTexto" id="desOficinaAlta" size="20" maxlength="20">&nbsp;&nbsp;
	   </td>
	   <td align="right" valign="middle">&nbsp;</td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Gerencia Territorial *</b></font>
       </td>
       <td align="left" valign="middle">
			<input type="text" name="codGerenciaTerrit" class="cajaTexto" id="codGerenciaTerrit" size="10" maxlength="10">
			<input type="text" name="desGerenciaTerrit" class="cajaTexto" id="desGerenciaTerrit" size="20" maxlength="20">&nbsp;&nbsp;
	   </td>
	   <td align="right" valign="middle">&nbsp;</td>
	</tr>
	</table>
	</fieldset>
	
	<fieldset style="width: 830px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	Datos del Log de Procesos
   	</font></legend>
		
	<table id="listLogProceso" class="grid">
	</table>
		
	</fieldset>
	
</form>

</body>
</html>