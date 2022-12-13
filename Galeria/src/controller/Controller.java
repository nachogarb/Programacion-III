/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Figura;
import model.Model;
/**
 *
 * @author nacho
 */
public class Controller {
    Model m = new Model();
   
    public void saveBin(){
        m.saveBin();
    }
    public void loadBin() {
        m.loadBin();
    }
    public void importarCSV() throws Exception{
        m.importarCSV();
    }

    public void exportarCSV() {
        try {
            m.exportarCSV();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String exportarHTML() throws FileNotFoundException{
        return m.exportarHTML();
    }

    public String [][] tablaFigura() {
        return m.tablaFigura();
    }
    
    //LISTADOS
    public void ordenarFigurasPorIdentificador() {
         m.ordenarFigurasPorIdentificador();
    }
    public void ordenarFigurasPorAnoEIdentificador() {
        m.ordenarFigurasPorAnoEIdentificador();
    }
     public void ordenarFigurasPorFabricanteYAno() {
         m.ordenarFigurasPorFabricanteYAno();
    }
    //GESTION 
    public Figura addFigura(String id, Float altura, String material, int cantidad, int ano, String foto, String fabricante) {
        return m.addFigura(id,altura,material,cantidad,ano,foto,fabricante);
    }
    
    public Figura removeFigura(String id){
        return m.removeFigura(id);
    }
    
    
    

    public void consultarFigura(String id) {
        m.consultarFigura(id);
    }

    public void mostrarFigura(String id) {
        m.mostrarFigura(id);
    }

    public String modifyAltura(String id, float altura) {
        return m.modifyAltura(id,altura);
    }
    public String modifyAno(String id, int ano) {
        return m.modifyAno(id,ano);
    }
    public String modifyCantidad(String id, int cantidad) {
        return m.modifyCantidad(id,cantidad);
    }
    public String modifyMaterial(String id, String material) {
        return m.modifyMaterial(id,material);
    }
     public String modifyFoto(String id, String foto) {
        return m.modifyFoto(id,foto);
    }
      public String modifyFabricante(String id, String fabricante) {
        return m.modifyFabricante(id,fabricante);
    }

    public String[] obtenerLista() {
        return m.obtenerLista();
    }

    
}
