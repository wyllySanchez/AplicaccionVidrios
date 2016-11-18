/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Ventanadetalle;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author ander
 */
public interface VentaDetalle {
      public boolean registar(Session session,Ventanadetalle ventanadetalle)throws Exception;
    public boolean actualizar(Session session,Ventanadetalle ventanadetalle)throws Exception;
    public boolean eliminar(Session session,Ventanadetalle ventanadetalle)throws Exception;
    public Ventanadetalle getById(Session session,int id)throws Exception;
    public List<Ventanadetalle> getAll(Session session)throws Exception;  
        public List<Ventanadetalle> getAllByIdFactura(Session session,int idFactura)throws Exception;

}
