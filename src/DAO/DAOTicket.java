package DAO;


import DAO.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Ticket;

/**
 *
 * @author otvfe
 */
public class DAOTicket {
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
    
    public boolean insereTicket(Ticket ticket){
        
        conectar();
        String sql = "INSERT INTO TICKET(assunto, setor, status, tipo_ocorr, dt_previsao, "
                + "prioridade, solicitante, responsavel, conteudo) VALUES(?,?,?,?,?,?,?,?,?)";
        
        try{
            comando = con.prepareStatement(sql);
            comando.setString(1, ticket.getAssunto());
            comando.setString(2, ticket.getSetor());
            comando.setString(3, ticket.getStatus());
            comando.setString(4, ticket.getTipo_ocorr());
            comando.setString(5, ticket.getDt_previsao());
            comando.setString(6, ticket.getPrioridade());
            comando.setString(7, ticket.getSolicitante());
            comando.setString(8, ticket.getResponsavel());
            comando.setString(9, ticket.getConteudo());
            
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
    
    
    public  ArrayList<Ticket> selecionarTodosRegistros()
    {
        conectar();
        //interface utilizada pra guardar dados vindos de um banco de dados
        ResultSet rs;
        String sql = "SELECT * FROM TICKET";
        //lista que conterá todas as informações de pessoas no banco de dados
        ArrayList<Ticket> listaTickets = new ArrayList();
        try{
            comando = con.prepareStatement(sql);
            rs = comando.executeQuery();
            while(rs.next())
            {
                Ticket ticket = new Ticket();
                ticket.setAssunto(rs.getString("ASSUNTO"));
                ticket.setSetor(rs.getString("SETOR"));
                ticket.setStatus(rs.getString("STATUS"));
                ticket.setTipo_ocorr(rs.getString("TIPO_OCORR"));
                ticket.setDt_previsao(rs.getString("DT_PREVISAO"));
                ticket.setPrioridade(rs.getString("PRIORIDADE"));
                ticket.setSolicitante(rs.getString("SOLICITANTE"));
                ticket.setResponsavel(rs.getString("RESPONSAVEL"));
                ticket.setConteudo(rs.getString("CONTEUDO"));
                listaTickets.add(ticket);
            }
            fechar();
            return listaTickets;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao buscar registro."+e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
            fechar();
            return null;
        }
            
    }
    
    public boolean removeTicket(Integer id){
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
    
    public boolean alteraTicket(Ticket ticket){
        conectar();
         String sql = "UPDATE TICKET SET ASSUNTO = ?, SETOR = ?, STATUS = ?, TIPO_OCORR = ? "
                 + "DT_PREVISAO = ?, PRIORIDADE = ?, SOLICITANTE = ?, RESPONSAVEL = ?, CONTEUDO = ?"
                 + "WHERE ID=?";
         try{
            comando = con.prepareStatement(sql);
            comando.setString(1, ticket.getAssunto());
            comando.setString(2, ticket.getSetor());
            comando.setString(3, ticket.getStatus());
            comando.setString(4, ticket.getTipo_ocorr());
            comando.setString(5, ticket.getDt_previsao());
            comando.setString(6, ticket.getPrioridade());
            comando.setString(7, ticket.getSolicitante());
            comando.setString(8, ticket.getResponsavel());
            comando.setString(9, ticket.getConteudo());
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
