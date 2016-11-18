/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoPuertas;
import Daos.DaoVidrio;
import HibernateUtil.HibernateUtil;
import java.util.ArrayList;
import Clases.Puerta;
import Pojos.Puertas;
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
public class MbCalcularPuerta {

    private String alto;
    private String ancho;
    private int ganancia;
    private int manObra;
    private Session session;
    private Transaction transaccion;
    private ArrayList<Puertas> lista;
    private int total;
    private int idVidrio;
    private int precioVidrio;
    private int tipoVentana;
    private int precioCuerpo;
    private long precioTotal;
    private String nombreProducto;
    private int tipoPuerta;

    private double recorteAluminio3MarcoAlto;
    private double recorteAluminio3MarcoAncho;
    private double recorteAncho;
    private double recorteAlto;
    private double recorte4;

    private double recorteVidrioAncho;
    private double recorteVidrioAlto;

    private int cantidadMarcoAlto;
    private int cantidadMarcoAncho;
    private int cantidadNaveAlto;
    private int cantidadNaveAncho;
    private int cantidadPisaVidriosAncho;
    private int cantidadPisaVidriosAlto;
    private int cantidadVidrio;
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
    private String mensajeVidrio;
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
    private int canti;
    private String mensajeCanti;
    private long precioTotalCantidad;

    public MbCalcularPuerta() {
        this.lista = new ArrayList<>();
        this.precioVidrio = 0;
        this.precioTotal = 0;
        this.alto = "";
        this.ancho = "";
        this.ganancia = 0;
        this.manObra = 0;
        this.idVidrio = 0;
        this.tipoVentana = 0;
        this.nombreProducto = "";
        this.tipoPuerta = 0;
            this.canti=0;
    }

