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
	
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/ProductoAction.js'></script>
	
	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util/formatters.js" type="text/javascript"></script>
	
	<script type='text/javascript' src='<%= request.getContextPath()%>/script.js'></script>

<script type="text/javascript">

//frk: definir en la siguiente función todos los campos tipo calendario 
//para darles via jquery esta funcionalidad

$(function() {
    $( "#fechaIngresoOfic" ).datepicker({dateFormat: 'dd/mm/yy'});
});

//frk: para el caso de IExplorer en el que no 
//funciona bien el "getElementsByName" 

if (jQuery.browser.msie){
	document.getElementsByName = function(name, tag){
	    return getElementsByName_iefix(name, tag);
	};
}

var valCampaignGeneric = '';
var valBancaGeneric = '';
var lastSelProducto = '';
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

//frk: si damos agregar y luego ESC se debe eliminar la fila agregada
$(document).keyup(function(e) {
  //frk: se presiono el ESC
  var tecla = (document.all) ? e.keyCode : e.which; 
  if (tecla == 27) {
	  lastSelProducto = '';
	  deleteTblRowAdded('listProducts');
  }
});

var myColNames  = ['', '', '', '', 'Producto', 'Contrato Vinculado', 'Cod. Pre Evaluador', 'Campaña', 'Tipo', 'Monto', 'Plazo (Meses)', 'Garantia'];
var myDataModel = [ {name : 'codProducto',			index : 'codProducto', 			width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true, required:true}, hidden:true},
                    {name : 'valBanca',				index : 'valBanca', 			width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                    {name : 'valMoneda',			index : 'valMoneda', 			width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                    {name : 'valMontoTotal',		index : 'valMontoTotal', 		width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                    {name : 'desProducto',			index : 'desProducto', 			width : VAL_WIDTH.VMED, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: desProductoElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: desProductoFormat, unformat: genericUnFormat},
                    {name : 'contratoVinculado',	index : 'contratoVinculado',	width : VAL_WIDTH.VMED, 	editable:true,	edittype:'custom',  editoptions: {custom_element: contratoVincElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: contratoVincFormat, unformat: genericUnFormat},
                    {name : 'codPreEvaluador',		index : 'codPreEvaluador', 		width : VAL_WIDTH.SMALL, 	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 255}, editrules: {required: true}, align : 'center'},
                    {name : 'campania',				index : 'campania', 			width : VAL_WIDTH.VMED, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: campaniaElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: campaniaFormat, unformat: genericUnFormat},
                    {name : 'tipo',					index : 'tipo', 				width : VAL_WIDTH.XLSMALL, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: tipoElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: tipoFormat, unformat: genericUnFormat},
                    {name : 'monto',				index : 'monto', 				width : VAL_WIDTH.XLSMALL, 	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 10, style: 'text-align: right'}, 	editrules: {required: true, number: true, minValue: 0}, align : 'center'},
                    {name : 'plazo',				index : 'plazo', 				width : VAL_WIDTH.XLSMALL, 	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 6,  style: 'text-align: right'}, editrules: {required: true, integer: true, minValue: 0, maxValue: 999999}, align : 'center'},
                    {name : 'garantia',				index : 'garantia', 			width : VAL_WIDTH.SMALL, 	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 255}, editrules: {required: true}, align : 'center'}
                   ];


function setEventsValidationProducto(){
    jQuery("select[name=desProducto]").bind("change",function (evnt){
    	changeCampaignByProduct(this);
    });
}

function changeCampaignByProduct(obj){
	var indice = (obj.id).split("desProducto");
	dwr.util.removeAllOptions(indice[0]+"campania");
	
	ProductoAction.cargarCampaniasPorProducto(obj.value, function(data){
		dwr.util.addOptions(indice[0]+"campania", data, 'codParametro','desParametro');
	  	setElementSelected(dwr.util.byId(indice[0]+"campania"), valCampaignGeneric);
	});
}

function desProductoElementCustom(valElement, options) {
	
  var bancaCliente = document.getElementById("bancaCliente");
  ProductoAction.cargarProductosPorBanca(bancaCliente.value, function(data){
  	dwr.util.addOptions(options.id, data, 'codParametro','desParametro');
  	setElementSelected(dwr.util.byId(options.id), valElement);
  	
  	//frk: cargamos el combo de campanias una vez este 
  	//terminado la carga del combo producto
  	changeCampaignByProduct(dwr.util.byId(options.id));
  });
  
  return document.createElement("select");
}

