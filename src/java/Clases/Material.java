/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;




public class Material {

   

    private int alto = 0;
    private int ancho = 0;
    private long sumaTotal;
    private long subTotal;
    private int jamba;
    private int cabezal;
    private int sillar;
    private int traslape;
    private int enganche;
    private int hSuperior;
    private int hInferior;
    private int precioCabezal;
    private int precioSillar;
    private int precioJamba;
    private int precioEnganche;
    private int precioTraslape;
    private int precioHsuperior;
    private int precioHinferior;
    private int rodamiento;
    private int chapa;
    private int guias;
    private int empaque;
    private int adaptador;
  

   

    public Material(String ancho, String alto, int preTrabajo, int preDescuento, int preCabezal,
            int preJamba, int preSillar, int preEnganche, int preTraslape, int preHsuper, int preHinfe,
            int preGuias, int preChapa, int preRodamiento, int preEmpaque, int tipoVentana, int adaptador) {
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

        switch (tipoVentana) {
            case 1:
                this.jamba = this.alto * 2;
                this.cabezal = this.ancho;
                this.sillar = this.ancho;
                this.traslape = this.jamba;
                this.enganche = this.jamba;
                this.hSuperior = this.cabezal;
                this.hInferior = this.cabezal;

                this.precioCabezal = this.cabezal * preCabezal;
                this.precioSillar = this.sillar * preSillar;
                this.precioJamba = this.jamba * preJamba;
                this.precioEnganche = this.enganche * preEnganche;
                this.precioTraslape = this.traslape * preTraslape;
                this.precioHsuperior = this.hSuperior * preHsuper;
                this.precioHinferior = this.hInferior * preHinfe;
                this.rodamiento = (preRodamiento *100)*2;
                this.chapa = preChapa;
                this.guias = (preGuias * 100) * 8;
                this.empaque = (this.ancho * 2 + this.alto * 4) * preEmpaque;

                this.sumaTotal = this.precioCabezal + this.precioSillar
                        + this.precioJamba + this.precioEnganche + this.precioTraslape
                        + this.precioHsuperior + this.precioHinferior + this.rodamiento
                        + this.chapa + this.guias + this.empaque;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;

            case 2:

                this.jamba = this.alto * 2;
                this.cabezal = this.ancho;
                this.sillar = this.ancho;
                this.traslape = this.alto * 2;
                this.enganche = this.alto * 4;
                this.hSuperior = this.cabezal;
                this.hInferior = this.cabezal;

                this.precioCabezal = this.cabezal * preCabezal;
                this.precioSillar = this.sillar * preSillar;
                this.precioJamba = this.jamba * preJamba;
                this.precioEnganche = this.enganche * preEnganche;
                this.precioTraslape = this.traslape * preTraslape;
                this.precioHsuperior = this.hSuperior * preHsuper;
                this.precioHinferior = this.hInferior * preHinfe;
                this.rodamiento = (preRodamiento * 100) * 4;
                this.chapa = (preChapa * 100) * 2;
                this.guias = (preGuias * 100) * 8;
                this.empaque = (this.ancho * 2 + this.alto * 6) * preEmpaque;

                this.sumaTotal = this.precioCabezal + this.precioSillar
                        + this.precioJamba + this.precioEnganche + this.precioTraslape
                        + this.precioHsuperior + this.precioHinferior + this.rodamiento
                        + this.chapa + this.guias + this.empaque;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;

            case 3:

                this.jamba = this.alto * 2;
                this.cabezal = this.ancho;
                this.sillar = this.ancho;
                this.traslape = this.alto * 4;
                this.enganche = this.alto * 4;
                this.hSuperior = this.cabezal;
                this.hInferior = this.cabezal;

                this.adaptador = this.alto * adaptador;
                this.precioCabezal = this.cabezal * preCabezal;
                this.precioSillar = this.sillar * preSillar;
                this.precioJamba = this.jamba * preJamba;
                this.precioEnganche = this.enganche * preEnganche;
                this.precioTraslape = this.traslape * preTraslape;
                this.precioHsuperior = this.hSuperior * preHsuper;
                this.precioHinferior = this.hInferior * preHinfe;
                this.rodamiento = (preRodamiento * 100) * 4;
                this.chapa = (preChapa * 100) * 2;
                this.guias = (preGuias * 100) * 8;
                this.empaque = (this.ancho * 2 + this.alto * 8) * preEmpaque;

                this.sumaTotal = this.precioCabezal + this.precioSillar
                        + this.precioJamba + this.precioEnganche + this.precioTraslape
                        + this.precioHsuperior + this.precioHinferior + this.rodamiento
                        + this.chapa + this.guias + this.empaque + this.adaptador;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;
                
                
                
//               estas son laa que ponemos en el constructor 
//             
                
////                   case 4:
//
//                this.aluminio3 = this.alto * 2 + this.ancho * 2;
//                this.aluminioT87 = this.alto *2 + this.ancho *2;
//                this.partidorT103 = this.ancho *prePartidorT103;
//             
//          
//                this.precioAluminio3 = this.aluminio3 * preAluminio3;
//                this.precioAluminioT87 = this.aluminioT87 * preAluminiot87;
//                this.pisavidrios = (this.ancho * 4 + this.alto * 4) * prePisavidrios; 
//              
//                this.pibotesAmericanos = prePibotesAmericanos;
//                this.chapaPuerta = preChapaPuerta;
//                this.varillaTensora = (preVarillaTensora * 100) * 2;
//                this.escuadras = (this.precioEscuadras * 8) * preEscuadras;
//               
//
//                this.sumaTotal = this.precioAluminio3 + this.precioAluminioT87
//                        + this.partidorT103 + this.pisavidrios + this.chapaPuerta
//                        + this.pibotesAmericanos + this.varillaTensora + this.escuadras;
//                        
//
//                this.subTotal = this.sumaTotal;
//
//                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
//                preTrabajo = (preTrabajo * 100);
//                this.sumaTotal = this.sumaTotal + preTrabajo;
//                this.sumaTotal = recortar(String.valueOf(sumaTotal));
//
//                break;
//                       
//                case 5:
//
//                this.aluminio3 = this.alto * 2 + this.ancho * 2;
//                this.partidorT103 = this.alto *2 + this.ancho *3;
//                this.perfilU71 = (this.ancho * 4 + this.alto * 4) * prePerfilU71;             
//          
//                this.precioAluminio3 = this.aluminio3 * preAluminio3;
//                this.precioAluminioT87 = this.aluminioT87 * preAluminiot87;
//              
//                this.pibotesAmericanos = prePibotesAmericanos;
//                this.chapaPuerta = preChapaPuerta;
//                this.varillaTensora = (preVarillaTensora * 100) * 2;
//                this.escuadras = (this.precioEscuadras * 8) * preEscuadras;
//               
//
//                this.sumaTotal = this.precioAluminio3 + this.precioAluminioT87
//                        + this.partidorT103 + this.pisavidrios + this.chapaPuerta
//                        + this.pibotesAmericanos + this.varillaTensora + this.escuadras;
//                        
//
//                this.subTotal = this.sumaTotal;
//
//                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
//                preTrabajo = (preTrabajo * 100);
//                this.sumaTotal = this.sumaTotal + preTrabajo;
//                this.sumaTotal = recortar(String.valueOf(sumaTotal));
//
//                break;
        
        }

    }

