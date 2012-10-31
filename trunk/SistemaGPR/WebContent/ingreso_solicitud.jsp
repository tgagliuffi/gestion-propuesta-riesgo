<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bbva.pe.gpr.bean.Banca"%>
<%@ page   import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
		
	<script src="<%=request.getContextPath()%>/js/util.gpr.js" type="text/javascript"></script>
	
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/IngresoSolicitudAction.js'></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/ProductoAction.js'></script>
	
	
	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util/formatters.js" type="text/javascript"></script>
	

<script type="text/javascript">
$(function() {
    $( "#fechaIngreso" ).datepicker({dateFormat: 'dd/mm/yy'});
});

	ocultarElementByID('formSolicitudIngreso:txtMsjOk',4000);
	ocultarElementByID('formSolicitudIngreso:txtMsjError',10000);
	ocultarElementByID('formSolicitudIngreso:txtMsjAlerta',10000);

//frk: para el caso de IExplorer en el que no 
//funciona bien el "getElementsByName" 

if (jQuery.browser.msie){
	document.getElementsByName = function(name, tag){
	    return getElementsByName_iefix(name, tag);
	};
}

<%
//frk: Parametros con los cuales identificaremos que acción se esta realizando
String codigoSolicitud = request.getParameter("codigoSolicitud");
String asigPrioridadIndividual = request.getParameter("asigPrioridadIndividual");
String asigAnulacionIndividual = request.getParameter("asigAnulacionIndividual");
String indMensaje = (String) request.getAttribute("indMensaje");

%>
var valCampaignGeneric = '';
var valBancaGeneric = '';
var lastSelProducto = '';
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

$(document).keyup(function(e) {
	  //frk: se presiono el ESC
	  var tecla = (document.all) ? e.keyCode : e.which; 
	  if (tecla == 27) {
		  lastSelProducto = '';
		  deleteTblRowAdded('listProducts');
	  }
	});
