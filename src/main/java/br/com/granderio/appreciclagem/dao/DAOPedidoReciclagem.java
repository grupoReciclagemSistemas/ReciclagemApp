/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.ChatAplicacao;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Transportador;
import br.com.granderio.appreciclagem.util.UtilError;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

public class DAOPedidoReciclagem extends DAO<PedidoReciclagem> {

	public DAOPedidoReciclagem(PedidoReciclagem pedido) {
		super(pedido);
	}

	public List<PedidoReciclagem> listarTodos() {
		List<PedidoReciclagem> retorno = null;
		Query query = null;
		try {
			DAO.getS().getTransaction().begin();
			query = DAO.getS().getNamedQuery("Pedido.buscarTodos");
			retorno = query.list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException ex) {
			String mensagem = UtilError.getMensagemErro(ex);
			System.err.println("Erro ao buscar registros (lista): " + mensagem);
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }
		return retorno;
	}

	public List<PedidoReciclagem> listarPorCidade(String cidade) {
		List<PedidoReciclagem> retorno = null;
		Query query = null;
		try {
			DAO.getS().getTransaction().begin();
			query = DAO.getS().getNamedQuery("Pedido.buscarPedidosPorCidade");
			retorno = query.setString("cidade", cidade).list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException ex) {
			String mensagem = UtilError.getMensagemErro(ex);
			System.err.println("Erro ao buscar registros (lista): " + mensagem);
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }
		return retorno;
	}

	public List<PedidoReciclagem> listarPorMaterial(long idMaterial) {
		List<PedidoReciclagem> retorno = null;
		Query query = null;
		try {
			DAO.getS().getTransaction().begin();
			query = DAO.getS().getNamedQuery("Pedido.buscarPedidosPorMaterial");
			retorno = query.setLong("idmaterial", idMaterial).list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException ex) {
			String mensagem = UtilError.getMensagemErro(ex);
			System.err.println("Erro ao buscar registros (lista): " + mensagem);
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }
		return retorno;
	}

	public List<PedidoReciclagem> listarPorCidadeMaterial(String cidade, long idMaterial) {
		List<PedidoReciclagem> retorno = null;
		Query query = null;
		try {
			DAO.getS().getTransaction().begin();
			query = DAO.getS().getNamedQuery("Pedido.buscarPedidosPorMaterialCidade");
			retorno = query.setString("cidade", cidade).setLong("idmaterial", idMaterial).list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException ex) {
			String mensagem = UtilError.getMensagemErro(ex);
			System.err.println("Erro ao buscar registros (lista): " + mensagem);
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }
		return retorno;
	}

	public List<PedidoReciclagem> listaDeMateriaisVendendo() {
		List<PedidoReciclagem> list = null;
		try {
			DAO.getS().getTransaction().begin();
			Criteria criteria = DAO.getS().createCriteria(PedidoReciclagem.class);
			criteria.add(Restrictions.isNull("transportador"));
			criteria.add(Restrictions.isNull("reciclador"));
			list = criteria.list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException ex) {
			String mensagem = UtilError.getMensagemErro(ex);
			System.err.println("Erro ao buscar registros (lista): " + mensagem);
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }

		return (List<PedidoReciclagem>) list;
	}

	public boolean existeNegociacoes(PedidoReciclagem pedido) {
		List<Negociacao> retorno = null;
		Query query = null;
		try {
			DAO.getS().getTransaction().begin();
			query = DAO.getS().getNamedQuery("Negociacao.listarPorIdPedidoReciclagem");
			retorno = query.setLong("id", pedido.getIdPedidoReciclagem()).list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException ex) {
			String mensagem = UtilError.getMensagemErro(ex);
			System.err.println("Erro ao buscar registros (lista): " + mensagem);
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }

		if (retorno != null && retorno.size() > 0)
			return true;

		return false;
	}

	// Busca por ID
	public PedidoReciclagem findPedidoReciclagem(long idPedido) {
		PedidoReciclagem retorno = null;
		try {
			DAO.getS().getTransaction().begin();
			Criteria cri = DAO.getS().createCriteria(PedidoReciclagem.class);
			cri.add(Restrictions.eq("idPedidoReciclagem", idPedido));
			List<PedidoReciclagem> list = cri.list();
			if (list != null && list.size() > 0) {
				retorno = list.get(0);
			}
			DAO.getS().getTransaction().commit();
		} catch (HibernateException ex) {
			String mensagem = UtilError.getMensagemErro(ex);
			System.err.println("Erro ao buscar registros (lista): " + mensagem);
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }
		return retorno;
	}

	public List<PedidoReciclagem> buscarLazyModelPedidos(int first, int max, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<PedidoReciclagem> lista = null;
		Query query = null;
		try {
			DAO.getS().getTransaction().begin();
			query = DAO.getS().getNamedQuery("Pedido.buscarTodos");
			query.setFirstResult(first);
			query.setMaxResults(max);
			lista = query.list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Error: " + e.getMessage());
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }

		return lista;
	}

	public int getPedidosTotalCount() {
		List<PedidoReciclagem> lista = null;
		Query query = null;
		try {
			DAO.getS().getTransaction().begin();
			query = DAO.getS().getNamedQuery("Pedido.buscarTodos");
			lista = query.list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Error: " + e.getMessage());
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }

		if (lista != null)
			return lista.size();

		return 0;
	}

}
