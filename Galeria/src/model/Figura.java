/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.lang.*;

/**
 *
 * @author nacho
 */
public class Figura implements Serializable{
    
   private String id;
   private float altura;
   private String material;
   private int cantidad;
   private int ano;
   private String foto;
   private String fabricante;
   
   
   
   public Figura(String id, float altura, String material, int cantidad, int ano, String foto, String fabricante){
       this.id = id;
       this.altura = altura;
       this.material = material;
       this.cantidad = cantidad;
       this.ano = ano;
       this.foto = foto;
       this.fabricante = fabricante;
   }
   
   static Figura factory(String[] propiedades) throws Exception{
       
    String id;
    float altura = 0;
    String material;
    int cantidad = 0;
    int ano = 0;
    String foto;
    String fabricante;
       for(String a : propiedades){
           if(a.isBlank()){
               return null;
           }
       }
       for(int i=0; i<propiedades.length;i++){
           if(propiedades[i].isEmpty()){
               return null;
           }
       }
        id = propiedades[0];
        material = propiedades[2];
        foto = propiedades[5];
        fabricante = propiedades[6];
       try{
         altura = Float.parseFloat(propiedades[1]);
         cantidad = Integer.parseInt(propiedades[3]);
         ano = Integer.parseInt(propiedades[4]);
       }catch(Exception ex){
           System.out.println("Exceptio"+ex);
       }
       
       
       return new Figura(id,altura,material,cantidad,ano,foto,fabricante);
       
   }
   
   static String encabezadoTabla(){
       String tabla = String.format("<th>%s</th>"
               + "<th>%s</th>"
               + "<th>%s</th>"
               + "<th>%s</th>"
               + "<th>%s</th>"
               + "<th>%s</th>"
               + "<th>%s</th>",
               "Id",
               "Altura",
               "Material",
               "Cantidad",
               "Año",
               "Foto",
               "Fabricante");
       return tabla;
   }
   
   String[] comoFila() {
        String[] fila = {this.id,
            this.altura+"",
            this.material+
            this.cantidad+"",
            this.ano+"",
            this.foto,
            this.fabricante};
        return fila;
    }
   
   String comoFilaHTML(){
       String fila = String.format("<TR>"
               + "<TD>%s</TD>"
               + "<TD>%s</TD>"
               + "<TD>%s</TD>"
               + "<TD>%s</TD>"
               + "<TD>%s</TD>"
               + "<TD>%s</TD>"
               + "<TD>%s</TD>",
               this.id,
               this.altura,
               this.material,
               this.cantidad,
               this.ano,
               this.foto,
               this.fabricante);
       return fila;
   }
   
   //Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    
    
   
   
    
}

