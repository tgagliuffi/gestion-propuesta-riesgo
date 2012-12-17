<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/css/gpr_style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/css/buttonGPR.css" />

<script src="<%=request.getContextPath()%>/js/util.gpr.js"
	type="text/javascript"></script>

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/JDate.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/dwr/interface/BusquedaSolicitudAction.js'></script>

<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"    type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js"	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/util/formatters.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/script.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/util.gpr.js" type="text/javascript"></script>

<script type="text/javascript">
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
$(function() {
    $( "#fechaIngresoIni").datepicker({dateFormat: 'dd/mm/yy'});
    $( "#fechaIngresoFin").datepicker({dateFormat: 'dd/mm/yy'});
});

	var rutaContexto = rutaContexto1.substr(0, rutaContexto1
			.indexOf(rutaContexto2))
			+ rutaContexto2;

	var myColNames = [ 'Nro Solicitud', 
	                   'Fecha Ingreso', 
	                   'Cod Central',
					   'Tipo Persona', 
					   'Cliente', 
					   'Moneda', 
					   'Mto Solicitud',
					   'Riesgo Actual', 
					   'Riesgo Total', 
					   //'Banca',  
					   //'Sub Banca', 
					   'Cod oficina',
					   'Nombre Oficina', 'Estado Solicitud', 'E. Actual', 'Detalle', 'Check SubGerente' ];

	var myDataModel = [ {name : 'nroSolicitud', 		index : 'nroSolicitud', 		width : 214, 	sortable : false}, 
	                    {name : 'strFechaIngreso',		index : 'strFechaIngreso',		width : 230,	sortable : false}, 
	                    {name : 'codCentral',			index : 'codCentral',			width : 200,	sortable : false}, 
	                    {name : 'desMultTipoPersona',	index : 'desMultTipoPersona',	width : 340,	sortable : false}, 
	                    {name : 'desSolicitante',		index : 'desSolicitante',		width : 400,	sortable : false}, 
	                    {name : 'desMultMoneda',		index : 'desMultMoneda',		width : 180,	sortable : false}, 
	                    {name : 'mtoSolicitud',			index : 'mtoSolicitud',			width : 320,	sortable : false,	align : 'right'}, 
	                    {name : 'riesgoActual',			index : 'riesgoActual',			width : 240,	sortable : false,	align : 'right'}, 
	                    {name : 'riesgoTotal',			index : 'riesgoTotal',			width : 240,	sortable : false,	align : 'right'}, 
	                    //{name : 'desBanca',				index : 'desBanca',				width : 320,	sortable : false}, 
	                    //{name : 'descripcionSubanca',	index :'descripcionSubanca',	width : 320,	sortable : false}, 
	                    {name : 'oficinaAltaCod',		index : 'oficinaAltaCod',		width : 200,	sortable : false}, 
	                    {name : 'oficinaAltaNom',		index : 'oficinaAltaNom',		width : 500,	sortable : false}, 
	                    {name : 'estadoSolicitud',		index : 'estadoSolicitud',		width : 260,	sortable : false,	align : 'center'}, 
	                    {name : 'estado',				index : 'estado',				width : 140,	formatter : estadoFormat,		sortable : false,align : 'center'}, 
	                    {name : 'nroSolicitud',			index : 'hdnCodigo',			width : 220,	formatter : btnOpcionFormat,	sortable : false,align : 'center'},
						{name : 'nroSolicitud',			index : 'hdnCodigo',			width : 220,	formatter : btnOpcionFormatchkSubGerente,	sortable : false,align : 'center'}];
	function estadoFormat(cellvalue, options, rowObject) {
		if (cellvalue == '1')
			return "<img src='imagenes/boton_verde.png' border='0' height='18'/><input type='hidden' id='hidEstado_" + rowObject['nroSolicitud'] + "' value='" + cellvalue + "'/>";
		else if (cellvalue == '0')
			return "<img src='imagenes/boton_rojo.png' border='0' height='18'/><input type='hidden' id='hidEstado_" + rowObject['nroSolicitud'] + "' value='" + cellvalue + "'/>";
		else
			return "";
	}

	function mostrarData(data) {
		$('body').append(
				'<div id="paginador_listSolicitudes" class="grid"></div>');
		var paginador = "paginador_listSolicitudes";
		jQuery("#listSolicitud").jqGrid({
			beforeSelectRow : function() {
			},
			caption : "Listado de Solicitudes",
			data : data,
			datatype : "local",
			height : "100%",
			weight : 1200,
			colNames : myColNames,
			colModel : myDataModel,
			width : 1300,
			rowList : [ 5, 10, 15, 20, 25 ],
			rowNum : 10,
			pager : paginador,
			viewrecords : true,
			multiselect : true,
			subGrid : false,
			jsonReader : {
				repeatitems : false
			},
			footerrow : false,
			loadComplete : function(data) {
			}
		});
	}

	function consultarSolicitud() {
		var formulario = document.getElementById('solicitudForm');
		var codBanca = formulario.codBanca.value;
		var codOficina = formulario.codOficina.value;
		var desOficina = formulario.desOficina.value;
		var codCentral = formulario.codCentral.value;
		var nroSolicitud = formulario.nroSolicitud.value;
		var fechaIngresoIni = formulario.fechaIngresoIni.value;
		var fechaIngresoFin = formulario.fechaIngresoFin.value;
		var codEstadoMult = formulario.codEstadoMult.value;
		var codRol = formulario.codRol.value;
		var regEvaluador = formulario.regEvaluador.value;
		var nomEvaluador = formulario.nomEvaluador.value;
		var subBanca = formulario.subBanca.value;

		jQuery("#listSolicitud").GridUnload();
		BusquedaSolicitudAction.consultarSolicitudAjax(codBanca, codOficina,
				desOficina, codCentral, nroSolicitud, fechaIngresoIni,
				fechaIngresoFin, codEstadoMult, codRol, regEvaluador,
				nomEvaluador, subBanca, function(data) {
					mostrarData(data);
				});
	}

	function btnOpcionFormat(cellvalue, options, rowObject) {
		return "<a title='Detalle Consulta' href='javascript:showDetalle("
				+ cellvalue
				+ ");'><img src='imagenes/OpmDetalle.png' border='0' height='18'></a>";
	}
	
	function btnOpcionFormatchkSubGerente(cellvalue, options, rowObject) {
		return "<a title='Detalle Consulta' href='javascript:chkSubGerente("
				+ cellvalue
				+ ");'><img src='imagenes/1.png' border='0' height='18'></a>";
	}

	function showDetalle(codigo) {
		window.open(
				'busquedaSolicitudAction.do?method=detalleSolicitud&flag=2&codigoSolicitud='
						+ codigo, '_self');
	}

	function chkSubGerente(codigo) {
		window.open(
				'busquedaSolicitudAction.do?method=detalleSolicitud&flag=1&codigoSolicitud='
						+ codigo, '_self');
	}
	
	function expotar() {
		var formulario = document.getElementById('solicitudForm');
		formulario.action = rutaContexto
				+ '//busquedaSolicitudAction.do?method=reporteExcelAjax';
		formulario.submit();
	}
	
	function changeBancSubBanca(codBanca, codSubBanca){
		BusquedaSolicitudAction.getLstSubBanca(codBanca, function(data) {
			 dwr.util.removeAllOptions("subBanca");
			 dwr.util.addOptions("subBanca", data,'codSubanca','descripcion');
			
		});
	}
