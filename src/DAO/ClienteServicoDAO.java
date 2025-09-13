/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.ClienteServico;
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
public class ClienteServicoDAO {
    Connection conexao = null;
    
    public ClienteServicoDAO() {
        
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
    
    public void insert(ClienteServico cs){
        String comando = "";
        comando = "INSERT INTO clienteServico (cliente_id, servico_id, dataInicio, dataFim, dataVencimento, valor, ativo, periodo) "
                + "VALUES ("+ cs.getIdCliente()+","+cs.getIdServico()+",'"+cs.getDataInicioToSql()+"',"+cs.getDataFimToSql()+",'"+cs.getDataVencimentoToSql()+"',"+cs.getValor()+","+cs.isAtivo()+","+cs.getPeriodo()+")";
        /*//tratamento para caso a data venha nula pois se estiver null não vai '' se tiver valor vai ''
        if(cs.getDataFimToSql().equalsIgnoreCase("NULL")){
            comando = "INSERT INTO clienteServico (cliente_id, servico_id, dataInicio, dataFim, ativo, valorStart, valorMensal, periodoFaturamento, qtdParcelas, qtdParcelasAPagar) "
                + "VALUES ("+ cs.getIdCliente()+","+cs.getIdServico()+",'"+cs.getDataInicioToSql()+"',"+cs.getDataFimToSql()+"',"+cs.isAtivo()+","+cs.getValor()+","+cs.getValorMensal()+","+cs.getQuantidadeParcelas()+","+cs.getQuantidadeParcelasAPagar()+")";
        }else{
            comando = "INSERT INTO clienteServico (cliente_id, servico_id, ativo, dataInicio, dataFim) "
                + "VALUES ("+ cs.getIdCliente()+","+cs.getIdServico()+","+cs.isAtivo()+",'"+cs.getDataInicioToSql()+"','"+cs.getDataFimToSql()+"')";
        }*/
        
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void update(ClienteServico cs){
        String comando = "";
        if(cs.getDataFimToSql().equalsIgnoreCase("NULL")){
            comando = "UPDATE clienteServico SET dataInicio='" + cs.getDataInicio()
                                            + "', dataFim=" + cs.getDataFimToSql()
                                            + ", dataVencimento='" + cs.getDataVencimento()
                                            + "', valor=" + cs.getValor()
                                            + ", ativo=" + cs.isAtivo()
                                            + ", periodo=" + cs.getPeriodo()
                                            + " WHERE id = " + cs.getId();
        }else{
            comando = "UPDATE clienteServico SET dataInicio='" + cs.getDataInicio()
                                            + "', dataFim='" + cs.getDataFimToSql()
                                            + "', dataVencimento='" + cs.getDataVencimento()
                                            + "', valor=" + cs.getValor()
                                            + ", ativo=" + cs.isAtivo()
                                            + ", periodo=" + cs.getPeriodo()
                                            + " WHERE id = " + cs.getId();
        }
            
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    public void updateDataVencimento(int id, String dataVenc){
        String comando = "UPDATE clienteServico SET dataVencimento='" + dataVenc
                                            + "' WHERE id = " + id;
            
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    public void updateDataPeriodo(int id, byte periodo){
        String comando = "UPDATE clienteServico SET periodo=" + periodo
                                            + " WHERE id = " + id;
            
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    
    public void delete(int id){
        String comando = "DELETE FROM clienteServico WHERE id =" + id;
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public List<ClienteServico> consultar(int idCliente){
        String comando = "SELECT * FROM clienteServico WHERE cliente_id = "+ idCliente;
        ArrayList<ClienteServico> servicos = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);
            
            while(rs.next()){
                int id = rs.getInt("id");
                int idCli = rs.getInt("cliente_id");
                int idSer = rs.getInt("servico_id");
                String dataIni = rs.getString("dataInicio");
                String dataFim = rs.getString("dataFim");
                String dataVen = rs.getString("dataVencimento");
                float valor = rs.getFloat("valor");
                boolean ativo = rs.getBoolean("ativo");
                byte perriodo = rs.getByte("periodo");
                
                //tratamento para nao dar erro depois ao parsear a data para exibir na tela
                if(dataFim == null)
                    dataFim = "";
                ClienteServico cs = new ClienteServico(id, idCliente, idSer, dataIni, dataFim, dataVen, valor, ativo, perriodo);
                
                servicos.add(cs);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return servicos;
    }
    
    public List<ClienteServico> listAtivos() {
        String comando = "SELECT * FROM clienteServico WHERE ativo = TRUE ORDER BY dataVencimento";
        ArrayList<ClienteServico> clientesServicos = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);

            while (rs.next()) {
                int id = rs.getInt("id");
                int idCli = rs.getInt("cliente_id");
                int idSer = rs.getInt("servico_id");
                String dataIni = rs.getString("dataInicio");
                String dataFim = rs.getString("dataFim");
                String dataVen = rs.getString("dataVencimento");
                float valor = rs.getFloat("valor");
                boolean ativo = rs.getBoolean("ativo");
                byte perriodo = rs.getByte("periodo");
                
                //tratamento para nao dar erro depois ao parsear a data para exibir na tela
                if(dataFim == null)
                    dataFim = "";
                ClienteServico cs = new ClienteServico(id, idCli, idSer, dataIni, dataFim, dataVen, valor, ativo, perriodo);
                
                clientesServicos.add(cs);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return clientesServicos;
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
            s.executeUpdate("CREATE TABLE IF NOT EXISTS clienteServico (" +
                                "  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," +
                                "  cliente_id INTEGER UNSIGNED NOT NULL," +
                                "  servico_id INTEGER UNSIGNED NOT NULL," +
                                "  dataInicio DATE NOT NULL," +
                                "  dataFim DATE NULL," +
                                "  dataVencimento DATE NULL," +
                                "  valor DOUBLE NULL," +
                                "  ativo BOOL NULL," +
                                "  periodo INTEGER UNSIGNED NULL," +
                                "  PRIMARY KEY(id, cliente_id, servico_id, dataInicio)," +
                                "  INDEX cliente_servico_FKIndex1(cliente_id)," +
                                "  INDEX cliente_servico_FKIndex2(servico_id)" +
                                ");");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    /*
    public List<ClienteServico> list(){
        String comando = "SELECT * FROM clienteServico";
        ArrayList<Cliente> clientes = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nomeRSocial = rs.getString("nomeRSocial");
                String cpfCnpj = rs.getString("cpfCnpj");
                String email = rs.getString("email");
                String fone = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                String dataCadastro = rs.getString("dataCadastro");
                String obs = rs.getString("observacao");
                String usuario = rs.getString("usuario");
                String senha = rs.getString("senha");
                
                Cliente c = new Cliente(id, nomeRSocial, cpfCnpj ,email, fone, endereco, dataCadastro, obs, usuario, senha);
                
                clientes.add(c);
            }
        } catch (SQLException ex) {
             System.out.println(ex);
        }

        return clientes;
    }*/
}

