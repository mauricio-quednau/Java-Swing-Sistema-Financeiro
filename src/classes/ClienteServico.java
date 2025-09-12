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
public class ClienteServico {
    private int id;
    private int idCliente;
    private int idServico;
    private String dataInicio;
    private String dataFim;
    private String dataVencimento;
    private float valor;
    private boolean ativo;
    private byte periodo;

    public ClienteServico(int idCliente, int idServico, String dataInicio, String dataFim, String dataVencimento, float valor, boolean ativo, byte periodo) {
        this.idCliente = idCliente;
        this.idServico = idServico;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.ativo = ativo;
        this.periodo = periodo;
    }

    public ClienteServico(int id, int idCliente, int idServico, String dataInicio, String dataFim, String dataVencimento, float valor, boolean ativo, byte periodo) {
        this.id = id;
        this.idCliente = idCliente;
        this.idServico = idServico;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.ativo = ativo;
        this.periodo = periodo;
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

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public byte getPeriodo() {
        return periodo;
    }

    public void setPeriodo(byte periodo) {
        this.periodo = periodo;
    }
    
    
    
    

    
    
    public String getPeriodoFaturamentoString() {
        /*switch(periodoFaturamento){
            case 30:
                return "Mensal";
            case 90:
                return "Trimestral";
            case 180:
                return "Semestral";
            case 360:
                return "Anual";
            default:
                return "Indef";
        }*/return "Indef";
    }
    
    
    public String getDataInicioToSql() {
        String dia = dataInicio.substring(0, 2);
        String mes = dataInicio.substring(3, 5); 
        String ano = dataInicio.substring(6);
        return ano+"-"+mes+"-"+dia;
    }
    public String getDataInicioFromSql() {
        String ano = dataInicio.substring(0, 4);
        String mes = dataInicio.substring(5, 7); 
        String dia = dataInicio.substring(8);
        return dia+"/"+mes+"/"+ano;
    }

    public String getDataFimToSql() {
        if(dataFim.equals(""))
            return "NULL";
        
        String dia = dataFim.substring(0, 2);
        String mes = dataFim.substring(3, 5); 
        String ano = dataFim.substring(6);
        return ano+"-"+mes+"-"+dia;
    }
    public String getDataFimFromSql() {
        if(dataFim.equals(""))
            return "NULL";
        String ano = dataFim.substring(0, 4);
        String mes = dataFim.substring(5, 7); 
        String dia = dataFim.substring(8);
        return dia+"/"+mes+"/"+ano;
    }
    
    public String getDataVencimentoToSql() {
        if(dataVencimento.equals(""))
            return "NULL";
        
        String dia = dataVencimento.substring(0, 2);
        String mes = dataVencimento.substring(3, 5); 
        String ano = dataVencimento.substring(6);
        return ano+"-"+mes+"-"+dia;
    }
    
    public String getDataVencimentoFromSql() {
        if(dataVencimento.equals(""))
            return "NULL";
        String ano = dataVencimento.substring(0, 4);
        String mes = dataVencimento.substring(5, 7); 
        String dia = dataVencimento.substring(8);
        return dia+"/"+mes+"/"+ano;
    }
    
    
    
}
