/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Configuration;

/**
 *
 * @author corentin
 */
public class Parameter{
    private String name;
    public String value;
    
    public Parameter(String content){
        this.name = content.split("=")[0];
        this.value = content.split("=")[1];
        System.out.println("+"+this);
    }
    
    public Parameter(String name,String value){
        this.name = name;
        this.value = value;
    }
    
    public String toString(){
        return this.name+"="+this.value;
    }
    
    public String getName(){
        return this.name;
    }
}