package com.TTecnologia.EmissorNfeTorres.model.entity;


import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "NFe")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "NotaFiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @XmlElement(name = "Emitente")
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

    @XmlElement(name = "Destinatário")
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @XmlElement(name = "DatadeEmissão")
    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    @XmlElement(name = "Produtos")
    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemNotaFiscal> itens = new ArrayList<>();

    @XmlElement(name = "ValorTotal")
    @Column(name = "valor_total")
    private Double valorTotal;

    @XmlElement(name = "ValorTotalDosImpostos")
    @Column(name = "valor_total_impostos")
    private Double valorTotalImpostos;

    @OneToOne
    private FileXML fileXML;

    public NotaFiscal(
            Empresa empresa, Cliente cliente,
                      LocalDate dataEmissao, List<ItemNotaFiscal> itens,
                      Double valorTotal, Double valorTotalImpostos) {
        this.empresa = empresa;
        this.cliente = cliente;
        this.dataEmissao = dataEmissao;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.valorTotalImpostos = valorTotalImpostos;
    }

    public NotaFiscal(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ItemNotaFiscal> getItens() {
        return itens;
    }

    public void setItens(List<ItemNotaFiscal> itens) {
        this.itens = itens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorTotalImpostos() {
        return valorTotalImpostos;
    }

    public void setValorTotalImpostos(Double valorTotalImpostos) {
        this.valorTotalImpostos = valorTotalImpostos;
    }

    public FileXML getFileXML() {
        return fileXML;
    }

    public void setFileXML(FileXML fileXML) {
        this.fileXML = fileXML;
    }

    public static NotaFiscal setUpNfe(Empresa empresa, Cliente cliente, List<ItemNotaFiscal> produtos){

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setEmpresa(empresa);
        notaFiscal.setCliente(cliente);
        notaFiscal.setItens(produtos);
        notaFiscal.setDataEmissao(LocalDate.now());
        notaFiscal.setValorTotal(calcularValorTotal(produtos));

        double totalImpostos = 0.0;
        for (ItemNotaFiscal itemNotaFiscal : produtos) {
            totalImpostos += Produto.calcularTotalImpostos(
                            itemNotaFiscal.getProduto(),
                            itemNotaFiscal.getSubtotal());
        }
        notaFiscal.setValorTotalImpostos(totalImpostos);

        return notaFiscal;
    }

    public NotaFiscal changeNfe(NotaFiscal notaFiscal, NotaFiscal newNotaFiscal) {

        // TODO: CRIAR VALIDAÇÃO PARA SABER SE A NOTA FISCAL JÁ FOI ENVIADA PARA A SEFAZ.

        notaFiscal.setCliente(newNotaFiscal.getCliente());
        notaFiscal.setEmpresa(newNotaFiscal.getEmpresa());
        notaFiscal.setItens(newNotaFiscal.getItens());

        return notaFiscal;
    }

    private static double calcularValorTotal(List<ItemNotaFiscal> produtos) {
        return produtos.stream().mapToDouble(p -> p.getPrecoUnitario() * p.getQuantidade()).sum();
    }
}
