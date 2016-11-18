/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;
import Pojos.Cotizaciondetalle;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public interface CotizacionDetalle {
    public boolean registar(Session session,Cotizaciondetalle cotizacionDetalle)throws Exception;
    public boolean actualizar(Session session,Cotizaciondetalle cotizacionDetalle)throws Exception;
    public boolean eliminar(Session session,Cotizaciondetalle cotizacionDetalle)throws Exception;
    public Cotizaciondetalle getById(Session session,int id)throws Exception;
    public List<Cotizaciondetalle> getAll(Session session)throws Exception;  
    public List<Cotizaciondetalle> getAllByIdCotizacion(Session session,int idCotizacion)throws Exception;
}
