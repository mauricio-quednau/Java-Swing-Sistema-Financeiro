package classes;

import java.util.Date;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio
 */
public class Cliente {
    private int id;
    private String nomeRSocial; //nome ou razao social
    private String cpfCnpj;
    private String email;
    private String telefone;
    private String endereco;
    
    private String dataCadastro;
    private String observacao;
    private String usuario;
    private String senha;

    public Cliente() {
    }

    public Cliente(String nomeRSocial, String cpfCnpj, String email, String telefone, String endereco, String dataCadastro, String observacao, String usuario, String senha) {
        this.nomeRSocial = nomeRSocial;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.observacao = observacao;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Cliente(int id, String nomeRSocial, String cpfCnpj, String email, String telefone, String endereco, String dataCadastro, String observacao, String usuario, String senha) {
        this.id = id;
        this.nomeRSocial = nomeRSocial;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.observacao = observacao;
        this.usuario = usuario;
        this.senha = senha;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeRSocial() {
        return nomeRSocial;
    }

    public void setNomeRSocial(String nomeRSocial) {
        this.nomeRSocial = nomeRSocial;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataCadastro(){
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
    public String getDataCadastroToSQL() {
        String dia = dataCadastro.substring(0, 2);
        String mes = dataCadastro.substring(3, 5); 
        String ano = dataCadastro.substring(6);
        return ano+"-"+mes+"-"+dia;
    }
    
    public String getDataCadastroFromSQL() {
        String ano = dataCadastro.substring(0, 4);
        String mes = dataCadastro.substring(5, 7); 
        String dia = dataCadastro.substring(8);
        
        return dia+"/"+mes+"/"+ano;
    }
}
