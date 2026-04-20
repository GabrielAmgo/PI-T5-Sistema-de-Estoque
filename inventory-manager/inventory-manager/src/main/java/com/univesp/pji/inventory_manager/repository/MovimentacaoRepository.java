package com.univesp.pji.inventory_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.univesp.pji.inventory_manager.model.MovimentacaoEstoque;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEstoque, Long> {
    // Verifique se o primeiro parâmetro entre < > é MovimentacaoEstoque e não Produto
}