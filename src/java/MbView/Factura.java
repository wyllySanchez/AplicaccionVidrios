/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import BeanSession.MbLogin;
import Clases.Material;
import Clases.Puerta;
import Clases.Vitrina;
import Daos.DaoCliente;
import Daos.DaoDetalle;
import Daos.DaoEmpleado;
import Daos.DaoFactura;
import Daos.DaoMaterial;
import Daos.DaoProductos;
import Daos.DaoPuertas;
import Daos.DaoVidrio;
import Daos.DaoVitrinas;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import HibernateUtil.HibernateUtil;
import Pojos.Cliente;
import Pojos.Empleado;
import Pojos.Facturas;
import Pojos.Materiales;
import Pojos.Puertas;
import Pojos.Ventanadetalle;
import Pojos.Vitrinas;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author ander
 */
@ManagedBean
@ViewScoped
public class Factura {

    private Session session;
    private Transaction transaccion;
    private Ventanadetalle ventana;
    private ArrayList<Ventanadetalle> listaVentana;
    private ArrayList<Ventanadetalle> listaFactura;
    private ArrayList<Materiales> lista;
    private int tipoVidrio;
    private int tipoVentana;
    private String ancho;
    private String alto;
    private String fondo;
    private int ganancia;
    private int manObra;
    private Facturas factura;
    private List<Facturas> listaFact;
    private List<Facturas> listaFactFiltrado;
    private List<Facturas> listaVentasByFecha;
    private String idCliente;
    private int idEmpleado;
    private long precioVidrio;
    private int idVidrio;
    private Date fechaFin;
    private Date fechaInicio;
    private long totalVentasFecha;
    private int tipoAluminio;
    private int productoTipo;
    private String numeroDocumento;
    private List<Puertas> listaPuertas;
    private int tipoPuerta;
    private List<Vitrinas> listaVitrinas;
    private int tipoVitrina;
    private int idFactura;
    private Cliente cliente;
    private Long montoDinero;
    private Long saldoDevuelta;
    private int tipoEntrepanos;
    private String nombreProducto;
    private long precioTotal;
    private Cliente client1;
    private Vitrina vitrina;

    private Empleado empleado;
    private int codigoCliente;
    private int codigoEmpleado;
    private String correoElectronico;

    private List<Cliente> listaClientes;
    private List<Empleado> listaEmpleado;
    private String productoSelecionado;
    private List<Cliente> listaClienteFiltrado;

    @ManagedProperty("#{mbLogin}")
    private MbLogin mbLogin;

    /**
     * Creates a new instance of VentanaDetalle
     */
    public Factura() {
        this.lista = new ArrayList<>();
        this.listaVentana = new ArrayList<>();
        this.listaFactura = new ArrayList<>();
        this.factura = new Facturas();
        this.ventana = new Ventanadetalle();
        this.listaPuertas = new ArrayList<>();
        this.listaVitrinas = new ArrayList<>();
        this.listaClienteFiltrado = new ArrayList<>();
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
        this.productoTipo = 0;
        this.empleado = new Empleado();
        this.client1 = new Cliente();
        this.listaClientes = new ArrayList<>();
        this.listaEmpleado = new ArrayList<>();

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
//          

            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }
    
            this.listaVentana.add(new Ventanadetalle(null, daoProductos.getById(this.session, codigoVitrina),
                    daoProductos.getById(this.session, codigoVitrina).getNombre() + " " + this.alto + "*" + this.ancho + " fondo: " + this.fondo,
                    1, vitrina.getSumaTotal() + this.precioVidrio, 1 * vitrina.getSumaTotal()
                    + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, this.fondo,daoProductos.getById(this.session, codigoVitrina ).getImgPrinci()));

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

