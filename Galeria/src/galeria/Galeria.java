/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package galeria;
import static com.coti.tools.OpMat.importFromDisk;
import com.coti.tools.Rutas;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import model.Figura;
import view.View;
/**
 *
 * @author nacho
 */
public class Galeria {

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) throws Exception {
        View v = new View();       
        v.introduction();
        v.loadBin();
        v.showMenu();
        
    }
       
}
