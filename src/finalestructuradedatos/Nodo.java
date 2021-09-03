package finalestructuradedatos;

public class Nodo {

    private int dato;
    private Nodo hi;
    private Nodo hd;

    //para usar en arbol avl
    private int fe;

    public Nodo(int dato) {
        this.dato = dato;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getHi() {
        return hi;
    }

    public void setHi(Nodo hi) {
        this.hi = hi;
    }

    public Nodo getHd() {
        return hd;
    }

    public void setHd(Nodo hd) {
        this.hd = hd;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public boolean hiNoExist() {
        return this.getHi() == null;
    }

    public boolean hdNoExist() {
        return this.getHd() == null;
    }

    public boolean hiExist() {
        return this.getHi() != null;
    }

    public boolean hdExist() {
        return this.getHd() != null;
    }
}
