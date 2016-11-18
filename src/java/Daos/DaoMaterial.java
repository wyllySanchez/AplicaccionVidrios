/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.Material;
import Pojos.Materiales;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ander
 */
public class DaoMaterial implements Material{

    @Override
    public boolean registar(Session session, Materiales materiales) throws Exception {
    session.save(materiales);
    return true;
    }

    @Override
    public boolean actualizar(Session session, Materiales materiales) throws Exception {
   session.update(materiales);
    return true;
    }

    @Override
    public boolean eliminar(Session session, Materiales materiales) throws Exception {
    session.delete(materiales);
    return true;
    }

    @Override
    public Materiales getById(Session session, int id) throws Exception {
    return (Materiales) session.get(Materiales.class,id);
    }

    @Override
    public List<Materiales> getAll(Session session) throws Exception {
    String hql="FROM Materiales";
   Query query=session.createQuery(hql);
    return (List<Materiales>)query.list();
    }
    
}
