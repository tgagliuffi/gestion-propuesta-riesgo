<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
	
	<script src="<%=request.getContextPath()%>/js/util.gpr.js" type="text/javascript"></script>
	
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/AsignacionAction.js'></script>
	
	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util/formatters.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/script.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util.gpr.js" type="text/javascript"></script>
	
<script type="text/javascript">
function ocultarElementByID(id,tiempo){
	setTimeout("document.getElementById('"+id+"')!=null?document.getElementById('"+id+"').style.display='none':document.getElementById('"+id+"');", tiempo);
}
	ocultarElementByID('divError',4000);
	ocultarElementByID('divAlerta',10000);
	ocultarElementByID('divExito',10000);
	
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;
$(function() {
    $( "#fechaIngresoIni").datepicker({dateFormat: 'dd/mm/yy'});
    $( "#fechaIngresoFin").datepicker({dateFormat: 'dd/mm/yy'});
    $( "#fechaVencimiento").datepicker({dateFormat: 'dd/mm/yy'});
    $("#dialog-form").dialog(optionDialog);
});

var myColNamesEval  = ['','Registro', 'Nombres', 'Cargo', 'Cantidad', 'Mto PerNatural','Mto Rating','Mto Sin Rating', 'Dependiente'];
var myDataModelEval = [ 
                  
					{name : 'codigoUsuario',		index : 'codigoUsuario',  			width : VAL_WIDTH.VSMALL,    formatter:formatoRadio ,align:'center'},
                    {name : 'codigoUsuario',		index : 'codigoUsuario', 			width : VAL_WIDTH.SMALL		},
                    {name : 'nombres',				index : 'nombres',					width : VAL_WIDTH.SMALL		},
                    {name : 'concatRoles',			index : 'concatRoles', 				width : VAL_WIDTH.SMALL		},
                    {name : 'cantidad',				index : 'cantidad', 				width : VAL_WIDTH.XLSMALL	},
                    {name : 'mtoMaxPerNatual',		index : 'mtoMaxPerNatual', 			width : VAL_WIDTH.SMALL,	formatter:formartMonto, align:'right'},
                    {name : 'mtoMaxRating',			index : 'mtoMaxRating', 			width : VAL_WIDTH.SMALL, 	formatter:formartMonto, align:'right'},
                    {name : 'mtoSinRating',			index : 'mtoSinRating', 			width : VAL_WIDTH.SMALL,	formatter:formartMonto, align:'right'},
                    {name : 'dependiente',			index : 'dependiente', 				width : VAL_WIDTH.XLSMALL	}
                   ];
                   
var myColNamesSol  = ['','Nro Solicitud', 'Fecha Ingreso', 'Codigo Central', 'Tipo Persona', 'Cliente', 'Moneda', 
                                        'Monto Solicitud', 'Riesgo Actual', 'Riesgo Total', 'Estado'];

var myDataModelSol = 
					[
					{name : 'nroSolicitud',				width : VAL_WIDTH.SMALL, hidden : true	},
					{name : 'nroSolicitud',				index : 'nroSolicitud', 	width : 200      ,sortable:false},
					{name : 'strFechaIngreso',			index : 'strFechaIngreso', 		width : 200		, sortable:false},
                    {name : 'codCentral',				index : 'codCentral', 			width : 200		, sortable:false},
                    {name : 'desMultTipoPersona',		index : 'desMultTipoPersona', 	width : 300		, sortable:false},
                    {name : 'desSolicitante',			index : 'desSolicitante', 		width : 320		, sortable:false},
                    {name : 'desMultMoneda',			index : 'desMultMoneda', 		width : 220		, sortable:false},
                    {name : 'mtoSolicitud',				index : 'mtoSolicitud', 		width : 340		, sortable:false, align:'right'},
                    {name : 'riesgoActual',				index : 'riesgoActual', 		width : 300		, sortable:false, align:'right'},
                    {name : 'riesgoTotal',				index : 'riesgoTotal', 			width : 300		, sortable:false,	align:'right'},    
                    {name : 'estado',					index : 'estado', 				width : VAL_WIDTH.XLSMALL,  	formatter: estadoFormat, sortable: false, align:'center'}
               
 ];

