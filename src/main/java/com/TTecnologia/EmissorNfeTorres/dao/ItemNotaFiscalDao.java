package com.TTecnologia.EmissorNfeTorres.dao;

import com.TTecnologia.EmissorNfeTorres.model.entity.ItemNotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemNotaFiscalDao extends JpaRepository<ItemNotaFiscal, Integer> {

    Optional<List<ItemNotaFiscal>> findByNotaFiscalId(Integer id);
}
