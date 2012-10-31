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
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>	
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>

<script type="text/javascript">

var myColNamesSol  = ['','Fecha', 'Solicitud', 'Central', 'Cliente', 'Moneda', 'Monto Sol.', 'Riesgo Act.', 'Riesgo Tot.', 'Prioridad', 'Fuera de Rango', ''];
var myDataModelSol = [ {name : 'codigo',				width : VAL_WIDTH.SMALL, hidden : true	},
                    {name : 'fecha',				index : 'fecha', 				width : VAL_WIDTH.XLSMALL	},
                    {name : 'solicitud',			index : 'solicitud',			width : VAL_WIDTH.XLSMALL	},
                    {name : 'central',				index : 'central', 				width : VAL_WIDTH.XLSMALL	},
                    {name : 'cliente',				index : 'cliente', 				width : VAL_WIDTH.SMALL		},
                    {name : 'moneda',				index : 'moneda', 				width : VAL_WIDTH.XLSMALL	},
                    {name : 'montoSol',				index : 'montoSol', 			width : VAL_WIDTH.XLSMALL	},
                    {name : 'riesgoAct',			index : 'riesgoAct', 			width : VAL_WIDTH.XLSMALL	},
                    {name : 'riesgoTot',			index : 'riesgoTot', 			width : VAL_WIDTH.XLSMALL	},
                    {name : 'prioridad',			index : 'prioridad', 			width : VAL_WIDTH.XLSMALL,  formatter: prioridadFormat, unformat: prioridadUnFormat, sortable: false},
                    {name : 'fueraRango',			index : 'fueraRango', 			width : VAL_WIDTH.XLSMALL	},
                    {name : 'hdnCodigo',			index : 'hdnCodigo', 			width : VAL_WIDTH.VSMALL,  	formatter: btnOpcionFormat, sortable: false, align:'center'}
                   ];

function btnOpcionFormat(cellvalue, options, rowObject){
	return "<a title='Prioridad Individual' href='javascript:editPrioridad("+cellvalue+");'><img src='imagenes/detalle.gif' border='0' height='18'></a>&nbsp;"+
	"<a title='Anulación Individual' href='javascript:editAnular("+cellvalue+");'><img src='imagenes/detalle.gif' border='0' height='18'></a>";
}

function prioridadFormat(cellvalue, options, rowObject){
	
	var slctPrioridad = "<select name='arrayPrioridad'>";
	if(cellvalue == '1'){
		slctPrioridad += "<option selected='selected' value='1'>Alta</option>"+
		"<option value='2'>Normal</option>"+			
		"<option value='3'>Baja</option>";
	}else if(cellvalue == '2'){
		slctPrioridad += "<option value='1'>Alta</option>"+
		"<option selected='selected' value='2'>Normal</option>"+			
		"<option value='3'>Baja</option>";
	}else if(cellvalue == '3'){
		slctPrioridad += "<option value='1'>Alta</option>"+
		"<option value='2'>Normal</option>"+			
		"<option selected='selected' value='3'>Baja</option>";
	}
	
	slctPrioridad += "</select>";	
	return slctPrioridad;
}

function prioridadUnFormat(cellvalue, options, cell){
	return $('input', cell).attr('value');
}

function consultarSolicitud(){
	
	jQuery("#listSolicitud").GridUnload();
	/* ProductoAction.consultarAjax(function(data){
		mostrarTablaSolicitud(data);
	}); */
	
	var dataTable = [
	{"codigo":"1", "fecha":"01/06/2012", "solicitud":"000776", "central":"200923", "cliente":"Ferrita Minera", "moneda":"S/.", "montoSol":"30,000", "riesgoAct":"", "riesgoTot":"", "prioridad":"1", "fueraRango":"Si", "hdnCodigo":"1"},
	{"codigo":"2", "fecha":"05/06/2012", "solicitud":"000779", "central":"200980", "cliente":"Viajes y Turismo", "moneda":"S/.", "montoSol":"45,000", "riesgoAct":"1,000", "riesgoTot":"46,000", "prioridad":"2", "fueraRango":"", "hdnCodigo":"2"},
	{"codigo":"3", "fecha":"05/06/2012", "solicitud":"000785", "central":"200756", "cliente":"Asociación de Calzado", "moneda":"S/.", "montoSol":"47,000", "riesgoAct":"5,000", "riesgoTot":"52,000", "prioridad":"2", "fueraRango":"", "hdnCodigo":"3"},
	{"codigo":"4", "fecha":"10/06/2012", "solicitud":"000789", "central":"200587", "cliente":"Tornos el Hierro", "moneda":"S/.", "montoSol":"60,000", "riesgoAct":"", "riesgoTot":"", "prioridad":"2", "fueraRango":"", "hdnCodigo":"4"},
	{"codigo":"5", "fecha":"15/06/2012", "solicitud":"000790", "central":"200722", "cliente":"Pc Master", "moneda":"S/.", "montoSol":"80,000", "riesgoAct":"40,000", "riesgoTot":"48,000", "prioridad":"2", "fueraRango":"", "hdnCodigo":"5"},
	{"codigo":"6", "fecha":"20/06/2012", "solicitud":"000793", "central":"200400", "cliente":"Mecanica el Grande", "moneda":"S/.", "montoSol":"10,000", "riesgoAct":"", "riesgoTot":"", "prioridad":"2", "fueraRango":"", "hdnCodigo":"6"},
	{"codigo":"7", "fecha":"30/06/2012", "solicitud":"000799", "central":"200044", "cliente":"Estudio Fotografico Flash", "moneda":"S/.", "montoSol":"100,000", "riesgoAct":"", "riesgoTot":"", "prioridad":"2", "fueraRango":"", "hdnCodigo":"7"}
	];
	
	mostrarTablaSolicitud(dataTable);
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
		pager 		: paginador,
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
}

function editPrioridad(codigo){
	window.open('ingreso_solicitud.jsp?asigPrioridadIndividual=true&codigoSolicitud='+codigo,'_self');
}

function editAnular(codigo){
	window.open('ingreso_solicitud.jsp?asigAnulacionIndividual=true&codigoSolicitud='+codigo,'_self');
}

</script>
	
</head>
<body onload="consultarSolicitud();">

<form name="formAsigPrioridad" method="post">

	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Módulo de Asignación de Prioridad en las Solicitudes</b></font>
	</div>

	<br/>
	<table style="width: 600px" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Código Central</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="codigoCentral" class="cajaTexto" id="codigoCentral" size="10" maxlength="8">
		</td>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Número Solicitud</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="numSolicitud" class="cajaTexto" id="numSolicitud" size="10" maxlength="8">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Fecha Solicitud</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="inifechaSolicitud" class="cajaTexto" id="inifechaSolicitud" size="10" maxlength="8">&nbsp;al&nbsp;
			<input type="text" name="finfechaSolicitud" class="cajaTexto" id="finfechaSolicitud" size="10" maxlength="8">
		</td>
		<td colspan="2" align="left" valign="middle">
			<input type="button" class="buttonGPR"  name="btnConsultar" id="btnConsultar" value="Consultar">
		</td>	
	</tr>
	</table>
	
	<br/>
	<table id="listSolicitud" class="grid">
	</table>
	<br/>
	
	<input type="button" class="buttonGPR"  name="btnProcesarPrioridad" id="btnProcesarPrioridad" value="Prioridad Masiva">&nbsp;
	<input type="button" class="buttonGPR"  name="btnAnularSolicitud" id="btnAnularSolicitud" value="Anulación Masiva">
	
</form>

</body>
</html>