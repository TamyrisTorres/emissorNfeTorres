package com.TTecnologia.EmissorNfeTorres.controller;

import com.TTecnologia.EmissorNfeTorres.model.entity.Produto;
import com.TTecnologia.EmissorNfeTorres.service.ProdutoService;
import com.TTecnologia.EmissorNfeTorres.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping
    public void createProduto(@RequestBody Produto produto){
        produtoService.addProduto(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos(){
        return ResponseEntity.ok(produtoService.getAllProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable Integer id){
        Produto produto = produtoService.getProduto(id);
        if (produto != null){
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public String deleteProduto(@PathVariable Integer id) {
        Produto produto = produtoService.getProduto(id);

        if (produto != null) {
            produtoService.deleteProduto(id);
            return ("Produto deletado com sucesso");
        }

        return ("Produto não encontrado");
    }

    @PutMapping
    public Produto updateProduto(@PathVariable Integer id, @RequestBody Produto newProduto){
        Produto produto = produtoService.getProduto(id);

        if(produto != null){
            newProduto.setId(id);
            return newProduto;
        }

        return null;
    }


}
