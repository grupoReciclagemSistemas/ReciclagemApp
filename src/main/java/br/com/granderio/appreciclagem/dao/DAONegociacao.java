/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.Negociacao;
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
public class DAONegociacao extends DAO<Negociacao> {

    public DAONegociacao(Negociacao neg) {
        super(neg);
    }
    
     public Negociacao buscarNegociacao(long id){
        List<Negociacao> lista = new ArrayList();
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(Negociacao.class);
            criteria.add(Restrictions.eq("idNegociacao", id));
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

