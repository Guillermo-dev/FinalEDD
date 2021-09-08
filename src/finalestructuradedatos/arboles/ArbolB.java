package finalestructuradedatos.arboles;

public class ArbolB {

    private NodoB raiz;

    public ArbolB() {
    }

    public NodoB getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoB raiz) {
        this.raiz = raiz;
    }

    public boolean isEmpty() {
        return this.getRaiz() == null;
    }

    public void recorridoInordenB(NodoB nodoActual) {
        if (nodoActual != null) {
            recorridoInordenB(nodoActual.getHijos()[0]);
            int i = 0;
            int j = 0;
            while (nodoActual.getDatos()[i] != null) {
                System.out.print(nodoActual.getDatos()[i].getValue() + ", ");
                if (nodoActual.getHijos()[j + 1] != null) {
                    recorridoInordenB(nodoActual.getHijos()[j + 1]);
                    j++;
                }
                i++;
            }
        }
    }

    public void recorrer() {
        if (isEmpty()) {
            System.out.println("El arbol esta vacio");
        } else if (this.getRaiz().bucketIsEmpty()) {
            System.out.println("El arbol esta vacio");
        } else {
            NodoB nodoActual = this.getRaiz();
            System.out.print("Recorrido Inorden arbolB: ");
            recorridoInordenB(nodoActual);
            System.out.println("");
        }
    }

    public NodoB valorMinimoB(NodoB nodoActual) {
        if (nodoActual.childsIsEmpty()) {
            return nodoActual;
        } else {
            nodoActual = nodoActual.getHijos()[0];
            return valorMinimoB(nodoActual);
        }
    }

    public int getMinimo() {
        NodoB nodoMinimo = valorMinimoB(this.raiz);
        return nodoMinimo.getDatos()[0].getValue();
    }

    public NodoB valorMaximoB(NodoB nodoActual) {
        for (int i = 4; i > 0; i--) {
            if (nodoActual.getHijos()[i] != null) {
                return valorMaximoB(nodoActual.getHijos()[i]);
            }
        }
        return nodoActual;
    }

    public int getMaximo() {
        NodoB nodoMaximo = valorMaximoB(this.getRaiz());
        int i = 0;
        while (nodoMaximo.getDatos()[i + 1] != null) {
            i++;
        }
        return nodoMaximo.getDatos()[i].getValue();
    }

    public static NodoB busqueda(NodoB nodoActual, int dato) {
        if (nodoActual == null) {
            return nodoActual;
        }
        int i = 0;
        while (nodoActual.getDatos()[i] != null) {
            if (nodoActual.getDatos()[i].getValue() == dato) {
                return nodoActual;
            }
            i++;
        }
        i = 0;
        while (nodoActual.getDatos()[i] != null) {
            if (nodoActual.getDatos()[i].getValue() > dato) {
                return busqueda(nodoActual.getHijos()[i], dato);
            }
            i++;
        }
        return busqueda(nodoActual.getHijos()[i], dato);
    }

    public boolean buscar(int dato) {
        return (busqueda(this.getRaiz(), dato) != null);
    }

    public boolean noEsRepetidoB(NodoB nodoRaiz, int dato) {
        return !(buscar(dato));
    }

    public NodoB buscarNodoParaInsertar(NodoB nodoActual, int dato) {
        if (nodoActual.childsIsNoEmpty()) {
            int i = 0;
            while (nodoActual.getDatos()[i] != null) {
                if (nodoActual.getDatos()[i].getValue() > dato) {
                    nodoActual = nodoActual.getHijos()[i];
                    return buscarNodoParaInsertar(nodoActual, dato);
                }
                i++;
            }
            return buscarNodoParaInsertar(nodoActual.getHijos()[i], dato);
        }
        return nodoActual;
    }

    public void insertarEnNodo(NodoB nodoActual, int dato) {
        int i = 0;
        while (nodoActual.getDatos()[i] != null) {
            i++;
        }
        Dato nuevoDato = new Dato(dato);
        nodoActual.setDato(i, nuevoDato);
        while (i > 0) {
            if (nodoActual.getDatos()[i].getValue() < nodoActual.getDatos()[i - 1].getValue()) {
                Dato aux = nodoActual.getDatos()[i - 1];
                nodoActual.setDato(i - 1, nodoActual.getDatos()[i]);
                nodoActual.setDato(i, aux);
            }
            i--;
        }
    }

    public boolean nodoLleno(Dato[] datos) {
        return (datos[4] != null);
    }

    public boolean noHayPadre(NodoB nodoActual) {
        return (this.getRaiz() == nodoActual);
    }

    public NodoB buscarPadre(NodoB nodoActual, NodoB nodoHijo) {
        int i = 0;
        while (nodoActual.getDatos()[i] != null) {
            if (nodoActual.getHijos()[i] == nodoHijo) {
                return nodoActual;
            }
            i++;
        }
        if (nodoActual.getHijos()[i] == nodoHijo) {
            return nodoActual;
        }
        int j = 0;
        while (nodoActual.getDatos()[j] != null) {
            if (nodoActual.getDatos()[j].getValue() > nodoHijo.getDatos()[0].getValue()) {
                return buscarPadre(nodoActual.getHijos()[j], nodoHijo);
            }
            j++;
        }
        return buscarPadre(nodoActual.getHijos()[j], nodoHijo);
    }

    public void solucionarOverflow(NodoB nodoActual, NodoB nodoAux) {
        NodoB nuevoNodo = new NodoB();
        nuevoNodo.setDato(0, new Dato(nodoActual.getDatos()[3].getValue()));
        nuevoNodo.setDato(1, new Dato(nodoActual.getDatos()[4].getValue()));
        nodoActual.setDato(3, null);
        nodoActual.setDato(4, null);

        nuevoNodo.setHijo(0, nodoActual.getHijos()[3]);
        nuevoNodo.setHijo(1, nodoActual.getHijos()[4]);
        nuevoNodo.setHijo(2, nodoAux);
        nodoActual.setHijo(3, null);
        nodoActual.setHijo(4, null);

        if (noHayPadre(nodoActual)) {
            NodoB nuevaRaiz = new NodoB();
            nuevaRaiz.setDato(0, new Dato(nodoActual.getDatos()[2].getValue()));
            nodoActual.setDato(2, null);
            nuevaRaiz.setHijo(0, this.raiz);
            nuevaRaiz.setHijo(1, nuevoNodo);
            this.setRaiz(nuevaRaiz);
        } else {
            NodoB nodoPadre = buscarPadre(this.getRaiz(), nodoActual);
            insertarEnNodo(nodoPadre, nodoActual.getDatos()[2].getValue());
            nodoActual.setDato(2, null);
            if (nodoLleno(nodoPadre.getDatos())) {
                solucionarOverflow(nodoPadre, nuevoNodo);
            } else {
                int i = 0;
                while (nodoPadre.getHijos()[i] != null) {
                    i++;
                }
                nodoPadre.getHijos()[i] = nuevoNodo;
                while (i > 0) {
                    if (nodoPadre.getHijos()[i].getDatos()[0].getValue() < nodoPadre.getHijos()[i - 1].getDatos()[0].getValue()) {
                        NodoB aux = nodoPadre.getHijos()[i - 1];
                        nodoPadre.setHijo(i - 1, nuevoNodo);
                        nodoPadre.setHijo(i, aux);
                    }
                    i--;
                }
            }
        }
    }

    public void insert(int dato) {
        NodoB nodoActual = this.getRaiz();
        if (this.isEmpty()) {
            NodoB nuevoNodo = new NodoB();
            Dato nuevoDato = new Dato(dato);
            nuevoNodo.setDato(0, nuevoDato);
            this.setRaiz(nuevoNodo);
        } else {
            if (noEsRepetidoB(nodoActual, dato)) {
                nodoActual = buscarNodoParaInsertar(nodoActual, dato);
                insertarEnNodo(nodoActual, dato);
                if (nodoLleno(nodoActual.getDatos())) {
                    solucionarOverflow(nodoActual, null);
                }
            } else {
                System.out.println("El dato (" + dato + ") ya se encuentra en el arbol y los arboles B no permiten claves duplicadas");
            }
        }
    }

    public boolean esNodoInterno(NodoB nodoActual) {
        return (nodoActual.childsIsNoEmpty());
    }

    public int cantidadElementos(NodoB nodoActual) {
        int i = 0;
        while (nodoActual.getDatos()[i] != null) {
            i++;
        }
        return i;
    }

    public void fusion(NodoB nodoPadre, NodoB nodoActual, NodoB hermanoIzq, NodoB hermanoDer) {
        if (hermanoIzq != null) {
            int i = 0;
            while (nodoPadre.getHijos()[i] != nodoActual) {
                i++;
            }
            insertarEnNodo(nodoActual, nodoPadre.getDatos()[i - 1].getValue());
            insertarEnNodo(nodoActual, hermanoIzq.getDatos()[0].getValue());
            insertarEnNodo(nodoActual, hermanoIzq.getDatos()[1].getValue());
            nodoActual.setHijo(3, nodoActual.getHijos()[1]);
            nodoActual.setHijo(4, nodoActual.getHijos()[2]);
            nodoActual.setHijo(0, nodoActual.getHijos()[0]);
            nodoActual.setHijo(1, nodoActual.getHijos()[1]);
            nodoActual.setHijo(2, nodoActual.getHijos()[2]);

            while (nodoPadre.getDatos()[i] != null) {
                nodoPadre.setDato(i - 1, nodoPadre.getDatos()[i]);
                i++;
            }
            nodoPadre.setDato(i - 1, null);

            i = 0;
            while (i < 4) {
                if (nodoPadre.getHijos()[i + 1] != null) {
                    if (nodoPadre.getHijos()[i].getDatos()[0].getValue() == nodoPadre.getHijos()[i + 1].getDatos()[0].getValue()) {
                        nodoPadre.setHijo(i, nodoPadre.getHijos()[i + 1]);
                        if (i < 3) {
                            nodoPadre.setHijo(i + 1, nodoPadre.getHijos()[i + 2]);
                        } else {
                            nodoPadre.setHijo(4, null);
                        }
                    }
                }
                i++;
            }

        } else {
            int i = 0;
            while (nodoPadre.getHijos()[i] != nodoActual) {
                i++;
            }
            insertarEnNodo(nodoActual, nodoPadre.getDatos()[i].getValue());
            insertarEnNodo(nodoActual, hermanoDer.getDatos()[0].getValue());
            insertarEnNodo(nodoActual, hermanoDer.getDatos()[1].getValue());
            nodoActual.setHijo(2, hermanoDer.getHijos()[0]);
            nodoActual.setHijo(3, hermanoDer.getHijos()[1]);
            nodoActual.setHijo(4, hermanoDer.getHijos()[2]);

            while (nodoPadre.getDatos()[i + 1] != null) {
                nodoPadre.setDato(i, nodoPadre.getDatos()[i + 1]);
                i++;
            }
            nodoPadre.setDato(i, null);

            i = 0;
            while (i < 4) {
                if (nodoPadre.getHijos()[i + 1] != null) {
                    if (nodoPadre.getHijos()[i].getDatos()[2] != null) {
                        if (nodoPadre.getHijos()[i].getDatos()[2].getValue() == nodoPadre.getHijos()[i + 1].getDatos()[0].getValue()) {
                            nodoPadre.setHijo(i + 1, nodoPadre.getHijos()[i + 2]);
                        }
                    } else {
                        if (nodoPadre.getHijos()[i] == nodoPadre.getHijos()[i + 1]) {
                            if (i < 3) {
                                nodoPadre.setHijo(i + 1, nodoPadre.getHijos()[i + 2]);
                            } else {
                                nodoPadre.setHijo(4, null);
                            }
                        }
                    }
                }
                i++;
            }
        }
        System.out.println("Fusion");
        if (nodoPadre.bucketIsEmpty()) {
            this.setRaiz(nodoPadre.getHijos()[0]);
        } else if (!(noHayPadre(nodoPadre))) {
            if (cantidadElementos(nodoPadre) < 2) {
                transferencia(buscarPadre(this.getRaiz(), nodoPadre), nodoPadre);
            }
        }
    }

    public void transferencia(NodoB nodoPadre, NodoB nodoActual) {
        int i = 0;
        int cantHI = 0;
        int cantHD = 0;
        NodoB hermanoIzq = null;
        NodoB hermanoDer = null;
        while (nodoPadre.getHijos()[i] != nodoActual) {
            i++;
        }
        if (i != 0) {
            if (nodoPadre.getHijos()[i - 1] != null) {
                hermanoIzq = nodoPadre.getHijos()[i - 1];
                cantHI = cantidadElementos(hermanoIzq);
            }
        }
        if (i != 4) {
            if (nodoPadre.getHijos()[i + 1] != null) {
                hermanoDer = nodoPadre.getHijos()[i + 1];
                cantHD = cantidadElementos(hermanoDer);
            }
        }
        if (cantHI > 2 || cantHD > 2) {
            if (cantHI > cantHD) {
                insertarEnNodo(nodoActual, nodoPadre.getDatos()[i - 1].getValue());
                nodoPadre.setDato(i - 1, hermanoIzq.getDatos()[cantHI - 1]);
                hermanoIzq.setDato(cantHI - 1, null);
                System.out.println("Transferencia con hermano izquierdo");
            } else {
                insertarEnNodo(nodoActual, nodoPadre.getDatos()[i].getValue());
                nodoPadre.setDato(i, hermanoDer.getDatos()[0]);
                i = 0;
                while (hermanoDer.getDatos()[i + 1] != null) {
                    hermanoDer.setDato(i, hermanoDer.getDatos()[i + 1]);
                    i++;
                }
                hermanoDer.setDato(i, null);
                System.out.println("Transferencia con hermano derecho");
            }
        } else {
            fusion(nodoPadre, nodoActual, hermanoIzq, hermanoDer);
        }
    }

    public void borrarElemento(NodoB nodoAEliminar, int dato) {
        if (esNodoInterno(nodoAEliminar)) {
            int i = 0;
            while (nodoAEliminar.getDatos()[i].getValue() != dato) {
                i++;
            }
            NodoB nodoHI = nodoAEliminar.getHijos()[i];
            NodoB nodoHD = nodoAEliminar.getHijos()[i + 1];
            int cantHI = cantidadElementos(nodoHI);
            int cantHD = cantidadElementos(nodoHD);

            if (cantHI > cantHD) {
                nodoAEliminar.setDato(i, nodoHI.getDatos()[cantHI]);
                nodoHI.getDatos()[cantHI].setValue(dato);
                borrarElemento(nodoHI, dato);
            } else {
                nodoAEliminar.setDato(i, nodoHD.getDatos()[0]);
                nodoHD.getDatos()[0].setValue(dato);
                borrarElemento(nodoHD, dato);
            }
        } else {
            int i = 0;
            while (nodoAEliminar.getDatos()[i].getValue() != dato) {
                i++;
            }
            while (nodoAEliminar.getDatos()[i + 1] != null) {
                nodoAEliminar.setDato(i, nodoAEliminar.getDatos()[i + 1]);
                nodoAEliminar.getDatos()[i + 1].setValue(dato);
                i++;
            }
            nodoAEliminar.setDato(i, null);
            if (!(noHayPadre(nodoAEliminar))) {
                if (cantidadElementos(nodoAEliminar) < 2) {
                    NodoB nodoPadre = buscarPadre(this.getRaiz(), nodoAEliminar);
                    transferencia(nodoPadre, nodoAEliminar);
                }
            }
        }
    }

    public void delete(int dato) {
        if (!buscar(dato)) {
            System.out.println("El dato que desea eliminar no se encuentra en el arbol");
        } else {
            NodoB nodoAEliminar = busqueda(this.getRaiz(), dato);
            borrarElemento(nodoAEliminar, dato);
        }
    }
}
