package com.univesp.pji.inventory_manager.controller;

import com.univesp.pji.inventory_manager.model.MovimentacaoEstoque;
import com.univesp.pji.inventory_manager.model.Produto;
import com.univesp.pji.inventory_manager.model.TipoMovimentacao;
import com.univesp.pji.inventory_manager.repository.MovimentacaoRepository;
import com.univesp.pji.inventory_manager.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @GetMapping("/resumo-financeiro")
    public Map<String, Object> getResumoFinanceiro() {
        List<Produto> produtos = produtoRepository.findAll();
        List<MovimentacaoEstoque> movimentacoes = movimentacaoRepository.findAll();

        // 1. INVESTIMENTO: Soma de todas as ENTRADAS (Qtd Movimentada * Preço Custo)
        BigDecimal investimentoTotal = movimentacoes.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.ENTRADA)
                .map(m -> m.getProduto().getPrecoCusto().multiply(BigDecimal.valueOf(m.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2. VENDAS (Faturamento): Soma de todas as SAÍDAS (Qtd Movimentada * Preço Venda)
        BigDecimal faturamentoTotal = movimentacoes.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.SAIDA)
                .map(m -> m.getProduto().getPrecoVenda().multiply(BigDecimal.valueOf(m.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3. VALOR EM ESTOQUE: O que está parado na prateleira agora (Qtd Atual * Preço Venda)
        BigDecimal valorEmEstoqueVenda = produtos.stream()
                .map(p -> p.getPrecoVenda().multiply(BigDecimal.valueOf(p.getQuantidadeEstoque())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> resumo = new HashMap<>();
        resumo.put("valorInvestidoCusto", investimentoTotal);
        resumo.put("faturamentoTotal", faturamentoTotal);
        resumo.put("valorEmEstoqueVenda", valorEmEstoqueVenda);
        resumo.put("totalItens", produtos.size());

        return resumo;
    }
}