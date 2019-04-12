/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.Legislacao;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.util.UtilError;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;


/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
public class DAOMaterial extends DAO<Material> {

    public DAOMaterial(Material mat) {
        super(mat);
    }
      
    
    public int quantidadeDeMateriais(){
    List<Material> lista = null;       
      try{     
        s.getTransaction().begin();       
        Criteria cri = s.createCriteria(Material.class);
        lista = cri.list();
        s.getTransaction().commit();
      }catch(HibernateException ex){
        System.err.println("Erro ao buscar lista de Material: " + ex);
            s.getTransaction().rollback();
        }
    return lista.size();
    }
    
    public Material buscarMaterial(long id){
      List<Material> lista = new ArrayList();
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(Material.class);
            criteria.add(Restrictions.eq("idMaterial", id));
            lista = criteria.list();
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        }
        if(lista.size() >0){
            return lista.get(0);
        }
        return null;
    }
}

