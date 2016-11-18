/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.Producto;
import Pojos.Productos;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ander
 */
public class DaoProductos implements Producto {

    @Override
    public boolean registar(Session session, Productos productos) throws Exception {
        session.save(productos);
        return true;
    }

    @Override
    public boolean actualizar(Session session, Productos productos) throws Exception {
        session.update(productos);
        return true;
    }

    @Override
    public boolean eliminar(Session session, Productos productos) throws Exception {
        session.delete(productos);
        return true;
    }

    @Override
    public Productos getById(Session session, int id) throws Exception {
        return (Productos) session.get(Productos.class, id);
    }

    @Override
    public List<Productos> getAll(Session session) throws Exception {
        String hql = "FROM Productos";
        Query query = session.createQuery(hql);
        return (List<Productos>) query.list();
    }

}