            this.listaVentana.add(new Ventanadetalle(null, daoProductos.getById(this.session, codigoPuerta),
                    daoProductos.getById(this.session, codigoPuerta).getNombre() + " " + this.alto + "*" + this.ancho,
                    1, puertas.getSumaTotal() + this.precioVidrio, 1 * puertas.getSumaTotal()
                    + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, "",daoProductos.getById(this.session, codigoPuerta ).getImgPrinci()));

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
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2) + this.manObra;
                int espacios = String.valueOf(this.precioVidrio).length();
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios - 4));
            }
            if (this.idVidrio == 0) {
                this.precioVidrio = 0;
            }

            if (this.tipoVentana < 4 && this.tipoVentana > 0) {
                this.listaVentana.add(new Ventanadetalle(null, daoProductos.getById(this.session, this.tipoVentana),
                        daoProductos.getById(this.session, this.tipoVentana).getNombre() + " " + this.alto + "*" + this.ancho,
                        1, material.getSumaTotal() + this.precioVidrio, 1 * material.getSumaTotal()
                        + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, "",daoProductos.getById(this.session, this.tipoVentana).getImgPrinci()));

            }
            if (this.tipoVentana == 4) {
                this.listaVentana.add(new Ventanadetalle(null, daoProductos.getById(this.session, this.tipoVentana),
                        daoProductos.getById(this.session, this.tipoVentana).getNombre() + " " + this.alto + "*" + this.ancho,
                        1, this.precioVidrio + this.manObra, 1 * this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, "",daoProductos.getById(this.session, this.tipoVentana).getImgPrinci()));
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

    public void generarFactura() throws Exception {
        this.session = null;
        this.transaccion = null;
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String correoEmple = httpSession.getAttribute("correoElectronico").toString();
        try {
            DaoCliente daoCliente = new DaoCliente();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            DaoFactura daoFactura = new DaoFactura();
            DaoDetalle daoDetalle = new DaoDetalle();

            if (this.cliente == null) {
                System.out.println("codigo del cliente");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Por favor selecione un cliente."));
                return;
            }
            System.out.println(".........3");
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            int idBdFactura = daoFactura.getByUltimoRegistro(this.session).getIdfacturas();
            this.factura.setIdfacturas(idFactura + 1);
            this.factura.setCliente(cliente);
//            this.factura.setEmpleado(empleado);
            this.factura.setEmpleado(daoEmpleado.getByCorreoElectronico(this.session, correoEmple));
            System.out.println(".........4");
            this.factura.setFechaventa(new Date());
            daoFactura.registar(this.session, this.factura);
            System.out.println(".........5");
            this.factura = daoFactura.getByUltimoRegistro(this.session);
            long suma = 0;
            long subtota = 0;
            long ivat = 0;
            for (Ventanadetalle listaVentana1 : this.listaVentana) {
                listaVentana1.setFacturas(this.factura);
                suma += listaVentana1.getTotal();
                ivat = this.factura.getPreciototal() * 16 / 100;
                subtota = this.factura.getPreciototal() - ivat;

//                este metodo es para descontar de la bd los productos por unidades 
//                estos son pra las vitrinas 3 8 9
                if (this.productoTipo == 3) {
                    DaoVitrinas daoVitrinas = new DaoVitrinas();
                    Vitrinas vitrina = new Vitrinas();
                    vitrina = daoVitrinas.getById(this.session, 3);//este el codigo de acoples
                    if (vitrina.getCantidad() >= 8) {
                        vitrina.setCantidad(vitrina.getCantidad() - 8);
                        daoVitrinas.actualizar(this.session, vitrina);
                    }
                    vitrina = daoVitrinas.getById(this.session, 5);//este el codigo de tiañas
                    if (vitrina.getCantidad() >= 8) {
                        vitrina.setCantidad(vitrina.getCantidad() - 8);
                        daoVitrinas.actualizar(this.session, vitrina);
                    }

                    vitrina = daoVitrinas.getById(this.session, 6);//este el codigo de rodamiento piso
                    if (vitrina.getCantidad() >= 4) {
                        vitrina.setCantidad(vitrina.getCantidad() - 4);
                        daoVitrinas.actualizar(this.session, vitrina);
                    }
                    vitrina = daoVitrinas.getById(this.session, 6);//este el codigo de rodamiento ducha
                    if (vitrina.getCantidad() >= 4) {
                        vitrina.setCantidad(vitrina.getCantidad() - 4);
                        daoVitrinas.actualizar(this.session, vitrina);
                        System.out.println("restamos la cantidad de producto");
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "incorrecto", "La Cantidad produtos superara el STOCK"));
                        return;
                    }

                }

                if (this.productoTipo == 8) {
                    DaoVitrinas daoVitrinas = new DaoVitrinas();
                    Vitrinas vitrina = new Vitrinas();
                    vitrina = daoVitrinas.getById(this.session, 12);//este el codigo de acoples
                    if (vitrina.getCantidad() >= 8) {
                        vitrina.setCantidad(vitrina.getCantidad() - 8);
                        daoVitrinas.actualizar(this.session, vitrina);
                    }
                    vitrina = daoVitrinas.getById(this.session, 5);//este el codigo de tiañas
                    if (vitrina.getCantidad() >= 8) {
                        vitrina.setCantidad(vitrina.getCantidad() - 8);
                        daoVitrinas.actualizar(this.session, vitrina);
                    }

                    vitrina = daoVitrinas.getById(this.session, 13);//este el codigo de rodamiento piso
                    if (vitrina.getCantidad() >= 4) {
                        vitrina.setCantidad(vitrina.getCantidad() - 4);
                        daoVitrinas.actualizar(this.session, vitrina);
                        System.out.println("restamos la cantidad de producto");
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "incorrecto", "La Cantidad produtos superara el STOCK"));
                        return;
                    }

                }
                if (this.productoTipo == 9) {
                    DaoVitrinas daoVitrinas = new DaoVitrinas();
                    Vitrinas vitrina = new Vitrinas();
                    vitrina = daoVitrinas.getById(this.session, 12);//este el codigo de acoples
                    if (vitrina.getCantidad() >= 8) {
                        vitrina.setCantidad(vitrina.getCantidad() - 8);
                        daoVitrinas.actualizar(this.session, vitrina);
                    }
                    vitrina = daoVitrinas.getById(this.session, 5);//este el codigo de tiañas
                    if (vitrina.getCantidad() >= 8) {
                        vitrina.setCantidad(vitrina.getCantidad() - 16);
                        daoVitrinas.actualizar(this.session, vitrina);
                    }

                    vitrina = daoVitrinas.getById(this.session, 13);//este el codigo de rodamiento piso
                    if (vitrina.getCantidad() >= 4) {
                        vitrina.setCantidad(vitrina.getCantidad() - 4);
                        daoVitrinas.actualizar(this.session, vitrina);
                        System.out.println("restamos la cantidad de producto");
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "incorrecto", "La Cantidad produtos superara el STOCK"));
                        return;
                    }

                }
                //                este metodo es para descontar de la bd los productos por unidades 
