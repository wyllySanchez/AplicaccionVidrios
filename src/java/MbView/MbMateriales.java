/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoMaterial;
import HibernateUtil.HibernateUtil;
import Pojos.Materiales;
import java.util.List;
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
public class MbMateriales {

    /**
     * Creates a new instance of MbMateriales
     */
     private Session session;
    private Transaction transaccion;
    private Materiales materiales;
    private List<Materiales> lista;
     private List<Materiales> listaFiltradoMateriales;
    private Materiales MaterialesSele;
    private int idMateriales;
    
    public MbMateriales() {
          this.materiales = new Materiales();
        this.materiales.setIdmateriales(Integer.MIN_VALUE);
        this.materiales.setNombre("");
        this.materiales.setPreciocost(0);
    }
   
    public String registrar() {
        this.session = null;
        this.transaccion = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();
            DaoMaterial daoMaterial = new DaoMaterial();

            if (this.materiales.getNombre().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return "administrador/materiales/registrarMateriales";
            }

            daoMaterial.registar(this.session, this.materiales);
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Se ha registrado."));
            this.materiales = new Materiales();
            this.materiales.setIdmateriales(Integer.MIN_VALUE);
            this.materiales.setNombre("");
            this.materiales.setPreciocost(0);

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

        return "administrador/materiales/listaMateriales";
    }

    public List<Materiales> getAll() {
        this.session = null;
        this.transaccion = null;
        try {
         
            DaoMaterial daoMaterial  = new DaoMaterial();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

          
            this.lista = daoMaterial.getAll(this.session);
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

        
            DaoMaterial daoMaterial = new DaoMaterial();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            if (this.materiales.getNombre().equals("") || this.materiales.getNombre().trim() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres no puede llevar numeros ni caracteres especiales"));

                return;
            }

          
            daoMaterial.actualizar(this.session,this.materiales);
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

    public void cargarMaterialesEditar(int idMateriales) {
        this.session = null;
        this.transaccion = null;
        try {

            DaoMaterial daoMaterial = new DaoMaterial();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.materiales = daoMaterial.getById(this.session, idMateriales);
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

    public Materiales getBycodigo(int idVidrios) {
        this.session = null;
        this.transaccion = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            DaoMaterial daoMaterial = new DaoMaterial();
            this.MaterialesSele = daoMaterial.getById(this.session,this.idMateriales);
          
            this.transaccion.commit();
            return this.MaterialesSele;

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

    public Materiales getMateriales() {
        return materiales;
    }

    public void setMateriales(Materiales materiales) {
        this.materiales = materiales;
    }

    public List<Materiales> getLista() {
        return lista;
    }

    public void setLista(List<Materiales> lista) {
        this.lista = lista;
    }

    public Materiales getMaterialesSele() {
        return MaterialesSele;
    }

    public void setMaterialesSele(Materiales MaterialesSele) {
        this.MaterialesSele = MaterialesSele;
    }

    public int getIdMateriales() {
        return idMateriales;
    }

    public void setIdMateriales(int idMateriales) {
        this.idMateriales = idMateriales;
    }

    public List<Materiales> getListaFiltradoMateriales() {
        return listaFiltradoMateriales;
    }

    public void setListaFiltradoMateriales(List<Materiales> listaFiltradoMateriales) {
        this.listaFiltradoMateriales = listaFiltradoMateriales;
    }
 
}
