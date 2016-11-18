/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Pojos.Vitrinas;
import HibernateUtil.HibernateUtil;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author William Sanchez
 */
public class Vitrina {

    private int alto = 0;
    private int ancho = 0;
    private int fondo = 0;
    private long sumaTotal;
    private long subTotal;
    private int cuartoCirculo;
    private int anguloMedia;
    private int acoples;
    private int tianas;
    private int rodamientosPiso;
    private int naveDivisionBano;
    private int carrileras;
    private int rodamientosDucha;

    private int perfilEsquineroUna;
    private int tubularUnaAleta;
    private int rodamientoEconomico;
    private int anclajeA15;

    private int precioCuartoCirculo;
    private int precioAnguloMedia;
    private int precioNaveDivicionBano;
    private int precioCarrileras;
    private int empaque;
    private int precioPerfilEsquineroUna;
    private int precioTubularUna;
    private int precioEmpaque;

    private Vitrinas vitrinas;

    public Vitrina(String ancho, String alto, String fondo, int preTrabajo, int preDescuento, int preCuartoCirculo, int preAnguloMedia,
            int preAcoples, int preTianas, int preRodamientosPiso, int preNaveDvisionBano, int preCarrileras, int preRodamientoDucha,
            int preEmpaque, int tipoVitrinas, int prePerfilEsquineroUna, int preTubularUna, int preRodamientoEco, int preAnclaajeA15) {
        switch (alto.substring(2)) {
            case "1":
                this.alto = Integer.valueOf(alto) + 9;
                break;
            case "2":
                this.alto = Integer.valueOf(alto) + 8;
                break;
            case "3":
                this.alto = Integer.valueOf(alto) + 7;
                break;
            case "4":
                this.alto = Integer.valueOf(alto) + 6;
                break;
            case "5":
                this.alto = Integer.valueOf(alto) + 5;
                break;
            case "6":
                this.alto = Integer.valueOf(alto) + 4;
                break;
            case "7":
                this.alto = Integer.valueOf(alto) + 3;
                break;
            case "8":
                this.alto = Integer.valueOf(alto) + 2;
                break;
            case "9":
                this.alto = Integer.valueOf(alto) + 1;
                break;
            case "0":
                this.alto = Integer.valueOf(alto);
                break;
        }

        switch (ancho.substring(2)) {
            case "1":
                this.ancho = Integer.valueOf(ancho) + 9;
                break;
            case "2":
                this.ancho = Integer.valueOf(ancho) + 8;
                break;
            case "3":
                this.ancho = Integer.valueOf(ancho) + 7;
                break;
            case "4":
                this.ancho = Integer.valueOf(ancho) + 6;
                break;
            case "5":
                this.ancho = Integer.valueOf(ancho) + 5;
                break;
            case "6":
                this.ancho = Integer.valueOf(ancho) + 4;
                break;
            case "7":
                this.ancho = Integer.valueOf(ancho) + 3;
                break;
            case "8":
                this.ancho = Integer.valueOf(ancho) + 2;
                break;
            case "9":
                this.ancho = Integer.valueOf(ancho) + 1;
                break;
            case "0":
                this.ancho = Integer.valueOf(ancho);
                break;
        }
        switch (fondo.substring(2)) {
            case "1":
                this.fondo = Integer.valueOf(fondo) + 9;
                break;
            case "2":
                this.fondo = Integer.valueOf(fondo) + 8;
                break;
            case "3":
                this.fondo = Integer.valueOf(fondo) + 7;
                break;
            case "4":
                this.fondo = Integer.valueOf(fondo) + 6;
                break;
            case "5":
                this.fondo = Integer.valueOf(fondo) + 5;
                break;
            case "6":
                this.fondo = Integer.valueOf(fondo) + 4;
                break;
            case "7":
                this.fondo = Integer.valueOf(fondo) + 3;
                break;
            case "8":
                this.fondo = Integer.valueOf(fondo) + 2;
                break;
            case "9":
                this.fondo = Integer.valueOf(fondo) + 1;
                break;
            case "0":
                this.fondo = Integer.valueOf(fondo);
                break;
        }

        switch (tipoVitrinas) {

            case 1:

                this.cuartoCirculo = this.alto * 4 + this.ancho * 4 + this.fondo * 4;
                this.anguloMedia = this.alto * 6 + this.ancho * 6 + this.fondo * 8;
                this.carrileras = this.ancho * 2;
                this.naveDivisionBano = this.ancho * 2;
                this.acoples = (preAcoples * 100) * 8;
                this.tianas = (preTianas * 100) * 8;
                this.rodamientosPiso = (preRodamientosPiso * 100) * 4;
                this.rodamientosDucha = (preRodamientoDucha * 100) * 4;
                this.precioCuartoCirculo = this.cuartoCirculo * preCuartoCirculo;
                this.precioAnguloMedia = this.anguloMedia * preAnguloMedia;
                this.precioCarrileras = this.carrileras * preCarrileras;
                this.precioNaveDivicionBano = this.naveDivisionBano * preNaveDvisionBano;
                this.empaque = (this.ancho * 2) * preEmpaque;

                this.sumaTotal = this.precioCuartoCirculo + this.precioAnguloMedia
                        + this.acoples + this.tianas + this.precioCarrileras + this.precioNaveDivicionBano
                        + this.rodamientosPiso + this.rodamientosDucha;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;

            case 2:

                this.perfilEsquineroUna = this.ancho * 2 + this.fondo * 2;
                this.tubularUnaAleta = this.alto * 4 + this.ancho * 2 + this.fondo * 2;
                this.carrileras = this.ancho;
                this.naveDivisionBano = this.ancho;
                this.anguloMedia = this.alto * 6 + this.ancho * 6 + this.fondo * 8;
                this.tianas = (preTianas * 100) * 8;
                this.rodamientoEconomico = (preRodamientoEco * 100) * 4;
                this.anclajeA15 = (preAnclaajeA15 * 100) * 8;
                this.empaque = this.ancho * 2;

                this.precioPerfilEsquineroUna = this.perfilEsquineroUna * prePerfilEsquineroUna;
                this.precioTubularUna = tubularUnaAleta * preTubularUna;
                this.precioAnguloMedia = this.anguloMedia * preAnguloMedia;
                this.precioCarrileras = this.carrileras * preCarrileras;
                this.precioNaveDivicionBano = this.naveDivisionBano * preNaveDvisionBano;
                this.precioEmpaque = this.empaque * preEmpaque;

                this.sumaTotal = this.precioPerfilEsquineroUna + this.precioTubularUna
                        + this.precioCarrileras + this.precioNaveDivicionBano + this.precioAnguloMedia + this.tianas
                        + this.rodamientoEconomico + this.anclajeA15 + this.precioEmpaque;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;

            case 3:

                this.perfilEsquineroUna = this.ancho * 2 + this.fondo * 2;
                this.tubularUnaAleta = this.alto * 6 + this.ancho * 2 + this.fondo * 2;
                this.carrileras = this.ancho;
                this.naveDivisionBano = this.ancho;
                this.anguloMedia = this.alto * 6 + this.ancho * 6 + this.fondo * 8;
                this.tianas = (preTianas * 100) * 16;
                this.rodamientoEconomico = (preRodamientoEco * 100) * 4;
                this.anclajeA15 = (preAnclaajeA15 * 100) * 8;
                this.empaque = (this.ancho * 2) * preEmpaque;
                this.precioPerfilEsquineroUna = this.perfilEsquineroUna * prePerfilEsquineroUna;
                this.precioTubularUna = tubularUnaAleta * preTubularUna;
                this.precioAnguloMedia = this.anguloMedia * preAnguloMedia;
                this.precioCarrileras = this.carrileras * preCarrileras;
                this.precioNaveDivicionBano = this.naveDivisionBano * preNaveDvisionBano;
                this.precioEmpaque = empaque = this.precioEmpaque;

                this.sumaTotal = this.precioPerfilEsquineroUna + this.precioTubularUna
                        + this.precioCarrileras + this.precioNaveDivicionBano + this.precioAnguloMedia + this.tianas
                        + this.rodamientoEconomico + this.anclajeA15 + this.precioEmpaque;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;

        }

    }

