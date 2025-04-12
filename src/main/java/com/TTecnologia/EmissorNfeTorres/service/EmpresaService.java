package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.EmpresaDao;
import com.TTecnologia.EmissorNfeTorres.model.entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaDao empresaDao;

    private String addEmpresa(Empresa empresa){
        boolean hasEmpresa = empresaDao.existsById(empresa.getId());

        if (!hasEmpresa){
            empresaDao.save(empresa);

            return "Empresa cadastrado com Sucesso.";
        }

        return "Empresa não cadastrado";
    }

    private List<Empresa> getAllEmpresas(){
        List<Empresa> empresas = empresaDao.findAll();

        if(!empresas.isEmpty()){
            return empresas;
        }
        return null;
    }

    private Empresa getEmpresa(Long id){
        Optional<Empresa> empresa = empresaDao.findById(id);

        if (empresa.isPresent()){
            return empresa.get();
        }

        return null;
    }

    private String upDateEmpresa(Long id, Empresa newEmpresa){
        Optional<Empresa> empresaOptional = empresaDao.findById(id);

        if(empresaOptional.isPresent()){
            Empresa empresa = empresaOptional.get();
            newEmpresa.setId(empresa.getId());
            empresaDao.save(newEmpresa);

            return "Cadastro do empresa efetuado com sucesso.";
        }

        return "Cadastro não efetuado.";
    }

    private void deleteEmpresa(Long id){
        Optional<Empresa> empresaOptional = empresaDao.findById(id);

        if(empresaOptional.isPresent()){
            empresaDao.deleteById(id);
        }
    }
}
