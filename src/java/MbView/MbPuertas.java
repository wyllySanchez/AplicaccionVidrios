/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoPuertas;
import HibernateUtil.HibernateUtil;
import java.util.List;
import Pojos.Puertas;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author William Sanchez
 */
@ManagedBean
@SessionScoped
public class MbPuertas {

    /**
     * Creates a new instance of MbPuertas
     */
    private Session session;
    private Transaction transaccion;
    private Puertas puertas;
    private List<Puertas> lista;
    private List<Puertas> listaFiltradoPuertas;
    private Puertas PuertasSele;
    private int idPuertas;

    public MbPuertas() {

        this.puertas = new Puertas();
        this.puertas.setIdpuertas(Integer.MIN_VALUE);
        this.puertas.setNombre("");
//        this.puertas.setPreciocot(0);
    }

    public void registrar() {
        this.session = null;
        this.transaccion = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();
            DaoPuertas daoPuertas = new DaoPuertas();

            if (this.puertas.getNombre().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return ;
            }

            daoPuertas.registar(this.session, this.puertas);
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Se ha registrado."));
            this.puertas = new Puertas();
            this.puertas.setIdpuertas(Integer.MIN_VALUE);
            this.puertas.setNombre("");
//        this.puertas.setPreciocot(0);

        } catch (Exception e) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor intente mas tarde" + e.getMessage()));

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }

     
    }

    public List<Puertas> getAll() {
        this.session = null;
        this.transaccion = null;
        try {

            DaoPuertas daoPuertas = new DaoPuertas();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.lista = daoPuertas.getAll(this.session);
            this.transaccion.commit();
            return this.lista;

        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }

        }

    }

    public void actualizarMaterial() {
        this.session = null;
        this.transaccion = null;
        try {

            DaoPuertas daoPuertas = new DaoPuertas();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            if (this.puertas.getNombre().equals("") || this.puertas.getNombre().trim() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres no puede llevar numeros ni caracteres especiales"));

                return;
            }

            daoPuertas.actualizar(this.session, this.puertas);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Los cambios fueron guardados correctamente"));
            this.transaccion.commit();

        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void cargarMaterialesPuertasEditar(int idPuertas) {
        this.session = null;
        this.transaccion = null;
        try {

            DaoPuertas daoPuertas = new DaoPuertas();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.puertas = daoPuertas.getById(this.session, idPuertas);
            RequestContext.getCurrentInstance().update("frmEditarMateriales:panelEditarMateriales");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarMateriales').show()");
            this.transaccion.commit();

        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }

    }

    public Puertas getBycodigo(int idVidrios) {
        this.session = null;
        this.transaccion = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            DaoPuertas daoPuertas = new DaoPuertas();
            this.PuertasSele = daoPuertas.getById(this.session, this.idPuertas);

            this.transaccion.commit();
            return this.PuertasSele;

        } catch (Exception e) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + e.getMessage()));

            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }

    }

    public Puertas getPuertas() {
        return puertas;
    }

    public void setPuertas(Puertas puertas) {
        this.puertas = puertas;
    }

    public List<Puertas> getLista() {
        return lista;
    }

    public void setLista(List<Puertas> lista) {
        this.lista = lista;
    }

   

    public Puertas getPuertasSele() {
        return PuertasSele;
    }

    public void setPuertasSele(Puertas PuertasSele) {
        this.PuertasSele = PuertasSele;
    }

    public int getIdPuertas() {
        return idPuertas;
    }

    public List<Puertas> getListaFiltradoPuertas() {
        return listaFiltradoPuertas;
    }

    public void setListaFiltradoPuertas(List<Puertas> listaFiltradoPuertas) {
        this.listaFiltradoPuertas = listaFiltradoPuertas;
    }

    public void setIdPuertas(int idPuertas) {
        this.idPuertas = idPuertas;
    }

}
