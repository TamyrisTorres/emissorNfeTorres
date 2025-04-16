package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.NotaFiscalDao;
import com.TTecnologia.EmissorNfeTorres.model.entity.NotaFiscal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalDao notaFiscalDao;


    public String addNotaFiscal(NotaFiscal notaFiscal){
        boolean hasNotaFiscal = notaFiscalDao.existsById(notaFiscal.getId());

        if (!hasNotaFiscal){
            notaFiscalDao.save(notaFiscal);

            return "NotaFiscal cadastrado com Sucesso.";
        }

        return "NotaFiscal não cadastrado";
    }

    public List<NotaFiscal> getAllNotaFiscals(){
        List<NotaFiscal> notaFiscals = notaFiscalDao.findAll();

        if(!notaFiscals.isEmpty()){
            return notaFiscals;
        }
        return null;
    }

    public NotaFiscal getNotaFiscal(Integer id){
        Optional<NotaFiscal> notaFiscal = notaFiscalDao.findById(id);

        if (notaFiscal.isPresent()){
            return notaFiscal.get();
        }

        return null;
    }

    public String upDateNotaFiscal(Integer id, NotaFiscal newNotaFiscal){
        Optional<NotaFiscal> notaFiscalOptional = notaFiscalDao.findById(id);

        if(notaFiscalOptional.isPresent()){
            NotaFiscal notaFiscal = notaFiscalOptional.get();
            newNotaFiscal.setId(notaFiscal.getId());
            notaFiscalDao.save(newNotaFiscal);

            return "Cadastro do notaFiscal efetuado com sucesso.";
        }

        return "Cadastro não efetuado.";
    }

    public void deleteNotaFiscal(Integer id){
        Optional<NotaFiscal> notaFiscalOptional = notaFiscalDao.findById(id);

        if(notaFiscalOptional.isPresent()){
            notaFiscalDao.deleteById(id);
        }
    }
}
