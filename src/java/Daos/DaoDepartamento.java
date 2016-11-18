/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.InterfaceDepartamento;
import Pojos.Departamento;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public class DaoDepartamento implements InterfaceDepartamento {

    @Override
    public boolean registrar(Session session, Departamento departamento) throws Exception {
        session.save(departamento);
        return true;
    }

    @Override
    public List<Departamento> getAll(Session session) throws Exception {
        String hql = "from Departamento";
        Query query = session.createQuery(hql);

        List<Departamento> listaDepartamento = (List<Departamento>) query.list();

        return listaDepartamento;
    }

    

    @Override
    public Departamento getByDepartamento(Session session, int codigoDepartamento) throws Exception {
        return (Departamento) session.get(Departamento.class, codigoDepartamento);
    }

    @Override
    public boolean update(Session session, Departamento departamento) throws Exception {
        session.update(departamento);
        return true;
    }

    @Override
    public boolean delete(Session session, Departamento departamento) throws Exception {
        session.delete(departamento);
        return true;
    }

  

}
