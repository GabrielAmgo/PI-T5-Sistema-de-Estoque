package com.univesp.pji.inventory_manager.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univesp.pji.inventory_manager.model.MovimentacaoEstoque;
import com.univesp.pji.inventory_manager.model.TipoMovimentacao;
import com.univesp.pji.inventory_manager.service.EstoqueService;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    /**
     * Endpoint para registrar movimentações (Entrada/Saída)
     * Espera um JSON: { "produtoId": 1, "quantidade": 10, "tipo": "ENTRADA", "motivo": "Compra" }
     */
    @PostMapping("/movimentar")
    public ResponseEntity<?> movimentar(@RequestBody Map<String, Object> payload) {
        try {
            Long produtoId = Long.valueOf(payload.get("produtoId").toString());
            Integer quantidade = Integer.valueOf(payload.get("quantidade").toString());
            TipoMovimentacao tipo = TipoMovimentacao.valueOf(payload.get("tipo").toString());
            String motivo = payload.get("motivo").toString();

            MovimentacaoEstoque mov = estoqueService.registarMovimentacao(produtoId, quantidade, tipo, motivo);
            return ResponseEntity.ok(mov);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}