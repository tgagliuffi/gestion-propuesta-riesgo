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
$(function() {
    $( "#fechaIngresoIni").datepicker({dateFormat: 'dd/mm/yy'});
    $( "#fechaIngresoFin").datepicker({dateFormat: 'dd/mm/yy'});
    $( "#fechaVencimiento").datepicker({dateFormat: 'dd/mm/yy'});
});

var myColNamesEval  = ['','Registro', 'Nombres', 'Cargo', 'Cantidad', 'Monto delegado', 'Dependiente'];
var myDataModelEval = [ 
                  
					{name : 'codigoUsuario',		index : 'codigoUsuario',  			width : VAL_WIDTH.VSMALL,    formatter:formatoRadio ,align:'center'},
                    {name : 'codigoUsuario',		index : 'codigoUsuario', 			width : VAL_WIDTH.SMALL		},
                    {name : 'nombres',				index : 'nombres',					width : VAL_WIDTH.SMALL		},
                    {name : 'concatRoles',			index : 'concatRoles', 				width : VAL_WIDTH.SMALL		},
                    {name : 'cantidad',				index : 'cantidad', 				width : VAL_WIDTH.XLSMALL	},
                    {name : 'mtoMaxDelegacion',		index : 'mtoMaxDelegacion', 		width : VAL_WIDTH.XLSMALL	},
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
                    {name : 'mtoSolicitud',				index : 'mtoSolicitud', 		width : 340		, sortable:false},
                    {name : 'riesgoActual',				index : 'riesgoActual', 		width : 300		, sortable:false},
                    {name : 'riesgoTotal',				index : 'riesgoTotal', 			width : 300		, sortable:false},    
                    {name : 'estado',					index : 'estado', 				width : VAL_WIDTH.XLSMALL,  	formatter: estadoFormat, sortable: false, align:'center'}
               
 ];
	
function formatoRadio(cellvalue, options, rowObject){
    var radio ="<input type=\"radio\" onclick=\"mostrarMensaje('"+cellvalue+"');\" name=\"radioButton\" />";
	return	radio;
}

function mostrarMensaje(valor){	
	alert(valor);
}

function estadoFormat(cellvalue, options, rowObject)
{	
	if(cellvalue == '1')
		return "<img src='imagenes/verde.png' border='0' height='18'/><input type='hidden' id='hidEstado_" + rowObject['nroSolicitud'] + "' value='" + cellvalue + "'/>";
	else if(cellvalue == '0')
		return "<img src='imagenes/rojo.gif' border='0' height='18'/><input type='hidden' id='hidEstado_" + rowObject['nroSolicitud'] + "' value='" + cellvalue + "'/>";
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

function consultarSolicitud(){
	var formulario = document.getElementById('asigacionForm');
	
	var codCentral = formulario.codCentral.value;
	var nroSolicitud = formulario.nroSolicitud.value;
	var fechaIngresoIni = formulario.fechaIngresoIni.value;
	var fechaIngresoFin = formulario.fechaIngresoFin.value;
	var fechaVencimiento = formulario.fechaVencimiento.value;
	
	jQuery("#listSolicitud").GridUnload();
	AsignacionAction.consultarSolicitudAjax(codCentral,nroSolicitud,fechaIngresoIni,fechaIngresoFin, fechaVencimiento, function(dataTable){
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
	var selecciones = buscarSelecciones('listSolicitud');
	var ids = $("#listEvaluador").jqGrid('getCol', 'codigoUsuario', true);
   	for (var i = 0; i < ids.length; i++) {
         if (ids[i].id != '') {
            var idx = $("#listEvaluador").jqGrid('getCell', ids[i].id, 'codigoUsuario');alert(idx);
        }
    }
   	
	if (selecciones.length == 0){
		alert ("No ha seleccionado ningún registro para la actualización masiva de prioridades.");
		return false;
	}else{
		var arrayCod = selecciones.split('**');
	}
}

</script>
	
</head>
<body onload="consultarEvaluador();consultarSolicitud();">

<html:form styleId="asigacionForm" method="post" action="asignacionAction.do?method=init">
 
	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF">&nbsp;Módulo de Asignación Individual</font>
	</div>

	<br/>
	<div class="ui-widget ui-widget-content ui-corner-all" style="width: 1000px;margin: 3px; padding: 5px;">
	<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
	<label>Usuarios</label></div>
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top: 5px;">
	<tr>
		<td valign="middle">
			<font class="fontText">Cargo</font>&nbsp;
			 <html:select property="codRol" styleClass="codRol" onchange="consultarEvaluador();">
				<html:option value="-1" >TODOS</html:option>
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
			<input type="checkbox" name="stFueraRango" class="cajaTexto" id="stFueraRango">&nbsp;
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
	</div>
</html:form>

</body>
</html>