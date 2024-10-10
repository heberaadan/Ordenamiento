
package proyecto1_v1;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Proyecto1_V1 {

    public static void main(String[] args) throws IOException {
        String nombreA,nombre,a1 = "f1",a2 = "f2";
        File F0;
        ArrayList<Persona> f0 = new ArrayList();
        Scanner leer = new Scanner(System.in);
        int op,salir = 1,repetir = 0;
        
        do{
            System.out.print("\nIngrese el nombre del archivo a ordenar: ");
            nombre = leer.nextLine();
            nombre = utilidades.verificarNombre(nombre);
            if(utilidades.verificarArchivo(nombre)){
                do{
                    repetir = 0;
                    salir = 0;
                    F0 = new File(nombre+".txt");
                    do{
                        System.out.print("\n || MENU ALGORITMOS DE ORDENAMIENTO EXTERNOS ||\n\n"
                        + " (1)Polifase\n"
                        + " (2)Mezcla Equilibrada\n"
                        + " (3)Radix\n"
                        + " (4)Cambiar nombre\n"
                        + " (5)Salir\n\n Opción >>> ");
                        op = leer.nextInt();
                    }while(op < 1 || op > 5);
                        
                        switch(op){
                            case 1:
                                System.out.println("\nArchivo a ordenar: "+nombre+".txt");
                                OperacionesArchivo.leerArchivo(f0, F0);                 //lee el archivo y lo guarda en la lista f0
                                Ordenamiento.Polifase(f0,nombre,5,a1,a2,1,f0.size());   //Comienza el ordenamiento Polifase
                                System.out.println("\nEl archivo "+nombre+".txt fue ordenado con exito!!!!");
                                salir = utilidades.Salir();                             
                                break;
                            case 2:
                                System.out.println("Archivo a ordenar: "+nombre);
                                OperacionesArchivo.leerArchivo(f0, F0);
                                Ordenamiento.MezclaEquilibrada(f0, f0.size(), nombre);
                                break;
                            case 3:
                                System.out.println("Archivo a ordenar: "+nombre);
                                OperacionesArchivo.leerArchivo(f0, F0);
                                RadixSort.sort(f0,nombre); // Inicia el ordenamiento de radixSort
                                salir = utilidades.Salir();
                                break;
                            case 4:
                                System.out.print("Ingrese el nuevo nombre del archivo a ordenar: ");
                                nombre = leer.nextLine();
                                nombreA = utilidades.verificarNombre(nombre);
                                repetir = 1;
                                break;
                            case 5:
                                repetir = 0;
                                salir = 0;
                                break;
                        }
                    f0.clear();
                }while(repetir == 1 || salir == 1);
                System.out.print("\nIngrese 1 si quiere ordenar otro archivo (otro número en caso contrario) : ");
                salir = leer.nextInt();
                leer.nextLine();
            }
        }while(salir == 1);
        System.out.println("\nGracias por usar el Programa del Equipo 7 !!! :)\n");
        
    }
}
