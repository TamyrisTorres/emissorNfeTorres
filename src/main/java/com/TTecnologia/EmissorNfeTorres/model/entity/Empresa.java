package com.TTecnologia.EmissorNfeTorres.model.entity;

import com.TTecnologia.EmissorNfeTorres.model.utlis.ValidationCpfCnpj;
import jakarta.persistence.*;

import java.io.IOException;


@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "razao-social", unique = true, nullable = false)
    private String razaoSocial;

    @Column(name = "cnpj", unique = true, nullable = false)
    private String CNPJ;

    @Embedded
    private Endereco endereco;

    public Empresa(String razaoSocial, String CNPJ, Endereco endereco) {
        this.razaoSocial = razaoSocial;
        this.CNPJ = CNPJ;
        this.endereco = endereco;
    }

    public Empresa() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static Boolean validDataEmpresa(Empresa empresa) {
        return ValidationCpfCnpj.validationCNPJ(empresa.getCNPJ());
    }

    public Empresa changeEmpresa(Empresa empresa, Empresa newEmpresa){

        if (validDataEmpresa(newEmpresa)){
            empresa.setRazaoSocial(newEmpresa.getRazaoSocial());
            empresa.setCNPJ(newEmpresa.getCNPJ());
            empresa.setEndereco(newEmpresa.getEndereco());

            return empresa;
        }

        return null;
    }
}
