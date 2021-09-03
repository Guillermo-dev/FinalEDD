package finalestructuradedatos;

public class ArbolABB {

    private Nodo raiz;

    public ArbolABB() {
    }

    public Nodo getRaiz() {
        return this.raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public void recorridoInorden(Nodo nodoActual) {
        if (nodoActual != null) {
            recorridoInorden(nodoActual.getHi());
            System.out.print(nodoActual.getDato() + ", ");
            recorridoInorden(nodoActual.getHd());
        }
    }

    public void recorrer() {
        if (this.isEmpty()) {
            System.out.println("El arbol esta vasio");
        } else {
            Nodo nodoActual = getRaiz();
            System.out.print("Recorrido Inorden arbolABB: ");
            recorridoInorden(nodoActual);
            System.out.println("");
        }
    }

    public Nodo valorMinimo(Nodo nodoActual) {
        if (nodoActual.getHi() == null) {
            return nodoActual;
        } else {
            nodoActual = nodoActual.getHi();
            return valorMinimo(nodoActual);
        }
    }

    public int getMinimo() {
        Nodo nodoMinimo = valorMinimo(this.getRaiz());
        return nodoMinimo.getDato();
    }

    public Nodo valorMaximo(Nodo nodoActual) {
        if (nodoActual.getHd() == null) {
            return nodoActual;
        } else {
            nodoActual = nodoActual.getHd();
            return valorMaximo(nodoActual);
        }
    }

    public int getMaximo() {
        Nodo nodoMayor = valorMaximo(this.getRaiz());
        return nodoMayor.getDato();
    }

    public Nodo busqueda(Nodo nodoActual, int dato) {
        if (nodoActual == null || nodoActual.getDato() == dato) {
            return nodoActual;
        } else {
            if (nodoActual.getDato() > dato) {
                nodoActual = nodoActual.getHi();
                return busqueda(nodoActual, dato);
            } else {
                nodoActual = nodoActual.getHd();
                return busqueda(nodoActual, dato);
            }
        }
    }

    public boolean buscar(int aBuscar) {
        return busqueda(this.getRaiz(), aBuscar) != null;
    }

    public boolean noEsRepetido(int dato) {
        return !(buscar(dato));
    }

    public static Nodo buscarPadreNuevoNodo(Nodo nodoActual, int dato) {
        if (nodoActual.getDato() > dato) {
            if (nodoActual.getHi() == null) {
                return nodoActual;
            } else {
                return buscarPadreNuevoNodo(nodoActual.getHi(), dato);
            }
        } else {
            if (nodoActual.getHd() == null) {
                return nodoActual;
            } else {
                return buscarPadreNuevoNodo(nodoActual.getHd(), dato);
            }
        }
    }

    public void insert(int dato) {
        Nodo nodoActual = this.getRaiz();
        if (noEsRepetido(dato)) {
            Nodo nuevoNodo = new Nodo(dato);
            if (this.isEmpty()) {
                this.setRaiz(nuevoNodo);
            } else {
                nodoActual = buscarPadreNuevoNodo(nodoActual, dato);
                if (nodoActual.getDato() > dato) {
                    nodoActual.setHi(nuevoNodo);
                } else {
                    nodoActual.setHd(nuevoNodo);
                }
            }
        } else {
            System.out.println("El dato (" + dato + ") ya se encuentra en el arbol y los arboles ABB no permiten claves duplicadas");
        }
    }

    public Nodo busquedaPadre(Nodo nodoActual, int dato) {
        if (nodoActual.getDato() == dato) {
            return nodoActual;
        } else {
            if (nodoActual.getHi() != null && nodoActual.getHi().getDato() == dato) {
                return nodoActual;
            } else if (nodoActual.getHd() != null && nodoActual.getHd().getDato() == dato) {
                return nodoActual;
            } else {
                if (nodoActual.getDato() > dato) {
                    nodoActual = nodoActual.getHi();
                    return busquedaPadre(nodoActual, dato);
                } else {
                    nodoActual = nodoActual.getHd();
                    return busquedaPadre(nodoActual, dato);
                }
            }
        }
    }

    public void eliminarNodo(Nodo nodoAEliminar, Nodo nodoPadre) {
        if (nodoAEliminar.getHi() == null && nodoAEliminar.getHd() == null) {
            if (nodoPadre.getDato() >= nodoAEliminar.getDato()) {//>= por elimincacion de raiz con predecesor
                nodoPadre.setHi(null);
            } else if (nodoPadre.getDato() < nodoAEliminar.getDato()) {
                nodoPadre.setHd(null);
            }
        } else {
            if (nodoAEliminar.getHi() != null && nodoAEliminar.getHd() == null) {
                if (nodoPadre.getDato() == nodoAEliminar.getDato()) {
                    nodoPadre = nodoPadre.getHi();
                } else if (nodoPadre.getDato() > nodoAEliminar.getDato()) {
                    nodoPadre.setHi(nodoAEliminar.getHi());
                } else if (nodoPadre.getDato() < nodoAEliminar.getDato()) {
                    nodoPadre.setHd(nodoAEliminar.getHi());
                }
            } else {
                if (nodoAEliminar.getHi() == null && nodoAEliminar.getHd() != null) {
                    if (nodoPadre.getDato() == nodoAEliminar.getDato()) {
                        nodoPadre = nodoPadre.getHd();
                    } else if (nodoPadre.getDato() >= nodoAEliminar.getDato()) {
                        nodoPadre.setHi(nodoAEliminar.getHd());
                    } else if (nodoPadre.getDato() < nodoAEliminar.getDato()) {
                        nodoPadre.setHd(nodoAEliminar.getHd()); 
                    }
                } else {
                    Nodo nodoPredecesor = valorMinimo(nodoAEliminar);
                    Nodo nodoPadrePredecesor = busquedaPadre(nodoAEliminar, nodoPredecesor.getDato());
                    nodoAEliminar.setDato(nodoPredecesor.getFe());
                    eliminarNodo(nodoPredecesor, nodoPadrePredecesor);
                }
            }
        }
    }

    public void delete(int dato) {
        if (!buscar(dato)) {
            System.out.println("El dato que desea eliminar no se encuentra en el arbol");
        } else {
            Nodo nodoAEliminar = busqueda(this.getRaiz(), dato);
            Nodo nodoPadre = busquedaPadre(this.getRaiz(), dato);
            if (nodoAEliminar == nodoPadre && nodoAEliminar.getHi() == null && nodoAEliminar.getHd() == null) {
                setRaiz(null);
                System.out.println("El nodo con la clave: " + dato + " se elimino con exito");
            } else {
                eliminarNodo(nodoAEliminar, nodoPadre);
                System.out.println("El nodo con la clave: " + dato + " se elimino con exito");
            }
        }
    }
}
