/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import static com.coti.tools.DiaUtil.clear;
import com.coti.tools.Esdia;
import static com.coti.tools.Esdia.readFloat;
import static com.coti.tools.Esdia.readInt;
import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.readString_ne;
import static com.coti.tools.Esdia.siOno;
import static com.coti.tools.Esdia.underline2;
import static com.coti.tools.OpMat.printToScreen3;
import controller.Controller;
import java.io.FileNotFoundException;
import static java.lang.System.err;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Figura;
/**
 *
 * @author nacho
 */
public class View {
    Controller c = new Controller();
    
    public void introduction(){
        underline2("GALERIA DE ARTE");
        System.out.println("Ignacio Garcia Benito 70963905G");
    
    }

    public void cargarFicheroBin() {
        System.out.println("Cargando fichero .bin ...");    
    }
    public void showMenu() throws Exception{
        clear();
        boolean exit = false;
        String menu = "Introduce una opcion: %n"
                + "1.Archivos%n"
                + "2.Gestion de la galeria%n"
                + "3.Listados%n"
                + "4.Salir%n";
        
        do{
            String option = readString(menu).toLowerCase();
            switch(option){
                case "1"-> this.subMenuArchivos();
                case "2"-> this.subMenuGestion();
                case "3"-> this.subMenuListados();
                case "4"-> {
                    this.saveBin();
                    exit = siOno("Desea salir del programa?");
                }
            }
        }while(!exit);
           
    }
    
    public void subMenuArchivos() throws Exception{
        clear();
        boolean exit = false;
        String menu = "Introduce una opcion: %n"
                + "a.Importar CSV%n"
                + "b.Exportar CSV%n"
                + "c.Exportar HTML%n"
                + "q.Salir";
         do{
            String option = readString(menu).toLowerCase();
            switch(option){
                case "a"-> this.importarCSV();
                case "b"-> this.exportarCSV();
                case "c"-> this.exportarHTML();
                case "q"-> exit = siOno("Desea salir del submenu?");
            }
        }while(!exit);
    }
    
    public void subMenuGestion(){
        clear();
        boolean exit = false;
        String menu = "Introduce una opcion: %n"
                + "a.Añadir una figura a la galeria%n"
                + "b.Consultar datos de una figura%n"
                + "c.Modificar datos de una figura%n"
                + "d.Eliminar datos de una figura%n"
                + "q.Salir";
         do{
            String option = readString(menu).toLowerCase();
            switch(option){
                case "a"-> this.addFigura();
                case "b"-> this.consultarFigura();
                case "c"-> this.modifyFigura();
                case "d"-> this.removeFigura();
                case "q"-> exit = siOno("Desea salir del submenu?");
            }
        }while(!exit);
    }
    public void subMenuListados(){
        clear();
        boolean exit = false;
        String menu = "Introduce una opcion: %n"
                + "a.Listado por identificador%n"
                + "b.Listado por año e identificador%n"
                + "c.Listado por fabricante y año%n"
                + "q.Salir";
         do{
            String option = readString(menu).toLowerCase();
            switch(option){
                case "a"-> this.ordenarFigurasPorIdentificador();
                case "b"-> this.ordenarFigurasPorAnoEIdentificador();
                case "c"-> this.ordenarFigurasPorFabricanteYAno();
                case "q"-> exit = siOno("Desea salir del submenu?");
            }
        }while(!exit);
    }
    
    public void saveBin(){
        c.saveBin();
    }
    public void loadBin(){
        c.loadBin();
    }

    public void importarCSV() throws Exception {
        clear();
        System.out.println("Importando fichero figuras.csv ...");
        c.importarCSV();
    }

    private void exportarCSV() {
        clear();
        System.out.println("Exportando fichero figuras.csv ...");
        c.exportarCSV();
    }

    private void exportarHTML() throws FileNotFoundException {
        System.out.printf("%s%n",c.exportarHTML());
    }
    
