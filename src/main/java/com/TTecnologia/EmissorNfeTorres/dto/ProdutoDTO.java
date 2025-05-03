package com.TTecnologia.EmissorNfeTorres.dto;

import com.TTecnologia.EmissorNfeTorres.model.entity.Imposto;

public record ProdutoDTO(Long codigo, String nome, String descricao, Long quantidade,
                         Double preco, String ncm, String cfop, Imposto imposto) {
}
