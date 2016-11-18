/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

public class Puerta {

    private int alto = 0;
    private int ancho = 0;
    private long sumaTotal;
    private long subTotal;
    private int aluminioT87;
    private int aluminio3;
    private int partidorT103;
    private int pisavidrios;
    private int chapaPuerta;
    private int pibotesAmericanos;
    private int varillaTensora;
    private int escuadras;
    private int entamborado;
    private int alamoUnoMedia;
    private int entamboradoF06;
    private int bisagras;
    private int empaque;
    private int precioAluminioT87;
    private int precioAluminio3;
    private int precioPartidorT103;
    private int precioPisavidrios;
    private int precioChapaPuerta;
    private int precioPibotesAmericanos;
    private int precioVarillaTensora;
    private int precioEscuadras;
    private int perfilU71;
    private int precioPerfilU71;
    private int precioEntamborado;
    private int precioEntamboradoF06;
    private int precioAlamoUnoMedia;
    private int precioBisagras;
    private int precioEmpaque;

    public Puerta(String ancho, String alto, int preTrabajo, int preDescuento, int preAluminiot87, int preAluminio3,
            int prePartidorT103, int prePisavidrios, int prePibotesAmericanos,
            int preVarillaTensora, int preChapaPuerta, int preEscuadras, int prePerfilU71, int tipoPuerta,
            int preEntamborado, int preEntamboradoF06, int preAlamoUnoMedia, int preBisagras, int preEmpaque) {
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

        switch (tipoPuerta) {

            case 1:
                //marco de la pared
                this.aluminio3 = this.alto * 2 + this.ancho * 1;
                //marco puerta
                this.aluminioT87 = this.alto * 2 + this.ancho * 2;
                this.partidorT103 = this.ancho;
                //  this.partidorT103=recortar(String.valueOf(partidorT103));
                this.empaque = this.alto * 2 + this.ancho * 4;
                this.precioAluminio3 = this.aluminio3 * preAluminio3;
                this.precioAluminioT87 = this.aluminioT87 * preAluminiot87;
                this.precioPartidorT103 = this.partidorT103 * prePartidorT103;
                this.pisavidrios = this.ancho * 8 + this.alto * 4;
                this.precioPisavidrios = pisavidrios * prePisavidrios;
                this.precioEmpaque = this.empaque * preEmpaque;
                this.pibotesAmericanos = prePibotesAmericanos;
                this.chapaPuerta = preChapaPuerta;
                this.varillaTensora = (preVarillaTensora * 100) * 2;
                this.precioEscuadras = (preEscuadras * 100) * 12;

                this.sumaTotal = this.precioAluminio3 + this.precioAluminioT87
                        + this.precioPartidorT103 + this.precioPisavidrios + this.precioEmpaque + this.chapaPuerta
                        + this.pibotesAmericanos + this.varillaTensora + this.precioEscuadras;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;

            case 2:

                this.aluminio3 = this.alto * 2 + this.ancho * 1;

                this.partidorT103 = this.alto * 2 + this.ancho * 2;
                this.entamborado = (this.ancho / 7) * this.alto;
                this.perfilU71 = this.ancho * 4 + this.alto * 4;

                this.precioPerfilU71 = this.perfilU71 * prePerfilU71;
                this.precioPartidorT103 = this.partidorT103 * prePartidorT103;
                this.precioAluminio3 = this.aluminio3 * preAluminio3;
                this.precioEntamborado = this.entamborado * preEntamborado;

                this.pibotesAmericanos = prePibotesAmericanos;
                this.chapaPuerta = preChapaPuerta;
                this.varillaTensora = (preVarillaTensora * 100) * 2;
                this.escuadras = (preEscuadras * 100) * 12;

                this.sumaTotal = this.precioAluminio3 + this.chapaPuerta + this.precioPartidorT103
                        + this.pibotesAmericanos + this.varillaTensora + this.escuadras
                        + this.precioPerfilU71 + this.precioEntamborado;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;

            case 3:
                //marco de la pared
                this.aluminio3 = this.alto * 2 + this.ancho * 1;
                //marco puerta
                this.aluminioT87 = this.alto * 2 + this.ancho * 2;

                this.entamboradoF06 = ((this.ancho / 7) * this.alto);
                //  this.partidorT103=recortar(String.valueOf(partidorT103));

                this.precioAluminio3 = this.aluminio3 * preAluminio3;
                this.precioAluminioT87 = this.aluminioT87 * preAluminiot87;
                this.pisavidrios = this.ancho * 8 + this.alto * 4;
                this.precioPisavidrios = this.pisavidrios * prePisavidrios;
                this.pibotesAmericanos = prePibotesAmericanos;
                this.chapaPuerta = preChapaPuerta;
                this.varillaTensora = (preVarillaTensora * 100) * 2;
                this.precioEscuadras = (preEscuadras * 100) * 12;
                this.precioEntamboradoF06 = this.entamboradoF06 * preEntamboradoF06;

                this.sumaTotal = this.precioAluminio3 + this.precioAluminioT87
                        + this.precioPisavidrios + this.chapaPuerta
                        + this.pibotesAmericanos + this.varillaTensora + this.precioEscuadras + this.precioEntamboradoF06;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;

            case 4:

                this.aluminio3 = this.alto * 2 + this.ancho * 1;
                this.partidorT103 = this.alto * 2 + this.ancho * 3;               
                this.pisavidrios = this.ancho * 4 + this.alto * 2;
                 this.empaque = this.alto * 2 + this.ancho * 4;
                 
                 this.precioPartidorT103 = this.partidorT103 * prePartidorT103;
                this.precioPisavidrios = this.pisavidrios * prePisavidrios;              
                this.precioAluminio3 = this.aluminio3 * preAluminio3;
                this.precioEmpaque = this.empaque * preEmpaque;
                this.pibotesAmericanos = (prePibotesAmericanos *100)*1;
                this.chapaPuerta = (preChapaPuerta * 100)*1;
                this.varillaTensora = (preVarillaTensora * 100) * 2;
                this.escuadras = (preEscuadras * 100) * 12;

                this.sumaTotal = this.precioAluminio3 + this.precioPartidorT103 + this.precioEmpaque + this.chapaPuerta
                        + this.pibotesAmericanos + this.varillaTensora + this.escuadras + this.precioPisavidrios;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;

            case 5:
                this.aluminio3 = this.alto * 2 + this.ancho * 1;
                this.alamoUnoMedia = this.alto * 2 + this.ancho * 2;

                this.partidorT103 = this.ancho;
                this.empaque = this.alto * 4 + this.ancho * 8;
                this.precioAluminio3 = this.aluminio3 * preAluminio3;
                this.precioPartidorT103 = this.partidorT103 * prePartidorT103;

                this.precioAlamoUnoMedia = this.alamoUnoMedia * preAlamoUnoMedia;
                this.precioEmpaque = this.empaque * preEmpaque;
                this.bisagras = (preBisagras * 100) * 2;
                this.chapaPuerta = preChapaPuerta;
                this.varillaTensora = (preVarillaTensora * 100) * 2;
                this.escuadras = (preEscuadras * 100) * 12;

                this.sumaTotal = this.precioAluminio3 + this.precioPartidorT103 + this.precioAlamoUnoMedia
                        + this.bisagras + this.chapaPuerta + this.varillaTensora + this.precioEmpaque + this.escuadras;

                this.subTotal = this.sumaTotal;

                this.sumaTotal = this.sumaTotal + (this.sumaTotal * preDescuento / 100);
                preTrabajo = (preTrabajo * 100);
                this.sumaTotal = this.sumaTotal + preTrabajo;
                this.sumaTotal = recortar(String.valueOf(sumaTotal));

                break;

        }

    }

