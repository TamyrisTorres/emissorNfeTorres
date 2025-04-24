package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.ClienteDao;
import com.TTecnologia.EmissorNfeTorres.exception.clientException.ClientInvalidException;
import com.TTecnologia.EmissorNfeTorres.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    public void addCliente(Cliente newcliente){
        Optional<Cliente> clienteOptional = clienteDao.findById(newcliente.getId());

        if (clienteOptional.isPresent() && !Cliente.validDataClient(newcliente)){
            throw new ClientInvalidException("Cliente não cadastrado");
        }

        clienteDao.save(newcliente);
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

        return cliente.orElse(null);
    }

    public void upDateCliente(Integer id, Cliente newCliente){
        Optional<Cliente> clienteOptional = clienteDao.findById(id);

        if(clienteOptional.isEmpty()){
            throw new ClientInvalidException(
                    "Cliente não consta em nosso registro.");
        }

        Cliente cliente = newCliente.changeClient(clienteOptional.get(), newCliente);
        clienteDao.save(cliente);
    }

    public void deleteCliente(Integer id){
        Optional<Cliente> clienteOptional = clienteDao.findById(id);

        if(clienteOptional.isEmpty()){
            throw new ClientInvalidException(
                    "Cliente não consta em nosso registro.");
        }

        clienteDao.deleteById(id);
    }
}
