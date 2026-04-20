package com.univesp.pji.inventory_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.univesp.pji.inventory_manager.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}