package finalestructuradedatos;

import java.util.Scanner;

public class FinalEstructuraDeDatos {

    static Scanner in = new Scanner(System.in);

    public static void menu() {
        System.out.println("=======================ARBOLES=======================\n"
                + "1)ABB (enteros)\n"
                + "2)AVL (enteros)\n"
                + "3)B M=5(enteros)\n"
                + "=======================ARCHIVOS=======================\n"
                + "4)Archivos (stock de productos para la venta)\n"
                + "======================================================\n");
    }

    public static void menuArboles() {
        System.out.println("1)Recorrer (inorden)\n"
                + "2)Mostrar elemento minimo\n"
                + "3)Mostrar elemento maximo\n"
                + "4)Buscar elemento\n"
                + "5)Insertar elemento\n"
                + "6)Borrar elemento\n");
    }

    public static void operacionesArbolABB(ArbolABB arbolABB) {
        int respuesta;
        int dato;
        do {
            menuArboles();
            System.out.println("Ingresar numero de operacion (7 para finalizar)");
            respuesta = in.nextInt();
            switch (respuesta) {
                case 1:
                    recorrerABB(arbolABB);
                    break;
                case 2:
                    minimoABB(arbolABB);
                    break;
                case 3:
                    maximoABB(arbolABB);
                    break;
                case 4:
                    System.out.println("Ingresar dato a buscar:");
                    dato = in.nextInt();
                    busquedaABB(arbolABB, dato);
                    break;
                case 5:
                    System.out.println("Ingresar dato a agregar:");
                    dato = in.nextInt();
                    insertarABB(arbolABB, dato);
                    break;
                case 6:
                    System.out.println("Ingresar dato a borrar:");
                    dato = in.nextInt();
                    borrarABB(arbolABB, dato);
                    break;
                default:
                    System.out.println("Ingresar operacion valida");

            }
        } while (respuesta != 7);
    }

    public static void recorrerABB(ArbolABB arbolABB) {
        arbolABB.recorrer();
    }

    public static void minimoABB(ArbolABB arbolABB) {
        if (arbolABB.isEmpty()) {
            System.out.println("El arbol esta vacio");
        } else {
            System.out.println("El valor minimo es: " + arbolABB.getMinimo());
        }
    }

    public static void maximoABB(ArbolABB arbolABB) {
        if (arbolABB.isEmpty()) {
            System.out.println("El arbol esta vacio");
        } else {
            System.out.println("El valor maximo del arbol ABB es " + arbolABB.getMaximo());
        }
    }

    public static void busquedaABB(ArbolABB arbolABB, int dato) {
        if (arbolABB.buscar(dato)) {
            System.out.println("El dato: " + dato + " se encuentra en el arbol");
        } else {
            System.out.println("El dato " + dato + " NO se encuentra en el arbol");
        }
    }

    public static void insertarABB(ArbolABB arbolABB, int dato) {
        arbolABB.insert(dato);
    }

    public static void borrarABB(ArbolABB arbolABB, int dato) {
        arbolABB.delete(dato);
    }

    public static void operacionesArbolAVL(ArbolAVL arbolAVL) {
        int respuesta;
        int dato;
        do {
            menuArboles();
            System.out.println("Ingresar numero de operacion (7 para finalizar)");
            respuesta = in.nextInt();
            switch (respuesta) {
                case 1:
                    recorrerAVL(arbolAVL);
                    break;
                case 2:
                    minimoAVL(arbolAVL);
                    break;
                case 3:
                    maximoAVL(arbolAVL);
                    break;
                case 4:
                    System.out.println("Ingresar dato a buscar:");
                    dato = in.nextInt();
                    busquedaAVL(arbolAVL, dato);
                    break;
                case 5:
                    System.out.println("Ingresar dato a agregar:");
                    dato = in.nextInt();
                    insertarAVL(arbolAVL, dato);
                    break;
                case 6:
                    System.out.println("Ingresar dato a borrar:");
                    dato = in.nextInt();
                    borrarAVL(arbolAVL, dato);
                    break;
                default:
                    System.out.println("Ingresar operacion valida");

            }
        } while (respuesta != 7);
    }

