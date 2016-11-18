/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Clases.ValidacionTexto;
import Daos.DaoDepartamento;
import HibernateUtil.HibernateUtil;
import Pojos.Departamento;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author William Sanchez
 */
@ManagedBean
@SessionScoped
public class BeanDepartamento {

    /**
     * Creates a new instance of BeanDepartamento
     */
    private Session session;
    private Transaction transacion;

    private Departamento departamento;
    private List<Departamento> listaDepartamento;
    private List<Departamento> listaDepartamentoFiltrado;
    private int codigoDepartamento;

    public BeanDepartamento() {
        this.departamento = new Departamento();
        this.departamento.setIddepartamento(Integer.MIN_VALUE);

    }

    public String registrar() {
        this.session = null;
        this.transacion = null;

        try {

            if (this.departamento.getNombreDept().equals("") || this.departamento.getNombreDept().length() < 2) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return "/administrador/departamento/registrarDepartamento";
            }

            ValidacionTexto valida = new ValidacionTexto();

            if (!valida.validar(this.departamento.getNombreDept())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres no puede llevar numeros ni caracteres especiales"));

                return "/administrador/departamento/registrarDepartamento";
            }

            DaoDepartamento daoDepartamento = new DaoDepartamento();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transacion = session.beginTransaction();
            
                 if(daoDepartamento.getByDepartamento(this.session,this.codigoDepartamento) != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El departamento ya se encuentra registrado en el sistema"));

                return "/administrador/departamento/registrarDepartamento";
            }

            daoDepartamento.registrar(this.session, this.departamento);

            this.transacion.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro fue realizado correctamente"));

            this.departamento = new Departamento();
            this.departamento.setIddepartamento(Integer.MIN_VALUE);

            return "/administrador/inicio";

        } catch (Exception ex) {
            if (this.transacion != null) {
                this.transacion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return "/administrador/departamento/registrarDepartamento";
    }

    public void update() {

        this.session = null;
        this.transacion = null;

        try {
            DaoDepartamento daoDepartamento = new DaoDepartamento();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transacion = session.beginTransaction();

            if (this.departamento.getNombreDept().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es obligatorio"));
                return;

            }

            daoDepartamento.update(this.session, this.departamento);

            this.transacion.commit();

            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.setAttribute("NombreDept", this.departamento.getNombreDept());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Los cambios fueron guardados correctamente"));

        } catch (Exception e) {
            if (this.transacion != null) {
                this.transacion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + e.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void cargarDepartamentoEditar(int codigoDepartamento) {
        this.session = null;
        this.transacion = null;

        try {

            DaoDepartamento daoDepartamento = new DaoDepartamento();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transacion = session.beginTransaction();

            this.departamento = daoDepartamento.getByDepartamento(this.session, codigoDepartamento);

            RequestContext.getCurrentInstance().update("frmEditarDepartamento:panelEditarDepartamento");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarDepartamento').show()");

            this.transacion.commit();

        } catch (Exception e) {
            if (this.transacion != null) {
                this.transacion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + e.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }

    }

    ;
        
        public List<Departamento> getALL() {
        this.session = null;
        this.transacion = null;

        try {
            DaoDepartamento daoDepartamento = new DaoDepartamento();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transacion = session.beginTransaction();

            this.listaDepartamento = daoDepartamento.getAll(this.session);

            this.transacion.commit();
            return this.listaDepartamento;

        } catch (Exception e) {
            if (this.transacion != null) {
                this.transacion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor intente mas tarde " + e.getMessage()));
            return null;
            
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }



public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Departamento> getListaDepartamentoFiltrado() {
        return listaDepartamentoFiltrado;
    }

    public void setListaDepartamentoFiltrado(List<Departamento> listaDepartamentoFiltrado) {
        this.listaDepartamentoFiltrado = listaDepartamentoFiltrado;
    }

    public int getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(int codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

}
