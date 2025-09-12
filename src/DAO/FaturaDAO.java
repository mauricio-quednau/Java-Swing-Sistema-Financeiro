/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.Pagamento;
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
public class FaturaDAO {
    Connection conexao = null;

    public FaturaDAO() {

        try {
            String url = "jdbc:mysql://localhost:3306/FinanSites4u";
            String user = "root";
            String senha = "";
            this.conexao = DriverManager.getConnection(url, user, senha);
        } catch (SQLException ex) {
            System.out.println(ex);
            criarDatabase();
            criarTabela();
        }
        criarTabela();
    }
    
    public List<String> consultarMesesAnosDisponiveis() {
        ArrayList<String> mesesAnos = new ArrayList();
        String comando = "SELECT mesReferencia FROM fatura GROUP BY mesReferencia";
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);

            while (rs.next()) {
                String mes = rs.getString("mesReferencia");
                mesesAnos.add(mes);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return mesesAnos;
    }
    
    public List<Pagamento> consultar(String mesAno) {
        String comando = "SELECT * FROM fatura WHERE mesReferencia = " + mesAno;
        ArrayList<Pagamento> faturas = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);

            while (rs.next()) {
                int id = rs.getInt("id");
                int clienteId = rs.getInt("cliente_id");
                String mes = rs.getString("mesAno");
                float valor = rs.getFloat("valor");
                float encargos = rs.getFloat("encargos");
                float acrescimo = rs.getFloat("acrescimo");
                String dataVenci = rs.getString("dtencimento");
                String dataPagto = rs.getString("dtPagto");
                char estado = rs.getString("estado").charAt(0);
                String obs = rs.getString("obs");

//                Fatura f = new Fatura(id, clienteId, mes, valor, encargos, acrescimo, dataVenci, dataPagto, estado, obs);
   //             faturas.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return faturas;
    }
    
    public void gerarFaturamento(String mesRef){
        //select em clienteServico para saber valor
        String comando = "SELECT cliente_id, qtdParcelasAPagar, SUM(valorStart), SUM(valorMensal) FROM cliente_servico WHERE ativo = TRUE group by cliente_id";
        ArrayList<Pagamento> faturas = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);

            while (rs.next()) {
                int id = rs.getInt("id");
                int servicoId = rs.getInt("cliente_servico_servico_id");
                int clienteId = rs.getInt("cliente_servico_cliente_id");
                int cliSer = rs.getInt("cliente_servico");
                String mes = rs.getString("mesReferencia");
                String dataVenci = rs.getString("dataVencimento");
                float vlTotal = rs.getFloat("valorTotal");
                float encargos = rs.getFloat("encargos");
                float acrescimo = rs.getFloat("acrescimo");
                boolean pago = rs.getBoolean("pago");
                String dataPagto = rs.getString("dataPagamento");
                char estado = rs.getString("estado").charAt(0);
                String obs = rs.getString("obs");

//                Fatura f = new Fatura(id, clienteId, servicoId, cliSer, mes, dataVenci, vlTotal, encargos, acrescimo, pago, dataPagto, estado, obs);
  //              faturas.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
/*        String comando = "INSERT INTO cliente (nomeRSocial, cpfCnpj, email, telefone, endereco, dataCadastro, observacao, usuario, senha) "
                + "VALUES ('" + c.getNomeRSocial() + "','" + c.getCpfCnpj() + "','" + c.getEmail() + "','" + c.getTelefone() + "','" + c.getEndereco() + "','" + c.getDataCadastroToSQL() + "','" + c.getObservacao() + "', '" + c.getUsuario() + "','" + c.getSenha() + "')";

        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }*/
    }
    public void apagarFaturamento(String mesRef){
        String comando = "DELETE FROM fatura WHERE mesReferencia = '" + mesRef+"'";
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    
    private final void criarDatabase(){
        Statement st;
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
            st = conexao.createStatement();
            int result = st.executeUpdate("CREATE DATABASE IF NOT EXISTS FinanSites4u");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    private final void criarTabela(){
        try {
            String url = "jdbc:mysql://localhost:3306/FinanSites4u";
            String user = "root";
            String senha = "";
            this.conexao = DriverManager.getConnection(url, user, senha);
            Statement s = conexao.createStatement();
            //s.executeUpdate("CREATE DATABASE IF NOT EXISTS finanSites4u;");  //nao é possível criar a base sem conexao
            s.executeUpdate("CREATE TABLE IF NOT EXISTS fatura (" +
                                "  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," +
                                "  clienteServico_cliente_id INTEGER UNSIGNED NOT NULL," +
                                "  clienteServico_id INTEGER UNSIGNED NOT NULL," +
                                "  clienteServico_servico_id INTEGER UNSIGNED NOT NULL," +
                                "  dtVencimento DATE NULL," +
                                "  dtPagto DATE NULL," +
                                "  valorOriginal FLOAT NULL," +
                                "  valorPago FLOAT NULL," +
                                "  obs VARCHAR(254) NULL," +
                                "  PRIMARY KEY(id)," +
                                "  INDEX fatura_FKIndex1(clienteServico_id, clienteServico_cliente_id, clienteServico_servico_id)" +
                                ");");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}


/*
CREATE TABLE fatura (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  clienteServico_cliente_id INTEGER UNSIGNED NOT NULL,
  clienteServico_id INTEGER UNSIGNED NOT NULL,
  clienteServico_servico_id INTEGER UNSIGNED NOT NULL,
  dtVencimento DATE NULL,
  dtPagto DATE NULL,
  valorOriginal FLOAT NULL,
  valorPago FLOAT NULL,
  obs VARCHAR(254) NULL,
  PRIMARY KEY(id),
  INDEX fatura_FKIndex1(clienteServico_id, clienteServico_cliente_id, clienteServico_servico_id)
);
*/