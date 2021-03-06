package Pojos;
// Generated 2/11/2016 10:27:29 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * Sueldo generated by hbm2java
 */
public class Sueldo  implements java.io.Serializable {


     private Integer idsueldo;
     private Empleado empleado;
     private BigDecimal sueldoBase;
     private BigDecimal comision;
     private Integer horasExtras;
     private BigDecimal descuentos;
     private BigDecimal sueldoTotal;
     private Date fechaCreacionSueldo;

    public Sueldo() {
    }

	
    public Sueldo(Empleado empleado, BigDecimal sueldoBase, BigDecimal comision, BigDecimal sueldoTotal, Date fechaCreacionSueldo) {
        this.empleado = empleado;
        this.sueldoBase = sueldoBase;
        this.comision = comision;
        this.sueldoTotal = sueldoTotal;
        this.fechaCreacionSueldo = fechaCreacionSueldo;
    }
    public Sueldo(Empleado empleado, BigDecimal sueldoBase, BigDecimal comision, Integer horasExtras, BigDecimal descuentos, BigDecimal sueldoTotal, Date fechaCreacionSueldo) {
       this.empleado = empleado;
       this.sueldoBase = sueldoBase;
       this.comision = comision;
       this.horasExtras = horasExtras;
       this.descuentos = descuentos;
       this.sueldoTotal = sueldoTotal;
       this.fechaCreacionSueldo = fechaCreacionSueldo;
    }
   
    public Integer getIdsueldo() {
        return this.idsueldo;
    }
    
    public void setIdsueldo(Integer idsueldo) {
        this.idsueldo = idsueldo;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public BigDecimal getSueldoBase() {
        return this.sueldoBase;
    }
    
    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }
    public BigDecimal getComision() {
        return this.comision;
    }
    
    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }
    public Integer getHorasExtras() {
        return this.horasExtras;
    }
    
    public void setHorasExtras(Integer horasExtras) {
        this.horasExtras = horasExtras;
    }
    public BigDecimal getDescuentos() {
        return this.descuentos;
    }
    
    public void setDescuentos(BigDecimal descuentos) {
        this.descuentos = descuentos;
    }
    public BigDecimal getSueldoTotal() {
        return this.sueldoTotal;
    }
    
    public void setSueldoTotal(BigDecimal sueldoTotal) {
        this.sueldoTotal = sueldoTotal;
    }
    public Date getFechaCreacionSueldo() {
        return this.fechaCreacionSueldo;
    }
    
    public void setFechaCreacionSueldo(Date fechaCreacionSueldo) {
        this.fechaCreacionSueldo = fechaCreacionSueldo;
    }




}


