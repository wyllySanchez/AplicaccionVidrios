/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Clases.Material;
import Clases.Puerta;
import Clases.Vitrina;
import Daos.DaoCliente;
import Daos.DaoCotizacion;
import Daos.DaoCotizacionDetalle;
import Daos.DaoEmpleado;
import Daos.DaoMaterial;
import Daos.DaoProductos;
import Daos.DaoPuertas;
import Daos.DaoVidrio;
import Daos.DaoVitrinas;
import HibernateUtil.HibernateUtil;

import Pojos.Cliente;
import Pojos.Cotizacion;
import Pojos.Cotizaciondetalle;
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
public class MbCotizacion {

    /**
     * Creates a new instance of MbCotizacion
     */
    private Session session;
    private Transaction transaccion;
    private Cotizaciondetalle cotizacionDetall;
    private ArrayList<Cotizaciondetalle> listaVentana;
    private ArrayList<Cotizaciondetalle> listaCotizacion;
     private ArrayList<Cotizaciondetalle> listaCotizacionFiltrado;
    private ArrayList<Materiales> lista;
    private int tipoVidrio;
    private int tipoVentana;
    private String ancho;
    private String alto;
    private int ganancia;
    private int manObra;
    private Cotizacion cotizacionT;
    private List<Cotizacion> listaCot;
    private Cliente cliente;
    private String idCliente;
    private int idEmpleado;
    private long precioVidrio;
    private int idVidrio;
    private int productoTipo;
    private String fondo;
    private List<Puertas> listaPuertas;
    private int tipoPuerta;
    private List<Vitrinas> listaVitrinas;
    private int tipoVitrina;
    private String numeroDocumento;
    private int idCotizacion;
    private int tipoEntrepanos;
    private String nombreProducto;
    private long precioTotal;
    private int tipoAluminio;
    private Empleado empleado;
    private int codigoCliente;
    private int codigoEmpleado;
    private String correoElectronico;

    private List<Cliente> listaClientes;
    private List<Empleado> listaEmpleado;
    private String productoSelecionado;
   

    public MbCotizacion() {
        this.lista = new ArrayList<>();
        this.listaVentana = new ArrayList<>();
        this.listaCotizacion = new ArrayList<>();
        this.listaPuertas = new ArrayList<>();
        this.listaVitrinas = new ArrayList<>();
        this.cotizacionT = new Cotizacion();
        this.cotizacionDetall = new Cotizaciondetalle();
        this.empleado = new Empleado();
        this.listaClientes = new ArrayList<>();
        this.listaEmpleado = new ArrayList<>();
        this.cliente = new Cliente();
        this.idCliente = "";
        this.idEmpleado = 0;
        this.idVidrio = 0;
        this.ganancia = 0;
        this.alto = "";
        this.ancho = "";
        this.manObra = 0;
        this.tipoVentana = 0;
        this.tipoVidrio = 0;
        this.tipoVitrina = 0;
        this.fondo = "";
        this.tipoEntrepanos = 0;
        
    }

