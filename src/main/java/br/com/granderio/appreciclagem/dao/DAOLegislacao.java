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

public class DAOLegislacao extends DAO<Legislacao> {

	public DAOLegislacao(Legislacao leg) {
		super(leg);
	}

	public Legislacao buscarLegislacao(long id) {
		List<Legislacao> lista = new ArrayList();
		try {
			DAO.getS().getTransaction().begin();
			Criteria criteria = DAO.getS().createCriteria(Legislacao.class);
			criteria.add(Restrictions.eq("idLegislacao", id));
			lista = criteria.list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException ex) {
			String mensagem = UtilError.getMensagemErro(ex);
			System.err.println("Erro ao buscar registros (lista): " + mensagem);
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }

		if (lista.size() > 0) {
			return lista.get(0);
		}

		return null;
	}

}
