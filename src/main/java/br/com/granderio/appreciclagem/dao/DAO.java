/*
 CRIADO E EDITADO POR RAFAEL NUNES - 2019
 */
package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.Transportador;
import br.com.granderio.appreciclagem.util.HibernateUtil;
import br.com.granderio.appreciclagem.util.UtilError;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class DAO<T> {
    
    private T objetoModelo;
    private static Session s;
    
    public DAO(T objetoModelo) {
        this.objetoModelo = objetoModelo;    
        s = HibernateUtil.pegarSession();
    }
   
   
    public int quantidade(String email, String senha){  
        int quantidade = 0;
        try{
        	DAO.getS().getTransaction().begin();       
            Criteria cri = DAO.getS().createCriteria(Transportador.class);
            cri.add(Restrictions.eq("email", email));
            cri.add(Restrictions.eq("senha", senha));
            quantidade = cri.list().size();
            DAO.getS().getTransaction().commit();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao logar: " + mensagem);
            DAO.getS().getTransaction().rollback();
        }finally {
        	DAO.fecharSession();
        }
        return quantidade;
    }


    public void inserir() {      
        try {      
        	DAO.getS().getTransaction().begin();
        	DAO.getS().save(getObjetoModelo());
        	DAO.getS().getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao incluir registro: " + mensagem);
            DAO.getS().getTransaction().rollback();
        }finally {
        	DAO.fecharSession();
        }
    }
    
    public void alterar() {       
        try {
        	DAO.getS().getTransaction().begin();
        	DAO.getS().update(getObjetoModelo());
        	DAO.getS().getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao incluir alterar: " + mensagem);
            DAO.getS().getTransaction().rollback();
        }finally {
        	DAO.fecharSession();
        }
    }
    
    public void excluir() {      
        try {      
        	DAO.getS().getTransaction().begin();
        	DAO.getS().delete(getObjetoModelo());
        	DAO.getS().getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);       
            System.err.println("Erro ao excluir registro: " + mensagem);
            DAO.getS().getTransaction().rollback();
        }finally {
        	DAO.fecharSession();
        }
    }
    
    public List<T> obterLista() {     
        List<T> list = null;
        try {
        	DAO.getS().getTransaction().begin();
            Criteria criteria = s.createCriteria(getObjetoModelo().getClass());
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            list = criteria.list();
            DAO.getS().getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            DAO.getS().getTransaction().rollback();
        }finally {
        	DAO.fecharSession();
        }
        return list;
    }

    public void buscar(long id) {    
        try {
        	DAO.getS().getTransaction().begin();
        	DAO.getS().load(getObjetoModelo(), id);
        	DAO.getS().getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registro: " + mensagem);
            DAO.getS().getTransaction().rollback();
        }finally {
        	DAO.fecharSession();
        }
    }
    
    public List executarConsultaPersonalizada(String sql) {      
        List data = null;
        try {
        	DAO.getS().getTransaction().begin();
            SQLQuery query = DAO.getS().createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            data = query.list();
            DAO.getS().getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros: " + mensagem);
            DAO.getS().getTransaction().rollback();
        }
        return data;
    }
      
    public List<?> listByNamedQuery(String name){
    	List<?> list = null;
        Query query = null;
         try {
        	DAO.getS().getTransaction().begin();
        	query = DAO.getS().getNamedQuery(name);
        	list = query.list();
        	DAO.getS().getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            DAO.getS().getTransaction().rollback();
        }finally {
        	DAO.fecharSession();
        }
        return list;
    }
    
    public static void fecharSession() {
    	if(DAO.getS() != null && DAO.getS().isOpen())
    		DAO.getS().close();
    }

    public static void setS(Session s) {
		DAO.s = s;
	}
    
    public static Session getS() {
    	s = HibernateUtil.pegarSession();
		return s;
	}
    
    public T getObjetoModelo() {
        return objetoModelo;
    }

    public void setObjetoModelo(T objetoModelo) {
        this.objetoModelo = objetoModelo;
    }
        
}

