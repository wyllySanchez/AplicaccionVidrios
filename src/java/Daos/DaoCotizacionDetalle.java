/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.CotizacionDetalle;
import Pojos.Cotizaciondetalle;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public class DaoCotizacionDetalle implements CotizacionDetalle{

    @Override
    public boolean registar(Session session, Cotizaciondetalle cotizacionDetalle) throws Exception {
session.save(cotizacionDetalle);
    return true;
    }

    @Override
    public boolean actualizar(Session session, Cotizaciondetalle cotizacionDetalle) throws Exception {
session.update(cotizacionDetalle);
    return true;
    }

    @Override
    public boolean eliminar(Session session, Cotizaciondetalle cotizacionDetalle) throws Exception {
session.delete(cotizacionDetalle);
    return true;
    }

    @Override
    public Cotizaciondetalle getById(Session session, int id) throws Exception {
  return (Cotizaciondetalle) session.get(CotizacionDetalle.class, id);     }

    @Override
    public List<Cotizaciondetalle> getAll(Session session) throws Exception {
 String hql = "FROM Cotizaciondetalle";
        Query query = session.createQuery(hql);
        return (List<Cotizaciondetalle>) query.list();   
    }

    @Override
    public List<Cotizaciondetalle> getAllByIdCotizacion(Session session, int idCotizacion) throws Exception {
  String hql = "FROM Cotizaciondetalle d inner join fetch d.cotizacion dc where dc.idcotizacion=:idCotizacion";
        Query query = session.createQuery(hql);
         query.setParameter("idCotizacion", idCotizacion);
        return (List<Cotizaciondetalle>) query.list();     }
    
}
