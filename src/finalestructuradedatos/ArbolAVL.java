package finalestructuradedatos;

public class ArbolAVL extends ArbolBinario {

    static class Bool {

        public boolean desbalanceado;
    }

    public ArbolAVL() {
    }

    public Nodo buscarNodoPivote(Nodo nodoRaiz, int dato) {
        Nodo nodoPivote = busquedaPadre(nodoRaiz, dato);
        if (nodoPivote.getFe() != 0) {
            return nodoPivote;
        } else if (nodoRaiz == nodoPivote) {
            return nodoRaiz;
        } else {
            return buscarNodoPivote(nodoRaiz, nodoPivote.getDato());
        }
    }

    public int calcularAltura(Nodo nodoActual) {
        if (nodoActual == null) {
            return 0;
        } else {
            if (nodoActual.hiExist() && nodoActual.hdExist()) {
                if (calcularAltura(nodoActual.getHi()) > calcularAltura(nodoActual.getHd())) {
                    return calcularAltura(nodoActual.getHi()) + 1;
                } else {
                    return calcularAltura(nodoActual.getHd()) + 1;
                }
            } else if (nodoActual.hiExist() && nodoActual.hdNoExist()) {
                return calcularAltura(nodoActual.getHi()) + 1;
            } else if (nodoActual.hiNoExist() && nodoActual.hdExist()) {
                return calcularAltura(nodoActual.getHd()) + 1;
            } else {
                return 1;
            }
        }
    }

    public void calcularFE(Nodo nodoActual) {
        if (nodoActual != null) {
            calcularFE(nodoActual.getHi());
            System.out.print(nodoActual.getDato() + ", ");
            nodoActual.setFe(calcularAltura(nodoActual.getHd()) - calcularAltura(nodoActual.getHi()));
            System.out.println("FE:" + nodoActual.getFe());
            calcularFE(nodoActual.getHd());
        }
    }

    public void estaDesvalanceado(Nodo nodoActual, Bool estado) {
        if (nodoActual != null) {
            if (nodoActual.getFe() < -1 || nodoActual.getFe() > 1) {
                estado.desbalanceado = true;
            }
            estaDesvalanceado(nodoActual.getHi(), estado);
            estaDesvalanceado(nodoActual.getHd(), estado);
        }
    }

    public void rsi(ArbolAVL arbolAVL, Nodo nodoPivote) {
        Nodo hijoPivote;
        Nodo padrePivote;

        hijoPivote = nodoPivote.getHd();
        nodoPivote.setHd(hijoPivote.getHi());
        nodoPivote.setHi(hijoPivote);
        if (this.getRaiz() == nodoPivote) {
            this.setRaiz(hijoPivote);
        } else {
            padrePivote = busquedaPadre(this.getRaiz(), nodoPivote.getDato());
            if (padrePivote.getHi() == nodoPivote) {
                padrePivote.setHi(hijoPivote);
            } else {
                padrePivote.setHd(hijoPivote);
            }
        }

    }

    public void rsd(ArbolAVL arbolAVL, Nodo nodoPivote) {
        Nodo hijoPivote;
        Nodo padrePivote;

        hijoPivote = nodoPivote.getHi();
        nodoPivote.setHi(hijoPivote.getHd());
        nodoPivote.setHd(hijoPivote);
        if (this.getRaiz() == nodoPivote) {
            this.setRaiz(hijoPivote);
        } else {
            padrePivote = busquedaPadre(this.getRaiz(), nodoPivote.getDato());
            if (padrePivote.getHi() == nodoPivote) {
                padrePivote.setHi(hijoPivote);
            } else {
                padrePivote.setHd(hijoPivote);
            }
        }
    }

    public void hacerRotacion(ArbolAVL arbolAVL, Nodo nodoPivote) {
        if (nodoPivote.getFe() > 1 && nodoPivote.getHd().getFe() >= 0) {//">=0" para cuadno elimina
            rsi(arbolAVL, nodoPivote);
            System.out.println("RSI");
        } else if (nodoPivote.getFe() < 1 && nodoPivote.getHi().getFe() <= 0) {//"<=0" para cuadno elimina
            rsd(arbolAVL, nodoPivote);
            System.out.println("RSD");
        } else if (nodoPivote.getFe() < 1 && nodoPivote.getHi().getFe() >= 1) {
            rsi(arbolAVL, nodoPivote.getHi());
            rsd(arbolAVL, nodoPivote);
            System.out.println("RDI");
        } else if (nodoPivote.getFe() > 1 && nodoPivote.getHd().getFe() <= 1) {
            rsd(arbolAVL, nodoPivote.getHd());
            rsi(arbolAVL, nodoPivote);
            System.out.println("RDD");
        }
    }

    public void balanceo(ArbolAVL arbolAVL, Nodo nodoPivote, Bool estado) {
        System.out.println("Pivote: " + nodoPivote.getDato() + " ");
        calcularFE(this.getRaiz());
        System.out.println("RAIZ:" + this.getRaiz().getDato());
        estaDesvalanceado(this.getRaiz(), estado);
        if (estado.desbalanceado) {
            hacerRotacion(arbolAVL, nodoPivote);
            calcularFE(this.getRaiz());
            System.out.println("RAIZ:" + this.getRaiz().getDato());
        } else {
            System.out.println("NO esta desbalanceado");
        }
    }

    public void insert(int dato) {
        Nodo nodoActual = this.getRaiz();
        Bool estado = new Bool();
        if (noEsRepetido(dato)) {
            Nodo nuevoNodo = new Nodo(dato);
            if (this.isEmpty()) {
                this.setRaiz(nuevoNodo);
                this.getRaiz().setFe(0);
            } else {
                nodoActual = buscarPadreNuevoNodo(nodoActual, dato);
                if (nodoActual.getDato() > dato) {
                    nodoActual.setHi(nuevoNodo);
                } else {
                    nodoActual.setHd(nuevoNodo);
                }
                Nodo nodoPivote = buscarNodoPivote(this.getRaiz(), nuevoNodo.getDato());
                balanceo(this, nodoPivote, estado);

            }
        } else {
            System.out.println("Ese dato (" + dato + ") ya se encuentra en el arbol y los arboles AVL no permiten claves duplicadas");
        }
    }

    public void delete(int dato) {
        Bool estado = new Bool();
        if (!buscar(dato)) {
            System.out.println("El dato que desea eliminar no se encuentra en el arbol");
        } else {
            Nodo nodoAEliminar = busqueda(this.getRaiz(), dato);
            Nodo nodoPadre = busquedaPadre(this.getRaiz(), dato);
            Nodo nodoPivote = buscarNodoPivote(this.getRaiz(), nodoAEliminar.getDato());
            if (nodoAEliminar == nodoPadre && nodoAEliminar.hiNoExist() && nodoAEliminar.hdNoExist()) {
                this.setRaiz(null);
                System.out.println("El nodo con la clave: " + dato + " se elimino con exito");
            } else {
                eliminarNodo(nodoAEliminar, nodoPadre);
                System.out.println("El nodo con la clave: " + dato + " se elimino con exito");
            }
            balanceo(this, nodoPivote, estado);
        }
    }

}
