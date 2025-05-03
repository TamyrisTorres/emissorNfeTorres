package com.TTecnologia.EmissorNfeTorres.model.entity;

import com.TTecnologia.EmissorNfeTorres.dto.ProdutoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_produto", unique = true, nullable = false)
    private Long codigo;

    @Column(name = "nome_produto", nullable = false)
    private String nome;

    private String descricao;

    private Long quantidade;

    private Double preco;

    private String ncm;

    private String cfop;

    @Embedded
    private Imposto imposto;

    public Produto(Long codigo, String nome, String descricao, Long quantidade,
                   Double preco, String ncm, String cfop, Imposto imposto) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.ncm = ncm;
        this.cfop = cfop;
        this.imposto = imposto;
    }

    public Produto (ProdutoDTO produtoDTO){
        this.codigo = produtoDTO.codigo();
        this.nome = produtoDTO.nome();
        this.descricao = produtoDTO.descricao();
        this.quantidade = produtoDTO.quantidade();
        this.preco = produtoDTO.preco();
        this.ncm = produtoDTO.ncm();
        this.cfop = produtoDTO.cfop();
        this.imposto = produtoDTO.imposto();
    }

    public Produto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public Imposto getImposto() {
        return imposto;
    }

    public void setImposto(Imposto imposto) {
        this.imposto = imposto;
    }

    public Produto changeProduto(Produto produto, Produto newProduto){
       produto.setCodigo(newProduto.getCodigo());
       produto.setNome(newProduto.getNome());
       produto.setDescricao(newProduto.getDescricao());
       produto.setPreco(newProduto.getPreco());
       produto.setQuantidade(newProduto.getQuantidade());
       produto.setNcm(newProduto.getNcm());
       produto.setCfop(newProduto.getCfop());
       produto.setImposto(newProduto.getImposto());

       return produto;
    }

    public void changeStock(ItemNotaFiscal itemNotaFiscal) {
        itemNotaFiscal.getId();
    }

    public static Double calcularTotalImpostos(Produto produto, Double valorProduto){
        return Imposto.setupImpostos(produto, valorProduto);
    };
}
