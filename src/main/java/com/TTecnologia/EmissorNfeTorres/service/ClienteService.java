package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.ClienteDao;
import com.TTecnologia.EmissorNfeTorres.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    private String addCliente(Cliente cliente){
        boolean hasCliente = clienteDao.existsById(cliente.getId());

        if (!hasCliente){
            clienteDao.save(cliente);

            return "Cliente cadastrado com Sucesso.";
        }

        return "Cliente não cadastrado";
    }

    private List<Cliente> getAllClientes(){
        List<Cliente> clientes = clienteDao.findAll();

        if(!clientes.isEmpty()){
            return clientes;
        }
        return null;
    }

    private Cliente getCliente(Long id){
        Optional<Cliente> cliente = clienteDao.findById(id);

        if (cliente.isPresent()){
            return cliente.get();
        }

        return null;
    }

    private String upDateCliente(Long id, Cliente newCliente){
        Optional<Cliente> clienteOptional = clienteDao.findById(id);

        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            newCliente.setId(cliente.getId());
            clienteDao.save(newCliente);

            return "Cadastro do cliente efetuado com sucesso.";
        }

        return "Cadastro não efetuado.";
    }

    private void deleteCliente(Long id){
        Optional<Cliente> clienteOptional = clienteDao.findById(id);

        if(clienteOptional.isPresent()){
            clienteDao.deleteById(id);
        }
    }
}
