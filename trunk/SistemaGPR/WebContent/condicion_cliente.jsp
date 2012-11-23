<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@page import="bbva.pe.gpr.bean.*"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
List<MultitablaDetalle> usuarioBbva = (List<MultitablaDetalle>)request.getAttribute("multitablaDetalleBBVA");
List<MultitablaDetalle> usuarioBureau = (List<MultitablaDetalle>)request.getAttribute("multitablaDetalleBUREAU");
List<MultitablaDetalle> usuarioSiFinan = (List<MultitablaDetalle>)request.getAttribute("multitablaDetalleSFinan");
List<MultitablaDetalle> usuarioRelPub = (List<MultitablaDetalle>)request.getAttribute("multitablaDetalleRelePubl");
List<MultitablaDetalle> usuarioInele = (List<MultitablaDetalle>)request.getAttribute("multitablaDetalleInele");

%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonOHC.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />
	
	<script src="<%=request.getContextPath()%>/js/util/gridUtil.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.js" type="text/javascript"></script>	
	<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/script.js" type="text/javascript"></script>

<script type="text/javascript">
function save_condicion(){
	var idChecked = '';
	   var idNochecked='';
       $("input:checkbox[name^='checkCliente']").each(function(index,e){
           var $this = $(this);
           if($this.is(":checked")){
               var v = $this.attr("id");
               idChecked += v +',';
           	}else{
        	    var g = $this.attr("id");
                idNochecked += g +',';
           		 }
           });
       $.ajax({
           url: "condicionCliente.do?method=actualizarCondicion",
           type: "POST",
           data: { 
        	   idChecked:idChecked,
        	   idNochecked:idNochecked
        	   },
           cache: false,
           success: function (response,request) {
        	   alert("Datos Grabados Correctamente..!!");
           }
       });      
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<html:form method="post" action="condicionCliente.do?method=listarCondicion">
<input type="hidden"/>

	<br>
	<br>
	<table>
    <tr>
    <td>
	<fieldset style="width:120px">
   		<legend>
  			<font class="fontText">
   			<b>Bureau</b>
   			</font></legend>
   			<%if(!usuarioBureau.isEmpty() && usuarioBureau!=null) {%>
    			<% for(int i=0;i<usuarioBureau.size();i++) {%>
	            <% if(usuarioBureau.get(i).getNumberValor()!=null) {%>
    			    <% if(usuarioBureau.get(i).getNumberValor().intValue()==1) {%>
     				<input type="checkbox" name="checkCliente" id="<%= usuarioBureau.get(i).getCodElemento() %>" class="cajaTexto" size="10" checked="checked"><%=usuarioBureau.get(i).getStrValor()%><br>
				    <% } else {%>
				    <input type="checkbox" name="checkCliente" class="cajaTexto" id="<%= usuarioBureau.get(i).getCodElemento() %>"><%=usuarioBureau.get(i).getStrValor()%><br>
				    <%}   %>     				
				<% }%>
				<% }%>
				<%}%> 
		   <br/>
	</fieldset>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 </td>
   <td>
	<fieldset style="width:160px">
   		<legend>
  			<font class="fontText">
   				<b>Clasificacion BBVA</b>
   			</font></legend>
			<%if(!usuarioBbva.isEmpty() && usuarioBbva!=null) {%>
				<% for(int i=0;i<usuarioBbva.size();i++) {%>
	            <% if(usuarioBbva.get(i).getNumberValor()!=null) {%>
    			    <% if(usuarioBbva.get(i).getNumberValor().intValue()==1) {%>
     				<input type="checkbox" name="checkCliente" class="cajaTexto" id="<%= usuarioBbva.get(i).getCodElemento() %>" checked="checked"><%=usuarioBbva.get(i).getStrValor()%><br>
				    <% } else {%>
				    <input type="checkbox" name="checkCliente" class="cajaTexto" id="<%= usuarioBbva.get(i).getCodElemento() %>" ><%=usuarioBbva.get(i).getStrValor()%><br>
				    <%}   %>     				
				<% }%>
				<% }%>
				<%} %>
	<br/>
	</fieldset>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
	<fieldset style="width:180px">
   		<legend>
  			<font class="fontText">
   				<b>Clasificacion S. Financiero</b>
   			</font></legend>
	    	<%if(!usuarioSiFinan.isEmpty() && usuarioSiFinan!=null) {%>
	            <% for(int i=0;i<usuarioSiFinan.size();i++) {%>
    			<% if(usuarioSiFinan.get(i).getNumberValor()!=null) {%>
    			    <% if(usuarioSiFinan.get(i).getNumberValor().intValue()==1) {%>
     				<input type="checkbox" name="checkCliente" class="cajaTexto" id="<%= usuarioSiFinan.get(i).getCodElemento() %>" checked="checked"><%=usuarioSiFinan.get(i).getStrValor()%><br>
				    <% } else {%>
				    <input type="checkbox" name="checkCliente" class="cajaTexto" id="<%= usuarioSiFinan.get(i).getCodElemento() %>"><%=usuarioSiFinan.get(i).getStrValor()%><br>
				    <%}   %>     				
				<% }%>
				<% }%>
				<% }%>
	<br/>
	</fieldset>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>  
<td>
	<fieldset style="width:130px">
   		<legend>
  			<font class="fontText">
   				<b>Relevancia Publica</b>
   			</font></legend>
	    	<%if(!usuarioRelPub.isEmpty() && usuarioRelPub != null ) {%>
	            <% for(int i=0;i<usuarioRelPub.size();i++) {%>
   			<% if(usuarioRelPub.get(i).getNumberValor()!=null) {%>
	               <% if(usuarioRelPub.get(i).getNumberValor().intValue()==1) {%>
     				<input type="checkbox" name="checkCliente" class="cajaTexto" id="<%= usuarioRelPub.get(i).getCodElemento() %>" checked="checked"><%=usuarioRelPub.get(i).getStrValor()%><br>
				    <% } else {%>
				    <input type="checkbox" name="checkCliente" class="cajaTexto" id="<%= usuarioRelPub.get(i).getCodElemento() %>"><%=usuarioRelPub.get(i).getStrValor()%><br>
				    <%}   %>     				
				<% }%>
				<% }%>				
				<% }%>
	<br/>
	</fieldset>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</td>
<td>
	<fieldset style="width:120px">
   		<legend>
  			<font class="fontText">
   				<b>Inelegibles</b>
   			</font></legend>
	    	<%if(!usuarioInele.isEmpty() && usuarioInele!=null) {%>
	            <% for(int i=0;i<usuarioInele.size();i++) {%>
    			<% if(usuarioInele.get(i).getNumberValor()!=null) {%>	    
    			    <% if(usuarioInele.get(i).getNumberValor().intValue()==1) {%>
     				<input type="checkbox" name="checkCliente" class="cajaTexto" id="<%= usuarioInele.get(i).getCodElemento() %>" checked="checked"><%=usuarioInele.get(i).getStrValor()%><br>
				    <% } else {%>
				    <input type="checkbox" name="checkCliente" class="cajaTexto" id="<%= usuarioInele.get(i).getCodElemento() %>"><%=usuarioInele.get(i).getStrValor()%><br>
				    <%}   %>     				
				<% }%>
				<% }%>
<% }%>
	<br/>
	</fieldset>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
	</tr>
	</table>
	</html:form>
	<table>
	<tr>
	<td >
		<a href="javascript:save_condicion();" class="buttonGPR">GRABAR</a>
	</td>
    </tr>
    </table>
</body>
</html>