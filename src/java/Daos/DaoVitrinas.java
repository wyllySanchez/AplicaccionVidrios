/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.InterfaceVitrina;
import Pojos.Vitrinas;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public class DaoVitrinas implements InterfaceVitrina{

    @Override
    public boolean registar(Session session, Vitrinas vitrinas) throws Exception {
session.save(vitrinas);
    return true;
    }

    @Override
    public boolean actualizar(Session session, Vitrinas vitrinas) throws Exception {
session.update(vitrinas);
    return true;
    }

    @Override
    public boolean eliminar(Session session, Vitrinas vitrinas) throws Exception {
session.delete(vitrinas);
    return true;
    }

    @Override
    public Vitrinas getById(Session session, int id) throws Exception {
return (Vitrinas)session.get(Vitrinas.class, id);
    }

    @Override
    public List<Vitrinas> getAll(Session session) throws Exception {
 String hql="FROM Vitrinas";
   Query query=session.createQuery(hql);
    return (List<Vitrinas>)query.list(); 
    }
    
}
