package com.TTecnologia.EmissorNfeTorres.dto;

import com.TTecnologia.EmissorNfeTorres.model.entity.Produto;

import java.util.List;

public record NfeSimplifyRequestDTO(String nomeEmpresa, String cnpjEmpresa,
                                    String nomeCliente, String cnpjCpfCliente,
                                    List<ItemNotaFiscalDTO> itensProdutoList) {
}
