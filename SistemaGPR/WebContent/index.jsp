<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login ::: Gestión de Propuestas de Riesgos y Control de Delegaciones</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonOHC.css" />
</head>
<body>
<html:form method="post" action="login.do?method=validarUsuario">
<html:hidden property="desa" value="desa"/>
	<table>
		<tr><td style="font-family:Arial;font-size:13px">Usuario:</td>
		    <td><html:text property="userName" value="P011989"></html:text><html:errors property="userName" /></td>
		</tr>
		<tr><td style="font-family:Arial;font-size:13px">Contraseña:</td>
		    <td><html:password property="password" value="123"></html:password><html:errors property="password"/></td>
		</tr>
	</table>
	<html:submit/>
	<html:reset/>
</html:form>
</body>
</html>