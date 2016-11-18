/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Productos;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author ander
 */
public interface Producto {
    public boolean registar(Session session,Productos productos)throws Exception;
    public boolean actualizar(Session session,Productos productos)throws Exception;
    public boolean eliminar(Session session,Productos productos)throws Exception;
    public Productos getById(Session session,int id)throws Exception;
    public List<Productos> getAll(Session session)throws Exception;  
}
