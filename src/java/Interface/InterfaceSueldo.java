/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Sueldo;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public interface InterfaceSueldo {
        public boolean registrar(Session session, Sueldo sueldo)throws Exception;
    public List<Sueldo> getAll(Session session)throws Exception;
    public Sueldo getByCodigoSueldo(Session session, int codigoSueldo)throws Exception;
    public boolean update(Session session, Sueldo tipodocumento)throws Exception;
    public boolean delete(Session session, Sueldo sueldo) throws Exception;
    public List<Sueldo> getAllFecha(Session session, Date fechaInicio, Date fechaFin) throws Exception;
    public List<Sueldo> getAllByEmpleado(Session session,String numeroDocumento)throws Exception;
        public List<Sueldo> getAllByIdEmpleado(Session session,int id)throws Exception;




}
