package com.univesp.pji.inventory_manager.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "produtos")
@Data // Gera getters, setters, equals e hashcode automaticamente pelo Lombok
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    private String descricao;

    private String sku; // Unidade de Manutenção de Estoque (Código de barras/interno)

    @NotNull(message = "O preço de venda é obrigatório")
    @Min(value = 0, message = "O preço não pode ser negativo")
    private BigDecimal precoVenda;

    @NotNull(message = "O preço de custo é obrigatório")
    private BigDecimal precoCusto;

    @NotNull(message = "A quantidade em estoque é obrigatória")
    private Integer quantidadeEstoque;

    @NotNull(message = "O estoque mínimo é obrigatório")
    private Integer estoqueMinimo;

    // Relacionamento com Empresa (conforme seu MER)
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonBackReference
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}