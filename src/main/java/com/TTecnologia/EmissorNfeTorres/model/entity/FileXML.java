package com.TTecnologia.EmissorNfeTorres.model.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlTransient;

@Entity
public class FileXML {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String xmlData;

    @XmlTransient
    @OneToOne
    @JoinColumn(name = "nota_fiscal_id", referencedColumnName = "id")
    private NotaFiscal notaFiscal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXML() {
        return xmlData;
    }

    public void setXML(String xmlData) {
        this.xmlData = xmlData;
    }

    public Integer getNotaFiscalId() {
        return (notaFiscal != null) ? notaFiscal.getId() : null;
    }

    public void setNotaFiscalId(Integer notaFiscalId) {
        if (notaFiscal == null) {
            notaFiscal = new NotaFiscal(); // Apenas cria uma instância se for null
        }
        notaFiscal.setId(notaFiscalId); // Atribui o ID à NotaFiscal
    }

}
