/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.datamodel;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOChatAplicacao;
import br.com.granderio.appreciclagem.dao.DAOPedidoReciclagem;
import br.com.granderio.appreciclagem.dto.PedidoReciclagemDto;
import br.com.granderio.appreciclagem.model.ChatAplicacao;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
public class PedidoReciclagemLazyDataModel extends LazyDataModel<PedidoReciclagem> {
    
    public PedidoReciclagemLazyDataModel(){
        super();
    }
    
//    public List<PedidoReciclagemDto> listaDto(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
//        List<PedidoReciclagemDto> retorno = new ArrayList<>();
//        List<PedidoReciclagem> lista = this.load(first, pageSize, sortField, sortOrder, filters);
//        if(lista != null){
//            for(PedidoReciclagem pedido : lista){
//
//            }
//        }
//    }
     
    @Override
    public List<PedidoReciclagem> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
      DAOPedidoReciclagem dao = new DAOPedidoReciclagem(new PedidoReciclagem());
      List<PedidoReciclagem> lista = dao.buscarLazyModelPedidos(first, pageSize, sortField, sortOrder, filters);
      setRowCount(dao.getPedidosTotalCount());
      setPageSize(pageSize);
      return lista;
    }
     
    @Override
    public Object getRowKey(PedidoReciclagem pedido) {
        return pedido.getIdPedidoReciclagem();
    }

}