function campaniaElementCustom(valElement, options) {
  valCampaignGeneric = valElement;  
  return document.createElement("select");
}

function tipoElementCustom(valElement, options){

	ProductoAction.cargarTipos(function(data){
		dwr.util.addOptions(options.id, data, 'codParametro','desParametro');
		setElementSelected(dwr.util.byId(options.id), valElement);
	});
	  
	return document.createElement("select");
}

function contratoVincElementCustom(valElement, options) {
  var bancaCliente = document.getElementById("bancaCliente");
  if(bancaCliente.value == 'BP'){ //frk: en el caso de Banca Personas, sera un combobox
	  
	  ProductoAction.cargarContratosVincPorBanca(bancaCliente.value, function(data){
	  	dwr.util.addOptions(options.id, data, 'codParametro','desParametro');
		setElementSelected(dwr.util.byId(options.id), valElement);
	  });
	  
	  return document.createElement("select");  
  }else{ //frk: en caso contrario, sera un textbox
	  
	  //frk: el valor esta llegando tal y como lo armamos en 
	  //contratoVincFormat() es por eso que lo vamos a splitear
	  var valReal = valElement.split("<input");
	  var el = document.createElement("input");
	  el.type="text";
	  el.value = valReal[0];
	  return el;
  }
}
 
function consultarProductos(operation, message){
	
	var formulario = document.getElementById('formParametroList');
	var codBanca = formulario.bancaCliente.value;
	alert(codBanca);
	jQuery("#listProducts").GridUnload();
	ProductoAction.consultarAjax(codBanca, function(data){
		mostrarTabla(data);
		if(operation == 'logicDelete'){
			alert(message);
		}
	});
}

