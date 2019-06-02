/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOChatAplicacao;
import br.com.granderio.appreciclagem.dao.DAONegociacao;
import br.com.granderio.appreciclagem.dao.DAOPedidoReciclagem;
import br.com.granderio.appreciclagem.model.ChatAplicacao;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.util.UtilError;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="controladorNegociacao")
@SessionScoped
public class ControladorNegociacao extends ControladorPrincipal<Negociacao> {
    
    private Negociacao negociacao;
    private long idNegociacao;
    
    private String novaMensagem;
    
    private ChatAplicacao chatAplicacaoSelecionado;
    
    public ControladorNegociacao(){
        super(new Negociacao() );
        novaMensagem = null;
    }
    
    public void updateNegociacao(){
        try{
        DAOChatAplicacao acesso = new DAOChatAplicacao(new ChatAplicacao());
        negociacao.getChat().setChatAplicacao(acesso.buscarMensagensChat(negociacao.getChat()));
        }catch(Exception e){
            UtilMensagens.mensagemError(e.getMessage());
        }
     }
    
    public String quemEnviouMsg(ChatAplicacao chatAplica){
        if(chatAplica.getGerador() != null){
            return chatAplica.getGerador().getRazaoSocial();
        }
        return chatAplica.getReciclador().getRazaoSocial();
    }
    /**
     * @return the negociacao
     */
    public Negociacao getNegociacao() {
        return negociacao;
    }
    
    public void adicionarNovaMensagemGerador(Gerador pessoa){
        if(novaMensagem == null){
                UtilMensagens.mensagemError("Mensagem nula");
                novaMensagem = null;
                return;
            }
        if(novaMensagem != null && novaMensagem.equals("")){
            UtilMensagens.mensagemError("Mensagem nula");
            novaMensagem = null;
                return;
        }
            ChatAplicacao novo = new ChatAplicacao();
            novo.setDataHora(new Date());
            novo.setChat(negociacao.getChat());
            novo.setMensagem(novaMensagem);
            novo.setGerador(pessoa);
            negociacao.getChat().adicionarChatAplica(novo);
            DAO<ChatAplicacao> dao = new DAO(novo);
            dao.inserir();
            novaMensagem = null;
        }
    
     public void adicionarNovaMensagemReciclador(Reciclador pessoa){
        if(novaMensagem == null){
                UtilMensagens.mensagemError("Mensagem nula");
                novaMensagem = null;
                return;
            }
        if(novaMensagem != null && novaMensagem.equals("")){
            UtilMensagens.mensagemError("Mensagem nula");
            novaMensagem = null;
                return;
        }
            ChatAplicacao novo = new ChatAplicacao();
            novo.setDataHora(new Date());
            novo.setChat(negociacao.getChat());
            novo.setMensagem(novaMensagem);
            novo.setReciclador(pessoa);
            negociacao.getChat().adicionarChatAplica(novo);
            DAO<ChatAplicacao> dao = new DAO(novo);
            dao.inserir();
            novaMensagem = null;
        }
    
    /**
     * @param negociacao the negociacao to set
     */
    public void setNegociacao(Negociacao negociacao) {
        this.negociacao = negociacao;
    }
    
    public void listenerIrNegociar(Negociacao negociacao){
        this.setNegociacao(negociacao);
        this.setIdNegociacao(negociacao.getIdNegociacao());     
    }

    /**
     * @return the novaMensagem
     */
    public String getNovaMensagem() {
        return novaMensagem;
    }

    /**
     * @param novaMensagem the novaMensagem to set
     */
    public void setNovaMensagem(String novaMensagem) {
        this.novaMensagem = novaMensagem;
    }

    /**
     * @return the idNegociacao
     */
    public long getIdNegociacao() {
        return idNegociacao;
    }

    /**
     * @param idNegociacao the idNegociacao to set
     */
    public void setIdNegociacao(long idNegociacao) {
        this.idNegociacao = idNegociacao;
    }

    /**
     * @return the chatAplicacaoSelecionado
     */
    public ChatAplicacao getChatAplicacaoSelecionado() {
        return chatAplicacaoSelecionado;
    }

    /**
     * @param chatAplicacaoSelecionado the chatAplicacaoSelecionado to set
     */
    public void setChatAplicacaoSelecionado(ChatAplicacao chatAplicacaoSelecionado) {
        this.chatAplicacaoSelecionado = chatAplicacaoSelecionado;
    }
    
