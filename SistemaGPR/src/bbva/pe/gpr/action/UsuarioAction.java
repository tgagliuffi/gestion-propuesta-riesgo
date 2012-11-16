package bbva.pe.gpr.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.grupobbva.bc.per.tele.ldap.comunes.IILDPeExcepcion;
import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;

import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.FuncionRol;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.UsuarioForm;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.util.Constant;

public class UsuarioAction  extends DispatchAction{
	private CatalogoService catalogoService; 
	private SeguridadService seguridadService;
	
	public UsuarioAction() {
		catalogoService = (CatalogoService)Context.getInstance().getBean("catalogoService");
		seguridadService = (SeguridadService)Context.getInstance().getBean("seguridadService");
	}
	
	public ActionForward listarUsuarios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
			Banca banca = new Banca();
			Rol rolBean = new Rol();
			banca.setEstado(Constant.ESTADO_ACTIVO);
			rolBean.setEstado(Constant.ESTADO_ACTIVO);
			try {
			request.setAttribute("getLstBanca", catalogoService.getLstBancaByCriteria(banca));
			request.setAttribute("getLstRoles", catalogoService.getLstRolesByCriteria(rolBean));
			}catch (Exception e) {
				System.out.print("Error " + e.getLocalizedMessage());
			}
		return mapping.findForward("parametriaUsuario");
	}
	
	public List<Usuario> consultarAjax(String codUsuario,String nombreUsuario ,String codBanca,Long codRol) {
		Usuario user = new Usuario();
		user.setEstado("1");
		try {
			if(codUsuario!=null && codUsuario !=""){
				 user.setCodigoUsuario("%"+codUsuario+"%");
				 }
				 if(nombreUsuario!=null && nombreUsuario !=""){
				 user.setNombres("%"+nombreUsuario+"%");
				 }
				 if(codBanca!=null && codBanca !=""){
					 user.setCodUsuarioCreacion("%"+codBanca+"%");
				 }
				 user.setIdUsuario(codRol);
		    List<Usuario> getLstUser = catalogoService.getLstUsuario(user);
			return getLstUser;
		    } catch (Exception e) {
			return new ArrayList<Usuario>();
		}
	}
	
	public ActionForward configuracionUsuario(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

			try {
				String strMensaje="";
				String codUsuario=request.getParameter("codUsuarios");
				IILDPeUsuario usuario = com.grupobbva.bc.per.tele.ldap.directorio.IILDPeUsuario.recuperarUsuario(codUsuario);
				if(usuario !=null){
				    request.setAttribute("getUsuario", usuario); 
				}else{
					strMensaje = "El Usuario no se Encuentra en el Sistema";
				}
			    request.setAttribute("mensaje",strMensaje); 
			}catch (Exception e) {
				System.out.print("Error " + e.getLocalizedMessage());
			}
		return mapping.findForward("configuracionUsuario");
	}
	
	@SuppressWarnings("unchecked")
	public Vector<IILDPeUsuario> consultarSubordinados(String codUsuario) throws IILDPeExcepcion {
		Vector<IILDPeUsuario> usuario = null;
		usuario=com.grupobbva.bc.per.tele.ldap.directorio.IILDPeGestorUsuarios.buscarSubordinados(codUsuario);
		return	usuario;
	}
	
	public List<Funcion> consultarFuncionAjax() {
		try {
			Funcion funcionBean= new Funcion();
			funcionBean.setEstado("1");
			List<Funcion> getLstFuncion= catalogoService.getLstFuncionByCriteria(funcionBean);
			return getLstFuncion;
		    } catch (Exception e){
			System.out.print(""+e.getMessage());
		    return new ArrayList<Funcion>();
		}
	}
	
	public List<Rol> consultarRolesAjax(){
		try {
			Rol rolBean = new Rol();
			rolBean.setEstado(Constant.ESTADO_ACTIVO);
			List<Rol> getLstRoles= catalogoService.getLstRolesByCriteria(rolBean);
			return getLstRoles;
		    } catch (Exception e){
			System.out.print(""+e.getMessage());
		    return new ArrayList<Rol>();
		}
	}
	
	public ActionForward listarRol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String codUsuario= request.getParameter("codUsuarios");
		Usuario user = new Usuario();
		user.setCodigoUsuario(codUsuario);
		user.setEstado("1");
		try {
		List<Usuario> getLstUser = catalogoService.getLstUsuario(user);
		UsuarioForm usuarioForm= new UsuarioForm();
		usuarioForm.setCodUsuario(getLstUser.get(0).getCodigoUsuario());
		usuarioForm.setNombre(getLstUser.get(0).getNombres());
		request.setAttribute("getUsuario",usuarioForm);
		}catch (Exception e) {
			System.out.print("Error " + e.getLocalizedMessage());
		}
		return mapping.findForward("asignarRol");
	}
	
	public ActionForward listarFunciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String codRol= request.getParameter("codRol");
		Rol rolBean = new Rol();
		rolBean.setCodRol(new BigDecimal(codRol));
		rolBean.setEstado(Constant.ESTADO_ACTIVO);
		try {
		List<Rol> getLstRoles = catalogoService.getLstRolesByCriteria(rolBean);
		UsuarioForm usuarioForm= new UsuarioForm();
		usuarioForm.setCodRol(getLstRoles.get(0).getCodRol());
		usuarioForm.setDescripcionRol((getLstRoles.get(0).getDescripcion()));
		request.setAttribute("getRol",usuarioForm);
		}catch (Exception e) {
			System.out.print("Error " + e.getLocalizedMessage());
		}
		return mapping.findForward("asignarFunciones");		
	}
	
	public List<FuncionRol> getLstFuncionRol(BigDecimal codRol){
		FuncionRol funcionRolBean= new FuncionRol(); 
		funcionRolBean.setCodRol(codRol);
		funcionRolBean.setEstado(Constant.ESTADO_ACTIVO);
		try {
			List<FuncionRol> getLstFuncionRol= seguridadService.getLstFuncionRol(funcionRolBean);
			return getLstFuncionRol;
		} catch (Exception e) {
			return new ArrayList<FuncionRol>();
		}
	}
	
	public String saveFuncionRolAjax(BigDecimal codRol, BigDecimal codFuncion){
		try {
		FuncionRol funcionRolBean= new FuncionRol();
		funcionRolBean.setCodRol(codRol);
		funcionRolBean.setCodFuncion(codFuncion);
		funcionRolBean.setEstado(Constant.ESTADO_ACTIVO);
        seguridadService.saveFuncionRol(funcionRolBean);
        return "Grabo";
		} catch (Exception e) {
			System.out.print(""+e.getMessage());
			System.out.print(""+e.getCause());
		   return e.getMessage();
		}
	}
	
	public String eliminarFuncionRolAjax(String codFunciones){
		try {
			StringTokenizer st = new StringTokenizer(codFunciones, "**");
			String concatIds = "";
			while (st.hasMoreTokens()) {
				String elemento = st.nextToken();
				StringTokenizer stComas = new StringTokenizer(elemento, "-");
				while (stComas.hasMoreTokens()) {
					stComas.nextToken();
					if(stComas.hasMoreTokens()){
						String id = stComas.nextToken();
						concatIds = concatIds + ","+id+",";
					}
				}					
			}
			concatIds = concatIds.substring(0, concatIds.length()-1);
			catalogoService.deleteOficinaAsignada(concatIds);
			return "Eliminado";
		} catch (Exception e) {
			System.out.print(""+e.getLocalizedMessage());
		   return e.getLocalizedMessage();
		}
	}
	
	public String deleteUsuario(String codUsuario){
		int rows=0;
		try {
	     rows=catalogoService.getDeleteUsuario(codUsuario);
		return "No existe";
		} catch (Exception e) {
			System.out.print(""+e.getMessage());
			System.out.print(""+e.getCause());
		   return e.getMessage();
		}
	}
	
	public String validarExisteBD(String codUsuario){
		Usuario usuarioBean= new Usuario();
		usuarioBean.setCodigoUsuario(codUsuario);
		usuarioBean.setEstado("1");
		try {
		String valor=catalogoService.getUsuarioExiste(usuarioBean);
       if (valor.equals("1")){
    	   return "Existe";
       }else
    	   return "No Existe";
		} catch (Exception e) {
			System.out.print(""+e.getCause());
		   return e.getMessage();
		}
	}

	public ActionForward registroMasivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("registroMasivo");		
	}

