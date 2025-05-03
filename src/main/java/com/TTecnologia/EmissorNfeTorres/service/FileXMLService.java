package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.FileXMLDao;
import com.TTecnologia.EmissorNfeTorres.model.entity.FileXML;
import com.TTecnologia.EmissorNfeTorres.model.entity.NotaFiscal;
import com.TTecnologia.EmissorNfeTorres.model.utlis.GeradorXmlNfe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileXMLService {

    @Autowired
    private FileXMLDao fileXMLDao;

    public void saveXml(FileXML fileXML) {
        if (fileXML != null) {
            fileXMLDao.save(fileXML);
        }
    }

    public FileXML gerarXMl(NotaFiscal notaFiscal) {
        if (notaFiscal == null || notaFiscal.getId() == null) {
            throw new IllegalArgumentException("NotaFiscal não pode ser nula e deve ter um ID válido.");
        }

        String xmlData = GeradorXmlNfe.gerarXml(notaFiscal);

        FileXML fileXML = new FileXML();
        fileXML.setXML(xmlData);
        fileXML.setNotaFiscalId(notaFiscal.getId());

        saveXml(fileXML);

        return fileXML;
    }
}
