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
public class DAOLegislacao extends DAO<Legislacao> {

    public DAOLegislacao(Legislacao leg) {
        super(leg);
    }
    
    public Legislacao buscarLegislacao(long id){
      List<Legislacao> lista = new ArrayList();
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(Legislacao.class);
            criteria.add(Restrictions.eq("idLegislacao", id));
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

