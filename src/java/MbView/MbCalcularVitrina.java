/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Clases.Vitrina;
import Daos.DaoVidrio;
import Daos.DaoVitrinas;
import HibernateUtil.HibernateUtil;
import Pojos.Vitrinas;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author William Sanchez
 */
@ManagedBean
@ViewScoped
public class MbCalcularVitrina {

    /**
     * Creates a new instance of MbCalcularVitrina
     */
    private String alto;
    private String ancho;
    private String fondo;
    private int ganancia;
    private int manObra;
    private Session session;
    private Transaction transaccion;
    private ArrayList<Vitrinas> lista;
    private int total;
    private int idVidrio;
    private long precioVidrio;
    private int tipoVentana;
    private int precioCuerpo;
    private long precioTotal;
    private String nombreProducto;
    private int tipoVitrina;
     private int modelo;
    private int tipoEntrepanos;
    private double recorteAncho;
    private double recorteAlto;
    private double recorteFondo;
    private double recorteVidrioAncho;
    private double recorteVidrioAlto;
    private double recorteVidrioFondo;

    private int cantidadCuartoCirculoAlto;
    private int cantidadCuartoCirculoAncho;
    private int cantidadCuartoCirculoFondo;
    private int cantidadAnguloMediaAlto;
    private int cantidadAnguloMediaAncho;
    private int cantidadAnguloMediaFondo;
    private int cantidadNaveDivisionAncho;
    private int cantidadVidrio;
    private int cantidadVidrioEntrepanos;
    private int cantidadPerfilEsquineroUnaAncho;
    private int cntidadPerfilEsquineroUnaFondo;
    private int cantidadTubularUna;
    private int pisaVidrioAlto;
    private int pisaVidrioAncho;
    private int pisaVidrioFondo;
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
    private int canti;
    private String mensajeCanti;
    private long precioTotalCantidad;

    public MbCalcularVitrina() {
        this.alto = "";
        this.ancho = "";
        this.ganancia = 0;
        this.manObra = 0;
        this.idVidrio = 0;
        this.tipoEntrepanos = 0;
        this.precioTotal = 0;
        this.lista = new ArrayList<>();
     
            this.canti=0;
    }

