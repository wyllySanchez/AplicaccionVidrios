/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoCotizacionDetalle;
import HibernateUtil.HibernateUtil;
import Pojos.Cotizacion;
import Pojos.Cotizaciondetalle;
import java.util.ArrayList;
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
public class MbCotizacionDetalle {

    /**
     * Creates a new instance of MbCotizacionDetalle
     */
    
      private Session session;
    private Transaction transaccion;
      private Cotizaciondetalle cotizacionDetalle;
    private List<Cotizaciondetalle> listaCotizaciondetalle;
      private List<Cotizaciondetalle> listaCotizaciondetalleFiltrado;
      private Cotizacion cotizacion;
    
    public MbCotizacionDetalle() {
        this.cotizacionDetalle = new Cotizaciondetalle();
        this.listaCotizaciondetalle= new ArrayList<>();
    }
    
       public List<Cotizaciondetalle> getAll() {
        this.session = null;
        this.transaccion = null;
        try {
      
       
            DaoCotizacionDetalle daoCotizacionDetalle = new DaoCotizacionDetalle();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaCotizaciondetalle = daoCotizacionDetalle.getAll(this.session);
            this.transaccion.commit();
            return this.listaCotizaciondetalle;
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

        public void BuscarBYcodigoFactura(int id) {
        this.listaCotizaciondetalle = new ArrayList<>();
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoCotizacionDetalle daoCotizacionDetalle= new DaoCotizacionDetalle();
      this.listaCotizaciondetalle.clear();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "el id es" + id));
            System.out.println("el id es"+ id);
            this.listaCotizaciondetalle.addAll(daoCotizacionDetalle.getAllByIdCotizacion(this.session, id));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            this.listaCotizaciondetalle = null;
            this.cotizacion= null;
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }

       
       
    public Cotizaciondetalle getCotizacionDetalle() {
        return cotizacionDetalle;
    }

    public void setCotizacionDetalle(Cotizaciondetalle cotizacionDetalle) {
        this.cotizacionDetalle = cotizacionDetalle;
    }

    public List<Cotizaciondetalle> getListaCotizaciondetalle() {
        return listaCotizaciondetalle;
    }

    public void setListaCotizaciondetalle(List<Cotizaciondetalle> listaCotizaciondetalle) {
        this.listaCotizaciondetalle = listaCotizaciondetalle;
    }

    public List<Cotizaciondetalle> getListaCotizaciondetalleFiltrado() {
        return listaCotizaciondetalleFiltrado;
    }

    public void setListaCotizaciondetalleFiltrado(List<Cotizaciondetalle> listaCotizaciondetalleFiltrado) {
        this.listaCotizaciondetalleFiltrado = listaCotizaciondetalleFiltrado;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }
       
}
