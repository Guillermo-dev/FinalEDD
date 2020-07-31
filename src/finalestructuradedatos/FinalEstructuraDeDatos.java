package finalestructuradedatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinalEstructuraDeDatos {

    //CONSTANTES
    static Scanner in = new Scanner(System.in);
    static String RUTA = "";
    static int CANT_PRODUCTOS = 4;
    static String[] NOMBRES = {"Producto 1", "Producto 2", "Producto 3", "Producto 4", "Producto 5"};
    static int[] CODIGOS = {0001, 0002, 0003, 0004, 0005};
    static float[] PRECIOS = {100, 200, 150, 500, 350};
    static int[] STOCK = {1000, 1000, 1000, 1000, 1000};

    static class Bool {

        public boolean desbalanceado;
    }

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
        if (arbolABB.raiz == null) {
            System.out.println("El arbol esta vasio");
        } else {
            Nodo nodoActual = arbolABB.raiz;
            System.out.print("Recorrido Inorden arbolABB: ");
            recorridoInorden(nodoActual);
            System.out.println("");
        }
    }

    public static void recorridoInorden(Nodo nodoActual) {
        if (nodoActual != null) {
            recorridoInorden(nodoActual.hi);
            System.out.print(nodoActual.dato + ", ");
            recorridoInorden(nodoActual.hd);
        }
    }

    public static void minimoABB(ArbolABB arbolABB) {
        if (arbolABB.raiz == null) {
            System.out.println("El arbol esta vacio");
        } else {
            Nodo nodoMinimo = valorMinimo(arbolABB.raiz);
            System.out.println("El valor minimo del arbol ABB es: " + nodoMinimo.dato);
        }
    }

    public static Nodo valorMinimo(Nodo nodoActual) {
        if (nodoActual.hi == null) {
            return nodoActual;
        } else {
            nodoActual = nodoActual.hi;
            return valorMinimo(nodoActual);
        }
    }

    public static void maximoABB(ArbolABB arbolABB) {
        if (arbolABB.raiz == null) {
            System.out.println("El arbol esta vacio");
        } else {
            Nodo nodoMayor = valorMaximo(arbolABB.raiz);
            System.out.println("El valor maximo del arbol ABB es " + nodoMayor.dato);
        }
    }

    public static Nodo valorMaximo(Nodo nodoActual) {
        if (nodoActual.hd == null) {
            return nodoActual;
        } else {
            nodoActual = nodoActual.hd;
            return valorMaximo(nodoActual);
        }
    }

    public static void busquedaABB(ArbolABB arbolABB, int dato) {
        Nodo nodoActual = arbolABB.raiz;
        if (seEncontro(nodoActual, dato)) {
            System.out.println("El dato: " + dato + " se encuentra en el arbol");
        } else {
            System.out.println("El dato " + dato + " NO se encuentra en el arbol");
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

    public static void insertarABB(ArbolABB arbolABB, int dato) {
        Nodo nodoActual = arbolABB.raiz;
        if (noEsRepetido(nodoActual, dato)) {
            Nodo nuevoNodo = new Nodo();
            nuevoNodo.dato = dato;
            if (arbolABB.raiz == null) {
                arbolABB.raiz = nuevoNodo;
            } else {
                nodoActual = buscarPadreNuevoNodo(nodoActual, dato);
                if (nodoActual.dato > dato) {
                    nodoActual.hi = nuevoNodo;
                } else {
                    nodoActual.hd = nuevoNodo;
                }
            }
        } else {
            System.out.println("El dato (" + dato + ") ya se encuentra en el arbol y los arboles ABB no permiten claves duplicadas");
        }
    }

    public static boolean noEsRepetido(Nodo nodoActual, int dato) {
        return !(seEncontro(nodoActual, dato));
    }

    public static Nodo buscarPadreNuevoNodo(Nodo nodoActual, int dato) {
        if (nodoActual.dato > dato) {
            if (nodoActual.hi == null) {
                return nodoActual;
            } else {
                return buscarPadreNuevoNodo(nodoActual.hi, dato);
            }
        } else {
            if (nodoActual.hd == null) {
                return nodoActual;
            } else {
                return buscarPadreNuevoNodo(nodoActual.hd, dato);
            }
        }
    }

    public static void borrarABB(ArbolABB arbolABB, int dato) {
        if (!seEncontro(arbolABB.raiz, dato)) {
            System.out.println("El dato que desea eliminar no se encuentra en el arbol");
        } else {
            Nodo nodoAEliminar = busqueda(arbolABB.raiz, dato);
            Nodo nodoPadre = busquedaPadre(arbolABB.raiz, dato);
            if (nodoAEliminar == nodoPadre && nodoAEliminar.hi == null && nodoAEliminar.hd == null) {
                arbolABB.raiz = null;
                System.out.println("El nodo con la clave: " + dato + " se elimino con exito");
            } else {
                eliminarNodo(nodoAEliminar, nodoPadre);
                System.out.println("El nodo con la clave: " + dato + " se elimino con exito");
            }
        }
    }

    public static void eliminarNodo(Nodo nodoAEliminar, Nodo nodoPadre) {

        if (nodoAEliminar.hi == null && nodoAEliminar.hd == null) {
            if (nodoPadre.dato >= nodoAEliminar.dato) {//>= por elimincacion de raiz con predecesor
                nodoPadre.hi = null;
            } else if (nodoPadre.dato < nodoAEliminar.dato) {
                nodoPadre.hd = null;
            }
        } else {
            if (nodoAEliminar.hi != null && nodoAEliminar.hd == null) {
                if (nodoPadre.dato == nodoAEliminar.dato) {
                    nodoPadre = nodoPadre.hi;
                } else if (nodoPadre.dato > nodoAEliminar.dato) {
                    nodoPadre.hi = nodoAEliminar.hi;
                } else if (nodoPadre.dato < nodoAEliminar.dato) {
                    nodoPadre.hd = nodoAEliminar.hi;
                }
            } else {
                if (nodoAEliminar.hi == null && nodoAEliminar.hd != null) {
                    if (nodoPadre.dato == nodoAEliminar.dato) {
                        nodoPadre = nodoPadre.hd;
                    } else if (nodoPadre.dato >= nodoAEliminar.dato) {
                        nodoPadre.hi = nodoAEliminar.hd;
                    } else if (nodoPadre.dato < nodoAEliminar.dato) {
                        nodoPadre.hd = nodoAEliminar.hd;
                    }
                } else {
                    Nodo nodoPredecesor = valorMinimo(nodoAEliminar);
                    Nodo nodoPadrePredecesor = busquedaPadre(nodoAEliminar, nodoPredecesor.dato);
                    nodoAEliminar.dato = nodoPredecesor.dato;
                    eliminarNodo(nodoPredecesor, nodoPadrePredecesor);
                }
            }
        }
    }

    public static Nodo busquedaPadre(Nodo nodoActual, int dato) {
        if (nodoActual.dato == dato) {
            return nodoActual;
        } else {
            if (nodoActual.hi != null && nodoActual.hi.dato == dato) {
                return nodoActual;
            } else if (nodoActual.hd != null && nodoActual.hd.dato == dato) {
                return nodoActual;
            } else {
                if (nodoActual.dato > dato) {
                    nodoActual = nodoActual.hi;
                    return busquedaPadre(nodoActual, dato);
                } else {
                    nodoActual = nodoActual.hd;
                    return busquedaPadre(nodoActual, dato);
                }
            }
        }
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
        if (arbolAVL.raiz == null) {
            System.out.println("El arbol esta vasio");
        } else {
            Nodo nodoActual = arbolAVL.raiz;
            System.out.print("Recorrido Inorden arbolAVL: ");
            recorridoInorden(nodoActual);
            System.out.println("");
        }
    }

    public static void minimoAVL(ArbolAVL arbolAVL) {
        if (arbolAVL.raiz == null) {
            System.out.println("El arbol esta vacio");
        } else {
            Nodo nodoMinimo = valorMinimo(arbolAVL.raiz);
            System.out.println("El valor minimo del arbol AVL es: " + nodoMinimo.dato);
        }
    }

    public static void maximoAVL(ArbolAVL arbolAVL) {
        if (arbolAVL.raiz == null) {
            System.out.println("El arbol esta vacio");
        } else {
            Nodo nodoMaximo = valorMaximo(arbolAVL.raiz);
            System.out.println("El valor minimo del arbol AVL es: " + nodoMaximo.dato);
        }
    }

    public static void busquedaAVL(ArbolAVL arbolAVL, int dato) {
        Nodo nodoActual = arbolAVL.raiz;
        if (seEncontro(nodoActual, dato)) {
            System.out.println("El dato: " + dato + " se encuentra en el arbol");
        } else {
            System.out.println("El dato " + dato + " NO se encuentra en el arbol");
        }
    }

    public static void insertarAVL(ArbolAVL arbolAVL, int dato) {
        Nodo nodoActual = arbolAVL.raiz;
        Bool estado = new Bool();
        if (noEsRepetido(nodoActual, dato)) {
            Nodo nuevoNodo = new Nodo();
            nuevoNodo.dato = dato;
            if (arbolAVL.raiz == null) {
                arbolAVL.raiz = nuevoNodo;
                arbolAVL.raiz.fe = 0;
            } else {
                nodoActual = buscarPadreNuevoNodo(nodoActual, dato);
                if (nodoActual.dato > dato) {
                    nodoActual.hi = nuevoNodo;
                } else {
                    nodoActual.hd = nuevoNodo;
                }
                Nodo nodoPivote = buscarNodoPivote(arbolAVL.raiz, nuevoNodo.dato);
                balanceo(arbolAVL, nodoPivote, estado);

            }
        } else {
            System.out.println("Ese dato (" + dato + ") ya se encuentra en el arbol y los arboles AVL no permiten claves duplicadas");
        }
    }

    public static void balanceo(ArbolAVL arbolAVL, Nodo nodoPivote, Bool estado) {

        System.out.println("Pivote: " + nodoPivote.dato + " ");
        calcularFE(arbolAVL.raiz);
        System.out.println("RAIZ:" + arbolAVL.raiz.dato);
        estaDesvalanceado(arbolAVL.raiz, estado);
        if (estado.desbalanceado) {
            hacerRotacion(arbolAVL, nodoPivote);
            calcularFE(arbolAVL.raiz);
            System.out.println("RAIZ:" + arbolAVL.raiz.dato);
        } else {
            System.out.println("NO esta desbalanceado");
        }
    }

    public static Nodo buscarNodoPivote(Nodo nodoRaiz, int dato) {
        Nodo nodoPivote = busquedaPadre(nodoRaiz, dato);
        if (nodoPivote.fe != 0) {
            return nodoPivote;
        } else if (nodoRaiz == nodoPivote) {
            return nodoRaiz;
        } else {
            return buscarNodoPivote(nodoRaiz, nodoPivote.dato);
        }
    }

    public static void calcularFE(Nodo nodoActual) {
        if (nodoActual != null) {
            calcularFE(nodoActual.hi);
            System.out.print(nodoActual.dato + ", ");
            nodoActual.fe = calcularAltura(nodoActual.hd) - calcularAltura(nodoActual.hi);
            System.out.println("FE:" + nodoActual.fe);
            calcularFE(nodoActual.hd);
        }
    }

    public static int calcularAltura(Nodo nodoActual) {
        if (nodoActual == null) {
            return 0;
        } else {
            if (nodoActual.hi != null && nodoActual.hd != null) {
                if (calcularAltura(nodoActual.hi) > calcularAltura(nodoActual.hd)) {
                    return calcularAltura(nodoActual.hi) + 1;
                } else {
                    return calcularAltura(nodoActual.hd) + 1;
                }
            } else if (nodoActual.hi != null && nodoActual.hd == null) {
                return calcularAltura(nodoActual.hi) + 1;
            } else if (nodoActual.hi == null && nodoActual.hd != null) {
                return calcularAltura(nodoActual.hd) + 1;
            } else {
                return 1;
            }
        }
    }

    public static void estaDesvalanceado(Nodo nodoActual, Bool estado) {
        if (nodoActual != null) {
            if (nodoActual.fe < -1 || nodoActual.fe > 1) {
                estado.desbalanceado = true;
            }
            estaDesvalanceado(nodoActual.hi, estado);
            estaDesvalanceado(nodoActual.hd, estado);
        }
    }

    public static void hacerRotacion(ArbolAVL arbolAVL, Nodo nodoPivote) {
        if (nodoPivote.fe > 1 && nodoPivote.hd.fe >= 0) {//">=0" para cuadno elimina
            rsi(arbolAVL, nodoPivote);
            System.out.println("RSI");
        } else if (nodoPivote.fe < 1 && nodoPivote.hi.fe <= 0) {//"<=0" para cuadno elimina
            rsd(arbolAVL, nodoPivote);
            System.out.println("RSD");
        } else if (nodoPivote.fe < 1 && nodoPivote.hi.fe >= 1) {
            rsi(arbolAVL, nodoPivote.hi);
            rsd(arbolAVL, nodoPivote);
            System.out.println("RDI");
        } else if (nodoPivote.fe > 1 && nodoPivote.hd.fe <= 1) {
            rsd(arbolAVL, nodoPivote.hd);
            rsi(arbolAVL, nodoPivote);
            System.out.println("RDD");
        }
    }

    public static void rsi(ArbolAVL arbolAVL, Nodo nodoPivote) {
        Nodo hijoPivote;
        Nodo padrePivote;

        hijoPivote = nodoPivote.hd;
        nodoPivote.hd = hijoPivote.hi;
        hijoPivote.hi = nodoPivote;
        if (arbolAVL.raiz == nodoPivote) {
            arbolAVL.raiz = hijoPivote;
        } else {
            padrePivote = busquedaPadre(arbolAVL.raiz, nodoPivote.dato);
            if (padrePivote.hi == nodoPivote) {
                padrePivote.hi = hijoPivote;
            } else {
                padrePivote.hd = hijoPivote;
            }
        }

    }

    public static void rsd(ArbolAVL arbolAVL, Nodo nodoPivote) {
        Nodo hijoPivote;
        Nodo padrePivote;

        hijoPivote = nodoPivote.hi;
        nodoPivote.hi = hijoPivote.hd;
        hijoPivote.hd = nodoPivote;
        if (arbolAVL.raiz == nodoPivote) {
            arbolAVL.raiz = hijoPivote;
        } else {
            padrePivote = busquedaPadre(arbolAVL.raiz, nodoPivote.dato);
            if (padrePivote.hi == nodoPivote) {
                padrePivote.hi = hijoPivote;
            } else {
                padrePivote.hd = hijoPivote;
            }
        }
    }

    public static void borrarAVL(ArbolAVL arbolAVL, int dato) {
        Bool estado = new Bool();
        if (!seEncontro(arbolAVL.raiz, dato)) {
            System.out.println("El dato que desea eliminar no se encuentra en el arbol");
        } else {
            Nodo nodoAEliminar = busqueda(arbolAVL.raiz, dato);
            Nodo nodoPadre = busquedaPadre(arbolAVL.raiz, dato);
            Nodo nodoPivote = buscarNodoPivote(arbolAVL.raiz, nodoAEliminar.dato);
            if (nodoAEliminar == nodoPadre && nodoAEliminar.hi == null && nodoAEliminar.hd == null) {
                arbolAVL.raiz = null;
                System.out.println("El nodo con la clave: " + dato + " se elimino con exito");
            } else {
                eliminarNodo(nodoAEliminar, nodoPadre);
                System.out.println("El nodo con la clave: " + dato + " se elimino con exito");
            }
            balanceo(arbolAVL, nodoPivote, estado);
        }
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
        if (arbolB.raiz == null) {
            System.out.println("El arbol esta vacio");
        } else if (arbolB.raiz.datos[0] == null) {
            System.out.println("El arbol esta vacio");
        } else {
            NodoB nodoActual = arbolB.raiz;
            System.out.print("Recorrido Inorden arbolB: ");
            recorridoInordenB(nodoActual);
            System.out.println("");
        }
    }

    public static void recorridoInordenB(NodoB nodoActual) {
        if (nodoActual != null) {
            recorridoInordenB(nodoActual.hijos[0]);
            int i = 0;
            int j = 0;
            while (nodoActual.datos[i] != null) {
                System.out.print(nodoActual.datos[i].dato + ", ");
                if (nodoActual.hijos[j + 1] != null) {
                    recorridoInordenB(nodoActual.hijos[j + 1]);
                    j++;
                }
                i++;
            }

        }
    }

    public static void minimoB(ArbolB arbolB) {
        if (arbolB.raiz == null) {
            System.out.println("El arbol esta vacio");
        } else {
            NodoB nodoMinimo = valorMinimoB(arbolB.raiz);
            System.out.println("El valor minimo del arbol B es: " + nodoMinimo.datos[0].dato);
        }
    }

    public static NodoB valorMinimoB(NodoB nodoActual) {
        if (nodoActual.hijos[0] == null) {
            return nodoActual;
        } else {
            nodoActual = nodoActual.hijos[0];
            return valorMinimoB(nodoActual);
        }
    }

    public static void maximoB(ArbolB arbolB) {
        if (arbolB.raiz == null) {
            System.out.println("El arbol esta vacio");
        } else {
            NodoB nodoMaximo = valorMaximoB(arbolB.raiz);
            int i = 0;
            while (nodoMaximo.datos[i + 1] != null) {
                i++;
            }
            System.out.println("El valor maximo del arbol B es: " + nodoMaximo.datos[i].dato);
        }
    }

    public static NodoB valorMaximoB(NodoB nodoActual) {
        for (int i = 4; i > 0; i--) {
            if (nodoActual.hijos[i] != null) {
                return valorMaximoB(nodoActual.hijos[i]);
            }
        }
        return nodoActual;
    }

    public static void busquedaB(ArbolB arbolB, int dato) {
        NodoB nodoActual = arbolB.raiz;
        if (seEncontroB(nodoActual, dato)) {
            System.out.println("El dato " + dato + " se encuentra en el arbol");
        } else {
            System.out.println("El dato " + dato + " NO se encuentra en el arbol");
        }
    }

    public static boolean seEncontroB(NodoB nodoActual, int dato) {
        return (busquedaB(nodoActual, dato) != null);
    }

    public static NodoB busquedaB(NodoB nodoActual, int dato) {
        if (nodoActual == null) {
            return nodoActual;
        }
        int i = 0;
        while (nodoActual.datos[i] != null) {
            if (nodoActual.datos[i].dato == dato) {
                return nodoActual;
            }
            i++;
        }
        i = 0;
        while (nodoActual.datos[i] != null) {
            if (nodoActual.datos[i].dato > dato) {
                return busquedaB(nodoActual.hijos[i], dato);
            }
            i++;
        }
        return busquedaB(nodoActual.hijos[i], dato);
    }

    public static void insertarB(ArbolB arbolB, int dato) {
        NodoB nodoActual = arbolB.raiz;
        if (arbolB.raiz == null) {
            NodoB nuevoNodo = new NodoB();
            nuevoNodo.datos[0] = new Dato();
            nuevoNodo.datos[0].dato = dato;
            arbolB.raiz = nuevoNodo;
        } else {
            if (noEsRepetidoB(nodoActual, dato)) {
                nodoActual = buscarNodoParaInsertar(nodoActual, dato);
                insertarEnNodo(nodoActual, dato);
                if (nodoLleno(nodoActual.datos)) {
                    solucionarOverflow(arbolB, nodoActual, null);
                }
            } else {
                System.out.println("El dato (" + dato + ") ya se encuentra en el arbol y los arboles B no permiten claves duplicadas");
            }
        }
    }

    public static boolean noEsRepetidoB(NodoB nodoRaiz, int dato) {
        return !(seEncontroB(nodoRaiz, dato));
    }

    public static NodoB buscarNodoParaInsertar(NodoB nodoActual, int dato) {
        if (nodoActual.hijos[0] != null) {
            int i = 0;
            while (nodoActual.datos[i] != null) {
                if (nodoActual.datos[i].dato > dato) {
                    nodoActual = nodoActual.hijos[i];
                    return buscarNodoParaInsertar(nodoActual, dato);
                }
                i++;
            }
            return buscarNodoParaInsertar(nodoActual.hijos[i], dato);
        }

        return nodoActual;
    }

    public static void insertarEnNodo(NodoB nodoActual, int dato) {
        int i = 0;
        while (nodoActual.datos[i] != null) {
            i++;
        }
        nodoActual.datos[i] = new Dato();
        nodoActual.datos[i].dato = dato;
        while (i > 0) {
            if (nodoActual.datos[i].dato < nodoActual.datos[i - 1].dato) {
                int aux = nodoActual.datos[i - 1].dato;
                nodoActual.datos[i - 1].dato = nodoActual.datos[i].dato;
                nodoActual.datos[i].dato = aux;
            }
            i--;
        }
    }

    public static boolean nodoLleno(Dato[] datos) {
        return (datos[4] != null);
    }

    public static void solucionarOverflow(ArbolB arbolB, NodoB nodoActual, NodoB nodoAux) {
        NodoB nuevoNodo = new NodoB();
        nuevoNodo.datos[0] = new Dato();
        nuevoNodo.datos[0].dato = nodoActual.datos[3].dato;
        nuevoNodo.datos[1] = new Dato();
        nuevoNodo.datos[1].dato = nodoActual.datos[4].dato;
        nodoActual.datos[3] = null;
        nodoActual.datos[4] = null;
        nuevoNodo.hijos[0] = nodoActual.hijos[3];
        nuevoNodo.hijos[1] = nodoActual.hijos[4];
        nuevoNodo.hijos[2] = nodoAux;
        nodoActual.hijos[3] = null;
        nodoActual.hijos[4] = null;

        if (noHayPadre(arbolB, nodoActual)) {
            NodoB nuevaRaiz = new NodoB();
            nuevaRaiz.datos[0] = new Dato();
            nuevaRaiz.datos[0].dato = nodoActual.datos[2].dato;
            nodoActual.datos[2] = null;
            nuevaRaiz.hijos[0] = arbolB.raiz;
            nuevaRaiz.hijos[1] = nuevoNodo;
            arbolB.raiz = nuevaRaiz;
        } else {
            NodoB nodoPadre = buscarPadre(arbolB.raiz, nodoActual);
            insertarEnNodo(nodoPadre, nodoActual.datos[2].dato);
            nodoActual.datos[2] = null;
            if (nodoLleno(nodoPadre.datos)) {
                solucionarOverflow(arbolB, nodoPadre, nuevoNodo);
            } else {
                int i = 0;
                while (nodoPadre.hijos[i] != null) {
                    i++;
                }
                nodoPadre.hijos[i] = nuevoNodo;
                while (i > 0) {
                    if (nodoPadre.hijos[i].datos[0].dato < nodoPadre.hijos[i - 1].datos[0].dato) {
                        NodoB aux = nodoPadre.hijos[i - 1];
                        nodoPadre.hijos[i - 1] = nuevoNodo;
                        nodoPadre.hijos[i] = aux;
                    }
                    i--;
                }
            }

        }

    }

    public static boolean noHayPadre(ArbolB arbolB, NodoB nodoActual) {
        return (arbolB.raiz == nodoActual);
    }

    public static NodoB buscarPadre(NodoB nodoActual, NodoB nodoHijo) {
        int i = 0;
        while (nodoActual.datos[i] != null) {
            if (nodoActual.hijos[i] == nodoHijo) {
                return nodoActual;
            }
            i++;
        }
        if (nodoActual.hijos[i] == nodoHijo) {
            return nodoActual;
        }
        int j = 0;
        while (nodoActual.datos[j] != null) {
            if (nodoActual.datos[j].dato > nodoHijo.datos[0].dato) {
                return buscarPadre(nodoActual.hijos[j], nodoHijo);
            }
            j++;
        }
        return buscarPadre(nodoActual.hijos[j], nodoHijo);
    }

    public static void borrarB(ArbolB arbolB, int dato) {
        if (!seEncontroB(arbolB.raiz, dato)) {
            System.out.println("El dato que desea eliminar no se encuentra en el arbol");
        } else {
            NodoB nodoAEliminar = busquedaB(arbolB.raiz, dato);
            borrarElemento(arbolB, nodoAEliminar, dato);

        }
    }

    public static void borrarElemento(ArbolB arbolB, NodoB nodoAEliminar, int dato) {
        if (esNodoInterno(nodoAEliminar)) {
            int i = 0;
            while (nodoAEliminar.datos[i].dato != dato) {
                i++;
            }
            NodoB nodoHI = nodoAEliminar.hijos[i];
            NodoB nodoHD = nodoAEliminar.hijos[i + 1];
            int cantHI = cantidadElementos(nodoHI);
            int cantHD = cantidadElementos(nodoHD);

            if (cantHI > cantHD) {
                nodoAEliminar.datos[i].dato = nodoHI.datos[cantHI].dato;
                nodoHI.datos[cantHI].dato = dato;
                borrarElemento(arbolB, nodoHI, dato);
            } else {
                nodoAEliminar.datos[i].dato = nodoHD.datos[0].dato;
                nodoHD.datos[0].dato = dato;
                borrarElemento(arbolB, nodoHD, dato);
            }
        } else {
            int i = 0;
            while (nodoAEliminar.datos[i].dato != dato) {
                i++;
            }
            while (nodoAEliminar.datos[i + 1] != null) {
                nodoAEliminar.datos[i].dato = nodoAEliminar.datos[i + 1].dato;
                nodoAEliminar.datos[i + 1].dato = dato;
                i++;
            }
            nodoAEliminar.datos[i] = null;
            if (!(noHayPadre(arbolB, nodoAEliminar))) {
                if (cantidadElementos(nodoAEliminar) < 2) {
                    NodoB nodoPadre = buscarPadre(arbolB.raiz, nodoAEliminar);
                    transferencia(arbolB, nodoPadre, nodoAEliminar);
                }
            }
        }
    }

    public static boolean esNodoInterno(NodoB nodoActual) {
        return (nodoActual.hijos[0] != null);
    }

    public static int cantidadElementos(NodoB nodoActual) {
        int i = 0;
        while (nodoActual.datos[i] != null) {
            i++;
        }
        return i;
    }

    public static void transferencia(ArbolB arbolB, NodoB nodoPadre, NodoB nodoActual) {
        int i = 0;
        int cantHI = 0;
        int cantHD = 0;
        NodoB hermanoIzq = null;
        NodoB hermanoDer = null;
        while (nodoPadre.hijos[i] != nodoActual) {
            i++;
        }
        if (i != 0) {
            if (nodoPadre.hijos[i - 1] != null) {
                hermanoIzq = nodoPadre.hijos[i - 1];
                cantHI = cantidadElementos(hermanoIzq);
            }
        }
        if (i != 4) {
            if (nodoPadre.hijos[i + 1] != null) {
                hermanoDer = nodoPadre.hijos[i + 1];
                cantHD = cantidadElementos(hermanoDer);
            }
        }
        if (cantHI > 2 || cantHD > 2) {
            if (cantHI > cantHD) {
                insertarEnNodo(nodoActual, nodoPadre.datos[i - 1].dato);
                nodoPadre.datos[i - 1] = hermanoIzq.datos[cantHI - 1];
                hermanoIzq.datos[cantHI - 1] = null;
                System.out.println("Transferencia con hermano izquierdo");
            } else {
                insertarEnNodo(nodoActual, nodoPadre.datos[i].dato);
                nodoPadre.datos[i] = hermanoDer.datos[0];
                i = 0;
                while (hermanoDer.datos[i + 1] != null) {
                    hermanoDer.datos[i] = hermanoDer.datos[i + 1];
                    i++;
                }
                hermanoDer.datos[i] = null;
                System.out.println("Transferencia con hermano derecho");
            }
        } else {
            fusion(arbolB, nodoPadre, nodoActual, hermanoIzq, hermanoDer);
        }
    }

    public static void fusion(ArbolB arbolB, NodoB nodoPadre, NodoB nodoActual, NodoB hermanoIzq, NodoB hermanoDer) {
        if (hermanoIzq != null) {
            int i = 0;
            while (nodoPadre.hijos[i] != nodoActual) {
                i++;
            }
            insertarEnNodo(nodoActual, nodoPadre.datos[i - 1].dato);
            insertarEnNodo(nodoActual, hermanoIzq.datos[0].dato);
            insertarEnNodo(nodoActual, hermanoIzq.datos[1].dato);
            nodoActual.hijos[3] = nodoActual.hijos[1];
            nodoActual.hijos[4] = nodoActual.hijos[2];
            nodoActual.hijos[0] = hermanoIzq.hijos[0];
            nodoActual.hijos[1] = hermanoIzq.hijos[1];
            nodoActual.hijos[2] = hermanoIzq.hijos[2];

            while (nodoPadre.datos[i] != null) {
                nodoPadre.datos[i - 1].dato = nodoPadre.datos[i].dato;
                i++;
            }
            nodoPadre.datos[i - 1] = null;

            i = 0;
            while (i < 4) {
                if (nodoPadre.hijos[i + 1] != null) {
                    if (nodoPadre.hijos[i].datos[0].dato == nodoPadre.hijos[i + 1].datos[0].dato) {
                        nodoPadre.hijos[i] = nodoPadre.hijos[i + 1];
                        if (i < 3) {
                            nodoPadre.hijos[i + 1] = nodoPadre.hijos[i + 2];
                        } else {
                            nodoPadre.hijos[4] = null;
                        }
                    }

                }
                i++;
            }

        } else {
            int i = 0;
            while (nodoPadre.hijos[i] != nodoActual) {
                i++;
            }
            insertarEnNodo(nodoActual, nodoPadre.datos[i].dato);
            insertarEnNodo(nodoActual, hermanoDer.datos[0].dato);
            insertarEnNodo(nodoActual, hermanoDer.datos[1].dato);
            nodoActual.hijos[2] = hermanoDer.hijos[0];
            nodoActual.hijos[3] = hermanoDer.hijos[1];
            nodoActual.hijos[4] = hermanoDer.hijos[2];

            while (nodoPadre.datos[i + 1] != null) {
                nodoPadre.datos[i].dato = nodoPadre.datos[i + 1].dato;
                i++;
            }
            nodoPadre.datos[i] = null;

            i = 0;
            while (i < 4) {
                if (nodoPadre.hijos[i + 1] != null) {
                    if (nodoPadre.hijos[i].datos[2] != null) {
                        if (nodoPadre.hijos[i].datos[2].dato == nodoPadre.hijos[i + 1].datos[0].dato) {
                            nodoPadre.hijos[i + 1] = nodoPadre.hijos[i + 2];
                        }
                    } else {
                        if (nodoPadre.hijos[i] == nodoPadre.hijos[i + 1]) {
                            if (i < 3) {
                                nodoPadre.hijos[i + 1] = nodoPadre.hijos[i + 2];
                            } else {
                                nodoPadre.hijos[4] = null;
                            }
                        }
                    }

                }
                i++;
            }
        }
        System.out.println("Fusion");
        if (nodoPadre.datos[0] == null) {
            arbolB.raiz = nodoPadre.hijos[0];
        } else if (!(noHayPadre(arbolB, nodoPadre))) {
            if (cantidadElementos(nodoPadre) < 2) {
                transferencia(arbolB, buscarPadre(arbolB.raiz, nodoPadre), nodoPadre);
            }
        }
    }

    public static void menuArchivos() {
        System.out.println("1)Generar archivo maestro (constante)\n"
                + "2)Actualizar archivo maestro (constatnte) con un archivo detalle\n"
                + "3)Merge\n"
                + "4)Corte de Control (constatnte)\n"
                + "5)Corte de control (Merge)\n"
                + "6)Eliminar maestro (Constante y/o merge)\n");
    }

    public static void operacionesArchivos() {
        int respuesta;
        String nombreMaestro = "";
        String nombreMaestro2 = "";
        in.nextLine();

        do {
            menuArchivos();
            System.out.println("Ingresar numero de operacion (7 para finalizar)");
            respuesta = in.nextInt();
            switch (respuesta) {
                case 1:
                    System.out.println("Ingresar el nombre que quiera que tenga el archivo");
                    in.nextLine();
                    nombreMaestro = in.nextLine();
                    generarMaestro(nombreMaestro);
                    break;
                case 2:
                    generarDetalle("Detalle", nombreMaestro);
                    actualizarMaestro(nombreMaestro, "Detalle");
                    break;
                case 3:
                    in.nextLine();
                    generarDetalleMerge("Detalle1");
                    generarDetalleMerge("Detalle2");
                    generarDetalleMerge("Detalle3");
                    System.out.println("Ingresar nombre para el archivo maestro");
                    nombreMaestro2 = in.nextLine();
                    merge("Detalle1", "Detalle2", "Detalle3", nombreMaestro2 + "Merge");
                    break;
                case 4:
                    corteDeControl(nombreMaestro);
                    break;
                case 5:
                    corteControlMerge(nombreMaestro2 + "Merge");
                    break;
                case 6:
                    eliminarMaestro(nombreMaestro, nombreMaestro2 + "Merge");
                    break;
                default:
                    System.out.println("Ingresar operacion valida");
            }
        } while (respuesta != 7);
    }

    public static void generarMaestro(String nombreMaestro) {
        FileOutputStream maestro;
        ObjectOutputStream bufferSalida;

        try {

            maestro = new FileOutputStream(RUTA + nombreMaestro + ".dat");
            bufferSalida = new ObjectOutputStream(maestro);

            for (int i = 0; i < CANT_PRODUCTOS; i++) {

                Producto nuevoProducto = new Producto();
                nuevoProducto.nombre = NOMBRES[i];
                nuevoProducto.codigo = CODIGOS[i];
                nuevoProducto.precio = PRECIOS[i];
                nuevoProducto.stock = STOCK[i];

                bufferSalida.writeObject(nuevoProducto);
            }

            bufferSalida.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro un archivo con el nombre " + nombreMaestro + ".dat");
        } catch (IOException ex) {
        }
    }

    public static void generarDetalle(String nombreDetalle, String nombreMaestro) {
        char respuesta;
        FileOutputStream detalle;
        ObjectOutputStream bufferSalida;

        try {
            FileInputStream maestro = new FileInputStream(RUTA + nombreMaestro + ".dat");
            detalle = new FileOutputStream(RUTA + nombreDetalle + ".dat");
            bufferSalida = new ObjectOutputStream(detalle);

            do {

                Venta nuevaVenta = new Venta();
                System.out.println("Ingresar el codigo del producto que se vendio (0001 a 0004)");
                nuevaVenta.codigo = in.nextInt();
                while (nuevaVenta.codigo != 0001 && nuevaVenta.codigo != 0002 && nuevaVenta.codigo != 0003 && nuevaVenta.codigo != 0004) {
                    System.out.println("Ingresar el codigo del producto que se vendio (0001 a 0004)");
                    nuevaVenta.codigo = in.nextInt();
                }
                System.out.println("Ingresar la cantidad de elementos vendidos");
                nuevaVenta.cantidadVendida = in.nextInt();
                while (cantidadExedida(nuevaVenta.cantidadVendida, nombreMaestro)) {
                    System.out.println("Ingresar la cantidad de elementos vendidos");
                    nuevaVenta.cantidadVendida = in.nextInt();
                }

                bufferSalida.writeObject(nuevaVenta);

                System.out.println("Desea ingresar un nuevo prroducto?(S/N)");
                in.nextLine();
                respuesta = in.nextLine().charAt(0);
                while (respuesta != 's' && respuesta != 'S' && respuesta != 'n' && respuesta != 'N') {
                    System.out.println("Desea ingresar un nuevo prroducto?(S/N)");
                    in.nextLine();
                    respuesta = in.nextLine().charAt(0);
                }

            } while (respuesta == 's' || respuesta == 'S');

            bufferSalida.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public static boolean cantidadExedida(int cantidadVendida, String nombreMaestro) {
        FileInputStream maestro;
        ObjectInputStream bufferEntrada;

        try {
            maestro = new FileInputStream(RUTA + nombreMaestro + ".dat");
            bufferEntrada = new ObjectInputStream(maestro);

            Producto producto = (Producto) bufferEntrada.readObject();
            return producto.stock < cantidadVendida;

        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {
        }
        return true;
    }

    public static void actualizarMaestro(String nombreMaestro, String nombreDetalle) {
        FileInputStream maestro;
        FileInputStream detalle;
        ObjectInputStream bufferEntradaDet;

        try {

            detalle = new FileInputStream(RUTA + nombreDetalle + ".dat");
            bufferEntradaDet = new ObjectInputStream(detalle);

            for (int j = 0; j < CANT_PRODUCTOS; j++) {

                maestro = new FileInputStream(RUTA + nombreMaestro + ".dat");
                ObjectInputStream bufferEntradaMas = new ObjectInputStream(maestro);

                Venta venta = (Venta) bufferEntradaDet.readObject();

                FileOutputStream nuevoMaestro = new FileOutputStream(RUTA + nombreMaestro + "2.dat");
                ObjectOutputStream bufferSalida = new ObjectOutputStream(nuevoMaestro);

                for (int i = 0; i < CANT_PRODUCTOS; i++) {

                    Producto producto = (Producto) bufferEntradaMas.readObject();

                    if (venta.codigo == producto.codigo) {
                        producto.stock -= venta.cantidadVendida;
                    }
                    Producto nuevoProducto = new Producto();
                    nuevoProducto.nombre = producto.nombre;
                    nuevoProducto.codigo = producto.codigo;
                    nuevoProducto.precio = producto.precio;
                    nuevoProducto.stock = producto.stock;
                    bufferSalida.writeObject(nuevoProducto);

                }

                File viejoMaestro = new File(RUTA + nombreMaestro + ".dat");
                viejoMaestro.delete();

                FileInputStream maestro2 = new FileInputStream(RUTA + nombreMaestro + "2.dat");
                ObjectInputStream bufferEntradaMas2 = new ObjectInputStream(maestro2);

                FileOutputStream nuevoMaestro2 = new FileOutputStream(RUTA + nombreMaestro + ".dat");
                ObjectOutputStream bufferSalida2 = new ObjectOutputStream(nuevoMaestro2);

                for (int i = 0; i < CANT_PRODUCTOS; i++) {

                    Producto producto = (Producto) bufferEntradaMas2.readObject();

                    Producto nuevoProducto = new Producto();
                    nuevoProducto.nombre = producto.nombre;
                    nuevoProducto.codigo = producto.codigo;
                    nuevoProducto.precio = producto.precio;
                    nuevoProducto.stock = producto.stock;
                    bufferSalida2.writeObject(nuevoProducto);
                }

                bufferEntradaMas2.close();
                bufferSalida2.close();
                nuevoMaestro.close();
                bufferSalida.close();
                bufferEntradaMas.close();

                File aux = new File(RUTA + nombreMaestro + "2.dat");
                aux.delete();

            }

            bufferEntradaDet.close();

        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo maestro\n");
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    public static void generarDetalleMerge(String nombre) {
        FileOutputStream detalle;
        ObjectOutputStream bufferSalida;
        char respuesta;

        try {

            detalle = new FileOutputStream(RUTA + nombre + ".dat");
            bufferSalida = new ObjectOutputStream(detalle);

            do {

                Producto nuevoProducto = new Producto();
                System.out.println("Ingresar el nombre del producto");
                nuevoProducto.nombre = in.nextLine();
                System.out.println("Ingresar el codigo del producto");
                nuevoProducto.codigo = in.nextInt();
                System.out.println("Ingresar el precio del producto");
                nuevoProducto.precio = in.nextFloat();
                System.out.println("Ingresar el stock disponible del producto");
                nuevoProducto.stock = in.nextInt();

                bufferSalida.writeObject(nuevoProducto);

                System.out.println("Desea ingresar un nuevo prroducto en el " + nombre + " ?(S/N)");
                in.nextLine();
                respuesta = in.nextLine().charAt(0);
                while (respuesta != 's' && respuesta != 'S' && respuesta != 'n' && respuesta != 'N') {
                    System.out.println("Desea ingresar un nuevo prroducto en el " + nombre + " ?(S/N)");
                    in.nextLine();
                    respuesta = in.nextLine().charAt(0);
                }
            } while (respuesta == 's' || respuesta == 'S');

            bufferSalida.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public static void merge(String detalle1, String detalle2, String detalle3, String nombreMaestro) {
        FileInputStream d1;
        ObjectInputStream entradaD1 = null;
        FileInputStream d2;
        ObjectInputStream entradaD2 = null;
        FileInputStream d3;
        ObjectInputStream entradaD3 = null;
        boolean salirD1 = true, salirD2 = true, salirD3 = true;
        Producto min = null;
        Producto productoD1 = null;
        Producto productoD2 = null;
        Producto productoD3 = null;

        FileOutputStream maestro;
        ObjectOutputStream bufferSalida = null;

        try {
            d1 = new FileInputStream(RUTA + detalle1 + ".dat");
            entradaD1 = new ObjectInputStream(d1);

            d2 = new FileInputStream(RUTA + detalle2 + ".dat");
            entradaD2 = new ObjectInputStream(d2);

            d3 = new FileInputStream(RUTA + detalle3 + ".dat");
            entradaD3 = new ObjectInputStream(d3);

            maestro = new FileOutputStream(RUTA + nombreMaestro + ".dat");
            bufferSalida = new ObjectOutputStream(maestro);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        while (salirD1 || salirD2 || salirD3) {

            try {
                if (min == null) {
                    productoD1 = (Producto) entradaD1.readObject();
                } else if (productoD1 == min) {
                    productoD1 = (Producto) entradaD1.readObject();
                }
            } catch (IOException | ClassNotFoundException ex) {
                productoD1 = null;
                salirD1 = false;
            }

            try {
                if (min == null) {
                    productoD2 = (Producto) entradaD2.readObject();
                } else if (productoD2 == min) {
                    productoD2 = (Producto) entradaD2.readObject();
                }
            } catch (IOException | ClassNotFoundException ex) {
                productoD2 = null;
                salirD2 = false;
            }

            try {
                if (min == null) {
                    productoD3 = (Producto) entradaD3.readObject();
                } else if (productoD3 == min) {
                    productoD3 = (Producto) entradaD3.readObject();
                }
            } catch (IOException | ClassNotFoundException ex) {
                productoD3 = null;
                salirD3 = false;
            }

            min = minimo(productoD1, productoD2, productoD3);

            try {
                if (min != null) {
                    Producto producto = new Producto();
                    producto.codigo = min.codigo;
                    producto.nombre = min.nombre;
                    producto.precio = min.precio;
                    producto.stock = min.stock;

                    bufferSalida.writeObject(producto);
                }

            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
        }
        try {
            bufferSalida.close();
            entradaD1.close();
            entradaD2.close();
            entradaD3.close();
        } catch (IOException ex) {
        }

    }

    public static Producto minimo(Producto d1, Producto d2, Producto d3) {
        if (d1 != null && d2 != null && d3 != null) {
            if (d1.codigo <= d2.codigo && d1.codigo <= d3.codigo) {
                return d1;
            } else if (d2.codigo <= d3.codigo) {
                return d2;
            } else {
                return d3;
            }
        } else if (d1 != null && d2 != null) {
            if (d1.codigo <= d2.codigo) {
                return d1;
            } else {
                return d2;
            }
        } else if (d2 != null && d3 != null) {
            if (d2.codigo <= d3.codigo) {
                return d2;
            } else {
                return d3;
            }
        } else if (d1 != null) {
            return d1;
        } else if (d2 != null) {
            return d2;
        } else if (d3 != null) {
            return d3;
        } else {
            return null;
        }
    }

    public static void corteDeControl(String nombreMaestro) {
        FileInputStream maestro;
        ObjectInputStream bufferEntrada = null;
        int ventasTotales = 0;
        float dineroTotal = 0;
        int i = 0;
        boolean mostrar = false;

        try {
            maestro = new FileInputStream(RUTA + nombreMaestro + ".dat");
            bufferEntrada = new ObjectInputStream(maestro);
            mostrar = true;

            while (true) {
                Producto producto = (Producto) bufferEntrada.readObject();

                System.out.println("===============================================================");
                System.out.println("Producto: " + producto.nombre);
                System.out.println("Codigo: " + producto.codigo);
                System.out.println("Stock disponible: " + producto.stock);
                System.out.println("Ventas realizadas: " + (STOCK[i] - producto.stock));
                System.out.println("Dinero por ventas: $" + (STOCK[i] - producto.stock) * PRECIOS[i]);
                ventasTotales += (STOCK[i] - producto.stock);
                dineroTotal += (STOCK[i] - producto.stock) * PRECIOS[i];
                i++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro un archivo maestro\n");
        } catch (IOException | ClassNotFoundException ex) {
        }
        try {
            if (mostrar) {
                System.out.println("===============================================================");
                System.out.println("Total de ventas realizadas: " + ventasTotales);
                System.out.println("Total de dinero por ventas: $" + dineroTotal);
                System.out.println("===============================================================");
                System.out.println("                                Nombre del archivo:" + nombreMaestro + ".dat\n");
                bufferEntrada.close();
            }
        } catch (IOException ex) {
        }
    }

    public static void corteControlMerge(String nombreMaestro) {
        FileInputStream maestro;
        ObjectInputStream bufferEntrada = null;

        try {
            maestro = new FileInputStream(RUTA + nombreMaestro + ".dat");
            bufferEntrada = new ObjectInputStream(maestro);

            while (true) {
                Producto producto = (Producto) bufferEntrada.readObject();

                System.out.println("===============================================================");
                System.out.println("Producto: " + producto.nombre);
                System.out.println("Codigo: " + producto.codigo);
                System.out.println("Stock disponible: " + producto.stock);
                System.out.println("Precio unitario : $" + producto.precio);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro un archivo maestro\n");
        } catch (IOException | ClassNotFoundException ex) {
        }
        System.out.println("===============================================================");
        System.out.println("                                Nombre del archivo:" + nombreMaestro + ".dat\n");
        try {
            bufferEntrada.close();
        } catch (IOException ex) {
        }
    }

    public static void eliminarMaestro(String maestroConst, String maestroMerge) {
        char respuesta;
        int p;
        File maestroC = new File(RUTA + maestroConst + ".dat");
        File maestroM = new File(RUTA + maestroMerge + ".dat");

        do {
            System.out.println("Que desea eliminar:\n"
                    + "1)Maestro constante \n"
                    + "2)Generado por el merge \n"
                    + "3)Ambos \n"
                    + "4)Finalizar \n");
            p = in.nextInt();
            switch (p) {
                case 1:
                    System.out.println("Seguro que quiere eliminar el archivo: " + maestroConst + " ? (S/N)");
                    in.nextLine();
                    respuesta = in.nextLine().charAt(0);
                    while (respuesta != 's' && respuesta != 'S' && respuesta != 'n' && respuesta != 'N') {
                        System.out.println("Ingresar respuesta valida (S/N)");
                        in.nextLine();
                        respuesta = in.nextLine().charAt(0);
                    }
                    if (respuesta == 's' || respuesta == 'S') {
                        maestroC.delete();
                        System.out.println("Se elimino el archivo " + maestroConst + "\n");
                    }
                    break;
                case 2:
                    System.out.println("Seguro que quiere eliminar el archivo: " + maestroMerge + " ? (S/N)");
                    in.nextLine();
                    respuesta = in.nextLine().charAt(0);
                    while (respuesta != 's' && respuesta != 'S' && respuesta != 'n' && respuesta != 'N') {
                        System.out.println("Ingresar respuesta valida (S/N)");
                        in.nextLine();
                        respuesta = in.nextLine().charAt(0);
                    }
                    if (respuesta == 's' || respuesta == 'S') {
                        maestroM.delete();
                        System.out.println("Se elimino el archivo: " + maestroMerge + "\n");
                    }
                    break;
                case 3:
                    System.out.println("Seguro que quiere eliminar los archivo: " + maestroConst + " y  " + maestroMerge + " ? (S/N)");
                    in.nextLine();
                    respuesta = in.nextLine().charAt(0);
                    while (respuesta != 's' && respuesta != 'S' && respuesta != 'n' && respuesta != 'N') {
                        System.out.println("Ingresar respuesta valida (S/N)");
                        in.nextLine();
                        respuesta = in.nextLine().charAt(0);
                    }
                    if (respuesta == 's' || respuesta == 'S') {
                        maestroC.delete();
                        maestroM.delete();
                        System.out.println("Se eliminaron los archivos: " + maestroConst + " y " + maestroMerge + "\n");
                    }
                    break;
                case 4:
                    System.out.println("No se elimino ningun archivo\n");
                    break;
                default:
                    System.out.println("Ingresar operacion valida");
            }
        } while (p != 4);
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
                    operacionesArchivos();
                    break;
                default:
                    System.out.println("Ingresar numero de operacion valido");
            }
        } while (respuesta != 5);
    }
}