//                estos son pra las puertas 2 4 5 6 7
    if (this.productoTipo == 2 || this.productoTipo==4 || this.productoTipo== 5 || this.productoTipo==6 || this.productoTipo==7) {
                  
                    DaoPuertas daoPuertas = new DaoPuertas();
                    Puertas puertas = new Puertas();
                    puertas = daoPuertas.getById(this.session, 5);//este el codigo de acoples
                    if (puertas.getCantidad() >= 1) {
                        puertas.setCantidad(puertas.getCantidad() - 1);
                        daoPuertas.actualizar(this.session, puertas);
                    }
                      puertas = daoPuertas.getById(this.session, 6);//este el codigo de acoples
                    if (puertas.getCantidad() >= 1) {
                        puertas.setCantidad(puertas.getCantidad() - 1);
                        daoPuertas.actualizar(this.session, puertas);
                    }

                      puertas = daoPuertas.getById(this.session, 7);//este el codigo de acoples
                    if (puertas.getCantidad() >= 2) {
                        puertas.setCantidad(puertas.getCantidad() - 2);
                        daoPuertas.actualizar(this.session, puertas);
                    } 
                       puertas = daoPuertas.getById(this.session, 8);//este el codigo de acoples
                    if (puertas.getCantidad() >= 12) {
                        puertas.setCantidad(puertas.getCantidad() - 12);
                        daoPuertas.actualizar(this.session, puertas);
                    }
                    else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "incorrecto", "La Cantidad produtos superara el STOCK"));
                        return;
                    }

                }
 
 //                este metodo es para descontar de la bd los productos por unidades 
