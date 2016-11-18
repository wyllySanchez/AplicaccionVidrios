/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.interfaceAbonoDetalle;
import Pojos.Abonodetalle;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public class DaoAbonoDetalle  implements interfaceAbonoDetalle{

    @Override
    public boolean registar(Session session, Abonodetalle abonoDetalle) throws Exception {
 session.save(abonoDetalle);
    return true;
    }

    @Override
    public boolean actualizar(Session session, Abonodetalle abonoDetalle) throws Exception {
 session.update(abonoDetalle);
    return true;    }

    @Override
    public boolean eliminar(Session session, Abonodetalle abonoDetalle) throws Exception {
session.delete(abonoDetalle);
    return true;
    }

    @Override
    public Abonodetalle getById(Session session, int id) throws Exception {
    return (Abonodetalle) session.get(Abonodetalle.class, id); 
    }

    @Override
    public List<Abonodetalle> getAll(Session session) throws Exception {
     String hql = "FROM Abonodetalle";
        Query query = session.createQuery(hql);
        return (List<Abonodetalle>) query.list();  
    }

    @Override
    public List<Abonodetalle> getAllByIdFactura(Session session, int idFactura) throws Exception {
   String hql = "FROM Abonodetalle v inner join fetch v.abonos vf where vf.idabonos=:idFactura ";
        Query query = session.createQuery(hql);
         query.setParameter("idFactura", idFactura);
        return (List<Abonodetalle>) query.list();  
                }
    
}
