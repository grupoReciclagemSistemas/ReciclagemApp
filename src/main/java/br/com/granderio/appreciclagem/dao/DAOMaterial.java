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

public class DAOMaterial extends DAO<Material> {

	public DAOMaterial(Material mat) {
		super(mat);
	}

	public int quantidadeDeMateriais() {
		List<Material> lista = null;
		try {
			DAO.getS().getTransaction().begin();
			Criteria cri = DAO.getS().createCriteria(Material.class);
			lista = cri.list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao buscar lista de Material: " + ex);
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }
		
		if (lista != null)
			return lista.size();

		return 0;
	}

	public Material buscarMaterial(long id) {
		List<Material> lista = new ArrayList();
		try {
			DAO.getS().getTransaction().begin();
			Criteria criteria = DAO.getS().createCriteria(Material.class);
			criteria.add(Restrictions.eq("idMaterial", id));
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
