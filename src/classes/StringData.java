/*
Classe utilitaria para trabalhar com Strings e Datas
 */
package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mauricio
 */
public class StringData {
    
    public static final String dateToSql(String dataBrasil){
        String dia = dataBrasil.substring(0, 2);
        String mes = dataBrasil.substring(3, 5); 
        String ano = dataBrasil.substring(6);
        return ano+"-"+mes+"-"+dia;
    }
    
    public static final String dateFromSQL(String dataAmerica) {
        String ano = dataAmerica.substring(0, 4);
        String mes = dataAmerica.substring(5, 7); 
        String dia = dataAmerica.substring(8);
        
        return dia+"/"+mes+"/"+ano;
    }
    
    public static final String getLastDayOfMonthString(byte month){
        switch(month){
            case 2:
                return "28";
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return "31";
            case 4:
            case 6:
            case 9: 
            case 11:
                return "30";
            default: return "0";
        }
    }
}
