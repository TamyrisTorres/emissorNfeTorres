package com.TTecnologia.EmissorNfeTorres.controller;

import com.TTecnologia.EmissorNfeTorres.model.entity.Cliente;
import com.TTecnologia.EmissorNfeTorres.service.ClienteService;
import com.TTecnologia.EmissorNfeTorres.model.utlis.ValidationCpfCnpj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    private ValidationCpfCnpj validationCpfCnpj;


    @PostMapping
    public void createCliente(@RequestBody Cliente cliente) throws IOException, InterruptedException {
        clienteService.addCliente(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes(){
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Integer id){
        Cliente cliente = clienteService.getCliente(id);
        if (cliente != null){
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public String deleteCliente(@PathVariable Integer id) {
        Cliente cliente = clienteService.getCliente(id);

        if (cliente != null) {
            clienteService.deleteCliente(id);
            return ("Cliente deletado com sucesso");
        }

        return ("Cliente não encontrado");
    }

    @PutMapping
    public Cliente updateCliente(@PathVariable Integer id, @RequestBody Cliente newCliente){
        Cliente cliente = clienteService.getCliente(id);

        if(cliente != null){
            newCliente.setId(id);
            return newCliente;
        }

        return null;
    }
}
