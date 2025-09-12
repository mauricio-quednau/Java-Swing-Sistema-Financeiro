
package classes;

import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public class Pagamento {
    
    private int id;
    private int idCliente;
    private int idServico;
    private int idClienteServico;
    private String dataInicioServico;//apenas para base de dados (pk, fk)
    private String dataVencimento;
    private String dataPagamento;
    private float valorOriginal;
    private ArrayList<OperacaoPagamento> operacoes;
    
    public Pagamento() {
        this.operacoes = new ArrayList();
    }

    public Pagamento(int idCliente, int idServico, int idClienteServico, String dataVencimento, String dataPagamento, float valorOriginal, ArrayList<OperacaoPagamento> operacoes) {
        this.idCliente = idCliente;
        this.idServico = idServico;
        this.idClienteServico = idClienteServico;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valorOriginal = valorOriginal;
        this.operacoes = operacoes;
    }

    public Pagamento(int id, int idCliente, int idServico, int idClienteServico, String dataVencimento, String dataPagamento, float valorOriginal, ArrayList<OperacaoPagamento> operacoes) {
        this.id = id;
        this.idCliente = idCliente;
        this.idServico = idServico;
        this.idClienteServico = idClienteServico;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valorOriginal = valorOriginal;
        this.operacoes = operacoes;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdClienteServico() {
        return idClienteServico;
    }

    public void setIdClienteServico(int idClienteServico) {
        this.idClienteServico = idClienteServico;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public float getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(float valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public void setOperacoes(ArrayList<OperacaoPagamento> operacoes) {
        this.operacoes = operacoes;
    }

    public String getDataInicioServico() {
        return dataInicioServico;
    }

    public void setDataInicioServico(String dataInicioServico) {
        this.dataInicioServico = dataInicioServico;
    }
    
    

    
    public ArrayList<OperacaoPagamento> getOperacoes() {
        return operacoes;
    }

    
    
    public void addOperacao(OperacaoPagamento op){
        this.operacoes.add(op);
    }
    
    
    
    
}
