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
public class Todo {
    private int id;
    private String todo;
    private String data;

    public Todo(String todo, String data) {
        this.todo = todo;
        this.data = data;
    }

    public Todo(int id, String todo, String data) {
        this.id = id;
        this.todo = todo;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getTodo() {
        return todo;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ID["+id + "]" + " Data: "+ data +"\n     TODO:" + todo  + ";";
    }

    
    
}
