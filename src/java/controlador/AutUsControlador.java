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
import modelo.Usuario_1;
import util.AuthUtil;

/**
 *
 * @author Silvia
 */
@Named(value = "autenticUsuario")
@SessionScoped
public class AutUsControlador implements Serializable {

    private Usuario_1 usuario;
    private Usuario_1 usuarioAutenticado;
     @EJB
    UsuarioDAO ejbFacade;
    public AutUsControlador() {
         usuario = new Usuario_1();
        usuarioAutenticado = null;
        
    }
    
    public String login() {

        String claveSegura = AuthUtil.crearClaveSegura(usuario.getContraseñaUsuario());
        
        usuarioAutenticado = ejbFacade.authUser(usuario.getNombreUsuario(), claveSegura );

        if(usuarioAutenticado!=null){
            return "Menu?faces-redirect=true";
        }else{
            return "Lobby?faces-redirect=true";
        }
        
    }
    
    public Usuario_1 getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario_1 usuario) {
        this.usuario = usuario;
    }

    public Usuario_1 getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario_1 usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    
    
    
}
