package codigos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        try {
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

            conn = new conectaDAO().connectDB();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setInt(2, produto.getValor());
            preparedStatement.setString(3, produto.getStatus());
            JOptionPane.showMessageDialog(null, "Dado salvo com sucesso!");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados: " + e.getMessage());
        }

    }

    public void venderProduto(int id) {
        try {
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

            conn = new conectaDAO().connectDB();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado ou não pôde ser vendido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            String sql = "SELECT * FROM produtos";

            conn = new conectaDAO().connectDB();

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            listagem.clear();

            while (resultSet.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getInt("valor"));
                produto.setStatus(resultSet.getString("status"));
                listagem.add(produto);
            }

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados: " + e.getMessage());
        }
        return listagem;
    }

}