     //SACAR TABLA POR PANTALLA
    private void tablaFiguras(){
        var ldt = c.tablaFigura();
        try {
            printToScreen3(ldt);
        } catch (Exception ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //GESTION
    private int compararId(String id){
        String[] lista = c.obtenerLista();
        int aux = 0;
        for(int n=0;n<lista.length;n++){
            if(id.equalsIgnoreCase(lista[n])){
                aux = 1;
                return aux;
            }
        }
        return aux;
    }
    private void addFigura() {
        int comprobar;
        String id = readString_ne("Introduzca el identificador de la figura:");
        comprobar = this.compararId(id);
        while(comprobar == 1){
            err.println("ERROR: Ese ID ya esta registrado.");
            id = readString_ne("Introduzca el identificador de la figura:");
            comprobar = this.compararId(id);
        }
        Float altura = readFloat("Introduzca la altura de la figura:",0,2);
        String material = readString_ne("Introduzca el material del que esta hecho la figura:");
        int cantidad = readInt("Introduzca la cantidad de figuras: ",1,99999999);
        int ano = readInt("Introduzca el año de la compra de la figura: ",0,2100);
        String foto = readString_ne("Introduzca el nombre de la foto de la figura: ");
        while (foto.length() < 4){
            err.println("ERROR: El nombre del archivo tiene que tener como minimo 4 caracteres.");
            foto = readString_ne("Introduzca el nombre de la foto de la figura: ");
        }
        int l = foto.length();
        String fin = foto.substring(l-4);
        String png = ".png";
        while(!fin.equals(png)){
            err.println("ERROR: El nombre del archivo foto debe terminar en .png");
            foto = readString_ne("Introduzca el nombre de la foto de la figura: ");
            l = foto.length();
            fin = foto.substring(l-4);
        }
        
        String fabricante = readString("Introduzca el nombre del fabricante:");
         
        
        Figura f = c.addFigura(id,altura,material,cantidad,ano,foto,fabricante);
        if(f != null){
            System.out.println("Se añadió el registro de forma existosa");
        }else{
            System.err.println("Hubo un problema añadiendo el registro");
        }
    }
   
    private void consultarFigura(){
        String id = readString("Introduce el ID de la figura que quieres consultar: ");
        int comprobar;
        comprobar = this.compararId(id);
        while(comprobar == 0){
            err.println("ERROR: Ese ID no existe");
            id = readString_ne("Introduzca el identificador de la figura que quieres modificar:");
            comprobar = this.compararId(id);
        }
        c.consultarFigura(id);
        
    }
    private void mostrarFigura(String id){
        c.mostrarFigura(id);
    }
    private void modifyFigura() {
        
        String id = readString_ne("Introduzca el ID de la figura que quieres modificar: ");
        int comprobar;
        comprobar = this.compararId(id);
        while(comprobar == 0){
            err.println("ERROR: Ese ID no existe");
            id = readString_ne("Introduzca el identificador de la figura que quieres modificar:");
            comprobar = this.compararId(id);
        }
        this.mostrarFigura(id);
        boolean condition = true;
        String menu = "Introduzca el parametro que quiere modificar :%n "
                + "1) Altura%n"
                + "2) Año de compra%n"
                + "3) Cantidad%n"
                + "4) Material%n"
                + "5) Foto%n"
                + "6) Fabricante%n";
         while(condition){
            String option = readString_ne(menu).toLowerCase();
            switch(option){
                case "1"->{
                    float altura = readFloat("Introduzca la altura de la figura:  ",0,2);
                    String result = c.modifyAltura(id,altura);
                    if(result == null){
                        System.out.println("ERROR: No se pudo modificar");
                    }else{
                        System.out.printf("%s%n",result);
                    }
                    condition = siOno("¿Desea modificar otro parametro?");
                    break;
                }
                
                case "2"-> {
                    int ano = readInt("Introduzca el año de la compra de la figura:  ",0,2100);
                    String result = c.modifyAno(id,ano);
                    if(result == null){
                        System.out.println("ERROR: No se pudo modificar");
                    }else{
                        System.out.printf("%s%n",result);
                    }
                    condition = siOno("¿Desea modificar otro parametro?");
                    break;
                }
                case "3"->{
                    int cantidad = readInt("Introduzca la cantidad de la figura:  ",1,99999);
                    String result = c.modifyCantidad(id,cantidad);
                    if(result == null){
                        System.out.println("ERROR: No se pudo modificar");
                    }else{
                        System.out.printf("%s%n",result);
                    }
                    condition = siOno("¿Desea modificar otro parametro?");
                    break;
                }
                case "4"->{
                    String material = readString_ne("Introduzca el material de la figura:  ");
                    String result = c.modifyMaterial(id,material);
                    if(result == null){
                        System.out.println("ERROR: No se pudo modificar");
                    }else{
                        System.out.printf("%s%n",result);
                    }
                    condition = siOno("¿Desea modificar otro parametro?");
                    break;
                }
                case "5"->{
                    String foto = readString_ne("Introduzca el archivo foto de la figura:  ");
                    while (foto.length() < 4){
                        err.println("ERROR: El nombre del archivo tiene que tener como minimo 4 caracteres.");
                        foto = readString_ne("Introduzca el nombre de la foto de la figura: ");
                    }
                    int l = foto.length();
                    String fin = foto.substring(l-4);
                    String png = ".png";
                    while(!fin.equals(png)){
                        err.println("ERROR: El nombre del archivo foto debe terminar en .png");
                        foto = readString_ne("Introduzca el nombre de la foto de la figura: ");
                        l = foto.length();
                        fin = foto.substring(l-4);
                    }
                    String result = c.modifyFoto(id,foto);
                    if(result == null){
                        System.out.println("ERROR: No se pudo modificar");
                    }else{
                        System.out.printf("%s%n",result);
                    }
                    condition = siOno("¿Desea modificar otro parametro?");
                    break;
                }
                case "6"->{
                    String fabricante = readString_ne("Introduzca el fabricante de la figura:  ");
                    String result = c.modifyFabricante(id,fabricante);
                    if(result == null){
                        System.out.println("ERROR: No se pudo modificar");
                    }else{
                        System.out.printf("%s%n",result);
                    }
                    condition = siOno("¿Desea modificar otro parametro?");
                    break;
                }
               
               
            }
        }
    }
    
  

    private void removeFigura() {
        boolean condition = false;
        String id = null;
        
        id = readString_ne("Introduzca el ID de la figura que quiere borrar: ");
        condition = siOno("Esta seguro de que quiere eliminar la figura con id : " + id);
        
        
        var tmp = c.removeFigura(id);
         if (null == tmp) {
            System.out.println("\nEse ID no existe\n");
        } else {
            System.out.println("\nEl elemento eliminado es : \n");
            System.out.println(tmp);
        }
                
    }

    //LISTADOS
    private void ordenarFigurasPorIdentificador() {
        c.ordenarFigurasPorIdentificador();
        this.tablaFiguras();
    }

    private void ordenarFigurasPorAnoEIdentificador() {
        c.ordenarFigurasPorAnoEIdentificador();
        this.tablaFiguras();
    }

    private void ordenarFigurasPorFabricanteYAno() {
         c.ordenarFigurasPorFabricanteYAno();
         this.tablaFiguras();
     }
}
