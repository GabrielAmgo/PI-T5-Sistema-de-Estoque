package com.univesp.pji.inventory_manager.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "empresas")
@Data
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da empresa é obrigatório")
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório")
    @Column(unique = true)
    private String cnpj;

    private String endereco;

    private String telefone;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Produto> produtos;
}