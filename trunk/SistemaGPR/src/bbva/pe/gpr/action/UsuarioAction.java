package bbva.pe.gpr.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.BancaSub;
import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.FuncionRol;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.bean.UsuarioRol;
import bbva.pe.gpr.bean.UsuarioSubanca;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.UsuarioForm;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.FechaUtil;
import bbva.pe.gpr.util.FilaError;

import com.grupobbva.bc.per.tele.ldap.comunes.IILDPeExcepcion;
import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;

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
			System.out.print(""+e.getMessage());
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
		return mapping.findForward("cargaMasiva");		
	}

	public ActionForward cargarRegistroMasivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      HashMap<String, String> parametros=new HashMap<String, String>();
      String rutaPlantilla= catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_CONFIGURACIONES, "16002").getStrValor() +"PLANTILLA_ASIGNAR_ROL.xls";  
      String rutaPlantillaLogError= catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_CONFIGURACIONES, "16002").getStrValor() +"PLANTILLA_LOG_ERROR.xls";  
      parametros.put("DIR_PLANTILLAS",rutaPlantilla);
		parametros.put("NOM_PLANTILLA_LOG_ERR",rutaPlantillaLogError);
		try{
			UsuarioForm usuarioForm= (UsuarioForm)form;
			grabarRegistroMasivo(usuarioForm,parametros,request);
			List<FilaError> getListError=(List<FilaError>)request.getAttribute("LstErrores");
			 if(!getListError.isEmpty()){
				 request.setAttribute("listaLog","1");
			 }
		}
		catch (Exception e) {
		}
		return mapping.findForward("cargaMasiva");		
	}

	public void grabarRegistroMasivo (ActionForm form,HashMap<String, String> parametros,HttpServletRequest request) throws Exception {
		UsuarioForm usuarioForm= (UsuarioForm)form;
		try {
		FormFile file=usuarioForm.getFile();
		List<UsuarioRol> listaUsuariosRol = new ArrayList<UsuarioRol>();
		List<FilaError> listaErrores = new ArrayList<FilaError>();
		POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		UsuarioRol usuarioRol = new UsuarioRol();
		List<UsuarioSubanca> listausuarioSubbanca = new ArrayList<UsuarioSubanca>();
		UsuarioSubanca usuarioSubbanca=new UsuarioSubanca();
		Map<Integer, Object> registro = null;
		int i = 1;
		Row row = sheet.getRow(i);
		int numErrores = 0;
		int numCamposVacios = 0;
		while (row != null) {
			for (int j = 0; j < Constant.TOTAL_POSICIONES_CARGA_MASIVA; j++) {
				Cell cell = row.getCell(j);
				Object valorValidar = null;
				if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
					valorValidar = cell.getStringCellValue();
				} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					valorValidar = cell.getNumericCellValue();
				} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_BLANK) {
					valorValidar = "";
				} else {
					valorValidar = "";
				}
				if (valorValidar == null || valorValidar.toString().trim().equals("")) {
					numCamposVacios++;
				}
			}
			if (numCamposVacios == Constant.TOTAL_POSICIONES_CARGA_MASIVA) {
				i++;
				row = sheet.getRow(i);
				numCamposVacios = 0;
				continue;
			}
			numCamposVacios = 0;
			registro = new HashMap<Integer, Object>();
			numErrores = 0;
			for (int j = 0; j <= Constant.TOTAL_POSICIONES_CARGA_MASIVA; j++) {
				Cell cell = row.getCell(j);
				Object valor = null;
				if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
					valor = cell.getStringCellValue();
				} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					valor = cell.getNumericCellValue();
				} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_BLANK) {
					valor = "";
				} else {
					valor = "";
				}
				FilaError error = validarDato(i + 1, j, valor);
				if (error != null) {
					numErrores++;
				    listaErrores.add(error);
				}
				registro.put(j, valor);
			}
			if (numErrores == 0) {
				FilaError error = null;
				try {
					usuarioRol = crearObjectoUsuario(registro);
					if (usuarioRol != null) {
						if(usuarioRol.getCodUsuario()== null){
							error = new FilaError();
							error.setNumFila(i + 1);
							error.setNombreColumna("CODIGO");
							error.setDescripcionError("Error consultando codigo de Funcionario.");
							listaErrores.add(error);
						}else if(usuarioRol.getCodRol()== null){
							error = new FilaError();
							error.setNumFila(i + 1);
							error.setNombreColumna("ROL");
							error.setDescripcionError("Error consultando rol de Funcionario.");
							listaErrores.add(error);
						}else if((usuarioRol.getDescripcion()== null)) {
							error = new FilaError();
							error.setNumFila(i + 1);
							error.setNombreColumna("SUBBANCA");
							error.setDescripcionError("Error consultando subbanca de Funcionario.");
							listaErrores.add(error);
						}else{
							listaUsuariosRol.add(usuarioRol);
						}
					} 
 		      } catch (Exception e) {
					System.out.print(""+e.getMessage());
					error = new FilaError();
					error.setNumFila(i + 1);
					error.setNombreColumna("COD_CARGO");
					error.setDescripcionError(e.getMessage());
					listaErrores.add(error);
				}
			}
			i++;
			row = sheet.getRow(i);
		}
		if (!listaUsuariosRol.isEmpty()) {
			saveUsuarioRolesCargaMasiva(listaUsuariosRol);
		}
		if (!listaErrores.isEmpty()) {
			saveLogErrores(listaErrores,parametros,request);
			request.setAttribute("LstErrores", listaErrores);
		}
		if (!listaUsuariosRol.isEmpty()) {
			saveUsuarioSubBancaCargaMasiva(listaUsuariosRol);
		}
		
		}catch (Exception e) {
	    log.debug(e.getLocalizedMessage());
		}
	}
	
	public FilaError validarDato(int fila, int posicion, Object valor) {
		FilaError error = null;
		if (posicion == Constant.POSICION_COD_REGISTRO) {
			if (valor == null || valor.toString().trim().equals("")) {
				error = new FilaError();
				error.setNumFila(fila);
				error.setNombreColumna("COD_REGISTRO");
				error.setDescripcionError("El código de REGISTRO es obligatorio");
			}
		} else  if(posicion == Constant.POSICION_COD_ROL){
			if (valor == null || valor.toString().trim().equals("")) {
					if (valor == null || valor.toString().trim().equals("")) {
						error = new FilaError();
						error.setNumFila(fila);
						error.setNombreColumna("COD_rol");
						error.setDescripcionError("El código de ROL es obligatorio");
							}
					}	
				}else if(posicion == Constant.POSICION_COD_SUBBANCA){
					if (valor == null || valor.toString().trim().equals("")) {
						if (valor == null || valor.toString().trim().equals("")) {
							error = new FilaError();
							error.setNumFila(fila);
							error.setNombreColumna("COD_SUBBANCA");
							error.setDescripcionError("El código de SUBBANCA es obligatorio");
								}
						}	
				}
		return error;
	}
	
	private UsuarioRol crearObjectoUsuario(Map<Integer, Object> parameter)throws IILDPeExcepcion {
		UsuarioRol usuarioRol =new UsuarioRol();
		Object value = null;
		String codRegistro = null;
		String codRol = null;
		String codSubBanca=null;
		value = parameter.get(Constant.POSICION_COD_REGISTRO);
		codRegistro = (value != null ? value.toString() : null);
		if (codRegistro.substring(0,1).equals("0")&&codRegistro.length()==7){
			codRegistro = "P"+codRegistro.substring(1,7);
		}
		value = parameter.get(Constant.POSICION_COD_ROL);
		codRol = (value != null ? value.toString() : null);
		
		value = parameter.get(Constant.POSICION_COD_SUBBANCA);
		codSubBanca = (value != null ? value.toString() : null);
		
		try {
		UsuarioSubanca usuarioSubanca= new UsuarioSubanca();
        usuarioSubanca.setCodSubanca(codSubBanca);
        usuarioSubanca.setCodUsuario(codRegistro);
        UsuarioSubanca userSubBanca=catalogoService.selectByPrimaryKey(usuarioSubanca);
        if(userSubBanca==null){
			BancaSub getSubBanca=catalogoService.selectByPrimaryKeyBancaSub(codSubBanca);
	        if(getSubBanca!=null){
	    		usuarioRol.setDescripcion(getSubBanca.getCodSubanca());
	        }else{
	        	usuarioRol.setDescripcion(null);
	        }
        }
        UsuarioRol usuario=new UsuarioRol();
		usuario.setCodRol(new BigDecimal(codRol));
		usuario.setCodUsuario(codRegistro);
		UsuarioRol user=seguridadService.selectByPrimaryKeyUsuarioRol(usuario);
		if(user==null){
			Rol getRol=catalogoService.getRolSelectByPrimaryKey(new BigDecimal(codRol));
	        if(getRol!=null){
	    		usuarioRol.setCodRol(getRol.getCodRol());
	        }else{
	        	usuarioRol.setCodRol(null);
	     }
	    Usuario getLstusuario=catalogoService.selectUsuarioByPrimaryKey(codRegistro);
		if(getLstusuario!=null){
			usuarioRol.setCodUsuario(getLstusuario.getCodigoUsuario());
	        }else{
	    		usuarioRol.setCodUsuario(null);
	        }
			usuarioRol.setEstado(Constant.ESTADO_ACTIVO);
			}
			}catch (Exception e) {
        System.out.print(""+e.getLocalizedMessage());
		}
			return usuarioRol;
	}
	
	public void saveLogErrores(List<FilaError> listaErrores, HashMap<String, String> parametros,HttpServletRequest request) throws Exception {
		InputStream inp = null;
		FileOutputStream fileOut = null;
		try {
			File file = new File(parametros.get("NOM_PLANTILLA_LOG_ERR"));
			inp = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			int i = 1;
			Row row = null;
			Cell cell0 = null;
			Cell cell1 = null;
			Cell cell2 = null;
			for (FilaError error : listaErrores) {
				row = sheet.getRow(i);
				if (row == null) {
					row = sheet.createRow(i);
				}
				cell0 = row.getCell(0);
				if (cell0 == null) {
					cell0 = row.createCell(0);
				}
				cell0.setCellValue(error.getNumFila());
				cell1 = row.getCell(1);
				if (cell1 == null) {
					cell1 = row.createCell(1);
				}
				cell1.setCellValue(error.getNombreColumna());
				cell2 = row.getCell(2);
				if (cell2 == null) {
					cell2 = row.createCell(2);
				}
				cell2.setCellValue(error.getDescripcionError());
				i++;
			}
		    String rutaPlantilla= catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_CONFIGURACIONES, "16002").getStrValor();
			String nombreFileError =rutaPlantilla+"LOG_ERROR_"+FechaUtil.formatFecha(new Date(),FechaUtil.YYYYMMDD_HHMMSS)+".xls";
			fileOut = new FileOutputStream(nombreFileError);
			request.getSession().setAttribute("FILE_LOG_ERROR_MASIVO", nombreFileError);
			wb.write(fileOut);
		} catch (FileNotFoundException e) {
			log.debug(e);
		} catch (InvalidFormatException e) {
			log.debug(e);
		} catch (IOException e) {
			log.debug(e);
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				log.debug(e);
			}
		}
	}
	
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
	
    public void saveUsuarioRolesCargaMasiva(List<UsuarioRol> getLstUSuarioRoles) throws Exception{
	 if(!getLstUSuarioRoles.isEmpty()){
		  for (UsuarioRol usuarioRol:getLstUSuarioRoles){
		   seguridadService.insert(usuarioRol);
		   }
	 	}
    }	
    
    public void saveUsuarioSubBancaCargaMasiva(List<UsuarioRol> getLstUSuarioSuBanca) throws Exception{
   	 if(!getLstUSuarioSuBanca.isEmpty()){
   		  for (UsuarioRol usuarioSubBanca:getLstUSuarioSuBanca){
   		      UsuarioSubanca usuario= new UsuarioSubanca();
   		      usuario.setCodSubanca(usuarioSubBanca.getDescripcion());
   		      usuario.setCodUsuario(usuarioSubBanca.getCodUsuario());
   		      usuario.setEstado(Constant.ESTADO_ACTIVO);
   			  catalogoService.insert(usuario);
   		   }
   	 	}
       }	
    
	public ActionForward descargarPlantillaExcel(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	    
		File file;
	    String rutaPlantilla= catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_CONFIGURACIONES, "16002").getStrValor() +"PLANTILLA_ASIGNAR_ROL.xls";  
		file = new File(rutaPlantilla);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 		
		byte b[] = new byte[2048];
	    int len = 0;
	    while ((len = fis.read(b)) != -1) {
	    	baos.write(b, 0, len);
	    }
		response.setContentType("application/ms-excel");
		response.setHeader("Expires:", "0");
		response.setHeader("Content-Disposition", "attachment; filename=PlantillaUsuarioRol.xls");
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().close();
		response.getOutputStream().flush();
		return null;
	}
	
	public void descargarLogError(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	    
		File file;		
		String getNombre=(String)request.getSession().getAttribute("FILE_LOG_ERROR_MASIVO");
		file = new File(getNombre);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 		
		byte b[] = new byte[2048];
	    int len = 0;
	    while ((len = fis.read(b)) != -1) {
	    	baos.write(b, 0, len);
	    }
		response.setContentType("application/ms-excel");
		response.setHeader("Expires:", "0");
		response.setHeader("Content-Disposition", "attachment; filename=LogError.xls");
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().close();
		response.getOutputStream().flush();;
	}
   
}