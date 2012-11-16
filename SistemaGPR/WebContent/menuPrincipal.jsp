<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page   import="bbva.pe.gpr.bean.Usuario"%>
<%@page import="bbva.pe.gpr.bean.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Menu Principal ::: Gestión de Propuestas de Riesgos y Control de Delegaciones</title>
<link rel="stylesheet" type="text/css" href="css/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="css/demos.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.core.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.widget.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.tabs.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$("#tabs").tabs({
		ajaxOptions: {
			error: function( xhr, status, index, anchor ) {
				$( anchor.hash ).html(
					"No se puede cargar esta pestaña, se produjo un error inesperado." );
			}
		}
	});
});
<%
String usuario = (String)request.getSession().getAttribute("USUARIO_NOMBRE");
List<Menu> getLstMenu =(List<Menu>)request.getAttribute("getLstMenu");
%>
</script>
</head>
<body>
<form name="formPrincipal">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td bgcolor="#FFFFFF"><img src="imagenes/cabizq.jpg" border="0"></td>
		<td align="right" bgcolor="#FFFFFF"><img src="imagenes/cabder.jpg" border="0"></td>
	</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<font face="Arial Narrow" size="3" color="#535AA0"><b>
			&nbsp;&nbsp;&nbsp;<%=usuario%>
			</b></font>
		</td>
		<td align="right" style="font-family:Arial;font-size:13px"><b>Gestión de Propuestas de Riesgos y Control de Delegaciones</b>&nbsp;&nbsp;&nbsp;
		<br/><br/>
		<a href="index.jsp" target="_top" class="buttonOHC">SALIR</a>&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	</table>
</form>
<%if(getLstMenu != null && !getLstMenu.isEmpty()){ %>
	
<div class="demo">
	<div id="tabs">
		<ul>		
			<%for(int i=0;i<getLstMenu.size();i++){	%>
				<li><a href="#tabs<%=i+1%>"><b><%=getLstMenu.get(i).getDescripcion()%></b></a></li>
				<%}%>
		</ul>
		<%for(int i=0;i<getLstMenu.size();i++){
				String url = getLstMenu.get(i).getLink();
				String desIframe = getLstMenu.get(i).getDescripcion();
				%>			
				<div id="tabs<%=i+1%>">
					<iframe src="<%=url%>" name="<%=desIframe%>" width="100%" scrolling="auto" frameborder="0">
				      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
				    </iframe>
				</div>
			<%}%>
	</div>
</div>
<%}%>
</body>
<script type="text/javascript">

	var viewportWidth;
	var viewportHeight;
	 
	if (typeof window.innerWidth != 'undefined') {
		viewportWidth = window.innerWidth;
		viewportHeight = window.innerHeight;
	} else if (typeof document.documentElement != 'undefined'
			&& typeof document.documentElement.clientWidth != 'undefined'
			&& document.documentElement.clientWidth != 0) {
		viewportWidth = document.documentElement.clientWidth;
		viewportHeight = document.documentElement.clientHeight;
	} else {
		viewportWidth = document.getElementsByTagName('body')[0].clientWidth;
		viewportHeight = document.getElementsByTagName('body')[0].clientHeight;
	}
	//alert('Your viewport width is '+viewportWidth+'x'+viewportHeight+'');

	listIFrames = document.getElementsByTagName("iframe");
	for(var f=0;f<listIFrames.length;f++) {
		listIFrames[f].style.height = (viewportHeight - 215)+"px";
	}
</script>
</html>