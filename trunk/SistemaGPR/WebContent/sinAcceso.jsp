<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<TITLE></TITLE>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
<STYLE type=text/css>
#mensaje {
	BORDER-RIGHT: #d7e8f0 2px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #d7e8f0 2px solid;
	PADDING-LEFT: 5px;
	FONT-SIZE: 11px;
	BACKGROUND: #f5f8fa;
	PADDING-BOTTOM: 5px;
	BORDER-LEFT: #d7e8f0 2px solid;
	COLOR: #000;
	LINE-HEIGHT: 14px;
	PADDING-TOP: 5px;
	BORDER-BOTTOM: #d7e8f0 2px solid;
	FONT-FAMILY: Verdana, Arial, sans-serif;
	background-color: #83c0e2;
}
</STYLE>
</HEAD>
<body style="background-image:url('imagenes/template/arg_fondo_ex.gif');">
		<center>
   <!-- style="width: 100%;padding-top: 100px;" -->
    	
        <div id="body" style="margin-top: 200px; width: 260px;">
        	<div id="mensaje" style="background: white">
        	<table width="337">
        	<tr>
        		<td style="width: 30px;padding-top: 0px;padding-bottom: 0px;"  >
					<img src="imagenes/iconos/warning.png" width="20" >
			 </td> 
			 <!-- <td class='Estilo29'>Su Sesi�n ha expirado. Ingrese nuevamente!!!</td> -->
			 <td  style="font-family: verdana; font-size: 11px;color:graytext;">${STR_MENSAJE}</td> 
			 </tr>
        	</table></div>
		</div>
    
</center>
	</body>
<b></b>

</html>
