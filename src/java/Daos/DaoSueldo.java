/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.InterfaceSueldo;
import Pojos.Sueldo;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public class DaoSueldo implements InterfaceSueldo {

    @Override
    public boolean registrar(Session session, Sueldo sueldo) throws Exception {
        session.save(sueldo);
        return true;
    }

    @Override
    public List<Sueldo> getAll(Session session) throws Exception {
        String hql = "from Sueldo";
        Query query = session.createQuery(hql);
        List<Sueldo> listaSueldo = (List<Sueldo>) query.list();
        return listaSueldo;
    }

    @Override
    public Sueldo getByCodigoSueldo(Session session, int codigoSueldo) throws Exception {
        return (Sueldo) session.get(Sueldo.class, codigoSueldo);
    }

    @Override
    public boolean update(Session session, Sueldo sueldo) throws Exception {
        session.update(sueldo);
        return true;
    }

    @Override
    public boolean delete(Session session, Sueldo sueldo) throws Exception {
        session.delete(sueldo);
        return true;
    }

    @Override
    public List<Sueldo> getAllFecha(Session session, Date fechaInicio, Date fechaFin) throws Exception {
  String hql = "from Sueldo where fechaCreacionSueldo between :fechaInicio and :fechaFin";
        Query query = session.createQuery(hql);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
        return (List<Sueldo>) query.list(); 
    }

    @Override
    public List<Sueldo> getAllByEmpleado(Session session, String numeroDocumento) throws Exception {
  String hql = "FROM Sueldo a inner join fetch a.empleado ac where ac.numeroDocumento=:numeroDocumento";
        Query query = session.createQuery(hql);
            query.setParameter("numeroDocumento", numeroDocumento);
           
        return (List<Sueldo>) query.list();
    }
 @Override
    public List<Sueldo> getAllByIdEmpleado(Session session, int id) {
 String hql = "FROM Sueldo a inner join fetch a.empleado ac where ac.idempleado=:id";
        Query query = session.createQuery(hql);
            query.setParameter("id", id);
           
        return (List<Sueldo>) query.list();    }

   

  
  

}
