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
public class OperacaoPagamento {
    private int id;
    private int idPagamento;
    private float valor;
    private FormaPagamento formaPagto;

    public OperacaoPagamento(int idPagamento, float valor, FormaPagamento formaPagto) {
        this.idPagamento = idPagamento;
        this.valor = valor;
        this.formaPagto = formaPagto;
    }

    public OperacaoPagamento(int id, int idPagamento, float valor, FormaPagamento formaPagto) {
        this.id = id;
        this.idPagamento = idPagamento;
        this.valor = valor;
        this.formaPagto = formaPagto;
    }

    public int getId() {
        return id;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public float getValor() {
        return valor;
    }

    public FormaPagamento getFormaPagto() {
        return formaPagto;
    }
    
    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }
    
    
    
}
