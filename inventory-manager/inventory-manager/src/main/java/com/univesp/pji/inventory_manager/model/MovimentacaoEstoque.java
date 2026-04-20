package com.univesp.pji.inventory_manager.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "movimentacoes_estoque")
@Data
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @NotNull
    private Produto produto;

    @NotNull
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoMovimentacao tipo; // ENTRADA ou SAIDA

    private LocalDateTime dataHora;

    private String motivo; // Ex: "Venda", "Reposição", "Ajuste de Inventário"

    @PrePersist
    protected void onCreate() {
        this.dataHora = LocalDateTime.now();
    }
}