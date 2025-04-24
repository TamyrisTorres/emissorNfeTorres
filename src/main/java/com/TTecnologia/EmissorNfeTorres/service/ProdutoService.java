package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.ProdutoDao;
import com.TTecnologia.EmissorNfeTorres.exception.ProdutoException.ExceptionProductInvalid;
import com.TTecnologia.EmissorNfeTorres.model.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoDao produtoDao;


    public void addProduto(Produto produto){
        boolean hasProduto = produtoDao.existsById(produto.getId());

        if (hasProduto){
            throw new ExceptionProductInvalid("Produto não cadastrado.");
        }

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

        if(produtoOptional.isEmpty()){
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
