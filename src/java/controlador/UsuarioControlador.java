/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import modelo.Usuario_1;
import util.AuthUtil;

/**
 *
 * @author Silvia
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

     private DataModel<Usuario_1> items;
    private Usuario_1 current;

    @EJB
    private UsuarioDAO ejbFacade;
    public UsuarioControlador() {
    }

    

    

    public DataModel<Usuario_1> getListado() {
        items = new ListDataModel<Usuario_1>(ejbFacade.findAll());
        return items;
    }

    public String doUserCreate() {

        String claveSegura = AuthUtil.crearClaveSegura(current.getContraseñaUsuario());
        current.setContraseñaUsuario(claveSegura);
        ejbFacade.create(current);
        current = new Usuario_1();
        return prepareList();

    }

    public String doUserUpdate() {
        String claveSegura = AuthUtil.crearClaveSegura(current.getContraseñaUsuario());
        current.setContraseñaUsuario(claveSegura);
        ejbFacade.edit(current);
        current = new Usuario_1();
        return prepareList();

    }

    public String doUserDelete() {

        ejbFacade.remove(current);
        current = new Usuario_1();
        return prepareList();

    }
    //Navegacion
    
    public String prepareList() {
        return "/usuario/List?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new Usuario_1();
        return "/usuario/Create?faces-redirect=true";
    }

    public String prepareEdit() {
        current = (Usuario_1) getItems().getRowData();
        return "/usuario/Edit?faces-redirect=true";
    }

    public void prepareDelete() {
        current = (Usuario_1) getItems().getRowData();
    }

    public String prepareView() {

        current = (Usuario_1) getItems().getRowData();

        return "/usuario/View?faces-redirect=true";
    }

    // Getters 
    public DataModel<Usuario_1> getItems() {
        return items;
    }

    public void setItems(DataModel<Usuario_1> items) {
        this.items = items;
    }

    public Usuario_1 getCurrent() {
        return current;
    }

    public void setCurrent(Usuario_1 current) {
        this.current = current;
    }

}
