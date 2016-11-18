/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoVidrio;
import HibernateUtil.HibernateUtil;
import Pojos.Vidrios;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ander
 */
@ManagedBean
@ViewScoped
public class MbVidrios {

    private Session session;
    private Transaction transaccion;
    private Vidrios vidrios;
    private List<Vidrios> lista;
      private List<Vidrios> listaFiltradoVidrios;
    private Vidrios vidriosSele;
    private int idVidrios;

    /**
     * Creates a new instance of MbVidrios
     */
    public MbVidrios() {
        this.vidrios = new Vidrios();
        this.vidrios.setIdvidrios(Integer.MIN_VALUE);
        this.vidrios.setPreciocost(0);
        this.vidrios.setCalibre("");
        this.lista = new ArrayList<>();
    }

    public void registrar() {
        this.session = null;
        this.transaccion = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();
            DaoVidrio daoVidrio = new DaoVidrio();

            if (this.vidrios.getCalibre().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return ;
            }

            daoVidrio.registar(this.session, this.vidrios);
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Se ha registrado."));
            this.vidrios = new Vidrios();
            this.vidrios.setIdvidrios(Integer.MIN_VALUE);
            this.vidrios.setPreciocost(0);
            this.vidrios.setCalibre("");

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

    public List<Vidrios> getAll() {
        this.session = null;
        this.transaccion = null;
        try {
            DaoVidrio daoVidrio = new DaoVidrio();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.lista = daoVidrio.getAll(this.session);
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
    
     public void actualizar() {
        this.session = null;
        this.transaccion = null;
        try {

           
            DaoVidrio daoVidrio = new DaoVidrio();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            if (this.vidrios.getCalibre().equals("") || this.vidrios.getCalibre().trim() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres no puede llevar numeros ni caracteres especiales"));

                return;
            }
          
        
            daoVidrio.actualizar(this.session,this.vidrios);
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

    public void cargarVidriosEditar(int idVidrios) {
        this.session = null;
        this.transaccion = null;
        try {

            DaoVidrio daoVidrio = new DaoVidrio();
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

           
            this.vidrios = daoVidrio.getById(this.session, idVidrios);
            
            RequestContext.getCurrentInstance().update("frmEditarVidrios:panelEditarVidrios");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarVidrios').show()");
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

    public Vidrios getBycodigo(int idVidrios) {
        this.session = null;
        this.transaccion = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

         
            DaoVidrio daoVidrio = new DaoVidrio();
            this.vidriosSele = daoVidrio.getById(this.session,this.idVidrios);

            this.transaccion.commit();
            return this.vidriosSele;

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

    public List<Vidrios> getLista() {
        return lista;
    }

    public void setLista(List<Vidrios> lista) {
        this.lista = lista;
    }

    public Vidrios getVidrios() {
        return vidrios;
    }

    public void setVidrios(Vidrios vidrios) {
        this.vidrios = vidrios;
    }

    public int getIdVidrios() {
        return idVidrios;
    }

    public void setIdVidrios(int idVidrios) {
        this.idVidrios = idVidrios;
    }

    public Vidrios getVidriosSele() {
        return vidriosSele;
    }

    public void setVidriosSele(Vidrios vidriosSele) {
        this.vidriosSele = vidriosSele;
    }

    public List<Vidrios> getListaFiltradoVidrios() {
        return listaFiltradoVidrios;
    }

    public void setListaFiltradoVidrios(List<Vidrios> listaFiltradoVidrios) {
        this.listaFiltradoVidrios = listaFiltradoVidrios;
    }

}
