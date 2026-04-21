# 📦 Inventory Manager Pro | UNIVESP
O Inventory Manager Pro é uma aplicação Full-Stack desenvolvida como Projeto Integrador para a UNIVESP. O objetivo do sistema é fornecer uma interface ágil e confiável para o gerenciamento de materiais, com foco na precisão financeira e rastreabilidade total das movimentações de estoque.

## 🎯 Principais Funcionalidades
📊 Dashboard Financeiro em Tempo Real: Cálculos dinâmicos que separam o Investimento (custo de reposição), Valor em Estoque (patrimônio imobilizado) e Faturamento (receita de vendas realizadas).

⚡ Movimentação Inline: Capacidade de registrar entradas e saídas de estoque diretamente da tabela principal, otimizando o tempo de operação (estilo PDV).

🛡️ Integridade de Dados: Regras de negócio no backend que impedem estoque negativo e garantem a consistência financeira.

📝 Histórico de Transações (Audit Log): Tabela de fatos inalterável que registra cada evento de Entrada ou Saída, incluindo o motivo e a data/hora exata.

🛠️ CRUD Completo de Produtos: Interface dedicada para cadastro, edição e exclusão de itens do inventário.

## 🏗️ Arquitetura e Tecnologias (Stack)
O projeto segue a arquitetura de camadas (N-Tier) para garantir a separação de responsabilidades (Model, View, Controller e Service).

Backend:
- Java 17 (LTS)
- Spring Boot 3.2.x: Framework base (Spring Web, Spring Data JPA).
- H2 Database: Banco de dados relacional embarcado para testes e operação MVP.
- Hibernate: ORM (Object-Relational Mapping).
- Lombok: Redução de código boilerplate.

Frontend:
- HTML5 / CSS3
- Bootstrap 5.3: Framework de estilos e responsividade.
- JavaScript (Vanilla): Lógica assíncrona usando Fetch API para comunicação REST.
- Bootstrap Icons: Biblioteca de iconografia.

## ⚙️ Como Executar o Projeto Localmente
Pré-requisitos:
- Java Development Kit (JDK) 17 instalado.
- IDE (IntelliJ IDEA, Eclipse ou VS Code).
- Maven (geralmente embutido na IDE).

## Passos para Execução
Clone o repositório:

Bash
- git clone https://github.com/seu-usuario/inventory-manager-pro.git
- Importe o projeto na sua IDE favorita como um projeto Maven.
- Aguarde o Maven baixar todas as dependências (pom.xml).
- Execute a classe principal InventoryManagerApplication.java.
- Acesse o sistema: Abra o navegador e acesse a URL:
http://localhost:8080
(Nota: Como o banco H2 está em memória, os dados são resetados ao reiniciar o servidor, a menos que a configuração do application.properties seja alterada para gravar em arquivo).

## 🚀 Próximos Passos (Roadmap de Evolução)
Este projeto foi construído como um MVP (Minimum Viable Product), ainda não está em sua versão final. As seguintes melhorias estão mapeadas para o futuro:

[ ] Módulo de Segurança: Implementação de Autenticação e Autorização (Login) usando Spring Security e JWT.

[ ] Migração de Banco: Substituição do H2 por MySql para persistência definitiva.

[ ] Notificações: Alertas visuais e envio de e-mails automáticos quando produtos atingirem o "Estoque Mínimo".

[ ] Categorização Avançada: Tabela relacional própria para Categorias e Fornecedores.

[ ] Gráficos Analíticos: Integração com Chart.js para visualização da curva de vendas no tempo.
