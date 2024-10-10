package proyecto1_v1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EscribirFichero {
     /*
    *   Imprime la lista f0 ya ordenada en el archivo f0_Ordenado
    */
    public static void impirmirArchivoOrdenado(ArrayList<Persona> lista, String nombre,String ordenamiento){
        FileWriter escribir;
        PrintWriter linea;
        nombre = nombre.concat("_"+ordenamiento);
        nombre = nombre.concat("_Ordenado.txt");
        File ordenado  = new File(nombre);

        while(!ordenado.exists()){
           try {
                ordenado.createNewFile();
            } catch (Exception e) {
            }
        }
        try {
                escribir = new FileWriter(ordenado,true);
                linea = new PrintWriter(escribir);
                for(int i = 0;i<lista.size();i++)
                    linea.println(lista.get(i).getDatos());
                linea.close();
                escribir.close();
        } catch (Exception e) {
        }
    }
}
