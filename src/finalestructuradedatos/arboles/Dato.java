package finalestructuradedatos.arboles;

//para que java lo inicialice los datos en 0 y no tener un valor reservado innecesario
public class Dato {

    public Dato(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
