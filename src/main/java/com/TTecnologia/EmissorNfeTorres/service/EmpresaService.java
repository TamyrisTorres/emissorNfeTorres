package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.EmpresaDao;
import com.TTecnologia.EmissorNfeTorres.exception.EmpresaException.ExceptionInvalidEmpresa;
import com.TTecnologia.EmissorNfeTorres.exception.NfeException.NfeInvalidException;
import com.TTecnologia.EmissorNfeTorres.model.entity.Empresa;
import com.TTecnologia.EmissorNfeTorres.model.utlis.ValidationCpfCnpj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaDao empresaDao;

    public void addEmpresa(Empresa empresa) {
        boolean hasEmpresa = empresaDao.existsById(empresa.getId());

        if (hasEmpresa && !Empresa.validDataEmpresa(empresa)){
            throw new NfeInvalidException("Empresa não cadastrada.");
        }

        empresaDao.save(empresa);
    }

    public List<Empresa> getAllEmpresas(){
        List<Empresa> empresas = empresaDao.findAll();

        if(!empresas.isEmpty()){
            return empresas;
        }
        return null;
    }

    public Empresa getEmpresa(Integer id){
        Optional<Empresa> empresa = empresaDao.findById(id);

        if (empresa.isEmpty()){
            throw new ExceptionInvalidEmpresa(
                    "Empresa não consta em nosso registro.");
        }

        return empresa.get();
    }

    public void upDateEmpresa(Integer id, Empresa newEmpresa){
        Optional<Empresa> empresaOptional = empresaDao.findById(id);

        if(empresaOptional.isEmpty()){
            throw new ExceptionInvalidEmpresa(
                    "Empresa não consta em nosso registro");
        }

        Empresa empresa = newEmpresa.changeEmpresa(
                empresaOptional.get(), newEmpresa);

        empresaDao.save(empresa);
    }

    public void deleteEmpresa(Integer id){
        Optional<Empresa> empresaOptional = empresaDao.findById(id);

        if(empresaOptional.isPresent()){
            empresaDao.deleteById(id);
        }
    }
}
