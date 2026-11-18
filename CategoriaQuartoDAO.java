package dao;

import model.CategoriaQuarto;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaQuartoDAO {

    public CategoriaQuarto salvar(CategoriaQuarto c) throws SQLException {
        String sql = "INSERT INTO categoria_quarto (nome, capacidade_pessoas, preco_base) " +
                     "VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNome());
            ps.setInt   (2, c.getCapacidadePessoas());
            ps.setDouble(3, c.getPrecoBase());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
            return c;
        }
    }

    public CategoriaQuarto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM categoria_quarto WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    public List<CategoriaQuarto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM categoria_quarto ORDER BY id";
        List<CategoriaQuarto> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public void atualizar(CategoriaQuarto c) throws SQLException {
        String sql = "UPDATE categoria_quarto SET nome=?, capacidade_pessoas=?, preco_base=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setInt   (2, c.getCapacidadePessoas());
            ps.setDouble(3, c.getPrecoBase());
            ps.setInt   (4, c.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM categoria_quarto WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private CategoriaQuarto mapear(ResultSet rs) throws SQLException {
        return new CategoriaQuarto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("capacidade_pessoas"),
                rs.getDouble("preco_base"));
    }
}
