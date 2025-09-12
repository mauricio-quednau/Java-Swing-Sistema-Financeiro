/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.Servico;
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
public class ServicoDAO {
    Connection conexao = null;
    
    public ServicoDAO() {
        
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
    
    public void insert(Servico s){
        String comando = "INSERT INTO servico (nome, descricao, dataInclusao) "
                + "VALUES ('"+ s.getNome()+"','"+s.getDescricao()+"','"+s.getDataToSQL()+"')";
        
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void update(Servico s){
        
        String comando = "UPDATE servico SET nome='" + s.getNome()
                                            + "', descricao='" + s.getDescricao()
                                            + "', dataInclusao='" + s.getDataToSQL()
                                            + "' WHERE id = " + s.getId();
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    public void delete(int id_ser){
        String comando = "DELETE FROM servico WHERE id =" + id_ser;
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public Servico consultar(int id){
        String comando = "SELECT * FROM servico WHERE id = "+id;
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);
            
            while(rs.next()){
                int id2 = rs.getInt("id");
                String nome2 = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String dataI = rs.getString("dataInclusao");
                
                Servico s = new Servico(id2, nome2, descricao, dataI);
                return s;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }
    
    public List<Servico> consultar(String nome){
        String comando = "SELECT * FROM servico WHERE nome like '%"+ nome + "%'";
        ArrayList<Servico> servicos = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nome2 = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String dataI = rs.getString("dataInclusao");
                
                Servico s = new Servico(id, nome2, descricao, dataI);
                servicos.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return servicos;
    }
    
    public List<Servico> list(){
        String comando = "SELECT * FROM servico";
        ArrayList<Servico> servicos = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nome2 = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String dataI = rs.getString("dataInclusao");
                
                Servico s = new Servico(id, nome2, descricao, dataI);
                servicos.add(s);
            }
        } catch (SQLException ex) {
             System.out.println(ex);
        }

        return servicos;
    }
    
    
    private final void criarTabela(){
        try {
            String url = "jdbc:mysql://localhost:3306/FinanSites4u";
            String user = "root";
            String senha = "";
            this.conexao = DriverManager.getConnection(url, user, senha);
            Statement s = conexao.createStatement();
            //s.executeUpdate("CREATE DATABASE IF NOT EXISTS finanSites4u;");  //nao é possível criar a base sem conexao
            s.executeUpdate("CREATE TABLE IF NOT EXISTS servico (" +
                                "id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,"+
                                "nome VARCHAR(40) NULL,"+
                                "descricao VARCHAR(200) NULL,"+
                                "dataInclusao DATE NULL,"+
                                "PRIMARY KEY(id)"+
                                ");");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}


/*
CREATE TABLE servico (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(40) NULL,
  descricao VARCHAR(200) NULL,
  dataInclusao DATE NULL,
  PRIMARY KEY(id)
);

CREATE TABLE servico (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(40) NULL,
  descricao VARCHAR(200) NULL,
  valor DOUBLE NULL,
  dataInclusao DATE NULL,
  tipoPagamento INTEGER UNSIGNED NULL,
  qtdParcelas INTEGER UNSIGNED NULL,
  periocidadeDiasFaturamento INTEGER UNSIGNED NULL,
  PRIMARY KEY(id)
);
*/