/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.Factura;
import Pojos.Facturas;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ander
 */
public class DaoFactura implements Factura {

    @Override
    public boolean registar(Session session, Facturas facturas) throws Exception {
        session.save(facturas);
        return true;
    }

    @Override
    public boolean actualizar(Session session, Facturas facturas) throws Exception {
        session.save(facturas);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Facturas facturas) throws Exception {
        session.delete(facturas);
        return true;
    }

    @Override
    public Facturas getById(Session session, int id) throws Exception {
        return (Facturas) session.get(Facturas.class, id);
    }

    @Override
    public List<Facturas> getAll(Session session) throws Exception {
        String hql = "FROM Facturas";
        Query query = session.createQuery(hql);
        return (List<Facturas>) query.list();
    }
 @Override
    public Facturas getByUltimoRegistro(Session session) throws Exception {
       String hql="from Facturas order by idfacturas desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (Facturas) query.uniqueResult();
    }

    @Override
    public List<Facturas> getAllFecha(Session session, Date fechaInicio, Date fechaFin) throws Exception {
   String hql = "from Facturas where fechaventa between :fechaInicio and :fechaFin";
        Query query = session.createQuery(hql);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
        return (List<Facturas>) query.list();  
    }

    @Override
    public List<Facturas> getAllByCliente(Session session, String numeroDocumento) throws Exception {
  String hql = "FROM Facturas a inner join fetch a.cliente ac where ac.numeroDocumentoC=:numeroDocumento";
        Query query = session.createQuery(hql);
            query.setParameter("numeroDocumento", numeroDocumento);
           
        return (List<Facturas>) query.list();  
    }
}
