<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>

<%@page import="bbva.pe.gpr.bean.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>:: Listado de Asignaciones del Evaluador ::</title>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonOHC.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>	
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/AsignarOficinaAction.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
<%
List<Territorio> getLstOficina = (List<Territorio>)request.getAttribute("getLstTerritorio");
%>
<script type="text/javascript">
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColNamesOficina  = ['','Codigo Oficina','Nombre de Oficina'];
var myDataModelOficina = [  {name : 'id_Oficina_asignada', 	index : 'id_Oficina_asignada', 	width : VAL_WIDTH.SMALL ,hidden : true	},
                            {name : 'cod_oficina', index : 'cod_oficina',width : VAL_WIDTH.XLSMALL},
							{name : 'cod_usuario',    index : 'cod_usuario',   width : VAL_WIDTH.LARGE}
];

function consultarOficinaAsignada(){
	var formulario = document.getElementById('asignarOficinaForm');
	var codTipoParametroPadre = formulario.codAsignar.value;
	jQuery("#listOficinas").GridUnload();
	AsignarOficinaAction.consultarOficinaAsignadaAjax(codTipoParametroPadre, function(data){
		mostrarTablaOficinaAsignada(data);
	});
}

function mostrarTablaOficinaAsignada(data){
	$('body').append('<div id="paginador_listPlazos" class="grid"></div>'); 
	var paginador = "paginador_listPlazos";
	jQuery("#listOficinas").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Oficinas",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesOficina,
		colModel 	: myDataModelOficina,
		rowList 	: [5,10],
		rowNum 		: 10, 
		pager 		: paginador,
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		jsonReader : { repeatitems: false },
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
}

function btnRegresar(){
 	var formulario = document.getElementById('asignarOficinaForm');
	formulario.action =rutaContexto+'/asignarOficina.do?method=listarAsignarOficina';
 	formulario.submit();	
}

function comboOficinas() {
	  var codTerritorio = document.getElementById("idTerritorio");
	  AsignarOficinaAction.consultarOficinaAjax(codTerritorio.value, function(data){
		dwr.util.removeAllOptions("codOficinas");
		dwr.util.addOptions("codOficinas", data,'codOficina','nombre');
	  });
}

function comboTerritorio() {
	  AsignarOficinaAction.consultarTerritorioAjax( function(data){
		dwr.util.removeAllOptions("idTerritorio");
		dwr.util.addOptions("idTerritorio", data,'codTerritorio','nombre');
	  });
}

function agregarOficina(){
	var formulario = document.getElementById('asignarOficinaForm');
	var codOficina = formulario.codOficinas.value;
	var codTerritorio=formulario.idTerritorio.value;
	var codigoUsuario = formulario.codAsignar.value;
	if(codTerritorio!=""){
	AsignarOficinaAction.saveAsignarOficinaAjax(codOficina, codigoUsuario, function(msg){
		   if(msg == "Oficina"){
	          alert("Este Usuario es de Oficina y no se le puede asignar mas oficinas");
				 }
			consultarOficinaAsignada();
			});
	}else{
		alert("Debes Seleccionar un Territorio");
	}
}

function desabilitarButton(){
		document.getElementById("btnAsignar").disabled=false;
}

function eliminarOficina(){
	var selecciones = buscarSelecciones('listOficinas');
	if (selecciones.length == 0){
			alert ("No ha seleccionado ningún Registro para la Eliminacion");
			return false;
	}else{
			var ans = confirm('¿Esta seguro que desea eliminar el registro seleccionado?');
			if(ans){
			AsignarOficinaAction.eliminarOficinaAsignadaAjax(selecciones, function(msg){
				if(msg == "Eliminado"){
					consultarOficinaAsignada();
					alert("Se Elimino Satisfactoriamente la Informacion");
				}
			});
		}
 	}
}
</script>
</head>
<body onload="consultarOficinaAsignada();comboTerritorio()">
<!-- <input type="button" name="btnRegresar" id="btnRegresar" value="Regresar" onclick="btnRegresar();"> -->
<html:form styleId="asignarOficinaForm" method="post" >
<html:hidden property="codAsignar" value="${getUsuario.codAsignar}" styleId="codUsuario"/>
<br/>
	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Módulo Asignacion de Oficinas</b></font>
	</div>
	<br/>
	<a href="javascript:btnRegresar();" class="buttonGPR">REGRESAR</a>
	<br/><br/>
	<fieldset style="width: 500px">
   	<legend>
   	<font face="Arial Narrow" size=3 color="#000080">
   	Datos del Usuario
   	</font></legend>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>
			<font face="Arial Narrow" size=4 color="#000080"><b></b><br>
			Codigo Usuario : <c:out value="${getUsuario.codAsignar}"/>
			</font><br><br>
			<font face="Arial Narrow" size=4 color="#000080">
			Nombres y Apellidos : <c:out value="${getUsuario.nombreUsuario}"/> <c:out value="${getUsuario.apePaterno}"/> <c:out value="${getUsuario.apeMaterno}"/>
			</font><br><br>
<!-- 			<font face="Arial Narrow" size=4 color="#000080"> -->
<%-- 			Territorios :  <html:select property="idTerritorio" styleId="idTerritorio" onchange="comboOficinas()"> --%>
<%-- 	 	    	<html:option value="">Seleccionar</html:option> --%>
<%-- 		    </html:select> --%>
<!-- 			</font><br><br> -->
<!-- 			<font face="Arial Narrow" size=4 color="#000080"> -->
<!-- 		    Oficinas    :   -->
<%-- 		    <html:select property="codOficinas" styleId="codOficinas" onchange="desabilitarButton()"> --%>
<%--              	<html:option value="">Seleccionar</html:option> --%>
<%--             </html:select> --%>
<!-- 			</font><br><br> -->
			</td>
		</tr>
		</table>
		</fieldset>
		<br>
	<fieldset style="width: 500px">
   	<legend>
   	<font face="Arial Narrow" size=3 color="#000080">
   	Datos de las Oficinas
   	</font></legend>
			<font face="Arial Narrow" size=4 color="#000080">
			Territorios :  <html:select property="idTerritorio" styleId="idTerritorio" onchange="comboOficinas()">
	 	    					<html:option value="">Seleccionar</html:option>
		    				</html:select>
			</font><br><br>
			<font face="Arial Narrow" size=4 color="#000080">
		    Oficinas    :  <html:select property="codOficinas" styleId="codOficinas" onchange="desabilitarButton()">
             					<html:option value="">Seleccionar</html:option>
            			   </html:select>
			</font><br><br>
			<a href="javascript:agregarOficina();" class="buttonGPR">AGREGAR</a>
			<a href="javascript:eliminarOficina();" class="buttonGPR">ELIMINAR</a>
		<br>
		<br>
		<table id="listOficinas" class="grid">
		</table>
		</fieldset>
</html:form>
<br/>
<br/>
<center>
</center>
</body>
</html>