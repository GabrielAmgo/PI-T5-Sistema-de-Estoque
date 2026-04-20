package com.univesp.pji.inventory_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.univesp.pji.inventory_manager.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}