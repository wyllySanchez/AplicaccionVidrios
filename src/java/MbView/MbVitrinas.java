/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoVitrinas;
import HibernateUtil.HibernateUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import Pojos.Vitrinas;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author William Sanchez
 */
@ManagedBean
@ViewScoped
public class MbVitrinas {

    /**
     * Creates a new instance of MbVitrinas
     */
     private Session session;
    private Transaction transaccion;
    private Vitrinas vitrinas;
    private List<Vitrinas> lista;
    private List<Vitrinas> listaFiltradoVitrinas;
    private Vitrinas VitrinasSele;
    private int idVitrinas;
    
    public MbVitrinas() {
           this.vitrinas = new  Vitrinas();
        this.vitrinas.setIdvitrinas(Integer.MIN_VALUE);
        this.vitrinas.setNombre("");
//        this.vitrinas.setPreciocot(0);
    }
    

    public void registrar() {
        this.session = null;
        this.transaccion = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();
            DaoVitrinas daoVitrinas= new DaoVitrinas();

            if (this.vitrinas.getNombre().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return ;
            }

            daoVitrinas.registar(this.session, this.vitrinas);
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Se ha registrado."));
            this.vitrinas = new Vitrinas();
            this.vitrinas.setIdvitrinas(Integer.MIN_VALUE);
            this.vitrinas.setNombre("");
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

    public List<Vitrinas> getAll() {
        this.session = null;
        this.transaccion = null;
        try {

            DaoVitrinas daoVitrinas= new DaoVitrinas();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.lista = daoVitrinas.getAll(this.session);
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

    public void actualizarMaterialVitrina() {
        this.session = null;
        this.transaccion = null;
        try {

            DaoVitrinas daoVitrinas= new DaoVitrinas();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            if (this.vitrinas.getNombre().equals("") || this.vitrinas.getNombre().trim() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres no puede llevar numeros ni caracteres especiales"));

                return;
            }

            daoVitrinas.actualizar(this.session, this.vitrinas);
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

    public void cargarMaterialesVitrinasEditar(int idVitrinas) {
        this.session = null;
        this.transaccion = null;
        try {

            DaoVitrinas daoVitrinas= new DaoVitrinas();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.vitrinas = daoVitrinas.getById(this.session, idVitrinas);
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

    public Vitrinas getBycodigo(int idVidrios) {
        this.session = null;
        this.transaccion = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            DaoVitrinas daoVitrinas= new DaoVitrinas();
            this.VitrinasSele = daoVitrinas.getById(this.session, this.idVitrinas);

            this.transaccion.commit();
            return this.VitrinasSele;

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


    public Vitrinas getVitrinas() {
        return vitrinas;
    }

    public void setVitrinas(Vitrinas vitrinas) {
        this.vitrinas = vitrinas;
    }

    public List<Vitrinas> getLista() {
        return lista;
    }

    public void setLista(List<Vitrinas> lista) {
        this.lista = lista;
    }

    public List<Vitrinas> getListaFiltradoVitrinas() {
        return listaFiltradoVitrinas;
    }

    public void setListaFiltradoVitrinas(List<Vitrinas> listaFiltradoVitrinas) {
        this.listaFiltradoVitrinas = listaFiltradoVitrinas;
    }

   
    public Vitrinas getVitrinasSele() {
        return VitrinasSele;
    }

    public void setVitrinasSele(Vitrinas VitrinasSele) {
        this.VitrinasSele = VitrinasSele;
    }

    public int getIdVitrinas() {
        return idVitrinas;
    }

    public void setIdVitrinas(int idVitrinas) {
        this.idVitrinas = idVitrinas;
    }
    
}
