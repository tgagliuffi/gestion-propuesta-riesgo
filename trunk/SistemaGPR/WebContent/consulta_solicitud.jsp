<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
	
	
	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
	
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>	
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>

<script type="text/javascript">
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColNames  = ['','Fecha Ingreso Solicitud', 'Numero Solicitud', 'Codigo Central', 'Tipo Persona', 'Cliente', 'Moneda', 
                   'Monto Solicitud', 'Riesgo Actual', 'Riesgo Total', 'Banca', 'Código', 'Descripción', ''];
          
var myDataModel = [ {name : 'nroSolicitud',				width : VAL_WIDTH.SMALL, hidden : true	, sortable:false},
                    {name : 'fechaIngreso',				index : 'fechaIngreso', 		width : VAL_WIDTH.SMALL		, sortable:false},
                    {name : 'codCentral',				index : 'codCentral', 			width : VAL_WIDTH.SMALL		, sortable:false},
                    {name : 'desMultTipoPersona',		index : 'desMultTipoPersona', 	width : VAL_WIDTH.SMALL		, sortable:false},
                    {name : 'desSolicitante',			index : 'desSolicitante', 		width : VAL_WIDTH.SMALL		, sortable:false},
                    {name : 'desMultMoneda',			index : 'codMultMoneda', 		width : VAL_WIDTH.SMALL		, sortable:false},
                    {name : 'mtoSolicitud',				index : 'mtoSolicitud', 		width : VAL_WIDTH.SMALL		, sortable:false},
                    {name : 'riesgoActual',				index : 'riesgoActual', 		width : VAL_WIDTH.SMALL		, sortable:false},
                    {name : 'riesgoTotal',				index : 'riesgoTotal', 			width : VAL_WIDTH.SMALL		, sortable:false},
                    {name : 'desBanca',					index : 'desBanca', 			width : VAL_WIDTH.SMALL		, sortable:false},
                    {name : 'codOficina',				index : 'codOficina', 			width : VAL_WIDTH.SMALL		, sortable:false},
                    {name : 'desOficina',				index : 'desOficina', 			width : VAL_WIDTH.SMALL		, sortable:false},              
                    {name : 'hdnCodigo',				index : 'hdnCodigo', 			width : VAL_WIDTH.VSMALL,  	formatter: btnOpcionFormat, sortable: false, align:'center'}
                   ];
                   
function listarSolicitudes(){
	
	jQuery("#listSolicitud").jqGrid(
	{
		url: rutaContexto+'/busquedaSolicitudAction.do?method=buscarSolicitud',
		caption		: "Listado de Solicitudes",
		datatype 	: "local",
		datatype 	: "json",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNames,
		colModel 	: myDataModel,
        width : 775,
        rowList 	: [5,10,15,20,25],
		rowNum 		: 10, 
		pager 		: '#pager2',
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false
	});
	jQuery("#listSolicitud").jqGrid('navGrid','#pager2',{edit:true,add:false,del:false});
}

$(function() {
	$( "#fechaIngresoIni" ).datepicker({dateFormat: 'dd/mm/yy'});
	$( "#fechaIngresoFin" ).datepicker({dateFormat: 'dd/mm/yy'});
});

function btnOpcionFormat(cellvalue, options, rowObject){
	return "<a title='Detalle Consulta' href='javascript:showDetalle("+cellvalue+");'><img src='imagenes/detalle.gif' border='0' height='18'></a>";
}

function showDetalle(codigo){
	window.open('ingreso_solicitud.jsp?consultaSolicitud=true&codigoSolicitud='+codigo,'_self');
}

</script>
	
</head>
<body onload="listarSolicitudes();">

<html:form styleId="solicitudForm"  action="busquedaSolicitudAction.do?method=listarSolicitud">

	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Módulo de Consulta de Solicitudes</b></font>
	</div>

	<br/>
	<fieldset style="width: 830px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	Datos de la Solicitud
   	</font></legend>
	<table style="width: 800px" border="0" cellspacing="0" cellpadding="0">
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Banca</b></font>
       </td>
       <td align="left" valign="middle">
       	<html:select property="codBanca">
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
	   <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Oficina de alta</b></font>
       </td>
       <td align="left" valign="middle">       
	   		<input type="text" name="codOficina" class="cajaTexto" id="codOficina" size="10" maxlength="10">
			<input type="text" name="desOficina" class="cajaTexto" id="desOficina" size="40" maxlength="40">
	   </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Codigo Central</b></font>
       </td>
       <td align="left" valign="middle">
			<input type="text" name="codCentral" class="cajaTexto" id="codCentral" size="30" maxlength="30">
	   </td>
	   <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Numero de Solicitud</b></font>
       </td>
       <td align="left" valign="middle">       
			<input type="text" name="nroSolicitud" class="cajaTexto" id="nroSolicitud" size="54" maxlength="54">
	   </td>
	</tr>
	<tr>
		<td align="left" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Fecha Solicitud Inicio</b></font>
		</td>
		<td align="left" valign="middle">
			<input type="text" name="fechaIngresoIni" class="cajaTexto" id="fechaIngresoIni" size="14" maxlength="14">
		</td>
		<td align="left" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Fecha Solicitud Fin</b></font>
		</td>
		<td align="left" valign="middle">
			<input type="text" name="fechaIngresoFin" class="cajaTexto" id="fechaIngresoFin" size="14" maxlength="14">
		</td>
	</tr>
	<tr>
		<td align="left" valign="middle">
			<font face="Arial Narrow" size="3" color="#000080"><b>Estado</b></font>
		</td>
		<td align="left" valign="middle">
       	<html:select property="codEstadoMult">
				<html:option value="-1" >TODOS</html:option>
				<c:if test="${lstEstados != null}">
					<c:forEach var="estado" items="${lstEstados}">
						<html:option value="${estado.codElemento}">
							<c:out value="${estado.strValor}" />
						</html:option>
					</c:forEach>
				</c:if>
			</html:select>
	   </td>
	</tr>
	</table>
	</fieldset>
	
	<fieldset style="width: 830px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	Datos del Evaluador
   	</font></legend>
	<table style="width: 650px" border="0" cellspacing="0" cellpadding="0">
	<tr>
      
	 <td align="left" valign="middle">
       <font face="Arial Narrow" size="3" color="#000080"><b>Rol</b></font>&nbsp;
       <html:select property="codRol">
				<html:option value="-1" >TODOS</html:option>
				<c:if test="${lstRol != null}">
					<c:forEach var="rol" items="${lstRol}">
						<html:option value="${rol.codRol}">
							<c:out value="${rol.decripcion}" />
						</html:option>
					</c:forEach>
				</c:if>
			</html:select>
	   </td>
      
	   <td align="right" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Registro Evaluador</b></font>&nbsp;&nbsp;
       </td>
       <td align="left" valign="middle">       
			<input type="text" name="regEvaluador" class="cajaTexto" id="regEvaluador" size="40" maxlength="40">
	   </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Apellidos Evaluador</b></font>
       </td>
       <td align="left" valign="middle" colspan="3">
			<input type="text" name="apeEvaluador" class="cajaTexto" id="apeEvaluador" size="93" maxlength="93">
	   </td>
	</tr>
	</table>
	</fieldset>
	
	<br/>
	<input type="button" class="buttonGPR"  name="btnConsultar" id="btnConsultar" value="Consultar" onclick="listarSolicitudes();">
	<br/><br/>
	
	<table id="listSolicitud" class="grid">
	</table>
	
</html:form>

</body>
</html>