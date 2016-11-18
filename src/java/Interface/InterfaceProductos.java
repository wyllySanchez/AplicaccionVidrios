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
 * @author KevinArnold
 */
public interface InterfaceProductos 
{
           public boolean registrar(Session session, Productos productos) throws Exception;

    public Productos getByIdProducto(Session session, Integer idproductos) throws Exception;
    public List<Productos> getAll(Session session) throws Exception;
    public Productos getByCodigoBarras(Session session, String codigoBarras) throws Exception;
        public boolean update(Session session, Productos productos) throws Exception;
        public Productos getByCodidoProducto(Session session, String codigoProducto);

}