</script>

</head>
<body onload="consultarSolicitud();">

	<html:form styleId="solicitudForm"
		action="busquedaSolicitudAction.do?method=listarSolicitud">
		<br />
		<div class="ui-widget ui-widget-content ui-corner-all"
			style="width: 920px; margin: 3px;">
			<div class="ui-widget ui-state-default ui-corner-top"
				style="height: 20px; line-height: 20px;">
				<label>Datos de la Solicitud</label>
			</div>
			<table style="width: 900px; padding: 5px;" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="left" valign="middle"><font class="fontText">Banca</font>
					</td>
					<td align="left" valign="middle"><html:select
							property="codBanca" styleClass="codBanca" onchange="changeBancSubBanca(this.value, null);">
							<html:option value="-1">SELECCIONE</html:option>
							<c:if test="${lstBancas != null}">
								<c:forEach var="banca" items="${lstBancas}">
									<html:option value="${banca.codBanca}">
										<c:out value="${banca.nombre}" />
									</html:option>
								</c:forEach>
							</c:if>
						</html:select></td>
						<td align="left" valign="middle"><font class="fontText">Sub Banca</font></td>
					    <td><html:select property="subBanca" styleId="subBanca">
							<html:option value="-1">[   SELECCIONE   ]</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="left" valign="middle"><font class="fontText">Código
							Central</font></td>
					<td align="left" valign="middle"><input type="text"
						name="codCentral" class="cajaTexto" id="codCentral" size="30"
						maxlength="30"></td>
					<td align="left" valign="middle"><font class="fontText">Número
							de Solicitud</font></td>
					<td align="left" valign="middle"><input type="text"
						name="nroSolicitud" class="cajaTexto" id="nroSolicitud" size="54"
						maxlength="54"></td>
				</tr>
				<tr>
					<td align="left" valign="middle"><font class="fontText">Fecha
							Solicitud Inicio</font></td>
					<td align="left" valign="middle"><input type="text"
						name="fechaIngresoIni" class="cajaTexto" id="fechaIngresoIni"
						size="14" maxlength="14"></td>
					<td align="left" valign="middle"><font class="fontText">Fecha
							Solicitud Fin</font></td>
					<td align="left" valign="middle"><input type="text"
						name="fechaIngresoFin" class="cajaTexto" id="fechaIngresoFin"
						size="14" maxlength="14"></td>
				</tr>
				<tr>
					<td align="left" valign="middle"><font class="fontText">Estado</font>
					</td>
					<td align="left" valign="middle"><html:select
							property="codEstadoMult" styleId="codEstadoMult">
							<html:option value="-1">SELECCIONE</html:option>
							<c:if test="${lstEstados != null}">
								<c:forEach var="estado" items="${lstEstados}">
									<html:option
										value="${estado.codMultitabla}${estado.codElemento}">
										<c:out value="${estado.strValor}" />
									</html:option>
								</c:forEach>
							</c:if>
						</html:select></td>
					<td align="left" valign="middle"><font class="fontText">Oficina
							de alta</font></td>
					<td align="left" valign="middle"><input type="text"
						name="codOficina" class="cajaTexto" id="codOficina" size="10"
						maxlength="10"> <input type="text" name="desOficina"
						class="cajaTexto" id="desOficina" size="40" maxlength="40">
					</td>
				</tr>
			</table>
		</div>
		<div class="ui-widget ui-widget-content ui-corner-all"
			style="width: 920px; margin: 3px; margin-top: 10px;">
			<div class="ui-widget ui-state-default ui-corner-top"
				style="height: 20px; line-height: 20px;">
				<label>Datos del Evaluador</label>
			</div>
			<table style="width: 900px; padding: 5px;" border="0" cellspacing="0"
				cellpadding="0">
				<tr>

					<td align="left" valign="middle"><font class="fontText">Rol</font>&nbsp;
						<html:select property="codRol" styleClass="codRol">
							<html:option value="-1">SELECCIONE</html:option>
							<c:if test="${lstRol != null}">
								<c:forEach var="rol" items="${lstRol}">
									<html:option value="${rol.codRol}">
										<c:out value="${rol.descripcion}" />
									</html:option>
								</c:forEach>
							</c:if>
						</html:select></td>

					<td align="right" valign="middle"><font class="fontText">Registro
							Evaluador</font>&nbsp;&nbsp;</td>
					<td align="left" valign="middle"><input type="text"
						name="regEvaluador" class="cajaTexto" id="regEvaluador" size="40"
						maxlength="40"></td>
				</tr>
				<tr>
					<td align="left" valign="middle"><font class="fontText">Apellidos
							Evaluador</font></td>
					<td align="left" valign="middle" colspan="3"><input
						type="text" name="nomEvaluador" class="cajaTexto"
						id="nomEvaluador" size="93" maxlength="93"></td>
				</tr>
			</table>
		</div>

		<br />
		<input type="button" class="buttonGPR" name="btnConsultar"
			id="btnConsultar" value="Consultar" onclick="consultarSolicitud();">
		<input type="button" class="buttonGPR" name="btnExpotar"
			id="btnExpotar" value="Expotar a Excel" onclick="expotar();">
		<br />
		<br />

		<table id="listSolicitud" class="grid" width="1300px;">
		</table>
	</html:form>
</body>
</html>