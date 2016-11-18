/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Clases.Encrypt;
import Daos.DaoCiudad;
import Daos.DaoEmpleado;
import Daos.DaoRol;
import Daos.DaoTipodocumento;
import HibernateUtil.HibernateUtil;
import Pojos.Empleado;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author William Sanchez
 */
@ManagedBean
@ViewScoped
public class BeanEmpleado {

    /**
     * Creates a new instance of BeanEmpleado
     */
    private Session session;
    private Transaction transaccion;
    private Empleado empleado;
    private List<Empleado> listaEmpleado;
    private List<Empleado> listaEmpleadoFiltrado;
    private String ContraseniaRepita;
    private String numerodocumento;
    private String correoElectronico;
    private int CodigoCiudad;
    private int CodigoRol;
    private int CodigotipoDocumento;
    private Empleado empleadoSelect;
     private UploadedFile avatar;

    public BeanEmpleado() {
          this.empleado = new Empleado();
            this.empleado.setIdempleado(Integer.MIN_VALUE);
            this.empleado.setNombre("");
            this.empleado.setApellidos("");
            this.empleado.setCorreoElectronico("");
            this.empleado.setNumeroDocumento("");
            this.empleado.getFechaNacimiento();
            this.empleado.setGenero(true);
            this.empleado.setFechaIngreso(new Date());
            this.CodigoCiudad=0;
            this.CodigoRol=0;
            this.CodigotipoDocumento=0;
    }

