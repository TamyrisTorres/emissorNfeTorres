package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.ProdutoDao;
import com.TTecnologia.EmissorNfeTorres.model.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoDao produtoDao;


    public Produto addProduto(Produto produto){
        boolean hasProduto = produtoDao.existsById(produto.getId());

        if (!hasProduto){
            return produtoDao.save(produto);
        }

        return null;
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

        if (produto.isPresent()){
            return produto.get();
        }

        return null;
    }

    public String upDateProduto(Integer id, Produto newProduto){
        Optional<Produto> produtoOptional = produtoDao.findById(id);

        if(produtoOptional.isPresent()){
            Produto produto = produtoOptional.get();
            newProduto.setId(produto.getId());
            produtoDao.save(newProduto);

            return "Cadastro do produto efetuado com sucesso.";
        }

        return "Cadastro não efetuado.";
    }

    public void deleteProduto(Integer id){
        Optional<Produto> produtoOptional = produtoDao.findById(id);

        if(produtoOptional.isPresent()){
            produtoDao.deleteById(id);
        }
    }
}