    public void agregarProducto() {
        switch (this.productoTipo) {
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
                calcularPuerta();
                break;
            case 5:
                calcularPuerta();
                break;
            case 6:
                calcularPuerta();
                break;
            case 7:
                calcularPuerta();
                break;
            case 8:
                calcularVitrina();
                break;
            case 9:
                calcularVitrina();
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

            if (this.productoTipo == 3) {
                codigoVitrina = 7;
                vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                        this.listaVitrinas.get(0).getPreciocot(), this.listaVitrinas.get(1).getPreciocot(),
                        this.listaVitrinas.get(2).getPreciocot(), this.listaVitrinas.get(4).getPreciocot(), this.listaVitrinas.get(5).getPreciocot(),
                        this.listaVitrinas.get(6).getPreciocot(), this.listaVitrinas.get(3).getPreciocot(),
                        this.listaVitrinas.get(7).getPreciocot(),
                        this.listaVitrinas.get(8).getPreciocot(), 1, 0, 0, 0, 0);

            } else {
                if (this.productoTipo == 8) {
                    codigoVitrina = 11;
                    vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                            0, this.listaVitrinas.get(1).getPreciocot(),
                            0, this.listaVitrinas.get(4).getPreciocot(), 0,
                            this.listaVitrinas.get(6).getPreciocot(), 0, 0,
                            this.listaVitrinas.get(8).getPreciocot(),
                            2, this.listaVitrinas.get(9).getPreciocot(), this.listaVitrinas.get(10).getPreciocot(),
                            this.listaVitrinas.get(11).getPreciocot(), this.listaVitrinas.get(12).getPreciocot());

                } else {
                    if (this.productoTipo == 9) {
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

                this.precioVidrio = this.precioVidrio + vidrioEntrepano;
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);

                int espacios = String.valueOf(this.precioVidrio).length();
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios - 4));

            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }

            this.listaCotizacion.add(new Cotizaciondetalle(null, daoProductos.getById(this.session, 7),
                    daoProductos.getById(this.session, 7).getNombre() + " " + this.alto + "*" + this.ancho + " fondo: " + this.fondo,
                    1, vitrina.getSumaTotal() + this.precioVidrio, 1 * vitrina.getSumaTotal()
                    + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, this.fondo, daoProductos.getById(this.session, 7).getImgPrinci()));

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
            int codigoPuerta = 0;

            if (this.productoTipo == 2) {

                codigoPuerta = 5;
                puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                        this.listaPuertas.get(1).getPreciocot(), this.listaPuertas.get(0).getPreciocot(),
                        this.listaPuertas.get(2).getPreciocot(), this.listaPuertas.get(3).getPreciocot(), this.listaPuertas.get(5).getPreciocot(),
                        this.listaPuertas.get(6).getPreciocot(), this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                        0, 1, 0, 0, 0, 0, this.listaPuertas.get(13).getPreciocot());

            } else {
                if (this.productoTipo == 4) {
                    codigoPuerta = 6;
                    puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                            0, this.listaPuertas.get(0).getPreciocot(), this.listaPuertas.get(2).getPreciocot(),
                            0, this.listaPuertas.get(5).getPreciocot(), this.listaPuertas.get(6).getPreciocot(),
                            this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                            this.listaPuertas.get(8).getPreciocot(),
                            2, this.listaPuertas.get(9).getPreciocot(), 0, 0, 0, 0);

                } else {
                    if (this.productoTipo == 5) {
                        codigoPuerta = 8;
                        puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                this.listaPuertas.get(1).getPreciocot(), this.listaPuertas.get(0).getPreciocot(),
                                this.listaPuertas.get(2).getPreciocot(), this.listaPuertas.get(3).getPreciocot(), this.listaPuertas.get(5).getPreciocot(),
                                this.listaPuertas.get(6).getPreciocot(), this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                                this.listaPuertas.get(8).getPreciocot(),
                                3, 0, this.listaPuertas.get(10).getPreciocot(), 0, 0, 0);
                    } else {
                        if (this.productoTipo == 6) {
                            codigoPuerta = 9;
                            puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                    0, this.listaPuertas.get(0).getPreciocot(), this.listaPuertas.get(2).getPreciocot(),
                                    this.listaPuertas.get(3).getPreciocot(), this.listaPuertas.get(5).getPreciocot(), this.listaPuertas.get(6).getPreciocot(),
                                    this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                                    this.listaPuertas.get(8).getPreciocot(),
                                    4, 0, 0, 0, 0, 0);
                        } else {
                            if (this.productoTipo == 7) {
                                codigoPuerta = 10;
                                puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                        0, this.listaPuertas.get(0).getPreciocot(), this.listaPuertas.get(2).getPreciocot(), 0, 0,
                                        this.listaPuertas.get(6).getPreciocot(), this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(), 0,
                                        5, 0, 0, this.listaPuertas.get(11).getPreciocot(), this.listaPuertas.get(12).getPreciocot(),
                                        this.listaPuertas.get(13).getPreciocot());
                            }
                        }
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
//          

            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }
            this.listaCotizacion.add(new Cotizaciondetalle(null, daoProductos.getById(this.session, codigoPuerta),
                    daoProductos.getById(this.session, codigoPuerta).getNombre() + " " + this.alto + "*" + this.ancho,
                    1, puertas.getSumaTotal() + this.precioVidrio, 1 * puertas.getSumaTotal()
                    + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, "", daoProductos.getById(this.session, codigoPuerta).getImgPrinci()));

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
                    material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                            this.lista.get(24).getPreciocost(), this.lista.get(26).getPreciocost(),
                            this.lista.get(25).getPreciocost(), this.lista.get(27).getPreciocost(),
                            this.lista.get(28).getPreciocost(),
                            this.lista.get(29).getPreciocost(), this.lista.get(30).getPreciocost(),
                            this.lista.get(33).getPreciocost(), this.lista.get(32).getPreciocost(),
                            this.lista.get(31).getPreciocost(), this.lista.get(34).getPreciocost(),
                            this.tipoVentana, this.lista.get(35).getPreciocost());
                }

            }

            if (this.idVidrio != 0) {
                DaoVidrio daoVidrio = new DaoVidrio();

                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
                this.precioVidrio = this.precioVidrio * (material.getAlto() * material.getAncho());

                // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
                int espacios = String.valueOf(this.precioVidrio).length();
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios - 4));
            }
            if (this.idVidrio == 0) {
                this.precioVidrio = 0;
            }

            if (this.tipoVentana < 4 && this.tipoVentana > 0) {
                this.listaCotizacion.add(new Cotizaciondetalle(null, daoProductos.getById(this.session, this.tipoVentana),
                        daoProductos.getById(this.session, this.tipoVentana).getNombre() + " " + this.alto + "*" + this.ancho,
                        1, material.getSumaTotal() + this.precioVidrio, 1 * material.getSumaTotal()
                        + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, "", daoProductos.getById(this.session, this.tipoVentana).getImgPrinci()));

            }
            if (this.tipoVentana == 4) {
                this.listaCotizacion.add(new Cotizaciondetalle(null, daoProductos.getById(this.session, this.tipoVentana),
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

    public void generarCotizacion() {
        this.session = null;
        this.transaccion = null;
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String correoEmple = httpSession.getAttribute("correoElectronico").toString();
        try {
            DaoCliente daoCliente = new DaoCliente();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            DaoCotizacion daoCotizacion = new DaoCotizacion();
            DaoCotizacionDetalle daoCotizacionDetalle = new DaoCotizacionDetalle();

             if (this.cliente==null ) {
                System.out.println("codigo del cliente");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Por favor selecione un cliente."));
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.cotizacionT.setCliente(cliente);
            this.cotizacionT.setEmpleado(daoEmpleado.getByCorreoElectronico(this.session, correoEmple));
            this.cotizacionT.setFechacotizacion(new Date());

            daoCotizacion.registar(this.session, this.cotizacionT);

            this.cotizacionT = daoCotizacion.getByUltimoRegistro(this.session);
            long suma = 0;
            long subtota = 0;
            long ivat = 0;
            for (Cotizaciondetalle listaVentana1 : this.listaCotizacion) {
                listaVentana1.setCotizacion(this.cotizacionT);
                suma += listaVentana1.getTotal();
                ivat = this.cotizacionT.getPreciototal() * 16 / 100;
                subtota = this.cotizacionT.getPreciototal() - ivat;

                daoCotizacionDetalle.registar(this.session, listaVentana1);
            }
            this.listaVentana = this.listaCotizacion;
            this.cotizacionT.setSubtotal(subtota);
            this.cotizacionT.setIva(ivat);
            this.cotizacionT.setPreciototal(suma);

            daoCotizacion.actualizar(this.session, this.cotizacionT);
            this.cotizacionT.getFechacotizacion();

            HttpSession sesson
                    = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            sesson.setAttribute("idCotizacion", this.cotizacionT.getIdcotizacion());
            this.transaccion.commit();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Se ha registrado la cotizacion"));

            System.out.println("ok se registro");
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
            for (Cotizaciondetalle listaVentana1 : this.listaCotizacion) {
                if (listaVentana1.getNombrepro().equals(nombre)) {
                    this.listaCotizacion.remove(i);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Productos retirado de la lista de ventas"));

                    break;
                }
                i++;
            
            }
               long total = 0;
               for (Cotizaciondetalle listaVentana1 : listaCotizacion) {
                listaVentana1.setTotal(listaVentana1.getCantidad() * listaVentana1.getPrecioventa());
                total = total + listaVentana1.getTotal();

            }
         this.cotizacionT.setPreciototal(total);
         
           this.cotizacionT.setIva(this.cotizacionT.getPreciototal() * 16 / 100);
            this.cotizacionT.setSubtotal(this.cotizacionT.getPreciototal() - this.cotizacionT.getIva());
               RequestContext.getCurrentInstance().update("frmCotizacion:mensajeGeneral");
         

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }
    

    public void calcular() {
         long total = 0;
        try {
            for (Cotizaciondetalle listaVentana1 : listaCotizacion) {
                listaVentana1.setTotal(listaVentana1.getCantidad() * listaVentana1.getPrecioventa());
                total = total + listaVentana1.getTotal();

            }
            this.cotizacionT.setPreciototal(total);
            this.cotizacionT.setIva(this.cotizacionT.getPreciototal() * 16 / 100);
            this.cotizacionT.setSubtotal(this.cotizacionT.getPreciototal() - this.cotizacionT.getIva());

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));

        }
    }

    public List<Cotizaciondetalle> getByIdCotizacion() {
        this.session = null;
        this.transaccion = null;

        try {

            DaoCotizacion daoCotizacion = new DaoCotizacion();
            DaoCotizacionDetalle daoCotizacionDetalle = new DaoCotizacionDetalle();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

            this.cotizacionT = daoCotizacion.getById(this.session, Integer.valueOf(sesson.getAttribute("idCotizacion").toString()));
            List<Cotizaciondetalle> listaCoti = daoCotizacionDetalle.getAllByIdCotizacion(this.session, this.cotizacionT.getIdcotizacion());
            this.transaccion.commit();
            return listaCoti;
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

       public String convertFecha(Date fecha){
        if(fecha!=null){
             SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
             return format.format(fecha);
        }
      return "";  
    }
    
    
    public Cotizacion getCotizacionActual() {
        this.session = null;
        this.transaccion = null;

        try {

            DaoCotizacion daoCotizacion = new DaoCotizacion();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            this.cotizacionT = daoCotizacion.getById(this.session, Integer.valueOf(sesson.getAttribute("idCotizacion").toString()));

            this.transaccion.commit();
            return this.cotizacionT;
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

    public List<Cotizacion> getAll() {
        this.session = null;
        this.transaccion = null;
        try {

            DaoCotizacion daoCotizacion = new DaoCotizacion();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaCot = daoCotizacion.getAll(this.session);
            this.transaccion.commit();
            return this.listaCot;
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

    public void selectCotizacion(int id) {
        this.idCotizacion = id;
    }

    public void searchByDocumento() {
        this.listaCot = new ArrayList<>();
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoCliente daoCliente = new DaoCliente();
            DaoCotizacion daoCotizacion = new DaoCotizacion();
            this.cliente = daoCliente.getByNumeroDocumento(this.session, this.numeroDocumento);
            this.listaCot.addAll(daoCotizacion.getAllByCliente(this.session, this.numeroDocumento));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            this.listaCot = null;
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

    public void limpiarCotiacion() {
        this.cliente = new Cliente();
        this.cotizacionT = new Cotizacion();
        this.listaCotizacion = new ArrayList<>();
        this.listaVentana= new ArrayList<>();

//        invocar el metodo para destivar controles en a factura
        this.disableButton();
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

    public Cotizaciondetalle getCotizacionDetall() {
        return cotizacionDetall;
    }

    public void setCotizacionDetall(Cotizaciondetalle cotizacionDetall) {
        this.cotizacionDetall = cotizacionDetall;
    }

    public ArrayList<Cotizaciondetalle> getListaVentana() {
        return listaVentana;
    }

    public void setListaVentana(ArrayList<Cotizaciondetalle> listaVentana) {
        this.listaVentana = listaVentana;
    }

    public ArrayList<Cotizaciondetalle> getListaCotizacion() {
        return listaCotizacion;
    }

    public void setListaCotizacion(ArrayList<Cotizaciondetalle> listaCotizacion) {
        this.listaCotizacion = listaCotizacion;
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

    public Cotizacion getCotizacionT() {
        return cotizacionT;
    }

    public void setCotizacionT(Cotizacion cotizacionT) {
        this.cotizacionT = cotizacionT;
    }

    public List<Cotizacion> getListaCot() {
        return listaCot;
    }

    public void setListaCot(List<Cotizacion> listaCot) {
        this.listaCot = listaCot;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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

    public String getProductoSelecionado() {
        return productoSelecionado;
    }

    public void setProductoSelecionado(String productoSelecionado) {
        this.productoSelecionado = productoSelecionado;
    }

    public ArrayList<Cotizaciondetalle> getListaCotizacionFiltrado() {
        return listaCotizacionFiltrado;
    }

    public void setListaCotizacionFiltrado(ArrayList<Cotizaciondetalle> listaCotizacionFiltrado) {
        this.listaCotizacionFiltrado = listaCotizacionFiltrado;
    }

  

}
