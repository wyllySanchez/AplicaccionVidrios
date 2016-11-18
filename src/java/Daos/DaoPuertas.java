/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.InterfacePuerta;
import Pojos.Puertas;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public class DaoPuertas implements InterfacePuerta{

    @Override
    public boolean registar(Session session, Puertas puertas) throws Exception {
session.save(puertas);
    return true;
    }

    @Override
    public boolean actualizar(Session session, Puertas puertas) throws Exception {
session.update(puertas);
    return true;
    }

    @Override
    public boolean eliminar(Session session, Puertas puertas) throws Exception {
session.delete(puertas);
    return true;
    }

    @Override
    public Puertas getById(Session session, int id) throws Exception {
    return (Puertas) session.get(Puertas.class,id);
    }

    @Override
    public List<Puertas> getAll(Session session) throws Exception {
  String hql="FROM Puertas";
   Query query=session.createQuery(hql);
    return (List<Puertas>)query.list(); 
    }
    
}