    public void register() {
 
        this.session = null;
        this.transaccion = null;

        try {
            if (this.empleado.getNombre().equals("") || this.empleado.getNombre().length() < 4) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return;
            }
            if (this.empleado.getApellidos().equals("") || this.empleado.getApellidos().length() < 4) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return;
            }
            if (this.empleado.getNumeroDocumento().equals("") || this.empleado.getNombre().length() < 6) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return;
            }
             if (this.empleado.getPassword().equals("") || this.empleado.getPassword().length()<3) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "La comtraseña es muy corta"));
                return;
            }
              if (!this.empleado.getPassword().equals(this.ContraseniaRepita)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo contraseña es obligatorio"));
                return;
            }
              if (this.empleado.getNumeroDocumento().equals("") || this.empleado.getNombre().length() < 6) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return;
            }
               if (this.empleado.getNumeroDocumento().equals("") || this.empleado.getNombre().length() < 6) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return;
            }
            
             if(this.CodigoCiudad==0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El por favor escoja un Ciudad."));
               return;
             }
                 if(this.CodigoRol==0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El por favor escoja un Rol."));
               return;
            }
                   if(this.CodigotipoDocumento==0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El por favor escoja un Tipo de Documento."));
               return;
            }

            DaoEmpleado daoEmpleado = new DaoEmpleado();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            if (daoEmpleado.getByCorreoElectronico(this.session, this.empleado.getCorreoElectronico()) != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El usuario ya se encuentra registrado en el sistema"));

                return;
            }
             if (daoEmpleado.getByNumeroDocumento(this.session, this.empleado.getNumeroDocumento())!= null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El Empleado con ese numero ya se encuentra registrado en el sistema"));

                return;
            }
            DaoCiudad daoCiudad = new DaoCiudad();
            DaoRol daoRol = new DaoRol();
            DaoTipodocumento daoTipodocumento = new DaoTipodocumento();

            this.empleado.setCiudad(daoCiudad.getByCodigoCiudad(this.session, this.CodigoCiudad));
            this.empleado.setRol(daoRol.getByRol(this.session, this.CodigoRol));
            this.empleado.setTipodocumento(daoTipodocumento.getByCodigoTipodocumeno(this.session,this.CodigotipoDocumento));
            this.empleado.setPassword(Encrypt.sha512(this.empleado.getPassword()));
            daoEmpleado.registar(this.session, this.empleado);
               this.empleado = new Empleado();
            this.empleado.setIdempleado(Integer.MIN_VALUE);
            this.transaccion.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro fue realizado correctamente"));
          this.empleado = new Empleado();
            this.empleado.setIdempleado(Integer.MIN_VALUE);
            this.empleado.setNombre("");
            this.empleado.setApellidos("");
            this.empleado.setCorreoElectronico("");
            this.empleado.setNumeroDocumento("");
            this.empleado.getFechaNacimiento();
            this.empleado.setGenero(true);
            this.empleado.setFechaIngreso(new Date());
            this.CodigoCiudad=0;
            this.CodigoRol=0;
            this.CodigotipoDocumento=0;

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

    public Empleado getById(int id) {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoEmpleado daoEmpleado = new DaoEmpleado();

            Empleado emplead = daoEmpleado.getById(this.session, id);
            this.transaccion.commit();
            return emplead;

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

    public List<Empleado> getAll() {
        this.session = null;
        this.transaccion = null;

        try {
            DaoEmpleado daoEmpleado = new DaoEmpleado();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaEmpleado = daoEmpleado.getAll(this.session);

            this.transaccion.commit();

            return this.listaEmpleado;
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

    public void actualizar() {
        this.session = null;
        this.transaccion = null;

        try {
            DaoEmpleado daoEmpleado = new DaoEmpleado();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            if (this.empleadoSelect.getNombre().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es obligatorio"));
                return;
            }

            if (this.empleadoSelect.getApellidos().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es obligatorio"));
                return;
            }

            daoEmpleado.actualizar(this.session, this.empleadoSelect);

            this.transaccion.commit();

            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.setAttribute("correoElectronico", this.empleado.getCorreoElectronico());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Los cambios fueron guardados correctamente"));
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

    public void cargarEmpleadoEditar(int codigoEmpleado) {
        this.session = null;
        this.transaccion = null;

        try {
            DaoEmpleado daoEmpleado = new DaoEmpleado();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            this.empleadoSelect = daoEmpleado.getById(this.session, codigoEmpleado);
//            this.transaccion.commit();
            RequestContext.getCurrentInstance().update("frmEditarEmpleado:panelEditarEmpleado");
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarEmpleado').show()");

            this.transaccion.commit();
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

    public Empleado getByCorreoElectronico() {
        this.session = null;
        this.transaccion = null;

        try {
            DaoEmpleado daoEmpleado = new DaoEmpleado();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sessionEmpleado = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            this.empleado = daoEmpleado.getByCorreoElectronico(this.session, sessionEmpleado.getAttribute("correoElectronico").toString());

            this.transaccion.commit();

            return this.empleado;
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

         public void actualizarAvatar() throws IOException {

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {

            if (this.avatar.getSize() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Ud. debe seleccionar un archivo de imagen \".png\""));
                return;
            }

            if (this.avatar.getFileName().endsWith(".png") || this.avatar.getFileName().endsWith(".jpg")) {

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "No se reconoce la extencion del archivo"));
                return;
            }

            if (this.avatar.getSize() > 2097152) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo no puede ser más de 2mb"));
                return;
            }

//             HttpSession sessionUsuario = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String carpetaAvatar = (String) servletContext.getRealPath("/carpetaAvatar");
          
            
             

            outputStream = new FileOutputStream(new File(carpetaAvatar + "/" + this.empleadoSelect.getIdempleado() + ".png"));
            inputStream = this.avatar.getInputstream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            actualizarFoto(getByCorreoElectronico().getIdempleado() + this.avatar.getFileName());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Avatar actualizado correctamente"));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));

        } finally {

            if (inputStream != null) {
                inputStream.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

    public void actualizarFoto(String nombreFoto) {
        this.session = null;
        this.transaccion = null;

        try {
            
            
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            Empleado emple;
            emple = getByCorreoElectronico();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            emple.setImgPrincipal(nombreFoto);
            daoEmpleado.actualizar(this.session, emple);

            this.transaccion.commit();
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

    public void cargarEmpleadoEditarAvatar(int codigoEmpleado) {
        this.session = null;
        this.transaccion = null;
        System.out.println("Entra a cargar empleado");
        try {
            DaoEmpleado daoEmpleado = new DaoEmpleado();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            this.empleadoSelect = daoEmpleado.getById(this.session, codigoEmpleado);
           System.out.println("Cargo el empleado: " + this.empleadoSelect.getIdempleado());

            RequestContext.getCurrentInstance().update(":avatarEmple:frmActualizarAvatar");
            RequestContext.getCurrentInstance().execute("PF('dialogoActualizarAvatar').show()");

            this.transaccion.commit();
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
    
    
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getListaEmpleado() {
        return listaEmpleado;
    }

    public void setListaEmpleado(List<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public List<Empleado> getListaEmpleadoFiltrado() {
        return listaEmpleadoFiltrado;
    }

    public void setListaEmpleadoFiltrado(List<Empleado> listaEmpleadoFiltrado) {
        this.listaEmpleadoFiltrado = listaEmpleadoFiltrado;
    }

    public String getContraseniaRepita() {
        return ContraseniaRepita;
    }

    public void setContraseniaRepita(String ContraseniaRepita) {
        this.ContraseniaRepita = ContraseniaRepita;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getCodigoCiudad() {
        return CodigoCiudad;
    }

    public void setCodigoCiudad(int CodigoCiudad) {
        this.CodigoCiudad = CodigoCiudad;
    }

    public int getCodigoRol() {
        return CodigoRol;
    }

    public void setCodigoRol(int CodigoRol) {
        this.CodigoRol = CodigoRol;
    }

    public int getCodigotipoDocumento() {
        return CodigotipoDocumento;
    }

    public void setCodigotipoDocumento(int CodigotipoDocumento) {
        this.CodigotipoDocumento = CodigotipoDocumento;
    }

    public Empleado getEmpleadoSelect() {
        return empleadoSelect;
    }

    public void setEmpleadoSelect(Empleado empleadoSelect) {
        this.empleadoSelect = empleadoSelect;
    }

    public UploadedFile getAvatar() {
        return avatar;
    }

    public void setAvatar(UploadedFile avatar) {
        this.avatar = avatar;
    }

}
