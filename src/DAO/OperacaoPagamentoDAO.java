/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.FormaPagamento;
import classes.OperacaoPagamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mauricio
 */
public class OperacaoPagamentoDAO {
    Connection conexao = null;
    
    public OperacaoPagamentoDAO() {
        
        try {
//            String url = "jdbc:mysql://localhost:3306/FinanSites4u";
//            String user = "root";
//            String senha = "";
//            this.conexao = DriverManager.getConnection(url, user, senha);
            this.conexao = Conexao.getInstance();
        } catch (SQLException ex) {
            System.out.println(ex);
            criarTabela();
        }   
        criarTabela();
    }
    
    void insert(OperacaoPagamento op){
        String comando = "INSERT INTO operacaoPagamento (pagamento_id, fp_id, valor) "
                            + "VALUES ("+ op.getIdPagamento()+","+op.getFormaPagto().getId()+","+op.getValor()+")";
        
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public List<OperacaoPagamento> list(int idPagto){
        String comando = "SELECT * FROM operacaoPagamento WHERE pagamento_id";
        ArrayList<OperacaoPagamento> opsPagto = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);
            
            while(rs.next()){
                int id = rs.getInt("id");
                //int idPagto = rs.getInt("pagamento_id");
                float valor = rs.getFloat("valor");
                int idFp = rs.getInt("fp_id");
                
                String comando2 = "SELECT * FROM fp WHERE id = " + idFp;
                Statement stat2 = this.conexao.createStatement();
                ResultSet rs2 = stat2.executeQuery(comando2);
                String descricaoFp = "";
                if(rs2.next()){
                    descricaoFp = rs2.getString("descricao");
                }
                
                OperacaoPagamento op = new OperacaoPagamento(id, idPagto, valor, new FormaPagamento(idFp, descricaoFp));
                opsPagto.add(op);
            }
        } catch (SQLException ex) {
             System.out.println(ex);
        }

        return opsPagto;
    }
    
    
    
    private final void criarTabela(){
        try {
//            String url = "jdbc:mysql://localhost:3306/FinanSites4u";
//            String user = "root";
//            String senha = "";
//            this.conexao = DriverManager.getConnection(url, user, senha);
            this.conexao = Conexao.getInstance();
            Statement s = this.conexao.createStatement();
            //s.executeUpdate("CREATE DATABASE IF NOT EXISTS finanSites4u;");  //nao é possível criar a base sem conexao
            s.executeUpdate("CREATE TABLE IF NOT EXISTS operacaoPagamento (" +
                                "  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," +
                                "  pagamento_id INTEGER UNSIGNED NOT NULL," +
                                "  fp_id INTEGER UNSIGNED NOT NULL," +
                                "  valor FLOAT NULL," +
                                "  PRIMARY KEY(id)," +
                                "  INDEX pagamento_FKIndex2(fp_id)," +
                                "  INDEX operacaoPagamento_FKIndex2(pagamento_id)" +
                                ");");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}



/*
CREATE TABLE operacaoPagamento (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  pagamento_id INTEGER UNSIGNED NOT NULL,
  fp_id INTEGER UNSIGNED NOT NULL,
  valor FLOAT NULL,
  PRIMARY KEY(id),
  INDEX pagamento_FKIndex2(fp_id),
  INDEX operacaoPagamento_FKIndex2(pagamento_id)
);
*/