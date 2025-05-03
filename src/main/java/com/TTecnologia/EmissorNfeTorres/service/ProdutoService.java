package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.ItemNotaFiscalDao;
import com.TTecnologia.EmissorNfeTorres.dao.ProdutoDao;
import com.TTecnologia.EmissorNfeTorres.dto.ItemNotaFiscalDTO;
import com.TTecnologia.EmissorNfeTorres.dto.ProdutoDTO;
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
public class ProdutoService {

    @Autowired
    private ProdutoDao produtoDao;

    @Autowired
    private ItemNotaFiscalDao itemNotaFiscalDao;


    public void addProduto(ProdutoDTO produtoDTO){
        Optional<Produto> produtoOptional = produtoDao.findByCodigo(produtoDTO.codigo());

        if (produtoOptional.isPresent()){
            throw new ExceptionProductInvalid("Produto já cadastrado.");
        }

        Produto produto = new Produto(produtoDTO);
        produtoDao.save(produto);
    }

    public List<Produto> getAllProdutos(){
        List<Produto> produtos = produtoDao.findAll();

        if(!produtos.isEmpty()){
            return produtos;
        }

        return null;
    }

    public Produto getProduto(Integer id){
        Optional<Produto> produto = produtoDao.findById(id);

        return produto.orElse(null);
    }

    public void upDateProduto(Integer id, Produto newProduto){
        Optional<Produto> produtoOptional = produtoDao.findById(id);

        if(produtoOptional.isEmpty() || newProduto == null){
            throw new ExceptionProductInvalid(
                    "Produto não foi encontrado em nosso registro.");
        }

        Produto produto = newProduto.changeProduto(produtoOptional.get(), newProduto);
        produtoDao.save(produto);
    }

    public void deleteProduto(Integer id){
        Optional<Produto> produtoOptional = produtoDao.findById(id);

        if(produtoOptional.isPresent()){
            produtoDao.deleteById(id);
        }
    }
}
