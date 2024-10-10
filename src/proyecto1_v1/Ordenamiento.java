
package proyecto1_v1;

//import java.util.LinkedList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Ordenamiento {
    /*
    *   Esta función es la encargada de realizar el ordenamiento
    *    f0 -> Lista que contiene todos los datos del archivo original
    *    nombre -> nombre del archivo Original
    *    n -> tamaño de los bloques donde se almacenan las claves
    *    A1 -> nombre archivo f1
    *    A2 -> nombre nombre archivo f2
    *    i -> número de iteraciones del programa
    *    tam -> cantidad de claves del archivo original
    */
    static void Polifase(ArrayList<Persona> f0,String nombre,int n,String A1,String A2,int i,int tam){
        /*
         * Aqui se crean los archivos auxiliares para el ordenamiento 
        */
        A1 = utilidades.nombreIteraciones(A1, i,nombre,"Polifase");
        A2 = utilidades.nombreIteraciones(A2, i,nombre,"Polifase");
        File f1 = new File(A1);
        File f2 = new File(A2);
        
        OperacionesArchivo.LlenarArchivosPolifase(f0, f1, f2, n);                //Llena los archivos F1 y F2
        utilidades.unirBloquesPolifase(f1, f2, f0, n, tam, nombre, i);                   //Lee los archivos F1 y F2 y lo almacena en la lista f0 e imprime el archivo f0
        /*
        *   Verifica que al juntar los bloques estos no ingresen mas datos que
        *   los que se encuentran en el archivo original
        */
        if((n*2)>tam){                      
            /*
            *   Imprime el Archivo Ordenamos por el método Polifase
            */
            EscribirFichero.impirmirArchivoOrdenado(f0, nombre,"Polifase");
            return;
        }
        /*
        *   Llamada Recursiva
        */
        Ordenamiento.Polifase(f0, nombre, n*2, "f1", "f2",i+1,tam);
    }
    /*
    * Ordena los datos en base a los Apellidos usando el algoritmo de ordenamiento InsertionSort
    */
    static void ordenarBloques(ArrayList<Persona> fichero){
        Persona index;
        int j;
        for(int i = 0;i<fichero.size();i++){
            index = fichero.get(i);
            j = i;
            while(j>0 && index.getApellido().compareToIgnoreCase(fichero.get(j-1).getApellido()) < 0){
                fichero.set(j, fichero.get(j-1));
                j -=1;
            }
            fichero.set(j, index);
        }
    }
    public static void MezclaEquilibrada(ArrayList<Persona> f0,int size,String nombre) throws IOException{
        int k=1;
        File F1 = new File("ME_I0_"+nombre+"_f1.txt");
        File F2 = new File("ME_I0_"+nombre+"_f2.txt");
        particionInicial(f0,F1,F2);
        utilidades.unirBloquesME(f0, F1, F2, size, k, nombre);
    }
    
    public static void particionInicial(ArrayList<Persona> ListaOriginal, File F1, File F2) throws IOException{
        ArrayList<Persona>  Lista1= new ArrayList<>() ;
        ArrayList<Persona>  Lista2= new ArrayList<>() ;    
        int i=0;
        FileWriter escribirf1,escribirF2, escribirF1;
        PrintWriter lineaf1,lineaF2, lineaF1;
        escribirF1 = new FileWriter(F1,true);
        lineaF1 = new PrintWriter(escribirF1);
        escribirf1 = new FileWriter(F1,true);
        lineaf1 = new PrintWriter(escribirf1);
        escribirF2 = new FileWriter(F2,true);
        lineaF2 = new PrintWriter(escribirF2);
        
        lineaF1.println("********************************");
        lineaF2.println("********************************");
        while(!ListaOriginal.isEmpty()){
            do{
                if(!ListaOriginal.isEmpty()){
                    Lista1.add(ListaOriginal.get(i));
                    lineaF1.println(ListaOriginal.remove(i).getDatos());
                    if(ListaOriginal.isEmpty()){
                        break;
                    }
                }else{
                    break;
                }
            }while(Lista1.get(Lista1.size()-1).getApellido().compareToIgnoreCase(ListaOriginal.get(i).getApellido())<0 || Lista1.get(Lista1.size()-1).getApellido().compareTo(ListaOriginal.get(i).getApellido())==0);
            lineaF1.println("********************************");
            do{
                if(!ListaOriginal.isEmpty()){
                    Lista2.add(ListaOriginal.get(i));
                    lineaF2.println(ListaOriginal.remove(i).getDatos());
                    if(ListaOriginal.isEmpty()){
                        break;
                    }
                }else{
                    break;
                }
            }while(Lista2.get(Lista2.size()-1).getApellido().compareToIgnoreCase(ListaOriginal.get(i).getApellido())<0 || Lista2.get(Lista2.size()-1).getApellido().compareTo(ListaOriginal.get(i).getApellido())==0);
            lineaF2.println("********************************");
        }
        lineaF1.close();
        escribirF1.close();
        lineaF2.close();
        escribirF2.close();
        lineaf1.close();
        escribirf1.close();
    }
}
