/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.util;

import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.granderio.appreciclagem.model.Endereco;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Response;

/**
 *
 * https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?wsdl
 */
public class Consulta {

    public static Endereco consultarCEP(String cep) throws SQLException_Exception, SigepClienteException{
         Endereco retorno = new Endereco();
         br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService service = new br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService();
         br.com.correios.bsb.sigep.master.bean.cliente.AtendeCliente port = service.getAtendeClientePort();
         br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP result = port.consultaCEP(cep);

//         ServiceCorreios service = new ServiceCorreios();
//         AtendeCliente port = service.getAtendeClientePort();
//         EnderecoERP result = port.consultaCEP(cep);
         
         retorno.setBairro(result.getBairro());
         retorno.setCep(cep);
         retorno.setCidade(result.getCidade());
         retorno.setLogradouro(result.getEnd());
         retorno.setUf(result.getUf());
         return retorno;
    }
    
    public static Endereco restCEP(String cep){
        if(cep.length() > 8){
            String splitOne = cep.substring(0, 2);
            String splitTwo = cep.substring(3,6);
            String splitThree = cep.substring(7);
            cep = splitOne + splitTwo + splitThree;
        }
        Endereco end = new Endereco();
        end.setCep(cep);
        
        Map<String, String> mapaJson = null;
        Gson gson = new Gson();
        try{
        Client client = ClientBuilder.newClient();   
        WebTarget target = client.target(retornaURLWS(cep));
        
        String resposta = target.request(MediaType.APPLICATION_JSON).get(String.class);
        
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        mapaJson = gson.fromJson(resposta, type);
           
        if(mapaJson != null){
            end.setBairro(mapaJson.get("bairro"));
            end.setCidade(mapaJson.get("localidade"));
            end.setUf(mapaJson.get("uf"));
            end.setLogradouro(mapaJson.get("logradouro"));
        }
        
	} catch (Exception ex) {
            System.out.println(ex.getMessage());
	}
         return end;
	}
    
    private static String retornaURLWS(String cep){
            StringBuilder retorno = new StringBuilder();
            retorno.append("https://viacep.com.br/ws/");
            retorno.append(cep);
            retorno.append("/json");
            return retorno.toString();
    }

    
    }

        
