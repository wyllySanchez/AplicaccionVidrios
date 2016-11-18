/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Vitrinas;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author William Sanchez
 */
public interface InterfaceVitrina {
        public boolean registar(Session session,Vitrinas vitrinas)throws Exception;
    public boolean actualizar(Session session,Vitrinas vitrinas)throws Exception;
    public boolean eliminar(Session session,Vitrinas vitrinas)throws Exception;
    public Vitrinas getById(Session session,int id)throws Exception;
    public List<Vitrinas> getAll(Session session)throws Exception; 
}

