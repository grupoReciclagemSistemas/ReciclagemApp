/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAONegociacao;
import br.com.granderio.appreciclagem.dao.DAOPedidoReciclagem;
import br.com.granderio.appreciclagem.dao.DAOReciclador;
import br.com.granderio.appreciclagem.dto.PedidoReciclagemDto;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.util.List;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="controladorReciclador")
@SessionScoped
public class ControladorReciclador extends ControladorPrincipal<Reciclador> {
         
    private Reciclador recicladorSelecionado;
    
    public ControladorReciclador(){
        super(new Reciclador());
        
    }
   
    public String entrarEmNegociacao(PedidoReciclagemDto pedidoDto, Reciclador recicladorLogado){
        if(pedidoDto == null){
            UtilMensagens.mensagemError("Pedido retornado Null, contate o Admin do Sistema.");
            return "";
        }
         if(recicladorLogado == null){
            UtilMensagens.mensagemError("RecicladorLogado Null, contate o Admin do Sistema.");
            return "";
        }
        DAONegociacao daoNegociacao = new DAONegociacao(new Negociacao());
        List<Negociacao> listaNegociacoes = daoNegociacao.buscarNegociacaoPorIdGeradorReciclador(pedidoDto, recicladorLogado);
        if(listaNegociacoes != null && listaNegociacoes.size() > 0){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "JÁ EXISTE UMA NEGOCIAÇÃO ENTRE VOCÊ E ESTE GERADOR.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "";
        }
        DAOReciclador dao = new DAOReciclador(recicladorLogado);
        DAOPedidoReciclagem daoPedido = new DAOPedidoReciclagem(new PedidoReciclagem());
        PedidoReciclagem pedido = daoPedido.findPedidoReciclagem(pedidoDto.getIdPedido());
        Negociacao neg = new Negociacao(pedido, recicladorLogado, pedido.getGerador());
        
        //Adiciona na lista de Reciclador e Gerador a Negociacao criada
        recicladorLogado.adicionarNegociacao(neg);
        pedido.getGerador().adicionarNegociacao(neg);
             
        // Insere a Negociacao
        daoNegociacao = new DAONegociacao(neg);
        daoNegociacao.inserir(); 
        
        // Atualiza Reciclador   
        dao.alterar();
        
        // Atualiza o Gerador 
        DAO<Gerador> daoGerador = new DAO(pedido.getGerador());
        daoGerador.alterar();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "A NEGOCIAÇÃO FOI INICIADA COM SUCESSO, CONSULTA SUA CONTA PARA ENVIAR MENSAGEM.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
      public void onDoubleClick(SelectEvent event){
       Reciclador obj = (Reciclador) event.getObject();
       recicladorSelecionado = obj;
   }
   
   public void onRowSelect(SelectEvent event){
       Reciclador obj = (Reciclador) event.getObject();
       recicladorSelecionado = obj;
   }
   
   public void onRowUnselect(UnselectEvent event){
       recicladorSelecionado = null;
   }
    
    public List<Reciclador> lista(){
        DAO<Reciclador> lista = new DAO(new Reciclador());
        return lista.obterLista();
    }
 
    public String testarTrans(boolean valor){
        if(valor){
            return "Sim";
        }
        return "Não";              
    }
    
    public String novoReciclador(){
        return "registrar?faces-redirect=true";
    }

    /**
     * @return the recicladorSelecionado
     */
    public Reciclador getRecicladorSelecionado() {
        return recicladorSelecionado;
    }

    /**
     * @param recicladorSelecionado the recicladorLogado to set
     */
    public void setRecicladorSelecionado(Reciclador recicladorSelecionado) {
        this.recicladorSelecionado = recicladorSelecionado;
    }
      
}
