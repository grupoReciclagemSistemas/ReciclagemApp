/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.granderio.appreciclagem.dto.PedidoReciclagemDto;


@ManagedBean(name="controleMap")
@SessionScoped
public class ControladorGMap {
    
    private MapModel geoModel;
    private String centerGeoMap;
    
    private LatLng coordenada;
    
    // Pedido em que ele está consultando
    private PedidoReciclagemDto pedido;
    
    /**
     * @return the centerGeoMap
     */
    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    /**
     * @param centerGeoMap the centerGeoMap to set
     */
    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    /**
     * @return the geoModel
     */
    public MapModel getGeoModel() {
        return geoModel;
    }

    /**
     * @param geoModel the geoModel to set
     */
    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
    }


	public LatLng getCoordenada() {
		return coordenada;
	}


	public void setCoordenada(LatLng coordenada) {
		this.coordenada = coordenada;
	}


	public PedidoReciclagemDto getPedido() {
		return pedido;
	}


	public void setPedido(PedidoReciclagemDto pedido) {
		this.pedido = pedido;
	}
	
	public void irConsultarNoMapa(PedidoReciclagemDto pedido) {
		geoModel = new DefaultMapModel();
		this.pedido = pedido;
		this.centerGeoMap = pedido.retornaLatLng();
		if(centerGeoMap!=null){
            String[] split = centerGeoMap.split(",");
            coordenada = new LatLng(Double.valueOf(split[0]), Double.valueOf(split[1]));
            geoModel.addOverlay(new Marker(coordenada, "Gerador do Pedido: " + pedido.getNomeMaterial() + " -- " + pedido.getPesoTotal() + "KG" + " -- R$ " + pedido.getValorTotal()) );
        }
	}

}
