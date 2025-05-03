package com.TTecnologia.EmissorNfeTorres.service;

import com.TTecnologia.EmissorNfeTorres.dao.ClienteDao;
import com.TTecnologia.EmissorNfeTorres.dao.EmpresaDao;
import com.TTecnologia.EmissorNfeTorres.dao.NotaFiscalDao;
import com.TTecnologia.EmissorNfeTorres.dto.NfeSimplifyRequestDTO;
import com.TTecnologia.EmissorNfeTorres.exception.NfeException.NfeInvalidException;
import com.TTecnologia.EmissorNfeTorres.exception.ProdutoException.ExceptionProductInvalid;
import com.TTecnologia.EmissorNfeTorres.model.entity.*;
import com.TTecnologia.EmissorNfeTorres.model.utlis.GeradorXmlNfe;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalDao notaFiscalDao;

    @Autowired
    private EmpresaDao empresaDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemNotaFiscalService itemNotaFiscalService;

    @Autowired
    private FileXMLService fileXMLService;

    public NotaFiscal addNotaFiscal(NfeSimplifyRequestDTO nfeSimplifyRequestDTO) {
        return generationDataNfe(nfeSimplifyRequestDTO);
    }

    private NotaFiscal generationDataNfe(NfeSimplifyRequestDTO nfeSimplifyRequestDTO) {
        Optional<Cliente> cliente = clienteDao.findByNome(
                nfeSimplifyRequestDTO.nomeCliente());

        Optional<Empresa> empresa = empresaDao.findByRazaoSocial(
                nfeSimplifyRequestDTO.nomeEmpresa());

        if (cliente.isEmpty() || empresa.isEmpty()) {
            throw new IllegalArgumentException("Cliente ou empresa não encontrados");
        }

        List<ItemNotaFiscal> itensProdutoList = itemNotaFiscalService.getAllProdutosByCodigo(
                nfeSimplifyRequestDTO.itensProdutoList());

        if (itensProdutoList.isEmpty()){
            throw new ExceptionProductInvalid("Inclua produtos a Nota fiscal.");
        }

        NotaFiscal notaFiscal = NotaFiscal.setUpNfe(
                empresa.get(), cliente.get(), itensProdutoList);


        notaFiscal =  notaFiscalDao.save(notaFiscal);
        FileXML fileXML = fileXMLService.gerarXMl(notaFiscal);
        itemNotaFiscalService.saveNfeInItens(itensProdutoList, notaFiscal);
        notaFiscal.setFileXML(fileXML);

        return notaFiscal;
    }

    public List<NotaFiscal> getAllNotaFiscals(){
        List<NotaFiscal> notaFiscalList = notaFiscalDao.findAll();

        if(!notaFiscalList.isEmpty()){
            return notaFiscalList;
        }
        return null;
    }

    public NotaFiscal getNotaFiscal(Integer id){
        Optional<NotaFiscal> notaFiscal = notaFiscalDao.findById(id);

        return notaFiscal.orElse(null);

    }

    public List<ItemNotaFiscal> getItensNfeById(Integer id){
        return itemNotaFiscalService.getItensNfeByID(id);
    }

    public void upDateNotaFiscal(Integer id, NotaFiscal newNotaFiscal){
        Optional<NotaFiscal> notaFiscalOptional = notaFiscalDao.findById(id);

        if(notaFiscalOptional.isEmpty()){
            throw new NfeInvalidException(
                    "Nota fiscal não encontrada em nosso registro");
        }

        NotaFiscal notaFiscal = newNotaFiscal.changeNfe(notaFiscalOptional.get(), newNotaFiscal);
        notaFiscalDao.save(notaFiscal);
    }

    public void deleteNotaFiscal(Integer id){
        Optional<NotaFiscal> notaFiscalOptional = notaFiscalDao.findById(id);

        if(notaFiscalOptional.isPresent()){
            notaFiscalDao.deleteById(id);
        }
    }
}
