package com.TTecnologia.EmissorNfeTorres.dao;

import com.TTecnologia.EmissorNfeTorres.model.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaDao extends JpaRepository<Empresa, Integer> {
}
