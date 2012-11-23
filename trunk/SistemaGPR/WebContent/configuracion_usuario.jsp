<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	 <link rel="stylesheet" type="text/css" media="screen" 	href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" 	href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen"  	href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" 	href="<%=request.getContextPath()%>/css/buttonGPR.css" />
	
	<script src="<%=request.getContextPath()%>/js/util.gpr.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js" 	type="text/javascript"></script>

	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/util/formatters.js" 	type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/script.js" 	type="text/javascript"></script>
	<script type="text/javascript" src='<%= request.getContextPath()%>/dwr/interface/UsuarioAction.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%= request.getContextPath()%>/dwr/util.js'></script>

<script type="text/javascript">

var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

var myColNamesSol  = ['', 'Registro', 'Nombres', 'Cargo'];
var myDataModelSol = [ 
                    {name : 'UID',	index : 'UID', width : VAL_WIDTH.SMALL, hidden : true	},
                    {name : 'UID',	index : 'UID', width : VAL_WIDTH.XLSMALL    },
                    {name : 'nombre', index : 'nombre',	width : VAL_WIDTH.VMED  },
                    {name : 'gesPer', index : 'gesPer', width : VAL_WIDTH.SMALL }                    
                   ];

function consultarSolicitud(){
	var formulario = document.getElementById('usuarioForm');
	var codUsuariojefe = formulario.codJefe.value;
	jQuery("#listParametria").GridUnload();
	UsuarioAction.consultarSubordinados(codUsuariojefe,function(data){
		mostrarTablaSolicitud(data);
	}); 
}

function mostrarTablaSolicitud(data){
	$('body').append('<div id="paginador_listParametria" class="grid"></div>'); 
	var paginador = "paginador_listParametria";
	jQuery("#listParametria").jqGrid(
	{
		beforeSelectRow: function(){},
		data 	 	: data,
		datatype 	: "local",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: myColNamesSol,
		colModel 	: myDataModelSol,
		rowList 	: [5,10,15,20,25],
		rowNum 		: 5, 
		pager 		: paginador,
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false,
		loadComplete :
           function (data) {}
	});
}

function viewParamUsuarioMain(){	
	var formulario = document.getElementById('usuarioForm');
	formulario.action =rutaContexto+'/usuarioAction.do?method=listarUsuarios';
	formulario.submit();
}

function cargarUsuarioLdap(){
	var formulario = document.getElementById('usuarioForm');
	var codUsuarios = formulario.codUsuarios.value;
			formulario.action =rutaContexto+'/usuarioAction.do?method=configuracionUsuario'+'&codUsuarios='+codUsuarios;
			formulario.submit();
}

function asignarRoles(){
	var formulario = document.getElementById('usuarioForm');
	codUsuarios=formulario.codUsuarios.value;
 	UsuarioAction.validarExisteBD(codUsuarios, function(msg){
 	if(msg == "Existe"){
	if(codUsuarios!=""){
	formulario.action =rutaContexto+'/usuarioAction.do?method=listarRol'+'&codUsuarios='+codUsuarios;
	formulario.submit();
	}else{
		alert("Ingresar un Codigo de Registro");
	}
 		}else{
 			alert("El Usuario no se Encuentra dentro del Sistema");
 		}
 	});
}

</script>
</head>
<body onload="consultarSolicitud();">
<form name="usuarioForm" id="usuarioForm" method="post">
	<br/>
	<br>
	<a href="javascript:viewParamUsuarioMain();" class="buttonGPR">REGRESAR</a>
	<br/><br/>
	 <fieldset style="width:800px">
   	<legend>
   	<font face="Arial Narrow" size=3 color="#000080">
   	Registros de Usuario
   	</font></legend>
	<table style="width: 800px" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="middle">
			<font class="fontText"><b>Registro</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="codUsuarios" class="cajaTexto" id="codUsuarios" size="14" maxlength="14" value="${getUsuario.UID}">&nbsp;		
<!-- 			<input type="button" name="btnCargar" id="btnCargar" value="Cargar" onclick="cargarUsuarioLdap();"> -->
		</td>
		<td valign="middle">
			<font class="fontText"><b>SubBanca</b></font>
		</td>
		<td valign="middle" colspan="2">
			<input type="text" name="bancaCliente" class="cajaTexto" id="bancaCliente"  readonly="readonly" size="50" maxlength="255" value="${getUsuario.bancoOficina.descripcion}">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText"><b>Nombres</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="nombres" class="cajaTexto" id="nombres" readonly="readonly" size="28" maxlength="255" value="${getUsuario.nombre}">
		</td>
		<td valign="middle">
			<font class="fontText"><b>A. Paterno</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="apePaterno" class="cajaTexto" id="apePaterno" readonly="readonly" size="28" maxlength="255" value="${getUsuario.apellido1}">
		</td>
		<td align="right" valign="middle">
			<font class="fontText"><b>A. Materno</b></font>&nbsp;
		</td>
		<td valign="middle">
			<input type="text" name="apeMaterno" class="cajaTexto" id="apeMaterno" readonly="readonly" size="34" maxlength="255" value="${getUsuario.apellido2}">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText"><b>Roles</b></font>
		</td>
		<td valign="middle">
			<input type="text" name="cargoCliente" class="cajaTexto" id="cargoCliente" readonly="readonly" size="28" maxlength="255" value="${getUsuario.cargo.descripcion}">
		</td>
		<td valign="middle">
			<font class="fontText"><b>Cod. Oficina</b></font>&nbsp;
		</td>
		<td valign="middle">
			<input type="text" name="codOficina" class="cajaTexto" id="codOficina"  readonly="readonly" size="28" maxlength="255" value="${getUsuario.bancoOficina.codigo}">
		</td>
		<td align="right" valign="middle">
			<font class="fontText"><b>Nombre Oficina</b></font>&nbsp;
		</td>
		<td valign="middle">
			<input type="text" name="nomOficina" class="cajaTexto" id="nomOficina" readonly="readonly" size="34" maxlength="255" value="${getUsuario.bancoOficina.descripcion}">
		</td>
	</tr>
	<tr>
		<td valign="middle">
			<font class="fontText"><b>Jefe Inmediato</b></font>&nbsp;
		</td>
		<td valign="middle" colspan="3">
			<input type="text" name="codJefe" class="cajaTexto" id="codJefe" size="15" maxlength="255" value="${getUsuario.UIDJefe}">
			<input type="text" name="desJefe" class="cajaTexto" id="desJefe" size="40" maxlength="255" value="${getUsuario.nombre}">
		</td>
	</tr>
	</table>
	</fieldset>
	<br/>
	<table>
	<tr>
	<td>
	<fieldset style="width:450px">
   	<legend>
   	<font face="Arial Narrow" size=3 color="#000080">
   	Registros Dependientes
   	</font></legend>
		<table id="listParametria" class="grid">
		</table>
	</fieldset>
	</td>
	</tr>
	<tr>
	<td>
	<input type="button" class="buttonGPR" name="btnConsultar" id="btnConsultar" value="ASIGNAR ROLES" onclick="asignarRoles();">
	</td>
	</tr>
	</table>
</form>
</body>
</html>