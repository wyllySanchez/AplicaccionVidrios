/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Interface.InterfaceClientes;
import Pojos.Cliente;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author ander
 */
public class DaoCliente implements InterfaceClientes{

    @Override
    public boolean registar(Session session, Cliente cliente) throws Exception {
  session.save(cliente);
    return true;
    }

    @Override
    public boolean actualizar(Session session, Cliente cliente) throws Exception {
    session.update(cliente); 
    return true;
    }

    @Override
    public boolean eliminar(Session session, Cliente cliente) throws Exception {
   session.delete(cliente);
    return true;
    }

    @Override
    public Cliente getById(Session session, int id) throws Exception {
       return (Cliente) session.get(Cliente.class, id);
     }

    @Override
    public List<Cliente> getAll(Session session) throws Exception {
        String hql = "FROM Cliente";
        Query query = session.createQuery(hql);
        return (List<Cliente>) query.list();
    }

    @Override
    public Cliente getByCorreoElectronico(Session session, String correoElectronico) throws Exception {
 String hql = "FROM Cliente WHERE correoElectronico=:correoElectronico";
        Query query = session.createQuery(hql);
        query.setParameter("correoElectronico", correoElectronico);
        return (Cliente) query.uniqueResult();
    }

    @Override
    public Cliente getByNumeroDocumento(Session session, String numerodocumento) throws Exception {
 String hql = "FROM Cliente WHERE numeroDocumentoC=:numeroDocumentoC";
        Query query = session.createQuery(hql);
        query.setParameter("numeroDocumentoC", numerodocumento);
        return (Cliente) query.uniqueResult();     }

 
    
}
 