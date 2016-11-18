/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoAbonoSecundario;
import Daos.DaoAbonos;
import Daos.DaoCliente;
import Daos.DaoEmpleado;
import HibernateUtil.HibernateUtil;
import Pojos.Abonos;
import Pojos.Abonosecundario;
import Pojos.Cliente;
import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ander
 */
@ManagedBean
@ViewScoped
public class MbAbonoSecundario {

    private Session session;
    private Transaction transaccion;
    private Abonosecundario abonosecundario;
    private int idEmpleado;
    private int idAbono;
    private int idAbonoSecundario;
    private String numeroDocumento;
    private List<Abonos> listaAbonos;
    private List<Abonosecundario> listaAbonoSecundario;
    private List<Abonosecundario> listaAbonoFiltradoSecundario;
    private Cliente cliente;
    private Abonos abonos;
    private List<Abonosecundario> listaAbonoSecundarioByFecha;
    private Date fechaFin;
    private Date fechaInicio;
    private long totalVentasFecha;
     private Abonosecundario abonoSelect;

    /**
     * Creates a new instance of MbAbonoSecundario
     */
    public MbAbonoSecundario() {
        this.abonosecundario = new Abonosecundario();
        this.abonosecundario.setIdabonosecun(Integer.MIN_VALUE);
        this.listaAbonos = new ArrayList<>();
        this.cliente = new Cliente();
        this.listaAbonoSecundario = new ArrayList<>();
        this.abonos = new Abonos();
    }

    public void save() {
        this.listaAbonos = new ArrayList<>();
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            DaoAbonoSecundario daoAbonoSecundario = new DaoAbonoSecundario();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            DaoAbonos daoAbonos = new DaoAbonos();
            Abonos abonos = new Abonos();
            
            abonos = daoAbonos.getById(this.session, this.idAbono);
            abonos.setSaldofinal(abonos.getSaldofinal() - this.abonosecundario.getValorAbono());
            daoAbonos.actualizar(this.session, abonos);
            
              long suma = 0;
            this.abonosecundario.setAbonos(abonos);
            this.abonosecundario.setEmpleado(daoEmpleado.getById(this.session, this.idEmpleado));
            suma = abonos.getSaldofinal()-abonosecundario.getValorAbono();
            daoAbonoSecundario.registar(this.session, this.abonosecundario);
            
             this.abonosecundario.setSaldoTotaL(suma);
            this.listaAbonos.addAll(daoAbonos.getAllByCliente(this.session, this.numeroDocumento));
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "correcto", "Se ha registrado"));
            this.abonosecundario = new Abonosecundario();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }

    public void selectAbono(int id) {
        this.idAbono = id;
    }
    
    public void selectAbonoSecundario(int id){
        this.idAbonoSecundario=id;
    }

    public void searchByDocumento() {
        this.listaAbonos = new ArrayList<>();
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoCliente daoCliente = new DaoCliente();
            DaoAbonos daoAbonos = new DaoAbonos();
            this.cliente = daoCliente.getByNumeroDocumento(this.session, this.numeroDocumento);
            this.listaAbonos.addAll(daoAbonos.getAllByCliente(this.session, this.numeroDocumento));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            this.listaAbonos = null;
            this.cliente = null;
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }

    public void BuscarBYcodigoAbonoSecundario(int id) {
         this.listaAbonoSecundario = new ArrayList<>();
         this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoAbonoSecundario daoAbonoSecundario = new DaoAbonoSecundario();
             this.listaAbonoSecundario.clear();
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "el id es" + id));
            System.out.println("el id es"+ id);
            this.listaAbonoSecundario.addAll(daoAbonoSecundario.getAllByIdAbonos(this.session, id));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            this.listaAbonoSecundario = null;
            this.abonos = null;
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }

        public void BuscarBYcodigoAbono(int id) {
        this.session = null;
        this.transaccion = null;
        this.abonoSelect=null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            DaoAbonoSecundario daoAbonoSecundario = new DaoAbonoSecundario();

            this.abonoSelect = daoAbonoSecundario.getById(this.session, id);
            this.transaccion.commit();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }
    
    public List<Abonosecundario> getAll() {
        this.session = null;
        this.transaccion = null;
        try {

            DaoAbonoSecundario daoAbonoSecundario = new DaoAbonoSecundario();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaAbonoSecundario = daoAbonoSecundario.getAll(this.session);
            this.transaccion.commit();
            return this.listaAbonoSecundario;
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

    public List<Abonosecundario> getListAbonoSecundarioByFecha() {
        if (listaAbonoSecundarioByFecha == null) {
            listaAbonoSecundarioByFecha = new ArrayList<>();
        }
        return listaAbonoSecundarioByFecha;
    }

    public void consultarVentas() {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            DaoAbonoSecundario daoAbonoSecundario = new DaoAbonoSecundario();
            this.listaAbonoSecundarioByFecha = daoAbonoSecundario.getAllByDate(this.session, this.fechaInicio, this.fechaFin);

            totalVentasFecha = 0;
            for (Abonosecundario abonosecundari : listaAbonoSecundarioByFecha) {
                totalVentasFecha = totalVentasFecha + (abonosecundari.getValorAbono());
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

    public Abonosecundario getAbonosecundario() {
        return abonosecundario;
    }

    public void setAbonosecundario(Abonosecundario abonosecundario) {
        this.abonosecundario = abonosecundario;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public List<Abonos> getListaAbonos() {
        return listaAbonos;
    }

    public void setListaAbonos(List<Abonos> listaAbonos) {
        this.listaAbonos = listaAbonos;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Abonosecundario> getListaAbonoSecundario() {
        return listaAbonoSecundario;
    }

    public void setListaAbonoSecundario(List<Abonosecundario> listaAbonoSecundario) {
        this.listaAbonoSecundario = listaAbonoSecundario;
    }

    public List<Abonosecundario> getListaAbonoFiltradoSecundario() {
        return listaAbonoFiltradoSecundario;
    }

    public void setListaAbonoFiltradoSecundario(List<Abonosecundario> listaAbonoFiltradoSecundario) {
        this.listaAbonoFiltradoSecundario = listaAbonoFiltradoSecundario;
    }

    public Abonos getAbonos() {
        return abonos;
    }

    public void setAbonos(Abonos abonos) {
        this.abonos = abonos;
    }

    public List<Abonosecundario> getListaAbonoSecundarioByFecha() {
        return listaAbonoSecundarioByFecha;
    }

    public void setListaAbonoSecundarioByFecha(List<Abonosecundario> listaAbonoSecundarioByFecha) {
        this.listaAbonoSecundarioByFecha = listaAbonoSecundarioByFecha;
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

    public long getTotalVentasFecha() {
        return totalVentasFecha;
    }

    public void setTotalVentasFecha(long totalVentasFecha) {
        this.totalVentasFecha = totalVentasFecha;
    }

    public int getIdAbonoSecundario() {
        return idAbonoSecundario;
    }

    public Abonosecundario getAbonoSelect() {
        return abonoSelect;
    }

    public void setAbonoSelect(Abonosecundario abonoSelect) {
        this.abonoSelect = abonoSelect;
    }

    public void setIdAbonoSecundario(int idAbonoSecundario) {
        this.idAbonoSecundario = idAbonoSecundario;
    }


}
