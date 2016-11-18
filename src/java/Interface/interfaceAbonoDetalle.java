/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Abonodetalle;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public interface interfaceAbonoDetalle {
    public boolean registar(Session session,Abonodetalle abonoDetalle)throws Exception;
    public boolean actualizar(Session session,Abonodetalle abonoDetalle)throws Exception;
    public boolean eliminar(Session session,Abonodetalle abonoDetalle)throws Exception;
    public Abonodetalle getById(Session session,int id)throws Exception;
    public List<Abonodetalle> getAll(Session session)throws Exception;  
    public List<Abonodetalle> getAllByIdFactura(Session session,int idFactura)throws Exception;
  
}
