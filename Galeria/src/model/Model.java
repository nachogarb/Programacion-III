/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import static com.coti.tools.OpMat.exportToDisk;
import static com.coti.tools.OpMat.importFromDisk;
import com.coti.tools.Rutas;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import static java.lang.System.err;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nacho
 */
public class Model {
    
   HashMap<String, Figura> map = new HashMap<>();
   String[] ids = null;
    
    Coleccion coleccion = new Coleccion();
    public List <Figura> lista = null; 
    
    public Model(){
        this.lista = new ArrayList<>();
}
    
    
    //FICHEROS
    public void saveBin() {
        Path p = Rutas.pathToFileInFolderOnDesktop(coleccion.FOLDERONDESKTOP, coleccion.FIGURASBINARYFILE);
        try{
            FileOutputStream fos = new FileOutputStream(p.toFile());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(lista);
            oos.close();
        }catch(IOException ex){
            err.println("No fue posible guardar el archivo: figuras.bin");
            err.println(ex.toString());
        }           
    }
    
    public void loadBin(){
        Path p = Rutas.pathToFileInFolderOnDesktop(coleccion.FOLDERONDESKTOP, coleccion.FIGURASBINARYFILE);
        try{
            FileInputStream fis = new FileInputStream(p.toFile());
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            lista = (List<Figura>) ois.readObject();
            ois.close();
        }catch(IOException | ClassNotFoundException ex){
            err.println("No fue posible leer el archivo: figuras.bin");
            err.println("Existe la posibilidad de importar un archivo CSV en el menu Archivos");
        }     
    }
    

    public int importarCSV() throws Exception {
        Path p = Rutas.pathToFileInFolderOnDesktop(coleccion.FOLDERONDESKTOP,coleccion.FIGURASCSV);
        String [][] tmp = importFromDisk(p.toFile(),"\t");
        int numFiguras = tmp.length;
        Figura f;
        String id;
        lista = new ArrayList<>();
        for(int n=0; n<numFiguras; n++){
            id = tmp[n][0];
            f = Figura.factory(tmp[n]);
            if(null != f){
                lista.add(f);   
                map.put(id, f);
            }
        }
        return 0;
    }
    public void exportarCSV() throws Exception{
        
        Path p = Rutas.pathToFileInFolderOnDesktop(coleccion.FOLDERONDESKTOP, coleccion.FIGURASCSV);
        if(lista == null){
            err.println("ERRROR: No se pudo exportar por que la lista está vacía");
        }
        
        exportToDisk(this.tablaFigura(),p.toFile(),"\t");
    }
    public String exportarHTML() throws FileNotFoundException{
        if(lista.isEmpty()){
            return "ERROR: No hay figuras";
        }
        File file = Rutas.fileToFileInFolderOnDesktop(coleccion.FOLDERONDESKTOP, coleccion.FIGURASHTML);
        try (PrintWriter pw = new PrintWriter(file)){
            pw.printf("<!DOCTYPE html>%n"
                    + "<HTML>%n"
                    + "<HEAD>%n"
                    + "<meta charset=\"UTF-8\">%n"
                    + "<H1 style=\"text-align:center;\">COLECCION DE FIGURAS%n</H1>"
                    + "</Head>%n"
                    + "<BODY>%n");
             pw.printf("<TABLE>%n"
                    + "<TABLE BORDER= 1px solid black>%n");
             pw.printf("%s%n", Figura.encabezadoTabla());
             for(Figura f : lista){
                 pw.printf("%s%n", f.comoFilaHTML());
             }
            pw.printf("</TABLE>%n");
            pw.printf("</BODY>%n");
            pw.printf("</HTML>");
        }catch (FileNotFoundException ex) {
            return "Error: no se creó el archivo " + coleccion.FIGURASHTML;
        }
        return "Se ha creado figuras.html correctamente";
    }
    
     public String[][] tablaFigura(){

        int numfigu=this.lista.size();
        String[][] tabla_fig=new String[numfigu][];
        for(int n=0;n<numfigu;n++) {
            tabla_fig[n]=lista.get(n).comoFila();
        }
        return tabla_fig;
     }
     