//                estos son pra las ventanas 1 2 3
//                esta es para ventana de 2 cuerpos
         
                    if (this.tipoAluminio == 1) {
              DaoMaterial daoMaterial = new DaoMaterial();
              Materiales materiales = new Materiales();
              
                     materiales = daoMaterial.getById(this.session, 8);//este el codigo de rodamientos
                    if (materiales.getCantidad() >= 2) {
                        materiales.setCantidad(materiales.getCantidad() - 2);
                        daoMaterial.actualizar(this.session, materiales);
                    }
                     materiales = daoMaterial.getById(this.session, 9);//este el codigo de chapa
                    if (materiales.getCantidad() >= 1) {
                        materiales.setCantidad(materiales.getCantidad() - 1);
                        daoMaterial.actualizar(this.session, materiales);
                    }

                    materiales = daoMaterial.getById(this.session, 10);//este el codigo de guias
                    if (materiales.getCantidad() >= 8) {
                        materiales.setCantidad(materiales.getCantidad() - 8);
                        daoMaterial.actualizar(this.session, materiales);
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "incorrecto", "La Cantidad produtos superara el STOCK"));
                        return;
                    }

                }
//                    esta es ventana de 3 cuerpos
                          if (this.tipoAluminio == 2 || this.tipoAluminio ==3) {
              DaoMaterial daoMaterial = new DaoMaterial();
              Materiales materiales = new Materiales();
              
                     materiales = daoMaterial.getById(this.session, 8);//este el codigo de rodamientos 
                    if (materiales.getCantidad() >= 4) {
                        materiales.setCantidad(materiales.getCantidad() - 4);
                        daoMaterial.actualizar(this.session, materiales);
                    }
                     materiales = daoMaterial.getById(this.session, 9);//este el codigo de chapa
                    if (materiales.getCantidad() >= 2) {
                        materiales.setCantidad(materiales.getCantidad() - 2);
                        daoMaterial.actualizar(this.session, materiales);
                    }

                    materiales = daoMaterial.getById(this.session, 10);//este el codigo de guias
                    if (materiales.getCantidad() >= 8) {
                        materiales.setCantidad(materiales.getCantidad() - 8);
                        daoMaterial.actualizar(this.session, materiales);
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "incorrecto", "La Cantidad produtos superara el STOCK"));
                        return;
                    }

                }
                  if (this.tipoAluminio == 3) {
              DaoMaterial daoMaterial = new DaoMaterial();
              Materiales materiales = new Materiales();
              
                     materiales = daoMaterial.getById(this.session, 8);//este el codigo de rodmientos
                    if (materiales.getCantidad() >= 2) {
                        materiales.setCantidad(materiales.getCantidad() - 2);
                        daoMaterial.actualizar(this.session, materiales);
                    }
                     materiales = daoMaterial.getById(this.session, 9);//este el codigo de chapa
                    if (materiales.getCantidad() >= 1) {
                        materiales.setCantidad(materiales.getCantidad() - 1);
                        daoMaterial.actualizar(this.session, materiales);
                    }

                    materiales = daoMaterial.getById(this.session, 10);//este el codigo de guias
                    if (materiales.getCantidad() >= 8) {
                        materiales.setCantidad(materiales.getCantidad() - 8);
                        daoMaterial.actualizar(this.session, materiales);
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "incorrecto", "La Cantidad produtos superara el STOCK"));
                        return;
                    }

                }
                daoDetalle.registar(this.session, listaVentana1);
            }
            this.listaFactura = this.listaVentana;
            this.factura.setSubtotal(subtota);
            this.factura.setIva(ivat);
            this.factura.setPreciototal(suma);

            daoFactura.actualizar(this.session, this.factura);
            this.factura.setFechaventa(new Date());
            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            sesson.setAttribute("idfactura", this.factura.getIdfacturas());

            System.out.println("recuperamos el id de la fact " + getFactura().getIdfacturas());
            this.transaccion.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Se ha registrado "));
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

    public String convertFecha(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(fecha);
        }
        return "";
    }

    public void retirarListaVentaDetalle(String nombre) {
        try {
            int i = 0;
            for (Ventanadetalle listaVentana1 : this.listaVentana) {
                if (listaVentana1.getNombrepro().equals(nombre)) {
                    this.listaVentana.remove(i);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Productos retirado de la lista de ventas"));

                    break;
                }
                i++;
            }
            long total = 0;

            for (Ventanadetalle listaVentana1 : listaVentana) {
                listaVentana1.setTotal(listaVentana1.getCantidad() * listaVentana1.getPrecioventa());
                total = total + listaVentana1.getTotal();

            }
            this.factura.setPreciototal(total);
//            this.factura.setIva(this.factura.getPreciototal() * 16 / 100);
//            this.factura.setSubtotal(this.factura.getPreciototal() - this.factura.getIva());
//            RequestContext.getCurrentInstance().update("frmFactura:tablaProductosFactura frmFactura:gripTotalFactura");
            RequestContext.getCurrentInstance().update("frmFactura:mensajeGeneral");

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    public void calcular() {

        long total = 0;
        try {
            for (Ventanadetalle listaVentana1 : listaVentana) {
                listaVentana1.setTotal(listaVentana1.getCantidad() * listaVentana1.getPrecioventa());
                total = total + listaVentana1.getTotal();

            }
            this.factura.setPreciototal(total);
            this.factura.setIva(this.factura.getPreciototal() * 16 / 100);
            this.factura.setSubtotal(this.factura.getPreciototal() - this.factura.getIva());

//            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));

        }

    }

    public List<Ventanadetalle> getByIdFactura() {
        this.session = null;
        this.transaccion = null;

        try {
            DaoFactura daoFactura = new DaoFactura();
            DaoDetalle daoDetalle = new DaoDetalle();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

            this.factura = daoFactura.getById(this.session, Integer.valueOf(sesson.getAttribute("idfactura").toString()));
            List<Ventanadetalle> listaVenta = daoDetalle.getAllByIdFactura(this.session, this.factura.getIdfacturas());
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

    public Facturas getFacturaActual() {
        this.session = null;
        this.transaccion = null;

        try {
            DaoFactura daoFactura = new DaoFactura();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            this.factura = daoFactura.getById(this.session, Integer.valueOf(sesson.getAttribute("idfactura").toString()));

            this.transaccion.commit();
            return this.factura;
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

    public List<Facturas> getAll() {
        this.session = null;
        this.transaccion = null;
        try {

            DaoFactura daoFactura = new DaoFactura();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaFact = daoFactura.getAll(this.session);
            this.transaccion.commit();
            return this.listaFact;
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

    public List<Facturas> getListVentasByFecha() {
        if (listaVentasByFecha == null) {
            listaVentasByFecha = new ArrayList<>();
        }
        return listaVentasByFecha;
    }

    public List<String> completeText(String query) {
        MbCliente mbCliente = new MbCliente();
        List<Cliente> allClientes = new ArrayList<>();
        allClientes.addAll(mbCliente.getAll());

        List<String> results = new ArrayList<>();

        for (int i = 0; i < allClientes.size(); i++) {
            Cliente clients = allClientes.get(i);

            if (clients.getNumeroDocumentoC().toLowerCase().startsWith(query)) {
                results.add(clients.getNumeroDocumentoC());
            }
        }

        return results;
    }

    public void consultarVentas() {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            DaoFactura daoFactura = new DaoFactura();
            this.listaVentasByFecha = daoFactura.getAllFecha(this.session, this.fechaInicio, this.fechaFin);

            totalVentasFecha = 0;
            for (Facturas facturas : listaVentasByFecha) {
                totalVentasFecha = totalVentasFecha + (facturas.getPreciototal());
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

    public void selectFactura(int id) {
        this.idFactura = id;
    }

    public void searchByDocumento() {
        this.listaFact = new ArrayList<>();
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoCliente daoCliente = new DaoCliente();
            DaoFactura daoFactura = new DaoFactura();
            this.cliente = daoCliente.getByNumeroDocumento(this.session, this.numeroDocumento);

            this.listaFact.addAll(daoFactura.getAllByCliente(this.session, this.numeroDocumento));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            this.listaFact = null;
            this.cliente = null;
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }

    public void calcularDevueltas(int monto, long precioVenta) {

        try {
            this.setMontoDinero(montoDinero);

            saldoDevuelta = saldoDevuelta + (this.montoDinero - this.factura.getPreciototal());

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));

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

    public void limpiarFactura() {
        this.cliente = new Cliente();
        this.empleado = new Empleado();
        this.factura = new Facturas();
        this.listaVentana = new ArrayList<>();
        this.listaFactura = new ArrayList<>();

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

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
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

    public Ventanadetalle getVentana() {
        return ventana;
    }

    public void setVentana(Ventanadetalle ventana) {
        this.ventana = ventana;
    }

    public ArrayList<Ventanadetalle> getListaVentana() {
        return listaVentana;
    }

    public void setListaVentana(ArrayList<Ventanadetalle> listaVentana) {
        this.listaVentana = listaVentana;
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

    public ArrayList<Materiales> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Materiales> lista) {
        this.lista = lista;
    }

    public ArrayList<Ventanadetalle> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(ArrayList<Ventanadetalle> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public List<Facturas> getListaFact() {
        return listaFact;
    }

    public void setListaFact(List<Facturas> listaFact) {
        this.listaFact = listaFact;
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

    public List<Facturas> getListaVentasByFecha() {
        return listaVentasByFecha;
    }

    public void setListaVentasByFecha(List<Facturas> listaVentasByFecha) {
        this.listaVentasByFecha = listaVentasByFecha;
    }

    public List<Facturas> getListaFactFiltrado() {
        return listaFactFiltrado;
    }

    public void setListaFactFiltrado(List<Facturas> listaFactFiltrado) {
        this.listaFactFiltrado = listaFactFiltrado;
    }

    public int getTipoAluminio() {
        return tipoAluminio;
    }

    public void setTipoAluminio(int tipoAluminio) {
        this.tipoAluminio = tipoAluminio;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public int getProductoTipo() {
        return productoTipo;
    }

    public void setProductoTipo(int productoTipo) {
        this.productoTipo = productoTipo;
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

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getMontoDinero() {
        return montoDinero;
    }

    public void setMontoDinero(Long montoDinero) {
        this.montoDinero = montoDinero;
    }

    public Long getSaldoDevuelta() {
        return saldoDevuelta;
    }

    public void setSaldoDevuelta(Long saldoDevuelta) {
        this.saldoDevuelta = saldoDevuelta;
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

    public Cliente getClient1() {
        return client1;
    }

    public void setClient1(Cliente client1) {
        this.client1 = client1;
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

    public MbLogin getMbLogin() {
        return mbLogin;
    }

    public void setMbLogin(MbLogin mbLogin) {
        this.mbLogin = mbLogin;
    }

    public List<Cliente> getListaClienteFiltrado() {
        return listaClienteFiltrado;
    }

    public void setListaClienteFiltrado(List<Cliente> listaClienteFiltrado) {
        this.listaClienteFiltrado = listaClienteFiltrado;
    }

    public Vitrina getVitrina() {
        return vitrina;
    }

    public void setVitrina(Vitrina vitrina) {
        this.vitrina = vitrina;
    }

}
