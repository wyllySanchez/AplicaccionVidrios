/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Clases.Despiece;
import Clases.Material;
import Clases.Puerta;
import Clases.Vitrina;
import Daos.DaoAbonoDetalle;
import Daos.DaoAbonoSecundario;
import Daos.DaoAbonos;
import Daos.DaoCliente;
import Daos.DaoEmpleado;
import Daos.DaoMaterial;
import Daos.DaoProductos;
import Daos.DaoPuertas;
import Daos.DaoVidrio;
import Daos.DaoVitrinas;
import HibernateUtil.HibernateUtil;
import Pojos.Abonodetalle;
import Pojos.Abonos;
import Pojos.Abonosecundario;
import Pojos.Cliente;
import Pojos.Empleado;
import Pojos.Materiales;
import Pojos.Puertas;
import Pojos.Vitrinas;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author William Sanchez
 */
@ManagedBean
@ViewScoped
public class MbAbonos {

    /**
     * Creates a new instance of MbAbonos
     */
    private Session session;
    private Transaction transaccion;

    private Abonodetalle abonoDeta;
    private ArrayList<Abonodetalle> listaAbono;
    private ArrayList<Abonodetalle> listaFactura;
    private ArrayList<Materiales> lista;
    private List<Abonos> listaAbonosFiltrado;
    private int tipoVidrio;
    private int tipoVentana;
    private String ancho;
    private String alto;
    private int ganancia;
    private int manObra;
    private Abonos abonos;
    private List<Abonos> listaAbonoSel;
    private List<Abonos> listaVentasByFecha;
    private String idCliente;
    private int idEmpleado;
    private int idAbono;
    private long precioVidrio;
    private int idVidrio;
    private Date fechaFin;
    private Date fechaInicio;
    private long totalVentasFecha;
    private long montoAbono;
    private List<Abonosecundario> listaAbonoSecundario;
    private int idAbonoSecundario;
    private int productoTipo;
    private int tipoProductos;
    private String fondo;
    private List<Puertas> listaPuertas;
    private int tipoPuerta;
    private List<Vitrinas> listaVitrinas;
    private int tipoVitrina;
    private int tipoEntrepanos;
    private String nombreProducto;
    private long precioTotal;
    private int tipoAluminio;
    private String numeroDocumento;
    private Cliente cliente;
    private Abonos abonoSelect;
    private Empleado empleado;
    private int codigoCliente;
    private int codigoEmpleado;
    private List<Despiece> lstDespiece;
    private int tipoAluminioPuerta;

    private List<Cliente> listaClientes;
    private List<Empleado> listaEmpleado;

    public MbAbonos() {
        this.lstDespiece = new ArrayList<>();
        this.listaVentasByFecha = new ArrayList<>();
        this.listaAbonoSel = new ArrayList<>();
        this.lista = new ArrayList<>();
        this.listaPuertas = new ArrayList<>();
        this.listaAbono = new ArrayList<>();
        this.listaVitrinas = new ArrayList<>();
        this.listaFactura = new ArrayList<>();
        this.abonos = new Abonos();
        this.abonoDeta = new Abonodetalle();
        this.listaAbonoSecundario = new ArrayList<>();
        this.empleado = new Empleado();
        this.listaClientes = new ArrayList<>();
        this.listaEmpleado = new ArrayList<>();
        this.cliente = new Cliente();
        this.idVidrio = 0;
        this.ganancia = 0;
        this.alto = "";
        this.ancho = "";
        this.manObra = 0;
        this.tipoVentana = 0;
        this.tipoPuerta = 0;
        this.tipoVidrio = 0;
        this.tipoVitrina = 0;
        this.fondo = "";
        this.productoTipo = 0;
        this.tipoEntrepanos = 0;
        this.tipoAluminioPuerta = 0;
    }

    public void agregarProducto() {
        switch (this.tipoProductos) {
            case 1:
                calcularVentana();
                break;
            case 2:
                calcularPuerta();
                break;
            case 3:
                calcularVitrina();
                break;
            case 4:
                calcularVentana();
                break;
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Escoja un tipo de producto"));
                break;
        }
    }

