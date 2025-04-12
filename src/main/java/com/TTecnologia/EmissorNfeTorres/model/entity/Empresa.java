package com.TTecnologia.EmissorNfeTorres.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "empresa")
public class Empresa {

    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    @Column(name = "razao-social", unique = true, nullable = false)
    private String razaoSocial;

    @Setter
    @Getter
    @Column(name = "cnpj", unique = true, nullable = false)
    private CNPJ CNPJ;

    @Setter
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    public Empresa(String razaoSocial, CNPJ CNPJ, Endereco endereco) {
        this.razaoSocial = razaoSocial;
        this.CNPJ = CNPJ;
        this.endereco = endereco;
    }

    public Empresa() {}
}
