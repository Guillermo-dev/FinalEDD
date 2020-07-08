package finalestructuradedatos;

import java.util.Scanner;

public class FinalEstructuraDeDatos {

    public static Scanner in = new Scanner(System.in);

    public static void menu() {
        System.out.println("=======================ARBOLES=======================\n"
                + "1)ABB (enteros)\n"
                + "2)AVL (enteros)\n"
                + "3)B (enteros)\n"
                + "=======================ARCHIVOS=======================\n"
                + "4)Actualizar archivo maestro con un archivo detalle\n"
                + "5)Merge\n"
                + "6)Corte de Control\n"
                + "======================================================\n");
    }

    public static void menuArboles(ArbolABB arbolABB) {
        int respuesta;
        int dato;
        do {
        System.out.println("1)Recorrer (inorden)\n"
                + "2)Mostrar elemento minimo\n"
                + "3)Mostrar elemento maximo\n"
                + "4)Buscar elemento\n"
                + "5)Insertar elemento\n"
                + "6)Borrar elemento\n");
        
            System.out.println("Ingresar numero de operacion(7 para finalizar)");
            respuesta = in.nextInt();
            switch (respuesta) {
                case 1:
                    System.out.println("Recorrer");
                    break;
                case 2:
                    System.out.println("Minimo");
                    break;
                case 3:
                    System.out.println("Maximo");
                    break;
                case 4:
                    System.out.println("Buscar");
                    break;
                case 5:
                    System.out.println("Ingresar dato");
                    dato = in.nextInt();
                    insertarABB(arbolABB, dato);
                    break;
                case 6:
                    System.out.println("Borrar");
                    break;
                default:
                    System.out.println("Ingresar operacion valida");

            }
        } while (respuesta != 7);
    }
    
    public static void insertarABB (ArbolABB arbol, int dato){
        Nodo nodoActual = arbol.raiz;
        Nodo nuevoNodo = new Nodo();
            nuevoNodo.dato = dato;
        if (arbol.raiz == null){
            arbol.raiz = nuevoNodo;
            System.out.println("se cargo en la raiz");
        }else {
            nodoActual = espacioDisponibleABB (nodoActual, dato);
            if (nodoActual.dato > dato){
                nodoActual.hi = nuevoNodo;
                 System.out.println("se cargo en hi");
            }else{
                nodoActual.hd = nuevoNodo;
                 System.out.println("se cargo en hd");
            }
        }
    }
    public static Nodo espacioDisponibleABB (Nodo nodoActual, int dato){
        if (nodoActual.dato > dato){
            if (nodoActual.hi == null){
                return nodoActual;
            }else{
                return espacioDisponibleABB (nodoActual.hi, dato);
            }
        }else{
            if (nodoActual.hd == null){
                return nodoActual;
            }else{
                return espacioDisponibleABB(nodoActual.hd, dato);
            }
        }
    }
    

    public static void main(String[] args) {
        int respuesta;
        ArbolABB arbolABB = new ArbolABB();

        do {
            menu();
            System.out.println("Ingresar numero de operacion (7 para finalizar)");
            respuesta = in.nextInt();
            switch (respuesta) {
                case 1:
                    menuArboles(arbolABB);
                    break;
                case 2:
                    menuArboles(arbolABB);
                    break;
                case 3:
                    menuArboles(arbolABB);
                    break;
                case 4:
                    System.out.println("Actulizar");
                    break;
                case 5:
                    System.out.println("Merge");
                    break;
                case 6:
                    System.out.println("corte de control");
                    break;
                default:
                    System.out.println("Ingresar numero de operacion valido");
            }
        } while (respuesta != 7);
    }

}
