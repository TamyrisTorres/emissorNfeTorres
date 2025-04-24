package com.TTecnologia.EmissorNfeTorres.controller;

import com.TTecnologia.EmissorNfeTorres.model.entity.Empresa;
import com.TTecnologia.EmissorNfeTorres.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;


    @PostMapping
    public void createEmpresa(@RequestBody Empresa empresa) throws IOException, InterruptedException {
        empresaService.addEmpresa(empresa);
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> getAllEmpresas(){
        return ResponseEntity.ok(empresaService.getAllEmpresas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable Integer id){
        Empresa empresa = empresaService.getEmpresa(id);
        if (empresa != null){
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public String deleteEmpresa(@PathVariable Integer id) {
        Empresa empresa = empresaService.getEmpresa(id);

        if (empresa != null) {
            empresaService.deleteEmpresa(id);
            return ("Empresa deletado com sucesso");
        }

        return ("Empresa não encontrado");
    }

    @PutMapping
    public Empresa updateEmpresa(@PathVariable Integer id, @RequestBody Empresa newEmpresa){
        Empresa empresa = empresaService.getEmpresa(id);

        if(empresa != null){
            newEmpresa.setId(id);
            return newEmpresa;
        }

        return null;
    }
}
