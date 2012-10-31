package bbva.pe.gpr.service;


import java.util.List;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;

public interface SolicitudDetalleService {
  List<SolicitudDetalle> getListSolicitudDetalleForId(Solicitud idsolicitud)throws Exception;
}