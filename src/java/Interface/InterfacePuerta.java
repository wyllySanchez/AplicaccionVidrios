/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Puertas;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public interface InterfacePuerta {
       public boolean registar(Session session,Puertas puertas)throws Exception;
    public boolean actualizar(Session session,Puertas puertas)throws Exception;
    public boolean eliminar(Session session,Puertas puertas)throws Exception;
    public Puertas getById(Session session,int id)throws Exception;
    public List<Puertas> getAll(Session session)throws Exception; 
}
