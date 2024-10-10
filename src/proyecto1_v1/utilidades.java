
package proyecto1_v1;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
        
public class utilidades {
    /*
    *   Verifica si el nombre ingresado tiene un .txt para removerlo
    */
    static String verificarNombre(String nombre){
        char [] verificarCH;
        verificarCH = nombre.toCharArray();
        for(int i=0;i<verificarCH.length;i++){
            if(verificarCH[i]=='.'){
                nombre = nombre.replaceAll("\\p{Punct}", "");           //Elimina cualquier signo de puntuacion que se encuentre en la cadena
                nombre = nombre.replaceAll("txt", "");
                return nombre;
            }
        }
        return nombre;
    }
    /*
    *   Pregunta al usuario si quiere repetir el programa y la opcion se almacena en "salir"
    */
    static int Salir(){
        int salir;
        Scanner leer = new Scanner(System.in);
        System.out.print("\nEscriba 1 si desea ordenar otro otro método (otro número en caso contario) >>> ");
            salir = leer.nextInt();
            return salir;
    }
    /*
    *   Crea los nombres de los archivos de las iteraciones del programa
    */
    static String nombreIteraciones(String a1,int i,String name,String ordenamiento){
        String aux = a1;
        a1 = a1.replaceAll(a1, "");
        a1 = a1.concat(ordenamiento+"_I");
        a1 = a1.concat(String.valueOf(i));
        a1 = a1.concat("_"+name);
        a1 = a1.concat("_"+aux+".txt");
        return a1;
    }
    /*
    *   Crea el nombre para el archivo f0 en la iteracion "i" para el Ordenamiento Polifase
    */
    static String nombreIteracionf0(String nombre,int i,String Ordenamiento){
        String aux = nombre;
        nombre = nombre.replaceAll(aux, "");
        nombre = nombre.concat(Ordenamiento+"_I");
        nombre = nombre.concat(String.valueOf(i));
        nombre = nombre.concat("_"+aux+"_f0.txt");
        return nombre;
    }
    /*
    *   Lee el archivo f1 y f2 y los une para posteriormente almacenarlos en f0
    */
    public static void unirBloquesPolifase(File f1,File f2,ArrayList<Persona> f0,int n,int tam,String nombre,int k){
        ArrayList<Persona> f3 = new ArrayList();                //Lista auxiliar para unir los bloques que se lean
        BufferedReader almacenamientof1,almacenamientof2;
        FileReader leerf1, leerf2;
        FileWriter escribir;
        PrintWriter linea;
        String cadena = "";
        String[] alumno;
        int j = 0;                                             //Indice de f3
        
        nombre = utilidades.nombreIteracionf0(nombre, k,"Polifase");               //Nombre del archivo f0 donde se insertaran la fusión de f1 y f2
                                                                                   //k es el número de iteración en ese instante
        try{
                escribir = new FileWriter(nombre,true);
                linea = new PrintWriter(escribir);
                leerf1 = new FileReader(f1);
                almacenamientof1 = new BufferedReader(leerf1);
                leerf2 = new FileReader(f2);
                almacenamientof2 = new BufferedReader(leerf2);
                
                while(f0.size() != tam && cadena != null){      //Comprueba que haya terminado de leer ambos archivos, asi como que la lista f0 
                                                                //este llena completamente
                    /*
                    *   Lee un bloque de f1 y los almacena en f3                                            
                    */
                    for(int i=0;i<n;i++){                       
                        cadena = almacenamientof1.readLine();
                        if(cadena != null){
                            if(cadena.compareTo("********************************") != 0){
                                alumno = cadena.split(","); 
                                f3.add(new Persona());
                                f3.get(j).setNombre(alumno[0]);
                                f3.get(j).setApellido(alumno[1]);
                                f3.get(j).setNumCuenta(Integer.parseInt(alumno[2]));
                                j++;
                            }else
                                i--;
                        }
                    }
                    /*
                    *   Lee un bloque de f2 y lo almacena en f3
                    */
                    for(int i=0;i<n;i++){
                        cadena = almacenamientof2.readLine();
                        if(cadena != null){
                            if(cadena.compareTo("********************************") != 0){
                                alumno = cadena.split(",");
                                f3.add(new Persona());
                                f3.get(j).setNombre(alumno[0]);
                                f3.get(j).setApellido(alumno[1]);
                                f3.get(j).setNumCuenta(Integer.parseInt(alumno[2]));
                                j++;
                            }else
                                i--;
                        }
                    }
                    Ordenamiento.ordenarBloques(f3);                //Ordena la fusión de ambos bloques 
                    /*
                    *   Imprime los datos en el archivo f0 y tambien los ingresa en la lista f0
                    */
                    linea.println("********************************");
                    for(int i = 0;i<f3.size();i++){
                        linea.println(f3.get(i).getDatos());
                        f0.add(f3.get(i));
                    }
                    f3.clear();                                     //Limpia la lista para volver a almacenar los datos
                    j = 0;                                          //Restaura el valor del indice "j" para comenzar a llenar f3 desde el indice 0
                }
                linea.println("********************************");
                linea.close();
                escribir.close();
                almacenamientof1.close();
                leerf1.close();
                almacenamientof2.close();
                leerf2.close();
        }catch (Exception e){                        
        }
        
    }
    public static void unirBloquesME(ArrayList<Persona> f0, File f1, File f2, int size, int k,String nombre) throws IOException{
        ArrayList<Persona> f3 = new ArrayList();
        String nombreF0 = utilidades.nombreIteracionf0(nombre, k, "ME");
        File F0 = new File(nombreF0);
        int j=0,comprobar = 0;
        String cadena="", cadena2="";
        String[] persona;
        FileWriter escribirf0;
        PrintWriter lineaf1;
        BufferedReader almacenamientof1, almacenamientof2;
        FileReader leerf1, leerf2;
        try{
            escribirf0 = new FileWriter(F0,true);
            lineaf1 = new PrintWriter(escribirf0);
            leerf1 = new FileReader(f1);
            leerf2 = new FileReader(f2);
            almacenamientof1 = new BufferedReader(leerf1);
            almacenamientof2 = new BufferedReader(leerf2);
            
            cadena = almacenamientof1.readLine();
            cadena2 = almacenamientof2.readLine();
            System.out.println("Tamaño de f0: "+f0.size());
            System.out.println("Size: "+size);
            while(f0.size()!=size){
                do{
                    cadena = almacenamientof1.readLine(); 
                    if(cadena!=null){
                        if(cadena.compareTo("********************************")!=0){
                            persona = cadena.split(",");
                            f3.add(new Persona());       
                            f3.get(j).setNombre(persona[0]);
                            f3.get(j).setApellido(persona[1]);
                            f3.get(j).setNumCuenta(Integer.parseInt(persona[2]));
                            j++;
                        } 
                    }else{
                        break;
                    }           
                }while(cadena.compareTo("********************************")!=0);
                do{
                    cadena2 = almacenamientof2.readLine();  
                    if(cadena2!=null){
                        if(cadena2.compareTo("********************************")!=0){
                            persona = cadena2.split(",");
                            f3.add(new Persona());       
                            f3.get(j).setNombre(persona[0]);
                            f3.get(j).setApellido(persona[1]);
                            f3.get(j).setNumCuenta(Integer.parseInt(persona[2]));
                            j++;
                        }      
                    }else{
                        break;
                    }
                }while(cadena2.compareTo("********************************")!=0);
                
                lineaf1.println("********************************");
                Ordenamiento.ordenarBloques(f3);
                for(int i=0; i<f3.size();i++){
                    f0.add(f3.get(i));
                    lineaf1.println(f3.get(i).getDatos());
                }
                comprobar = f3.size();
                f3.clear();
                j=0;
            }
            lineaf1.println("********************************");
            lineaf1.close();
            escribirf0.close();
            leerf1.close();
            leerf2.close();
            almacenamientof1.close();
            almacenamientof2.close();
        }catch(IOException e){
         }
        if(comprobar == f0.size()){
            EscribirFichero.impirmirArchivoOrdenado(f0, nombre, "ME"); 
            return;
        }
        f0.clear();
        dividirME(f0, F0, k, nombre, size);
    }
    public static void dividirME(ArrayList<Persona> f0,File F0, int cont,String nombre,int size) throws FileNotFoundException, IOException{
        
        String nombref1 = utilidades.nombreIteraciones("f1", cont, nombre, "ME");
        String nombref2 = utilidades.nombreIteraciones("f2", cont, nombre, "ME");
        File f1 = new File(nombref1);
        File f2 = new File(nombref2);
        FileWriter escribirf1, escribirf2;
        PrintWriter lineaf1, lineaf2;
        escribirf1 = new FileWriter(f1,true);
        escribirf2 = new FileWriter(f2,true);
        lineaf1 = new PrintWriter(escribirf1);
        lineaf2 = new PrintWriter(escribirf2);
        String cadena = "";
        BufferedReader almacenamiento;
        FileReader leer;
        leer = new FileReader(F0);
        almacenamiento = new BufferedReader(leer);
        cadena = almacenamiento.readLine();
        lineaf1.println("********************************");
        while(cadena!=null){
            do{
                cadena = almacenamiento.readLine(); 
                if(cadena!=null){
                    if(cadena.compareTo("********************************")!=0){
                        lineaf1.println(cadena);
                    } 
                }else
                    break;
            }while(cadena.compareTo("********************************")!=0);
            lineaf1.println("********************************");
            lineaf2.println("********************************");
            do{
                cadena = almacenamiento.readLine();  
                if(cadena!=null){
                    if(cadena.compareTo("********************************")!=0){
                        lineaf2.println(cadena);
                    }        
                }else
                    break;
            }while(cadena.compareTo("********************************")!=0);
        }
        lineaf1.close();
        lineaf2.close();
        escribirf1.close();
        escribirf2.close();
        leer.close();
        almacenamiento.close();
        unirBloquesME(f0, f1, f2, size, cont+1, nombre);
    }
    
    public static boolean verificarArchivo(String nombre) {
        File af = new File(nombre+".txt"); // Objeto para comprobar si existe el archivo
        if (af.isFile()) {
            return true;
        } else {
            System.out.println("\nNo se encontro el archivo o no existe. Verifica la entrada de la dirección");
        }
        return false;
    }
}