    public Puerta() {
    }

    public final int recortar(String numero) {
        int espacios = numero.length();
        return Integer.valueOf(numero.substring(0, espacios - 2));
    }

    public int getAluminioT87() {
        return aluminioT87;
    }

    public void setAluminioT87(int aluminioT87) {
        this.aluminioT87 = aluminioT87;
    }

    public int getAluminio3() {
        return aluminio3;
    }

    public void setAluminio3(int aluminio3) {
        this.aluminio3 = aluminio3;
    }

    public int getPartidorT103() {
        return partidorT103;
    }

    public void setPartidorT103(int partidorT103) {
        this.partidorT103 = partidorT103;
    }

    public int getPisavidrios() {
        return pisavidrios;
    }

    public void setPisavidrios(int pisavidrios) {
        this.pisavidrios = pisavidrios;
    }

    public int getChapaPuerta() {
        return chapaPuerta;
    }

    public void setChapaPuerta(int chapaPuerta) {
        this.chapaPuerta = chapaPuerta;
    }

    public int getPibotesAmericanos() {
        return pibotesAmericanos;
    }

    public void setPibotesAmericanos(int pibotesAmericanos) {
        this.pibotesAmericanos = pibotesAmericanos;
    }

    public int getVarillaTensora() {
        return varillaTensora;
    }

