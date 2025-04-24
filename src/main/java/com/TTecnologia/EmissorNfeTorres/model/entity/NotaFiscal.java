package com.TTecnologia.EmissorNfeTorres.model.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

@Entity
@Table(name = "NotaFiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero", nullable = false, unique = true)
    private String numero;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "nota_fiscal_id")
    private List<Produto> produtoList;


    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "chave_acesso", length = 44, unique = true)
    private String chaveAcesso;

    public NotaFiscal(String numero, Empresa empresa, Cliente cliente,
                      LocalDate dataEmissao, List<Produto> produtoList,
                      Double valorTotal, String chaveAcesso) {
        this.numero = numero;
        this.empresa = empresa;
        this.cliente = cliente;
        this.dataEmissao = dataEmissao;
        this.produtoList = produtoList;
        this.valorTotal = valorTotal;
        this.chaveAcesso = chaveAcesso;
    }

    public NotaFiscal() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa( Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente( Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public NotaFiscal changeNfe(NotaFiscal notaFiscal, NotaFiscal newNotaFiscal) {

        // TODO: CRIAR VALIDAÇÃO PARA SABER SE A NOTA FISCAL JÁ FOI ENVIADA PARA A SEFAZ.

        notaFiscal.setCliente(newNotaFiscal.getCliente());
        notaFiscal.setEmpresa(newNotaFiscal.getEmpresa());
        notaFiscal.setProdutoList(newNotaFiscal.getProdutoList());

        return notaFiscal;
    }
}