//	public ActionForward grabarRegistroMasivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		UsuarioForm usuarioForm= (UsuarioForm)form;
//		@SuppressWarnings("unused")
//		FormFile file=usuarioForm.getFile();
//		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
//		POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
//		HSSFWorkbook wb = new HSSFWorkbook(fs);
//		HSSFSheet sheet = wb.getSheetAt(0);
//		Usuario usuario = null;
//		Map<Integer, Object> registro = null;
//		int i = 1;
//		Row row = sheet.getRow(i);
//		int numErrores = 0;
//		int numCamposVacios = 0;
//		
//		while (row != null) {
//
//			for (int j = 0; j < Constant.TOTAL_POSICIONES_CARGA_MASIVA; j++) {
//				Cell cell = row.getCell(j);
//				Object valorValidar = null;
//				if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
//					valorValidar = cell.getStringCellValue();
//				} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//					valorValidar = cell.getNumericCellValue();
//				} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_BLANK) {
//					valorValidar = "";
//				} else {
//					valorValidar = "";
//				}
//
//				if (valorValidar == null || valorValidar.toString().trim().equals("")) {
//					numCamposVacios++;
//				}
//			}
//			if (numCamposVacios == Constant.TOTAL_POSICIONES_CARGA_MASIVA) {
//				i++;
//				row = sheet.getRow(i);
//				numCamposVacios = 0;
//				continue;
//			}
//			numCamposVacios = 0;
//			registro = new HashMap<Integer, Object>();
//			numErrores = 0;
//			for (int j = 0; j <= Constant.TOTAL_POSICIONES_CARGA_MASIVA; j++) {
//				Cell cell = row.getCell(j);
//
//				Object valor = null;
//				if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
//					valor = cell.getStringCellValue();
//				} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//					valor = cell.getNumericCellValue();
//				} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_BLANK) {
//					valor = "";
//				} else {
//					valor = "";
//				}
//
//				// validar los datos
//				FilaError error = validarDato(i + 1, j, valor,listaUsuarios);
//				if (error != null) {
//					numErrores++;
//				//	listaErrores.add(error);
//				}
//				registro.put(j, valor);
//			}
//			if (numErrores == 0) {
////				// Validar creacion de Objeto Gerente
//				FilaError error = null;
//				try {
//				//	usuario = crearObjectoGerente(registro);
////					if (gerente != null) {
////						if(gerente.getNombres()== null){
////							error = new FilaError();
////							error.setNumFila(i + 1);
////							error.setNombreColumna("LDAP");
////							error.setDescripcionError("Error consultando Nombres de Funcionario.");
////							//listaErrores.add(error);
////						}else if(gerente.getApPaterno()== null){
////							error = new FilaError();
////							error.setNumFila(i + 1);
////							error.setNombreColumna("LDAP");
////							error.setDescripcionError("Error consultando Ap. Paterno de Funcionario.");
////							//listaErrores.add(error);
////						}else if(gerente.getApMaterno()== null){
////							error = new FilaError();
////							error.setNumFila(i + 1);
////							error.setNombreColumna("LDAP");
////							error.setDescripcionError("Error consultando Ap. Materno de Funcionario.");
////							//listaErrores.add(error);
////						}else if(gerente.getCodOficina()== null){
////							error = new FilaError();
////							error.setNumFila(i + 1);
////							error.setNombreColumna("LDAP");
////							error.setDescripcionError("Error consultando el Cod. Oficina de Funcionario.");
////							//listaErrores.add(error);
////						}else if(gerente.getNombreOficina()== null){
////							error = new FilaError();
////							error.setNumFila(i + 1);
////							error.setNombreColumna("LDAP");
////							error.setDescripcionError("Error consultando el Nombre Oficina de Funcionario.");
////							//listaErrores.add(error);
////						}else if(gerente.getCorreo()== null){
////							error = new FilaError();
////							error.setNumFila(i + 1);
////							error.setNombreColumna("LDAP");
////							error.setDescripcionError("Error consultando el Correo de Funcionario.");
////							//listaErrores.add(error);
////						}else{
//						listaUsuarios.add(usuario);
////							//listaUsuarios.add(usuario);
////						}
////					} else {
////						error = new FilaError();
////						error.setNumFila(i + 1);
////						error.setNombreColumna("COD_REGISTRO");
////						error.setDescripcionError("El código de registro no existe en el LDAP.");
////						//listaErrores.add(error);
////					}
////				} catch (IILDPeExcepcion e) {
////					error = new FilaError();
////					error.setNumFila(i + 1);
////					error.setNombreColumna("LDAP");
////					error.setDescripcionError("Error consultando LDAP.");
//					//listaErrores.add(error);
//				} catch (Exception e) {
//					error = new FilaError();
//					error.setNumFila(i + 1);
//					error.setNombreColumna("COD_CARGO");
//					error.setDescripcionError(e.getMessage());
//					//listaErrores.add(error);
//				}
//			}
//			i++;
//			row = sheet.getRow(i);
//		}
//		if (!listaUsuarios.isEmpty()) {
//			//super.saveCollection(listaUsuarios);
//		}
//		return null;		
//	}
   //añadido
