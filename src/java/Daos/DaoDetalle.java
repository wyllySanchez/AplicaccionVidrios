/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;
import Interface.VentaDetalle;
import Pojos.Ventanadetalle;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author ander
 */
public class DaoDetalle implements VentaDetalle{

    @Override
    public boolean registar(Session session, Ventanadetalle ventanadetalle) throws Exception {
   session.save(ventanadetalle);
    return true;
    }

    @Override
    public boolean actualizar(Session session, Ventanadetalle ventanadetalle) throws Exception {
   session.update(ventanadetalle);
    return true;
    }

    @Override
    public boolean eliminar(Session session, Ventanadetalle ventanadetalle) throws Exception {
  session.delete(ventanadetalle);
    return true;
    }

    @Override
    public Ventanadetalle getById(Session session, int id) throws Exception {
    return (Ventanadetalle) session.get(Ventanadetalle.class, id); 
    }

    @Override
    public List<Ventanadetalle> getAll(Session session) throws Exception {
     String hql = "FROM Ventanadetalle";
        Query query = session.createQuery(hql);
        return (List<Ventanadetalle>) query.list(); 
    }
    
     @Override
    public List<Ventanadetalle> getAllByIdFactura(Session session, int idFactura) throws Exception {
       String hql = "FROM Ventanadetalle v inner join fetch v.facturas vf where vf.idfacturas=:idFactura ";
        Query query = session.createQuery(hql);
         query.setParameter("idFactura", idFactura);
        return (List<Ventanadetalle>) query.list(); 
    }
}
