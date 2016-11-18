/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoDetalle;
import HibernateUtil.HibernateUtil;
import Pojos.Ventanadetalle;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author William Sanchez
 */
@ManagedBean
@SessionScoped
public class BeanVentanaDetalle {

    /**
     * Creates a new instance of BeanVentanaDetalle
     */
    private Session session;
    private Transaction transaccion;
    private Ventanadetalle ventanaDetalle;
    private List<Ventanadetalle> listaVentanadetalle;
      private List<Ventanadetalle> listaVentanadetalleFitrada;
    private List<Ventanadetalle> listaVentanadetalleSele;
    private Factura factura;

    public BeanVentanaDetalle() {
        this.ventanaDetalle = new Ventanadetalle();
        this.factura = new Factura();
        this.listaVentanadetalle = new ArrayList<>();
    }

    public List<Ventanadetalle> getAll() {
        this.session = null;
        this.transaccion = null;
        try {

            DaoDetalle daoDetalle = new DaoDetalle();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaVentanadetalleSele = daoDetalle.getAll(this.session);
            this.transaccion.commit();

            return this.listaVentanadetalleSele;

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
        this.listaVentanadetalleSele = new ArrayList<>();
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoDetalle daoDetalle = new DaoDetalle();
            this.listaVentanadetalleSele.clear();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "el id es" + id));
            System.out.println("el id es" + id);
            this.listaVentanadetalleSele.addAll(daoDetalle.getAllByIdFactura(this.session, id));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            this.listaVentanadetalleSele = null;
            this.factura = null;
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }

    public Ventanadetalle getVentanaDetalle() {
        return ventanaDetalle;
    }

    public void setVentanaDetalle(Ventanadetalle ventanaDetalle) {
        this.ventanaDetalle = ventanaDetalle;
    }

    public List<Ventanadetalle> getListaVentanadetalle() {
        return listaVentanadetalle;
    }

    public void setListaVentanadetalle(List<Ventanadetalle> listaVentanadetalle) {
        this.listaVentanadetalle = listaVentanadetalle;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Ventanadetalle> getListaVentanadetalleSele() {
        return listaVentanadetalleSele;
    }

    public void setListaVentanadetalleSele(List<Ventanadetalle> listaVentanadetalleSele) {
        this.listaVentanadetalleSele = listaVentanadetalleSele;
    }

    public List<Ventanadetalle> getListaVentanadetalleFitrada() {
        return listaVentanadetalleFitrada;
    }

    public void setListaVentanadetalleFitrada(List<Ventanadetalle> listaVentanadetalleFitrada) {
        this.listaVentanadetalleFitrada = listaVentanadetalleFitrada;
    }

}
