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

            System.out.println("Ingresar numero de operacion (7 para finalizar)");
            respuesta = in.nextInt();
            switch (respuesta) {
                case 1:
                    System.out.print("Recottido Inorden arbolABB: ");
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

    public static void recorrerABB(ArbolABB arbolABB) {
        Nodo nodoActual = arbolABB.raiz;
        recorridoInorden(nodoActual);
        System.out.println("");
    }

    public static void recorridoInorden(Nodo nodoActual) {
        if (nodoActual != null) {
            recorridoInorden(nodoActual.hi);
            System.out.print(nodoActual.dato + ", ");
            recorridoInorden(nodoActual.hd);
        }
    }

    public static void minimoABB(ArbolABB arbolABB) {
        Nodo nodoActual = arbolABB.raiz;
        System.out.println("El valor minimo del arbol ABB es: " + valorMinimo(nodoActual));
    }

    public static int valorMinimo(Nodo nodoActual) {
        while (nodoActual.hi != null) {
            nodoActual = nodoActual.hi;
        }
        return nodoActual.dato;
    }

    public static void maximoABB(ArbolABB arbolABB) {
        Nodo nodoActual = arbolABB.raiz;
        System.out.println("El valor maximo del arbol ABB es " + valorMaximo(nodoActual));
    }

    public static int valorMaximo(Nodo nodoActual) {
        while (nodoActual.hd != null) {
            nodoActual = nodoActual.hd;
        }
        return nodoActual.dato;
    }

    public static void busquedaABB(ArbolABB arbolABB, int dato) {
        Nodo nodoActual = arbolABB.raiz;
        if (seEncontro(nodoActual, dato)) {
            System.out.println("El dato: " + dato + " se encuentra en el arbol");
        } else {
            System.out.println("El dato " + dato + " no se encuentra en el arbol");
        }
    }

    public static boolean seEncontro(Nodo nodoActual, int dato) {
        return (busqueda(nodoActual, dato) != null);
    }

    public static Nodo busqueda(Nodo nodoActual, int dato) {
        if (nodoActual == null || nodoActual.dato == dato) {
            return nodoActual;
        } else {
            if (nodoActual.dato > dato) {
                nodoActual = nodoActual.hi;
                return busqueda(nodoActual, dato);
            } else {
                nodoActual = nodoActual.hd;
                return busqueda(nodoActual, dato);
            }
        }
    }

    public static void insertarABB(ArbolABB arbol, int dato) {
        Nodo nodoActual = arbol.raiz;
        if (noEsRepetido(nodoActual, dato)) {
            Nodo nuevoNodo = new Nodo();
            nuevoNodo.dato = dato;
            if (arbol.raiz == null) {
                arbol.raiz = nuevoNodo;
            } else {
                nodoActual = espacioDisponibleABB(nodoActual, dato);
                if (nodoActual.dato > dato) {
                    nodoActual.hi = nuevoNodo;
                } else {
                    nodoActual.hd = nuevoNodo;
                }
            }
        } else {
            System.out.println("Ese dato ya se encuentra en el arbol y los arboles ABB no permiten claves duplicadas");
        }
    }

    public static boolean noEsRepetido(Nodo nodoActual, int dato) {
        return !(seEncontro(nodoActual, dato));
    }

    public static Nodo espacioDisponibleABB(Nodo nodoActual, int dato) {
        if (nodoActual.dato > dato) {
            if (nodoActual.hi == null) {
                return nodoActual;
            } else {
                return espacioDisponibleABB(nodoActual.hi, dato);
            }
        } else {
            if (nodoActual.hd == null) {
                return nodoActual;
            } else {
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