    public void excluirNegociacao(Negociacao neg){
    	try {
	        DAO<Negociacao> dao = new DAO<Negociacao>(neg);
	        dao.excluir();
	        UtilMensagens.mensagemInfo("A negociação foi excluída com sucesso!");
    	}catch(Exception e) {
    		UtilMensagens.mensagemError("Error: " + UtilError.getUltimaExcecao(e));
    	}
    }
    
    public String retornaMensagemDoFinalizou(){
        long idNegociacao = (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idNegociacao");
        DAONegociacao daoNeg = new DAONegociacao(new Negociacao());
        Negociacao negociacao = daoNeg.buscarNegociacao(idNegociacao);
        if(!negociacao.isGeradorFinalizou() && !negociacao.isRecicladorFinalizou()){
            return "Ninguém finalizou o pedido!";
        }else if(!negociacao.isGeradorFinalizou() && negociacao.isRecicladorFinalizou() ){
            return "Reciclador finalizou, aguardando Gerador para prosseguir com o Pedido!";
      }else if(negociacao.isGeradorFinalizou() && !negociacao.isRecicladorFinalizou()){
            return "Gerador finalizou, aguardando Reciclador para prosseguir com o Pedido!";
      }else{
          return "Está negociação foi concluída, vá para a próxima etapa!";
      }
    }
    
    public void finalizarPedido(String tipo){
        long idNegociacao = (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idNegociacao");
        DAONegociacao daoNeg = new DAONegociacao(new Negociacao());
        Negociacao negociacao = daoNeg.buscarNegociacao(idNegociacao);
        
        if(tipo.equalsIgnoreCase("Gerador")){
            if(negociacao.isGeradorFinalizou() && negociacao.isRecicladorFinalizou()){
                UtilMensagens.mensagemAdvertencia("Essa Negociação já foi finalizada, vá para a próxima etapa.");
            }else if(negociacao.isGeradorFinalizou()){
                UtilMensagens.mensagemAdvertencia("Você já finalizou, aguarde o Reciclador.");
            }else{
                
                negociacao.setGeradorFinalozou(true);
                if(negociacao.isRecicladorFinalizou()){
                    UtilMensagens.mensagemInfo("Ambos finalizaram, podem prosseguir com o Pedido.");
                    PedidoReciclagem pedido = negociacao.getPedido();
                    pedido.setReciclador(negociacao.getReciclador());
                    DAOPedidoReciclagem daoPedido = new DAOPedidoReciclagem(pedido);
                    daoPedido.alterar();      
                }else{
                    UtilMensagens.mensagemInfo("Finalizado com sucesso, aguarde o Reciclador agora.");
                }
                daoNeg = new DAONegociacao(negociacao);
                daoNeg.alterar();
            }
        }else{
            if(negociacao.isRecicladorFinalizou() && negociacao.isGeradorFinalizou()){
               UtilMensagens.mensagemAdvertencia("Essa Negociação já foi finalizada, vá para a próxima etapa.");
            }else if(negociacao.isRecicladorFinalizou()){
                UtilMensagens.mensagemAdvertencia("Você já finalizou, aguarde o Gerador.");
            }        
            else{
                negociacao.setRecicladorFinalizou(true);
                if(negociacao.isGeradorFinalizou()){
                    UtilMensagens.mensagemInfo("Ambos finalizaram, podem prosseguir com o Pedido.");
                    PedidoReciclagem pedido = negociacao.getPedido();
                    pedido.setReciclador(negociacao.getReciclador());
                    DAOPedidoReciclagem daoPedido = new DAOPedidoReciclagem(pedido);
                    daoPedido.alterar();    
                }else{
                    UtilMensagens.mensagemInfo("Finalizado com sucesso, aguarde o Gerador agora.");
                }
                daoNeg = new DAONegociacao(negociacao);
                daoNeg.alterar();
            }
        }
 
    }
    
    public boolean renderizaButtonFinalizar(){
        long idNegociacao = (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idNegociacao");
        DAONegociacao daoNeg = new DAONegociacao(new Negociacao());
        Negociacao negociacao = daoNeg.buscarNegociacao(idNegociacao);
        
        if(negociacao.isGeradorFinalizou() && negociacao.isRecicladorFinalizou())
            return false;
        
        return true;
    }
    
    

}
