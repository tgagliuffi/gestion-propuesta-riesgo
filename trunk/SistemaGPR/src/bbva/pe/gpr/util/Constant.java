package bbva.pe.gpr.util;

import java.math.BigDecimal;

public class Constant {
	public static final String TABLA_PROCESO = "T003";
	
	public static final String MULT_PROCESO_INGRESO = "1";
	public static final String MULT_PROCESO_ASIGNAR = "2";
	public static final String MULT_PROCESO_ANALIZAR = "3";
	public static final String MULT_PROCESO_DICTAMINAR = "4";
	public static final String MULT_PROCESO_PRIORIZAR = "5";
	public static final String MULT_PROCESO_ANULAR = "6";
	
	public static final BigDecimal ESTADO_ACTIVO = new BigDecimal(1);
	public static final BigDecimal ESTADO_INACTIVO = new BigDecimal(0);
	
	public static final String TABLA_NATURALEZA = "T004";
	public static final String TABLA_MONEDA = "T005"; 
	public static final String TABLA_BUREAU = "T008";
	public static final String TABLA_BBVA = "T009";
	public static final String TABLA_SISTEMA_FINANCIERO = "T010";
	public static final String TABLA_RELEVANCIA_PUBLICA = "T011";
	public static final String TABLA_INELEGIBLES = "T012";
	public static final String TABLA_ESTADOS_SOLCITUD = "T015";
	public static final String TABLA_CONFIGURACIONES = "T016";
	
	public static final String VALOR_ENCONTRADO = "VALOR NO ENCONTRADO";

	public static final String USUARIO_APP = "USUARIO";
	public static final String PERSONA_NATURAL = "N";
	public static final int DICTAMINA_OFICINA = 1;
	
	public static final int ACTIVO = 1;
	public static final int INACTIVO = 0;
	
	public static final String ESPACIO = " ";
	public static final String VACIO = " ";
	public static final String FEMENINO = "M";
	public static final String MASCULINO = "H";
	
	public static final String GUION = "-";
	public static final String SIMBOLO_PORCENTAJE = "%";
	
	public static final String MSJ_ERROR = "0";
	public static final String MSJ_ALERT = "-1";
	public static final String MSJ_OK = "1";
	
	public static final String NOMBRE_LOG = "LOG_APP_GPR.LOG";
	
}
