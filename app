package app;

import dao.CategoriaQuartoDAO;
import dao.QuartoDAO;
import model.CategoriaQuarto;
import model.Quarto;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CategoriaQuartoDAO categoriaDAO = new CategoriaQuartoDAO();
        QuartoDAO quartoDAO = new QuartoDAO();

        try {
            // -------------------------------------------------
            // 1) Salvar a entidade do lado "1" (categoria)
            // -------------------------------------------------
            System.out.println(">> Inserindo categorias...");
            CategoriaQuarto standard = categoriaDAO.salvar(
                    new CategoriaQuarto("Standard", 2, 150.00));
            CategoriaQuarto luxo = categoriaDAO.salvar(
                    new CategoriaQuarto("Luxo", 3, 320.00));
            CategoriaQuarto suite = categoriaDAO.salvar(
                    new CategoriaQuarto("Suíte Presidencial", 4, 980.00));

            System.out.println("  " + standard);
            System.out.println("  " + luxo);
            System.out.println("  " + suite);

            // -------------------------------------------------
            // 2) Salvar entidades do lado "N" associando ao pai
            // -------------------------------------------------
            System.out.println("\n>> Inserindo quartos...");
            Quarto q1 = quartoDAO.salvar(new Quarto(101, 1, "DISPONIVEL", standard));
            Quarto q2 = quartoDAO.salvar(new Quarto(201, 2, "OCUPADO",    luxo));
            Quarto q3 = quartoDAO.salvar(new Quarto(1001, 10, "DISPONIVEL", suite));

            System.out.println("  " + q1);
            System.out.println("  " + q2);
            System.out.println("  " + q3);

            // -------------------------------------------------
            // 3) Consultas
            // -------------------------------------------------
            System.out.println("\n>> Buscar quarto por id (2):");
            System.out.println("  " + quartoDAO.buscarPorId(2));

            System.out.println("\n>> Listar todos os quartos (INNER JOIN):");
            List<Quarto> quartos = quartoDAO.listarTodos();
            for (Quarto q : quartos) {
                System.out.println("  " + q);
            }

            System.out.println("\n>> Listar todas as categorias:");
            for (CategoriaQuarto c : categoriaDAO.listarTodos()) {
                System.out.println("  " + c);
            }

            // -------------------------------------------------
            // 4) Atualização
            // -------------------------------------------------
            System.out.println("\n>> Atualizando quarto 101 para status 'OCUPADO'...");
            q1.setStatus("OCUPADO");
            quartoDAO.atualizar(q1);
            System.out.println("  " + quartoDAO.buscarPorId(q1.getId()));

            // -------------------------------------------------
            // 5) Teste de integridade referencial (RESTRICT)
            // -------------------------------------------------
            System.out.println("\n>> Tentando excluir a categoria 'Luxo' que possui quartos...");
            try {
                categoriaDAO.deletar(luxo.getId());
                System.out.println("   Categoria excluída (não deveria acontecer!).");
            } catch (SQLException e) {
                System.out.println("   Exclusão bloqueada pela FK (ON DELETE RESTRICT): "
                        + e.getMessage());
            }

            // -------------------------------------------------
            // 6) Exclusão válida (filho primeiro, depois pai)
            // -------------------------------------------------
            System.out.println("\n>> Excluindo quarto 1001 e depois a categoria 'Suíte Presidencial'...");
            quartoDAO.deletar(q3.getId());
            categoriaDAO.deletar(suite.getId());
            System.out.println("   Exclusão realizada com sucesso.");

            System.out.println("\n>> Estado final dos quartos:");
            for (Quarto q : quartoDAO.listarTodos()) {
                System.out.println("  " + q);
            }

        } catch (SQLException e) {
            System.err.println("Erro de banco: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
