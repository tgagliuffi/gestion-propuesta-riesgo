<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/gpr_style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/buttonGPR.css" />

	<!-- frk: incluir estos archivos cuando se quiera implementar el componente calendario y demas funciones jquery -->
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script type="text/javascript" src='<%=request.getContextPath()%>/dwr/interface/JDate.js'></script>
	<script type="text/javascript" src='<%=request.getContextPath()%>/dwr/interface/DictamenAction.js'></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/gridUtil.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js"></script>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js"></script> --%>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/01_jquery.layout.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/02_grid.locale-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/03_ui.multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/04_jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/05_jquery.tablednd.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/06_jquery.contextmenu.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/07_grid.addons.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/08_grid.postext.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/09_grid.setcolumns.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqGrid/10_jquery.searchFilter.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/dictamen/general.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/dictamen/dictamen.js"></script>
	<style>
		input.cajaTexto {
			padding-left: 3px;
		}
	</style>
</head>
<body>
	<form id="formAnalisisDictamen" name="formAnalisisDictamen" method="post">
		<input type="hidden" name="id_usuario" id="id_usuario" value="${requestScope.id_usuario}"/>
		<input type="hidden" name="monto_delegacion" id="monto_delegacion" value="${requestScope.monto_delegacion}"/>
		<input type="hidden" name="codAsignacion" id="codAsignacion" value="${requestScope.codAsignacion}"/>
		<input type="hidden" name="error" id="error" value="${requestScope.error}"/>
		<div style="background-color: #0066bb;height: 20px;line-height: 20px;">
			<font face="Arial Narrow" size=3 color="#FFFFFF"><b style="font-size: 12px;">&nbsp;&nbsp;Módulo de Análisis y Dictamen</b></font>
		</div>
		<br />
		<table class="ui-widget" width="800px" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<font class="fontText" color="#000080"><b>Código Central</b></font>&nbsp;
					<input type="text" name="codigoCentral" class="cajaTexto" id="codigoCentral" size="10" maxlength="8">
					&nbsp;
					<input type="button" class="buttonGPR" id="btnBuscar" value="Buscar" onclick="window.open(obtenerContexto() + 'bandejaEvaluador.do?method=index&codCliente=' + +$('#codigoCentral').val(), '_self')" />
					<input type="button" class="buttonGPR" id="btnBandeja" value="Bandeja" onclick="window.open(obtenerContexto() + 'bandejaEvaluador.do?method=index', '_self')" />
				</td>
			</tr>
		</table>
		<br />
		<div align="center" class="ui-widget ui-widget-content ui-corner-all" style="width: 900px;margin: 3px;">
			<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
	            <label>Datos del Cliente</label>
	        </div>
			<table>
				<tr>
					<td colspan="4" valign="middle">
						<font class="fontText" size="3" color="#000080"><b>Nro. Solicitud</b></font>&nbsp;
						<input type="text" name="nroSolicitud" class="cajaTexto" id="nroSolicitud" size="19" maxlength="19" value="${requestScope.nroSolicitud}" readonly>
						&nbsp;&nbsp;
						<font class="fontText" size="3" color="#000080"><b>Tipo Persona *</b></font>&nbsp;
						<input type="text" name="tipoPersona" class="cajaTexto" id="tipoPersona" size="10" maxlength="8" readonly style="width: 150px;">
						&nbsp;&nbsp;
						<font class="fontText" size="3" color="#000080"><b>Ruc / DNI *</b></font>&nbsp;
						<input type="text" name="rucDni" class="cajaTexto" id="rucDni" size="27" maxlength="27" readonly  style="width: 100px;">
					</td>
				</tr>
				<tr>
					<td colspan="4" valign="middle">
						<font class="fontText" size="3" color="#000080"><b>Razon Social / Apellidos y Nombres *</b></font>&nbsp;
						<input type="text" name="razonSocial" class="cajaTexto" id="razonSocial" size="72" maxlength="72" readonly style="width: 250px;">
					</td>
				</tr>
				<tr>
					<td align="left" valign="middle">
						<font class="fontText" size="3" color="#000080"><b>Oficina Principal *</b></font>
					</td>
					<td align="left" valign="middle">
						<input type="text" name="codOficina" class="cajaTexto" id="codOficina" size="10" maxlength="10" readonly>
						<input type="text" name="desOficina" class="cajaTexto" id="desOficina" size="20" maxlength="20" readonly style="width: 250px;">
						&nbsp;&nbsp;
					</td>
					<td align="left" valign="middle">
						<font class="fontText" size="3" color="#000080"><b>Gestor *</b></font>
					</td>
					<td align="left" valign="middle">
						<input type="text" name="codGestor" class="cajaTexto" id="codGestor" size="10" maxlength="10" readonly>
						<input type="text" name="desGestor" class="cajaTexto" id="desGestor" size="20" maxlength="20" readonly  style="width: 250px;">
					</td>
				</tr>
				<tr>
					<td align="left" valign="middle">
						<font class="fontText" size="3" color="#000080"><b>Evaluador</b></font>&nbsp;
						<img src="imagenes/${requestScope.imagen}.png" border="0"></td>
					<td align="left" valign="middle">
						<input type="text" name="codEvaluador" class="cajaTexto" id="codEvaluador" size="10" maxlength="10" value="${requestScope.id_usuario}" readonly>
						<input type="text" name="desEvaluador" class="cajaTexto" id="desEvaluador" size="20" maxlength="20" value="${requestScope.nombre_usuario}" readonly style="width: 250px;">
						&nbsp;&nbsp;
					</td>
					<td align="left" valign="middle">
						<font class="fontText" size="3" color="#000080"><b>Empleador *</b></font>
					</td>
					<td align="left" valign="middle">
						<input type="text" name="codEmpleador" class="cajaTexto" id="codEmpleador" size="10" maxlength="10" readonly style="width: 80px;">
						<input type="text" name="desEmpleador" class="cajaTexto" id="desEmpleador" size="20" maxlength="20" readonly style="width: 250px;">
					</td>
				</tr>
				<tr>
					<td align="left" valign="middle">
						<font class="fontText" size="3" color="#000080"><b>Jefe Inmediato</b></font>
					</td>
					<td align="left" valign="middle">
						<input type="text" name="codJefe" class="cajaTexto" id="codJefe" size="10" maxlength="10" value="${requestScope.id_jefe}" readonly>
						<input type="text" name="desJefe" class="cajaTexto" id="desJefe" size="20" maxlength="20" value="${requestScope.nombre_jefe}" readonly style="width: 250px;">
						&nbsp;&nbsp;
					</td>
					<td align="left" valign="middle">
						<font class="fontText" size="3" color="#000080"><b>Fecha Hoy</b></font>
					</td>
					<td align="left" valign="middle">
						<input type="text" name="fechaHoy" class="cajaTexto" id="fechaHoy" size="20" maxlength="20" readonly  style="width: 75px;">
					</td>
				</tr>
			</table>
		</div>

		<div id="tabsDictamen" style="width: 900px;">
			<ul>
				<li><a href="#tabs-1">Análisis - Dictamen</a></li>
				<li><a href="#tabs-2">Producto - Riesgo</a></li>
				<li><a href="#tabs-3">Ejecutivo</a></li>
				<li><a href="#tabs-4">Log Proceso</a></li>
			</ul>
			<div id="tabs-1" style="margin: 3px; padding: 3px;">
				<div class="ui-widget ui-widget-content ui-corner-all" style="width: 99%; margin: 3px;">
					<div align="center" class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
						<label>An&aacute;lisis</label>
					</div>
					<table style="padding: 3px; margin: 3px; width: 99%;">
						<tr>
							<td colspan="2">
								<input type="button" class="buttonGPR" value="Eliminar" id="btnEliminarAnalisis" />
								<input type="button" class="buttonGPR" value="Agregar" id="btnAgregarAnalisis" />
								<input type="button" class="buttonGPR" value="Cancelar" id="btnCancelarAnalisis" style="display: none"/>
								<input type="button" class="buttonGPR" value="Grabar" id="btnGrabarAnalisis" style="display: none"/>
							</td>
						</tr>
						<tr>
							<td rowspan="2" valign="top">
								<div id="panel_listAnalisis">
									<div id="paginador_listAnalisis" class="grid"></div>
									<table id="listAnalisis" class="grid"></table>
								</div>
								<div id="analisisEdit" style="display: none; ">
									<div class="ui-widget ui-widget-content ui-corner-all" style="width: 450px;">
										<div align="center" class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
								            <label>Proceso de Análisis</label>
								        </div>
										<table style="width: 99%; padding: 3px; margin: 3px;">
											<tr>
												<td>Proceso</td>
												<td>
													<select id="cboProceso"></select>
												</td>
											</tr>
											<tr>
												<td>Motivo</td>
												<td>
													<select id="cboMotivo"></select>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</td>
							<td>
								Mensaje
							</td>
						</tr>
						<tr>
							<td valign="top">
								<textarea id="textMensaje" rows="10" cols="40" style="width: 390px; height: 150px;" readonly></textarea>
							</td>
						</tr> 
					</table>
				</div>
				<div align="center" class="ui-widget ui-widget-content ui-corner-all" style="width: 99%;margin: 3px;">
					<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
			            <label>Dictaminación</label>
			        </div>
			    	<table style="padding: 3px; margin: 3px; width: 99%;">
						<tr>
							<td valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Dictamen</b></font>
							</td>
							<td align="left" valign="middle">
								<select id="slctDictamen" name="slctDictamen" id="slctDictamen" />
							</td>
							<td valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Nivel Aprobación</b></font>
							</td>
							<td valign="middle">
								<select id="slctNivAprob" name="slctNivAprob" id="slctNivAprob" />
							</td>
                            <td></td>
                            <td></td>
						</tr>
						<tr>
							<td valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Moneda Solicitud</b></font>
							</td>
							<td align="left" valign="middle">
								<select id="tipoMoneda" name="tipoMoneda">
									<option value="SL">SOLES</option>
									<option value="DL">DÓLARES</option>
								</select>
							</td>
							<td valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Riesgo Total</b></font>
							</td>
							<td valign="middle">
								<input type="text" name="riesgoTotal" class="cajaTexto" id="riesgoTotal" size="15" maxlength="15">&nbsp;
								<img src="imagenes/rojo.png" border="0">
							</td>
							<td valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Monto Aprobado</b></font>
							</td>
							<td align="left" valign="middle">
								<input type="text" name="montoAprobado" class="cajaTexto" id="montoAprobado" size="15" maxlength="15">
							</td>
						</tr>
						<tr>
							<td valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Ctrl Rating (C/O/I)</b></font>&nbsp;							
							</td>
							<td align="left" valign="middle">
	                            <input type="text" name="rating_dictamen" class="cajaTexto" id="rating_dictamen" size="3" maxlength="1">
							</td>
							<td valign="middle" colspan="4">
                                <table>
                                    <tr>
                                        <td>
                                            <font class="fontText" size="3" color="#000080"><b>Cual</b></font>&nbsp;
                                            <input type="checkbox" name="montoCualitativo" id="montoCualitativo" />
                                        </td>
                                    <td>
                                            <font class="fontText" size="3" color="#000080"><b>Cuant</b></font>&nbsp;
                                            <input type="checkbox" name="montoCuantitativo" id="montoCuantitativo" />
                                    </td>
                                        <td>
                                            <font class="fontText" size="3" color="#000080"><b>Icom</b></font>&nbsp;
                                            <input type="checkbox" name="montoICom" id="montoICom" />        
                                    </td>
                                        <td>
                                            <font class="fontText" size="3" color="#000080"><b>CAlertas</b></font>&nbsp;
                                            <input type="checkbox" name="montoCAlerta" id="montoCAlerta" />        
                                    </td>
                                    </tr>
                                </table>
							</td>
						</tr>
						<tr>
							<td valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Proactividad</b></font>
							</td>
							<td colspan="2" align="left" valign="middle">
								<select id="slctProactividad" style="width: 250px" name="slctProactividad">
									<option value="-1">----</option>
								</select>
							</td>
							<td align="right" valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Otro</b></font>&nbsp;&nbsp;
							</td>
							<td colspan="2" align="left" valign="middle">
								<input type="text" name="valOtr" class="cajaTexto" id="valOtr" size="15" maxlength="15">
							</td>
						</tr>
						<tr>
							<td valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Ctrl Scoring (C/O/I)</b></font></td>
							<td colspan="2" align="left" valign="middle">
								<input type="text" name="ctrlScoring_dictamen" class="cajaTexto" id="ctrlScoring_dictamen" size="3" maxlength="1">
							</td>
							<td colspan="2" valign="middle">
								<font class="fontText" size="3" color="#000080">
									<b>Fecha Vencimiento de la Solicitud dictaminada</b>
								</font>
							</td>
							<td colspan="2" align="left" valign="middle">
								<input type="text" name="fecVencimiento" class="cajaTexto" id="fecVencimiento" size="15" maxlength="15"></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="5" valign="middle">
								<input type="button" class="buttonGPR" name="btnDictaminar" id="btnDictaminar" value="Dictaminar / Dictamen Superior">
								<input type="button" class="buttonGPR" name="btnCondiciones" id="btnCondiciones" value="Condiciones Adicionales">
								<input type="button" class="buttonGPR" name="btnCondicionesScoring" id="btnCondicionesScoring" value="Condiciones del Scoring">
							</td>
						</tr>
					</table>
			    </div>
			</div>
			<div id="tabs-2" style="margin: 3px; padding: 3px;">
				<div align="center" class="ui-widget ui-widget-content ui-corner-all" style="width: 99%;margin: 3px;">
					<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
			            <label>Producto</label>
			        </div>
					<table style="padding: 3px; margin: 3px; width: 99%;">
						<tr>
							<td align="left" valign="middle"><font class="fontText"
								size="3" color="#000080"><b>Banca</b></font>&nbsp;
								<select id="bancaCliente" name="bancaCliente">
									<option value="BP">Banca Personas</option>
									<option value="BC">Banca Corporativa</option>
									<option value="BM">Banca Mayorista</option>
									<option value="BE">Banca Empresas</option>
								</select>
							</td>
							<td align="center" valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Moneda</b></font>&nbsp;
								<select id="tipoMoneda" name="tipoMoneda">
									<option value="SL">SOLES</option>
									<option value="DL">DÓLARES</option>
								</select>
							</td>
							<td align="right" valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Monto total</b></font>&nbsp;
								<input type="text" name="montoTotal" class="cajaTexto" id="montoTotal" size="20" maxlength="20">&nbsp;
								<input type="button" class="buttonGPR" name="btnBuscar" id="btnBuscar" value="Buscar">
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div id="panel_listProducts">
									<div id="paginador_listProducts" class="grid"></div>
									<table id="listProducts" class="grid"></table>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div align="center" class="ui-widget ui-widget-content ui-corner-all" style="width: 99%;margin: 3px;">
					<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
			            <label>Riesgo del Cliente</label>
			        </div>
					<table>
						<tr>
							<td valign="top">
								<table>
									<tr>
										<td valign="middle">
											<font class="fontText" size="3" color="#000080"><b>Rating *</b></font>
										</td>
										<td valign="middle">
											<input type="text" name="rating" class="cajaTexto" id="rating" size="19" maxlength="19" readonly>
										</td>
									</tr>
									<tr>
										<td valign="middle">
											<font class="fontText" size="3" color="#000080"><b>Scorating *</b></font>
										</td>
										<td valign="middle">
											<input type="text" name="Scorating" class="cajaTexto" id="Scorating" size="19" maxlength="19" readonly>
										</td>
									</tr>
									<tr>
										<td valign="middle">
                                        	<font class="fontText" size="3" color="#000080"><b>Clasificación del Cliente *</b></font>
                                         </td>
										<td valign="middle">
                                        	<input type="text" name="clasifCliente" class="cajaTexto" id="clasifCliente" size="19" maxlength="19" readonly>
										</td>
									</tr>
								</table>
							</td>
		
							<td valign="top">
								<table>
									<tr>
										<td valign="middle">
											<font class="fontText" size="3" color="#000080"><b>Relevancia Pública *</b></font><br />
											<input type="text" name="relevPublica1" class="cajaTexto" id="relevPublica1" size="19" maxlength="19" readonly><br />
											<input type="text" name="relevPublica2" class="cajaTexto" id="relevPublica2" size="19" maxlength="19" readonly><br />
											<input type="text" name="relevPublica3" class="cajaTexto" id="relevPublica3" size="19" maxlength="19" readonly><br />
											<input type="text" name="relevPublica4" class="cajaTexto" id="relevPublica4" size="19" maxlength="19" readonly><br />
											<input type="text" name="relevPublica5" class="cajaTexto" id="relevPublica5" size="19" maxlength="19" readonly>
										</td>
									</tr>
								</table>
							</td>
		
							<td valign="top">
								<table>
									<tr>
										<td valign="middle"><font class="fontText" size="3"
											color="#000080"><b>Deuda Directa *</b></font></td>
										<td valign="middle"><input type="text" name="deudaDirecta"
											class="cajaTexto" id="deudaDirecta" size="19" maxlength="19" readonly>
										</td>
									</tr>
									<tr>
										<td valign="middle"><font class="fontText" size="3"
											color="#000080"><b>Deuda Indirecta *</b></font></td>
										<td valign="middle"><input type="text"
											name="deudaIndirecta" class="cajaTexto" id="deudaIndirecta"
											size="19" maxlength="19" readonly></td>
									</tr>
									<tr>
										<td valign="middle"><font class="fontText" size="3"
											color="#000080"><b>Deuda Castigo *</b></font></td>
										<td valign="middle"><input type="text" name="deudaCastigo"
											class="cajaTexto" id="deudaCastigo" size="19" maxlength="19" readonly>
										</td>
									</tr>
									<tr>
										<td valign="middle"><font class="fontText" size="3"
											color="#000080"><b>Deuda en el Sistema Financiero *</b></font>
										</td>
										<td valign="middle"><input type="text" name="deudaSF"
											class="cajaTexto" id="deudaSF" size="19" maxlength="19" readonly>
										</td>
									</tr>
								</table>
							</td>
		
							<td valign="top">
								<table>
									<tr>
										<td valign="middle"><font class="fontText" size="3"
											color="#000080"><b>Otros Riesgos</b></font></td>
										<td valign="middle"><input type="text" name="otrosRiesgos"
											class="cajaTexto" id="otrosRiesgos" size="19" maxlength="19" readonly>
										</td>
									</tr>
									<tr>
										<td valign="middle"><font class="fontText" size="3"
											color="#000080"><b>Riesgo Grupal</b></font></td>
										<td valign="middle"><input type="text" name="riesgoGrupal"
											class="cajaTexto" id="riesgoGrupal" size="19" maxlength="19" readonly>
										</td>
									</tr>
									<tr>
										<td valign="middle"><font class="fontText" size="3"
											color="#000080"><b>Riesgo Actual</b></font></td>
										<td valign="middle"><input type="text" name="riesgoActual"
											class="cajaTexto" id="riesgoActual" size="19" maxlength="19" readonly>
										</td>
									</tr>
									<tr>
										<td valign="middle"><font class="fontText" size="3"
											color="#000080"><b>Riesgo Total</b></font></td>
										<td valign="middle"><input type="text" name="riesgoTotal1"
											class="cajaTexto" id="riesgoTotal1" size="19" maxlength="19" readonly>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="tabs-3" style="margin: 3px; padding: 3px;">
				<div align="center" class="ui-widget ui-widget-content ui-corner-all" style="width: 99%;margin: 3px;">
					<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
			            <label>Oficina y Ejecutivo</label>
			        </div>
					<table style="padding: 3px; margin: 3px; width: 99%;">
						<tr>
							<td align="left" valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Ejecutivo de Cuenta *</b></font>
							</td>
							<td align="left" valign="middle">
								<input readonly type="text" name="codCuentaEjecutivo" class="cajaTexto" id="codCuentaEjecutivo" size="10" maxlength="10">
								<input readonly type="text" name="desCuentaEjecutivo" class="cajaTexto" id="desCuentaEjecutivo" size="20" maxlength="20" style="width: 250px;">
							</td>
							<td align="right" valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Fecha de Ingreso Oficina *</b></font>&nbsp;
								<input readonly style="width: 85px;" type="text" name="fechaIngresoOfic" class="cajaTexto" id="fechaIngresoOfic" size="20" maxlength="20">
							</td>
						</tr>
						<tr>
							<td align="left" valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Oficina de Alta *</b></font>
							</td>
							<td align="left" valign="middle">
								<input readonly type="text" name="codOficinaAlta" class="cajaTexto" id="codOficinaAlta" size="10" maxlength="10">
								<input readonly type="text" name="desOficinaAlta" class="cajaTexto" id="desOficinaAlta" size="20" maxlength="20" style="width: 250px;">
								&nbsp;&nbsp;						
							</td>
							<td align="right" valign="middle">&nbsp;</td>
						</tr>
						<tr>
							<td align="left" valign="middle">
								<font class="fontText" size="3" color="#000080"><b>Gerencia Territorial *</b></font>
							</td>
							<td align="left" valign="middle">
								<input readonly type="text" name="codGerenciaTerrit" class="cajaTexto" id="codGerenciaTerrit" size="10" maxlength="10">
								<input readonly type="text" name="desGerenciaTerrit" class="cajaTexto" id="desGerenciaTerrit" size="20" maxlength="20" style="width: 250px;">
								&nbsp;&nbsp;
							</td>
							<td align="right" valign="middle">&nbsp;</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="tabs-4" style="margin: 3px; padding: 3px;">
				<div align="center" class="ui-widget ui-widget-content ui-corner-all" style="width: 99%;margin: 3px;">
					<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
			            <label>Log de Procesos</label>
			        </div>
			        <table style="padding: 3px; margin: 3px; width: 99%;">
						<tr>
							<td>
								<div id="panel_listLogProceso">
									<div id="paginador_listLogProceso" class="grid"></div>
									<table id="listLogProceso" class="grid"></table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
	
	<div id="dialog-form" title="Condiciones Adicionales" style="width: 400px">
		<form>
			<div class="ui-widget ui-widget-content ui-corner-all" style="margin: 2px;">
				<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
		            <label>&nbsp;&nbsp;&nbsp;Plazo</label>
		        </div>
		        <table style="padding: 3px; margin: 3px; width: 99%;">
					<tr>
						<td>Al Vencimiento</td>
						<td><input type="text" id="alVencimiento" value="" maxlength="30" /></td>
					</tr>
					<tr>
						<td>Al Reembolso</td>
						<td><input type="text" id="alVencimiento" value="" maxlength="30" /></td>
					</tr>
				</table>
			</div>
			<div class="ui-widget ui-widget-content ui-corner-all" style="margin: 2px;">
				<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
		            <label>&nbsp;&nbsp;&nbsp;Garantia</label>
		        </div>
		        <center>
		        	<textarea id="textGarantia" rows="10" cols="40" style="width: 380px; height: 140px;"></textarea>
		        </center>
		    </div>
		    <div class="ui-widget ui-widget-content ui-corner-all" style="margin: 2px;">
				<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
		            <label>&nbsp;&nbsp;&nbsp;Condicionantes</label>
		        </div>
		        <center>
		        	<textarea id="textCondicionantes" rows="10" cols="40" style="width: 380px; height: 140px;"></textarea>
		        </center>
		    </div>
		</form>
	</div>

	<div id="dialog-scoring" title="Condiciones del Scoring" style="width: 400px">
		<form>
			<div class="ui-widget ui-widget-content ui-corner-all" style="margin: 2px;">
				<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
		            <label>&nbsp;&nbsp;&nbsp;Condiciones del Scoring</label>
		        </div>
		        <table id="listCondiciones" style="padding: 3px; margin: 3px; width: 99%;">
					<tr><td><input type="checkbox"/>&nbsp;Condicion 1</td></tr>
					<tr><td><input type="checkbox"/>&nbsp;Condicion 2</td></tr>
					<tr><td><input type="checkbox"/>&nbsp;Condicion 3</td></tr>
				</table>
			</div>
		</form>
	</div>

	<div id="dialog-mensaje" title="Solicitud: Mensaje" style="width: 410px">
		<form>
			<div class="ui-widget ui-widget-content ui-corner-all" style="margin: 2px;">
				<div class="ui-widget ui-state-default ui-corner-top" style="height: 20px;line-height: 20px;">
		            <label>&nbsp;&nbsp;&nbsp;Mensaje</label>
		        </div>
		        <textarea id="textStrMensaje" rows="10" cols="40" style="width: 390px; height: 150px;" readonly></textarea>
		    </div>
		</form>
	</div>
</body>
</html>