function formartMonto(cellvalue, options, rowObject){
	if(cellvalue=='-1'){
		return  'NO REGISTRADO';
	}else{
		return cellvalue;
	}
}

function formatoRadio(cellvalue, options, rowObject){
    var radio ="<input type=\"radio\" onclick=\"mostrarMensaje('"+cellvalue+"');\" name=\"radioButton\" />";
	return	radio;
}

function mostrarMensaje(valor){	
	var formulario = document.getElementById('asigacionForm');
	formulario.hdnRegistro.value= valor;
}

function estadoFormat(cellvalue, options, rowObject)
{	
	if(cellvalue == '1')
		return "<img src='imagenes/boton_verde.png' border='0' height='18'/><input type='hidden' id='hidEstado_" + rowObject['nroSolicitud'] + "' value='" + cellvalue + "'/>";
	else if(cellvalue == '0')
		return "<img src='imagenes/boton_rojo.png' border='0' height='18'/><input type='hidden' id='hidEstado_" + rowObject['nroSolicitud'] + "' value='" + cellvalue + "'/>";
	else 
		return "";			
} 

function prioridadFormat(cellvalue, options, rowObject){
	
	var slctPrioridad = "<select name='arrayPrioridad'>";
	if(cellvalue == '1'){
		slctPrioridad += "<option selected='selected' value='1'>ALTA</option>"+
		"<option value='2'>NORMAL</option>"+			
		"<option value='3'>BAJA</option>";
	}else if(cellvalue == '2'){
		slctPrioridad += "<option value='1'>ALTA</option>"+
		"<option selected='selected' value='2'>NORMAL</option>"+			
		"<option value='3'>BAJA</option>";
	}else if(cellvalue == '3'){
		slctPrioridad += "<option value='1'>ALTA</option>"+
		"<option value='2'>NORMAL</option>"+			
		"<option selected='selected' value='3'>BAJA</option>";
	}
	
	slctPrioridad += "</select>";	
	return slctPrioridad;
}

function prioridadUnFormat(cellvalue, options, cell){
	return $('input', cell).attr('value');
}

function consultarSolicitud(objeto){
	var formulario = document.getElementById('asigacionForm');
	
	var codCentral = formulario.codCentral.value;
	var nroSolicitud = formulario.nroSolicitud.value;
	var fechaIngresoIni = formulario.fechaIngresoIni.value;
	var fechaIngresoFin = formulario.fechaIngresoFin.value;
	var fechaVencimiento = formulario.fechaVencimiento.value;

	jQuery("#listSolicitud").GridUnload();
	AsignacionAction.consultarSolicitudAjax(codCentral,nroSolicitud,fechaIngresoIni,fechaIngresoFin, fechaVencimiento, objeto, function(dataTable){
		mostrarTablaSolicitud(dataTable);
	});
			
}

function consultarEvaluador(){	
	var formulario = document.getElementById('asigacionForm');
	var codRol = formulario.codRol.value;
	jQuery("#listEvaluador").GridUnload();
	AsignacionAction.consultarUsuarioAjax(codRol, function(data){
		mostrarTablaEvaluador(data);		
	});
}

function mostrarTablaEvaluador(data){
	
	$('body').append('<div id="paginador_listEvaluador" class="grid"></div>'); 
	var paginador = "paginador_listEvaluador";
	
	jQuery("#listEvaluador").jqGrid(
	{
		beforeSelectRow: function(){},
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesEval,
		colModel 	: myDataModelEval,
		rowList 	: [5,10,15,20,25],
		rowNum 		: 10, 
		pager 		: paginador,
		viewrecords : true,
		multiselect : false,			
		subGrid    	: false,
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});

}

