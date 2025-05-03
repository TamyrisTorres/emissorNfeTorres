package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.ItemNotaFiscalDao;
import com.TTecnologia.EmissorNfeTorres.dao.ProdutoDao;
import com.TTecnologia.EmissorNfeTorres.dto.ItemNotaFiscalDTO;
import com.TTecnologia.EmissorNfeTorres.exception.ProdutoException.ExceptionProductInvalid;
import com.TTecnologia.EmissorNfeTorres.model.entity.ItemNotaFiscal;
import com.TTecnologia.EmissorNfeTorres.model.entity.NotaFiscal;
import com.TTecnologia.EmissorNfeTorres.model.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemNotaFiscalService {

    @Autowired
    private ItemNotaFiscalDao itemNotaFiscalDao;

    @Autowired
    private ProdutoDao produtoDao;

    public List<ItemNotaFiscal> getItensNfeByID(Integer idNfe){
        Optional<List<ItemNotaFiscal>> itemNotaFiscalListOptional = itemNotaFiscalDao.findByNotaFiscalId(idNfe);

        List<ItemNotaFiscal> itemNotaFiscalList = itemNotaFiscalListOptional.get();

        if (itemNotaFiscalList.isEmpty()){
            return null;
        }

        return itemNotaFiscalList;

    }

    public List<ItemNotaFiscal> getAllProdutosByCodigo(List<ItemNotaFiscalDTO> itemNotaFiscalDTOS) {
        List<ItemNotaFiscal> itensProdutosNfe = new ArrayList<>();

        for (ItemNotaFiscalDTO itemNotaFiscalDTO : itemNotaFiscalDTOS ){
            Optional<Produto> produtoOptional = produtoDao.findByCodigo(itemNotaFiscalDTO.codigo());

            if (produtoOptional.isEmpty()){
                throw new ExceptionProductInvalid("Produto não encontrado.");
            }

            ItemNotaFiscal itemNotaFiscal =
                    ItemNotaFiscal.setUpItem(produtoOptional.get(), itemNotaFiscalDTO);

            itensProdutosNfe.add(itemNotaFiscal);
        }

        return itensProdutosNfe;
    }

    public void saveNfeInItens(List<ItemNotaFiscal> itensProdutoList, NotaFiscal notaFiscal) {
        itemNotaFiscalDao.saveAll(ItemNotaFiscal.updateItens(itensProdutoList, notaFiscal));
    }
}
