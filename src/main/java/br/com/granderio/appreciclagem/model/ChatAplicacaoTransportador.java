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
    @NamedQuery(name="ChatAplicaTrans.buscarTodos", query="SELECT c FROM ChatAplicacaoTransportador c inner join c.chatTransportador WHERE c.chatTransportador.idChatTransportador = :idChat ORDER BY c.idChatAplicacaoTransportador DESC"),
    @NamedQuery(name="ChatAplicaTrans.buscarTodos2", query="SELECT c FROM ChatAplicacaoTransportador c inner join c.chatTransportador ORDER BY c.idChatAplicacaoTransportador DESC")
})
public class ChatAplicacaoTransportador implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idChatAplicacaoTransportador;
  
  
  //Mensagem que irá ser enviada
  private String mensagem;
  
  //Associação bidirecional
  @ManyToOne
  private ChatTransportador chatTransportador;
  
  @ManyToOne
  private Transportador transportador;
  
  @ManyToOne
  private Reciclador reciclador;
 
  //Data e Hora que a mensagem irá ser enviada
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataHora;

    /**
     * @return the idChatAplicacaoTransportador
     */
    public Long getIdChatAplicacaoTransportador() {
        return idChatAplicacaoTransportador;
    }

    /**
     * @param idChatAplicacaoTransportador the idChatAplicacaoTransportador to set
     */
    public void setIdChatAplicacaoTransportador(Long idChatAplicacaoTransportador) {
        this.idChatAplicacaoTransportador = idChatAplicacaoTransportador;
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
     * @return the chatTransportador
     */
    public ChatTransportador getChatTransportador() {
        return chatTransportador;
    }

    /**
     * @param chatTransportador the chatTransportador to set
     */
    public void setChatTransportador(ChatTransportador chatTransportador) {
        this.chatTransportador = chatTransportador;
    }

    /**
     * @return the transportador
     */
    public Transportador getTransportador() {
        return transportador;
    }

    /**
     * @param transportador the transportador to set
     */
    public void setTransportador(Transportador transportador) {
        this.transportador = transportador;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chatTransportador == null) ? 0 : chatTransportador.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result
				+ ((idChatAplicacaoTransportador == null) ? 0 : idChatAplicacaoTransportador.hashCode());
		result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((reciclador == null) ? 0 : reciclador.hashCode());
		result = prime * result + ((transportador == null) ? 0 : transportador.hashCode());
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
		ChatAplicacaoTransportador other = (ChatAplicacaoTransportador) obj;
		if (chatTransportador == null) {
			if (other.chatTransportador != null)
				return false;
		} else if (!chatTransportador.equals(other.chatTransportador))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (idChatAplicacaoTransportador == null) {
			if (other.idChatAplicacaoTransportador != null)
				return false;
		} else if (!idChatAplicacaoTransportador.equals(other.idChatAplicacaoTransportador))
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
		if (transportador == null) {
			if (other.transportador != null)
				return false;
		} else if (!transportador.equals(other.transportador))
			return false;
		return true;
	}

}
