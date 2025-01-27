package com.ed1.estoque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

    private final String url = "jdbc:postgresql://localhost/estoque";
    private final String user = "postgres";
    private final String password = "postgres";
    private Connection conn;

    Conexao() {
        try {

            Class.forName("org.postgresql.Driver");

            this.conn = DriverManager.getConnection(this.url, this.user, this.password);

        } catch (ClassNotFoundException e) {
            System.out.println("Classe Driver não encontrada");
        } catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados.");
        }
    }

    public Livro[] getLivros() {
        Lista lista = new Lista();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT id, titulo, autor, anodepublicacao, quantidadenoestoque FROM livro");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Livro l = new Livro();
                l.setId(rs.getInt(1));
                l.setTitulo(rs.getString(2));
                l.setAutor(rs.getString(3));
                l.setAnoDePublicacao(rs.getInt(4));
                l.setQuantidadeNoEstoque(rs.getInt(5));
                lista.inserir(l);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar livros do banco de dados");
        }
        return lista.getLista();

    }
    
    public void salvarAlteracoes(Lista lista) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("TRUNCATE TABLE livro RESTART IDENTITY;");
            stmt.executeUpdate();
            
            
            for(Livro l : lista.getLista()) {
                stmt = this.conn.prepareStatement("INSERT INTO livro (titulo, autor, anodepublicacao, quantidadenoestoque) VALUES (?, ?, ?, ?)");
                stmt.setString(1, l.getTitulo());
                stmt.setString(2, l.getAutor());
                stmt.setInt(3, l.getAnoDePublicacao());
                stmt.setInt(4, l.getQuantidadeNoEstoque());
                stmt.executeUpdate();
            }
            
            System.out.println("Alterações salvas com sucesso.");
            stmt.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
