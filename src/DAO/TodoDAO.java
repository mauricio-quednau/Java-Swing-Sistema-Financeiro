/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.OperacaoPagamento;
import classes.Pagamento;
import classes.StringData;
import classes.Todo;
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
public class TodoDAO {
    Connection conexao = null;
    
    public TodoDAO() {
        
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
    
    
    public void insert(Todo t){
        String comando = "INSERT INTO todoList (todo, dataInc) "
                            + "VALUES ('"+ t.getTodo()+"','"+StringData.dateToSql(t.getData())+"')";
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
        public List<Todo> list(){
        String comando = "SELECT * FROM todoList";
        ArrayList<Todo> todos = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String todo = rs.getString("todo");
                String dt = rs.getString("dataInc");
                Todo t = new Todo(id, todo, dt);
                
                todos.add(t);
            }
        } catch (SQLException ex) {
             System.out.println(ex);
        }

        return todos;
    }
    
    
    
    
    
    private final void criarTabela(){
        try {
//            String url = "jdbc:mysql://localhost:3306/FinanSites4u";
//            String user = "root";
//            String senha = "";
//            this.conexao = DriverManager.getConnection(url, user, senha);
            this.conexao = Conexao.getInstance();
            Statement s = conexao.createStatement();
            //s.executeUpdate("CREATE DATABASE IF NOT EXISTS finanSites4u;");  //nao é possível criar a base sem conexao
            s.executeUpdate("CREATE TABLE IF NOT EXISTS todoList (" +
                                "  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," +
                                "  todo VARCHAR(254) NULL," +
                                "  dataInc DATE NULL," +
                                "  PRIMARY KEY(id)" +
                                ");");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


}
