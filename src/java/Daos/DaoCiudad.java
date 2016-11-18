/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.InterfaceCiudad;
import Pojos.Ciudad;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Sebastian
 */
public class DaoCiudad implements InterfaceCiudad {

    @Override
    public boolean registrar(Session session, Ciudad ciudad) throws Exception {
        session.save(ciudad);
        return true;
    }

    @Override
    public List<Ciudad> getAll(Session session) throws Exception {
        String hql = "from Ciudad";
        Query query = session.createQuery(hql);

        List<Ciudad> listaCiudad = (List<Ciudad>) query.list();

        return listaCiudad;
    }

    @Override
    public Ciudad getByCodigoCiudad(Session session, int codigoCiudad) throws Exception {
        return (Ciudad) session.get(Ciudad.class, codigoCiudad);
    }

    @Override
    public boolean update(Session session, Ciudad ciudad) throws Exception {
session.update(ciudad);  
    return true;
    }

    @Override
    public boolean delete(Session session, Ciudad tipo) throws Exception {
session.delete(tipo);
    return true;
    }

    @Override
    public Ciudad getById(Session session, int id) throws Exception {
  return (Ciudad) session.get(Ciudad.class, id);    }

}
