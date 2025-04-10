package com.TTecnologia.EmissorNfeTorres.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@Table(name = "NotaFiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero", nullable = false, unique = true)
    private String numero;

    @Setter
    @Getter
    @NotNull
    @Column(name = "empresa")
    private Empresa empresa;

    @Setter
    @Getter
    @NotNull
    @Column(name = "cliente")
    private Cliente cliente;

    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    @Setter
    @Getter
    @NotNull(message = "O endereço não pode ser nulo.")
    @Column(name = "produtos")
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
}
