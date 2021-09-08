package finalestructuradedatos.arboles;

public class ArbolBinario {

    private Nodo raiz;

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
            Nodo nodoActual = this.getRaiz();
            System.out.print("Recorrido Inorden arbolAVL: ");
            recorridoInorden(nodoActual);
            System.out.println("");
        }
    }

    public Nodo valorMinimo(Nodo nodoActual) {
        if (nodoActual.hiNoExist()) {
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
        if (nodoActual.hdNoExist()) {
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
            if (nodoActual.hiNoExist()) {
                return nodoActual;
            } else {
                return buscarPadreNuevoNodo(nodoActual.getHi(), dato);
            }
        } else {
            if (nodoActual.hdNoExist()) {
                return nodoActual;
            } else {
                return buscarPadreNuevoNodo(nodoActual.getHd(), dato);
            }
        }
    }

    public Nodo busquedaPadre(Nodo nodoActual, int dato) {
        if (nodoActual.getDato() == dato) {
            return nodoActual;
        } else {
            if (nodoActual.hiExist() && nodoActual.getHi().getDato() == dato) {
                return nodoActual;
            } else if (nodoActual.hdExist() && nodoActual.getHd().getDato() == dato) {
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
        if (nodoAEliminar.hiNoExist() && nodoAEliminar.hdNoExist()) {
            if (nodoPadre.getDato() >= nodoAEliminar.getDato()) {//>= por elimincacion de raiz con predecesor
                nodoPadre.setHi(null);
            } else if (nodoPadre.getDato() < nodoAEliminar.getDato()) {
                nodoPadre.setHd(null);
            }
        } else {
            if (nodoAEliminar.hiExist() && nodoAEliminar.hdNoExist()) {
                if (nodoPadre.getDato() == nodoAEliminar.getDato()) {
                    nodoPadre = nodoPadre.getHi();
                } else if (nodoPadre.getDato() > nodoAEliminar.getDato()) {
                    nodoPadre.setHi(nodoAEliminar.getHi());
                } else if (nodoPadre.getDato() < nodoAEliminar.getDato()) {
                    nodoPadre.setHd(nodoAEliminar.getHi());
                }
            } else {
                if (nodoAEliminar.hiNoExist() && nodoAEliminar.hdExist()) {
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
}
