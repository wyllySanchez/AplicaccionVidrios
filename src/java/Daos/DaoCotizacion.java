/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.InterfaceCotizacion;
import Pojos.Cotizacion;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public class DaoCotizacion implements InterfaceCotizacion{

    @Override
    public boolean registar(Session session, Cotizacion cotizacion) throws Exception {
session.save(cotizacion);
    return true;
    }

    @Override
    public boolean actualizar(Session session, Cotizacion cotizacion) throws Exception {
session.update(cotizacion);
    return true;
    }

    @Override
    public boolean eliminar(Session session, Cotizacion cotizacion) throws Exception {
session.delete(cotizacion);
    return true;
    }

    @Override
    public Cotizacion getById(Session session, int id) throws Exception {
    return (Cotizacion) session.get(Cotizacion.class, id);
    }

    @Override
    public List<Cotizacion> getAll(Session session) throws Exception {
   String hql = "FROM Cotizacion";
        Query query = session.createQuery(hql);
        return (List<Cotizacion>) query.list();  
    }

    @Override
    public Cotizacion getByUltimoRegistro(Session session) throws Exception {
  String hql="from Cotizacion order by idcotizacion desc";
        Query query=session.createQuery(hql).setMaxResults(1);
        
        return (Cotizacion) query.uniqueResult(); 
    }

    @Override
    public List<Cotizacion> getAllByCliente(Session session, String numeroDocumento) throws Exception {
  String hql = "FROM Cotizacion a inner join fetch a.cliente ac where ac.numeroDocumentoC=:numeroDocumento";
        Query query = session.createQuery(hql);
            query.setParameter("numeroDocumento", numeroDocumento);
           
        return (List<Cotizacion>) query.list();   
    }

 
}
