<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="bbva.pe.gpr.bean.*"%>
<%@ page import="java.util.List"%>

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

<%
List<Banca> getLstBanca = (List<Banca>)request.getAttribute("getLstBanca");
List<Rol> getLstRoles = (List<Rol>)request.getAttribute("getLstRoles");
%>
<script type="text/javascript">
var rutaContexto1 = location.pathname;
var rutaContexto2 = "<%=request.getContextPath()%>";
var rutaContexto  = rutaContexto1.substr(0, rutaContexto1.indexOf(rutaContexto2)) + rutaContexto2;

function listarUsuario(){
	jQuery("#list2").jqGrid(
	{
		url: rutaContexto+'/asignarOficina.do?method=busquedaUsuario',
		caption		: "Listado de Usuarios",
		datatype 	: "local",
		datatype 	: "json",
		height   	: "100%",
		weight 	 	: 1000,
		colNames 	: ['Registro','Nombre', 'Sub Banca','Cargo'],
		colModel 	: [
					{name:'codUsuario',index:'codUsuario', width:55},
					{name:'nombre',index:'nombre', width:90},
					{name:'genero',index:'genero', width:100},
					{name:'codCargo',index:'codCargo', width:100}
         ],
        width : 775,
        rowList 	: [5,10,15,20,25],
		rowNum 		: 10, 
		pager 		: '#pager2',
		viewrecords : true,
		multiselect : true,			
		subGrid    	: false,
		footerrow  	: false
	});
	jQuery("#list2").jqGrid('navGrid','#pager2',{edit:true,add:false,del:false});
}
</script>
</head>
<body onload="listarUsuario()">
<html:form styleId="asignarOficinaForm" action="asignarOficina.do?method=listarAsignarOficina" >
	<div style="background-color: #0066bb;">
		<font face="Arial Narrow" size=3 color="#FFFFFF"><b>&nbsp;Parametria de Evaluadores/Dictaminadores </b></font>
	</div>
	<br/>
	<fieldset style="width: 830px">
   	<legend>
   	<font face="Arial Narrow" size="3" color="#000080">
   	
   	</font></legend>
	<table style="width: 800px" border="0" cellspacing="0" cellpadding="0">
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Registro</b></font>
       </td>
       <td align="left" valign="middle">       
	   		<html:text property="codUsuario" styleId="codUsuario" style="width: 150px" maxlength="8"/>				
	   </td>
	  <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Banca</b></font>
      </td>
      <td align="left" valign="middle">       
	   	<html:select property="bancaUsuario" styleId="bancaUsuario">
	 	     <html:option value=""></html:option>
				 <%if(getLstBanca!=null){   
				 for(int i=0; i<getLstBanca.size();i++){ %>
		     <html:option value="<%=getLstBanca.get(i).getCodBanca().toString()%>"><%=getLstBanca.get(i).getNombre()%></html:option>
  		   		<%}
				 }
  		   		%>
		</html:select>
	   </td>
	   <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Cargo</b></font>
       </td>
       <td align="left" valign="middle">       
	   	<html:select property="cargoUsuario" styleId="cargoUsuario">
	 	     <html:option value=""></html:option>
	 		 <%if(getLstRoles!=null){   
			      for(int i=0; i<getLstRoles.size();i++){ %>	
		     <html:option value="<%=getLstRoles.get(i).getCodRol().toString()%>"><%=getLstRoles.get(i).getDescripcion()%></html:option>
  			<%}
			}
  			%>
			</html:select>
	   </td>
	</tr>
	<tr>
       <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>Nombres</b></font>
       </td>
       <td align="left" valign="middle">
		   	<html:text property="nombreUsuario"  styleId="nombreUsuario" style="width: 150px"/>				
	   </td>
	   <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>A. Paterno</b></font>
       </td>
       <td align="left" valign="middle">       
		   	<html:text property="apePaterno"  styleId="apePaterno" style="width: 150px"/>				
	   </td>
	    <td align="left" valign="middle">
       		<font face="Arial Narrow" size="3" color="#000080"><b>A. Materno</b></font>
       </td>
       <td align="left" valign="middle">       
		   	<html:text property="apeMaterno"  styleId="apeMaterno" style="width: 150px"/>				
	   </td>
	</tr>
	</table>
	</fieldset>
	<br/>
	<input type="submit" name="btnConsultar" id="btnConsultar" value="Consultar" onclick="listarUsuario()">
	<input type="button" class="buttonGPR"  name="btnConsultar" id="btnConsultar" value="Asignar Oficina">
	<br/><br/>
</html:form>
<table id="list2"></table>
<div id="pager2"></div>
</body>
</html>