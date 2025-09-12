/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Mauricio
 */
public class FormaPagamento {
    public static final String FP_DINHEIRO = "dinheiro";
    public static final String FP_DEPOSITO = "trans/deposito";
    public static final String FP_CARTAO = "cartao";
    public static final String FP_CHEQUE = "cheque";
    public static final String FP_PERMUTA = "permuta";
    
    private int id;
    private String descricao;

    public FormaPagamento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }
    
    
    
}
