/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Facturas;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;


public interface Factura {
    public boolean registar(Session session,Facturas facturas)throws Exception;
    public boolean actualizar(Session session,Facturas facturas)throws Exception;
    public boolean eliminar(Session session,Facturas facturas)throws Exception;
    public Facturas getById(Session session,int id)throws Exception;
    public List<Facturas> getAll(Session session)throws Exception;   
    public Facturas getByUltimoRegistro(Session session)throws Exception;
    public List<Facturas> getAllFecha(Session session, Date fechaInicio, Date fechaFin) throws Exception;
        public List<Facturas> getAllByCliente(Session session,String numeroDocumento)throws Exception;


}
