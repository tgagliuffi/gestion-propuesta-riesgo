package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.Asignacion;

public interface AsignacionService {
  int asignarSolicitud(Asignacion asignacionBean)throws Exception;   
  List<Asignacion> getLstAsignaciones(Asignacion record)throws Exception;
  int asignarSolicitudMasiva(String arraySolitcitudes, String registro, String codUsuarioAsigno)throws Exception;
}