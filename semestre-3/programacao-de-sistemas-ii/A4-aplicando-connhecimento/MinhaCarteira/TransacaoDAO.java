import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {
    public void salvar(Transacao t) {
        String sql = "INSERT INTO transacoes (descricao, valor, data, tipo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoFactory.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getDescricao());
            stmt.setDouble(2, t.getValor());
            stmt.setString(3, t.getData());
            stmt.setString(4, (t instanceof Receita) ? "RECEITA" : "DESPESA");
            stmt.executeUpdate();
            System.out.println("Salvo no banco com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transacao> listar() {
        List<Transacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacoes";
        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                if (rs.getString("tipo").equals("RECEITA")) {
                    lista.add(new Receita(rs.getString("descricao"), rs.getDouble("valor"), rs.getString("data")));
                } else {
                    lista.add(new Despesa(rs.getString("descricao"), rs.getDouble("valor"), rs.getString("data")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}