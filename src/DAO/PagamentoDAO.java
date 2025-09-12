/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.OperacaoPagamento;
import classes.Pagamento;
import classes.StringData;
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
public class PagamentoDAO {
    Connection conexao = null;
    
    public PagamentoDAO() {
        
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
    
    public void insert(Pagamento p){
        String comando = "INSERT INTO pagamento (clienteServico_dataInicio, clienteServico_cliente_id, clienteServico_id, clienteServico_servico_id, dtVencimento, dtPagto, valorOriginal) "
                            + "VALUES ('"+ p.getDataInicioServico()+"',"+p.getIdCliente()+","+p.getIdClienteServico()+","+p.getIdServico()+",'"+p.getDataVencimento()+"','"+StringData.dateToSql(p.getDataPagamento())+"',"+p.getValorOriginal()+")";
        int idPagamentoGerado = 0;
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            idPagamentoGerado = stat.executeUpdate(comando, Statement.RETURN_GENERATED_KEYS);
            ResultSet r = stat.getGeneratedKeys();
            if(r.next())
                idPagamentoGerado = r.getInt(1);
            
            //fazer insert das operacoes
            for(OperacaoPagamento op : p.getOperacoes()){
                op.setIdPagamento(idPagamentoGerado);
                new OperacaoPagamentoDAO().insert(op);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void updateDataPagamento(Pagamento p){
        String comando = "UPDATE pagamento SET dtPagto='" + p.getDataPagamento()
                                            + "' WHERE clientServico_id = " + p.getIdClienteServico()
                                            + "AND dtVencimento = '" + p.getDataVencimento()+"'";
        
            
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    public List<Pagamento> listPorPeriodo(String dInicio, String dFim){
        String comando = "SELECT * FROM pagamento WHERE dtPagto >= '" + StringData.dateToSql(dInicio) + "' AND dtPagto <= '"+ StringData.dateToSql(dFim)+"' ORDER BY dtPagto DESC";
        ArrayList<Pagamento> pagtos = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);
            
            while(rs.next()){
                int id = rs.getInt("id");
                int idCliente = rs.getInt("clienteServico_cliente_id");
                int idClienteServico = rs.getInt("clienteServico_id");
                int idServico = rs.getInt("clienteServico_servico_id");
                String dtVenc = rs.getString("dtVencimento");
                String dtPag = rs.getString("dtPagto");
                float valor = rs.getFloat("valorOriginal");
                Pagamento p = new Pagamento();
                p.setId(id);
                p.setIdCliente(idCliente);
                p.setIdClienteServico(idClienteServico);
                p.setIdServico(idServico);
                p.setDataVencimento(dtVenc);
                p.setDataPagamento(dtPag);
                p.setValorOriginal(valor);
                
                //select de operacoesPagamento para ter o valor total pago
                String comando2 = "SELECT * FROM operacaoPagamento WHERE pagamento_id = " + id;
                Statement stat2 = this.conexao.createStatement();
                ResultSet rs2 = stat2.executeQuery(comando2);
                while(rs2.next()){
                    int idOper = rs2.getInt("id");
                    float valorOper = rs2.getFloat("valor");
                    p.addOperacao(new OperacaoPagamento(idOper, id, valorOper, null));
                }
                
                pagtos.add(p);
            }
        } catch (SQLException ex) {
             System.out.println(ex);
        }

        return pagtos;
    }
    
    public List<Pagamento> list(){
        String comando = "SELECT * FROM pagamento ORDER BY dtPagto DESC";
        ArrayList<Pagamento> pagtos = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);
            
            while(rs.next()){
                int id = rs.getInt("id");
                int idCliente = rs.getInt("clienteServico_cliente_id");
                int idClienteServico = rs.getInt("clienteServico_id");
                int idServico = rs.getInt("clienteServico_servico_id");
                String dtVenc = rs.getString("dtVencimento");
                String dtPag = rs.getString("dtPagto");
                float valor = rs.getFloat("valorOriginal");
                Pagamento p = new Pagamento();
                p.setId(id);
                p.setIdCliente(idCliente);
                p.setIdClienteServico(idClienteServico);
                p.setIdServico(idServico);
                p.setDataVencimento(dtVenc);
                p.setDataPagamento(dtPag);
                p.setValorOriginal(valor);
                
                //select de operacoesPagamento para ter o valor total pago
                String comando2 = "SELECT * FROM operacaoPagamento WHERE pagamento_id = " + id;
                Statement stat2 = this.conexao.createStatement();
                ResultSet rs2 = stat2.executeQuery(comando2);
                while(rs2.next()){
                    int idOper = rs2.getInt("id");
                    float valorOper = rs2.getFloat("valor");
                    p.addOperacao(new OperacaoPagamento(idOper, id, valorOper, null));
                }
                
                pagtos.add(p);
            }
        } catch (SQLException ex) {
             System.out.println(ex);
        }

        return pagtos;
    }
    
    
    
    
    
    private final void criarTabela(){
        try {
            String url = "jdbc:mysql://localhost:3306/FinanSites4u";
            String user = "root";
            String senha = "";
            this.conexao = DriverManager.getConnection(url, user, senha);
            Statement s = conexao.createStatement();
            //s.executeUpdate("CREATE DATABASE IF NOT EXISTS finanSites4u;");  //nao é possível criar a base sem conexao
            s.executeUpdate("CREATE TABLE IF NOT EXISTS pagamento (" +
                                "  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," +
                                "  clienteServico_dataInicio DATE NOT NULL," +
                                "  clienteServico_cliente_id INTEGER UNSIGNED NOT NULL," +
                                "  clienteServico_id INTEGER UNSIGNED NOT NULL," +
                                "  clienteServico_servico_id INTEGER UNSIGNED NOT NULL," +
                                "  dtVencimento DATE NULL," +
                                "  dtPagto DATE NULL," +
                                "  valorOriginal FLOAT NULL," +
                                "  PRIMARY KEY(id)," +
                                "  INDEX fatura_FKIndex1(clienteServico_id, clienteServico_cliente_id, clienteServico_servico_id, clienteServico_dataInicio)" +
                                ");");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}


