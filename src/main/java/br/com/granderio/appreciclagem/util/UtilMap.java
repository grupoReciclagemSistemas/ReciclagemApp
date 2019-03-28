/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.GeocodingResult;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @programador 
 * Since 2018
 * Rafael Nunes - rafaelnunes.inf@gmail.com - Desenvolvedor Java
 */
public class UtilMap {
    
    private static final String CHAVE_GOOGLE = "AIzaSyAXbHIRvrMpX-k18AXrJzgRY1Op3H0CNxo";
    
    public static void codigoGeo(String endereco) throws ApiException, InterruptedException, IOException{
        GeoApiContext context = new GeoApiContext.Builder().apiKey(CHAVE_GOOGLE).build();
        GeocodingResult[] results = GeocodingApi.geocode(context, endereco).await();
       
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(results[0].addressComponents));  
    }
    
    // Remetente --> Pessoa logada no sistema
    // Destino --> Cada um do DataTable
    public static void calcularDistancia(String enderecoRemetente, String enderecoDestino) throws ApiException, IOException, InterruptedException{
       GeoApiContext context = new GeoApiContext.Builder().apiKey(CHAVE_GOOGLE).build();
       
       DirectionsApiRequest request = DirectionsApi.getDirections(context, enderecoRemetente, enderecoDestino).language("pt_BR");
       DirectionsResult result = request.await();
       DirectionsRoute[] rota = result.routes;
       System.out.println(Arrays.toString(rota));    
       Gson gson = new GsonBuilder().setPrettyPrinting().create();
       System.out.println(gson.toJson(result));
    }

    /**
     * @return the CHAVE_GOOGLE
     */
    public static String getCHAVE_GOOGLE() {
        return CHAVE_GOOGLE;
    }

}
