package br.com.granderio.appreciclagem.util;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Administrador;
import br.com.granderio.appreciclagem.model.Endereco;
import com.google.maps.errors.ApiException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

public class GerarInstancias {
    
    public static void main(String[] args) throws InterruptedException{
        
//                Administrador adm = new Administrador();
//                adm.setEmail("rafaelnunes.inf@gmail.com");
//                adm.setLogin("admin");
//                adm.setSenha("123");
//                adm.setNome("Administrador Geral");
//                DAO<Administrador> dao = new DAO(adm);
//                dao.inserir();
      
            
//        try {
////            UtilMap.codigoGeo("Avenida Automóvel Clube 609, Rio de Janeiro, Brasil");
//        UtilMap.calcularDistancia("Avenida Automóvel Clube, 609, São João de Meriti", "Estrada Adhemar Bebiano, 250, Rio de Janeiro ");
//        } catch (ApiException | IOException ex) {
//            Logger.getLogger(GerarInstancias.class.getName()).log(Level.SEVERE, null, ex);
//        }

            Endereco a = new Endereco();
        try {
            a = Consulta.restCEP("25.515-125");
        } catch (Exception ex) {
        }
            System.out.println(a.getLogradouro());
    }
   
}
