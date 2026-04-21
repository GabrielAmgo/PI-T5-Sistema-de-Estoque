package com.univesp.pji.inventory_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univesp.pji.inventory_manager.model.MovimentacaoEstoque;
import com.univesp.pji.inventory_manager.model.Produto;
import com.univesp.pji.inventory_manager.model.TipoMovimentacao;
import com.univesp.pji.inventory_manager.repository.MovimentacaoRepository;
import com.univesp.pji.inventory_manager.repository.ProdutoRepository;

@Service
public class EstoqueService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public MovimentacaoEstoque registarMovimentacao(Long produtoId, Integer quantidade, TipoMovimentacao tipo, String motivo) {
        // 1. Procurar o produto
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // 2. Atualizar o saldo de stock
        if (tipo == TipoMovimentacao.ENTRADA) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        } else {
            if (produto.getQuantidadeEstoque() < quantidade) {
                throw new RuntimeException("Quantidade insuficiente para esta saída");
            }
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        }

        // 3. Guardar o produto atualizado
        produtoRepository.save(produto);

        // 4. Criar e guardar o log da movimentação
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setProduto(produto);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setTipo(tipo);
        movimentacao.setMotivo(motivo);

        return movimentacaoRepository.save(movimentacao);
    }
}