/*
 Classe para representar o periodo do venciomento - data e nro meses
 */
package classes;

/**
 *
 * @author Mauricio
 */
public class Periodo {
    String data;
    byte periodo;

    public Periodo(String data, byte periodo) {
        this.data = data;
        this.periodo = periodo;
    }

    public String getData() {
        return data;
    }

    public byte getPeriodo() {
        return periodo;
    }
    
    
}
