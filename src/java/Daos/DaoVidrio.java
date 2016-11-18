/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.Vidrio;
import Pojos.Vidrios;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ander
 */
public class DaoVidrio implements Vidrio {

    @Override
    public boolean registar(Session session, Vidrios vidrios) throws Exception {

        session.save(vidrios);
        return true;
    }

    @Override
    public boolean actualizar(Session session, Vidrios vidrios) throws Exception {
        session.update(vidrios);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Vidrios vidrios) throws Exception {
        session.delete(vidrios);
        return true;
    }

    @Override
    public Vidrios getById(Session session, int id) throws Exception {
        return (Vidrios) session.get(Vidrios.class, id);
    }

    @Override
    public List<Vidrios> getAll(Session session) throws Exception {
        String hql = "FROM Vidrios";
        Query query = session.createQuery(hql);
        return (List<Vidrios>) query.list();
    }

}
