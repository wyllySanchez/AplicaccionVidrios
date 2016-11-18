/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Clases.ValidacionTexto;
import Daos.DaoCiudad;
import Daos.DaoDepartamento;
import HibernateUtil.HibernateUtil;
import Pojos.Ciudad;
import Pojos.Departamento;
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
 * @author William Sanchez
 */
@ManagedBean
@ViewScoped
public class BeanCiudad {

    /**
     * Creates a new instance of BeanCiudad
     */
    private Session session;
    private Transaction transaccion;

    private Ciudad ciudad;
    private Departamento departamento;
    private List<Ciudad> listaCiudad;
    private List<Ciudad> ListaCiudadFiltrado;
    private int codigoDepto;
    private int codigoCiudad;

    public BeanCiudad() {
        this.ciudad = new Ciudad();
        this.ciudad.setIdciudad(Integer.MIN_VALUE);
       
    }

    public String registrar() {

        this.session = null;
        this.transaccion = null;

        try {

            if (this.departamento.getNombreDept().equals("") || this.departamento.getNombreDept().length() < 3) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return "/administrador/ciudad/registrarCiudad";
            }

            ValidacionTexto valida = new ValidacionTexto();

            if (!valida.validar(this.ciudad.getNombreCiu())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres no puede llevar numeros ni caracteres especiales"));

                return "/administrador/ciudad/registrarCiudad";
            }

            DaoCiudad daoCiudad = new DaoCiudad();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            if (daoCiudad.getByCodigoCiudad(this.session, this.codigoCiudad) != null) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El rol ya se encuentra registrado en el sistema"));
              return "administrador/ciudad/registrarCiudad";

            }

            DaoDepartamento daoDepartamento = new DaoDepartamento();

            this.ciudad.setDepartamento(daoDepartamento.getByDepartamento(this.session, this.codigoDepto));

            daoCiudad.registrar(this.session, this.ciudad);

            this.transaccion.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro fue realizado correctamente"));

            this.ciudad = new Ciudad();
            this.ciudad.setIdciudad(Integer.MIN_VALUE);
         

        } catch (Exception e) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + e.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return "/administrador/ciudad/registrarCiudad";
    }

    public void update() {
        this.session = null;
        this.transaccion = null;

        try {

            DaoCiudad daoCiudad = new DaoCiudad();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            if (this.ciudad.getNombreCiu().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es obligatorio"));
                return;
            }

            daoCiudad.update(this.session, this.ciudad);

            this.transaccion.commit();

//            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//            httpSession.setAttribute("nombreCiudad", this.ciudad.getNombreCiu());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Los cambios fueron guardados correctamente"));
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
    
     public void cargarCiudadEditar(int codigoCiudad) {
        this.session = null;
        this.transaccion = null;

        try {
          
            DaoCiudad daoCiudad = new DaoCiudad();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

           
            this.ciudad = daoCiudad.getByCodigoCiudad(this.session, codigoCiudad);

            RequestContext.getCurrentInstance().update("frmEditarCiudad:panelEditarCiudad");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarCiudad').show()");

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
     
     public List<Ciudad> getAll() {
        this.session = null;
        this.transaccion = null;
        try {
        
            DaoCiudad daoCiudad = new DaoCiudad();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            
            this.listaCiudad = daoCiudad.getAll(this.session);

          
            this.transaccion.commit();
            return this.listaCiudad;
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
   public Ciudad getById(int id) {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoCiudad daoCiudad = new DaoCiudad();

            Ciudad ciud = daoCiudad.getById(this.session, id);
            this.transaccion.commit();
            return ciud;

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


    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    public List<Ciudad> getListaCiudadFiltrado() {
        return ListaCiudadFiltrado;
    }

    public void setListaCiudadFiltrado(List<Ciudad> ListaCiudadFiltrado) {
        this.ListaCiudadFiltrado = ListaCiudadFiltrado;
    }

    public int getCodigoDepto() {
        return codigoDepto;
    }

    public void setCodigoDepto(int codigoDepto) {
        this.codigoDepto = codigoDepto;
    }

}