    public void setVarillaTensora(int varillaTensora) {
        this.varillaTensora = varillaTensora;
    }

    public int getEscuadras() {
        return escuadras;
    }

    public void setEscuadras(int escuadras) {
        this.escuadras = escuadras;
    }

    public int getPrecioAluminioT87() {
        return precioAluminioT87;
    }

    public void setPrecioAluminioT87(int precioAluminioT87) {
        this.precioAluminioT87 = precioAluminioT87;
    }

    public int getPrecioAluminio3() {
        return precioAluminio3;
    }

    public void setPrecioAluminio3(int precioAluminio3) {
        this.precioAluminio3 = precioAluminio3;
    }

    public int getPrecioPisavidrios() {
        return precioPisavidrios;
    }

    public void setPrecioPisavidrios(int precioPisavidrios) {
        this.precioPisavidrios = precioPisavidrios;
    }

    public int getPrecioEscuadras() {
        return precioEscuadras;
    }

    public void setPrecioEscuadras(int precioEscuadras) {
        this.precioEscuadras = precioEscuadras;
    }

    public int getPerfilU71() {
        return perfilU71;
    }

    public void setPerfilU71(int perfilU71) {
        this.perfilU71 = perfilU71;
    }

    public int getPrecioPerfilU71() {
        return precioPerfilU71;
    }

    public void setPrecioPerfilU71(int precioPerfilU71) {
        this.precioPerfilU71 = precioPerfilU71;
    }

    public int getPrecioPartidorT103() {
        return precioPartidorT103;
    }

    public void setPrecioPartidorT103(int precioPartidorT103) {
        this.precioPartidorT103 = precioPartidorT103;
    }

    public int getPrecioChapaPuerta() {
        return precioChapaPuerta;
    }

    public void setPrecioChapaPuerta(int precioChapaPuerta) {
        this.precioChapaPuerta = precioChapaPuerta;
    }

    public int getPrecioPibotesAmericanos() {
        return precioPibotesAmericanos;
    }

    public void setPrecioPibotesAmericanos(int precioPibotesAmericanos) {
        this.precioPibotesAmericanos = precioPibotesAmericanos;
    }

    public int getPrecioVarillaTensora() {
        return precioVarillaTensora;
    }

    public void setPrecioVarillaTensora(int precioVarillaTensora) {
        this.precioVarillaTensora = precioVarillaTensora;
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

    public int getEntamborado() {
        return entamborado;
    }

    public void setEntamborado(int entamborado) {
        this.entamborado = entamborado;
    }

    public int getAlamoUnoMedia() {
        return alamoUnoMedia;
    }

    public void setAlamoUnoMedia(int alamoUnoMedia) {
        this.alamoUnoMedia = alamoUnoMedia;
    }

    public int getEntamboradoF06() {
        return entamboradoF06;
    }

    public void setEntamboradoF06(int entamboradoF06) {
        this.entamboradoF06 = entamboradoF06;
    }

    public int getBisagras() {
        return bisagras;
    }

    public void setBisagras(int bisagras) {
        this.bisagras = bisagras;
    }

    public int getPrecioEntamborado() {
        return precioEntamborado;
    }

    public void setPrecioEntamborado(int precioEntamborado) {
        this.precioEntamborado = precioEntamborado;
    }

    public int getPrecioEntamboradoF06() {
        return precioEntamboradoF06;
    }

    public void setPrecioEntamboradoF06(int precioEntamboradoF06) {
        this.precioEntamboradoF06 = precioEntamboradoF06;
    }

    public int getPrecioAlamoUnoMedia() {
        return precioAlamoUnoMedia;
    }

    public void setPrecioAlamoUnoMedia(int precioAlamoUnoMedia) {
        this.precioAlamoUnoMedia = precioAlamoUnoMedia;
    }

    public int getPrecioBisagras() {
        return precioBisagras;
    }

    public void setPrecioBisagras(int precioBisagras) {
        this.precioBisagras = precioBisagras;
    }

    public int getEmpaque() {
        return empaque;
    }

    public void setEmpaque(int empaque) {
        this.empaque = empaque;
    }

    public int getPrecioEmpaque() {
        return precioEmpaque;
    }

    public void setPrecioEmpaque(int precioEmpaque) {
        this.precioEmpaque = precioEmpaque;
    }

}
