package finalestructuradedatos;

import finalestructuradedatos.arboles.ArbolB;
import finalestructuradedatos.arboles.ArbolABB;
import finalestructuradedatos.arboles.ArbolAVL;
import finalestructuradedatos.arboles.Arboles;
import finalestructuradedatos.archivos.Archivos;
import java.util.Scanner;

public class FinalEstructuraDeDatos {

    static Scanner in = new Scanner(System.in);

    public static void menu() {
        System.out.println("=======================ARBOLES=======================\n"
                + "1)ABB (enteros)\n"
                + "2)AVL (enteros)\n"
                + "3)B M=5(enteros)\n"
                + "=======================ARCHIVOS=======================\n"
                + "4)Archivos (stock de productos para la venta)\n"
                + "======================================================\n");
    }

    public static void main(String[] args) {
        int respuesta;

        do {
            menu();
            System.out.println("Ingresar numero de operacion (5 para finalizar)");
            try {
                respuesta = in.nextInt();
            } catch (Exception e) {
                respuesta = -1;
                in.nextLine();
            }
            switch (respuesta) {
                case 1:
                    ArbolABB arbolABB = new ArbolABB();
                    Arboles.operacionesArbolABB(arbolABB);
                    break;
                case 2:
                    ArbolAVL arbolAVL = new ArbolAVL();
                    Arboles.operacionesArbolAVL(arbolAVL);
                    break;
                case 3:
                    ArbolB arbolB = new ArbolB();
                    Arboles.operacionesArbolB(arbolB);
                    break;
                case 4:
                    Archivos.operacionesArchivos();
                    break;
                default:
                    System.out.println("Ingresar numero de operacion valido");
            }
        } while (respuesta != 5);
    }
}
