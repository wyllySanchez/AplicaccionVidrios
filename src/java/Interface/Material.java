/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import Pojos.Materiales;
import java.util.List;
import org.hibernate.Session;
/**
 *
 * @author ander
 */
public interface Material {
     public boolean registar(Session session,Materiales materiales)throws Exception;
    public boolean actualizar(Session session,Materiales materiales)throws Exception;
    public boolean eliminar(Session session,Materiales materiales)throws Exception;
    public Materiales getById(Session session,int id)throws Exception;
    public List<Materiales> getAll(Session session)throws Exception;    
}
