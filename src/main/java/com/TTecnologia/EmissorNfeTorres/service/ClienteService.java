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

    public Cliente addCliente(Cliente cliente){
        Optional<Cliente> clienteOptional = clienteDao.findById(cliente.getId());

        if (!clienteOptional.isPresent()){
            return clienteDao.save(cliente);
        }

        return null;
    }

    public List<Cliente> getAllClientes(){
        List<Cliente> clientes = clienteDao.findAll();

        if(!clientes.isEmpty()){
            return clientes;
        }
        return null;
    }

    public Cliente getCliente(Integer id){
        Optional<Cliente> cliente = clienteDao.findById(id);

        if (cliente.isPresent()){
            return cliente.get();
        }

        return null;
    }

    public String upDateCliente(Integer id, Cliente newCliente){
        Optional<Cliente> clienteOptional = clienteDao.findById(id);

        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            newCliente.setId(cliente.getId());
            clienteDao.save(newCliente);

            return "Cadastro do cliente efetuado com sucesso.";
        }

        return "Cadastro não efetuado.";
    }

    public void deleteCliente(Integer id){
        Optional<Cliente> clienteOptional = clienteDao.findById(id);

        if(clienteOptional.isPresent()){
            clienteDao.deleteById(id);
        }
    }
}
