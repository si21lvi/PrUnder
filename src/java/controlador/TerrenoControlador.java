/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.TerrenoDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import modelo.Planta;
import modelo.Terreno;

/**
 *
 * @author Silvia
 */
@Named(value = "terrenoControlador")
@SessionScoped
public class TerrenoControlador implements Serializable {

    public TerrenoControlador() {
    }
    private DataModel<Terreno> items;
    private Terreno current;
    private Planta planta;
    @EJB
    TerrenoDAO ejbFacade;

    /*@EJB
    TipoSolicitudDAO ejbFacadeTS;*/

    @Inject
    private AutUsControlador auth;

    

    public DataModel<Terreno> getListado() {
       
      String userId=auth.getUsuarioAutenticado().getNombreUsuario();
      
      items = new ListDataModel<Terreno>(ejbFacade.getTerrenoByUser(userId));
      return items;
    }

    public String doCreate() {
        
        current.setIdUsuarioSolicitud(auth.getUsuarioAutenticado());
        ejbFacade.create(current);
        return prepareList();
    }
    
   public String doUpdate(){
       
        ejbFacade.edit(current);
        current = new Solicitud();
         return prepareList();
        
    }    

    // Métodos de navegación
    public String prepareList() {

        return "/solicitud/List?faces-redirect=true";

    }

    public String prepareCreate() {
        current = new Solicitud();
        return "/solicitud/Create?faces-redirect=true";

    }
    
    public String prepareEdit(){
        current = (Solicitud) getItems().getRowData();
        return  "/solicitud/Edit?faces-redirect=true";
    }

    public DataModel<Solicitud> getItems() {
        return items;
    }

    public void setItems(DataModel<Solicitud> items) {
        this.items = items;
    }

    public Solicitud getCurrent() {
        return current;
    }

    public void setCurrent(Solicitud current) {
        this.current = current;
    }
    
    
    
}
