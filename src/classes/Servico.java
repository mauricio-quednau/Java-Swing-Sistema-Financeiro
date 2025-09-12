package classes;


/**
 *
 * @author Mauricio
 */
public class Servico {
    public static final byte TIPO_PAGTO_MENSALIDADE = 0;
    public static final byte TIPO_PAGTO_UNICO = 1;
    
    public static final int FAT_QUINZENAL = 15;
    public static final int FAT_MENSAL = 30;
    public static final int FAT_TRIMESTRAL = 90;
    public static final int FAT_SEMESTRAL = 180;
    public static final int FAT_ANUAL = 365;
    
    private int id;
    private String nome;
    private String descricao;
    private String dataInclusao;
    

    public Servico() {
    }

    public Servico(String nome, String descricao, String dataInclusao) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInclusao = dataInclusao;
    }

  
    
    public Servico(int id, String nome, String descricao, String dataInclusao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInclusao = dataInclusao;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(String dataInicial) {
        this.dataInclusao = dataInicial;
    }
    
    public String getDataToSQL() {
        String dia = dataInclusao.substring(0, 2);
        String mes = dataInclusao.substring(3, 5); 
        String ano = dataInclusao.substring(6);
        return ano+"-"+mes+"-"+dia;
    }
    
    public String getDataFromSQL() {
        String ano = dataInclusao.substring(0, 4);
        String mes = dataInclusao.substring(5, 7); 
        String dia = dataInclusao.substring(8);
        
        return dia+"/"+mes+"/"+ano;
    }
    
}
