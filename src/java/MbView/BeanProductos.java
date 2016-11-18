/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoProductos;
import HibernateUtil.HibernateUtil;
import Pojos.Productos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
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
public class BeanProductos {

   private Session session;
    private Transaction transaccion;
    private Productos productos;
    private List<Productos> listaProductos;
    private List<Productos> listaProductosFiltrado;
     private Productos productoSelect;
     private UploadedFile avatar;
     private int codigoProductos;
    
    public BeanProductos() {
        this.productos = new Productos();
        this.listaProductos = new ArrayList<>();
    }

      public void registrar() {
        this.session = null;
        this.transaccion = null;

        try {
            if (this.productos.getNombre().equals("") || this.productos.getNombre().length() < 4) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es muy corto"));
                return;
            }


            DaoProductos daoProductos = new DaoProductos();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

          

            daoProductos.registar(this.session, this.productos);

            this.transaccion.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro fue realizado correctamente"));
      this.productos = new Productos();
        this.listaProductos = new ArrayList<>();

          

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

     public List<Productos> getAll() {
        this.session = null;
        this.transaccion = null;

        try {
               DaoProductos daoProductos = new DaoProductos();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaProductos = daoProductos.getAll(this.session);

            this.transaccion.commit();

            return this.listaProductos;
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
                  DaoProductos daoProductos = new DaoProductos();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            if (this.productoSelect.getNombre().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El campo nombres es obligatorio"));
                return;
            }

           

            daoProductos.actualizar(this.session, this.productoSelect);

            this.transaccion.commit();

        

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

    public void cargarProductosEditar(int codigoProductos) {
        this.session = null;
        this.transaccion = null;

        try {
             DaoProductos daoProductos = new DaoProductos();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

            this.productoSelect = daoProductos.getById(this.session, codigoProductos);
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
            String carpetaProductos = (String) servletContext.getRealPath("/carpetaProductos");
          
            
             

            outputStream = new FileOutputStream(new File(carpetaProductos + "/" + this.productoSelect.getIdproductos() + ".png"));
            inputStream = this.avatar.getInputstream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            actualizarFoto(getById().getIdproductos() + this.avatar.getFileName());

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
    
           public Productos getById() {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            
      DaoProductos daoProductos = new DaoProductos();
       

            Productos produc = daoProductos.getById(this.session, this.codigoProductos);
            this.transaccion.commit();
            return produc;

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
         
         
         
          public void actualizarFoto(String nombreFoto) {
        this.session = null;
        this.transaccion = null;

        try {
            
            
               DaoProductos daoProductos = new DaoProductos();
            Productos product;
            product = getById();
      

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();

        
            product.setImgPrinci(nombreFoto);
            daoProductos.actualizar(this.session, product);
            
          

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
              DaoProductos daoProductos = new DaoProductos();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = session.beginTransaction();
            this.productoSelect = daoProductos.getById(this.session, codigoEmpleado);

           
           System.out.println("Cargo el empleado: " + this.productoSelect.getIdproductos());

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
    
          
          
    
    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public List<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Productos> getListaProductosFiltrado() {
        return listaProductosFiltrado;
    }

    public void setListaProductosFiltrado(List<Productos> listaProductosFiltrado) {
        this.listaProductosFiltrado = listaProductosFiltrado;
    }

    public Productos getProductoSelect() {
        return productoSelect;
    }

    public void setProductoSelect(Productos productoSelect) {
        this.productoSelect = productoSelect;
    }

    public UploadedFile getAvatar() {
        return avatar;
    }

    public void setAvatar(UploadedFile avatar) {
        this.avatar = avatar;
    }

    public int getCodigoProductos() {
        return codigoProductos;
    }

    public void setCodigoProductos(int codigoProductos) {
        this.codigoProductos = codigoProductos;
    }
    
}
