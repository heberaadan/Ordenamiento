package proyecto1_v1;

import java.io.*;
import java.util.*;

/*
 * En esta clase lee el archivo y lo guarda en el ArrayList listaPersonas, para posteiormente mandarla
 * a ordenarla con el algoritmo de ordenamiento QuickSort. 
 */
public class OperacionesArchivo {
    
    /*
    * Lee el archivo ingresado por el usuario y los datos que se encuentran en el los almacena en la lista tipo Persona f0
    */
    public static void leerArchivo(ArrayList<Persona> f0,File F0){
        FileReader leer;
        String cadena = "";
        int i = 0;
        String[] persona;
        BufferedReader almacenamiento;
        System.out.println("\nLeyendo el archivo "+F0.getName()+"......");
        try{
            leer = new FileReader(F0);
            almacenamiento = new BufferedReader(leer);
            while(cadena != null){
                cadena = almacenamiento.readLine();
                if(cadena != null && !cadena.equals("\n")){
                    persona = cadena.split(","); 
                    f0.add(new Persona());       
                    f0.get(i).setNombre(persona[0]);
                    f0.get(i).setApellido(persona[1]);
                    f0.get(i).setNumCuenta(Integer.parseInt(persona[2]));
                    i++;
                }
            }
            almacenamiento.close();
            leer.close();
        }catch (Exception e){
        }
   }
    /*
    *   Realiza las diferentes divisiones de los bloques de tamaño "n" elementos y los inserta en los archivos f1 y f2
    */
   public static void LlenarArchivosPolifase(ArrayList<Persona> f0,File F1,File F2,int n){
        ArrayList<Persona> A1 = new ArrayList();                                //Listas auxiliares para ordenar los bloques que se vayan leyendo
        ArrayList<Persona> A2 = new ArrayList();                                //en f1 y f2
        FileWriter escribirF1,escribirF2;
        PrintWriter lineaF1,lineaF2;
        Persona alumno;
        /*
        *   Crea los archivos f1 y f2 
        */
        while(!F1.exists() && !F2.exists()){
            try {
                System.out.println("\nCreando "+F1.getName()+" & "+F2.getName()+" ");
                F1.createNewFile();
                F2.createNewFile();
            } catch (Exception e) {
            }
        }
              try {
                System.out.println("\nIngresando los datos en los archivos "+F1.getName()+" & "+F2.getName()+" ");
                escribirF1 = new FileWriter(F1,true);
                lineaF1 = new PrintWriter(escribirF1);
                escribirF2 = new FileWriter(F2,true);
                lineaF2 = new PrintWriter(escribirF2);
                lineaF1.println("********************************");
                lineaF2.println("********************************");
                while(!f0.isEmpty()){
                    if(!f0.isEmpty()){
                    /*
                    *   Inserta el bloque de "n" elementos en el archivo f1    
                    */
                    for(int j=0;j<n;j++){
                        if(!f0.isEmpty()){
                            alumno = f0.remove(0);                      //Elimina el dato de f0 y lo asigna a la variable auxiliar "alumno"
                            A1.add(alumno);                             //para ingresarlo a la lista A1
                        }
                    }
                    Ordenamiento.ordenarBloques(A1);                    //Ordena el bloque 
                    for(int i = 0;i<A1.size();i++){
                        lineaF1.println(A1.get(i).getDatos());          //Imprime el bloque en el archivo f1
                    }
                    A1.clear();                                         //limpia la lista para no tener datos duplicados ni que se acumulen
                    lineaF1.println("********************************");
                    /*
                    *   Inserte el bloque de "n" elementos en el archivo f2
                    */
                        for(int j=0;j<n;j++){
                           if(!f0.isEmpty()){
                                alumno = f0.remove(0);                  //Elimina el dato de f0 y lo asigna a la variable auxiliar "alumno"
                                A2.add(alumno);                         //para ingresarlo a la lista A2
                           }
                        }
                        Ordenamiento.ordenarBloques(A2);                //Ordena el bloque
                        for(int i = 0;i<A2.size();i++){
                            lineaF2.println(A2.get(i).getDatos());      //Imprime el bloque en el archivo f2
                        }
                    A2.clear();                                         //Limpia la lista A2 para no teneer datos duplicados ni que se acumulen
                    lineaF2.println("********************************");
                    }
                }
                lineaF1.close();
                escribirF1.close();
                lineaF2.close();
                escribirF2.close();
            } catch (Exception e) {
            }
    }
}