    public static void recorrerAVL(ArbolAVL arbolAVL) {
        arbolAVL.recorrer();
    }

    public static void minimoAVL(ArbolAVL arbolAVL) {
        if (arbolAVL.isEmpty()) {
            System.out.println("El arbol esta vacio");
        } else {
            System.out.println("El valor minimo del arbol AVL es: " + arbolAVL.getMinimo());
        }
    }

    public static void maximoAVL(ArbolAVL arbolAVL) {
        if (arbolAVL.isEmpty()) {
            System.out.println("El arbol esta vacio");
        } else {
            System.out.println("El valor minimo del arbol AVL es: " + arbolAVL.getMaximo());
        }
    }

    public static void busquedaAVL(ArbolAVL arbolAVL, int dato) {
        if (arbolAVL.buscar(dato)) {
            System.out.println("El dato: " + dato + " se encuentra en el arbol");
        } else {
            System.out.println("El dato " + dato + " NO se encuentra en el arbol");
        }
    }

    public static void insertarAVL(ArbolAVL arbolAVL, int dato) {
        arbolAVL.insert(dato);
    }

    public static void borrarAVL(ArbolAVL arbolAVL, int dato) {
        arbolAVL.delete(dato);
    }

    public static void operacionesArbolB(ArbolB arbolB) {
        int respuesta;
        int dato;
        do {
            menuArboles();
            System.out.println("Ingresar numero de operacion (7 para finalizar)");
            respuesta = in.nextInt();
            switch (respuesta) {
                case 1:
                    recorrerB(arbolB);
                    break;
                case 2:
                    minimoB(arbolB);
                    break;
                case 3:
                    maximoB(arbolB);
                    break;
                case 4:
                    System.out.println("Ingresar dato a buscar:");
                    dato = in.nextInt();
                    busquedaB(arbolB, dato);
                    break;
                case 5:
                    System.out.println("Ingresar dato a agregar:");
                    dato = in.nextInt();
                    insertarB(arbolB, dato);
                    break;
                case 6:
                    System.out.println("Ingresar dato a borrar:");
                    dato = in.nextInt();
                    borrarB(arbolB, dato);
                    break;
                default:
                    System.out.println("Ingresar operacion valida");

            }
        } while (respuesta != 7);
    }

    public static void recorrerB(ArbolB arbolB) {
        arbolB.recorrer();
    }

    public static void minimoB(ArbolB arbolB) {
        if (arbolB.isEmpty()) {
            System.out.println("El arbol esta vacio");
        } else {
            System.out.println("El valor minimo del arbol B es: " + arbolB.getMinimo());
        }
    }

    public static void maximoB(ArbolB arbolB) {
        if (arbolB.isEmpty()) {
            System.out.println("El arbol esta vacio");
        } else {
            System.out.println("El valor maximo del arbol B es: " + arbolB.getMaximo());
        }
    }

    public static void busquedaB(ArbolB arbolB, int dato) {
        if (arbolB.buscar(dato)) {
            System.out.println("El dato " + dato + " se encuentra en el arbol");
        } else {
            System.out.println("El dato " + dato + " NO se encuentra en el arbol");
        }
    }

    public static void insertarB(ArbolB arbolB, int dato) {
        arbolB.insert(dato);
    }

    public static void borrarB(ArbolB arbolB, int dato) {
        arbolB.delete(dato);
    }

    public static void main(String[] args) {
        int respuesta;

        do {
            menu();
            System.out.println("Ingresar numero de operacion (5 para finalizar)");
            respuesta = in.nextInt();
            switch (respuesta) {
                case 1:
                    ArbolABB arbolABB = new ArbolABB();
                    operacionesArbolABB(arbolABB);
                    break;
                case 2:
                    ArbolAVL arbolAVL = new ArbolAVL();
                    operacionesArbolAVL(arbolAVL);
                    break;
                case 3:
                    ArbolB arbolB = new ArbolB();
                    operacionesArbolB(arbolB);
                    break;
                case 4:
                    Archivos.operacionesArchivos();
                    break;
                default:
                    System.out.println("Ingresar numero de operacion valido");
            }
        } while (respuesta != 5);
    }
}
