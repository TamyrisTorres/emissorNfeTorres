package com.TTecnologia.EmissorNfeTorres.model.utlis;

import com.TTecnologia.EmissorNfeTorres.model.entity.NotaFiscal;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.StringWriter;

public class GeradorXmlNfe {

    public static String gerarXml(NotaFiscal nfe) {
        try{
            JAXBContext context = JAXBContext.newInstance(NotaFiscal.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();

            marshaller.marshal(nfe, stringWriter);

           return stringWriter.toString();

        }catch (JAXBException e){
            e.printStackTrace();
        }
        return null;
    }
}
