package com.TTecnologia.EmissorNfeTorres.model.entity;

import com.TTecnologia.EmissorNfeTorres.model.utlis.ValidationCpfCnpj;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cnpjcpf", unique = true,  nullable = false)
    private String cnpjCpf;

    @Embedded
    private Endereco endereco;

    @Column(name = "telefone")
    private String telefone;

    public Cliente(String nome,
                   String cnpjCpf,
                   Endereco endereco, String telefone) {
        this.nome = nome;
        this.cnpjCpf = cnpjCpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Cliente() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public static Boolean validDataClient(Cliente cliente) {
        return ValidationCpfCnpj.validationCNPJ(cliente.cnpjCpf) ||
                ValidationCpfCnpj.isValidCPF(cliente.cnpjCpf);
    }

    public Cliente changeClient(Cliente cliente, Cliente newCliente){

        if (validDataClient(newCliente)){
            cliente.setNome(newCliente.getNome());
            cliente.setCnpjCpf(newCliente.getCnpjCpf());
            cliente.setEndereco(newCliente.getEndereco());
            cliente.setTelefone(newCliente.getTelefone());

            return cliente;
        }

        return null;
    }
}

