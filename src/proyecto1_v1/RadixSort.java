package proyecto1_v1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class RadixSort {
    public static void sort(ArrayList<Persona> lista, String directorio) {
        int max = obtenerMaximo(lista, lista.size()); // Busca el máximo de la lista
        var i = 1; // Esta variable nos ayudara a crear los ficheros .txt

        for (int exp = 1; (max / exp) > 0; exp *= 10) {
            ordenar(lista, exp, i, directorio);
            i++;
        }
        EscribirFichero.impirmirArchivoOrdenado(lista, directorio,"RadixSort");
    }

    static void ordenar(ArrayList<Persona> lista, int exp, int x, String directorio) {
        FileWriter fichero;
        PrintWriter pw;
        String colas = utilidades.nombreIteraciones("", x, directorio, "RadixSort");       //Nombre del archivo de iteracion "x+1"
        File iteraciones = new File(colas);
        // Declarando colas.
        Queue<Persona> cola0 = new LinkedList<>();
        Queue<Persona> cola1 = new LinkedList<>();
        Queue<Persona> cola2 = new LinkedList<>();
        Queue<Persona> cola3 = new LinkedList<>();
        Queue<Persona> cola4 = new LinkedList<>();
        Queue<Persona> cola5 = new LinkedList<>();
        Queue<Persona> cola6 = new LinkedList<>();
        Queue<Persona> cola7 = new LinkedList<>();
        Queue<Persona> cola8 = new LinkedList<>();
        Queue<Persona> cola9 = new LinkedList<>();

        for (var i = 0; i < lista.size(); i++) {
            /*
             * En este ciclo, categorizamos a los números en sus respectivas colas.
             */
            var indice = (lista.get(i).getNumCuenta() / exp) % 10;

            switch (indice) {
                case 0:
                    cola0.add(lista.get(i));
                    break;
                case 1:
                    cola1.add(lista.get(i));
                    break;
                case 2:
                    cola2.add(lista.get(i));
                    break;
                case 3:
                    cola3.add(lista.get(i));
                    break;
                case 4:
                    cola4.add(lista.get(i));
                    break;
                case 5:
                    cola5.add(lista.get(i));
                    break;
                case 6:
                    cola6.add(lista.get(i));
                    break;
                case 7:
                    cola7.add(lista.get(i));
                    break;
                case 8:
                    cola8.add(lista.get(i));
                    break;
                case 9:
                    cola9.add(lista.get(i));
                    break;
            }
        }
        lista.clear();
        try {
            fichero=new FileWriter(iteraciones);
            pw = new PrintWriter(fichero);
            if(!cola0.isEmpty())
                pw.println("*********  Cola 0  *********");
            while(cola0.peek()!=null){
                lista.add(cola0.peek());
                pw.println(cola0.poll().getDatos());
            }
            if(!cola1.isEmpty())
                pw.println("*********  Cola 1  *********");
            while(cola1.peek()!=null){
                lista.add(cola1.peek());
                pw.println(cola1.poll().getDatos());
            }
            if(!cola2.isEmpty())
                pw.println("*********  Cola 2  *********");
            while(cola2.peek()!=null){
                lista.add(cola2.peek());
                pw.println(cola2.poll().getDatos());
            }
            if(!cola3.isEmpty())
                pw.println("*********  Cola 3  *********");
            while(cola3.peek()!=null){
                lista.add(cola3.peek());
                pw.println(cola3.poll().getDatos());
            }
            if(!cola4.isEmpty())
                pw.println("*********  Cola 4  *********");
            while(cola4.peek()!=null){
                lista.add(cola4.peek());
                pw.println(cola4.poll().getDatos());
            }
            if(!cola5.isEmpty())
                pw.println("*********  Cola 5  *********");
            while(cola5.peek()!=null){
                lista.add(cola5.peek());
                pw.println(cola5.poll().getDatos());
            }
            if(!cola6.isEmpty())
                pw.println("*********  Cola 6  *********");
            while(cola6.peek()!=null){
                lista.add(cola6.peek());
                pw.println(cola6.poll().getDatos());
            }
            if(!cola7.isEmpty())
                pw.println("*********  Cola 7  *********");
            while(cola7.peek()!=null){
                lista.add(cola7.peek());
                pw.println(cola7.poll().getDatos());
            }
            if(!cola8.isEmpty())
                pw.println("*********  Cola 8  *********");
            while(cola8.peek()!=null){
                lista.add(cola8.peek());
                pw.println(cola8.poll().getDatos());
            }
            if(!cola9.isEmpty())
                pw.println("*********  Cola 9  *********");
            while(cola9.peek()!=null){
                lista.add(cola9.peek());
                pw.println(cola9.poll().getDatos());
            }
            pw.close();
            fichero.close();
        } catch (Exception e) {
        }
    }

    static int obtenerMaximo(ArrayList<Persona> lista, int n) {

        int max = lista.get(0).getNumCuenta();
        for (var i = 0; i < n; i++)
            if (lista.get(i).getNumCuenta() > max)
                max = lista.get(i).getNumCuenta();

        return max;
    }
}
