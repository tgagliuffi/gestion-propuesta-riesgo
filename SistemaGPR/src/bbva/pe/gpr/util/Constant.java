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
	public static final String TABLA_TIPOS = "T017";
	public static final String TABLA_DICTAMEN = "T014";
	public static final String TABLA_MOTIVO = "T002";
	public static final String TABLA_PROACTIVIDAD = "T013";
	public static final String TABLA_NIVEL_APROBACION = "T018";
	public static final String TABLA_SCORING = "T019";
	
	public static final String VALOR_NO_ENCONTRADO = "VALOR NO ENCONTRADO";

	public static final String USUARIO_APP = "USUARIO";
	public static final int DICTAMINA_OFICINA = 1;
	
	public static final int ACTIVO = 1;
	public static final int INACTIVO = 0;
	
	public static final String ESPACIO = " ";
	public static final String STR_VACIO = "";
	public static final String FEMENINO = "M";
	public static final String MASCULINO = "H";
	public static final String RESET_MONTO = "00.00";
	public static final String RESET_COMBO = "-1";
	
	public static final String CHAR_GUION = "-";
	public static final String CHAR_PORCENTAJE = "%";
	public static final String CHAR_CONCAT = "\\|";
	
	public static final String MSJ_ERROR = "0";
	public static final String MSJ_ALERT = "-1";
	public static final String MSJ_OK = "1";
	
	public static final String NOMBRE_LOG = "LOG_APP_GPR.LOG";
	
	public static final String ESTADO_SOLICITUD_PENDIENTE = "15001";
	public static final String ESTADO_SOLICITUD_ASIGNADO = "15002";
	public static final String ESTADO_SOLICITUD_ANALISIS = "15003";
	public static final String ESTADO_SOLICITUD_DICTAMINADO_ = "15004";
	public static final String ESTADO_SOLICITUD_RECHAZADO = "15005";
	public static final String ESTADO_SOLICITUD_ANULADO_ = "15006";
	
	
	public static final String PRIORIDAD_BAJA = "BAJA";
	public static final String PRIORIDAD_NORMAL = "NORMAL";
	public static final String PRIORIDAD_ALTA = "ALTA";
	
	public static final String PERSONA_JURIDICA = "JURIDICA";
	public static final String PERSONA_NATURAL = "NATURAL";
	
	public static final String GRUPO_PER_NATUAL = "1";
	public static final String GRUPO_CON_RATING = "2";
	public static final String GRUPO_SIN_RATING = "3";
	
	public static final BigDecimal EVALUADOR = BigDecimal.ONE ;
	public static final BigDecimal DICTAMINADOR = BigDecimal.valueOf(2);
	public static final BigDecimal RESPONSABLE = BigDecimal.valueOf(3);

	public static final String DICTAMEN = "4";
	public static final String USUARIO_OFICINA = "1";
	
	public static final String USUARIO_RIESGOS = "2";
    public static final int TOTAL_POSICIONES_CARGA_MASIVA=3;
    public static final int POSICION_COD_REGISTRO = 0;
	public static final int POSICION_COD_ROL = 1;
	public static final int POSICION_COD_SUBBANCA = 2;
	public static String NO_APTO="No Paso";
	public static String APTO="Paso";
	
	public static BigDecimal StringToBigDecimal(Object value) {
		double arg = 0;
		try {
			if(value != null) {
				arg = Double.parseDouble(value.toString());
			}
		} catch(NumberFormatException e) {
			arg = 0;
		}
		return BigDecimal.valueOf(arg);
	}
}
