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
    
    public List<PedidoReciclagem> listarTodos(){
          List<PedidoReciclagem> retorno = null;
          Query query = s.getNamedQuery("Pedido.buscarTodos");
         try {
            s.getTransaction().begin();  
            retorno = query.list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        return retorno;
    }
    
    public List<PedidoReciclagem> listarPorCidade(String cidade){
          List<PedidoReciclagem> retorno = null;
          Query query = s.getNamedQuery("Pedido.buscarPedidosPorCidade");
         try {
            s.getTransaction().begin();  
            retorno = query.setString("cidade", cidade).list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        return retorno;
    }
    
    public List<PedidoReciclagem> listarPorMaterial(long idMaterial){
          List<PedidoReciclagem> retorno = null;
          Query query = s.getNamedQuery("Pedido.buscarPedidosPorMaterial");
         try {
            s.getTransaction().begin();  
            retorno = query.setLong("idmaterial", idMaterial).list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        return retorno;
    }
    
    
    
    public List<PedidoReciclagem> listarPorCidadeMaterial(String cidade, long idMaterial){
          List<PedidoReciclagem> retorno = null;
          Query query = s.getNamedQuery("Pedido.buscarPedidosPorMaterialCidade");
         try {
            s.getTransaction().begin();  
            retorno = query.setString("cidade", cidade).setLong("idmaterial", idMaterial).list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        return retorno;
    }
    
    
    public List<PedidoReciclagem> listaDeMateriaisVendendo(){
        List<PedidoReciclagem> list = null;
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(PedidoReciclagem.class);
            criteria.add(Restrictions.isNull("transportador"));
            criteria.add(Restrictions.isNull("reciclador"));
            list = criteria.list();
             s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        }
        return (List<PedidoReciclagem>) list;
    }
    
    public boolean existeNegociacoes(PedidoReciclagem pedido){
          List<Negociacao> retorno = null;
          Query query = s.getNamedQuery("Negociacao.listarPorIdPedidoReciclagem");
         try {
            s.getTransaction().begin();  
            retorno = query.setLong("id", pedido.getIdPedidoReciclagem()).list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        if(retorno.size() > 0)
            return true;
       
        return false;
    }
    
    //Busca por ID
    public PedidoReciclagem findPedidoReciclagem(long idPedido){
         PedidoReciclagem retorno = null;
         try {
            s.getTransaction().begin();  
            Criteria cri = s.createCriteria(PedidoReciclagem.class);
            cri.add(Restrictions.eq("idPedidoReciclagem", idPedido));
            List<PedidoReciclagem> list = cri.list();
            if(list != null && list.size() > 0){
                retorno = list.get(0);
            }
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        return retorno;
    }
    
        public List<PedidoReciclagem> buscarLazyModelPedidos(int first, int max, String sortField, SortOrder sortOrder, Map<String, Object> filters){   
        List<PedidoReciclagem> lista = null;
        Query query = null;
         try{
            s.getTransaction().begin();
            query = s.getNamedQuery("Pedido.buscarTodos");
            query.setFirstResult(first);
            query.setMaxResults(max);
            lista = query.list();
            s.getTransaction().commit();
            return lista;
        }catch(HibernateException e){
             System.out.println("Error: " + e.getMessage());
             s.getTransaction().rollback();
             return null;
        }
    }
        
    public int getPedidosTotalCount() {
      List<PedidoReciclagem> lista = null;
      Query query = null;
         try{
            s.getTransaction().begin();
            query = s.getNamedQuery("Pedido.buscarTodos");
            lista = query.list();
            s.getTransaction().commit();
            return lista.size();
        }catch(HibernateException e){
             System.out.println("Error: " + e.getMessage());
             s.getTransaction().rollback();
             return 0;
        }
  }
      
}

