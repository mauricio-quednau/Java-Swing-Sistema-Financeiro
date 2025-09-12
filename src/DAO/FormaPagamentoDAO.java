/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.FormaPagamento;
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
public class FormaPagamentoDAO {
    Connection conexao = null;
    
    public FormaPagamentoDAO() {
        
        try {
            String url = "jdbc:mysql://localhost:3306/FinanSites4u";
            String user = "root";
            String senha = "";
            this.conexao = DriverManager.getConnection(url, user, senha);
        } catch (SQLException ex) {
            System.out.println(ex);
            criarTabela();
        }   
        criarTabela();
    }
    
    public void insert(FormaPagamento fp){
        String comando = "INSERT INTO fp (descricao) "
                            + "VALUES ('"+ fp.getDescricao()+"')";
        
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public List<FormaPagamento> list(){
        String comando = "SELECT * FROM fp";
        ArrayList<FormaPagamento> fps = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                
                FormaPagamento f = new FormaPagamento(id, descricao);
                fps.add(f);
            }
        } catch (SQLException ex) {
             System.out.println(ex);
        }

        return fps;
    }
    
    
    
    private final void criarTabela(){
        try {
            String url = "jdbc:mysql://localhost:3306/FinanSites4u";
            String user = "root";
            String senha = "";
            this.conexao = DriverManager.getConnection(url, user, senha);
            Statement s = conexao.createStatement();
            //s.executeUpdate("CREATE DATABASE IF NOT EXISTS finanSites4u;");  //nao é possível criar a base sem conexao
            s.executeUpdate("CREATE TABLE IF NOT EXISTS fp (" +
                                "  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," +
                                "  descricao VARCHAR(20) NULL," +
                                "  PRIMARY KEY(id)" +
                                ");");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}



/*
CREATE TABLE fp (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(20) NULL,
  PRIMARY KEY(id)
);
);
*/