var myColNames  = ['', '', '', '', 'Producto', 'Producto Base','Contrato Vinculado', 'Scoring',  'Cod. Pre Evaluador', 'Campaña', 'Tipo', 'Monto', 'Plazo (Meses)', 'Garantia'];
var myDataModel = [ {name : 'codProducto',			index : 'codProducto', 			width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true, required:true}, hidden:true},
                    {name : 'valBanca',				index : 'valBanca', 			width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                    {name : 'valMoneda',			index : 'valMoneda', 			width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                    {name : 'valMontoTotal',		index : 'valMontoTotal', 		width : VAL_WIDTH.SMALL, 	editable:true,	editrules: {edithidden:true}, hidden:true},
                    {name : 'desProducto',			index : 'desProducto', 			width : VAL_WIDTH.VMED, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: desProductoElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: desProductoFormat, unformat: genericUnFormat},                 
                    {name : 'codProdBase',			index : 'codProdBase', 			width : VAL_WIDTH.XLSMALL,	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 255}, editrules: {required: true}, align : 'center'},
                    {name : 'contratoVinculado',	index : 'contratoVinculado',	width : VAL_WIDTH.VMED, 	editable:true,	edittype:'custom',  editoptions: {custom_element: contratoVincElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: contratoVincFormat, unformat: genericUnFormat},
                    {name : 'scoring',				index : 'scoring', 				width : VAL_WIDTH.XLSMALL,  editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 255}, editrules: {required: true}, align : 'center'},
                    {name : 'codPreEvaluador',		index : 'codPreEvaluador', 		width : VAL_WIDTH.SMALL, 	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 255}, editrules: {required: true}, align : 'center'},
                    {name : 'campania',				index : 'campania', 			width : VAL_WIDTH.VMED, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: campaniaElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: campaniaFormat, unformat: genericUnFormat},
                    {name : 'tipo',					index : 'tipo', 				width : VAL_WIDTH.XLSMALL, 	editable:true,	edittype:'custom', 	editoptions: {custom_element: tipoElementCustom, custom_value: genericComboValueCustom}, editrules: {required: true}, align : 'center', formatter: tipoFormat, unformat: genericUnFormat},
                    {name : 'monto',				index : 'monto', 				width : VAL_WIDTH.XLSMALL, 	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 10, style: 'text-align: right'}, 	editrules: {required: true, number: true, minValue: 0}, align : 'center'},
                    {name : 'plazo',			    index : 'plazo', 				width : VAL_WIDTH.XLSMALL, 	editable:true,	edittype:'text', 	editoptions: {size:10, maxlength: 6,  style: 'text-align: right'}, editrules: {required: true, integer: true, minValue: 0, maxValue: 999999}, align : 'center'},
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
	
	  var codBanca = document.getElementById("codBanca");
	  ProductoAction.cargarProductosPorBanca(codBanca.value, function(data){
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
	  var codBanca = document.getElementById("codBanca");
	  if(codBanca.value == '1'){ //frk: en el caso de Banca Personas, sera un combobox
		  
		  ProductoAction.cargarContratosVincPorBanca(codBanca.value, function(data){
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

	var formulario = document.getElementById('formSolicitudIngreso');
	var codBanca = formulario.codBanca.value;

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
	
	var codBanca = document.getElementById("codBanca");
	var codMultMoneda = document.getElementById("codMultMoneda");
	var mtoTotal = document.getElementById("mtoTotal");
	
	if(codBanca.value == '-1'){
		alert("Debe seleccionar una banca para poder agregar un registro.");
	}else{
		var rowid = "-1";
		var mydataadd = 
			  [{
				codProducto			: rowid,
				valBanca			: codBanca.value,
                valMoneda			: codMultMoneda.value,
                valMontoTotal		: mtoTotal.value,
                desProducto 		: "",
                codProdBase			: "",
                contratoVinculado 	: "",
                scoring				: "",			
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
			valBancaGeneric = codBanca.value;
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
	loadInfoClient();
}

function loadInfoClient(){
	var formulario = document.getElementById('formSolicitudIngreso');
	formulario.action = rutaContexto+'/ingresoSolicitud.do?method=showClient';
	formulario.submit();
}

function guardarSolicitud(){
	var rows= jQuery("#listProducts").jqGrid('getRowData');
	  var paras=new Array();
	  for(var i=0;i<rows.length;i++){
	      var row=rows[i];
	      paras.push($.param(row));
	      
	  }
	
	  var formulario = document.getElementById('formSolicitudIngreso');
		formulario.action = rutaContexto+'/ingresoSolicitud.do?method=insertSolicitud';;
		formulario.submit();
	
}


function backAsignacionPrioridad(){
	window.open('asignacion_prioridad.jsp','_self');
}

</script>
	
</head>
<body onload="consultarProductos('','');">
<html:form method="post" action="ingresoSolicitud.do?method=init" styleId="formSolicitudIngreso">
<!-- <form name="formIngresoSolicitud" method="post"> -->

	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Módulo de Ingreso de la Solicitud</b></font>
	</div>

	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
	<br/>
	<input type="button" class="buttonGPR"  name="btnRegresar" id="btnRegresar" onclick="backAsignacionPrioridad();" value="Regresar">&nbsp;
	<input type="button" class="buttonGPR"  name="btnGuardar" id="btnGuardar"  class="buttonGPR"  value="Guardar">
	<br/>
	<%}%>

	<br/>
	<table width="740px" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>	
		<td>  <%if(indMensaje != null){ //error%>
				<%if(indMensaje.equals("1")){//error%>
				<html:text property="txtMsjOk"  styleClass="outputExito" style="width:100%;" value="${strMensaje}"></html:text>
				<%}else if(indMensaje.equals("-1")){%>
				<html:text property="txtMsjAlerta" styleClass="outputAlerta" style="width:100%;" value="${strMensaje}"></html:text>
				<%}else if(indMensaje.equals("0")){%>
				<html:text property="txtMsjError" styleClass="outputError" style="width:100%;" value="${strMensaje}"></html:text>				
				<% } }%>
			</td>
	</tr>
	<tr>
		<td>
			<font face="Arial Narrow" size="3" color="#000080"><b>Código Central</b></font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<html:text property="codCentral" styleClass="cajaTexto" styleId="codigoCentral" size="10" maxlength="8" readonly="readonly"></html:text><html:errors property="codCentral"/>
			<%}else{%>
			<html:text property="codCentral" styleClass="cajaTexto" styleId="codigoCentral" size="10" maxlength="8" value="42809680"></html:text><html:errors property="codCentral"/>
			<%}%>
			
			<%if(asigPrioridadIndividual == null && asigAnulacionIndividual == null){%>
			&nbsp;<a href="javascript:showBodyForm();"><img src="imagenes/lupa.gif" border="0" height="18"></a>
			<%}%>
		</td>
		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
		<td align="right">
			
			<%if(asigPrioridadIndividual != null){%>
				<font face="Arial Narrow" size="3" color="#000080"><b>Prioridad</b></font>&nbsp;
				<select id="slctPrioridad" name="slctPrioridad">
					<option value="1">Alta</option>			
					<option value="2" selected="selected">Normal</option> <!-- frk: valor seleccionado por defecto -->
					<option value="3">Baja</option>
				</select>
			<%}%>
			
			<%if(asigAnulacionIndividual != null){%>
				<font face="Arial Narrow" size="3" color="#000080"><b>Anular</b></font>&nbsp;
				<input type="checkbox" name="chkAnular" id="chkAnular">
			<%}%>
			
		</td>
		<%}%>
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
		<td colspan="2" valign="middle">
        	<font face="Arial Narrow" size="3" color="#000080"><b>Nro. Solicitud</b></font>&nbsp;
        	
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="nroSolicitud" class="cajaTexto" id="nroSolicitud" size="19" maxlength="19" readonly="readonly">&nbsp;
			<%}else{%>
			<input type="text" name="nroSolicitud" class="cajaTexto" id="nroSolicitud" size="19" maxlength="19" value='${solicitudForm.nroSolicitud}'>&nbsp;
			<%}%>
			
			<font face="Arial Narrow" size="3" color="#000080"><b>Tipo Persona *</b></font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
				<input type="text" name="desMultTipoPersona" class="cajaTexto" id="desMultTipoPersona" size="20" maxlength="8" readonly="readonly">&nbsp;
				<%}else{%>
				<input type="text" name="desMultTipoPersona" class="cajaTexto" id="desMultTipoPersona" size="20" maxlength="60" value='${solicitudForm.desMultTipoPersona}' >&nbsp;
			<%}%>			
			
			<font face="Arial Narrow" size="3" color="#000080"><b>Ruc / DNI *</b></font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>  
				<html:text property="numeroDocumento" styleClass="cajaTexto" styleId="numeroDocumento" size="27" maxlength="27" value='${solicitudForm.numeroDocumento}' readonly="readonly" />
				<%}else{%>
				<html:text property="numeroDocumento" styleClass="cajaTexto" styleId="numeroDocumento" size="27" maxlength="27" value='${solicitudForm.numeroDocumento}' />
			<%}%>
			
			
       </td>
    </tr>
    <tr>
       <td colspan="2" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Razon Social / Apellidos y Nombres *</b></font>&nbsp;
       		
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
       		<html:text property="desSolicitante" styleClass="cajaTexto" styleId="desSolicitante" size="76" maxlength="76" value='${solicitudForm.desSolicitante}' readonly="readonly" />
			<%}else{%>
			<html:text property="desSolicitante" styleClass="cajaTexto" styleId="desSolicitante" size="76" maxlength="76" value='${solicitudForm.desSolicitante}' />
			<%}%>
       </td>   
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Oficina Principal *</b></font>&nbsp;
       		
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
       		<html:text property="codOficina" styleClass="cajaTexto" styleId="codOficina" size="10" maxlength="10" value='${solicitudForm.codOficina}' readonly="readonly" />
       		<html:text property="desOficina" styleClass="cajaTexto" styleId="desOficina" size="40" maxlength="20" value='${solicitudForm.desOficina}' readonly="readonly" />&nbsp;&nbsp;
			<%}else{%>
			<html:text property="codOficina" styleClass="cajaTexto" styleId="codOficina" size="10" maxlength="10" value='${solicitudForm.codOficina}' />
       		<html:text property="desOficina" styleClass="cajaTexto" styleId="desOficina" size="40" maxlength="20" value='${solicitudForm.desOficina}' />&nbsp;&nbsp;
		
		    <%}%>
	   </td>
	   <td align="right" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Gestor *</b></font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<html:text property="gestorCod" styleClass="cajaTexto" styleId="gestorCod" size="10" maxlength="10" value='${solicitudForm.gestorCod}' readonly="readonly" />
       		<html:text property="gestorNom" styleClass="cajaTexto" styleId="gestorNom" size="40" maxlength="20" value='${solicitudForm.gestorNom}' readonly="readonly" />
			<%}else{%>
			<html:text property="gestorCod" styleClass="cajaTexto" styleId="gestorCod" size="10" maxlength="10" value='${solicitudForm.gestorCod}' />
       		<html:text property="gestorNom" styleClass="cajaTexto" styleId="gestorNom" size="40" maxlength="20" value='${solicitudForm.gestorNom}' />
			<%}%>
       </td>
	</tr>
	<tr>
	   <td align="left">&nbsp;</td>
       <td align="right" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Empleador *</b></font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<html:text property="empleadorCod" styleClass="cajaTexto" styleId="empleadorCod" size="14" maxlength="10" value='${solicitudForm.empleadorCod}' readonly="readonly" />
       		<html:text property="empleadorNom" styleClass="cajaTexto" styleId="empleadorNom" size="40" maxlength="20" value='${solicitudForm.empleadorNom}' readonly="readonly" />
			<%}else{%>
			<html:text property="empleadorCod" styleClass="cajaTexto" styleId="empleadorCod" size="14" maxlength="10" value='${solicitudForm.empleadorCod}' />
       		<html:text property="empleadorNom" styleClass="cajaTexto" styleId="empleadorNom" size="40" maxlength="20" value='${solicitudForm.empleadorNom}' />
			<%}%>
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
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="ejecutivoCtaCod" class="cajaTexto" id="ejecutivoCtaCod" size="10" maxlength="10" readonly="readonly">
			<input type="text" name="ejecutivoCtaNom" class="cajaTexto" id="ejecutivoCtaNom" size="40" maxlength="20" readonly="readonly">
			<%}else{%>
			<input type="text" name="ejecutivoCtaCod" class="cajaTexto" id="ejecutivoCtaCod" size="10" maxlength="10" value='${solicitudForm.ejecutivoCtaCod}' >
			<input type="text" name="ejecutivoCtaNom" class="cajaTexto" id="ejecutivoCtaNom" size="40" maxlength="20" value='${solicitudForm.ejecutivoCtaNom}' >
			<%}%>
			
	   </td>
	   <td align="right" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Fecha de Ingreso Oficina *</b></font>&nbsp;
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="strFechaIngreso" class="cajaTexto" id="strFechaIngreso" size="20" maxlength="20" readonly="readonly">
			<%}else{%>
			<input type="text" name="strFechaIngreso" class="cajaTexto" id="strFechaIngreso" size="30"  maxlength="30" value='${solicitudForm.strFechaIngreso}'>
			<%}%>
			
       </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Oficina de Alta *</b></font>
       </td>
       <td align="left" valign="middle">
       
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="oficinaAltaCod" class="cajaTexto" id="oficinaAltaCod" size="10" maxlength="10" readonly="readonly">
			<input type="text" name="oficinaAltaNom" class="cajaTexto" id="oficinaAltaNom" size="40" maxlength="20" readonly="readonly">&nbsp;&nbsp;
			<%}else{%>
			<input type="text" name="oficinaAltaCod" class="cajaTexto" id="oficinaAltaCod" size="10" maxlength="10" value='${solicitudForm.oficinaAltaCod}'>
			<input type="text" name="oficinaAltaNom" class="cajaTexto" id="oficinaAltaNom" size="40" maxlength="100" value='${solicitudForm.oficinaAltaNom}'  >&nbsp;&nbsp;
			<%}%>
			
	   </td>
	   <td align="right" valign="middle">&nbsp;</td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Gerencia Territorial *</b></font>
       </td>
       <td align="left" valign="middle">
       
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="gerenciaTerritorialCod" class="cajaTexto" id="gerenciaTerritorialCod" size="10" maxlength="10" readonly="readonly">
			<input type="text" name="gerenciaTerritorialNom" class="cajaTexto" id="gerenciaTerritorialNom" size="40" maxlength="20" readonly="readonly">&nbsp;&nbsp;
			<%}else{%>
			<input type="text" name="gerenciaTerritorialCod" class="cajaTexto" id=gerenciaTerritorialCod size="10" maxlength="10" value='${solicitudForm.gerenciaTerritorialCod}'>
			<input type="text" name="gerenciaTerritorialNom" class="cajaTexto" id="gerenciaTerritorialNom" size="40" maxlength="60" value='${solicitudForm.gerenciaTerritorialNom}' >&nbsp;&nbsp;
			<%}%>
			
	   </td>
	   <td align="right" valign="middle">&nbsp;</td>
	</tr>
	</table>
	</fieldset>
	
	<fieldset style="width: 1300px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	Datos del Producto
   	</font></legend>
	<table style="width: 1300px;">	
	<tr>
       <td align="left" valign="middle">
       <font face="Arial Narrow" size="3" color="#000080"><b>Banca</b></font>&nbsp;
       <html:select property="codBanca" styleId="codBanca" onchange="changeBankListProducts(this);">
				<html:option value="-1" >TODOS</html:option>
				<c:if test="${lstBancas != null}">
					<c:forEach var="banca" items="${lstBancas}">
						<html:option value="${banca.codBanca}">
							<c:out value="${banca.nombre}" />
						</html:option>
					</c:forEach>
				</c:if>
			</html:select>
	   </td>
	   <td align="center" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Moneda</b></font>&nbsp;       
	   		<html:select property="codMultMoneda" styleId="codMultMoneda">
				<html:option value="-1" >TODOS</html:option>
				<c:if test="${lstMonedas != null}">
					<c:forEach var="moneda" items="${lstMonedas}">
						<html:option value="${moneda.codElemento}">
							<c:out value="${moneda.strValor}" />
						</html:option>
					</c:forEach>
				</c:if>
			</html:select>
       </td>
       <td align="right" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Monto total</b></font>&nbsp;
	   		<input type="text" name="mtoTotal" class="cajaTexto" id="mtoTotal" size="20" maxlength="20" value='${solicitudForm.mtoTotal}'>
	   		
	   		&nbsp;<input type="button" class="buttonGPR"  name="btnBuscar" class="buttonGPR" id="btnBuscar" value="Buscar">
       </td>
	</tr>
	</table>
	
	<a href="javascript:deleteProducto();" class="buttonGPR">ELIMINAR</a>&nbsp;
	<a href="javascript:addProducto();" class="buttonGPR">AGREGAR</a>
	
	<br/><br/>
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
        	<font face="Arial Narrow" size="3" color="#000080"><b>Rating*</b></font>
        </td>
        <td valign="middle">
        	
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="rating" class="cajaTexto" id="rating" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="rating" class="cajaTexto" id="rating" size="19" maxlength="19" value='${solicitudForm.rating}'>
			<%}%>        
			
        </td>
    </tr>
    <tr>
		<td>
        	<font face="Arial Narrow" size="3" color="#000080"><b>Scorating*</b></font>
        </td>
        <td valign="middle">
        	
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="scorating" class="cajaTexto" id="scorating" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="scorating" class="cajaTexto" id="scorating" size="19" maxlength="19" readonly="readonly" value='${solicitudForm.scorating}'>
			<%}%>        
			
        </td>
    </tr>
    <tr>
       <td valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Clasificación del Cliente *</b></font>
       </td>
       <td valign="middle">
       		
       		<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="clasificacion" class="cajaTexto" id="clasificacion" size="19" maxlength="19" readonly="readonly" >
			<%}else{%>
			<input type="text" name="clasificacion" class="cajaTexto" id="clasificacion" size="19" maxlength="19" value='${solicitudForm.clasificacion}'>
			<%}%>
			
       </td>
	</tr>
	</table>
	</td>
	
	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font face="Arial Narrow" size="3" color="#000080"><b>Relevancia Pública *</b></font><br/>
        	
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="relevPublica1" class="cajaTexto" id="relevPublica1" size="19" maxlength="19" readonly="readonly"><br/>
			<input type="text" name="relevPublica2" class="cajaTexto" id="relevPublica2" size="19" maxlength="19" readonly="readonly"><br/>
			<input type="text" name="relevPublica3" class="cajaTexto" id="relevPublica3" size="19" maxlength="19" readonly="readonly"><br/>
			<input type="text" name="relevPublica4" class="cajaTexto" id="relevPublica4" size="19" maxlength="19" readonly="readonly"><br/>
			<input type="text" name="relevPublica5" class="cajaTexto" id="relevPublica5" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="relevPublica1" class="cajaTexto" id="relevPublica1" size="19" maxlength="19"  value='${solicitudForm.relevPublica1}'><br/>
			<input type="text" name="relevPublica2" class="cajaTexto" id="relevPublica2" size="19" maxlength="19"  value='${solicitudForm.relevPublica2}'><br/>
			<input type="text" name="relevPublica3" class="cajaTexto" id="relevPublica3" size="19" maxlength="19"  value='${solicitudForm.relevPublica3}'><br/>
			<input type="text" name="relevPublica4" class="cajaTexto" id="relevPublica4" size="19" maxlength="19"  value='${solicitudForm.relevPublica4}'><br/>
			<input type="text" name="relevPublica5" class="cajaTexto" id="relevPublica5" size="19" maxlength="19"  value='${solicitudForm.relevPublica5}'>
			<%}%>
			
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
        
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="deudaDirecta" class="cajaTexto" id="deudaDirecta" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="deudaDirecta" class="cajaTexto" id="deudaDirecta" size="19" maxlength="19" value='${solicitudForm.deudaDirecta}'>
			<%}%>
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Deuda Indirecta *</b></font>
		</td>
		<td valign="middle">
		
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="deudaIndirecta" class="cajaTexto" id="deudaIndirecta" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="deudaIndirecta" class="cajaTexto" id="deudaIndirecta" size="19" maxlength="19" value='${solicitudForm.deudaIndirecta}'>
			<%}%>
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Deuda Castigo *</b></font>
		</td>
		<td valign="middle">
		
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="castigo" class="cajaTexto" id="castigo" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="castigo" class="cajaTexto" id="castigo" size="19" maxlength="19" value='${solicitudForm.castigo}'>
			<%}%>
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Deuda en el Sistema Financiero *</b></font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="deudaSistemaFinanciero" class="cajaTexto" id="deudaSistemaFinanciero" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="deudaSistemaFinanciero" class="cajaTexto" id="deudaSistemaFinanciero" size="19" maxlength="19"  value='${solicitudForm.deudaSistemaFinanciero}'>
			<%}%>
			
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
        
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="otroRiesgo" class="cajaTexto" id="otroRiesgo" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="otroRiesgo" class="cajaTexto" id="otroRiesgo" size="19" maxlength="19" value='${solicitudForm.otroRiesgo}'>
			<%}%>        
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Riesgo Grupal</b></font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="riesgoGrupal" class="cajaTexto" id="riesgoGrupal" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="riesgoGrupal" class="cajaTexto" id="riesgoGrupal" size="19" maxlength="19" value='${solicitudForm.riesgoGrupal}'>
			<%}%>
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Riesgo Actual</b></font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="riesgoActual" class="cajaTexto" id="riesgoActual" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="riesgoActual" class="cajaTexto" id="riesgoActual" size="19" maxlength="19" value='${solicitudForm.riesgoActual}'>
			<%}%>
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Riesgo Total</b></font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="riesgoTotal" class="cajaTexto" id="riesgoTotal" size="19" maxlength="19" readonly="readonly">
			<%}else{%>
			<input type="text" name="riesgoTotal" class="cajaTexto" id="riesgoTotal" size="19" maxlength="19"  value='${solicitudForm.riesgoTotal}'>
			<%}%>
       </td>
    </tr>
	</table>
	</td>
	
	</tr>
	<tr>
		<td><input type="button" class="buttonGPR"  name="btnGuardar" id="btnGuardar" onclick="guardarSolicitud();" value="Guardar Solicitud">&nbsp;</td>
	</tr>
	</table>
	</fieldset>
</html:form>

</body>
</html>