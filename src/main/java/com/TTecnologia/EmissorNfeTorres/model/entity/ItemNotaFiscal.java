package com.TTecnologia.EmissorNfeTorres.model.entity;


import com.TTecnologia.EmissorNfeTorres.dto.ItemNotaFiscalDTO;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class ItemNotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id")
    @XmlTransient
    private NotaFiscal notaFiscal;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;
    private Double precoUnitario;
    private Double subtotal;

    public ItemNotaFiscal(NotaFiscal notaFiscal, Produto produto, Integer quantidade, Double precoUnitario, Double subtotal) {
        this.notaFiscal = notaFiscal;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
    }

    public ItemNotaFiscal() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public static ItemNotaFiscal setUpItem(Produto produto, ItemNotaFiscalDTO itemNotaFiscalDTO) {
        ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();

        itemNotaFiscal.setProduto(produto);
        itemNotaFiscal.setPrecoUnitario(itemNotaFiscalDTO.valor());
        itemNotaFiscal.setQuantidade(itemNotaFiscalDTO.quantidade());
        itemNotaFiscal.setSubtotal(calcularTotalProduto(itemNotaFiscalDTO.valor(), itemNotaFiscalDTO.quantidade()));

        return itemNotaFiscal;
    }

    public static List<ItemNotaFiscal> updateItens(List<ItemNotaFiscal> itensProdutoList, NotaFiscal notaFiscal) {
        itensProdutoList.forEach(
                p -> p.setNotaFiscal(notaFiscal));

        return itensProdutoList;
    }

    private static Double calcularTotalProduto(Double produtoDto, Integer quantidade) {
        return produtoDto * quantidade;
    }
}
