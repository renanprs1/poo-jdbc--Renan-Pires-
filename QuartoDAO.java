package dao;

import model.CategoriaQuarto;
import model.Quarto;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuartoDAO {

    public Quarto salvar(Quarto q) throws SQLException {
        String sql = "INSERT INTO quarto (numero, andar, status, categoria_id) " +
                     "VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt   (1, q.getNumero());
            ps.setInt   (2, q.getAndar());
            ps.setString(3, q.getStatus());
            ps.setInt   (4, q.getCategoria().getId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) q.setId(rs.getInt(1));
            }
            return q;
        }
    }

    public Quarto buscarPorId(int id) throws SQLException {
        String sql = "SELECT q.id, q.numero, q.andar, q.status, " +
                     "       c.id AS cat_id, c.nome, c.capacidade_pessoas, c.preco_base " +
                     "FROM quarto q " +
                     "INNER JOIN categoria_quarto c ON q.categoria_id = c.id " +
                     "WHERE q.id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    public List<Quarto> listarTodos() throws SQLException {
        String sql = "SELECT q.id, q.numero, q.andar, q.status, " +
                     "       c.id AS cat_id, c.nome, c.capacidade_pessoas, c.preco_base " +
                     "FROM quarto q " +
                     "INNER JOIN categoria_quarto c ON q.categoria_id = c.id " +
                     "ORDER BY q.id";
        List<Quarto> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public void atualizar(Quarto q) throws SQLException {
        String sql = "UPDATE quarto SET numero=?, andar=?, status=?, categoria_id=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt   (1, q.getNumero());
            ps.setInt   (2, q.getAndar());
            ps.setString(3, q.getStatus());
            ps.setInt   (4, q.getCategoria().getId());
            ps.setInt   (5, q.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM quarto WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Quarto mapear(ResultSet rs) throws SQLException {
        CategoriaQuarto cat = new CategoriaQuarto(
                rs.getInt("cat_id"),
                rs.getString("nome"),
                rs.getInt("capacidade_pessoas"),
                rs.getDouble("preco_base"));

        return new Quarto(
                rs.getInt("id"),
                rs.getInt("numero"),
                rs.getInt("andar"),
                rs.getString("status"),
                cat);
    }
}
