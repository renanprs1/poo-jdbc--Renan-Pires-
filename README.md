# 🏨 Sistema de Gestão Hoteleira — POO + JDBC

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos**, aplicando os conceitos de associação 1:N, persistência com JDBC e integridade referencial em PostgreSQL.

---

## 👤 Identificação do Aluno

- **Nome:** Renan Pires
- **Matrícula:** 1014237
- **Curso:** Sistema de Informação
- **Instituição:** Unilasalle
- **Professor:** Alexandre Neves Louzada
- **Disciplina:** Programação Orientada a Objetos
- **Semestre:** 2026.2

---

## 📋 Descrição do Estudo de Caso

Um hotel necessita administrar as acomodações disponíveis para reservas. Cada quarto é classificado sob uma **categoria** (Standard, Luxo, Suíte), a qual define a capacidade de ocupação e o valor base da diária.

**Modelo de domínio:**

- `CategoriaQuarto` (1) ────── (N) `Quarto`
- Uma categoria categoriza múltiplos quartos do estabelecimento.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **JDBC** (Java Database Connectivity)
- **PostgreSQL 15+**
- **Maven** (gerenciamento de dependências)
- **Eclipse IDE**

---

## 📁 Estrutura do Projeto