     //LISTADOS
     public void ordenarFigurasPorIdentificador() {
        Collections.sort(lista, Comparator.comparing(Figura::getId));
    }
      public void ordenarFigurasPorAnoEIdentificador() {
        Collections.sort(lista, Comparator.comparing(Figura::getAno).reversed()
                .thenComparing(Figura::getId));
    }
      public void ordenarFigurasPorFabricanteYAno() {
        Collections.sort(lista, Comparator.comparing(Figura::getFabricante)
                .thenComparing(Figura::getAno));
    }
    //AÑADIR FIGURA  
    public Figura addFigura(String id, Float altura, String material, int cantidad, int ano, String foto, String fabricante) {
        Figura f = new Figura(id,altura,material,cantidad,ano,foto,fabricante); 
        lista.add(f);
        int n = lista.size();
        map.put(id, f);
        return f;
    }
    
    //BORRAR FIGURA
    
    public Figura removeFigura(String id){
        Figura aborrar = null;
        for(Figura f : lista){
            if(f.getId().equalsIgnoreCase(id)){
                aborrar = f;
                break;
            }

        }
      
            lista.remove(aborrar);
            
        return aborrar;
    }
            
        
    

    
    public void consultarFigura(String id) {
       for(Figura aux : lista){
           if(aux.getId().equalsIgnoreCase(id)){
               System.out.printf(" ID: "); System.out.println(aux.getId());
               System.out.printf(" Altura: "); System.out.println(aux.getAltura());
               System.out.printf(" Año: "); System.out.println(aux.getAno());
               System.out.printf(" Cantidad: "); System.out.println(aux.getCantidad());
               System.out.printf(" Material: ");System.out.println(aux.getMaterial());
               System.out.printf(" Foto: ");System.out.println(aux.getFoto());
               System.out.printf(" Fabricante: ");System.out.println(aux.getFabricante());
           }
       }
       
    }

    public void mostrarFigura(String id) {
        for(Figura aux : lista){
           if(aux.getId().equalsIgnoreCase(id)){
               System.out.printf(" ID: "); System.out.println(aux.getId());
               System.out.printf(" Altura: "); System.out.println(aux.getAltura());
               System.out.printf(" Año: "); System.out.println(aux.getAno());
               System.out.printf(" Cantidad: "); System.out.println(aux.getCantidad());
               System.out.printf(" Material: ");System.out.println(aux.getMaterial());
               System.out.printf(" Foto: ");System.out.println(aux.getFoto());
               System.out.printf(" Fabricante: ");System.out.println(aux.getFabricante());
           }
       }
        
    }
    //MODIFICAR FIGURA
    public String modifyAltura(String id, float altura){
        String result = null;
        for(Figura f : lista){
            if(f.getId().equalsIgnoreCase(id)){
                f.setAltura(altura);
                result = "Se modificó correctamente";
                break;
            }
        }
        return result;
    }
    
    public String modifyAno (String id, int ano){
        String result = null;
        for(Figura f : lista){
            if(f.getId().equalsIgnoreCase(id)){
                f.setAno(ano);
                result = "Se modificó correctamente";
                break;
            }
        }
        return result;
    }
    
    public String modifyCantidad (String id, int cantidad){
        String result = null;
        for(Figura f : lista){
            if(f.getId().equalsIgnoreCase(id)){
                f.setCantidad(cantidad);
                result = "Se modificó correctamente";
                break;
            }
        }
        return result;
    }

    public String modifyMaterial(String id, String material) {
        String result = null;
        for(Figura f : lista){
            if(f.getId().equalsIgnoreCase(id)){
                f.setMaterial(material);
                result = "Se modificó correctamente";
                break;
            }
        }
        return result;
    }
     public String modifyFoto(String id, String foto) {
        String result = null;
        for(Figura f : lista){
            if(f.getId().equalsIgnoreCase(id)){
                f.setFoto(foto);
                result = "Se modificó correctamente";
                break;
            }
        }
        return result;
    }
     public String modifyFabricante(String id, String fabricante) {
        String result = null;
        for(Figura f : lista){
            if(f.getId().equalsIgnoreCase(id)){
                f.setFabricante(fabricante);
                result = "Se modificó correctamente";
                break;
            }
        }
        return result;
    }

    public String[] obtenerLista() {
        String aux = null;
        String [] listaId = new String[lista.size()];
        int n = 0;
        for(Figura f : lista){
            aux = f.getId();
            listaId[n] = aux;
            n++;
        }
        return listaId;
    }

    
    
}
       
    