    public Vitrina() {

    }

    public final int recortar(String numero) {
        int espacios = numero.length();
        return Integer.valueOf(numero.substring(0, espacios - 2));

    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getFondo() {
        return fondo;
    }

    public void setFondo(int fondo) {
        this.fondo = fondo;
    }

    public long getSumaTotal() {
        return sumaTotal;
    }

    public void setSumaTotal(long sumaTotal) {
        this.sumaTotal = sumaTotal;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    public int getCuartoCirculo() {
        return cuartoCirculo;
    }

    public void setCuartoCirculo(int cuartoCirculo) {
        this.cuartoCirculo = cuartoCirculo;
    }

    public int getAnguloMedia() {
        return anguloMedia;
    }

    public void setAnguloMedia(int anguloMedia) {
        this.anguloMedia = anguloMedia;
    }

    public int getAcoples() {
        return acoples;
    }

    public void setAcoples(int acoples) {
        this.acoples = acoples;
    }

    public int getTianas() {
        return tianas;
    }

    public void setTianas(int tianas) {
        this.tianas = tianas;
    }

    public int getRodamientosPiso() {
        return rodamientosPiso;
    }

    public void setRodamientosPiso(int rodamientosPiso) {
        this.rodamientosPiso = rodamientosPiso;
    }

    public int getNaveDivisionBano() {
        return naveDivisionBano;
    }

    public void setNaveDivisionBaño(int naveDivisionBano) {
        this.naveDivisionBano = naveDivisionBano;
    }

    public int getCarrileras() {
        return carrileras;
    }

    public void setCarrileras(int carrileras) {
        this.carrileras = carrileras;
    }

    public int getRodamientosDucha() {
        return rodamientosDucha;
    }

    public void setRodamientosDucha(int rodamientosDucha) {
        this.rodamientosDucha = rodamientosDucha;
    }

    public int getPrecioCuartoCirculo() {
        return precioCuartoCirculo;
    }

    public void setPrecioCuartoCirculo(int precioCuartoCirculo) {
        this.precioCuartoCirculo = precioCuartoCirculo;
    }

    public int getPrecioAnguloMedia() {
        return precioAnguloMedia;
    }

    public void setPrecioAnguloMedia(int precioAnguloMedia) {
        this.precioAnguloMedia = precioAnguloMedia;
    }

    public int getPrecioNaveDivicionBano() {
        return precioNaveDivicionBano;
    }

    public void setPrecioNaveDivicionBano(int precioNaveDivicionBano) {
        this.precioNaveDivicionBano = precioNaveDivicionBano;
    }

    public int getPrecioCarrileras() {
        return precioCarrileras;
    }

    public void setPrecioCarrileras(int precioCarrileras) {
        this.precioCarrileras = precioCarrileras;
    }

    public int getEmpaque() {
        return empaque;
    }

    public void setEmpaque(int empaque) {
        this.empaque = empaque;
    }

    public Vitrinas getVitrinas() {
        return vitrinas;
    }

    public void setVitrinas(Vitrinas vitrinas) {
        this.vitrinas = vitrinas;
    }

    public int getPerfilEsquineroUna() {
        return perfilEsquineroUna;
    }

    public void setPerfilEsquineroUna(int perfilEsquineroUna) {
        this.perfilEsquineroUna = perfilEsquineroUna;
    }

    public int getTubularUnaAleta() {
        return tubularUnaAleta;
    }

    public void setTubularUnaAleta(int tubularUnaAleta) {
        this.tubularUnaAleta = tubularUnaAleta;
    }

    public int getRodamientoEconomico() {
        return rodamientoEconomico;
    }

    public void setRodamientoEconomico(int rodamientoEconomico) {
        this.rodamientoEconomico = rodamientoEconomico;
    }

    public int getAnclajeA15() {
        return anclajeA15;
    }

    public void setAnclajeA15(int anclajeA15) {
        this.anclajeA15 = anclajeA15;
    }

    public int getPrecioPerfilEsquineroUna() {
        return precioPerfilEsquineroUna;
    }

    public void setPrecioPerfilEsquineroUna(int precioPerfilEsquineroUna) {
        this.precioPerfilEsquineroUna = precioPerfilEsquineroUna;
    }

    public int getPrecioTubularUna() {
        return precioTubularUna;
    }

    public void setPrecioTubularUna(int precioTubularUna) {
        this.precioTubularUna = precioTubularUna;
    }

    public int getPrecioEmpaque() {
        return precioEmpaque;
    }

    public void setPrecioEmpaque(int precioEmpaque) {
        this.precioEmpaque = precioEmpaque;
    }

}
