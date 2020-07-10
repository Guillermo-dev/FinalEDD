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

    public static void insertarABB(ArbolABB arbolABB, int dato) {
        Nodo nodoActual = arbolABB.raiz;
        if (noEsRepetido(nodoActual, dato)) {
            Nodo nuevoNodo = new Nodo();
            nuevoNodo.dato = dato;
            if (arbolABB.raiz == null) {
                arbolABB.raiz = nuevoNodo;
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
            System.out.println("El dato " + dato + " no se encuentra en el arbol");
        }
    }

    public static void insertarAVL(ArbolAVL arbolAVL, int dato) {
        Nodo nodoActual = arbolAVL.raiz;
        if (noEsRepetido(nodoActual, dato)) {
            Nodo nuevoNodo = new Nodo();
            nuevoNodo.dato = dato;
            if (arbolAVL.raiz == null) {
                arbolAVL.raiz = nuevoNodo;
                arbolAVL.raiz.fe = 0;
            } else {
                nodoActual = espacioDisponibleABB(nodoActual, dato);
                if (nodoActual.dato > dato) {
                    nodoActual.hi = nuevoNodo;
                } else {
                    nodoActual.hd = nuevoNodo;
                }
                Nodo nodoPivote = buscarNodoPivote(arbolAVL.raiz, nuevoNodo.dato);
                if (nodoPivote.fe != 0) {
                    calcularFE(arbolAVL.raiz);
                }else{
                    calcularFE(arbolAVL.raiz);
                }
            }
        } else {
            System.out.println("Ese dato ya se encuentra en el arbol y los arboles ABB no permiten claves duplicadas");
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
            System.out.println("FE:"+ nodoActual.fe);
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

    public static void borrarAVL(ArbolAVL arbolAVL, int dato) {

    }

    public static void operacionesArbolB() {

    }

    public static void main(String[] args) {
        int respuesta;

        do {
            menu();
            System.out.println("Ingresar numero de operacion (7 para finalizar)");
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

                    operacionesArbolB();
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
