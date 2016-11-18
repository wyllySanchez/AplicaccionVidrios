package MbView;

import Clases.Material;
import Clases.Puerta;
import Clases.Vitrina;
import Clases.Despiece;
import Daos.DaoMaterial;
import Daos.DaoProductos;
import Daos.DaoPuertas;
import Daos.DaoVidrio;
import Daos.DaoVitrinas;
import HibernateUtil.HibernateUtil;
import Pojos.Materiales;
import Pojos.Puertas;
import Pojos.Vitrinas;
import java.util.ArrayList;
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
public class MbCalcular {

    private String alto;
    private String ancho;
    private String fondo;
    private int ganancia;
    private int manObra;
    private Session session;
    private Transaction transaccion;
    private ArrayList<Materiales> lista;
    private List<Vitrinas> listaVitrinas;
    private int total;
    private int idVidrio;
    private long precioVidrio;
    private int tipoVentana;
    private int precioCuerpo;
    private long precioTotal;
    private String nombreProducto;
    private String nombre;
    private int tipoVitrina;
    private int tipoAluminio;
    private int tipoAluminioPuerta;
    private double recorteTraslape;
    private double recorteEnganche;
    private double recorteAncho;
    private double recorteAlto;
    private double recorte4;
    private double recorteVidrioAncho;
    private double recorteVidrioAlto;
    private int cantidadJamba;
    private int cantidadCabezal;
    private int cantidadSillar;
    private int cantidadTraslape;
    private int cantidadEnganche;
    private int cantidadHorizontalesSuperior;
    private int cantidadHorizontalesInferior;
    private int cantidadVidrio;
    private int cantidadAdactador;
    private double recorteJamba;
    private double recorteCabezal;
    private double recorteSillar;
    private String mensajeHSuperior;
    private String mensajeHInferior;
    private String mensajeCabezal;
    private String mensajeSillar;
    private String mensajeJamba;
    private String mensajeEnganche;
    private String mensajeTraslape;
    private String mensajeAdactador;
    private String mensajeVidrio;
    private String mensajeVidrioPuerta;
    private int canti;
    private String mensajeCanti;
    private long precioTotalCantidad;
    private int tipoProducto;
    private int tipoEntrepanos;
    private ArrayList<Puertas> listaPuertas;
    private int tipoPuerta;
    private double recorteAluminio3MarcoAlto;
    private double recorteAluminio3MarcoAncho;
    private int cantidadMarcoAlto;
    private int cantidadMarcoAncho;
    private int cantidadNaveAlto;
    private int cantidadNaveAncho;
    private int cantidadPisaVidriosAncho;
    private int cantidadPisaVidriosAlto;

    private int cantidadPartidor;
    private int cantidadEntamborado;
    private int cantidadEntamboradoF06;
    private int cantidadEmpaque;
    private int CantidadPerfilU71;
    private int cantidadPibotes;
    private int cantidadVarillasTensoras;
    private int cantidadChapa;
    private int cantidadEscuadras;
    private int visagras;

    private String mensajeAluminio3Ancho;
    private String mensajeAluminio3Alto;
    private String mensajeNaveAlto;
    private String mensajeNaveAncho;
    private String mensajePisaVidriosAncho;
    private String mensajePisavidriosAlto;
    private String mensajePartidor;
    private String mensageEntamborado;
    private String mensageEntamboradoF06;
    private String MensajeEmpaque;
    private String mensajePerfilU71;
    private String mensajeVarillasTensoras;
    private String mensajeChapa;
    private String mensajeEscuadras1;
    private String mensajePibotes;
    private String mensajeVisagras;
    private long precioTotalCant;
    private int cantidadCuartoCirculoAlto;
    private int cantidadCuartoCirculoAncho;
    private int cantidadCuartoCirculoFondo;
    private int cantidadAnguloMediaAlto;
    private int cantidadAnguloMediaAncho;
    private int cantidadAnguloMediaFondo;
    private int cantidadNaveDivisionAncho;
    private int cantidadVidrioEntrepanos;
    private int cantidadPerfilEsquineroUnaAncho;
    private int cntidadPerfilEsquineroUnaFondo;
    private int cantidadTubularUna;
    private int pisaVidrioAlto;
    private int pisaVidrioAncho;
    private int pisaVidrioFondo;
    private double recorteFondo;
    private String mensajeCuartoCirculoAlto;
    private String mensajeCuartoCirculoAncho;
    private String mensajeCuartoCirculoFondo;
    private String mensajeAnguloMediaAlto;
    private String mensajeAnguloMediaAncho;
    private String mensajeAnguloMediaFondo;
    private String mensajeNaveDivisionAncho;
    private String mensajeVidrioAltoAncho;
    private String mensajeVidrioAltoFondo;
    private String mensajeVidrioAnchoFondo;
    private String mensajeEntrepano;
    private String mensajePerfilEsquineroUna;
    private String mensajeTubularUna;
    private String mensajePisavidrioAlto;
    private String mensajePisavidrioAncho;
    private String mensajePisavidrioFondo;
    private List<Despiece> lstDespiece;

    /**
     * Creates a new instance of MbCalcular
     */
    public MbCalcular() {
        lstDespiece = new ArrayList<>();
        this.lista = new ArrayList<>();
        this.listaPuertas = new ArrayList<>();
        this.listaVitrinas = new ArrayList<>();
        this.precioVidrio = 0;
        this.precioTotal = 0;
        this.alto = "";
        this.ancho = "";
        this.fondo = "";
        this.ganancia = 0;
        this.manObra = 0;
        this.idVidrio = 0;
        this.canti = 0;
        this.nombre = "";
        this.nombreProducto = "";
        this.mensajeHSuperior = "";
        this.mensajeHInferior = "";
        this.tipoAluminio = 0;
        this.tipoEntrepanos = 0;
        this.tipoVitrina = 0;
        this.tipoProducto = 0;

    }

    public void tipoProducto() {
        switch (tipoProducto) {
            case 1:
                calcularCostoVentana();
                break;
            case 2:
                calculaCostoPuerta();
                break;
            case 3:
                calculaCostoVitrina();
                break;
        }

    }

