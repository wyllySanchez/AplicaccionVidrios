/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoAbonoDetalle;
import HibernateUtil.HibernateUtil;
import Pojos.Abonodetalle;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author William Sanchez
 */
@ManagedBean
@ViewScoped
public class MbAbonoDetalle {

    /**
     * Creates a new instance of MbAbonoDetalle
     */
    
      private Session session;
    private Transaction transaccion;
      private Abonodetalle abonoDetalle;
    private List<Abonodetalle> listaAbonodetalle;
    
    public MbAbonoDetalle() {
        this.abonoDetalle = new Abonodetalle();
    }
    
       public List<Abonodetalle> getAll() {
        this.session = null;
        this.transaccion = null;
        try {
      
            DaoAbonoDetalle daoAbonoDetalle = new DaoAbonoDetalle();
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaAbonodetalle = daoAbonoDetalle.getAll(this.session);
            this.transaccion.commit();
            return this.listaAbonodetalle;
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor intente mas tarde " + ex.getMessage()));

            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
}
