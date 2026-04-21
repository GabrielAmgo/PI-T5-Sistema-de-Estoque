package com.univesp.pji.inventory_manager.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.univesp.pji.inventory_manager.model.Categoria;
import com.univesp.pji.inventory_manager.model.Empresa;
import com.univesp.pji.inventory_manager.model.Produto;
import com.univesp.pji.inventory_manager.repository.CategoriaRepository;
import com.univesp.pji.inventory_manager.repository.EmpresaRepository;
import com.univesp.pji.inventory_manager.repository.ProdutoRepository;

@Configuration // <--- ESSENCIAL PARA RODAR NO STARTUP
public class DataInitializer implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final EmpresaRepository empresaRepository;

    // O Spring usa este construtor para injetar os repositórios automaticamente
    public DataInitializer(ProdutoRepository produtoRepository, 
                           CategoriaRepository categoriaRepository, 
                           EmpresaRepository empresaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (produtoRepository.count() == 0) { // Só cria se o banco estiver vazio
            System.out.println(">> Iniciando carga de dados de teste...");

            Empresa emp = new Empresa();
            emp.setNome("Materiais de Construção UNIVESP");
            emp.setCnpj("00.000.000/0001-00");
            empresaRepository.save(emp);

            Categoria cat = new Categoria();
            cat.setNome("Materiais Básicos");
            categoriaRepository.save(cat);

            Produto p1 = new Produto();
            p1.setNome("Cimento CP-II 50kg");
            p1.setPrecoCusto(new BigDecimal("30.00"));
            p1.setPrecoVenda(new BigDecimal("45.00"));
            p1.setQuantidadeEstoque(100);
            p1.setEstoqueMinimo(10);
            p1.setEmpresa(emp);
            p1.setCategoria(cat);
            produtoRepository.save(p1);

            System.out.println(">> Carga finalizada! Produtos criados.");
        }
    }
}