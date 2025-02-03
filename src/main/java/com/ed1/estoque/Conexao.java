package com.ed1.estoque;

import com.ed1.estoque.ed.Fila;
import com.ed1.estoque.ed.Lista;
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

    /**
     * Recupera a lista de livros do banco de dados
     */
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
        } catch (NullPointerException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
        return lista.getLivros();

    }

    /**
     * Recupera a fila de espera no banco de dados 
     */
    public String[] getFila() {
        Fila fila = new Fila();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT pessoa FROM fila ORDER BY id ASC");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                fila.inserir(rs.getString("pessoa"));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar a fila do banco de dados");
        } catch (NullPointerException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
        return fila.getFila();
    }

    /**
     * Salva os livros no banco de dados
     */
    public void salvarAlteracoes(Lista lista) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("TRUNCATE TABLE livro RESTART IDENTITY");
            stmt.executeUpdate();

            for (Livro l : lista.getLivros()) {
                stmt = this.conn.prepareStatement("INSERT INTO livro (titulo, autor, anodepublicacao, quantidadenoestoque) VALUES (?, ?, ?, ?)");
                stmt.setString(1, l.getTitulo());
                stmt.setString(2, l.getAutor());
                stmt.setInt(3, l.getAnoDePublicacao());
                stmt.setInt(4, l.getQuantidadeNoEstoque());
                stmt.executeUpdate();
            }

            System.out.println("Lista de livros salva.");
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro de banco de dados ao salvar lista: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
    }

    /**
     * Salva a fila de espera no banco de dados 
     */
    public void salvarAlteracoes(Fila fila) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("TRUNCATE TABLE fila RESTART IDENTITY");
            stmt.executeUpdate();

            for (String pessoa : fila.getFila()) {
                stmt = this.conn.prepareStatement("INSERT INTO fila (pessoa) VALUES (?)");
                stmt.setString(1, pessoa);
                stmt.executeUpdate();
            }

            System.out.println("Fila de espera salva.");
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
    }
}