    public void calculaCostoVitrina() {
        this.session = null;
        this.transaccion = null;
        Vitrina vitrina = new Vitrina();
        try {
            DaoVitrinas daoVitrinas = new DaoVitrinas();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaVitrinas.addAll(daoVitrinas.getAll(this.session));

            if (this.tipoVitrina == 1) {
                vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                        this.listaVitrinas.get(0).getPreciocot(), this.listaVitrinas.get(1).getPreciocot(),
                        this.listaVitrinas.get(2).getPreciocot(), this.listaVitrinas.get(4).getPreciocot(), this.listaVitrinas.get(5).getPreciocot(),
                        this.listaVitrinas.get(6).getPreciocot(), this.listaVitrinas.get(3).getPreciocot(),
                        this.listaVitrinas.get(7).getPreciocot(),
                        this.listaVitrinas.get(8).getPreciocot(), this.tipoVitrina, 0, 0, 0, 0);
            } else {
                if (this.tipoVitrina == 2) {
                    vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                            0, this.listaVitrinas.get(1).getPreciocot(),
                            0, this.listaVitrinas.get(4).getPreciocot(), 0,
                            this.listaVitrinas.get(6).getPreciocot(), 0, 0,
                            this.listaVitrinas.get(8).getPreciocot(),
                            this.tipoVitrina, this.listaVitrinas.get(9).getPreciocot(), this.listaVitrinas.get(10).getPreciocot(),
                            this.listaVitrinas.get(11).getPreciocot(), this.listaVitrinas.get(12).getPreciocot());

                } else {
                    if (this.tipoVitrina == 3) {
                        vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                                0, this.listaVitrinas.get(1).getPreciocot(),
                                0, this.listaVitrinas.get(4).getPreciocot(), 0,
                                this.listaVitrinas.get(6).getPreciocot(), 0, 0,
                                this.listaVitrinas.get(8).getPreciocot(),
                                this.tipoVitrina, this.listaVitrinas.get(9).getPreciocot(), this.listaVitrinas.get(10).getPreciocot(),
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

                int espacios1 = String.valueOf(this.precioVidrio).length();
                if (String.valueOf(this.precioVidrio).length() > 10) {
                    this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios1 - 5));
                } else {
                    this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios1 - 4));
                }
            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }

            if (this.tipoEntrepanos == 1) {
                this.nombreProducto = "Vitrina de 3 entrepaños " + this.alto + " * " + this.ancho + " Fondo: " + this.fondo;
                this.precioTotal = vitrina.getSumaTotal() + this.precioVidrio;
                this.precioTotalCantidad = this.precioTotal * this.canti;
                this.mensajeCanti = String.valueOf(this.canti);
            } else {
                this.nombreProducto = "Vitrina de 4 entrepaños " + this.alto + " * " + this.ancho + " Fondo: " + this.fondo;
                this.precioTotal = vitrina.getSumaTotal() + this.precioVidrio;
                this.precioTotalCantidad = this.precioTotal * this.canti;
                this.mensajeCanti = String.valueOf(this.canti);
            }

            if (this.tipoVitrina == 1) {
                //Se agregan a la variable despiece
                //a el final se añade a la lista
                //luego cuando le den generar se debe crear un metodo que recorra y valide cual tipo de producto es
                Despiece despie = new Despiece();
                this.recorteAncho = Double.valueOf(this.getAncho());
                this.recorteAlto = Double.valueOf(this.getAlto());
                this.recorteFondo = Double.valueOf(this.getFondo());
                despie.setNombreProducto(nombreProducto);
                despie.setRecorteFondo(Double.valueOf(this.getFondo()));
                despie.setCantidadCuartoCirculoAlto(4 * this.canti);
                despie.setCantidadCuartoCirculoAncho(4 * this.canti);
                despie.setCantidadCuartoCirculoFondo(4 * this.canti);
                despie.setCantidadAnguloMediaAlto(6 * this.canti);
                despie.setCantidadAnguloMediaAncho(6 * this.canti);
                despie.setCantidadAnguloMediaFondo(6 * this.canti);
                despie.setCantidadNaveDivisionAncho(2 * this.canti);
                despie.setCantidadVidrio(2 * this.canti);
                despie.setCantidadVidrioEntrepanos(4 * this.canti);

                despie.setMensajeCuartoCirculoAlto(this.cantidadCuartoCirculoAlto + " Aluminio CuartoCirculo Alto de:" + (this.recorteAlto - 8.0));
                despie.setMensajeCuartoCirculoAncho(this.cantidadCuartoCirculoAncho + "Alumininio CuartoCirculo Ancho de: " + (this.recorteAncho - 8.0));
                despie.setMensajeCuartoCirculoFondo(this.cantidadCuartoCirculoFondo + "Alumininio CuartoCirculo Fondo de: " + (this.recorteFondo - 8.0));
                despie.setMensajeAnguloMediaAlto(this.cantidadAnguloMediaAlto + "  AnguloMedia Alto de " + (this.recorteAlto - 8));
                despie.setMensajeAnguloMediaAncho(this.cantidadAnguloMediaAncho + " AnguloMedia Ancho de:  " + (this.recorteAncho - 10));
                despie.setMensajeAnguloMediaFondo(this.cantidadAnguloMediaFondo + " AnguloMedia Fondo de:  " + (this.recorteFondo - 10));
                despie.setMensajeNaveDivisionAncho(this.cantidadNaveDivisionAncho + "Nave DivisionBano Ancho de:  " + (this.recorteAncho / 2));
                despie.setMensajeVidrioAltoAncho(this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + " recorte ancho  " + (this.recorteAncho - 10.5));
                despie.setMensajeVidrioAltoFondo(this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + "Recorte fondo" + (this.recorteFondo - 10.5));
                despie.setMensajeVidrioAnchoFondo(this.cantidadVidrioEntrepanos + "  Vidrios de 6mm Ancho " + (this.recorteAncho - 1) + "recorte fondo" + (this.recorteFondo - 8.5));

                lstDespiece.add(despie);
            } else {
                if (this.tipoVitrina == 2) {
                    Despiece despie = new Despiece();
                    this.recorteAncho = Double.valueOf(this.getAncho());
                    this.recorteAlto = Double.valueOf(this.getAlto());
                    this.recorteFondo = Double.valueOf(this.getFondo());
                    despie.setNombreProducto(nombreProducto);
                    despie.setCantidadPerfilEsquineroUnaAncho(4 * this.canti);
                    despie.setCntidadPerfilEsquineroUnaFondo(2 * this.canti);
                    despie.setCantidadAnguloMediaAlto(4 * this.canti);
                    despie.setCantidadAnguloMediaAncho(2 * this.canti);
                    despie.setCantidadAnguloMediaFondo(2 * this.canti);
                    despie.setCantidadNaveDivisionAncho(2 * this.canti);
                    despie.setCantidadNaveAncho(2 * this.canti);
                    despie.setCantidadVidrio(2 * this.canti);
                    despie.setPisaVidrioAlto(6 * this.canti);
                    despie.setPisaVidrioAncho(6 * this.canti);
                    despie.setPisaVidrioFondo(6 * this.canti);

                    despie.setMensajePerfilEsquineroUna(this.cantidadPerfilEsquineroUnaAncho + " Perfil o de Una Ancho de: " + (this.recorteAncho));
                    despie.setMensajePerfilEsquineroUna(this.cntidadPerfilEsquineroUnaFondo + " Perfil Esquinero de Una Fondo de: " + (this.recorteFondo));
                    despie.setMensajeAnguloMediaAlto(this.cantidadAnguloMediaAlto + "  Tubular 1 * 1 Alto de " + (this.recorteAlto - 5));
                    despie.setMensajeAnguloMediaAncho(this.cantidadAnguloMediaAncho + " Tubular 1 * 1 Ancho de:  " + (this.recorteAncho - 5));
                    despie.setMensajeAnguloMediaFondo(this.cantidadAnguloMediaFondo + " Tubular 1 * 1 Fondo de:  " + (this.recorteFondo - 5));
                    despie.setMensajePisavidrioAlto(this.pisaVidrioAlto + " Pisavidrio de Media de: " + (this.recorteAlto));
                    despie.setMensajePisavidrioAncho(this.pisaVidrioAncho + " Pisavidrio de Media Alto de: " + (this.recorteAncho));
                    despie.setMensajePisavidrioFondo(this.pisaVidrioFondo + " Pisavidrio de Media Ancho de: " + (this.recorteFondo));
                    despie.setMensajeNaveAncho(this.cantidadNaveDivisionAncho + "Nave DivisionBano Ancho Fondo de:  " + (this.recorteAncho / 2));
                    despie.setMensajeVidrioAltoAncho(this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + " recorte ancho  " + (this.recorteAncho - 10.5));
                    despie.setMensajeVidrioAltoFondo(this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + "Recorte fondo" + (this.recorteFondo - 10.5));
                    despie.setMensajeVidrioAnchoFondo(this.cantidadVidrio + "  Vidrios Ancho " + (this.recorteAncho - 10.5) + "recorte fondo" + (this.recorteFondo - 1));
                    despie.setMensajeVidrioAnchoFondo(this.cantidadVidrioEntrepanos + "  Vidrios de 6mm Ancho " + (this.recorteAncho - 1) + "recorte fondo" + (this.recorteFondo - 1));
                    lstDespiece.add(despie);
                } else {
                    if (this.tipoVitrina == 3) {
                        Despiece despie = new Despiece();
                        this.recorteAncho = Double.valueOf(this.getAncho());
                        this.recorteAlto = Double.valueOf(this.getAlto());
                        this.recorteFondo = Double.valueOf(this.getFondo());
                        despie.setNombreProducto(nombreProducto);
                        despie.setCantidadPerfilEsquineroUnaAncho(2 * this.canti);
                        despie.setCntidadPerfilEsquineroUnaFondo(2 * this.canti);
                        despie.setCantidadAnguloMediaAlto(6 * this.canti);
                        despie.setCantidadAnguloMediaAncho(2 * this.canti);
                        despie.setCantidadAnguloMediaFondo(2 * this.canti);
                        despie.setCantidadNaveDivisionAncho(2 * this.canti);
                        despie.setCantidadNaveAncho(2 * this.canti);
                        despie.setCantidadVidrioEntrepanos(4 * this.canti);
                        despie.setCantidadVidrio(2 * this.canti);
                        despie.setPisaVidrioAlto(6 * this.canti);
                        despie.setPisaVidrioAncho(6 * this.canti);
                        despie.setPisaVidrioFondo(6 * this.canti);

                        despie.setMensajePerfilEsquineroUna(this.cantidadPerfilEsquineroUnaAncho + " Perfil o de Una Ancho de: " + (this.recorteAncho));
                        despie.setMensajePerfilEsquineroUna(this.cntidadPerfilEsquineroUnaFondo + " Perfil Esquinero de Una Fondo de: " + (this.recorteFondo));
                        despie.setMensajeAnguloMediaAlto(this.cantidadAnguloMediaAlto + " AnguloMedia Alto de  " + (this.recorteAlto - 8));
                        despie.setMensajeAnguloMediaAncho(this.cantidadAnguloMediaAncho + " AnguloMedia Ancho de:  " + (this.recorteAncho - 10));
                        despie.setMensajeAnguloMediaFondo(this.cantidadAnguloMediaFondo + " AnguloMedia Fondo de: " + (this.recorteFondo - 10));
                        despie.setMensajePisavidrioAlto(this.pisaVidrioAlto + " Pisavidrio de Media de: " + (this.recorteAlto));
                        despie.setMensajePisavidrioAncho(this.pisaVidrioAncho + " Pisavidrio de Media Alto de: " + (this.recorteAncho));
                        despie.setMensajePisavidrioFondo(this.pisaVidrioFondo + " Pisavidrio de Media Ancho de: " + (this.recorteFondo));
                        despie.setMensajeNaveAncho(this.cantidadNaveDivisionAncho + "Nave DivisionBano Ancho Fondo de:  " + (this.recorteAncho / 2));
                        despie.setMensajeVidrioAltoAncho(this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + " recorte ancho  " + (this.recorteAncho - 10.5));
                        despie.setMensajeVidrioAltoFondo(this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + "Recorte fondo" + (this.recorteFondo - 10.5));
                        despie.setMensajeVidrioAnchoFondo(this.cantidadVidrio + "  Vidrios Ancho " + (this.recorteAncho - 10.5) + "recorte fondo" + (this.recorteFondo - 1));
                        despie.setMensajeVidrioAnchoFondo(this.cantidadVidrioEntrepanos + "  Vidrios de 6mm Ancho " + (this.recorteAncho - 1) + "recorte fondo" + (this.recorteFondo - 1));
                        lstDespiece.add(despie);
                    }
                }
            }

            this.transaccion.commit();

            this.alto = "";
            this.ancho = "";
            this.ganancia = 0;
            this.manObra = 0;
            this.idVidrio = 0;
            this.tipoEntrepanos = 0;
            this.tipoVitrina = 0;
            this.listaVitrinas = new ArrayList<>();
            this.canti = 0;
            this.tipoProducto = 0;

        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }
            System.out.println("Ocurrio un error: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", ex.getCause() + "Por favor contacte con su administrador " + ex.getMessage()));

        } finally {
            if (this.session != null) {
                this.session.close();
            }

        }

    }

    public void calculaCostoPuerta() {
        this.session = null;
        this.transaccion = null;
        try {
            Puerta puertas = new Puerta();
            DaoPuertas daoPuertas = new DaoPuertas();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            this.listaPuertas.addAll(daoPuertas.getAll(this.session));

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
                this.precioVidrio = this.precioVidrio * (puertas.getAlto() * puertas.getAncho());
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
                int espacios = String.valueOf(this.precioVidrio).length();
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios - 4));
            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }

            if (this.tipoPuerta == 6) {
                this.nombreProducto = "Vidrio " + this.alto + " * " + this.ancho;
                this.precioTotal = this.precioVidrio;
            } else {
                this.nombreProducto = "Puerta " + this.alto + " * " + this.ancho;
                this.precioTotal = puertas.getSumaTotal() + this.precioVidrio;
                this.precioTotalCantidad = this.precioTotal * this.canti;
                this.mensajeCanti = String.valueOf(this.canti);

            }

            if (this.tipoPuerta == 1) {
                //Se agregan a la variable despiece
                //a el final se añade a la lista
                //luego cuando le den generar se debe crear un metodo que recorra y valide cual tipo de producto es
                Despiece despie = new Despiece();
                this.recorteAncho = Double.valueOf(this.getAncho());
                this.recorteAlto = Double.valueOf(this.getAlto());
                despie.setCantidadMarcoAncho(1 * this.canti);
                despie.setCantidadMarcoAlto(2 * this.canti);
                despie.setCantidadNaveAlto(2 * this.canti);
                despie.setCantidadNaveAncho(2 * this.canti);
                despie.setCantidadPartidor(1 * this.canti);
                despie.setCantidadPibotes(1 * this.canti);
                despie.setCantidadChapa(1 * this.canti);
                despie.setCantidadVarillasTensoras(2 * this.canti);
                despie.setCantidadEscuadras(12 * this.canti);
                despie.setCantidadPisaVidriosAlto(8 * this.canti);
                despie.setCantidadPisaVidriosAncho(8 * this.canti);
                despie.setCantidadVidrio(2 * this.canti);

                despie.setMensajeAluminio3Alto(this.cantidadMarcoAlto + " Recorte Aluminio 3 media Alto de:" + (this.recorteAlto));
                despie.setMensajeAluminio3Ancho(this.cantidadMarcoAncho + " Recorte Alumininio 3 media Ancho de: " + (this.recorteAncho));
                despie.setMensajeNaveAlto(this.cantidadNaveAlto + " Recorte  AluminiT87 Alto de " + (this.recorteAlto - 5.0));
                despie.setMensajeNaveAncho(this.cantidadNaveAncho + " Recorte AluminioT87 Ancho de:  " + (this.recorteAncho - 8.0));
                despie.setMensajePisaVidriosAncho(this.cantidadPisaVidriosAncho + " Recorte PisaVidrios Ancho de:  " + (this.recorteAncho - 15.6));
                despie.setMensajePisavidrioAlto(this.cantidadPisaVidriosAlto + " Recorte PisaVidrios Alto de:  " + (this.recorteAlto - 19.2));
                despie.setMensajePartidor(this.cantidadPartidor + " Recorte Partidor de:  " + (this.recorteAncho - 15.6));
                despie.setMensajePibotes(this.cantidadPibotes + " Cantidad Pibotes Americanos .");
                despie.setMensajeChapa(this.cantidadChapa + " Cantidad Chapa .");
                despie.setMensajeEscuadras1(this.cantidadEscuadras + "Cantidad Escudras .");
                despie.setMensajeVarillasTensoras(this.cantidadVarillasTensoras + " Cantidad Varillas Tensoras .");
                despie.setMensajeVidrioAltoAncho(this.cantidadVidrio + " Recorte  Vidrio Alto de: " + (this.recorteAlto / 2 - 19.7) + " Recorte VidrioAncho de: " + (this.recorteAncho - 16.1));

            } else {
                if (this.tipoPuerta == 2) {
                    //Se agregan a la variable despiece
                    //a el final se añade a la lista
                    //luego cuando le den generar se debe crear un metodo que recorra y valide cual tipo de producto es
                    Despiece despie = new Despiece();
                    this.recorteAncho = Double.valueOf(this.getAncho());
                    this.recorteAlto = Double.valueOf(this.getAlto());
                    despie.setCantidadMarcoAncho(1 * this.canti);
                    despie.setCantidadMarcoAlto(2 * this.canti);
                    despie.setCantidadNaveAlto(2 * this.canti);
                    despie.setCantidadNaveAncho(2 * this.canti);
                    despie.setCantidadPerfilU71(this.CantidadPerfilU71 = Integer.valueOf(this.getAncho()) / 7 * this.canti);

                    despie.setCantidadPibotes(1 * this.canti);
                    despie.setCantidadChapa(1 * this.canti);
                    despie.setCantidadVarillasTensoras(2 * this.canti);
                    despie.setCantidadEscuadras(12 * this.canti);
                    despie.setCantidadPartidor(1 * this.canti);
                    despie.setCantidadPisaVidriosAlto(4 * this.canti);
                    despie.setCantidadPisaVidriosAncho(4 * this.canti);
                    despie.setMensajeAluminio3Alto(this.cantidadMarcoAlto + " Recorte Aluminio 3 media Alto de:" + (this.recorteAlto));
                    despie.setMensajeAluminio3Ancho(this.cantidadMarcoAncho + " Recorte Alumininio 3 media Ancho de: " + (this.recorteAncho));
                    despie.setMensajeNaveAlto(this.cantidadNaveAlto + "  Recorte AluminiT103 Alto de " + (this.recorteAlto - 5.0));
                    despie.setMensajeNaveAncho(this.cantidadNaveAncho + " Recorte AluminioT103 Ancho de:  " + (this.recorteAncho - 8.0));
                    despie.setMensajePerfilU71(this.CantidadPerfilU71 + " Recorte pefil U71 Alto de:  " + (this.recorteAlto - 20.2));
                    despie.setMensajePibotes(this.cantidadPibotes + " Cantidad Pibotes Americanos .");
                    despie.setMensajeChapa(this.cantidadChapa + " Cantidad Chapa .");
                    despie.setMensajeEscuadras1(this.cantidadEscuadras + " Cantidad Escudras .");
                    despie.setMensajeVarillasTensoras(this.cantidadVarillasTensoras + " Cantidad Varillas Tensoras .");

                } else {
                    if (this.tipoPuerta == 3) {
                        //Se agregan a la variable despiece
                        //a el final se añade a la lista
                        //luego cuando le den generar se debe crear un metodo que recorra y valide cual tipo de producto es
                        Despiece despie = new Despiece();
                        this.recorteAncho = Double.valueOf(this.getAncho());
                        this.recorteAlto = Double.valueOf(this.getAlto());

                        despie.setCantidadMarcoAncho(1 * this.canti);
                        despie.setCantidadMarcoAlto(2 * this.canti);
                        despie.setCantidadNaveAlto(2 * this.canti);
                        despie.setCantidadNaveAncho(2 * this.canti);
                        despie.setCantidadPibotes(1 * this.canti);
                        despie.setCantidadChapa(1 * this.canti);
                        despie.setCantidadVarillasTensoras(2 * this.canti);
                        despie.setCantidadEscuadras(12 * this.canti);
                        despie.setCantidadEntamboradoF06(Integer.valueOf(this.getAncho()) / 7 * this.canti);
                        despie.setCantidadVidrio(2 * this.canti);
                        despie.setMensajeAluminio3Alto(this.cantidadMarcoAlto + " Recorte Aluminio 3 media Alto de:" + (this.recorteAlto));
                        despie.setMensajeAluminio3Ancho(this.cantidadMarcoAncho + " Recorte Alumininio 3 media Ancho de: " + (this.recorteAncho));
                        despie.setMensajeNaveAlto(this.cantidadNaveAlto + "  Recorte AluminiT87 Alto de " + (this.recorteAlto - 3.0));
                        despie.setMensajeNaveAncho(this.cantidadNaveAncho + " Recorte AluminioT87 Ancho de:  " + (this.recorteAncho - 2.0));
                        despie.setMensajePisaVidriosAncho(this.cantidadPisaVidriosAncho + "  Recorte PisaVidrios Ancho de:  " + (this.recorteAncho - 4.5));
                        despie.setMensajePisavidrioAlto(this.cantidadPisaVidriosAlto + " Recorte PisaVidrios Alto de:  " + (this.recorteAlto - 5.0));
                        despie.setMensageEntamboradoF06(this.cantidadEntamboradoF06 + " Recorte Emtamborado F06" + (this.recorteAlto - 12.6));
                        despie.setMensajePibotes(this.cantidadPibotes + " Cantidad Pibotes Americanos .");
                        despie.setMensajeChapa(this.cantidadChapa + " Cantidad Chapa .");
                        despie.setMensajeEscuadras1(this.cantidadEscuadras + " Cantidad Escudras .");
                        despie.setMensajeVarillasTensoras(this.cantidadVarillasTensoras + "Cantidad Varillas Tensoras .");
                    } else {
                        if (this.tipoVentana == 4) {
                            //Se agregan a la variable despiece
                            //a el final se añade a la lista
                            //luego cuando le den generar se debe crear un metodo que recorra y valide cual tipo de producto es
                            Despiece despie = new Despiece();
                            this.recorteAncho = Double.valueOf(this.getAncho());
                            this.recorteAlto = Double.valueOf(this.getAlto());
                            despie.setCantidadMarcoAncho(1 * this.canti);
                            despie.setCantidadMarcoAlto(2 * this.canti);
                            despie.setCantidadNaveAlto(2 * this.canti);
                            despie.setCantidadNaveAncho(2 * this.canti);
                            despie.setCantidadPisaVidriosAlto(4 * this.canti);
                            despie.setCantidadPisaVidriosAncho(8 * this.canti);
                            despie.setCantidadPartidor(1 * this.canti);
                            despie.setCantidadPibotes(1 * this.canti);
                            despie.setCantidadChapa(1 * this.canti);
                            despie.setCantidadVarillasTensoras(2 * this.canti);
                            despie.setCantidadEscuadras(12 * this.canti);
                            despie.setMensajeAluminio3Alto(this.cantidadMarcoAlto + " Recorte Aluminio 3 media Alto de:" + (this.recorteAlto));
                            despie.setMensajeAluminio3Ancho(this.cantidadMarcoAncho + " Recorte Alumininio 3 media Ancho de: " + (this.recorteAncho));
                            despie.setMensajeNaveAlto(this.cantidadNaveAlto + " Recorte  AluminiT103  Alto de " + (this.recorteAlto - 5.0));
                            despie.setMensajeNaveAncho(this.cantidadNaveAncho + " Recorte AluminioT103 de:  " + (this.recorteAncho - 8.0));
                            despie.setMensajePartidor(this.cantidadPartidor + " Recorte Partidor Aluminio T103 de: " + (this.recorteAncho - 23.6));
                            despie.setMensajePisaVidriosAncho(this.cantidadPisaVidriosAncho + "  Recorte PisaVidrios Ancho de:  " + (this.recorteAncho - 23.6));
                            despie.setMensajePisavidrioAlto(this.cantidadPisaVidriosAlto + " Recorte PisaVidrios Alto de:  " + (this.recorteAlto - 20.6));
                            despie.setMensajePibotes(this.cantidadPibotes + "  Cantidad Pibotes Americanos .");
                            despie.setMensajeChapa(this.cantidadChapa + " Cantidad Chapa .");
                            despie.setMensajeEscuadras1(this.cantidadEscuadras + " Cantidad Escudras .");
                            despie.setMensajeVarillasTensoras(this.cantidadVarillasTensoras + " Cantidad Varillas Tensoras .");
                            this.recorteVidrioAncho = Integer.valueOf(this.getAncho()) - 24.1;
                            this.recorteVidrioAlto = Integer.valueOf(this.getAlto()) - 21.1;
                            this.recorteVidrioAlto = this.recorteVidrioAlto / 2;

                        } else {
                            if (this.tipoPuerta == 5) {
                                //Se agregan a la variable despiece
                                //a el final se añade a la lista
                                //luego cuando le den generar se debe crear un metodo que recorra y valide cual tipo de producto es
                                Despiece despie = new Despiece();
                                this.recorteAncho = Double.valueOf(this.getAncho());
                                this.recorteAlto = Double.valueOf(this.getAlto());
                                despie.setCantidadMarcoAncho(1 * this.canti);
                                despie.setCantidadMarcoAlto(2 * this.canti);
                                despie.setCantidadNaveAlto(2 * this.canti);
                                despie.setCantidadNaveAncho(2 * this.canti);

                                despie.setCantidadPisaVidriosAlto(4 * this.canti);
                                despie.setCantidadPisaVidriosAncho(8 * this.canti);
                                despie.setCantidadPartidor(1 * this.canti);

                                despie.setVisagras(2 * this.canti);
                                despie.setCantidadChapa(1 * this.canti);
                                despie.setCantidadVarillasTensoras(2 * this.canti);
                                despie.setCantidadEscuadras(12 * this.canti);
                                despie.setMensajeAluminio3Alto(this.cantidadMarcoAlto + " Recorte Aluminio 3 media Alto de:" + (this.recorteAlto));
                                despie.setMensajeAluminio3Ancho(this.cantidadMarcoAncho + " Recorte Alumininio 3 media Ancho de: " + (this.recorteAncho));
                                despie.setMensajeNaveAlto(this.cantidadNaveAlto + "  Recorte AluminioT103  Alto de " + (this.recorteAlto - 5.0));
                                despie.setMensajeNaveAncho(this.cantidadNaveAncho + " Recorte AluminioT103  Ancho de:  " + (this.recorteAncho - 8.0));
                                despie.setMensajePartidor(this.cantidadPartidor + " Recorte Partidor Aluminio T103 Ancho de: " + (this.recorteAncho - 23.6));
                                despie.setMensajePisaVidriosAncho(this.cantidadPisaVidriosAncho + " Recorte PisaVidrios Ancho de:  " + (this.recorteAncho - 23.6));
                                despie.setMensajePisavidrioAlto(this.cantidadPisaVidriosAlto + " Recorte PisaVidrios Alto de:  " + (this.recorteAlto - 20.6));
                                despie.setMensajeVisagras(this.visagras + " Cantidad Visagras .");
                                despie.setMensajeChapa(this.cantidadChapa + " Cantidad Chapa .");
                                despie.setMensajeEscuadras1(this.cantidadEscuadras + " Cantidad Escudras .");
                                despie.setMensajeVarillasTensoras(this.cantidadVarillasTensoras + "Cantidad Varillas Tensoras .");
                                this.recorteVidrioAncho = Integer.valueOf(this.getAncho()) - 24.1;
                                this.recorteVidrioAlto = Integer.valueOf(this.getAlto()) - 21.1;
                                this.recorteVidrioAlto = this.recorteVidrioAlto / 2;

                            }
                        }

                    }
                }
            }

            this.precioVidrio = 0;
            this.alto = "";
            this.ancho = "";
            this.ganancia = 0;
            this.manObra = 0;
            this.idVidrio = 0;
            this.tipoProducto = 0;
            this.tipoPuerta = 0;
            this.canti = 0;

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

    public void calcularCostoVentana() {
        this.mensajeHSuperior = "";
        this.session = null;
        this.transaccion = null;
        try {
            Material material = new Material();
            DaoMaterial daoMaterial = new DaoMaterial();
            DaoProductos daoProductos = new DaoProductos();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            this.lista.addAll(daoMaterial.getAll(this.session));

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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio del vidrio db es : " + this.precioVidrio));
                this.precioVidrio = this.precioVidrio * (material.getAlto() * material.getAncho());
                int espacios1 = String.valueOf(this.precioVidrio).length();
                if (String.valueOf(this.precioVidrio).length() > 10) {
                    this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios1 - 5));
                } else {
                    this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios1 - 4));
                }

                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }

            if (this.tipoVentana == 4) {
                this.nombreProducto = "Vidrio " + this.alto + " * " + this.ancho;
                this.precioTotal = this.precioVidrio + manObra;
                this.precioTotalCantidad = this.precioTotal * this.canti;
                this.mensajeCanti = String.valueOf(this.canti);
            } else {
                this.nombreProducto = "ventana " + this.alto + " * " + this.ancho;
                this.precioTotal = material.getSumaTotal() + this.precioVidrio;
                this.precioTotalCantidad = this.precioTotal * this.canti;
                this.mensajeCanti = String.valueOf(this.canti);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio1 del vidrio es: " + this.precioVidrio));
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio del vidrio es: " + String.valueOf(this.precioVidrio).substring(0, espacios-4)));

//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El alto  es: " + material.getAlto()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "el ancho es: " + material.getAncho()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio empaque es: " + material.getEmpaque()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio guias es: " + material.getGuias()));
//
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio cabezal es: " + material.getPrecioCabezal()));
//
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio enganche es: " + material.getPrecioEnganche()));
//
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio traslape es: " + material.getPrecioTraslape()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio hinferior es: " + material.getPrecioHinferior()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio hsuperior es: " + material.getPrecioHsuperior()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio sillar es: " + material.getPrecioSillar()));
//
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio jamba es: " + material.getPrecioJamba()));
//            
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El total sin aumento: " + material.getSubTotal()));
//         
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Total de los Materiales es: " + material.getSumaTotal()));

            if (this.tipoVentana == 1) {
                //Se agregan a la variable despiece
                //a el final se añade a la lista
                //luego cuando le den generar se debe crear un metodo que recorra y valide cual tipo de producto es
                Despiece despie = new Despiece();
                this.recorteAncho = Double.valueOf(this.getAncho()) / 2;
                this.recorteAlto = Double.valueOf(this.getAlto()) / 2;
                despie.setCantidadCabezal(1 * this.canti);
                despie.setCantidadSillar(1 * this.canti);
                despie.setCantidadJamba(2 * this.canti);
                despie.setCantidadEnganche(2 * this.canti);
                despie.setCantidadEnganche(2 * this.canti);
                despie.setCantidadTraslape(2 * this.canti);
                despie.setCantidadTraslape(2 * this.canti);
                despie.setCantidadHorizontalesSuperior(2 * this.canti);
                despie.setCantidadHorizontalesInferior(2 * this.canti);
                this.cantidadVidrio = 2 * this.canti;
                despie.setMensajeCabezal(this.cantidadCabezal + " Recorte Cabeazal de:  " + (this.recorteAncho * 2));
                despie.setMensajeSillar(this.cantidadSillar + "  RecorteSillar de: " + (this.recorteAncho * 2));
                despie.setMensajeJamba(this.cantidadJamba + " Recorte Jamba de " + (this.recorteAlto + this.recorteAlto - 1.3));
                despie.setMensajeEnganche(this.cantidadEnganche + " Recorte Enganchez de:  " + (this.recorteAlto + this.recorteAlto - 2.5));
                despie.setMensajeTraslape(this.cantidadTraslape + " Recorte traslapes de:  " + (this.recorteAlto + recorteAlto - 2.5));
                despie.setMensajeHSuperior(this.cantidadHorizontalesSuperior + " Recorte Horizontales Superior de:  " + this.recorteAncho);
                despie.setMensajeHInferior(this.cantidadHorizontalesInferior + " Recorte Horizontales Inferior de:  " + this.recorteAncho);
                despie.setMensajeVidrio(this.cantidadVidrio + " Recorte Vidrio Alto de: " + (this.recorteAlto * 2 - 5) + "  Recorte VidrioAncho de: " + (this.recorteAncho * 2 - 5.5));

            } else {
                if (this.tipoVentana == 2) {
                    //Se agregan a la variable despiece
                    //a el final se añade a la lista
                    //luego cuando le den generar se debe crear un metodo que recorra y valide cual tipo de producto es
                    Despiece despie = new Despiece();
                    this.recorteAncho = Double.valueOf(this.getAncho()) / 2;
                    this.recorteAlto = Double.valueOf(this.getAlto()) / 2;
                    despie.setCantidadCabezal(1 * this.canti);
                    despie.setCantidadSillar(1 * this.canti);
                    despie.setCantidadJamba(2 * this.canti);
                    despie.setCantidadEnganche(2 * this.canti);
                    despie.setCantidadTraslape(4 * this.canti);
                    despie.setCantidadHorizontalesSuperior(2 * this.canti);
                    despie.setCantidadHorizontalesInferior(2 * this.canti);
                    despie.setMensajeCabezal(this.cantidadCabezal + "Recorte Cabeazal de:  " + (this.recorteAncho * 2));
                    despie.setMensajeSillar(this.cantidadSillar + " Recorte Sillar de: " + (this.recorteAncho * 2));;
                    despie.setMensajeJamba(this.cantidadJamba + " Recorte Jamba de " + (this.recorteAlto + this.recorteAlto - 1.3));
                    despie.setMensajeEnganche(this.cantidadEnganche + " Recorte Enganchez de:  " + (this.recorteAlto + this.recorteAlto - 2.5));
                    despie.setMensajeTraslape(this.cantidadTraslape + " Recortetraslapes de:  " + (this.recorteAlto + recorteAlto - 2.5));

                    despie.setCantidadHorizontalesSuperior(3 * this.canti);
                    despie.setCantidadHorizontalesInferior(3 * this.canti);
                    despie.setMensajeHSuperior(this.cantidadHorizontalesSuperior - 1 + "Recorte Horizontales Superior de:  " + (this.recorteAncho / 2) + "  y 1 horizontal Superior de: " + this.recorteAncho);
                    despie.setMensajeHInferior(this.cantidadHorizontalesInferior - 1 + " Recorte Horizontales Inferior de:  " + (this.recorteAncho / 2) + "  y 1 horizontal Inferior de: " + this.recorteAncho);
                    despie.setMensajeVidrio(this.cantidadVidrio + " Recorte Vidrio Alto de: " + (this.recorteAlto * 2 - 5) + " Recorte VidrioAncho de: " + (this.recorteAncho * 2 - 5.5));

                } else {
                    if (this.tipoVentana == 3) {
                        //Se agregan a la variable despiece
                        //a el final se añade a la lista
                        //luego cuando le den generar se debe crear un metodo que recorra y valide cual tipo de producto es
                        Despiece despie = new Despiece();
                        this.recorteAncho = Double.valueOf(this.getAncho()) / 4;
                        this.recorteAlto = Double.valueOf(this.getAlto()) / 4;

                        despie.setCantidadCabezal(1 * this.canti);
                        despie.setCantidadSillar(1 * this.canti);
                        despie.setCantidadJamba(2 * this.canti);
                        despie.setCantidadEnganche(4 * this.canti);

                        despie.setCantidadTraslape(4 * this.canti);
                        despie.setCantidadHorizontalesSuperior(4 * this.canti);
                        despie.setCantidadHorizontalesInferior(4 * this.canti);
                        despie.setCantidadAdactador(1 * this.canti);

                        despie.setMensajeCabezal(this.cantidadCabezal + "Recorte Cabeazal de:  " + (this.recorteAncho * 2));
                        despie.setMensajeSillar(this.cantidadSillar + "Recorte Sillar de: " + (this.recorteAncho * 2));
                        despie.setMensajeJamba(this.cantidadJamba + " Recorte Jamba de " + (this.recorteAlto + this.recorteAlto + this.recorteAlto + this.recorteAlto - 1.3));
                        despie.setMensajeEnganche(this.cantidadEnganche + "Recorte Enganchez de:  " + (this.recorteAlto + this.recorteAlto + this.recorteAlto + this.recorteAlto - 2.5));
                        despie.setMensajeTraslape(this.cantidadTraslape + "Recorte traslapes de:  " + (this.recorteAlto + recorteAlto + this.recorteAlto + this.recorteAlto - 2.5));
                        despie.setMensajeHSuperior(this.cantidadHorizontalesSuperior + "Recorte Horizontales Superior de:  " + this.recorteAncho);
                        despie.setMensajeHInferior(this.cantidadHorizontalesInferior + "Recorte Horizontales Inferior de:  " + this.recorteAncho);
                        despie.setMensajeHSuperior(this.cantidadHorizontalesSuperior + "Recorte Horizontales de:  " + this.recorteAncho);
                        despie.setMensajeHInferior(this.cantidadHorizontalesInferior + "Recorte Horizontales de:  " + this.recorteAncho);
                        despie.setMensajeAdactador(this.cantidadAdactador + " Adaptador de: " + (this.recorteAlto - 2.5));
                        despie.setMensajeVidrio(this.cantidadVidrio + " Recorte Vidrio Alto de: " + (this.recorteAlto * 2 - 5) + " Recorte VidrioAncho de: " + (this.recorteAncho * 2 - 5.5));

                    } else {
                        if (this.tipoVentana == 4) {
                            //Se agregan a la variable despiece
                            //a el final se añade a la lista
                            //luego cuando le den generar se debe crear un metodo que recorra y valide cual tipo de producto es
                            Despiece despie = new Despiece();
                            this.recorteAncho = 0;
                            this.recorteAlto = 0;

                            this.cantidadCabezal = 0;
                            this.cantidadSillar = 0;
                            this.cantidadJamba = 0;
                            this.cantidadEnganche = 0;
                            this.cantidadTraslape = 0;
                            despie.setCantidadHorizontalesSuperior(0);
                            despie.setCantidadHorizontalesInferior(0);
                            this.cantidadAdactador = 1 * this.canti;
                            despie.setMensajeCabezal(this.cantidadCabezal + " Recorte Cabeazal de:  ");
                            despie.setMensajeSillar(this.cantidadSillar + "Recorte  Sillar de: ");
                            despie.setMensajeJamba(this.cantidadJamba + " Recorte  Jamba de ");
                            despie.setMensajeEnganche(this.cantidadEnganche + "Recorte  Enganchez de:  ");
                            despie.setMensajeTraslape(this.cantidadTraslape + " Recorte traslapes de:  ");
                            despie.setMensajeHSuperior(this.cantidadHorizontalesSuperior + " Recorte Horizontales Superior de:  ");
                            despie.setMensajeHInferior(this.cantidadHorizontalesInferior + " Recorte Horizontales Inferior de:  ");
                            despie.setMensajeHSuperior(this.cantidadHorizontalesSuperior + " Recorte Horizontales de:  ");
                            despie.setMensajeHInferior(this.cantidadHorizontalesInferior + " Recorte Horizontales de:  ");
                            despie.setMensajeAdactador(this.cantidadAdactador + "Recorte  Adaptador de: ");

                            despie.setMensajeVidrio(this.cantidadVidrio + " Recorte Vidrio Alto de: ");

                        }
                    }
                }
            }

            this.precioVidrio = 0;
            this.alto = "";
            this.ancho = "";
            this.ganancia = 0;
            this.manObra = 0;
            this.idVidrio = 0;
            this.canti = 0;
            this.tipoVentana = 0;
            this.tipoAluminio = 0;
            this.tipoProducto = 0;
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

