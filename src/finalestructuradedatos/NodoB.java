package finalestructuradedatos;

public class NodoB {

    private NodoB[] hijos = new NodoB[5];
    private Dato[] datos = new Dato[5];//para que java lo inicialice los datos en 0 y no tener un valor reservado innecesario

    public NodoB() {
    }

    public NodoB[] getHijos() {
        return hijos;
    }

    public void setHijos(NodoB[] hijos) {
        this.hijos = hijos;
    }

    public Dato[] getDatos() {
        return datos;
    }

    public void setDatos(Dato[] datos) {
        this.datos = datos;
    }

    public void setDato(int position, Dato dato) {
        this.datos[position] = dato;
    }

    public void setHijo(int position, NodoB hijo) {
        this.hijos[position] = hijo;
    }

    public boolean bucketIsEmpty() {
        return this.getDatos()[0] == null;
    }

    public boolean childsIsEmpty() {
        return this.getHijos()[0] == null;
    }

    public boolean childsIsNoEmpty() {
        return this.getHijos()[0] != null;
    }
}