//	public FilaError validarDato(int fila, int posicion, Object valor,
//			List<Usuario> listaGerentes) {
//		FilaError error = null;
//		Usuario usuario = null;
//		if (posicion == Constant.POSICION_COD_REGISTRO) {
//			if (valor == null || valor.toString().trim().equals("")) {
//				error = new FilaError();
//				error.setNumFila(fila);
//				error.setNombreColumna("COD_REGISTRO");
//				error.setDescripcionError("El código de registro es obligatorio");
//			} else {
//				for (Usuario _gerente : listaGerentes) {
//					if (_gerente.getCodigoUsuario().equals(valor.toString())) {
//						error = new FilaError();
//						error.setNumFila(fila);
//						error.setNombreColumna("COD_REGISTRO");
//						error.setDescripcionError("El código de registro ya esta registrado en lineas anteriores");
//						break;
//					}
//				}
//
//			}
//
//		} else  if(posicion == Constant.POSICION_COD_BANCA){
//			if (valor != null && !valor.toString().trim().equals("")) {
//				String cargo = valor.toString();
//				try {
//					if (valor == null || valor.toString().trim().equals("")) {
//						error = new FilaError();
//						error.setNumFila(fila);
//						error.setNombreColumna("COD_BANCA");
//						error.setDescripcionError("No se pudo comprobar  el código de BANCA.");
//					}
//				} catch (Exception e) {
//					error = new FilaError();
//					error.setNumFila(fila);
//					error.setNombreColumna("COD_BANCA");
//					error.setDescripcionError("No se pudo comprobar  el código de BANCA.");
//				}
//			}
//		}else  if(posicion == Constant.POSICION_COD_ROL){
//			if (valor != null && !valor.toString().trim().equals("")) {
//				String cargo = valor.toString();
//				try {
//					if (valor == null || valor.toString().trim().equals("")) {
//						error = new FilaError();
//						error.setNumFila(fila);
//						error.setNombreColumna("COD_ROL");
//						error.setDescripcionError("No se pudo comprobar  el código de ROL.");
//					}
//				} catch (Exception e) {
//					error = new FilaError();
//					error.setNumFila(fila);
//					error.setNombreColumna("COD_ROL");
//					error.setDescripcionError("No se pudo comprobar  el código de ROL.");
//				}
//			}
//		}
//		return error;
//	}
	
	public String saveUsuarioRol(String codUsuario,String codRoles) throws Exception {
		try {
			StringTokenizer st = new StringTokenizer(codRoles, "**");
			String concatIds = "";
			while (st.hasMoreTokens()) {
				String elemento = st.nextToken();
				StringTokenizer stComas = new StringTokenizer(elemento, "-");
				while (stComas.hasMoreTokens()) {
					stComas.nextToken();
					if(stComas.hasMoreTokens()){
						String id = stComas.nextToken();
							concatIds = concatIds + ","+id+",";
					}
				}					
			}
		concatIds = concatIds.substring(0, concatIds.length()-1);
		seguridadService.saveUsuarioRol(codUsuario,concatIds);
		}catch (Exception e){
			System.out.print(""+e.getLocalizedMessage());
		}
	return "";
	}
	
	public ActionForward saveUsuario(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String codUsuario= request.getParameter("codUsuarios");
		String nombre = request.getParameter("nombre");
		Usuario user = new Usuario();
		user.setCodigoUsuario(codUsuario);
		user.setNombres(nombre);
		user.setFechaCreacion(new Date());
		user.setEstado("1");
		try {
	
		
		}catch (Exception e) {
			System.out.print("Error " + e.getLocalizedMessage());
		}
		return null;
	}
}