//     public void limpiarCotizacion() {
////        this.mensageEntamborado = "";
////        this.mensageEntamboradoF06="";
////        this.MensajeEmpaque="";
////        this.mensajeAdactador="";
////        this.mensajeAluminio3Alto="";
////        this.mensajeAluminio3Ancho="";
////        this.mensajeAnguloMediaFondo="";
////        this.mensajeCabezal="";
////        this.mensajeChapa="";
////        this.mensajeCuartoCirculoAlto="";
////        this.mensajeCuartoCirculoAncho="";
////        this.mensajeCuartoCirculoFondo="";
////        this.mensajeEnganche="";
////        this.mensajeEntrepano="";
////        this.mensajeEntrepano="";
////        this.mensajeEscuadras1="";
////        this.mensajeHInferior="";
////        this.mensajeHSuperior="";
////        this.mensajeJamba="";
////        this.mensajeNaveAlto="";
////        this.mensajeNaveAncho="";
////        this.mensajeNaveDivisionAncho="";
////        this.mensajePartidor="";
////        this.mensajePerfilEsquineroUna="";
////        this.mensajePerfilU71="";
////        this.mensajePibotes="";
////        this.mensajePisaVidriosAncho="";
////        this.mensajePisavidrioAlto="";
////        this.mensajePisaVidriosAncho="";
////        this.mensajePisavidrioFondo="";
////        this.mensajeSillar="";
////        this.mensajeTraslape="";
////        this.mensajeTubularUna="";
////        this.mensajeVarillasTensoras="";
////        this.mensajeVidrio="";
////        this.mensajeVidrioAltoAncho="";
////        this.mensajeVidrioAnchoFondo="";
////        this.mensajeVidrioAltoFondo="";
////        this.mensajeVidrioPuerta="";
////        this.mensajeVisagras="";
////        this.nombreProducto="";
////        this.precioTotal=0;
////        this.mensajeCanti="";
////        this.precioTotalCantidad=0;
//       
//
////        invocar el metodo para destivar controles en a factura
//        this.disableButton();
//      
//    }
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

    public ArrayList<Materiales> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Materiales> lista) {
        this.lista = lista;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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

    public int getIdVidrio() {
        return idVidrio;
    }

    public void setIdVidrio(int idVidrio) {
        this.idVidrio = idVidrio;
    }

    public int getTipoVentana() {
        return tipoVentana;
    }

    public void setTipoVentana(int tipoVentana) {
        this.tipoVentana = tipoVentana;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getPrecioVidrio() {
        return precioVidrio;
    }

    public void setPrecioVidrio(long precioVidrio) {
        this.precioVidrio = precioVidrio;
    }

    public int getPrecioCuerpo() {
        return precioCuerpo;
    }

    public void setPrecioCuerpo(int precioCuerpo) {
        this.precioCuerpo = precioCuerpo;
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

    public double getRecorteTraslape() {
        return recorteTraslape;
    }

    public void setRecorteTraslape(double recorteTraslape) {
        this.recorteTraslape = recorteTraslape;
    }

    public double getRecorteEnganche() {
        return recorteEnganche;
    }

    public void setRecorteEnganche(double recorteEnganche) {
        this.recorteEnganche = recorteEnganche;
    }

    public double getRecorteAncho() {
        return recorteAncho;
    }

    public void setRecorteAncho(double recorteAncho) {
        this.recorteAncho = recorteAncho;
    }

    public double getRecorteAlto() {
        return recorteAlto;
    }

    public void setRecorteAlto(double recorteAlto) {
        this.recorteAlto = recorteAlto;
    }

    public double getRecorte4() {
        return recorte4;
    }

    public void setRecorte4(double recorte4) {
        this.recorte4 = recorte4;
    }

    public double getRecorteVidrioAncho() {
        return recorteVidrioAncho;
    }

    public void setRecorteVidrioAncho(double recorteVidrioAncho) {
        this.recorteVidrioAncho = recorteVidrioAncho;
    }

    public double getRecorteVidrioAlto() {
        return recorteVidrioAlto;
    }

    public void setRecorteVidrioAlto(double recorteVidrioAlto) {
        this.recorteVidrioAlto = recorteVidrioAlto;
    }

    public int getCantidadHorizontalesSuperior() {
        return cantidadHorizontalesSuperior;
    }

    public void setCantidadHorizontalesSuperior(int cantidadHorizontalesSuperior) {
        this.cantidadHorizontalesSuperior = cantidadHorizontalesSuperior;
    }

    public int getCantidadHorizontalesInferior() {
        return cantidadHorizontalesInferior;
    }

    public void setCantidadHorizontalesInferior(int cantidadHorizontalesInferior) {
        this.cantidadHorizontalesInferior = cantidadHorizontalesInferior;
    }

    public double getRecorteCabezal() {
        return recorteCabezal;
    }

    public void setRecorteCabezal(double recorteCabezal) {
        this.recorteCabezal = recorteCabezal;
    }

    public double getRecorteSillar() {
        return recorteSillar;
    }

    public void setRecorteSillar(double recorteSillar) {
        this.recorteSillar = recorteSillar;
    }

    public double getRecorteJamba() {
        return recorteJamba;
    }

    public void setRecorteJamba(double recorteJamba) {
        this.recorteJamba = recorteJamba;
    }

    public String getMensajeHSuperior() {
        return mensajeHSuperior;
    }

    public void setMensajeHSuperior(String mensajeHSuperior) {
        this.mensajeHSuperior = mensajeHSuperior;
    }

    public String getMensajeHInferior() {
        return mensajeHInferior;
    }

    public void setMensajeHInferior(String mensajeHInferior) {
        this.mensajeHInferior = mensajeHInferior;
    }

    public int getCantidadJamba() {
        return cantidadJamba;
    }

    public void setCantidadJamba(int cantidadJamba) {
        this.cantidadJamba = cantidadJamba;
    }

    public int getCantidadCabezal() {
        return cantidadCabezal;
    }

    public void setCantidadCabezal(int cantidadCabezal) {
        this.cantidadCabezal = cantidadCabezal;
    }

    public int getCantidadSillar() {
        return cantidadSillar;
    }

    public void setCantidadSillar(int cantidadSillar) {
        this.cantidadSillar = cantidadSillar;
    }

    public int getCantidadTraslape() {
        return cantidadTraslape;
    }

    public void setCantidadTraslape(int cantidadTraslape) {
        this.cantidadTraslape = cantidadTraslape;
    }

    public int getCantidadEnganche() {
        return cantidadEnganche;
    }

    public void setCantidadEnganche(int cantidadEnganche) {
        this.cantidadEnganche = cantidadEnganche;
    }

    public int getCantidadVidrio() {
        return cantidadVidrio;
    }

    public void setCantidadVidrio(int cantidadVidrio) {
        this.cantidadVidrio = cantidadVidrio;
    }

    public String getMensajeCabezal() {
        return mensajeCabezal;
    }

    public void setMensajeCabezal(String mensajeCabezal) {
        this.mensajeCabezal = mensajeCabezal;
    }

    public String getMensajeSillar() {
        return mensajeSillar;
    }

    public void setMensajeSillar(String mensajeSillar) {
        this.mensajeSillar = mensajeSillar;
    }

    public String getMensajeJamba() {
        return mensajeJamba;
    }

    public void setMensajeJamba(String mensajeJamba) {
        this.mensajeJamba = mensajeJamba;
    }

    public String getMensajeEnganche() {
        return mensajeEnganche;
    }

    public void setMensajeEnganche(String mensajeEnganche) {
        this.mensajeEnganche = mensajeEnganche;
    }

    public String getMensajeTraslape() {
        return mensajeTraslape;
    }

    public void setMensajeTraslape(String mensajeTraslape) {
        this.mensajeTraslape = mensajeTraslape;
    }

    public int getCantidadAdactador() {
        return cantidadAdactador;
    }

    public void setCantidadAdactador(int cantidadAdactador) {
        this.cantidadAdactador = cantidadAdactador;
    }

    public String getMensajeAdactador() {
        return mensajeAdactador;
    }

    public void setMensajeAdactador(String mensajeAdactador) {
        this.mensajeAdactador = mensajeAdactador;
    }

    public int getCanti() {
        return canti;
    }

    public void setCanti(int canti) {
        this.canti = canti;
    }

    public String getMensajeCanti() {
        return mensajeCanti;
    }

    public void setMensajeCanti(String mensajeCanti) {
        this.mensajeCanti = mensajeCanti;
    }

    public long getPrecioTotalCantidad() {
        return precioTotalCantidad;
    }

    public void setPrecioTotalCantidad(long precioTotalCantidad) {
        this.precioTotalCantidad = precioTotalCantidad;
    }

    public String getMensajeVidrio() {
        return mensajeVidrio;
    }

    public void setMensajeVidrio(String mensajeVidrio) {
        this.mensajeVidrio = mensajeVidrio;
    }

    public int getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(int tipoProducto) {
        this.tipoProducto = tipoProducto;
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

    public ArrayList<Puertas> getListaPuertas() {
        return listaPuertas;
    }

    public void setListaPuertas(ArrayList<Puertas> listaPuertas) {
        this.listaPuertas = listaPuertas;
    }

    public int getTipoPuerta() {
        return tipoPuerta;
    }

    public void setTipoPuerta(int tipoPuerta) {
        this.tipoPuerta = tipoPuerta;
    }

    public double getRecorteAluminio3MarcoAlto() {
        return recorteAluminio3MarcoAlto;
    }

    public void setRecorteAluminio3MarcoAlto(double recorteAluminio3MarcoAlto) {
        this.recorteAluminio3MarcoAlto = recorteAluminio3MarcoAlto;
    }

    public double getRecorteAluminio3MarcoAncho() {
        return recorteAluminio3MarcoAncho;
    }

    public void setRecorteAluminio3MarcoAncho(double recorteAluminio3MarcoAncho) {
        this.recorteAluminio3MarcoAncho = recorteAluminio3MarcoAncho;
    }

    public int getCantidadMarcoAlto() {
        return cantidadMarcoAlto;
    }

    public void setCantidadMarcoAlto(int cantidadMarcoAlto) {
        this.cantidadMarcoAlto = cantidadMarcoAlto;
    }

    public int getCantidadMarcoAncho() {
        return cantidadMarcoAncho;
    }

    public void setCantidadMarcoAncho(int cantidadMarcoAncho) {
        this.cantidadMarcoAncho = cantidadMarcoAncho;
    }

    public int getCantidadNaveAlto() {
        return cantidadNaveAlto;
    }

    public void setCantidadNaveAlto(int cantidadNaveAlto) {
        this.cantidadNaveAlto = cantidadNaveAlto;
    }

    public int getCantidadNaveAncho() {
        return cantidadNaveAncho;
    }

    public void setCantidadNaveAncho(int cantidadNaveAncho) {
        this.cantidadNaveAncho = cantidadNaveAncho;
    }

    public int getCantidadPisaVidriosAncho() {
        return cantidadPisaVidriosAncho;
    }

    public void setCantidadPisaVidriosAncho(int cantidadPisaVidriosAncho) {
        this.cantidadPisaVidriosAncho = cantidadPisaVidriosAncho;
    }

    public int getCantidadPisaVidriosAlto() {
        return cantidadPisaVidriosAlto;
    }

    public void setCantidadPisaVidriosAlto(int cantidadPisaVidriosAlto) {
        this.cantidadPisaVidriosAlto = cantidadPisaVidriosAlto;
    }

    public int getCantidadPartidor() {
        return cantidadPartidor;
    }

    public void setCantidadPartidor(int cantidadPartidor) {
        this.cantidadPartidor = cantidadPartidor;
    }

    public int getCantidadEntamborado() {
        return cantidadEntamborado;
    }

    public void setCantidadEntamborado(int cantidadEntamborado) {
        this.cantidadEntamborado = cantidadEntamborado;
    }

    public int getCantidadEntamboradoF06() {
        return cantidadEntamboradoF06;
    }

    public void setCantidadEntamboradoF06(int cantidadEntamboradoF06) {
        this.cantidadEntamboradoF06 = cantidadEntamboradoF06;
    }

    public int getCantidadEmpaque() {
        return cantidadEmpaque;
    }

    public void setCantidadEmpaque(int cantidadEmpaque) {
        this.cantidadEmpaque = cantidadEmpaque;
    }

    public int getCantidadPerfilU71() {
        return CantidadPerfilU71;
    }

    public void setCantidadPerfilU71(int CantidadPerfilU71) {
        this.CantidadPerfilU71 = CantidadPerfilU71;
    }

    public int getCantidadPibotes() {
        return cantidadPibotes;
    }

    public void setCantidadPibotes(int cantidadPibotes) {
        this.cantidadPibotes = cantidadPibotes;
    }

    public int getCantidadVarillasTensoras() {
        return cantidadVarillasTensoras;
    }

    public void setCantidadVarillasTensoras(int cantidadVarillasTensoras) {
        this.cantidadVarillasTensoras = cantidadVarillasTensoras;
    }

    public int getCantidadChapa() {
        return cantidadChapa;
    }

    public void setCantidadChapa(int cantidadChapa) {
        this.cantidadChapa = cantidadChapa;
    }

    public int getCantidadEscuadras() {
        return cantidadEscuadras;
    }

    public void setCantidadEscuadras(int cantidadEscuadras) {
        this.cantidadEscuadras = cantidadEscuadras;
    }

    public int getVisagras() {
        return visagras;
    }

    public void setVisagras(int visagras) {
        this.visagras = visagras;
    }

    public String getMensajeAluminio3Ancho() {
        return mensajeAluminio3Ancho;
    }

    public void setMensajeAluminio3Ancho(String mensajeAluminio3Ancho) {
        this.mensajeAluminio3Ancho = mensajeAluminio3Ancho;
    }

    public String getMensajeAluminio3Alto() {
        return mensajeAluminio3Alto;
    }

    public void setMensajeAluminio3Alto(String mensajeAluminio3Alto) {
        this.mensajeAluminio3Alto = mensajeAluminio3Alto;
    }

    public String getMensajeNaveAlto() {
        return mensajeNaveAlto;
    }

    public void setMensajeNaveAlto(String mensajeNaveAlto) {
        this.mensajeNaveAlto = mensajeNaveAlto;
    }

    public String getMensajeNaveAncho() {
        return mensajeNaveAncho;
    }

    public void setMensajeNaveAncho(String mensajeNaveAncho) {
        this.mensajeNaveAncho = mensajeNaveAncho;
    }

    public String getMensajePisaVidriosAncho() {
        return mensajePisaVidriosAncho;
    }

    public void setMensajePisaVidriosAncho(String mensajePisaVidriosAncho) {
        this.mensajePisaVidriosAncho = mensajePisaVidriosAncho;
    }

    public String getMensajePisavidriosAlto() {
        return mensajePisavidriosAlto;
    }

    public void setMensajePisavidriosAlto(String mensajePisavidriosAlto) {
        this.mensajePisavidriosAlto = mensajePisavidriosAlto;
    }

    public String getMensajePartidor() {
        return mensajePartidor;
    }

    public void setMensajePartidor(String mensajePartidor) {
        this.mensajePartidor = mensajePartidor;
    }

    public String getMensageEntamborado() {
        return mensageEntamborado;
    }

    public void setMensageEntamborado(String mensageEntamborado) {
        this.mensageEntamborado = mensageEntamborado;
    }

    public String getMensageEntamboradoF06() {
        return mensageEntamboradoF06;
    }

    public void setMensageEntamboradoF06(String mensageEntamboradoF06) {
        this.mensageEntamboradoF06 = mensageEntamboradoF06;
    }

    public String getMensajeEmpaque() {
        return MensajeEmpaque;
    }

    public void setMensajeEmpaque(String MensajeEmpaque) {
        this.MensajeEmpaque = MensajeEmpaque;
    }

    public String getMensajePerfilU71() {
        return mensajePerfilU71;
    }

    public void setMensajePerfilU71(String mensajePerfilU71) {
        this.mensajePerfilU71 = mensajePerfilU71;
    }

    public String getMensajeVarillasTensoras() {
        return mensajeVarillasTensoras;
    }

    public void setMensajeVarillasTensoras(String mensajeVarillasTensoras) {
        this.mensajeVarillasTensoras = mensajeVarillasTensoras;
    }

    public String getMensajeChapa() {
        return mensajeChapa;
    }

    public void setMensajeChapa(String mensajeChapa) {
        this.mensajeChapa = mensajeChapa;
    }

    public String getMensajeEscuadras1() {
        return mensajeEscuadras1;
    }

    public void setMensajeEscuadras1(String mensajeEscuadras1) {
        this.mensajeEscuadras1 = mensajeEscuadras1;
    }

    public String getMensajePibotes() {
        return mensajePibotes;
    }

    public void setMensajePibotes(String mensajePibotes) {
        this.mensajePibotes = mensajePibotes;
    }

    public String getMensajeVisagras() {
        return mensajeVisagras;
    }

    public void setMensajeVisagras(String mensajeVisagras) {
        this.mensajeVisagras = mensajeVisagras;
    }

    public long getPrecioTotalCant() {
        return precioTotalCant;
    }

    public void setPrecioTotalCant(long precioTotalCant) {
        this.precioTotalCant = precioTotalCant;
    }

    public int getCantidadCuartoCirculoAlto() {
        return cantidadCuartoCirculoAlto;
    }

    public void setCantidadCuartoCirculoAlto(int cantidadCuartoCirculoAlto) {
        this.cantidadCuartoCirculoAlto = cantidadCuartoCirculoAlto;
    }

    public int getCantidadCuartoCirculoAncho() {
        return cantidadCuartoCirculoAncho;
    }

    public void setCantidadCuartoCirculoAncho(int cantidadCuartoCirculoAncho) {
        this.cantidadCuartoCirculoAncho = cantidadCuartoCirculoAncho;
    }

    public int getCantidadCuartoCirculoFondo() {
        return cantidadCuartoCirculoFondo;
    }

    public void setCantidadCuartoCirculoFondo(int cantidadCuartoCirculoFondo) {
        this.cantidadCuartoCirculoFondo = cantidadCuartoCirculoFondo;
    }

    public int getCantidadAnguloMediaAlto() {
        return cantidadAnguloMediaAlto;
    }

    public void setCantidadAnguloMediaAlto(int cantidadAnguloMediaAlto) {
        this.cantidadAnguloMediaAlto = cantidadAnguloMediaAlto;
    }

    public int getCantidadAnguloMediaAncho() {
        return cantidadAnguloMediaAncho;
    }

    public void setCantidadAnguloMediaAncho(int cantidadAnguloMediaAncho) {
        this.cantidadAnguloMediaAncho = cantidadAnguloMediaAncho;
    }

    public int getCantidadAnguloMediaFondo() {
        return cantidadAnguloMediaFondo;
    }

    public void setCantidadAnguloMediaFondo(int cantidadAnguloMediaFondo) {
        this.cantidadAnguloMediaFondo = cantidadAnguloMediaFondo;
    }

    public int getCantidadNaveDivisionAncho() {
        return cantidadNaveDivisionAncho;
    }

    public void setCantidadNaveDivisionAncho(int cantidadNaveDivisionAncho) {
        this.cantidadNaveDivisionAncho = cantidadNaveDivisionAncho;
    }

    public int getCantidadVidrioEntrepanos() {
        return cantidadVidrioEntrepanos;
    }

    public void setCantidadVidrioEntrepanos(int cantidadVidrioEntrepanos) {
        this.cantidadVidrioEntrepanos = cantidadVidrioEntrepanos;
    }

    public int getCantidadPerfilEsquineroUnaAncho() {
        return cantidadPerfilEsquineroUnaAncho;
    }

    public void setCantidadPerfilEsquineroUnaAncho(int cantidadPerfilEsquineroUnaAncho) {
        this.cantidadPerfilEsquineroUnaAncho = cantidadPerfilEsquineroUnaAncho;
    }

    public int getCntidadPerfilEsquineroUnaFondo() {
        return cntidadPerfilEsquineroUnaFondo;
    }

    public void setCntidadPerfilEsquineroUnaFondo(int cntidadPerfilEsquineroUnaFondo) {
        this.cntidadPerfilEsquineroUnaFondo = cntidadPerfilEsquineroUnaFondo;
    }

    public int getCantidadTubularUna() {
        return cantidadTubularUna;
    }

    public void setCantidadTubularUna(int cantidadTubularUna) {
        this.cantidadTubularUna = cantidadTubularUna;
    }

    public int getPisaVidrioAlto() {
        return pisaVidrioAlto;
    }

    public void setPisaVidrioAlto(int pisaVidrioAlto) {
        this.pisaVidrioAlto = pisaVidrioAlto;
    }

    public int getPisaVidrioAncho() {
        return pisaVidrioAncho;
    }

    public void setPisaVidrioAncho(int pisaVidrioAncho) {
        this.pisaVidrioAncho = pisaVidrioAncho;
    }

    public int getPisaVidrioFondo() {
        return pisaVidrioFondo;
    }

    public void setPisaVidrioFondo(int pisaVidrioFondo) {
        this.pisaVidrioFondo = pisaVidrioFondo;
    }

    public double getRecorteFondo() {
        return recorteFondo;
    }

    public void setRecorteFondo(double recorteFondo) {
        this.recorteFondo = recorteFondo;
    }

    public String getMensajeCuartoCirculoAlto() {
        return mensajeCuartoCirculoAlto;
    }

    public void setMensajeCuartoCirculoAlto(String mensajeCuartoCirculoAlto) {
        this.mensajeCuartoCirculoAlto = mensajeCuartoCirculoAlto;
    }

    public String getMensajeCuartoCirculoAncho() {
        return mensajeCuartoCirculoAncho;
    }

    public void setMensajeCuartoCirculoAncho(String mensajeCuartoCirculoAncho) {
        this.mensajeCuartoCirculoAncho = mensajeCuartoCirculoAncho;
    }

    public String getMensajeCuartoCirculoFondo() {
        return mensajeCuartoCirculoFondo;
    }

    public void setMensajeCuartoCirculoFondo(String mensajeCuartoCirculoFondo) {
        this.mensajeCuartoCirculoFondo = mensajeCuartoCirculoFondo;
    }

    public String getMensajeAnguloMediaAlto() {
        return mensajeAnguloMediaAlto;
    }

    public void setMensajeAnguloMediaAlto(String mensajeAnguloMediaAlto) {
        this.mensajeAnguloMediaAlto = mensajeAnguloMediaAlto;
    }

    public String getMensajeAnguloMediaAncho() {
        return mensajeAnguloMediaAncho;
    }

    public void setMensajeAnguloMediaAncho(String mensajeAnguloMediaAncho) {
        this.mensajeAnguloMediaAncho = mensajeAnguloMediaAncho;
    }

    public String getMensajeAnguloMediaFondo() {
        return mensajeAnguloMediaFondo;
    }

    public void setMensajeAnguloMediaFondo(String mensajeAnguloMediaFondo) {
        this.mensajeAnguloMediaFondo = mensajeAnguloMediaFondo;
    }

    public String getMensajeNaveDivisionAncho() {
        return mensajeNaveDivisionAncho;
    }

    public void setMensajeNaveDivisionAncho(String mensajeNaveDivisionAncho) {
        this.mensajeNaveDivisionAncho = mensajeNaveDivisionAncho;
    }

    public String getMensajeVidrioAltoAncho() {
        return mensajeVidrioAltoAncho;
    }

    public void setMensajeVidrioAltoAncho(String mensajeVidrioAltoAncho) {
        this.mensajeVidrioAltoAncho = mensajeVidrioAltoAncho;
    }

    public String getMensajeVidrioAltoFondo() {
        return mensajeVidrioAltoFondo;
    }

    public void setMensajeVidrioAltoFondo(String mensajeVidrioAltoFondo) {
        this.mensajeVidrioAltoFondo = mensajeVidrioAltoFondo;
    }

    public String getMensajeVidrioAnchoFondo() {
        return mensajeVidrioAnchoFondo;
    }

    public void setMensajeVidrioAnchoFondo(String mensajeVidrioAnchoFondo) {
        this.mensajeVidrioAnchoFondo = mensajeVidrioAnchoFondo;
    }

    public String getMensajeEntrepano() {
        return mensajeEntrepano;
    }

    public void setMensajeEntrepano(String mensajeEntrepano) {
        this.mensajeEntrepano = mensajeEntrepano;
    }

    public String getMensajePerfilEsquineroUna() {
        return mensajePerfilEsquineroUna;
    }

    public void setMensajePerfilEsquineroUna(String mensajePerfilEsquineroUna) {
        this.mensajePerfilEsquineroUna = mensajePerfilEsquineroUna;
    }

    public String getMensajeTubularUna() {
        return mensajeTubularUna;
    }

    public void setMensajeTubularUna(String mensajeTubularUna) {
        this.mensajeTubularUna = mensajeTubularUna;
    }

    public String getMensajePisavidrioAlto() {
        return mensajePisavidrioAlto;
    }

    public void setMensajePisavidrioAlto(String mensajePisavidrioAlto) {
        this.mensajePisavidrioAlto = mensajePisavidrioAlto;
    }

    public String getMensajePisavidrioAncho() {
        return mensajePisavidrioAncho;
    }

    public void setMensajePisavidrioAncho(String mensajePisavidrioAncho) {
        this.mensajePisavidrioAncho = mensajePisavidrioAncho;
    }

    public String getMensajePisavidrioFondo() {
        return mensajePisavidrioFondo;
    }

    public void setMensajePisavidrioFondo(String mensajePisavidrioFondo) {
        this.mensajePisavidrioFondo = mensajePisavidrioFondo;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public List<Vitrinas> getListaVitrinas() {
        return listaVitrinas;
    }

    public void setListaVitrinas(List<Vitrinas> listaVitrinas) {
        this.listaVitrinas = listaVitrinas;
    }

    public String getMensajeVidrioPuerta() {
        return mensajeVidrioPuerta;
    }

    public void setMensajeVidrioPuerta(String mensajeVidrioPuerta) {
        this.mensajeVidrioPuerta = mensajeVidrioPuerta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