function mostrarTabla(data){
	
	var idTableForm = 'listProducts';
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
	       		    	
	      		    	var mensaje = response.responseText;
	      		    	deleteTblRowAdded(idTableForm);
	      		    	
	   		    		if (mensaje != "" && mensaje != undefined) {
	   		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
  		    		    }else{
  		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
  		    		    }   		    		
	       		    },
	       		    afterrestorefunc: function(rowid) {
	       		    	lastSelProducto = '';
	       		    },
	       		    aftersavefunc: function(rowid, response){
	       		    	lastSelProducto = '';
	       		    	var idGenerado = response.responseText;
	       		    	flushTblRowAdded(idTableForm, '', 0, 1, idGenerado);
	       		    }
	       		});
				
				setEventsValidationProducto();
			}		
		},
		ondblClickRow: function(id, indexRow, indexCol, e){
			
			if(id && id!==lastSelProducto){
				
				if(lastSelProducto != ''){
		        	jQuery('#'+idTableForm).restoreRow(lastSelProducto,true);
				}
				
		    	var desProducto = document.getElementsByName("desProducto")[0];
		    	if(desProducto != undefined){
		    		if($.trim(desProducto.value) == ''){
		    			
		    			deleteTblRowAdded(idTableForm);
		    			jQuery('#'+idTableForm).jqGrid('editRow',id,
    			        { 
    	        		    keys : true,
    	        		    restoreAfterError : true,
    	        		    errorfunc: function(rowid, response) {
    	        		    	
    	       		    		var mensaje = response.responseText;
    	    		    		if (mensaje != "" && mensaje != undefined) {
    	    		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
    	   		    		    }else{
    	   		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
    	   		    		    }
    	        		    },
    	        		    afterrestorefunc: function(rowid) {
    	        		    	lastSelProducto = '';
    	        		    },
    	        		    aftersavefunc: function(rowid, response){
    	        		    	lastSelProducto = '';
    	        		    }
    	        		});
		    			
		    			setEventsValidationProducto();
		    			
		    			lastSelProducto=id;
    			        if(lastSelProducto == 'undefined'){
    			        	lastSelProducto = '';
    			        }    			        
		    			        
		    		}else{
		    			jQuery("#"+idTableForm).jqGrid('saveRow', "undefined",
		    			{
			       		    restoreAfterError : false,
			       		    errorfunc: function(rowid, response) {
			       		    	
			      		    	var mensaje = response.responseText;
			      		    	deleteTblRowAdded(idTableForm);
			      		    	
			   		    		if (mensaje != "" && mensaje != undefined) {
			   		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
		  		    		    }else{
		  		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
		  		    		    }   		    		
			       		    },
			       		    aftersavefunc: function(rowid, response){
			       		    	lastSelProducto = '';
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
            		    	
           		    		var mensaje = response.responseText;
        		    		if (mensaje != "" && mensaje != undefined) {
        		    			setTimeout("alert('"+mensaje+"','Cerrar',{left:460})", 250);        		    			
       		    		    }else{
       		    		    	setTimeout("alert('Ocurrió un error al intentar guardar la información.','Cerrar',{left:460})", 250);
       		    		    }
            		    },
            		    afterrestorefunc: function(rowid) {
            		    	lastSelProducto = '';
            		    },
            		    aftersavefunc: function(rowid, response){
            		    	lastSelProducto = '';
            		    }
            		});
		    		
		    		setEventsValidationProducto();
    		        
		    		lastSelProducto=id;
    		        if(lastSelProducto == 'undefined'){
    		        	lastSelProducto = '';
			        }
		    	}
		    }
		},
		onPaging: function(pgButton) {
			deleteTblRowAdded(idTableForm);
        },
		editurl		: rutaContexto+'/productForm.do?method=guardarProductoAjax',
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

function addProducto(){
	
	var bancaCliente = document.getElementById("bancaCliente");
	var tipoMoneda = document.getElementById("tipoMoneda");
	var montoTotal = document.getElementById("montoTotal");
	
	if(bancaCliente.value == '-1'){
		alert("Debe seleccionar una banca para poder agregar un registro.");
	}else{
		var rowid = "-1";
		var mydataadd = 
			  [{
				codProducto			: rowid,
				valBanca			: bancaCliente.value,
                valMoneda			: tipoMoneda.value,
                valMontoTotal		: montoTotal.value,
				desProducto 		: "",
				contratoVinculado 	: "",
				codPreEvaluador 	: "",
				campania  			: "",
				tipo    			: "",
				monto  				: "",
				plazo  				: "",
				garantia  			: ""
			  }];	
		
		if(lastSelProducto != ''){
			jQuery('#listProducts').restoreRow(lastSelProducto,true);
		}
		
		lastSelProducto = '';
		
		var subProducto = $("#undefined_desProducto");
		if(subProducto.length == 0){
			valBancaGeneric = bancaCliente.value;
			jQuery("#listProducts").addRowData(rowid, mydataadd, 'first');
		}		
	}
}

function deleteProducto(){
	var selecciones = buscarSelecciones("listProducts");
	if (selecciones.length == 0){
		alert('No ha seleccionado ningún elemento para la eliminación.');
	}else{
		
		var ans = confirm('¿Esta seguro que desea eliminar los registros seleccionados?');
		if(ans){
			
			ProductoAction.eliminarProductoAjax(selecciones, function(msg){
				
				var paramDelete = '';
				if(msg != ''){
					paramDelete = 'logicDelete';
				}
				
				consultarProductos(paramDelete, msg);
			});			
		}
	}
}

function changeBankListProducts(obj){
	
	if(valBancaGeneric == ''){
		valBancaGeneric = obj.value;
	}else if(valBancaGeneric != obj.value){
		var tabla = document.getElementById("listProducts");
	  	var tablaBody = tabla.getElementsByTagName("tbody")[0];
	  	var filas = tablaBody.getElementsByTagName("tr");
	  	if(filas.length > 1){
	  		var ans = confirm('Se cambio la banca seleccionada, se eliminarán los registros agregados. ¿Esta seguro que desea continuar?');
			if(ans){
				//frk: aca se limpia la grilla para volver añadir los 
				//registros relacionados a la nueva banca en seleccion
				valBancaGeneric = obj.value;
				consultarProductos('','');
			}else{
				obj.value = valBancaGeneric;
			}
	  	}
	}	
}

function showBodyForm(){
}

</script>
	
</head>
<body onload="consultarProductos('','');">

<form name="formIngresoSolicitud" method="post">

	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Módulo de Ingreso de la Solicitud</b></font>
	</div>

	<br/>
	<table width="740px" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<font class="fontText"><b>Código Central</b></font>&nbsp;
			<input type="text" name="codigoCentral" class="cajaTexto" id="codigoCentral" size="10" maxlength="8">
			&nbsp;<a href="javascript:showBodyForm();"><img src="imagenes/lupa.gif" border="0" height="18"></a>
		</td>
	</tr>
	</table>
	<br/>
	
	<fieldset style="width: 830px">
   	<legend>
   	<font class="fontText">
   	Datos del Cliente
   	</font></legend>
	<table>	
	<tr>
		<td colspan="2" valign="middle">
        	<font class="fontText"><b>Nro. Solicitud</b></font>&nbsp;
			<input type="text" name="nroSolicitud" class="cajaTexto" id="nroSolicitud" size="19" maxlength="19">&nbsp;
			
			<font class="fontText"><b>Tipo Persona *</b></font>&nbsp;
			<input type="text" name="tipoPersona" class="cajaTexto" id="tipoPersona" size="10" maxlength="8">&nbsp;
			
			<font class="fontText"><b>Ruc / DNI *</b></font>&nbsp;
			<input type="text" name="rucDni" class="cajaTexto" id="rucDni" size="27" maxlength="27">
       </td>
    </tr>
    <tr>
       <td colspan="2" valign="middle">
       		<font class="fontText"><b>Razon Social / Apellidos y Nombres *</b></font>&nbsp;
			<input type="text" name="razonSocial" class="cajaTexto" id="razonSocial" size="76" maxlength="76">
       </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText"><b>Oficina Principal *</b></font>&nbsp;
			<input type="text" name="codOficina" class="cajaTexto" id="codOficina" size="10" maxlength="10">
			<input type="text" name="desOficina" class="cajaTexto" id="desOficina" size="20" maxlength="20">&nbsp;&nbsp;
	   </td>
	   <td align="right" valign="middle">
			<font class="fontText"><b>Gestor *</b></font>&nbsp;
			<input type="text" name="codGestor" class="cajaTexto" id="codGestor" size="10" maxlength="10">
			<input type="text" name="desGestor" class="cajaTexto" id="desGestor" size="20" maxlength="20">
       </td>
	</tr>
	<tr>
	   <td align="left">&nbsp;</td>
       <td align="right" valign="middle">
			<font class="fontText"><b>Empleador *</b></font>&nbsp;
			<input type="text" name="codEmpleador" class="cajaTexto" id="codEmpleador" size="10" maxlength="10">
			<input type="text" name="desEmpleador" class="cajaTexto" id="desEmpleador" size="20" maxlength="20">
       </td>
	</tr>
	</table>
	</fieldset>
	
	<fieldset style="width: 830px">
   	<legend>
   	<font class="fontText">
   	Datos de la Oficina y Ejecutivo
   	</font></legend>
	<table>	
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText"><b>Ejecutivo de Cuenta *</b></font>
       </td>
       <td align="left" valign="middle">
			<input type="text" name="codCuentaEjecutivo" class="cajaTexto" id="codCuentaEjecutivo" size="10" maxlength="10">
			<input type="text" name="desCuentaEjecutivo" class="cajaTexto" id="desCuentaEjecutivo" size="20" maxlength="20">
	   </td>
	   <td align="right" valign="middle">
			<font class="fontText"><b>Fecha de Ingreso Oficina *</b></font>&nbsp;
			<input type="text" name="fechaIngresoOfic" class="cajaTexto" id="fechaIngresoOfic" size="20" maxlength="20">
       </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText"><b>Oficina de Alta *</b></font>
       </td>
       <td align="left" valign="middle">
			<input type="text" name="codOficinaAlta" class="cajaTexto" id="codOficinaAlta" size="10" maxlength="10">
			<input type="text" name="desOficinaAlta" class="cajaTexto" id="desOficinaAlta" size="20" maxlength="20">&nbsp;&nbsp;
	   </td>
	   <td align="right" valign="middle">&nbsp;</td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText"><b>Gerencia Territorial *</b></font>
       </td>
       <td align="left" valign="middle">
			<input type="text" name="codGerenciaTerrit" class="cajaTexto" id="codGerenciaTerrit" size="10" maxlength="10">
			<input type="text" name="desGerenciaTerrit" class="cajaTexto" id="desGerenciaTerrit" size="20" maxlength="20">&nbsp;&nbsp;
	   </td>
	   <td align="right" valign="middle">&nbsp;</td>
	</tr>
	</table>
	</fieldset>
	
	<div style="overflow: scroll;"></div>
		<fieldset style="width: 1100px">
   	<legend>
   	<font class="fontText">
   	Datos del Producto
   	</font></legend>
	<table style="width: 740px">	
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText"><b>Banca</b></font>&nbsp;       
	   		<select id="bancaCliente" name="bancaCliente" onchange="changeBankListProducts(this);">
				<option value="-1">-- Seleccionar Banca --</option>
				<option value="BP">Banca Personas</option>
				<option value="BC">Banca Corporativa</option>			
				<option value="BM">Banca Mayorista</option>
				<option value="BE">Banca Empresas</option>
			</select>
	   </td>
	   <td align="center" valign="middle">
			<font class="fontText"><b>Moneda</b></font>&nbsp;       
	   		<select id="tipoMoneda" name="tipoMoneda">
				<option value="SL">SOLES</option>
				<option value="DL">DÓLARES</option>
			</select>
       </td>
       <td align="right" valign="middle">
			<font class="fontText"><b>Monto total</b></font>&nbsp;
	   		<input type="text" name="montoTotal" class="cajaTexto" id="montoTotal" size="20" maxlength="20">
       </td>
	</tr>
	</table>
	<br/>
	
	<a href="javascript:deleteProducto();" class="buttonGPR">ELIMINAR</a>&nbsp;
	<a href="javascript:addProducto();" class="buttonGPR">AGREGAR</a>
	
	<br/><br/>
	<table id="listProducts" class="grid">
	</table>
	</fieldset>
	<fieldset style="width: 1000px">
   	<legend>
   	<font class="fontText">
   	Datos de Riesgo del Cliente
   	</font></legend>
   	
   	<table>
   	<tr>
   	
   	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font class="fontText"><b>Rating / Scorating / Scoring *</b></font>
        </td>
        <td valign="middle">
			<input type="text" name="rating" class="cajaTexto" id="rating" size="19" maxlength="19">
        </td>
    </tr>
    <tr>
       <td valign="middle">
       		<font class="fontText"><b>Clasificación del Cliente *</b></font>
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
        	<font class="fontText"><b>Relevancia Pública *</b></font><br/>
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
        	<font class="fontText"><b>Deuda Directa *</b></font>
        </td>
        <td valign="middle">
			<input type="text" name="deudaDirecta" class="cajaTexto" id="deudaDirecta" size="19" maxlength="19">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText"><b>Deuda Indirecta *</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="deudaIndirecta" class="cajaTexto" id="deudaIndirecta" size="19" maxlength="19">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText"><b>Deuda Castigo *</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="deudaCastigo" class="cajaTexto" id="deudaCastigo" size="19" maxlength="19">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText"><b>Deuda en el Sistema Financiero *</b></font>
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
        	<font class="fontText"><b>Otros Riesgos</b></font>
        </td>
        <td valign="middle">
			<input type="text" name="otrosRiesgos" class="cajaTexto" id="otrosRiesgos" size="19" maxlength="19" onkeypress="ingresoNumeros(event);">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText"><b>Riesgo Grupal</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="riesgoGrupal" class="cajaTexto" id="riesgoGrupal" size="19" maxlength="19" onkeypress="ingresoNumeros(event);">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText"><b>Riesgo Actual</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="riesgoActual" class="cajaTexto" id="riesgoActual" size="19" maxlength="19" onkeypress="ingresoNumeros(event);">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText"><b>Riesgo Total</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="riesgoTotal" class="cajaTexto" id="riesgoTotal" size="19" maxlength="19" onkeypress="ingresoNumeros(event);">
       </td>
    </tr>
	</table>
	</td>
	
	</tr>
	</table>
	</fieldset>
	
</form>

</body>
</html>