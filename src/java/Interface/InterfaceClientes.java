/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;
import Pojos.Cliente;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public interface InterfaceClientes {
    public boolean registar(Session session,Cliente cliente)throws Exception;
    public boolean actualizar(Session session,Cliente cliente)throws Exception;
    public boolean eliminar(Session session,Cliente cliente)throws Exception;
    public Cliente getById(Session session,int id)throws Exception;
    public List<Cliente> getAll(Session session)throws Exception;
    public Cliente getByCorreoElectronico(Session session, String correoElectronico) throws Exception;
    public Cliente getByNumeroDocumento(Session session, String numerodocumento) throws Exception;

    
}
