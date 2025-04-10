package com.TTecnologia.EmissorNfeTorres.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@Table(name = "NotaFiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    @NotNull
    private Empresa empresa;

    @Setter
    @Getter
    @NotNull
    private Cliente cliente;

    @Setter
    @Getter
    @NotNull(message = "O endereço não pode ser nulo.")
    private List<Produto> produtoList;

    public NotaFiscal(Empresa empresa, Cliente cliente, List<Produto> produtoList) {
        this.empresa = empresa;
        this.cliente = cliente;
        this.produtoList = produtoList;
    }
}
