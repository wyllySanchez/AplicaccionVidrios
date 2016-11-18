/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Cotizacion;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public interface InterfaceCotizacion {
   
     public boolean registar(Session session,Cotizacion cotizacion)throws Exception;
    public boolean actualizar(Session session,Cotizacion cotizacion)throws Exception;
    public boolean eliminar(Session session,Cotizacion cotizacion)throws Exception;
    public Cotizacion getById(Session session,int id)throws Exception;
    public List<Cotizacion> getAll(Session session)throws Exception;   
    public Cotizacion getByUltimoRegistro(Session session)throws Exception;
     public List<Cotizacion> getAllByCliente(Session session,String numeroDocumento)throws Exception;


    
    
}
