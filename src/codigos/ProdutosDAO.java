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

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
