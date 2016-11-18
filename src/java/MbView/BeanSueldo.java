/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoEmpleado;
import Daos.DaoSueldo;
import HibernateUtil.HibernateUtil;
import Pojos.Empleado;
import Pojos.Sueldo;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class BeanSueldo {

    /**
     * Creates a new instance of BeanSueldo
     */
    private Session session;
    private Transaction transaccion;

    private Sueldo sueldo;
    private Empleado empleado;
    private List<Sueldo> listaSueldo;
    private List<Empleado> listaEmpleado;
    private List<Sueldo> listaSueldoByFecha;
    private int codigoEmpleado;
    private int codigoSueldo;
    private List<Sueldo> listaSueldoFiltrado;
    private BigDecimal sueldoTotal;
    private Date fechaFin;
    private Date fechaInicio;
    private BigDecimal totalVentasFecha;
      private String numeroDocumento;
      private int idSueldo;
      private Sueldo sueldoSelect;

    public BeanSueldo() {
        this.sueldo = new Sueldo();
        this.sueldo.setIdsueldo(Integer.MIN_VALUE);
        this.codigoEmpleado = 0;
        this.sueldoTotal = new BigDecimal(0);
        this.sueldo.getHorasExtras();
        this.sueldo.getComision();
        this.sueldo.getSueldoBase();

    }

    public void registrar() {
        this.session = null;
        this.transaccion = null;

        try {

            if (String.valueOf(this.sueldo.getSueldoBase()).equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Por favor digite el sueldo base."));
                return;
            }
            if (String.valueOf(this.sueldo.getComision()).equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Por favor digite la comision."));
                return;
            }
            if (String.valueOf(this.sueldo.getHorasExtras()).equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Por favor digite las horas extras."));
                return;
            }
            if (String.valueOf(this.sueldo.getDescuentos()).equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Por favor digite el valor de los descuentos."));
                return;
            }
//            try {
//
//                int m = this.sueldo.getHorasExtras();
//
//            } catch (NumberFormatException nfe) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digito texto en un campo numerico."));
//            }

            DaoSueldo daosueldo = new DaoSueldo();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();
            DaoEmpleado daoEmpleado = new DaoEmpleado();

            this.sueldo.setEmpleado(daoEmpleado.getById(this.session, this.codigoEmpleado));

//            int suma = this.sueldo.getHorasextras() * 4000;
//            this.sueldo.setSueldototal(BigDecimal.valueOf(suma).add(this.sueldo.getSueldobase().add(this.sueldo.getComision()).subtract(this.sueldo.getDecuentos())));
            daosueldo.registrar(this.session, this.sueldo);

            this.transaccion.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro fue realizado correctamente"));

            this.sueldo = new Sueldo();
            this.sueldo.setIdsueldo(Integer.MIN_VALUE);
            this.codigoEmpleado = 0;
            this.sueldoTotal = new BigDecimal(0);
            this.sueldo.getHorasExtras();
            this.sueldo.getComision();
            this.sueldo.getSueldoBase();

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

    public List<Sueldo> getAll() {
        this.session = null;
        this.transaccion = null;
        try {
            DaoSueldo daoSueldo = new DaoSueldo();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaSueldo = daoSueldo.getAll(this.session);
            this.transaccion.commit();
            return this.listaSueldo;
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

    public List<Sueldo> getListSueldoByFecha() {
        if (listaSueldoByFecha == null) {
            listaSueldoByFecha = new ArrayList<>();
        }
        return listaSueldoByFecha;
    }

    public void consultarSueldo() {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            DaoSueldo daoSueldo = new DaoSueldo();
            this.listaSueldoByFecha = daoSueldo.getAllFecha(this.session, this.fechaInicio, this.fechaFin);

            totalVentasFecha = new BigDecimal(0);
            for (Sueldo sueldos : listaSueldoByFecha) {
                totalVentasFecha = totalVentasFecha.add(sueldos.getSueldoTotal());
            }
            this.transaccion.commit();
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor intente mas tarde " + ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void update() {
        this.session = null;
        this.transaccion = null;

        try {
            DaoSueldo daoSueldo = new DaoSueldo();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            if (this.sueldo.getSueldoBase().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es obligatorio"));
                return;
            }

            daoSueldo.update(this.session, this.sueldo);

            this.transaccion.commit();

            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.setAttribute("idsueldo", this.sueldo.getIdsueldo());

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

    public void cargarSueldoEditar(int codigoSueldo) {
        this.session = null;
        this.transaccion = null;

        try {
            DaoSueldo daoSueldo = new DaoSueldo();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            this.sueldo = daoSueldo.getByCodigoSueldo(this.session, codigoSueldo);

            RequestContext.getCurrentInstance().update("frmEditarSueldo:panelEditarSueldo");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarSueldo').show()");

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

    public void calcularSalario() {
        try {

            int suma = this.sueldo.getHorasExtras() * 4000;
//                this.salario.setSalarioTotal(BigDecimal.valueOf(suma).add(this.salario.getSueldobasico()).subtract(this.salario.getDescuentos()));
            this.sueldo.setSueldoTotal(BigDecimal.valueOf(suma).add(this.sueldo.getSueldoBase().add(this.sueldo.getComision())).subtract(this.sueldo.getDescuentos()));

            RequestContext.getCurrentInstance().update("frmCalcularSueldo:panelFinalSueldo");

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    
     public void selectSueldo(int id) {
        this.idSueldo = id;
    }
    
    
    public void searchByDocumento() {
        this.listaSueldo = new ArrayList<>();
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            DaoSueldo daoSueldo = new DaoSueldo();
            this.empleado = daoEmpleado.getByNumeroDocumento(this.session,this.numeroDocumento);
            this.listaSueldo.addAll(daoSueldo.getAllByEmpleado(this.session, this.numeroDocumento));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            this.listaSueldo = null;
            this.empleado = null;
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }
    
    
    
    public void BuscarBYcodigoSueldo(int id) {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoSueldo daoSueldo = new DaoSueldo();
     
          this.sueldoSelect=daoSueldo.getByCodigoSueldo(this.session, id);
          this.transaccion.commit();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }
    
    
    public Sueldo getSueldo() {
        return sueldo;
    }

    public void setSueldo(Sueldo sueldo) {
        this.sueldo = sueldo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Sueldo> getListaSueldo() {
        return listaSueldo;
    }

    public void setListaSueldo(List<Sueldo> listaSueldo) {
        this.listaSueldo = listaSueldo;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public List<Sueldo> getListaSueldoFiltrado() {
        return listaSueldoFiltrado;
    }

    public void setListaSueldoFiltrado(List<Sueldo> listaSueldoFiltrado) {
        this.listaSueldoFiltrado = listaSueldoFiltrado;
    }

    public int getCodigoSueldo() {
        return codigoSueldo;
    }

    public void setCodigoSueldo(int codigoSueldo) {
        this.codigoSueldo = codigoSueldo;
    }

    public BigDecimal getSueldoTotal() {
        return sueldoTotal;
    }

    public void setSueldoTotal(BigDecimal sueldoTotal) {
        this.sueldoTotal = sueldoTotal;
    }

    public List<Sueldo> getListaSueldoByFecha() {
        return listaSueldoByFecha;
    }

    public void setListaSueldoByFecha(List<Sueldo> listaSueldoByFecha) {
        this.listaSueldoByFecha = listaSueldoByFecha;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal getTotalVentasFecha() {
        return totalVentasFecha;
    }

    public void setTotalVentasFecha(BigDecimal totalVentasFecha) {
        this.totalVentasFecha = totalVentasFecha;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getIdSueldo() {
        return idSueldo;
    }

    public void setIdSueldo(int idSueldo) {
        this.idSueldo = idSueldo;
    }

    public List<Empleado> getListaEmpleado() {
        return listaEmpleado;
    }

    public void setListaEmpleado(List<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public Sueldo getSueldoSelect() {
        return sueldoSelect;
    }

    public void setSueldoSelect(Sueldo sueldoSelect) {
        this.sueldoSelect = sueldoSelect;
    }

}
