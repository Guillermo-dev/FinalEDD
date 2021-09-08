package finalestructuradedatos.arboles;

public class ArbolABB extends ArbolBinario {

    public ArbolABB() {
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

    public void delete(int dato) {
        if (!buscar(dato)) {
            System.out.println("El dato que desea eliminar no se encuentra en el arbol");
        } else {
            Nodo nodoAEliminar = busqueda(this.getRaiz(), dato);
            Nodo nodoPadre = busquedaPadre(this.getRaiz(), dato);
            if (nodoAEliminar == nodoPadre && nodoAEliminar.hiExist() && nodoAEliminar.hdNoExist()) {
                setRaiz(null);
                System.out.println("El nodo con la clave: " + dato + " se elimino con exito");
            } else {
                eliminarNodo(nodoAEliminar, nodoPadre);
                System.out.println("El nodo con la clave: " + dato + " se elimino con exito");
            }
        }
    }
}
