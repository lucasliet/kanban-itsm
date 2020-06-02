package ads.kanban.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.kanban.model.entity.*;
import ads.kanban.model.service.ColunaService;

import javax.xml.transform.Result;

public class ComentarioDAO {

    private ComentarioEntity criaComentarioEntityCompleto(ResultSet rs) throws SQLException {
        ComentarioEntity comentario = new ComentarioEntity();
        comentario.setId(rs.getInt("c.id"));
        comentario.setCorpo(rs.getString("corpo"));
        TicketEntity ticket = new TicketEntity();
        ticket.setId(rs.getInt("t.id"));
        ticket.setTitulo(rs.getString("t.titulo"));
        ticket.setDescricao(rs.getString("t.descricao"));
        ticket.setFoto(rs.getString("t.foto"));
        ColunaEntity coluna = new ColunaEntity();
        coluna.setId(rs.getInt("co.id"));
        coluna.setTitulo(rs.getString("co.titulo"));
        QuadroEntity quadro = new QuadroEntity();
        quadro.setId(rs.getInt("q.id"));
        quadro.setTitulo(rs.getString("q.titulo"));
        coluna.setQuadro(quadro);
        ticket.setColuna(coluna);
        comentario.setTicket(ticket);
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(rs.getInt("u.id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setUltimoNome(rs.getString("ultimo_nome"));
        usuario.setEndereco(rs.getString("endereco"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setFoto(rs.getString("u.foto"));
        usuario.setEmail(rs.getString("email"));
        comentario.setUsuario(usuario);
        return comentario;
    }

    private String criaQueryDeSelect(String condicao){
        return "SELECT c.id, c.corpo, " +
                      "u.id, nome, ultimo_nome, endereco, telefone, u.foto, email, " +
                      "t.id, t.titulo, t.descricao, t.foto, " +
                      "co.id, co.titulo, " +
                      "q.id, q.titulo FROM comentarios c " +
                    "JOIN usuarios u ON c.id_usuario = u.id " +
                    "JOIN tickets t  ON t.id = c.id_ticket " +
                    "JOIN colunas co ON t.id_coluna = co.id " +
                    "JOIN quadros q  ON co.id_quadro = q.id " +
                    "WHERE "+condicao+" = ?";
    }

    public ArrayList<ComentarioEntity> ultimosComentarios(int idTicket, int limit) throws IOException {
        ArrayList<ComentarioEntity> comentarios = new ArrayList<>();
        String sql = criaQueryDeSelect("t.id") + " ORDER BY c.id DESC LIMIT = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idTicket);
            pst.setInt(2, limit);
            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    comentarios.add(criaComentarioEntityCompleto(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return comentarios;
    }

    public ArrayList<ComentarioEntity> listarComentarios(int idTicket) throws IOException {
        ArrayList<ComentarioEntity> comentarios = new ArrayList<>();
        String sql = criaQueryDeSelect("t.id");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idTicket);
            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    comentarios.add(criaComentarioEntityCompleto(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return comentarios;
    }

    public ComentarioEntity buscarComentario(int idComentario) throws IOException {
        ComentarioEntity comentario = new ComentarioEntity();
        String sql = criaQueryDeSelect("c.id");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idComentario);
            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    comentario = criaComentarioEntityCompleto(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return comentario;
    }

    public int inserirComentario(ComentarioEntity comment) throws IOException {
        int id = -1;
        String sql = "INSERT INTO comentarios (corpo, id_usuario, id_ticket) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, comment.getCorpo());
            pst.setInt(2, comment.getUsuario().getId());
            pst.setInt(3, comment.getTicket().getId());
            pst.execute();

            String query = "SELECT LAST_INSERT_ID()";
            try (PreparedStatement pst1 = conn.prepareStatement(query);
                 ResultSet rs = pst1.executeQuery()) {

                if (rs.next()) {
                    id = rs.getInt(1);
                    comment.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        System.out.println("comentario " + comment.getCorpo() + " de ID " + comment.getId() + " gerado no banco com sucesso");
        return id;
    }

    public boolean deletarComentario(int idComentario) throws IOException {
        boolean deletou = false;
        ComentarioEntity coment;
        String sql = "DELETE FROM comentarios WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            coment = buscarComentario(idComentario);
            if (coment.getId() != 0) {
                pst.setInt(1, idComentario);
                pst.execute();
                deletou = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        if (deletou) {
            System.out.println("Corpo " + coment.getId() + " de Corpo " + coment.getCorpo() + " foi removido com sucesso");
        } else {
            System.out.println("Operação falhou");
        }
        return deletou;
    }
}