    public void calcularVitrina() {
        this.session = null;
        this.transaccion = null;
        Vitrina vitrina = new Vitrina();
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoProductos daoProductos = new DaoProductos();
            DaoVitrinas daoVitrinas = new DaoVitrinas();
            this.listaVitrinas.addAll(daoVitrinas.getAll(this.session));
            int codigoVitrina = 0;

            if (this.tipoVitrina == 1) {

                codigoVitrina = 7;
                vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                        this.listaVitrinas.get(0).getPreciocot(), this.listaVitrinas.get(1).getPreciocot(),
                        this.listaVitrinas.get(2).getPreciocot(), this.listaVitrinas.get(4).getPreciocot(), this.listaVitrinas.get(5).getPreciocot(),
                        this.listaVitrinas.get(6).getPreciocot(), this.listaVitrinas.get(3).getPreciocot(),
                        this.listaVitrinas.get(7).getPreciocot(),
                        this.listaVitrinas.get(8).getPreciocot(), 1, 0, 0, 0, 0);

            } else {
                if (this.tipoVitrina == 2) {
                    codigoVitrina = 11;
                    vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                            0, this.listaVitrinas.get(1).getPreciocot(),
                            0, this.listaVitrinas.get(4).getPreciocot(), 0,
                            this.listaVitrinas.get(6).getPreciocot(), 0, 0,
                            this.listaVitrinas.get(8).getPreciocot(),
                            2, this.listaVitrinas.get(9).getPreciocot(), this.listaVitrinas.get(10).getPreciocot(),
                            this.listaVitrinas.get(11).getPreciocot(), this.listaVitrinas.get(12).getPreciocot());

                } else {
                    if (this.tipoVitrina == 3) {
                        codigoVitrina = 12;
                        vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                                0, this.listaVitrinas.get(1).getPreciocot(),
                                0, this.listaVitrinas.get(4).getPreciocot(), 0,
                                this.listaVitrinas.get(6).getPreciocot(), 0, 0,
                                this.listaVitrinas.get(8).getPreciocot(),
                                3, this.listaVitrinas.get(9).getPreciocot(), this.listaVitrinas.get(10).getPreciocot(),
                                this.listaVitrinas.get(11).getPreciocot(), this.listaVitrinas.get(12).getPreciocot());

                    }
                }
            }

            if (this.idVidrio != 0) {
                int vidrioEntrepano = 0;
                DaoVidrio daoVidrio = new DaoVidrio();

                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
                vidrioEntrepano = daoVidrio.getById(this.session, 6).getPreciocost();
                long precFondos = this.precioVidrio * (vitrina.getAlto() * vitrina.getFondo());
                precFondos = precFondos * 2;
                long precFondoAncho = this.precioVidrio * (vitrina.getAncho() * vitrina.getFondo());
                precFondoAncho = precFondoAncho * 2;
                this.precioVidrio = this.precioVidrio * (vitrina.getAlto() * vitrina.getAncho());
                this.precioVidrio = this.precioVidrio * 2;
                this.precioVidrio = this.precioVidrio + precFondos + precFondoAncho;

                if (this.tipoEntrepanos == 1) {
                    vidrioEntrepano = vidrioEntrepano * (vitrina.getAncho() * vitrina.getFondo() * 3);
                } else {
                    if (this.tipoEntrepanos == 2) {
                        vidrioEntrepano = vidrioEntrepano * (vitrina.getAncho() * vitrina.getFondo() * 4);
                    }
                }

                // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                this.precioVidrio = this.precioVidrio + vidrioEntrepano;
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
                int espacios = String.valueOf(this.precioVidrio).length();
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios - 4));

            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }

            this.listaAbono.add(new Abonodetalle(null, daoProductos.getById(this.session, codigoVitrina),
                    daoProductos.getById(this.session, codigoVitrina).getNombre() + " " + this.alto + "*" + this.ancho + " fondo: " + this.fondo,
                    1, vitrina.getSumaTotal() + this.precioVidrio, 1 * vitrina.getSumaTotal()
                    + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, this.fondo, daoProductos.getById(this.session, codigoVitrina).getImgPrinci()));

            this.transaccion.commit();

            this.idVidrio = 0;
            this.ganancia = 0;
            this.alto = "";
            this.ancho = "";
            this.manObra = 0;
            this.tipoVentana = 0;
            this.tipoVidrio = 0;
            this.tipoVitrina = 0;
            this.fondo = "";
            this.productoTipo = 0;
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

    public void calcularPuerta() {
        this.session = null;
        this.transaccion = null;
        Puerta puertas = new Puerta();

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoProductos daoProductos = new DaoProductos();
            DaoPuertas daoPuertas = new DaoPuertas();
            this.listaPuertas.addAll(daoPuertas.getAll(this.session));

            if (this.tipoAluminioPuerta == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Seleccione un tipo de aluminio"));
                return;
            }
       
            if (this.tipoAluminioPuerta == 1) {
                puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                        this.listaPuertas.get(1).getPreciocot(), this.listaPuertas.get(0).getPreciocot(),
                        this.listaPuertas.get(2).getPreciocot(), this.listaPuertas.get(3).getPreciocot(),
                        this.listaPuertas.get(5).getPreciocot(), this.listaPuertas.get(6).getPreciocot(),
                        this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                        this.listaPuertas.get(8).getPreciocot(), this.tipoPuerta, this.listaPuertas.get(9).getPreciocot(),
                        this.listaPuertas.get(10).getPreciocot(), this.listaPuertas.get(11).getPreciocot(),
                        this.listaPuertas.get(12).getPreciocot(), this.listaPuertas.get(13).getPreciocot());
            } else {
                if (this.tipoAluminioPuerta == 2) {
                    puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                            this.listaPuertas.get(15).getPreciocot(), this.listaPuertas.get(14).getPreciocot(),
                            this.listaPuertas.get(16).getPreciocot(), this.listaPuertas.get(17).getPreciocot(),
                            this.listaPuertas.get(5).getPreciocot(), this.listaPuertas.get(6).getPreciocot(),
                            this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                            this.listaPuertas.get(18).getPreciocot(), this.tipoPuerta, this.listaPuertas.get(19).getPreciocot(),
                            this.listaPuertas.get(20).getPreciocot(), this.listaPuertas.get(21).getPreciocot(),
                            this.listaPuertas.get(12).getPreciocot(), this.listaPuertas.get(13).getPreciocot());

                } else {
                    if (this.tipoAluminioPuerta == 3) {
                        puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                this.listaPuertas.get(23).getPreciocot(), this.listaPuertas.get(22).getPreciocot(),
                                this.listaPuertas.get(24).getPreciocot(), this.listaPuertas.get(25).getPreciocot(),
                                this.listaPuertas.get(5).getPreciocot(), this.listaPuertas.get(6).getPreciocot(),
                                this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                                this.listaPuertas.get(26).getPreciocot(), this.tipoPuerta, this.listaPuertas.get(27).getPreciocot(),
                                this.listaPuertas.get(28).getPreciocot(), this.listaPuertas.get(29).getPreciocot(),
                                this.listaPuertas.get(12).getPreciocot(), this.listaPuertas.get(13).getPreciocot());
                    }
                }
            }

            if (this.idVidrio != 0) {
                DaoVidrio daoVidrio = new DaoVidrio();

                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
//                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio1 del vidrio es: " + this.precioVidrio));

                this.precioVidrio = this.precioVidrio * (puertas.getAlto() * puertas.getAncho());
                // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
                int espacios = String.valueOf(this.precioVidrio).length();
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios - 4));

            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }
            if (this.tipoPuerta < 6 && this.tipoPuerta > 0) {
                String nombreAluminio = "";
                switch (tipoAluminioPuerta) {
                    case 1:
                        nombreAluminio = " Color natural";
                        break;
                    case 2:
                        nombreAluminio = " Color Anolo";
                        break;
                    case 3:
                        nombreAluminio = " Color ";
                        break;
                }

                this.listaAbono.add(new Abonodetalle(null, daoProductos.getById(this.session, this.tipoPuerta),
                        daoProductos.getById(this.session, this.tipoPuerta).getNombre() + " " + nombreAluminio + "  " + this.alto + "*" + this.ancho,
                        1, puertas.getSumaTotal() + this.precioVidrio, 1 * puertas.getSumaTotal()
                        + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, "", daoProductos.getById(this.session, this.tipoPuerta).getImgPrinci()));
            }
            
            this.transaccion.commit();
            this.idVidrio = 0;
            this.ganancia = 0;
            this.alto = "";
            this.ancho = "";
            this.manObra = 0;
            this.tipoVidrio = 0;
            this.tipoVitrina = 0;
            this.fondo = "";
            this.productoTipo = 0;
            this.tipoAluminioPuerta = 0;

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

    public void calcularVentana() {
        this.session = null;
        this.transaccion = null;
        try {
            Material material = new Material();
            if (this.alto.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Por favor digite el alto."));
                return;
            }

            if (this.ancho.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Por favor digite el ancho."));
                return;
            }

            if (this.alto.length() > 3 || this.ancho.length() > 3) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Las medidas no pueden superar 3 cifras."));
                return;
            }

            if (String.valueOf(this.manObra).equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digite el precio de la mano de obra."));
                return;
            }

            if (String.valueOf(this.ganancia).equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digite el porcentaje de ganancia."));
                return;
            }

            if (this.tipoVentana == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Escoja un tipo de producto."));
                return;
            }

            try {
                int o = Integer.valueOf(this.alto);
                int p = Integer.valueOf(this.ancho);
                int m = this.manObra;
                int t = this.ganancia;
            } catch (NumberFormatException nfe) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digito texto en un campo numerico."));
            }

            DaoMaterial daoMaterial = new DaoMaterial();
            DaoProductos daoProductos = new DaoProductos();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.lista.addAll(daoMaterial.getAll(this.session));

            if (this.tipoAluminio == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Seleccione un tipo de aluminio"));
                return;
            }

             if (this.tipoAluminio == 1) {
                material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                        this.lista.get(0).getPreciocost(), this.lista.get(2).getPreciocost(),
                        this.lista.get(1).getPreciocost(), this.lista.get(3).getPreciocost(),
                        this.lista.get(4).getPreciocost(),
                        this.lista.get(5).getPreciocost(), this.lista.get(6).getPreciocost(),
                        this.lista.get(9).getPreciocost(), this.lista.get(8).getPreciocost(),
                        this.lista.get(7).getPreciocost(), this.lista.get(10).getPreciocost(),
                        this.tipoVentana, this.lista.get(11).getPreciocost());
            } else {

                if (this.tipoAluminio == 2) {
                    material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                            this.lista.get(12).getPreciocost(), this.lista.get(14).getPreciocost(),
                            this.lista.get(13).getPreciocost(), this.lista.get(15).getPreciocost(),
                            this.lista.get(16).getPreciocost(),
                            this.lista.get(17).getPreciocost(), this.lista.get(18).getPreciocost(),
                            this.lista.get(21).getPreciocost(), this.lista.get(20).getPreciocost(),
                            this.lista.get(19).getPreciocost(), this.lista.get(22).getPreciocost(),
                            this.tipoVentana, this.lista.get(23).getPreciocost());
                } else {
                    if (this.tipoAluminio == 3) {

                        material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                                this.lista.get(24).getPreciocost(), this.lista.get(26).getPreciocost(),
                                this.lista.get(25).getPreciocost(), this.lista.get(27).getPreciocost(),
                                this.lista.get(28).getPreciocost(),
                                this.lista.get(29).getPreciocost(), this.lista.get(30).getPreciocost(),
                                this.lista.get(33).getPreciocost(), this.lista.get(32).getPreciocost(),
                                this.lista.get(31).getPreciocost(), this.lista.get(34).getPreciocost(),
                                this.tipoVentana, this.lista.get(35).getPreciocost());

                    } else {
                        if (this.tipoAluminio == 4) {
                            material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                                    this.lista.get(36).getPreciocost(), this.lista.get(38).getPreciocost(),
                                    this.lista.get(37).getPreciocost(), this.lista.get(39).getPreciocost(),
                                    this.lista.get(40).getPreciocost(),
                                    this.lista.get(41).getPreciocost(), this.lista.get(42).getPreciocost(),
                                    this.lista.get(9).getPreciocost(), this.lista.get(8).getPreciocost(),
                                    this.lista.get(7).getPreciocost(), this.lista.get(10).getPreciocost(),
                                    this.tipoVentana, this.lista.get(43).getPreciocost());
                        } else {

                            if (this.tipoAluminio == 5) {
                                material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                                        this.lista.get(44).getPreciocost(), this.lista.get(46).getPreciocost(),
                                        this.lista.get(45).getPreciocost(), this.lista.get(47).getPreciocost(),
                                        this.lista.get(48).getPreciocost(),
                                        this.lista.get(49).getPreciocost(), this.lista.get(50).getPreciocost(),
                                        this.lista.get(21).getPreciocost(), this.lista.get(20).getPreciocost(),
                                        this.lista.get(19).getPreciocost(), this.lista.get(22).getPreciocost(),
                                        this.tipoVentana, this.lista.get(51).getPreciocost());
                            } else {
                                if (this.tipoAluminio == 6) {

                                    material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                                            this.lista.get(52).getPreciocost(), this.lista.get(54).getPreciocost(),
                                            this.lista.get(53).getPreciocost(), this.lista.get(55).getPreciocost(),
                                            this.lista.get(56).getPreciocost(),
                                            this.lista.get(57).getPreciocost(), this.lista.get(58).getPreciocost(),
                                            this.lista.get(33).getPreciocost(), this.lista.get(32).getPreciocost(),
                                            this.lista.get(31).getPreciocost(), this.lista.get(34).getPreciocost(),
                                            this.tipoVentana, this.lista.get(59).getPreciocost());
                                } else {

                                    if (this.tipoAluminio == 7) {
                                        material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                                                this.lista.get(60).getPreciocost(), this.lista.get(62).getPreciocost(),
                                                this.lista.get(61).getPreciocost(), this.lista.get(63).getPreciocost(),
                                                this.lista.get(64).getPreciocost(),
                                                this.lista.get(65).getPreciocost(), this.lista.get(66).getPreciocost(),
                                                this.lista.get(9).getPreciocost(), this.lista.get(8).getPreciocost(),
                                                this.lista.get(7).getPreciocost(), this.lista.get(10).getPreciocost(),
                                                this.tipoVentana, this.lista.get(67).getPreciocost());
                                    } else {

                                        if (this.tipoAluminio == 8) {
                                            material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                                                    this.lista.get(68).getPreciocost(), this.lista.get(70).getPreciocost(),
                                                    this.lista.get(69).getPreciocost(), this.lista.get(71).getPreciocost(),
                                                    this.lista.get(72).getPreciocost(),
                                                    this.lista.get(73).getPreciocost(), this.lista.get(74).getPreciocost(),
                                                    this.lista.get(21).getPreciocost(), this.lista.get(20).getPreciocost(),
                                                    this.lista.get(19).getPreciocost(), this.lista.get(22).getPreciocost(),
                                                    this.tipoVentana, this.lista.get(75).getPreciocost());
                                        } else {
                                            if (this.tipoAluminio == 9) {

                                                material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                                                        this.lista.get(76).getPreciocost(), this.lista.get(78).getPreciocost(),
                                                        this.lista.get(77).getPreciocost(), this.lista.get(79).getPreciocost(),
                                                        this.lista.get(80).getPreciocost(),
                                                        this.lista.get(81).getPreciocost(), this.lista.get(82).getPreciocost(),
                                                        this.lista.get(33).getPreciocost(), this.lista.get(32).getPreciocost(),
                                                        this.lista.get(31).getPreciocost(), this.lista.get(34).getPreciocost(),
                                                        this.tipoVentana, this.lista.get(83).getPreciocost());

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (this.idVidrio != 0) {
                DaoVidrio daoVidrio = new DaoVidrio();

                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
                this.precioVidrio = this.precioVidrio * (material.getAlto() * material.getAncho());

                // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2) + this.manObra;
                int espacios = String.valueOf(this.precioVidrio).length();
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios - 4));
            }
            if (this.idVidrio == 0) {
                this.precioVidrio = 0;
            }

            if (this.tipoVentana < 4 && this.tipoVentana > 0) {
                String nombreAluminio = "";
                switch (tipoAluminio) {
                    case 1:
                        nombreAluminio = " Aluminio 744 natural";
                        break;
                    case 2:
                        nombreAluminio = " Aluminio 5020 natural";
                        break;
                    case 3:
                        nombreAluminio = " Aluminio 8025 natural";
                        break;
                }

                this.listaAbono.add(new Abonodetalle(null, daoProductos.getById(this.session, this.tipoVentana),
                        daoProductos.getById(this.session, this.tipoVentana).getNombre() + " " + nombreAluminio + "  " + this.alto + "*" + this.ancho,
                        1, material.getSumaTotal() + this.precioVidrio, 1 * material.getSumaTotal()
                        + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, "", daoProductos.getById(this.session, this.tipoVentana).getImgPrinci()));

            }
            if (this.tipoProductos == 4) {
                this.listaAbono.add(new Abonodetalle(null, daoProductos.getById(this.session, this.tipoVentana),
                        daoProductos.getById(this.session, this.tipoVentana).getNombre() + " " + this.alto + "*" + this.ancho,
                        1, this.precioVidrio + this.manObra, 1 * this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, "", daoProductos.getById(this.session, this.tipoVentana).getImgPrinci()));
            }
            this.transaccion.commit();
            this.idVidrio = 0;
            this.ganancia = 0;
            this.alto = "";
            this.ancho = "";
            this.manObra = 0;
            this.tipoVentana = 0;
            this.tipoVidrio = 0;
            this.productoTipo = 0;

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

    public void generarFactura() {
        this.session = null;
        this.transaccion = null;
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String correoEmple = httpSession.getAttribute("correoElectronico").toString();
        try {
            DaoCliente daoCliente = new DaoCliente();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            DaoAbonos daoAbonos = new DaoAbonos();
            DaoAbonoDetalle daoAbonoDetalle = new DaoAbonoDetalle();

            if (this.montoAbono == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digite el valor del Abono."));
                return;
            }
            if (String.valueOf(this.montoAbono).equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digite el valor del Abono."));
                return;
            }
            try {

                long m = this.montoAbono;

            } catch (NumberFormatException nfe) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digito texto en un campo numerico."));
            }

            if (this.cliente == null) {
                System.out.println("codigo del cliente");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Por favor selecione un cliente."));
                return;
            }
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.abonos.setCliente(cliente);
            this.abonos.setEmpleado(daoEmpleado.getByCorreoElectronico(this.session, correoEmple));
            this.abonos.setFecharegistro(new Date());
            daoAbonos.registar(this.session, this.abonos);

            this.abonos = daoAbonos.getByUltimoRegistro(this.session);
            long suma = 0;
            for (Abonodetalle listaAbono1 : this.listaAbono) {
                listaAbono1.setAbonos(this.abonos);
                suma += listaAbono1.getTotal();
                daoAbonoDetalle.registar(this.session, listaAbono1);
            }
            this.listaFactura = this.listaAbono;
            this.abonos.setPrecioTotal(suma);
            this.abonos.setMontoabono(this.montoAbono);
            this.abonos.setSaldofinal(this.abonos.getPrecioTotal() - this.abonos.getMontoabono());
            daoAbonos.actualizar(this.session, this.abonos);
            this.abonos.getFecharegistro();
            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            sesson.setAttribute("idfactura", this.abonos.getIdabonos());
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Se ha registrado "));

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

    public void retirarListaVentaDetalle(String nombre) {
        try {
            int i = 0;
            for (Abonodetalle listaAbono1 : this.listaAbono) {
                if (listaAbono1.getNombrepro().equals(nombre)) {
                    this.listaAbono.remove(i);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Productos retirado de la lista de ventas"));

                    break;
                }
                i++;
            }
            long total = 0;

            for (Abonodetalle listaAbono1 : this.listaAbono) {
                listaAbono1.setTotal(listaAbono1.getCantidad() * listaAbono1.getPrecioventa());
                total = total + listaAbono1.getTotal();

            }
            this.abonos.setPrecioTotal(total);
//            this.abonos.setSaldofinal(this.abonos.getPrecioTotal() - this.abonos.getMontoabono());

//            RequestContext.getCurrentInstance().update("frmFactura:tablaProductosFactura frmFactura:gripTotalFactura");
            RequestContext.getCurrentInstance().update("frmAbono:mensajeGeneral");

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    public void calcular() {
        long total = 0;
        for (Abonodetalle listaAbono1 : listaAbono) {
            listaAbono1.setTotal(listaAbono1.getCantidad() * listaAbono1.getPrecioventa());
            total = total + listaAbono1.getTotal();
        }
        this.abonos.setPrecioTotal(total);
//      this.abonos.setSaldofinal(this.abonos.getSaldofinal() - this.abonos.getPrecioTotal());
    }

    public String convertFecha(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(fecha);
        }
        return "";
    }

    public List<Abonodetalle> getByIdFactura() {
        this.session = null;
        this.transaccion = null;

        try {

            DaoAbonos daoAbonos = new DaoAbonos();
            DaoAbonoDetalle daoAbonoDetalle = new DaoAbonoDetalle();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

            this.abonos = daoAbonos.getById(this.session, Integer.valueOf(sesson.getAttribute("idfactura").toString()));
            List<Abonodetalle> listaVenta = daoAbonoDetalle.getAllByIdFactura(this.session, this.abonos.getIdabonos());
            this.transaccion.commit();
            return listaVenta;
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

    public Abonos getFacturaActual() {
        this.session = null;
        this.transaccion = null;

        try {
            DaoAbonos daoAbonos = new DaoAbonos();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            this.abonos = daoAbonos.getById(this.session, Integer.valueOf(sesson.getAttribute("idfactura").toString()));

            this.transaccion.commit();
            return this.abonos;
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

    public List<Abonos> getAll() {
        this.session = null;
        this.transaccion = null;
        try {

            DaoAbonos daoAbonos = new DaoAbonos();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaAbonoSel = daoAbonos.getAll(this.session);
            this.transaccion.commit();
            return this.listaAbonoSel;
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

    public List<Abonos> getListVentasByFecha() {
        if (listaVentasByFecha == null) {
            listaVentasByFecha = new ArrayList<>();
        }
        return listaVentasByFecha;
    }

    public void consultarVentas() {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            DaoAbonos daoAbonos = new DaoAbonos();
            this.listaVentasByFecha = daoAbonos.getAllFecha(this.session, this.fechaInicio, this.fechaFin);

            totalVentasFecha = 0;
            for (Abonos abono : listaVentasByFecha) {
                totalVentasFecha = totalVentasFecha + (abono.getMontoabono());
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

    public void selectAbono(int id) {
        this.idAbono = id;
    }

    public void searchByDocumento() {
        this.listaAbonoSel = new ArrayList<>();
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoCliente daoCliente = new DaoCliente();
            DaoAbonos daoAbonos = new DaoAbonos();
            this.cliente = daoCliente.getByNumeroDocumento(this.session, this.numeroDocumento);
            this.listaAbonoSel.addAll(daoAbonos.getAllByCliente(this.session, this.numeroDocumento));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            this.listaAbonoSel = null;
            this.cliente = null;
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }

    //    aqui comienza el codigo nuevo 
    public void agregarDatosCliente(Integer codCliente) {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            DaoCliente daoCliente = new DaoCliente();
            this.transaccion = this.session.beginTransaction();
            //obtener los datos del cliente en la variable objecto cliente segun el codigo del cliente
            this.cliente = daoCliente.getById(this.session, codCliente);

            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro fue realizado correctamente"));
        } catch (Exception e) {
            if (this.transaccion != null) {
                System.out.println(e.getMessage());
                transaccion.rollback();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "El registro fue erroneo"));

            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void agregarDatosCliente2() {
        this.session = null;
        this.transaccion = null;
        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            DaoCliente daoCliente = new DaoCliente();
            this.transaccion = this.session.beginTransaction();
            //obtener los datos del producto en la variable objecto cliente segun el codigo del cliente

            this.cliente = daoCliente.getById(this.session, this.codigoCliente);

//            if (this.cliente != null) {
//                this.codigoCliente = null;
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Cliente Agregado"));
//
//            } else {
//                this.codigoCliente = null;
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "El cliente no fue encontrado"));
//            }
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro fue realizado correctamente"));
        } catch (Exception e) {
            if (this.transaccion != null) {
                System.out.println(e.getMessage());
                transaccion.rollback();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "El registro fue erroneo"));

            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void limpiarAbonos() {
        this.cliente = new Cliente();
        this.empleado = new Empleado();
        this.abonos = new Abonos();
        this.listaAbono = new ArrayList<>();
        this.listaFactura = new ArrayList<>();
        this.montoAbono = 0;

//        invocar el metodo para destivar controles en a factura
        this.disableButton();

    }

    public void agregarDatosEmpleado(Integer codEmpleado) {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            DaoEmpleado daoEmpleado = new DaoEmpleado();
            this.transaccion = this.session.beginTransaction();
            //obtener los datos del cliente en la variable objecto cliente segun el codigo del cliente

            this.empleado = daoEmpleado.getById(this.session, codEmpleado);

            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro fue realizado correctamente"));
        } catch (Exception e) {
            if (this.transaccion != null) {
                System.out.println(e.getMessage());
                transaccion.rollback();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "El registro fue erroneo"));

            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void agregarDatosEmpleado2() {
        this.session = null;
        this.transaccion = null;
        try {

            this.session = HibernateUtil.getSessionFactory().openSession();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            this.transaccion = this.session.beginTransaction();
            //obtener los datos del producto en la variable objecto cliente segun el codigo del cliente

            this.empleado = daoEmpleado.getById(this.session, this.codigoEmpleado);

//            if (this.empleado != null) {
////                this.codigoEmpleado = null;
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Cliente Agregado"));
//
//            } else {
//                this.codigoCliente = null;
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "El cliente no fue encontrado"));
//            }
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro fue realizado correctamente"));
        } catch (Exception e) {
            if (this.transaccion != null) {
                System.out.println(e.getMessage());
                transaccion.rollback();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "El registro fue erroneo"));

            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public List<Cliente> getAllClientes() {
        this.session = null;
        this.transaccion = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            DaoCliente daoCliente = new DaoCliente();

            this.transaccion = this.session.beginTransaction();

            this.listaClientes = daoCliente.getAll(this.session);

            this.transaccion.commit();

            return this.listaClientes;
        } catch (Exception ex) {
            if (this.transaccion != null) {
                transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));

            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public List<Empleado> getAllEmpleado() {
        this.session = null;
        this.transaccion = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            DaoEmpleado daoEmpleado = new DaoEmpleado();
            this.transaccion = this.session.beginTransaction();

            this.listaEmpleado = daoEmpleado.getAll(this.session);

            this.transaccion.commit();

            return this.listaEmpleado;
        } catch (Exception ex) {
            if (this.transaccion != null) {
                transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));

            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void onRowEdit(RowEditEvent Event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO:", "Producto editado "));

//                invocamos al metodo calculartotalfactura para actualizar la factura
        this.calcular();
    }

    public void onRowEditCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "...:", "No se hizo ningun cambio "));

    }

//    metodos para activar o desativar los botnes del sistema
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;

    }

    public void enebleButton() {
        enabled = true;
    }

    public void disableButton() {
        enabled = false;
    }

    public Abonodetalle getAbonoDeta() {
        return abonoDeta;
    }

    public void setAbonoDeta(Abonodetalle abonoDeta) {
        this.abonoDeta = abonoDeta;
    }

    public ArrayList<Abonodetalle> getListaAbono() {
        return listaAbono;
    }

    public void setListaAbono(ArrayList<Abonodetalle> listaAbono) {
        this.listaAbono = listaAbono;
    }

    public ArrayList<Abonodetalle> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(ArrayList<Abonodetalle> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public ArrayList<Materiales> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Materiales> lista) {
        this.lista = lista;
    }

    public int getTipoVidrio() {
        return tipoVidrio;
    }

    public void setTipoVidrio(int tipoVidrio) {
        this.tipoVidrio = tipoVidrio;
    }

    public int getTipoVentana() {
        return tipoVentana;
    }

    public void setTipoVentana(int tipoVentana) {
        this.tipoVentana = tipoVentana;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public int getGanancia() {
        return ganancia;
    }

    public void setGanancia(int ganancia) {
        this.ganancia = ganancia;
    }

    public int getManObra() {
        return manObra;
    }

    public void setManObra(int manObra) {
        this.manObra = manObra;
    }

    public Abonos getAbonos() {
        return abonos;
    }

    public void setAbonos(Abonos abonos) {
        this.abonos = abonos;
    }

    public List<Abonos> getListaAbonoSel() {
        return listaAbonoSel;
    }

    public void setListaAbonoSel(List<Abonos> listaAbonoSel) {
        this.listaAbonoSel = listaAbonoSel;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Abonos> getListaVentasByFecha() {
        return listaVentasByFecha;
    }

    public void setListaVentasByFecha(List<Abonos> listaVentasByFecha) {
        this.listaVentasByFecha = listaVentasByFecha;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public long getPrecioVidrio() {
        return precioVidrio;
    }

    public void setPrecioVidrio(long precioVidrio) {
        this.precioVidrio = precioVidrio;
    }

    public int getIdVidrio() {
        return idVidrio;
    }

    public void setIdVidrio(int idVidrio) {
        this.idVidrio = idVidrio;
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

    public List<Abonos> getListaAbonosFiltrado() {
        return listaAbonosFiltrado;
    }

    public void setListaAbonosFiltrado(List<Abonos> listaAbonosFiltrado) {
        this.listaAbonosFiltrado = listaAbonosFiltrado;
    }

    public long getMontoAbono() {
        return montoAbono;
    }

    public void setMontoAbono(long montoAbono) {
        this.montoAbono = montoAbono;
    }

    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public List<Abonosecundario> getListaAbonoSecundario() {
        return listaAbonoSecundario;
    }

    public void setListaAbonoSecundario(List<Abonosecundario> listaAbonoSecundario) {
        this.listaAbonoSecundario = listaAbonoSecundario;
    }

    public int getIdAbonoSecundario() {
        return idAbonoSecundario;
    }

    public void setIdAbonoSecundario(int idAbonoSecundario) {
        this.idAbonoSecundario = idAbonoSecundario;
    }

    public int getProductoTipo() {
        return productoTipo;
    }

    public void setProductoTipo(int productoTipo) {
        this.productoTipo = productoTipo;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public List<Puertas> getListaPuertas() {
        return listaPuertas;
    }

    public void setListaPuertas(List<Puertas> listaPuertas) {
        this.listaPuertas = listaPuertas;
    }

    public int getTipoPuerta() {
        return tipoPuerta;
    }

    public void setTipoPuerta(int tipoPuerta) {
        this.tipoPuerta = tipoPuerta;
    }

    public List<Vitrinas> getListaVitrinas() {
        return listaVitrinas;
    }

    public void setListaVitrinas(List<Vitrinas> listaVitrinas) {
        this.listaVitrinas = listaVitrinas;
    }

    public int getTipoVitrina() {
        return tipoVitrina;
    }

    public void setTipoVitrina(int tipoVitrina) {
        this.tipoVitrina = tipoVitrina;
    }

    public int getTipoEntrepanos() {
        return tipoEntrepanos;
    }

    public void setTipoEntrepanos(int tipoEntrepanos) {
        this.tipoEntrepanos = tipoEntrepanos;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public long getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(long precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getTipoAluminio() {
        return tipoAluminio;
    }

    public void setTipoAluminio(int tipoAluminio) {
        this.tipoAluminio = tipoAluminio;
    }

    public Abonos getAbonoSelect() {
        return abonoSelect;
    }

    public void setAbonoSelect(Abonos abonoSelect) {
        this.abonoSelect = abonoSelect;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Empleado> getListaEmpleado() {
        return listaEmpleado;
    }

    public void setListaEmpleado(List<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public int getTipoProductos() {
        return tipoProductos;
    }

    public void setTipoProductos(int tipoProductos) {
        this.tipoProductos = tipoProductos;
    }

    public List<Despiece> getLstDespiece() {
        return lstDespiece;
    }

    public void setLstDespiece(List<Despiece> lstDespiece) {
        this.lstDespiece = lstDespiece;
    }

    public int getTipoAluminioPuerta() {
        return tipoAluminioPuerta;
    }

    public void setTipoAluminioPuerta(int tipoAluminioPuerta) {
        this.tipoAluminioPuerta = tipoAluminioPuerta;
    }

}