    public void calcularCosto() {
        this.session = null;
        this.transaccion = null;
        Vitrina vitrina = new Vitrina();
        try {
            DaoVitrinas daoVitrinas = new DaoVitrinas();

            if (this.tipoVitrina == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Seleccione un tipo de ventana"));
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.lista.addAll(daoVitrinas.getAll(this.session));
            if (this.tipoVitrina == 1) {
                vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                        this.lista.get(0).getPreciocot(), this.lista.get(1).getPreciocot(),
                        this.lista.get(2).getPreciocot(), this.lista.get(4).getPreciocot(), this.lista.get(5).getPreciocot(),
                        this.lista.get(6).getPreciocot(), this.lista.get(3).getPreciocot(),
                        this.lista.get(7).getPreciocot(),
                        this.lista.get(8).getPreciocot(), this.tipoVitrina, 0, 0, 0, 0);
            } else {
                if (this.tipoVitrina == 2) {
                    vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                            0, this.lista.get(1).getPreciocot(),
                            0, this.lista.get(4).getPreciocot(), 0,
                            this.lista.get(6).getPreciocot(), 0,0,
                            this.lista.get(8).getPreciocot(),
                             this.tipoVitrina, this.lista.get(9).getPreciocot(), this.lista.get(10).getPreciocot(),
                            this.lista.get(11).getPreciocot(), this.lista.get(12).getPreciocot());

                } else {
                    if (this.tipoVitrina == 3) {
                        vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                                0, this.lista.get(1).getPreciocot(),
                                0, this.lista.get(4).getPreciocot(), 0,
                                this.lista.get(6).getPreciocot(), 0,0,
                                this.lista.get(8).getPreciocot(),
                                 this.tipoVitrina, this.lista.get(9).getPreciocot(), this.lista.get(10).getPreciocot(),
                                this.lista.get(11).getPreciocot(), this.lista.get(12).getPreciocot());
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
                if(String.valueOf(this.precioVidrio).length()>10){
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios1 - 5));
              }else{
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
                    this.precioTotalCantidad= this.precioTotal*this.canti;
                this.mensajeCanti=String.valueOf(this.canti);
            } else {
                this.nombreProducto = "Vitrina de 4 entrepaños " + this.alto + " * " + this.ancho + " Fondo: " + this.fondo;
                this.precioTotal = vitrina.getSumaTotal() + this.precioVidrio;
                    this.precioTotalCantidad= this.precioTotal*this.canti;
                this.mensajeCanti=String.valueOf(this.canti);
            }

            if (this.tipoVitrina == 1) {
//                this.recorte3=material.getAncho()/2;
                this.recorteAncho = Double.valueOf(this.getAncho());
                this.recorteAlto = Double.valueOf(this.getAlto());
                this.recorteFondo = Double.valueOf(this.getFondo());

                this.cantidadCuartoCirculoAncho = 4* this.canti;
                this.cantidadCuartoCirculoAlto = 4* this.canti;
                this.cantidadCuartoCirculoFondo = 4* this.canti;
                this.cantidadAnguloMediaAlto = 6* this.canti;
                this.cantidadAnguloMediaAncho = 6* this.canti;
                this.cantidadAnguloMediaFondo = 6* this.canti;
                this.cantidadNaveDivisionAncho = 2* this.canti;
                this.cantidadVidrio = 2* this.canti;
                this.cantidadVidrioEntrepanos = 4* this.canti;

                this.mensajeCuartoCirculoAlto = "" + this.cantidadCuartoCirculoAlto + " Aluminio CuartoCirculo Alto de:" + (this.recorteAlto - 8.0);
                this.mensajeCuartoCirculoAncho = "" + this.cantidadCuartoCirculoAncho + "Alumininio CuartoCirculo Ancho de: " + (this.recorteAncho - 8.0);
                this.mensajeCuartoCirculoFondo = "" + this.cantidadCuartoCirculoFondo + "Alumininio CuartoCirculo Fondo de: " + (this.recorteFondo - 8.0);
                this.mensajeAnguloMediaAlto = "" + this.cantidadAnguloMediaAlto + "  AnguloMedia Alto de " + (this.recorteAlto - 8);
                this.mensajeAnguloMediaAncho = "" + this.cantidadAnguloMediaAncho + " AnguloMedia Ancho de:  " + (this.recorteAncho - 10);
                this.mensajeAnguloMediaFondo = "" + this.cantidadAnguloMediaFondo + " AnguloMedia Fondo de:  " + (this.recorteFondo - 10);
                this.mensajeNaveDivisionAncho = "" + this.cantidadNaveDivisionAncho + "Nave DivisionBano Ancho de:  " + (this.recorteAncho / 2);
                this.mensajeVidrioAltoAncho = "" + this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + " recorte ancho  " + (this.recorteAncho - 10.5);
                this.mensajeVidrioAltoFondo = "" + this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + "Recorte fondo" + (this.recorteFondo - 10.5);
                this.mensajeVidrioAnchoFondo = "" + this.cantidadVidrioEntrepanos + "  Vidrios de 6mm Ancho " + (this.recorteAncho - 1) + "recorte fondo" + (this.recorteFondo - 8.5);

            } else {
                if (this.tipoVitrina == 2) {
//                this.recorte3=material.getAncho()/2;
                    this.recorteAncho = Double.valueOf(this.getAncho());
                    this.recorteAlto = Double.valueOf(this.getAlto());
                    this.recorteFondo = Double.valueOf(this.getFondo());

                    this.cantidadPerfilEsquineroUnaAncho = 2;
                    this.cntidadPerfilEsquineroUnaFondo = 2;

                    this.cantidadAnguloMediaAlto = 4* this.canti;
                    this.cantidadAnguloMediaAncho = 2* this.canti;
                    this.cantidadAnguloMediaFondo = 2* this.canti;
                    this.cantidadNaveDivisionAncho = 2* this.canti;
                    this.cantidadVidrio = 2* this.canti;
                    this.cantidadVidrioEntrepanos = 4* this.canti;
                    this.pisaVidrioAlto = 6* this.canti;
                    this.pisaVidrioAncho = 6* this.canti;
                    this.pisaVidrioFondo = 6* this.canti;

                    this.mensajePerfilEsquineroUna = "" + this.cantidadPerfilEsquineroUnaAncho + " Perfil o de Una Ancho de: " + (this.recorteAncho);
                    this.mensajePerfilEsquineroUna = "" + this.cntidadPerfilEsquineroUnaFondo + " Perfil Esquinero de Una Fondo de: " + (this.recorteFondo);
                    this.mensajeAnguloMediaAlto = "" + this.cantidadAnguloMediaAlto + "  Tubular 1 * 1 Alto de " + (this.recorteAlto - 5);
                    this.mensajeAnguloMediaAncho = "" + this.cantidadAnguloMediaAncho + " Tubular 1 * 1 Ancho de:  " + (this.recorteAncho - 5);
                    this.mensajeAnguloMediaFondo = "" + this.cantidadAnguloMediaFondo + " Tubular 1 * 1 Fondo de:  " + (this.recorteFondo - 5);
                    this.mensajePisavidrioAlto = "" + this.pisaVidrioAlto + " Pisavidrio de Media de: " + (this.recorteAlto);
                    this.mensajePisavidrioAncho = "" + this.pisaVidrioAncho + " Pisavidrio de Media Alto de: " + (this.recorteAncho);
                    this.mensajePisavidrioFondo = "" + this.pisaVidrioFondo + " Pisavidrio de Media Ancho de: " + (this.recorteFondo);
                    this.mensajeNaveDivisionAncho = "" + this.cantidadNaveDivisionAncho + "Nave DivisionBano Ancho Fondo de:  " + (this.recorteAncho / 2);
                    this.mensajeVidrioAltoAncho = "" + this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + " recorte ancho  " + (this.recorteAncho - 10.5);
                    this.mensajeVidrioAltoFondo = "" + this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + "Recorte fondo" + (this.recorteFondo - 10.5);
                    this.mensajeVidrioAnchoFondo = "" + this.cantidadVidrio + "  Vidrios Ancho " + (this.recorteAncho - 10.5) + "recorte fondo" + (this.recorteFondo - 1);
                    this.mensajeVidrioAnchoFondo = "" + this.cantidadVidrioEntrepanos + "  Vidrios de 6mm Ancho " + (this.recorteAncho - 1) + "recorte fondo" + (this.recorteFondo - 1);

                } else {
                    if (this.tipoVitrina == 3) {
//                this.recorte3=material.getAncho()/2;
                        this.recorteAncho = Double.valueOf(this.getAncho());
                        this.recorteAlto = Double.valueOf(this.getAlto());
                        this.recorteFondo = Double.valueOf(this.getFondo());

                        this.cantidadPerfilEsquineroUnaAncho = 2* this.canti;
                        this.cntidadPerfilEsquineroUnaFondo = 2* this.canti;

                        this.cantidadAnguloMediaAlto = 6* this.canti;
                        this.cantidadAnguloMediaAncho = 2* this.canti;
                        this.cantidadAnguloMediaFondo = 2* this.canti;
                        this.cantidadNaveDivisionAncho = 2* this.canti;
                        this.cantidadVidrio = 2* this.canti;
                        this.cantidadVidrioEntrepanos = 4* this.canti;
                        this.pisaVidrioAlto = 6* this.canti;
                        this.pisaVidrioAncho = 6* this.canti;
                        this.pisaVidrioFondo = 6* this.canti;

                        this.mensajePerfilEsquineroUna = "" + this.cantidadPerfilEsquineroUnaAncho + " Perfil o de Una Ancho de: " + (this.recorteAncho);
                        this.mensajePerfilEsquineroUna = "" + this.cntidadPerfilEsquineroUnaFondo + " Perfil Esquinero de Una Fondo de: " + (this.recorteFondo);
                        this.mensajeAnguloMediaAlto = "" + this.cantidadAnguloMediaAlto + "  AnguloMedia Alto de " + (this.recorteAlto - 8);
                        this.mensajeAnguloMediaAncho = "" + this.cantidadAnguloMediaAncho + " AnguloMedia Ancho de:  " + (this.recorteAncho - 10);
                        this.mensajeAnguloMediaFondo = "" + this.cantidadAnguloMediaFondo + " AnguloMedia Fondo de:  " + (this.recorteFondo - 10);
                        this.mensajePisavidrioAlto = "" + this.pisaVidrioAlto + " Pisavidrio de Media de: " + (this.recorteAlto);
                        this.mensajePisavidrioAncho = "" + this.pisaVidrioAncho + " Pisavidrio de Media Alto de: " + (this.recorteAncho);
                        this.mensajePisavidrioFondo = "" + this.pisaVidrioFondo + " Pisavidrio de Media Ancho de: " + (this.recorteFondo);
                        this.mensajeNaveDivisionAncho = "" + this.cantidadNaveDivisionAncho + "Nave DivisionBano Ancho Fondo de:  " + (this.recorteAncho / 2);
                        this.mensajeVidrioAltoAncho = "" + this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + " recorte ancho  " + (this.recorteAncho - 10.5);
                        this.mensajeVidrioAltoFondo = "" + this.cantidadVidrio + "  Vidrios ALto" + (this.recorteAlto - 10.5) + "Recorte fondo" + (this.recorteFondo - 10.5);
                        this.mensajeVidrioAnchoFondo = "" + this.cantidadVidrio + "  Vidrios Ancho " + (this.recorteAncho - 10.5) + "recorte fondo" + (this.recorteFondo - 1);
                        this.mensajeVidrioAnchoFondo = "" + this.cantidadVidrioEntrepanos + "  Vidrios de 6mm Ancho " + (this.recorteAncho - 1) + "recorte fondo" + (this.recorteFondo - 1);

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
              this.lista = new ArrayList<>();

        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", ex.getCause() + "Por favor contacte con su administrador " + ex.getMessage()));

        } finally {
            if (this.session != null) {
                this.session.close();
            }

        }

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

    public ArrayList<Vitrinas> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Vitrinas> lista) {
        this.lista = lista;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIdVidrio() {
        return idVidrio;
    }

    public void setIdVidrio(int idVidrio) {
        this.idVidrio = idVidrio;
    }

    public long getPrecioVidrio() {
        return precioVidrio;
    }

    public void setPrecioVidrio(long precioVidrio) {
        this.precioVidrio = precioVidrio;
    }

    public int getTipoVentana() {
        return tipoVentana;
    }

    public void setTipoVentana(int tipoVentana) {
        this.tipoVentana = tipoVentana;
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

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getTipoEntrepanos() {
        return tipoEntrepanos;
    }

    public void setTipoEntrepanos(int tipoEntrepanos) {
        this.tipoEntrepanos = tipoEntrepanos;
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

    public double getRecorteFondo() {
        return recorteFondo;
    }

    public void setRecorteFondo(double recorteFondo) {
        this.recorteFondo = recorteFondo;
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

    public double getRecorteVidrioFondo() {
        return recorteVidrioFondo;
    }

    public void setRecorteVidrioFondo(double recorteVidrioFondo) {
        this.recorteVidrioFondo = recorteVidrioFondo;
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

    public int getCantidadVidrio() {
        return cantidadVidrio;
    }

    public void setCantidadVidrio(int cantidadVidrio) {
        this.cantidadVidrio = cantidadVidrio;
    }

    public int getCantidadVidrioEntrepanos() {
        return cantidadVidrioEntrepanos;
    }

    public void setCantidadVidrioEntrepanos(int cantidadVidrioEntrepanos) {
        this.cantidadVidrioEntrepanos = cantidadVidrioEntrepanos;
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

    public int getTipoVitrina() {
        return tipoVitrina;
    }

    public void setTipoVitrina(int tipoVitrina) {
        this.tipoVitrina = tipoVitrina;
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

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

}
