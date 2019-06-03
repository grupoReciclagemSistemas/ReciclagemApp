/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
*
* Rafael Nunes - Version 1.0 - Desenvolvedor Java
*/
@Entity
@NamedQueries({
    @NamedQuery(name="ChatAplica.buscarTodos", query="SELECT c FROM ChatAplicacao c inner join c.chat WHERE c.chat.idChat = :idChat ORDER BY c.idChatAplicacao DESC"),
    @NamedQuery(name="ChatAplica.buscarTodos2", query="SELECT c FROM ChatAplicacao c inner join c.chat ORDER BY c.idChatAplicacao DESC")
})
public class ChatAplicacao implements Serializable {
   
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idChatAplicacao;
  
  
  //Mensagem que irá ser enviada
  private String mensagem;
  
  //Associação bidirecional
  @ManyToOne
  private Chat chat;
  
  //Quem está enviando a mensagem (Reciclador ou Gerador)
  @ManyToOne
  private Gerador gerador;
  
  @ManyToOne
  private Reciclador reciclador;
 
  //Data e Hora que a mensagem irá ser enviada
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataHora;

    /**
     * @return the idChatAplicacao
     */
    public Long getIdChatAplicacao() {
        return idChatAplicacao;
    }

    /**
     * @param idChatAplicacao the idChatAplicacao to set
     */
    public void setIdChatAplicacao(Long idChatAplicacao) {
        this.idChatAplicacao = idChatAplicacao;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the chat
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * @param chat the chat to set
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }

    /**
     * @return the dataHora
     */
    public Date getDataHora() {
        return dataHora;
    }

    /**
     * @param dataHora the dataHora to set
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * @return the gerador
     */
    public Gerador getGerador() {
        return gerador;
    }

    /**
     * @param gerador the gerador to set
     */
    public void setGerador(Gerador gerador) {
        this.gerador = gerador;
    }

    /**
     * @return the reciclador
     */
    public Reciclador getReciclador() {
        return reciclador;
    }

    /**
     * @param reciclador the reciclador to set
     */
    public void setReciclador(Reciclador reciclador) {
        this.reciclador = reciclador;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chat == null) ? 0 : chat.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + ((gerador == null) ? 0 : gerador.hashCode());
		result = prime * result + ((idChatAplicacao == null) ? 0 : idChatAplicacao.hashCode());
		result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((reciclador == null) ? 0 : reciclador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatAplicacao other = (ChatAplicacao) obj;
		if (chat == null) {
			if (other.chat != null)
				return false;
		} else if (!chat.equals(other.chat))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (gerador == null) {
			if (other.gerador != null)
				return false;
		} else if (!gerador.equals(other.gerador))
			return false;
		if (idChatAplicacao == null) {
			if (other.idChatAplicacao != null)
				return false;
		} else if (!idChatAplicacao.equals(other.idChatAplicacao))
			return false;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (reciclador == null) {
			if (other.reciclador != null)
				return false;
		} else if (!reciclador.equals(other.reciclador))
			return false;
		return true;
	}
          
}
