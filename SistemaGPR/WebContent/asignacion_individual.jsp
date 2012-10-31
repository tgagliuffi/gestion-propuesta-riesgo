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

var myColNamesEval  = ['','Registro', 'Nombres', 'Cargo', 'Cantidad', 'Monto delegado', 'Dependiente'];
var myDataModelEval = [ {name : 'codigo',				width : VAL_WIDTH.SMALL, hidden : true	},
                    {name : 'registro',				index : 'registro', 			width : VAL_WIDTH.SMALL		},
                    {name : 'nombres',				index : 'nombres',				width : VAL_WIDTH.SMALL		},
                    {name : 'cargo',				index : 'cargo', 				width : VAL_WIDTH.SMALL		},
                    {name : 'cantSolEval',			index : 'cantSolEval', 			width : VAL_WIDTH.XLSMALL	},
                    {name : 'montoDelegado',		index : 'montoDelegado', 		width : VAL_WIDTH.XLSMALL	},
                    {name : 'dependiente',			index : 'dependiente', 			width : VAL_WIDTH.XLSMALL	}
                   ];
                   
var myColNamesSol  = ['','Fecha', 'Solicitud', 'Central', 'Cliente', 'Moneda', 'Monto Sol.', 'Riesgo Act.', 'Riesgo Tot.', 'Prioridad', 'Fuera de Rango'];
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
                    {name : 'fueraRango',			index : 'fueraRango', 			width : VAL_WIDTH.XLSMALL	}
                   ];

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
	{"codigo":"1", "fecha":"01/06/2012", "solicitud":"000776", "central":"200923", "cliente":"Ferrita Minera", "moneda":"S/.", "montoSol":"30,000", "riesgoAct":"", "riesgoTot":"", "prioridad":"1", "fueraRango":"Si"},
	{"codigo":"2", "fecha":"05/06/2012", "solicitud":"000779", "central":"200980", "cliente":"Viajes y Turismo", "moneda":"S/.", "montoSol":"45,000", "riesgoAct":"1,000", "riesgoTot":"46,000", "prioridad":"2", "fueraRango":""},
	{"codigo":"3", "fecha":"05/06/2012", "solicitud":"000785", "central":"200756", "cliente":"Asociación de Calzado", "moneda":"S/.", "montoSol":"47,000", "riesgoAct":"5,000", "riesgoTot":"52,000", "prioridad":"2", "fueraRango":""},
	{"codigo":"3", "fecha":"10/06/2012", "solicitud":"000789", "central":"200587", "cliente":"Tornos el Hierro", "moneda":"S/.", "montoSol":"60,000", "riesgoAct":"", "riesgoTot":"", "prioridad":"2", "fueraRango":""},
	{"codigo":"3", "fecha":"15/06/2012", "solicitud":"000790", "central":"200722", "cliente":"Pc Master", "moneda":"S/.", "montoSol":"80,000", "riesgoAct":"40,000", "riesgoTot":"48,000", "prioridad":"2", "fueraRango":""},
	{"codigo":"3", "fecha":"20/06/2012", "solicitud":"000793", "central":"200400", "cliente":"Mecanica el Grande", "moneda":"S/.", "montoSol":"10,000", "riesgoAct":"", "riesgoTot":"", "prioridad":"2", "fueraRango":""},
	{"codigo":"3", "fecha":"30/06/2012", "solicitud":"000799", "central":"200044", "cliente":"Estudio Fotografico Flash", "moneda":"S/.", "montoSol":"100,000", "riesgoAct":"", "riesgoTot":"", "prioridad":"2", "fueraRango":""},
	];
	
	mostrarTablaSolicitud(dataTable);
}

function consultarEvaluador(){

	jQuery("#listEvaluador").GridUnload();
	/* ProductoAction.consultarAjax(function(data){
		mostrarTablaEvaluador(data);
	}); */
	
	var dataTable = [
	{"codigo":"1", "registro":"P016047", "nombres":"Lazo Peres Irma", "cargo":"Analista", "cantSolEval":"50", "montoDelegado":"100,000", "dependiente":"Si"},
	{"codigo":"2", "registro":"P007395", "nombres":"Ronaldo Christian", "cargo":"Analista", "cantSolEval":"12", "montoDelegado":"50,000", "dependiente":"Si"},
	{"codigo":"3", "registro":"P006528", "nombres":"Ramos Sergio", "cargo":"Analista", "cantSolEval":"35", "montoDelegado":"45,000", "dependiente":"No"}
	];
	
	mostrarTablaEvaluador(dataTable);
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

</script>
	
</head>
<body onload="consultarEvaluador();consultarSolicitud();">

<form name="formAsigIndividual" method="post">

	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Módulo de Asignación Individual</b></font>
	</div>

	<br/>
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Cargo</b></font>&nbsp;
			<select id="cargoEvaluador" name="cargoEvaluador">
				<option value="GT">Gerente</option>
				<option value="RS">Responsable</option>			
				<option value="JF">Jefe</option>
				<option value="AN">Analista</option>
			</select>
		</td>
	</tr>
	</table>
	
	<br/>
	<table id="listEvaluador" class="grid">
	</table>
	<br/>
	
	<table style="width: 800px" border="0" cellspacing="0" cellpadding="0">
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
		<td align="right" valign="middle">
			<input type="checkbox" name="stFueraRango" class="cajaTexto" id="stFueraRango">&nbsp;
			<font face="Arial Narrow" size="3" color="#000080"><b>Solicitudes Fuera de Rango</b></font>			
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
		<td valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Fecha de vencimiento de evaluación</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="fechaVencimiento" class="cajaTexto" id="fechaVencimiento" size="10" maxlength="8">
		</td>
		<td align="right" valign="middle">
			<input type="button" class="buttonGPR"  name="btnConsultar" id="btnConsultar" value="Consultar">
		</td>	
	</tr>
	</table>
	
	<br/>
	<table id="listSolicitud" class="grid">
	</table>
	<br/>
	
	<input type="button" class="buttonGPR"  name="btnEnviar" id="btnEnviar" value="Enviar">
	
</form>

</body>
</html>