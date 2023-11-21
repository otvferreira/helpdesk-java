/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Comentario;

/**
 *
 * @author otavio.ferreira
 */
public class DAOComentario {
        private Connection con;
    //Pre-compila a query para o banco de dados
    private PreparedStatement comando;
    
    // Classe acessada internamente para conectar com o banco
    private void conectar(){
        con = FabricaConexao.conexao();
    }
    
    //Classe que fecha a conexão com o banco
    private void fechar(){
        try{
            comando.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão"+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public boolean insereComentario(Comentario comentario){
        
        conectar();
        String sql = "INSERT INTO COMENTARIO(status, usuario, "
                + "ticket, conteudo) VALUES(?,?,?,?)";
        
        try{
            comando = con.prepareStatement(sql);
            comando.setString(1, comentario.getStatus());
            comando.setString(2, comentario.getUsuario());
            comando.setString(3, comentario.getTicket());
            comando.setString(4, comentario.getConteudo());
            
            
            comando.execute();
            return true;
        }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Erro ao inserir registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }finally{
            fechar();
        }
        return false;
    }
    
    
    public  ArrayList<Comentario> selecionarTodosRegistros(String ticket)
    {
        conectar();
        //interface utilizada pra guardar dados vindos de um banco de dados
        ResultSet rs;
        String sql = "SELECT * FROM COMENTARIO WHERE TICKET=?";
        //lista que conterá todas as informações de pessoas no banco de dados
        ArrayList<Comentario> listaComentarios = new ArrayList();
        try{
            comando = con.prepareStatement(sql);
            comando.setString(1, ticket);
            rs = comando.executeQuery();
            while(rs.next())
            {
                Comentario comentario = new Comentario();
                comentario.setId(rs.getInt("ID"));
                comentario.setStatus(rs.getString("STATUS"));
                comentario.setUsuario(rs.getString("USUARIO"));
                comentario.setTicket(rs.getString("TICKET"));
                comentario.setConteudo(rs.getString("CONTEUDO"));
                listaComentarios.add(comentario);
            }
            fechar();
            return listaComentarios;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao buscar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
            fechar();
            return null;
        }
            
    }
    
    public boolean removeComentario(Integer id){
        conectar();
        String sql = "DELETE FROM TICKET WHERE ID=?";
        try{
            comando = con.prepareStatement(sql);
            comando.setInt(1, id);
            comando.executeUpdate();
            return true;
        }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Erro ao excluir registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }finally{
            fechar();
        }
        return false;
    }
    
    public boolean alteraComentario(Comentario comentario){
        conectar();
         String sql = "UPDATE TICKET SET STATUS = ?, USUARIO = ?, TICKET = ?, CONTEUDO = ? "
                 + "WHERE ID=?";
         try{
            comando = con.prepareStatement(sql);
            comando.setString(1, comentario.getStatus());
            comando.setString(2, comentario.getUsuario());
            comando.setString(3, comentario.getTicket());
            comando.setString(4, comentario.getConteudo());
            comando.executeUpdate();
            return true;
        }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Erro ao atualizar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }finally{
            fechar();
        }
         return false;
    }
}
