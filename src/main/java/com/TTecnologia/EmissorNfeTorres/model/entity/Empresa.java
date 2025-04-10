package com.TTecnologia.EmissorNfeTorres.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    @NotNull(message = "O nome não pode ser nulo.")
    private String razaoSocial;

    @Setter
    @Getter
    @NotNull(message = "O CNPJ não pode ser nulo.")
    @UniqueElements
    private CNPJ CNPJ;

    @Setter
    @Getter
    @NotNull(message = "O endereço não pode ser nulo.")
    private String endereco;

    public Empresa(String razaoSocial, org.hibernate.validator.constraints.br.CNPJ CNPJ, String endereco) {
        this.razaoSocial = razaoSocial;
        this.CNPJ = CNPJ;
        this.endereco = endereco;
    }

    public Empresa() {}
}
