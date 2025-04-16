package com.TTecnologia.EmissorNfeTorres.dao;

import com.TTecnologia.EmissorNfeTorres.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Integer> {

}
