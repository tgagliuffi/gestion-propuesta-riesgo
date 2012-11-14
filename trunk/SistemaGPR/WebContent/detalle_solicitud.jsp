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
	
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/BusquedaSolicitudAction.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
		
	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util/formatters.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/script.js" type="text/javascript"></script>
	
<script type="text/javascript">
$(function() {
    $( "#fechaIngreso" ).datepicker({dateFormat: 'dd/mm/yy'});
});
<%
//frk: Parametros con los cuales identificaremos que acción se esta realizando
String codigoSolicitud = request.getParameter("codigoSolicitud");
String asigPrioridadIndividual = request.getParameter("asigPrioridadIndividual");
String asigAnulacionIndividual = request.getParameter("asigAnulacionIndividual");
String indMensaje = (String) request.getAttribute("indMensaje");

%>
var valCampaignGeneric = '';
var valBancaGeneric = '';
var valContratoGeneric = '';
var lastSelProducto = '';
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColSolicituDetalle  = [ 'Descripción Producto', 'Producto Base','Contrato Vinculado', 'Scoring',  'Cod. Pre Evaluador', 'Campaña', 'Tipo', 'Monto Solicitado', 'Plazo (Meses)', 'Monto Garantizado', 'Total'];

var myDataModelSolicitudDetalle = [ {name : 'desProducto',      	index : 'desProducto',       	width : 200      ,sortable:false},
                                    {name : 'desProdBase',      	index : 'desProdBase',       	width : 120      ,sortable:false},
                                    {name : 'contratoVinculado',  	index : 'contratoVinculado',  	width : 150      ,sortable:false},
                                    {name : 'scoring',        		index : 'scoring',         		width : 70      ,sortable:false},
                                    {name : 'codPreEvaluador',    	index : 'codPreEvaluador',      width : 100      ,sortable:false},
                                    {name : 'desCampania',      	index : 'desCampania',       	width : 120      ,sortable:false},
                                    {name : 'desTipo',        		index : 'desTipo',         		width : 80      ,sortable:false},
                                    {name : 'mtoProducto',      	index : 'mtoProducto',       	width : 100      ,sortable:false, editoptions: {style: 'text-align: right'}},
                                    {name : 'plazo',          		index : 'plazo',         		width : 80      ,sortable:false},
                                    {name : 'mtoGarantia',      	index : 'mtoGarantia',       	width : 100      ,sortable:false, editoptions: {style: 'text-align: right'}},
                                    {name : 'mtoTotalRow',      	index : 'mtoTotalRow',       	width : 60      ,sortable:false, editoptions: {style: 'text-align: right'}}
                        ];
                    			
function consultarUsuario(){
	var formulario = document.getElementById('formSolicitudIngreso');
	var nroSolicitud = formulario.nroSolicitud.value;
	jQuery("#listProducts").GridUnload();
	BusquedaSolicitudAction.consultarSolicitudDetalleAjax(nroSolicitud, function(data){
		mostrarTabla(data);
	});
}

function mostrarTabla(data){
	
	$('body').append('<div id="paginador_listPlazos" class="grid"></div>'); 
	
	jQuery("#listProducts").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Usuarios",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColSolicituDetalle,
		colModel 	: myDataModelSolicitudDetalle,
		rowList 	: [5,10,15,20],
		rowNum 		: 10, 
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		jsonReader : { repeatitems: false },
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
	$("#listProducts").closest(".ui-jqgrid-bdiv").attr("style",
			$("#listProducts").closest(".ui-jqgrid-bdiv").attr("style") + " overflow-y: scroll; ");
}

function regresarBusquedaSolicitud(){
	var formulario = document.getElementById('formSolicitudIngreso');
	formulario.action =rutaContexto+'/busquedaSolicitudAction.do?method=listarSolicitud';
	formulario.submit();
}
</script>
	
