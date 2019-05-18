/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.converter;


import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOLegislacao;
import br.com.granderio.appreciclagem.dao.DAOMaterial;
import br.com.granderio.appreciclagem.model.ChatAplicacao;
import br.com.granderio.appreciclagem.model.Legislacao;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.util.UtilError;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@FacesConverter(value="legislacaoConverter")
public class LegislacaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
            try{
                Legislacao retorno = new Legislacao();
                long id = Long.valueOf(value);
                DAOLegislacao acesso = new DAOLegislacao(retorno);
                retorno = acesso.buscarLegislacao(id);
                return retorno;
            }catch(NumberFormatException e){
                System.out.println(UtilError.getMensagemErro(e));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Legislacao){
            Legislacao legi = (Legislacao) value;
            return String.valueOf(legi.getIdLegislacao());
        }
        return null;
    }

    

}
