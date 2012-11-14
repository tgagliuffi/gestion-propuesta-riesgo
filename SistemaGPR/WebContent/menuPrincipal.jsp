<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page   import="bbva.pe.gpr.bean.Usuario"%>
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

<div class="demo">
	<div id="tabs">
		<ul>		
			<li><a href="#tabs1"><b>Ingreso de la Solicitud</b></a></li> 
			<li><a href="#tabs2"><b>Asignación Individual</b></a></li>
			<li><a href="#tabs3"><b>Asignación Prioridad</b></a></li>
			<li><a href="#tabs4"><b>Bandeja del Evaluador</b></a></li>
			<li><a href="#tabs5"><b>Análisis y Dictamen</b></a></li>
			<li><a href="#tabs6"><b>Estadísticas de Asignaciones</b></a></li>
			<li><a href="#tabs7"><b>Estadísticas de Atención de Solicitudes</b></a></li>
			<li><a href="#tabs8"><b>Consulta de Solicitudes</b></a></li>
			<li><a href="#tabs9"><b>Parametria Condicion Cliente</b></a></li>
			<li><a href="#tabs10"><b>Parametria Evaluadores/Dictaminadores Oficina-Territorio</b></a></li>
			<li><a href="#tabs11"><b>Parametria Usuarios</b></a></li>
		
		</ul>
		
		<div id="tabs1">
			<iframe src="ingresoSolicitud.do?method=init" name="frameIngresoSolicitud" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div> 
		
		<div id="tabs2">
			<iframe src="asignacion_individual.jsp" name="frameAsignacionIndividual" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div>
		
		<div id="tabs3">
			<iframe src="asignacion_prioridad.jsp" name="frameAsignacionPrioridad" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div>
		
		<div id="tabs4">
			<iframe src="bandejaEvaluador.do?method=index" name="frameBandejaEvaluador" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div>
		
		<div id="tabs5">
			<iframe src="dictamen.do?method=index" name="frameAnalisisDictamen" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div>
		
		<div id="tabs6">
			<iframe src="estadistica_asignacion.jsp" name="frameEstadicticaAsignacion" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div>
		
		<div id="tabs7">
			<iframe src="estadistica_atencion.jsp" name="frameEstadisticaAtencion" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div>
		
		<div id="tabs8">
			<iframe src="busquedaSolicitudAction.do?method=listarSolicitud" name="frameConsultaSolicitud" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div>
		<div id="tabs9">
			<iframe src="condicionCliente.do?method=listarCondicion" name="frameCondicionCliente" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div>
		<div id="tabs10">
			<iframe src="asignarOficina.do?method=listarAsignarOficina" name="frameAsignarOficina" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div>		
		<div id="tabs11">
			<iframe src="usuarioAction.do?method=listarUsuarios" name="frameAsignarOficina" width="100%" scrolling="auto" frameborder="0">
			  <!-- frk: En el caso que exista algun problema en el momento de cargar el iframe: -->
		      <p>Ocurrio un error no previsto, porfavor vuelva a intentarlo.</p>
		    </iframe>
		</div>		
	</div>
</div>

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