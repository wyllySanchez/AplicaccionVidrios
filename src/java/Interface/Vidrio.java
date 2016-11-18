/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Vidrios;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author ander
 */
public interface Vidrio {
     public boolean registar(Session session,Vidrios vidrios)throws Exception;
    public boolean actualizar(Session session,Vidrios vidrios)throws Exception;
    public boolean eliminar(Session session,Vidrios vidrios)throws Exception;
    public Vidrios getById(Session session,int id)throws Exception;
    public List<Vidrios> getAll(Session session)throws Exception;  
}
