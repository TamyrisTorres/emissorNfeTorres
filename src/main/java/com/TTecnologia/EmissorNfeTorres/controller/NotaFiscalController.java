package com.TTecnologia.EmissorNfeTorres.controller;

import com.TTecnologia.EmissorNfeTorres.model.entity.NotaFiscal;
import com.TTecnologia.EmissorNfeTorres.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notaFiscal")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;


    @PostMapping
    public void createNotaFiscal(@RequestBody NotaFiscal notaFiscal){
        notaFiscalService.addNotaFiscal(notaFiscal);
    }

    @GetMapping
    public ResponseEntity<List<NotaFiscal>> getAllNotaFiscals(){
        return ResponseEntity.ok(notaFiscalService.getAllNotaFiscals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscal> getNotaFiscal(@PathVariable Integer id){
        NotaFiscal notaFiscal = notaFiscalService.getNotaFiscal(id);
        if (notaFiscal != null){
            return ResponseEntity.ok(notaFiscal);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public String deleteNotaFiscal(@PathVariable Integer id) {
        NotaFiscal notaFiscal = notaFiscalService.getNotaFiscal(id);

        if (notaFiscal != null) {
            notaFiscalService.deleteNotaFiscal(id);
            return ("NotaFiscal deletado com sucesso");
        }

        return ("NotaFiscal não encontrado");
    }

    @PutMapping
    public NotaFiscal updateNotaFiscal(@PathVariable Integer id, @RequestBody NotaFiscal newNotaFiscal){
        NotaFiscal notaFiscal = notaFiscalService.getNotaFiscal(id);

        if(notaFiscal != null){
            newNotaFiscal.setId(id);
            return newNotaFiscal;
        }

        return null;
    }
}