</head>
<body onload="consultarUsuario();">
<html:form method="post" action="ingresoSolicitud.do?method=init" styleId="formSolicitudIngreso">
	<br/>
	<a href="javascript:regresarBusquedaSolicitud();" class="buttonGPR">REGRESAR</a>
	<br/>
	<br/>
	<table width="740px" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<font class="fontText">Código Central</font>&nbsp;
			<input value="${Solicitud.codCentral}" class="cajaTexto" size="10" readonly="readonly"/>			
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
        	<font class="fontText">Nro. Solicitud</font>&nbsp;
			<input value="${Solicitud.nroSolicitud}" id="nroSolicitud" class="cajaTexto" size="22" readonly="readonly"/>
			
			<font class="fontText">Tipo Persona *</font>&nbsp;
			<input value="${Solicitud.desMultTipoPersona}" class="cajaTexto" size="22" readonly="readonly"/>
											
			<font class="fontText">Ruc / DNI *</font>&nbsp;
			<input value="${Solicitud.numeroDocumento}" class="cajaTexto" size="22" readonly="readonly"/>			
       </td>
    </tr>
    <tr>
       <td colspan="2" valign="middle">
       		<font class="fontText">Razon Social / Apellidos y Nombres *</font>&nbsp;
       		<input value="${Solicitud.desSolicitante}" class="cajaTexto" size="80" readonly="readonly"/>       		       		
       </td>   
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText">Oficina Principal *</font>&nbsp;
       		<input value="${Solicitud.codOficina}" class="cajaTexto" size="10" readonly="readonly"/>
       		<input value="${Solicitud.desOficina}" class="cajaTexto" size="40" readonly="readonly"/> 
	   </td>
	   <td align="right" valign="middle">
			<font class="fontText">Gestor *</font>&nbsp;
			<input value="${Solicitud.gestorCod}" class="cajaTexto" size="10" readonly="readonly"/>
       		<input value="${Solicitud.gestorNom}" class="cajaTexto" size="40" readonly="readonly"/> 
       </td>
	</tr>
	<tr>
	   <td align="left">&nbsp;</td>
       <td align="right" valign="middle">
			<font class="fontText">Empleador *</font>&nbsp;
			<input value="${Solicitud.empleadorCod}" class="cajaTexto" size="10" readonly="readonly"/>
       		<input value="${Solicitud.empleadorNom}" class="cajaTexto" size="40" readonly="readonly"/> 
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
       		<font class="fontText">Ejecutivo de Cuenta *</font>
       </td>
       <td align="left" valign="middle">
       		<input value="${Solicitud.ejecutivoCtaCod}" class="cajaTexto" size="10" readonly="readonly"/>
       		<input value="${Solicitud.ejecutivoCtaNom}" class="cajaTexto" size="40" readonly="readonly"/> 
	   </td>
	   <td align="right" valign="middle">
			<font class="fontText">Fecha de Ingreso Oficina *</font>&nbsp;
			<input value="${Solicitud.strFechaIngreso}" class="cajaTexto" size="30" readonly="readonly"/> 
       </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText">Oficina de Alta *</font>
       </td>
       <td align="left" valign="middle">
       		<input value="${Solicitud.oficinaAltaCod}" class="cajaTexto" size="10" readonly="readonly"/>
       		<input value="${Solicitud.oficinaAltaNom}" class="cajaTexto" size="40" readonly="readonly"/>
	   </td>
	   <td align="right" valign="middle">&nbsp;</td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font class="fontText">Gerencia Territorial *</font>
       </td>
       <td align="left" valign="middle">
       		<input value="${Solicitud.gerenciaTerritorialCod}" class="cajaTexto" size="10" readonly="readonly"/>
       		<input value="${Solicitud.gerenciaTerritorialNom}" class="cajaTexto" size="40" readonly="readonly"/>
	   </td>
	   <td align="right" valign="middle">&nbsp;</td>
	</tr>
	</table>
	</fieldset>
	
	<fieldset style="width: 1260px;">
   	<legend>
   	<font class="txt-titulo">
   	Datos del Producto
   	</font></legend>
	
	<table id="listProducts" class="grid" style="width: 1190px;"></table>

	</fieldset>
	
	<fieldset style="width: 1200px; padding-left: 10px; padding-right: 10px; padding-top: 10px;padding-bottom: 10px;">
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
        	<font class="fontText">Rating*</font>
        </td>
        <td valign="middle">        	
        	<input value="${Solicitud.rating}" class="cajaTexto" size="19" readonly="readonly"/>       		
        </td>
    </tr>
    <tr>
		<td>
        	<font class="fontText">Scorating*</font>
        </td>
        <td valign="middle">
        	<input value="${Solicitud.scorating}" class="cajaTexto" size="19" readonly="readonly"/> 
        </td>
    </tr>
    <tr>
       <td valign="middle">
       		<font class="fontText">Clasificación del Cliente *</font>
       </td>
       <td valign="middle">
       		<input value="${Solicitud.clasificacion}" class="cajaTexto" size="19" readonly="readonly"/> 
       </td>
	</tr>
	</table>
	</td>
	
	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font class="fontText">Relevancia Pública *</font><br/>
        	
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="relevPublica1" class="cajaTexto" id="relevPublica1" size="60" maxlength="19" value='${Solicitud.relevPublica1}' readonly="readonly"><br/>
			<input type="text" name="relevPublica2" class="cajaTexto" id="relevPublica2" size="60" maxlength="19" value='${Solicitud.relevPublica2}' readonly="readonly"><br/>
			<input type="text" name="relevPublica3" class="cajaTexto" id="relevPublica3" size="60" maxlength="19" value='${Solicitud.relevPublica3}' readonly="readonly"><br/>
			<input type="text" name="relevPublica4" class="cajaTexto" id="relevPublica4" size="60" maxlength="19" value='${Solicitud.relevPublica4}' readonly="readonly"><br/>
			<input type="text" name="relevPublica5" class="cajaTexto" id="relevPublica5" size="60" maxlength="19" value='${Solicitud.relevPublica5}' readonly="readonly">
			<%}else{%>
			<input type="text" name="relevPublica1" class="cajaTexto" id="relevPublica1" size="60" maxlength="100"  value='${Solicitud.relevPublica1}' readonly="readonly"><br/>
			<input type="text" name="relevPublica2" class="cajaTexto" id="relevPublica2" size="60" maxlength="100"  value='${Solicitud.relevPublica2}' readonly="readonly"><br/>
			<input type="text" name="relevPublica3" class="cajaTexto" id="relevPublica3" size="60" maxlength="100"  value='${Solicitud.relevPublica3}' readonly="readonly"><br/>
			<input type="text" name="relevPublica4" class="cajaTexto" id="relevPublica4" size="60" maxlength="100"  value='${Solicitud.relevPublica4}' readonly="readonly"><br/>
			<input type="text" name="relevPublica5" class="cajaTexto" id="relevPublica5" size="60" maxlength="100"  value='${Solicitud.relevPublica5}' readonly="readonly">
			<%}%>
					
       </td>
    </tr>
	</table>
	</td>
	
	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font class="fontText">Deuda Directa *</font>
        </td>
        <td valign="middle">
        	<input value="${Solicitud.deudaDirecta}" class="cajaTexto" size="19" readonly="readonly" style="text-align: right;"/> 
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Deuda Indirecta *</font>
		</td>
		<td valign="middle">
			<input value="${Solicitud.deudaIndirecta}" class="cajaTexto" size="19" readonly="readonly" style="text-align: right;"/>
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Deuda Castigo *</font>
		</td>
		<td valign="middle">
			<input value="${Solicitud.castigo}" class="cajaTexto" size="19" readonly="readonly" style="text-align: right;"/>
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Deuda en el Sistema Financiero *</font>
		</td>
		<td valign="middle">
			<input value="${Solicitud.deudaSistemaFinanciero}" class="cajaTexto" size="19" readonly="readonly" style="text-align: right;"/>
       </td>
    </tr>
	</table>
	</td>
	
	<td valign="top">
	<table>	
	<tr>
		<td valign="middle">
        	<font class="fontText">Otros Riesgos</font>
        </td>
        <td valign="middle">
        
        	<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="otroRiesgo" class="cajaTexto" id="otroRiesgo" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${solicitudForm.otroRiesgo}' readonly="readonly"style="text-align: right;">
			<%}else{%>
			<input type="text" name="otroRiesgo" class="cajaTexto" id="otroRiesgo" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${solicitudForm.otroRiesgo}' onblur="calcularRiesgoActual(this);" style="text-align: right;">
			<%}%>        
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Riesgo Grupal</font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="riesgoGrupal" class="cajaTexto" id="riesgoGrupal" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${solicitudForm.riesgoGrupal}'  readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="riesgoGrupal" class="cajaTexto" id="riesgoGrupal" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${solicitudForm.riesgoGrupal}' readonly="readonly" style="text-align: right;">
			<%}%>
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Riesgo Actual</font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="riesgoActual" class="cajaTexto" id="riesgoActual" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${solicitudForm.riesgoActual}' readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="riesgoActual" class="cajaTexto" id="riesgoActual" size="19" maxlength="19" onkeypress="ingresoNumeros(event);" value='${solicitudForm.riesgoActual}' style="text-align: right;">
			<%}%>
			
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText">Riesgo Total</font>
		</td>
		<td valign="middle">
			
			<%if(asigPrioridadIndividual != null || asigAnulacionIndividual != null){%>
			<input type="text" name="riesgoTotal" class="cajaTexto" id="riesgoTotal" size="19" maxlength="19"  onkeypress="ingresoNumeros(event);" readonly="readonly" style="text-align: right;">
			<%}else{%>
			<input type="text" name="riesgoTotal" class="cajaTexto" id="riesgoTotal" size="19" maxlength="19"  value='${solicitudForm.riesgoTotal}'  onkeypress="ingresoNumeros(event);" readonly="readonly" style="text-align: right;">
			<%}%>
       </td>
    </tr>
	</table>
	</td>
	
	</tr>
	</table>
	</fieldset>
	<table id="listUsuarios">
	</table>
</html:form>
</body>
</html>