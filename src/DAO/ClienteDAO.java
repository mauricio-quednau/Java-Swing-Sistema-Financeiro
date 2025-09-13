/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.Cliente;
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
public class ClienteDAO {

    Connection conexao = null;

    public ClienteDAO() {

        try {
            //String url = "jdbc:mysql://localhost:3306/FinanSites4u";
            //String user = "root";
            //String senha = "";
            //this.conexao = DriverManager.getConnection(url, user, senha);
            this.conexao = Conexao.getInstance();
        } catch (SQLException ex) {
            System.out.println(ex);
            criarDatabase();
            criarTabela();
        }
        criarTabela();
    }

    public void insert(Cliente c) {
        String comando = "INSERT INTO cliente (nomeRSocial, cpfCnpj, email, telefone, endereco, dataCadastro, observacao, usuario, senha) "
                + "VALUES ('" + c.getNomeRSocial() + "','" + c.getCpfCnpj() + "','" + c.getEmail() + "','" + c.getTelefone() + "','" + c.getEndereco() + "','" + c.getDataCadastroToSQL() + "','" + c.getObservacao() + "', '" + c.getUsuario() + "','" + c.getSenha() + "')";

        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void update(Cliente c) {

        String comando = "UPDATE cliente SET nomeRSocial='" + c.getNomeRSocial()
                + "', cpfCnpj='" + c.getCpfCnpj()
                + "', email='" + c.getEmail()
                + "', telefone='" + c.getTelefone()
                + "', endereco='" + c.getEndereco()
                + "', dataCadastro='" + c.getDataCadastroToSQL() + ")"
                + "', observacao='" + c.getObservacao()
                + "', usuario='" + c.getUsuario()
                + "', senha='" + c.getSenha()
                + "' WHERE id = " + c.getId();
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void delete(int id_cli) {
        String comando = "DELETE FROM cliente WHERE id =" + id_cli;
        Statement stat = null;
        try {
            stat = this.conexao.createStatement();
            stat.executeUpdate(comando);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Cliente> consultar(String nome) {
        String comando = "SELECT * FROM cliente WHERE nomeRSocial like '%" + nome + "%'";
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);

            while (rs.next()) {
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

                Cliente c = new Cliente(id, nomeRSocial, cpfCnpj, email, fone, endereco, dataCadastro, obs, usuario, senha);
                clientes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return clientes;
    }
    
    public Cliente consultar(int id) {
        String comando = "SELECT * FROM cliente WHERE id = "+id;
        Cliente cliente = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);

            while (rs.next()) {
                int id2 = rs.getInt("id");
                String nomeRSocial = rs.getString("nomeRSocial");
                String cpfCnpj = rs.getString("cpfCnpj");
                String email = rs.getString("email");
                String fone = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                String dataCadastro = rs.getString("dataCadastro");
                String obs = rs.getString("observacao");
                String usuario = rs.getString("usuario");
                String senha = rs.getString("senha");

                Cliente c = new Cliente(id2, nomeRSocial, cpfCnpj, email, fone, endereco, dataCadastro, obs, usuario, senha);
                return c;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }

    public List<Cliente> list() {
        String comando = "SELECT * FROM cliente";
        ArrayList<Cliente> clientes = new ArrayList();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = this.conexao.createStatement();
            rs = stat.executeQuery(comando);

            while (rs.next()) {
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

                Cliente c = new Cliente(id, nomeRSocial, cpfCnpj, email, fone, endereco, dataCadastro, obs, usuario, senha);

                clientes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return clientes;
    }

    
    private final void criarDatabase(){
        Statement st;
        try {
            //conexao = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
            this.conexao = Conexao.getInstance();
            st = this.conexao.createStatement();
            int result = st.executeUpdate("CREATE DATABASE IF NOT EXISTS FinanSites4u");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    private final void criarTabela(){
        try {
            //String url = "jdbc:mysql://localhost:3306/FinanSites4u";
            //String user = "root";
            //String senha = "";
            //this.conexao = DriverManager.getConnection(url, user, senha);
            this.conexao = Conexao.getInstance();
            Statement s = this.conexao.createStatement();
            //s.executeUpdate("CREATE DATABASE IF NOT EXISTS finanSites4u;");  //nao é possível criar a base sem conexao
            s.executeUpdate("CREATE TABLE IF NOT EXISTS cliente (" +
                                    "  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," +
                                    "  nomeRSocial VARCHAR(50) NULL," +
                                    "  cpfCnpj VARCHAR(30) NULL," +
                                    "  email VARCHAR(70) NULL," +
                                    "  telefone VARCHAR(30) NULL," +
                                    "  endereco VARCHAR(120) NULL," +
                                    "  dataCadastro DATE NULL," +
                                    "  observacao TEXT NULL," +
                                    "  usuario VARCHAR(20) NULL," +
                                    "  senha VARCHAR(20) NULL," +
                                    "  PRIMARY KEY(id)" +
                                    ");");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}


/*
CREATE TABLE cliente (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nomeRSocial VARCHAR(50) NULL,
  cpfCnpj VARCHAR(30) NULL,
  email VARCHAR(70) NULL,
  telefone VARCHAR(30) NULL,
  endereco VARCHAR(120) NULL,
  dataCadastro DATE NULL,
  observacao TEXT NULL,
  usuario VARCHAR(20) NULL,
  senha VARCHAR(20) NULL,
  PRIMARY KEY(id)
);

CREATE TABLE servico (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(40) NULL,
  descricao VARCHAR(200) NULL,
  valor DOUBLE NULL,
  periocidadeDiasCobranca INTEGER UNSIGNED NULL,
  dataInclusao DATE NULL,
  PRIMARY KEY(id)
);

CREATE TABLE cliente_servico (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  cliente_id INTEGER UNSIGNED NOT NULL,
  servico_id INTEGER UNSIGNED NOT NULL,
  ativo BOOL NULL,
  dataInicio DATE NULL,
  dataFim DATE NULL,
  PRIMARY KEY(id, cliente_id, servico_id),
  INDEX cliente_has_servico_FKIndex1(cliente_id),
  INDEX cliente_has_servico_FKIndex2(servico_id)
);
*/