    public void calcularCosto() {
        this.session = null;
        this.transaccion = null;
        try {
            Puerta puertas = new Puerta();
            DaoPuertas daoPuertas = new DaoPuertas();

            if (this.tipoVentana == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Seleccione un tipo de ventana"));
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            this.lista.addAll(daoPuertas.getAll(this.session));

            if (this.tipoVentana == 1) {
                puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                        this.lista.get(1).getPreciocot(), this.lista.get(0).getPreciocot(),
                        this.lista.get(2).getPreciocot(), this.lista.get(3).getPreciocot(), this.lista.get(5).getPreciocot(),
                        this.lista.get(6).getPreciocot(), this.lista.get(4).getPreciocot(), this.lista.get(7).getPreciocot(),
                        0, this.tipoVentana, 0, 0, 0, 0, this.lista.get(13).getPreciocot());
            } else {
                if (this.tipoVentana == 2) {
                    puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                            0, this.lista.get(0).getPreciocot(), this.lista.get(2).getPreciocot(),
                            0, this.lista.get(5).getPreciocot(), this.lista.get(6).getPreciocot(),
                            this.lista.get(4).getPreciocot(), this.lista.get(7).getPreciocot(),
                            this.lista.get(8).getPreciocot(),
                            this.tipoVentana, this.lista.get(9).getPreciocot(), 0, 0, 0, 0);

                } else {
                    if (this.tipoVentana == 3) {
                        puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                this.lista.get(1).getPreciocot(), this.lista.get(0).getPreciocot(),
                                this.lista.get(2).getPreciocot(), this.lista.get(3).getPreciocot(), this.lista.get(5).getPreciocot(),
                                this.lista.get(6).getPreciocot(), this.lista.get(4).getPreciocot(), this.lista.get(7).getPreciocot(),
                                this.lista.get(8).getPreciocot(),
                                this.tipoVentana, 0, this.lista.get(10).getPreciocot(), 0, 0, 0);
                    } else {
                        if (this.tipoVentana == 4) {
                            puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                    0, this.lista.get(0).getPreciocot(), this.lista.get(2).getPreciocot(),
                                    this.lista.get(3).getPreciocot(), this.lista.get(5).getPreciocot(), this.lista.get(6).getPreciocot(),
                                    this.lista.get(4).getPreciocot(), this.lista.get(7).getPreciocot(),
                                    this.lista.get(8).getPreciocot(),
                                    this.tipoVentana, 0, 0, 0, 0, 0);

                        } else {
                            if (this.tipoVentana == 5) {
                                puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                        0, this.lista.get(0).getPreciocot(), this.lista.get(2).getPreciocot(), 0, 0,
                                        this.lista.get(6).getPreciocot(), this.lista.get(4).getPreciocot(), this.lista.get(7).getPreciocot(), 0,
                                        this.tipoVentana, 0, 0, this.lista.get(11).getPreciocot(), this.lista.get(12).getPreciocot(),
                                        this.lista.get(13).getPreciocot());

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
                //        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio del vidrio es: " + String.valueOf(this.precioVidrio).substring(0, espacios-4)));

            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }

            if (this.tipoVentana == 6) {
                this.nombreProducto = "Vidrio " + this.alto + " * " + this.ancho;
                this.precioTotal = this.precioVidrio;
            } else {
                this.nombreProducto = "Puerta " + this.alto + " * " + this.ancho;
                this.precioTotal = puertas.getSumaTotal() + this.precioVidrio;
                    this.precioTotalCantidad= this.precioTotal*this.canti;
                this.mensajeCanti=String.valueOf(this.canti);

            }

//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Total es: " + material.getSumaTotal()));
            if (this.tipoVentana == 1) {
//                this.recorte3=material.getAncho()/2;
                this.recorteAncho = Double.valueOf(this.getAncho());
                this.recorteAlto = Double.valueOf(this.getAlto());

                this.cantidadMarcoAncho = 1* this.canti;
                this.cantidadMarcoAlto = 2* this.canti;
                this.cantidadNaveAlto = 2* this.canti;
                this.cantidadNaveAncho = 2* this.canti;
                this.cantidadNaveAlto = 2* this.canti;
                this.cantidadPartidor = 1* this.canti;
                this.cantidadPibotes = 1* this.canti;
                this.cantidadChapa = 1* this.canti;
                this.cantidadVarillasTensoras = 2* this.canti;
                this.cantidadEscuadras = 12* this.canti;
                this.cantidadPisaVidriosAlto = 8* this.canti;
                this.cantidadPisaVidriosAncho = 8* this.canti;
                this.cantidadVidrio = 2* this.canti;
                this.mensajeAluminio3Alto = "" + this.cantidadMarcoAlto + " Aluminio 3 media Alto de:" + (this.recorteAlto);
                this.mensajeAluminio3Ancho = "" + this.cantidadMarcoAncho + "Alumininio 3media Ancho de: " + (this.recorteAncho);
                this.mensajeNaveAlto = "" + this.cantidadNaveAlto + "  AluminiT87 Alto de " + (this.recorteAlto - 5.0);
                this.mensajeNaveAncho = "" + this.cantidadNaveAncho + " AluminioT87 Ancho de:  " + (this.recorteAncho - 8.0);
                this.mensajePisaVidriosAncho = "" + this.cantidadPisaVidriosAncho + "PisaVidrios Ancho de:  " + (this.recorteAncho - 15.6);
                this.mensajePisavidriosAlto = "" + this.cantidadPisaVidriosAlto + " PisaVidrios Alto de:  " + (this.recorteAlto - 19.2);
                this.mensajePartidor = "" + this.cantidadPartidor + " Partidor de:  " + (this.recorteAncho - 15.6);
                this.mensajePibotes = "" + this.cantidadPibotes + "Pibotes Americanos .";
                this.mensajeChapa = "" + this.cantidadChapa + "Chapa .";
                this.mensajeEscuadras1 = "" + this.cantidadEscuadras + "Escudras .";
                this.mensajeVarillasTensoras = "" + this.cantidadVarillasTensoras + "Varillas Tensoras .";
             this.mensajeVidrio = "" + this.cantidadVidrio + " Vidrio Alto de: "+ (this.recorteAlto/2 - 19.7)+ " VidrioAncho de: "+ (this.recorteAncho - 16.1);



            } else {
                if (this.tipoVentana == 2) {
//                    this.recorte3=material.getAncho()/2;

                    this.recorteAncho = Double.valueOf(this.getAncho());
                    this.recorteAlto = Double.valueOf(this.getAlto());
                    this.cantidadMarcoAlto = 2* this.canti;
                    this.cantidadMarcoAncho = 1* this.canti;
                    this.cantidadNaveAlto = 2* this.canti;
                    this.cantidadNaveAncho = 2* this.canti;
                    this.CantidadPerfilU71 = Integer.valueOf(this.getAncho()) / 7* this.canti;
                    this.cantidadPibotes = 1* this.canti;
                    this.cantidadChapa = 1* this.canti;
                    this.cantidadVarillasTensoras = 2* this.canti;
                    this.cantidadEscuadras = 12* this.canti;
                    this.cantidadPartidor = 1* this.canti;
                    this.cantidadPisaVidriosAlto = 4* this.canti;
                    this.cantidadPisaVidriosAncho = 4* this.canti;
                    this.mensajeAluminio3Alto = "" + this.cantidadMarcoAlto + " Aluminio 3media Alto de:" + (this.recorteAlto);
                    this.mensajeAluminio3Ancho = "" + this.cantidadMarcoAncho + "Alumininio 3media de: " + (this.recorteAncho);
                    this.mensajeNaveAlto = "" + this.cantidadNaveAlto + "  AluminiT103 Alto de " + (this.recorteAlto - 5.0);
                    this.mensajeNaveAncho = "" + this.cantidadNaveAncho + " AluminioT103 Ancho de:  " + (this.recorteAncho - 8.0);
                    this.mensajePerfilU71 = "" + this.CantidadPerfilU71 + "pefil U71 Alto de:  " + (this.recorteAlto - 20.2);
                    this.mensajePibotes = "" + this.cantidadPibotes + "Pibotes Americanos .";
                    this.mensajeChapa = "" + this.cantidadChapa + "Chapa .";
                    this.mensajeEscuadras1 = "" + this.cantidadEscuadras + "Escudras .";
                    this.mensajeVarillasTensoras = "" + this.cantidadVarillasTensoras + "Varillas Tensoras .";

                } else {
                    if (this.tipoVentana == 3) {
//                this.recorte3=material.getAncho()/2;
                        this.recorteAncho = Double.valueOf(this.getAncho());
                        this.recorteAlto = Double.valueOf(this.getAlto());

                        this.cantidadMarcoAncho = 1* this.canti;
                        this.cantidadMarcoAlto = 2* this.canti;
                        this.cantidadNaveAlto = 2* this.canti;
                        this.cantidadNaveAncho = 2* this.canti;
                        this.cantidadNaveAlto = 2* this.canti;
                        this.cantidadPisaVidriosAlto = 4* this.canti;
                        this.cantidadPisaVidriosAncho = 4* this.canti;
                        this.cantidadPibotes = 1* this.canti;
                        this.cantidadChapa = 1* this.canti;
                        this.cantidadVarillasTensoras = 2* this.canti;
                        this.cantidadEscuadras = 12* this.canti;
                        this.cantidadEntamboradoF06 = Integer.valueOf(this.getAncho()) / 7* this.canti;
                        this.cantidadVidrio = 2* this.canti;
                        this.mensajeAluminio3Alto = "" + this.cantidadMarcoAlto + " Aluminio 3media Alto de:" + (this.recorteAlto);
                        this.mensajeAluminio3Ancho = "" + this.cantidadMarcoAncho + "Alumininio 3media Ancho de: " + (this.recorteAncho);
                        this.mensajeNaveAlto = "" + this.cantidadNaveAlto + "  AluminiT87 Alto de " + (this.recorteAlto - 3.0);
                        this.mensajeNaveAncho = "" + this.cantidadNaveAncho + " AluminioT87 Ancho de:  " + (this.recorteAncho - 2.0);
                        this.mensajePisaVidriosAncho = "" + this.cantidadPisaVidriosAncho + "PisaVidrios Ancho de:  " + (this.recorteAncho - 4.5);
                        this.mensajePisavidriosAlto = "" + this.cantidadPisaVidriosAlto + " PisaVidrios Alto de:  " + (this.recorteAlto - 5.0);
                        this.mensageEntamboradoF06 = "" + this.cantidadEntamboradoF06 + "Emtamborado F06" + (this.recorteAlto - 12.6);
//                  this.mensajeVidrio=""+this.cantidadVidrio+"  Vidrios ALto * Ancho:"+(this.recorteVidrioAlto-10)+(this.recorteVidrioAncho-5.5);
                        this.mensajePibotes = "" + this.cantidadPibotes + "Pibotes Americanos .";
                        this.mensajeChapa = "" + this.cantidadChapa + "Chapa .";
                        this.mensajeEscuadras1 = "" + this.cantidadEscuadras + "Escudras .";
                        this.mensajeVarillasTensoras = "" + this.cantidadVarillasTensoras + "Varillas Tensoras .";
                    } else {
                        if (this.tipoVentana == 4) {
//                    this.recorte3=material.getAncho()/2;

                            this.recorteAncho = Double.valueOf(this.getAncho());
                            this.recorteAlto = Double.valueOf(this.getAlto());
                            this.cantidadMarcoAncho = 1;
                            this.cantidadNaveAlto = 2;
                            this.cantidadNaveAncho = 2;
                            this.cantidadNaveAlto = 2;
                            this.cantidadPisaVidriosAlto = 4;
                            this.cantidadPisaVidriosAncho = 8;
                            this.cantidadPartidor = 1;
                            this.cantidadPibotes = 1;
                            this.cantidadChapa = 1;
                            this.cantidadVarillasTensoras = 2;
                            this.cantidadEscuadras = 12;
                            this.mensajeAluminio3Alto = "" + this.cantidadMarcoAlto + " Aluminio 3media Alto de:" + (this.recorteAlto);
                            this.mensajeAluminio3Ancho = "" + this.cantidadMarcoAncho + "Alumininio 3media de: " + (this.recorteAncho);
                            this.mensajeNaveAlto = "" + this.cantidadNaveAlto + "  AluminiT103  Alto de " + (this.recorteAlto - 5.0);
                            this.mensajeNaveAncho = "" + this.cantidadNaveAncho + " AluminioT103 de:  " + (this.recorteAncho - 8.0);
                            this.mensajePartidor = "" + this.cantidadPartidor + " Partidor Aluminio T103 de: " + (this.recorteAncho - 23.6);
                            this.mensajePisaVidriosAncho = "" + this.cantidadPisaVidriosAncho + "PisaVidrios Ancho de:  " + (this.recorteAncho - 23.6);
                            this.mensajePisavidriosAlto = "" + this.cantidadPisaVidriosAlto + " PisaVidrios Alto de:  " + (this.recorteAlto - 20.6);
                            this.mensajePibotes = "" + this.cantidadPibotes + "Pibotes Americanos .";
                            this.mensajeChapa = "" + this.cantidadChapa + "Chapa .";
                            this.mensajeEscuadras1 = "" + this.cantidadEscuadras + "Escudras .";
                            this.mensajeVarillasTensoras = "" + this.cantidadVarillasTensoras + "Varillas Tensoras .";
                            this.recorteVidrioAncho = Integer.valueOf(this.getAncho()) - 24.1;
//                            this.recorteVidrioAlto = puertas.getAlto() - 10;
                            this.recorteVidrioAlto = Integer.valueOf(this.getAlto()) - 21.1;
                                  this.recorteVidrioAlto=this.recorteVidrioAlto/2;


                        } else {
                            if (this.tipoVentana == 5) {
//                    this.recorte3=material.getAncho()/2;

                                this.recorteAncho = Double.valueOf(this.getAncho());
                                this.recorteAlto = Double.valueOf(this.getAlto());
                                this.cantidadMarcoAncho = 1;
                                this.cantidadMarcoAlto= 2;
                                this.cantidadNaveAlto = 2;
                                this.cantidadNaveAncho = 2;
                                this.cantidadNaveAlto = 2;
                                this.cantidadPisaVidriosAlto = 4;
                                this.cantidadPisaVidriosAncho = 8;
                                this.cantidadPartidor = 1;
                                this.visagras = 2;
                                this.cantidadChapa = 1;
                                this.cantidadVarillasTensoras = 2;
                                this.cantidadEscuadras = 12;
                                this.mensajeAluminio3Alto = "" + this.cantidadMarcoAlto + " Aluminio 3media Alto de:" + (this.recorteAlto);
                                this.mensajeAluminio3Ancho = "" + this.cantidadMarcoAncho + "Alumininio 3media Ancho de: " + (this.recorteAncho);
                                this.mensajeNaveAlto = "" + this.cantidadNaveAlto + "  AluminioT103  Alto de " + (this.recorteAlto - 5.0);
                                this.mensajeNaveAncho = "" + this.cantidadNaveAncho + " AluminioT103  Ancho de:  " + (this.recorteAncho - 8.0);
                                this.mensajePartidor = "" + this.cantidadPartidor + " Partidor Aluminio T103 Ancho de: " + (this.recorteAncho - 23.6);
                                this.mensajePisaVidriosAncho = "" + this.cantidadPisaVidriosAncho + "PisaVidrios Ancho de:  " + (this.recorteAncho - 23.6);
                                this.mensajePisavidriosAlto = "" + this.cantidadPisaVidriosAlto + " PisaVidrios Alto de:  " + (this.recorteAlto - 20.6);
                                this.mensajeVisagras = "" + this.visagras + "Visagras .";
                                this.mensajeChapa = "" + this.cantidadChapa + "Chapa .";
                                this.mensajeEscuadras1 = "" + this.cantidadEscuadras + "Escudras .";
                                this.mensajeVarillasTensoras = "" + this.cantidadVarillasTensoras + "Varillas Tensoras .";
                                this.recorteVidrioAncho = Integer.valueOf(this.getAncho()) - 24.1;
//                            this.recorteVidrioAlto = puertas.getAlto() - 10;
                                this.recorteVidrioAlto = Integer.valueOf(this.getAlto()) - 21.1;
                                      this.recorteVidrioAlto=this.recorteVidrioAlto/2;


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
            this.tipoVentana = 0;

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

    public ArrayList<Puertas> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Puertas> lista) {
        this.lista = lista;
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

    public int getPrecioVidrio() {
        return precioVidrio;
    }

    public void setPrecioVidrio(int precioVidrio) {
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

    public int getTipoPuerta() {
        return tipoPuerta;
    }

    public void setTipoPuerta(int tipoPuerta) {
        this.tipoPuerta = tipoPuerta;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaction transaccion) {
        this.transaccion = transaccion;
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

    public int getCantidadVidrio() {
        return cantidadVidrio;
    }

    public void setCantidadVidrio(int cantidadVidrio) {
        this.cantidadVidrio = cantidadVidrio;
    }

    public int getCantidadPartidor() {
        return cantidadPartidor;
    }

    public void setCantidadPartidor(int cantidadPartidor) {
        this.cantidadPartidor = cantidadPartidor;
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

    public String getMensajeVidrio() {
        return mensajeVidrio;
    }

    public void setMensajeVidrio(String mensajeVidrio) {
        this.mensajeVidrio = mensajeVidrio;
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

    public int getCantidadEmpaque() {
        return cantidadEmpaque;
    }

    public void setCantidadEmpaque(int cantidadEmpaque) {
        this.cantidadEmpaque = cantidadEmpaque;
    }

    public String getMensajeEmpaque() {
        return MensajeEmpaque;
    }

    public void setMensajeEmpaque(String MensajeEmpaque) {
        this.MensajeEmpaque = MensajeEmpaque;
    }

    public int getCantidadPerfilU71() {
        return CantidadPerfilU71;
    }

    public void setCantidadPerfilU71(int CantidadPerfilU71) {
        this.CantidadPerfilU71 = CantidadPerfilU71;
    }

    public String getMensajePerfilU71() {
        return mensajePerfilU71;
    }

    public void setMensajePerfilU71(String mensajePerfilU71) {
        this.mensajePerfilU71 = mensajePerfilU71;
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

    public int getVisagras() {
        return visagras;
    }

    public void setVisagras(int visagras) {
        this.visagras = visagras;
    }

    public String getMensajeVisagras() {
        return mensajeVisagras;
    }

    public void setMensajeVisagras(String mensajeVisagras) {
        this.mensajeVisagras = mensajeVisagras;
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

}
