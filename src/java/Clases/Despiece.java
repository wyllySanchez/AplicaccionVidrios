/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;

/**
 *
 * @author ander
 */
public class Despiece implements Serializable {

    private String alto;
    private String ancho;
    private int idVidrio;
    private int tipoVentana;
    private String nombreProducto;

    private int tipoAluminio;
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
    private int canti;
    private String mensajeCanti;

//    estos son los mensajes y cantidades de las puertas
    private int tipoPuerta;

    private double recorteAluminio3MarcoAlto;
    private double recorteAluminio3MarcoAncho;
    private double recorteAnchoPuertas;
    private double recorteAltoPuertas;
    private double recorte4Puertas;
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
    private int cantiPuerta;
    private String mensajeCantiPuerta;
    private long precioTotalCantidad;

//    esta son los mensajes y las cantidades de las vitrina
    private int tipoVitrina;
    private int modelo;
    private int tipoEntrepanos;
    private double recorteFondo;
    private double recorteVidrioFondo;
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

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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

    public int getCantidadVidrio() {
        return cantidadVidrio;
    }

    public void setCantidadVidrio(int cantidadVidrio) {
        this.cantidadVidrio = cantidadVidrio;
    }

    public int getCantidadAdactador() {
        return cantidadAdactador;
    }

    public void setCantidadAdactador(int cantidadAdactador) {
        this.cantidadAdactador = cantidadAdactador;
    }

    public double getRecorteJamba() {
        return recorteJamba;
    }

    public void setRecorteJamba(double recorteJamba) {
        this.recorteJamba = recorteJamba;
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

    public String getMensajeAdactador() {
        return mensajeAdactador;
    }

    public void setMensajeAdactador(String mensajeAdactador) {
        this.mensajeAdactador = mensajeAdactador;
    }

    public String getMensajeVidrio() {
        return mensajeVidrio;
    }

    public void setMensajeVidrio(String mensajeVidrio) {
        this.mensajeVidrio = mensajeVidrio;
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

    public double getRecorteAnchoPuertas() {
        return recorteAnchoPuertas;
    }

    public void setRecorteAnchoPuertas(double recorteAnchoPuertas) {
        this.recorteAnchoPuertas = recorteAnchoPuertas;
    }

    public double getRecorteAltoPuertas() {
        return recorteAltoPuertas;
    }

    public void setRecorteAltoPuertas(double recorteAltoPuertas) {
        this.recorteAltoPuertas = recorteAltoPuertas;
    }

    public double getRecorte4Puertas() {
        return recorte4Puertas;
    }

    public void setRecorte4Puertas(double recorte4Puertas) {
        this.recorte4Puertas = recorte4Puertas;
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

    public int getCantiPuerta() {
        return cantiPuerta;
    }

    public void setCantiPuerta(int cantiPuerta) {
        this.cantiPuerta = cantiPuerta;
    }

    public String getMensajeCantiPuerta() {
        return mensajeCantiPuerta;
    }

    public void setMensajeCantiPuerta(String mensajeCantiPuerta) {
        this.mensajeCantiPuerta = mensajeCantiPuerta;
    }

    public long getPrecioTotalCantidad() {
        return precioTotalCantidad;
    }

    public void setPrecioTotalCantidad(long precioTotalCantidad) {
        this.precioTotalCantidad = precioTotalCantidad;
    }

    public int getTipoVitrina() {
        return tipoVitrina;
    }

    public void setTipoVitrina(int tipoVitrina) {
        this.tipoVitrina = tipoVitrina;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getTipoEntrepanos() {
        return tipoEntrepanos;
    }

    public void setTipoEntrepanos(int tipoEntrepanos) {
        this.tipoEntrepanos = tipoEntrepanos;
    }

    public double getRecorteFondo() {
        return recorteFondo;
    }

    public void setRecorteFondo(double recorteFondo) {
        this.recorteFondo = recorteFondo;
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

    
    
    
    
    
    
    
    
}
