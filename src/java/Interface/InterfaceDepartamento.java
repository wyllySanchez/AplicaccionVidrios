/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Departamento;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public interface InterfaceDepartamento {
    
    public boolean registrar(Session session, Departamento departamento) throws Exception;

    public List<Departamento> getAll(Session session) throws Exception;
    

    public  Departamento getByDepartamento(Session session, int codigoDepartamento)throws Exception;

    public boolean update(Session session, Departamento departamento) throws Exception;

    public boolean delete(Session session, Departamento departamento) throws Exception;

    
    
}