function mostrarTablaSolicitud(data){
	
	$('body').append('<div id="paginador_listSolicitud" class="grid"></div>'); 
	var paginador = "paginador_listSolicitud";
	
	jQuery("#listSolicitud").jqGrid(
	{
		beforeSelectRow: function(){},
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesSol,
		colModel 	: myDataModelSol,
		rowList 	: [5,10,15,20,25],
		rowNum 		: 15, 
		 width 		: 1200,
		pager 		: paginador,
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
	$("#listSolicitud").closest(".ui-jqgrid-bdiv").attr("style",
			$("#listProductsDetalle").closest(".ui-jqgrid-bdiv").attr("style") + " overflow-y: scroll; ");
}

function grabarAsiganciones(){
	var formulario = document.getElementById('asigacionForm');
	var selecciones = buscarSelecciones('listSolicitud');
	
	if (selecciones.length == 0){
		alert ("No ha seleccionado ningún registro para la actualización masiva de prioridades.");
		return false;
	}else{
		var arrayCod = selecciones.split('**1-');
		formulario.hdnArreglo.value= arrayCod;
		formulario.action = rutaContexto+'/asignacionAction.do?method=grabarAsignaciones';
		formulario.submit();
		
	}
	consultarEvaluador();
	consultarSolicitud('');
}

function consultarFuertaRango(objeto){
	
	var formulario = document.getElementById('asigacionForm');
	vfueraRango = formulario.stFueraRango.checked;
	vUsuario = formulario.hdnRegistro.value;
	if(vfueraRango==true){
		if(vUsuario!=''){
			consultarEvaluador();
			consultarSolicitud(vUsuario);
		}else{
			alert('Debe seleccionar un usuario.');
			 formulario.stFueraRango.checked = false;
		}
	}
}

optionDialog = {
		width: 420,
		autoOpen: false,
	    modal: true,
	    buttons: {
	        "Aceptar": function() {
	        	var formulario = document.getElementById('asigacionForm');
	        	var text = $('#textGarantia').val();
	        	formulario.strMensajePopUP.value=text;
	        	console.log($(this).attr("id"));
	        	$(this).dialog("close");
	        },
	        "Cancelar": function() {
	        	console.log($(this).attr("id"));
	        	$(this).dialog("close");
	        }
	    },
	    close: function() {
	    	console.log($(this).attr("id"));
	    }
	}; 
		

</script>
	
</head>
<body onload="consultarEvaluador();consultarSolicitud('');">

<html:form styleId="asigacionForm" method="post" action="asignacionAction.do?method=init">
 	<input type="hidden" id="hdnArreglo" name="hdnArreglo" value=''></input>
 	 	<input type="hidden" id="hdnRegistro" name="hdnRegistro" value=''></input>
 	 	<input type="hidden" id="strMensajePopUP" name="strMensajePopUP" value=''></input>
 	 	
	
	<br/>
		<table width="1200px" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>	
			<td>  
			<logic:present name="indMensaje">
				<c:if test="${indMensaje=='1'}"> 
				<div id="divExito" style="text-align: center;">
				<html:text property="outputExito"  styleId="outputExito" styleClass="outputExito" style="width:100%;" value="${strMensaje}"></html:text>
				</div>
				</c:if>
				<c:if test="${indMensaje=='-1'}"> 
				<div id="divAlerta" style="text-align: center;">
				<html:text property="outputAlerta" styleClass="outputAlerta" styleId="outputAlerta" style="width:100%;" value="${strMensaje}"></html:text>
				</div>
				</c:if>
				<c:if test="${indMensaje=='0'}"> 
				<div id="divError" style="text-align: center;">
				<html:text property="outputError" styleClass="outputError" styleId="outputError" style="width:100%;" value="${strMensaje}"></html:text>				
				</div>
				</c:if>
				</logic:present>
			</td>
	</tr>
	</table>
	<div class="ui-widget ui-widget-content ui-corner-all" style="width: 1200px;margin: 3px; padding: 5px;">
	<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
	<label>Usuarios</label></div>
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top: 5px;">
	<tr>
		<td valign="middle">
			<font class="fontText">Cargo</font>&nbsp;
			 <html:select property="codRol" styleClass="codRol" onchange="consultarEvaluador();">
				<html:option value="-1" >SELECCIONE</html:option>
				<c:if test="${lstRol != null}">
					<c:forEach var="rol" items="${lstRol}">
						<html:option value="${rol.codRol}">
							<c:out value="${rol.descripcion}" />
						</html:option>
					</c:forEach>
				</c:if>
			</html:select>
		</td>
	</tr>
	</table>
	
	<br/>
	<table id="listEvaluador" class="grid">
	</table></div>
	<br/>
	<div class="ui-widget ui-widget-content ui-corner-all" style="width: 1200px;margin: 3px; padding: 5px;">
	<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
	<label>Solicitudes</label></div>
	<table style="width: 1000px;padding-top :5px;" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="middle">
			<font class="fontText">Código Central</font>
		</td>
		<td valign="middle">
			<input type="text" name="codCentral" class="cajaTexto" id="codCentral" size="10" maxlength="8" onkeypress="ingresoNumeros(event);" >
		</td>
		<td valign="middle">
			<font class="fontText">Número Solicitud</font>
		</td>
		<td valign="middle">
			<input type="text" name="nroSolicitud" class="cajaTexto" id="nroSolicitud" size="10" maxlength="8" onkeypress="ingresoNumeros(event);" >
		</td>
		<td align="right" valign="middle">
			<input type="checkbox" name="stFueraRango" class="cajaTexto" id="stFueraRango" onclick="consultarFuertaRango(this);">&nbsp;
			<font class="fontText">Solicitudes Fuera de Rango</font>			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Fecha Solicitud</font>
		</td>
		<td valign="middle">
			<input type="text" name="fechaIngresoIni" class="cajaTexto" id="fechaIngresoIni" size="10" maxlength="8">&nbsp;al&nbsp;
			<input type="text" name="fechaIngresoFin" class="cajaTexto" id="fechaIngresoFin" size="10" maxlength="8">
		</td>
		<td valign="middle">
			<font class="fontText">Fecha de vencimiento de evaluación</font>
		</td>
		<td valign="middle">
			<input type="text" name="fechaVencimiento" class="cajaTexto" id="fechaVencimiento" size="10" maxlength="8">
		</td>
		<td align="right" valign="middle">
			<input type="button" class="buttonGPR"  name="btnConsultar" id="btnConsultar" value="Consultar" onclick="consultarSolicitud();">
		</td>	
	</tr>
	</table>
	
	<br/>
	<table id="listSolicitud" class="grid">
	</table>
	<br/>
	<input type="button" class="buttonGPR"  name="btnEnviar" id="btnEnviar" value="Enviar" onclick="grabarAsiganciones();">
    <input type="button"   id="btnCondiciones"  class="buttonGPR" onclick="llamarPopup();" value="OBSERVACION">&nbsp;
		<div id="dialog-form" title="Añadir Observacion" style="width: 400px">
		<form>
	        <center>
	        	<textarea id="textGarantia" name="textGarantia" rows="10" cols="40" style="width: 300px; height: 140px;" onkeypress="return limita(this, event,100)" onkeyup="cuenta(this, event,100,'contador')">
	        	</textarea>
	        	<span id="contador"></span>    	
	        </center>
		</form>
	</div>
	</div>
</html:form>
<script type="text/javascript">

function limita(obj,elEvento, maxi) { 
	var elem = obj; var evento = elEvento || window.event; var cod = evento.charCode || evento.keyCode; 
	if(cod == 37 || cod == 38 || cod == 39 || cod == 40 || cod == 8 || cod == 46) {
		return true; } 
	    if(elem.value.length < maxi ) 
	    { return true; } 
	    return false; 
	}

function cuenta(obj,evento,maxi,div) { 
	var elem = obj.value; 
	var info = document.getElementById(div); info.innerHTML = maxi-elem.length; 
}

function llamarPopup(){
	$( "#dialog-form" ).dialog( "open" );
}
</script>
</body>
</html>