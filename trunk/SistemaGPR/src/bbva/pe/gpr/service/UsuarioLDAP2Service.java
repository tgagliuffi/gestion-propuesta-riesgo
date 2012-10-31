package bbva.pe.gpr.service;


import org.springframework.dao.DataAccessException;

import bbva.pe.gpr.bean.UsuarioLDAP2;
import bbva.pe.gpr.dao.UsuarioLDAP2DAO;


public interface UsuarioLDAP2Service {
    /**
     * @param codusu
     * @return
     */
    public abstract int deleteByPrimaryKey(String codusu)
            throws DataAccessException;

    /**
     * @param record
     */
    public abstract void insert(UsuarioLDAP2 record) throws DataAccessException;

    /**
     * @param record
     */
    public abstract void insertSelective(UsuarioLDAP2 record)
            throws DataAccessException;

    /**
     * @param codusu
     * @return
     */
    public abstract UsuarioLDAP2 selectByPrimaryKey(String codusu)
            throws DataAccessException;

    /**
     * @param record
     * @return
     */
    public abstract int updateByPrimaryKey(UsuarioLDAP2 record)
            throws DataAccessException;

    /**
     * @param record
     * @return
     */
    public abstract int updateByPrimaryKeySelective(UsuarioLDAP2 record)
            throws DataAccessException;

    /**
     * @param usuarioLDAP2DAO
     *            The usuarioLDAP2DAO to set.
     */
    public abstract void setUsuarioLDAP2DAO(UsuarioLDAP2DAO usuarioLDAP2DAO)
            throws DataAccessException;
}