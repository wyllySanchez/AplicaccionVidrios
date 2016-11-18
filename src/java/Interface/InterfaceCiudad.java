/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Ciudad;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Sebastian
 */
public interface InterfaceCiudad {
    
        public boolean registrar(Session session, Ciudad ciudad)throws Exception;
    public List<Ciudad> getAll(Session session)throws Exception;
    public Ciudad getByCodigoCiudad(Session session, int codigoUsuario)throws Exception;
    public boolean update(Session session, Ciudad ciudad)throws Exception;
    public boolean delete(Session session,Ciudad tipo) throws Exception;
        public Ciudad getById(Session session,int id)throws Exception;
}
