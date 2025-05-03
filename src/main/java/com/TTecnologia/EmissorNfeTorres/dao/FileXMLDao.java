package com.TTecnologia.EmissorNfeTorres.dao;

import com.TTecnologia.EmissorNfeTorres.model.entity.FileXML;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileXMLDao extends JpaRepository<FileXML, Integer> {
}