    public Material() {
    }
    
    
    

    public final int recortar(String numero) {
        int espacios = numero.length();
        return Integer.valueOf(numero.substring(0, espacios - 2));
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public int getJamba() {
        return jamba;
    }

    public int getCabezal() {
        return cabezal;
    }

    public int getSillar() {
        return sillar;
    }

    public int getTraslape() {
        return traslape;
    }

    public int getEnganche() {
        return enganche;
    }

    public int gethSuperior() {
        return hSuperior;
    }

    public int gethInferior() {
        return hInferior;
    }

    public int getPrecioCabezal() {
        return precioCabezal;
    }

    public int getPrecioSillar() {
        return precioSillar;
    }

    public int getPrecioJamba() {
        return precioJamba;
    }

    public int getPrecioEnganche() {
        return precioEnganche;
    }

    public int getPrecioTraslape() {
        return precioTraslape;
    }

    public int getPrecioHsuperior() {
        return precioHsuperior;
    }

    public int getPrecioHinferior() {
        return precioHinferior;
    }

    public int getRodamiento() {
        return rodamiento;
    }

    public int getChapa() {
        return chapa;
    }

    public int getGuias() {
        return guias;
    }

    public long getSumaTotal() {
        return sumaTotal;
    }

    public int getEmpaque() {
        return empaque;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public int getAdaptador() {
        return adaptador;
    }

    public void setAdaptador(int adaptador) {
        this.adaptador = adaptador;
    }


}
