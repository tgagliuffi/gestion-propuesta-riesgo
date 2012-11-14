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
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/UsuarioAction.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>
<script type="text/javascript">
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColNamesRoles  = ['','Codigo','Nombre de Rol','Detalles'];
var myDataModelRoles = [  {name : 'codRol', 	index : 'codRol', 	width : VAL_WIDTH.BMED ,hidden : true},
                          {name : 'codRol', index : 'codRol',width : VAL_WIDTH.VSMALL},
						  {name : 'descripcion',    index : 'descripcion',  width : VAL_WIDTH.MED},
                          {name : 'codRol', 	index : 'codRol', 	width : VAL_WIDTH.VSMALL ,formatter: accionesFormat ,align:'center'}
                          ];

function accionesFormat(cellvalue, options, rowObject){
	return	checkbox = "<input type=\"checkbox\" onclick=\"consultarFuncionesRoles('"+cellvalue+"');\" name=\"arrayCheckVigente\" offval=\"no\"/>";
}

var myColNamesFuncionesRoles  = ['','Codigo Funcion','Nombre de Funcion'];
var myDataModelFuncionesRoles = [  {name : 'codFuncion', 	index : 'codFuncion', 	width : VAL_WIDTH.SMALL ,hidden : true	},
                            {name : 'codFuncion', index : 'codFuncion',width : VAL_WIDTH.LSMALL},
							{name : 'descripcion',    index : 'descripcion',   width : VAL_WIDTH.LARGE }
];

function consultarFuncionesRoles(valor){
	div = document.getElementById("getPrueba");
	div.style.display = "";
	jQuery("#listOficinas").GridUnload();
	UsuarioAction.getLstFuncionRol(valor, function(data){
		mostrarFuncionesRoles(data);
	});
}

function mostrarFuncionesRoles(data){
	$('body').append('<div id="paginador_listPlazos" class="grid"></div>'); 
	var paginador = "paginador_listPlazos";
	jQuery("#listOficinas").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Funciones Roles",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesFuncionesRoles,
		colModel 	: myDataModelFuncionesRoles,
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

function consultarTablaRoles(){
	jQuery("#listRoles").GridUnload();
	UsuarioAction.consultarRolesAjax( function(data){
		mostrarTablaRoles(data);
	});
}

function mostrarTablaRoles(data){
	$('body').append('<div id="paginador_listPlazos" class="grid"></div>'); 
	var paginador = "paginador_listPlazos";
	jQuery("#listRoles").jqGrid(
	{
		beforeSelectRow: function(){},
		caption		: "Listado de Roles",
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesRoles,
		colModel 	: myDataModelRoles,
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
 	var formulario = document.getElementById('usuarioForm');
 	codUsuario2=formulario.codUsuario.value;
	formulario.action =rutaContexto+'/usuarioAction.do?method=configuracionUsuario'+'&codUsuarios='+codUsuario2;
 	formulario.submit();
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

function btnConfigurarRol(){
	var formulario = document.getElementById('usuarioForm');
	var selecciones = buscarSelecciones('listRoles');
	if (selecciones.length == 0){
		alert ("No ha seleccionado ningún Registro para la Asignacion");
		return false;
	}else{
		var arrayCod = selecciones.split('**');
		if(arrayCod.length > 2){
			alert ("Solo se debe seleccionar un Registro para la Asignacion");
		}else{
			var codRol=arrayCod[1].substring(2);
			codUsuario2=formulario.codUsuario.value;
			formulario.action =rutaContexto+'/usuarioAction.do?method=listarFunciones'+'&codRol='+codRol+'&codUsuario='+codUsuario2;
		 	formulario.submit();	
		}
	}
}

function btnAsignarRol(){
	var formulario = document.getElementById('usuarioForm');
	codUsuario=formulario.codUsuario.value;
	var selecciones = buscarSelecciones('listRoles');
	if (selecciones.length == 0){
		alert ("No ha seleccionado ningún Rol para la Asignacion");
		return false;
	}else{
		codUsuario2=formulario.codUsuario.value;
		UsuarioAction.saveUsuarioRol(codUsuario2,selecciones, function(msg){
		alert("Se le asigno los roles");
		});		
		}	
}
</script>
</head>
<body onload="consultarTablaRoles();">
<html:form styleId="usuarioForm" method="post" >
 <html:hidden property="codUsuario" value="${getUsuario.codUsuario}" styleId="codUsuario"/>
<br/>
	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Módulo Asignacion de Roles</b></font>
	</div>
	<br/>
	<a href="javascript:btnRegresar();" class="buttonGPR">REGRESAR</a>
	<br/><br/>
		<fieldset style="width:400px">
   	<legend>
   	<font face="Arial Narrow" size=3 color="#000080">
   	Datos del Usuario
   	</font></legend>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>
			<font face="Arial Narrow" size=4 color="#000080"><b></b><br>
			Codigo Usuario : <c:out value="${getUsuario.codUsuario}"/>
			</font><br><br>
			<font face="Arial Narrow" size=4 color="#000080">
			Nombres y Apellidos : <c:out value="${getUsuario.nombre}"/>
			</font><br><br>
		</td>
		</tr>
		</table>
		</fieldset>
			<br>
			<a href="javascript:btnAsignarRol();" class="buttonGPR">ASIGNAR ROL</a>
			<br>
			<div class="content">
				<div class="left">
					<table id="listRoles" class="grid">
					</table>
				</div>
				<div class="right" id="getPrueba" style="display: none;">
				<font face="Arial Narrow" size=4 color="#000080"><b></b>
					Funcion:
				</font>
					<table id="listOficinas" class="grid">
					</table>
				</div>
			</div>
</html:form>
<br/>
<br/>
<center>
</center>
</body>
</html>