/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Terreno;

/**
 *
 * @author Silvia
 */
@Stateless
public class TerrenoDAO extends AbstractFacade<Terreno>{
    @PersistenceContext(unitName = "WebMariaDBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TerrenoDAO(){
        super(Terreno.class);
    }
    
    public List<Terreno> getTerrenoByUser(String userId){
       //s.idUsuarioSolicitud.idUsuario = :id 
        List<Terreno> resultList = null;
        Query q = getEntityManager().createQuery("SELECT t from Terreno t WHERE t. ");
        q.setParameter("id", userId);
        
        try {
            resultList = (List<Terreno>) q.getResultList();
        } catch (NoResultException e) {
            Logger.getLogger("TerrenoDAO").log(Level.SEVERE, "No se encontraron registros");
        } catch (Exception e) {
            Logger.getLogger("TerrenoDAO").log(Level.SEVERE, "Error inesperado " + e.getLocalizedMessage());
        }
        
        return resultList;
    }
}
