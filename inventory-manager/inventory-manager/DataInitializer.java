package com.univesp.pji.inventory_manager.config;

import com.univesp.pji.inventory_manager.model.Categoria;
import com.univesp.pji.inventory_manager.model.Empresa;
import com.univesp.pji.inventory_manager.model.Produto;
import com.univesp.pji.inventory_manager.repository.CategoriaRepository;
import com.univesp.pji.inventory_manager.repository.EmpresaRepository;
import com.univesp.pji.inventory_manager.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final EmpresaRepository empresaRepository;

    public DataInitializer(ProdutoRepository produtoRepository, 
                           CategoriaRepository categoriaRepository, 
                           EmpresaRepository empresaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Criar Empresa de Teste
        Empresa empresa = new Empresa();
        empresa.setNome("Materiais de Construção Silva");
        empresa.setCnpj("12.345.678/0001-99");
        empresaRepository.save(empresa);

        // 2. Criar Categorias
        Categoria cat1 = new Categoria();
        cat1.setNome("Básicos");
        categoriaRepository.save(cat1);

        Categoria cat2 = new Categoria();
        cat2.setNome("Elétrica");
        categoriaRepository.save(cat2);

        // 3. Criar Produtos de Exemplo
        Produto p1 = new Produto();
        p1.setNome("Cimento CP-II 50kg");
        p1.setSku("CIM001");
        p1.setPrecoCusto(new BigDecimal("28.50"));
        p1.setPrecoVenda(new BigDecimal("35.00"));
        p1.setQuantidadeEstoque(100);
        p1.setEstoqueMinimo(20);
        p1.setCategoria(cat1);
        p1.setEmpresa(empresa);
        produtoRepository.save(p1);

        Produto p2 = new Produto();
        p2.setNome("Fio Flexível 2.5mm 100m");
        p2.setSku("ELE001");
        p2.setPrecoCusto(new BigDecimal("150.00"));
        p2.setPrecoVenda(new BigDecimal("210.00"));
        p2.setQuantidadeEstoque(15);
        p2.setEstoqueMinimo(10);
        p2.setCategoria(cat2);
        p2.setEmpresa(empresa);
        produtoRepository.save(p2);

        System.out.println(">> Banco de dados inicializado com sucesso!");
    }
}