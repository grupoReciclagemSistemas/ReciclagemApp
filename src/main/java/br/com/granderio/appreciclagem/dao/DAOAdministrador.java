/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.Administrador;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Transportador;
import br.com.granderio.appreciclagem.util.UtilError;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

public class DAOAdministrador extends DAO<Administrador> {

    public DAOAdministrador(Administrador admin) {
        super(admin);
    }
    
    public Administrador logarAdmin(String login, String senha){
        Administrador adm = null;
        try{
            DAO.getS().getTransaction().begin();
            Criteria cri = DAO.getS().createCriteria(Administrador.class);
            cri.add(Restrictions.eq("login", login));
            cri.add(Restrictions.eq("senha", senha));
            adm = (Administrador) cri.list().get(0);
            DAO.getS().getTransaction().commit();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao logar: " + mensagem);
            DAO.getS().getTransaction().rollback();
        }finally {
        	DAO.fecharSession();
        }
        return adm;  
    }
    
    

}
