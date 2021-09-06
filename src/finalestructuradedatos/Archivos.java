package finalestructuradedatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Archivos {

    //CONSTANTES
    static Scanner in = new Scanner(System.in);
    static String RUTA = "";
    static int CANT_PRODUCTOS = 4;
    static String[] NOMBRES = {"Producto 1", "Producto 2", "Producto 3", "Producto 4", "Producto 5"};
    static int[] CODIGOS = {0001, 0002, 0003, 0004, 0005};
    static float[] PRECIOS = {100, 200, 150, 500, 350};
    static int[] STOCK = {1000, 1000, 1000, 1000, 1000};

    public static void menuArchivos() {
        System.out.println("1)Generar archivo maestro (constante)\n"
                + "2)Actualizar archivo maestro (constatnte) con un archivo detalle\n"
                + "3)Merge\n"
                + "4)Corte de Control (constatnte)\n"
                + "5)Corte de control (Merge)\n"
                + "6)Eliminar maestro (Constante y/o merge)\n");
    }

    public static void operacionesArchivos() {
        int respuesta;
        String nombreMaestro = "";
        String nombreMaestro2 = "";
        in.nextLine();
        int p;

        do {
            menuArchivos();
            System.out.println("Ingresar numero de operacion (7 para finalizar)");
            respuesta = in.nextInt();
            switch (respuesta) {
                case 1:
                    System.out.println("Ingresar el nombre que quiera que tenga el archivo");
                    in.nextLine();
                    nombreMaestro = in.nextLine();
                    generarMaestro(nombreMaestro);
                    break;
                case 2:
                    generarDetalle("Detalle", nombreMaestro);
                    actualizarMaestro(nombreMaestro, "Detalle");
                    break;
                case 3:
                    in.nextLine();
                    generarDetalleMerge("Detalle1");
                    generarDetalleMerge("Detalle2");
                    generarDetalleMerge("Detalle3");
                    System.out.println("Ingresar nombre para el archivo maestro");
                    nombreMaestro2 = in.nextLine();
                    merge("Detalle1", "Detalle2", "Detalle3", nombreMaestro2 + "Merge");
                    break;
                case 4:
                    corteDeControl(nombreMaestro);
                    break;
                case 5:
                    corteControlMerge(nombreMaestro2 + "Merge");
                    break;
                case 6:
                    do {

                        System.out.println("Que desea eliminar:\n"
                                + "1)Maestro constante \n"
                                + "2)Generado por el merge \n"
                                + "3)Ambos \n"
                                + "4)Finalizar \n");
                        p = in.nextInt();
                        switch (p) {
                            case 1:
                                System.out.println("Seguro que quiere eliminar el archivo: " + nombreMaestro + " ? (S/N)");
                                in.nextLine();
                                respuesta = in.nextLine().charAt(0);
                                while (respuesta != 's' && respuesta != 'S' && respuesta != 'n' && respuesta != 'N') {
                                    System.out.println("Ingresar respuesta valida (S/N)");
                                    in.nextLine();
                                    respuesta = in.nextLine().charAt(0);
                                }
                                if (respuesta == 's' || respuesta == 'S') {
                                    eliminarMaestro(nombreMaestro);
                                }
                                break;
                            case 2:
                                System.out.println("Seguro que quiere eliminar el archivo: " + nombreMaestro2 + " ? (S/N)");
                                in.nextLine();
                                respuesta = in.nextLine().charAt(0);
                                while (respuesta != 's' && respuesta != 'S' && respuesta != 'n' && respuesta != 'N') {
                                    System.out.println("Ingresar respuesta valida (S/N)");
                                    in.nextLine();
                                    respuesta = in.nextLine().charAt(0);
                                }
                                if (respuesta == 's' || respuesta == 'S') {
                                    eliminarMaestro(nombreMaestro2 + "Merge");
                                }
                                break;
                            case 3:
                                System.out.println("Seguro que quiere eliminar los archivo: " + nombreMaestro + " y  " + nombreMaestro2 + " ? (S/N)");
                                in.nextLine();
                                respuesta = in.nextLine().charAt(0);
                                while (respuesta != 's' && respuesta != 'S' && respuesta != 'n' && respuesta != 'N') {
                                    System.out.println("Ingresar respuesta valida (S/N)");
                                    in.nextLine();
                                    respuesta = in.nextLine().charAt(0);
                                }
                                if (respuesta == 's' || respuesta == 'S') {
                                    eliminarMaestro(nombreMaestro);
                                    eliminarMaestro(nombreMaestro2 + "Merge");
                                }
                                break;
                            case 4:
                                System.out.println("No se elimino ningun archivo\n");
                                break;
                            default:
                                System.out.println("Ingresar operacion valida");
                        }
                    } while (p != 4);
                    break;
                default:
                    System.out.println("Ingresar operacion valida");
            }
        } while (respuesta != 7);
    }

    public static void generarMaestro(String nombreMaestro) {
        FileOutputStream maestro;
        ObjectOutputStream bufferSalida;

        try {

            maestro = new FileOutputStream(RUTA + nombreMaestro + ".dat");
            bufferSalida = new ObjectOutputStream(maestro);

            for (int i = 0; i < CANT_PRODUCTOS; i++) {

                Producto nuevoProducto = new Producto();
                nuevoProducto.nombre = NOMBRES[i];
                nuevoProducto.codigo = CODIGOS[i];
                nuevoProducto.precio = PRECIOS[i];
                nuevoProducto.stock = STOCK[i];

                bufferSalida.writeObject(nuevoProducto);
            }

            bufferSalida.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro un archivo con el nombre " + nombreMaestro + ".dat");
        } catch (IOException ex) {
        }
    }

    public static void generarDetalle(String nombreDetalle, String nombreMaestro) {
        char respuesta;
        FileOutputStream detalle;
        ObjectOutputStream bufferSalida;

        try {
            FileInputStream maestro = new FileInputStream(RUTA + nombreMaestro + ".dat");
            detalle = new FileOutputStream(RUTA + nombreDetalle + ".dat");
            bufferSalida = new ObjectOutputStream(detalle);

            do {

                Venta nuevaVenta = new Venta();
                System.out.println("Ingresar el codigo del producto que se vendio (0001 a 0004)");
                nuevaVenta.codigo = in.nextInt();
                while (nuevaVenta.codigo != 0001 && nuevaVenta.codigo != 0002 && nuevaVenta.codigo != 0003 && nuevaVenta.codigo != 0004) {
                    System.out.println("Ingresar el codigo del producto que se vendio (0001 a 0004)");
                    nuevaVenta.codigo = in.nextInt();
                }
                System.out.println("Ingresar la cantidad de elementos vendidos");
                nuevaVenta.cantidadVendida = in.nextInt();
                while (cantidadExedida(nuevaVenta.cantidadVendida, nombreMaestro)) {
                    System.out.println("Ingresar la cantidad de elementos vendidos");
                    nuevaVenta.cantidadVendida = in.nextInt();
                }

                bufferSalida.writeObject(nuevaVenta);

                System.out.println("Desea ingresar un nuevo prroducto?(S/N)");
                in.nextLine();
                respuesta = in.nextLine().charAt(0);
                while (respuesta != 's' && respuesta != 'S' && respuesta != 'n' && respuesta != 'N') {
                    System.out.println("Desea ingresar un nuevo prroducto?(S/N)");
                    in.nextLine();
                    respuesta = in.nextLine().charAt(0);
                }

            } while (respuesta == 's' || respuesta == 'S');

            bufferSalida.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public static boolean cantidadExedida(int cantidadVendida, String nombreMaestro) {
        FileInputStream maestro;
        ObjectInputStream bufferEntrada;

        try {
            maestro = new FileInputStream(RUTA + nombreMaestro + ".dat");
            bufferEntrada = new ObjectInputStream(maestro);

            Producto producto = (Producto) bufferEntrada.readObject();
            return producto.stock < cantidadVendida;

        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {
        }
        return true;
    }

    public static void actualizarMaestro(String nombreMaestro, String nombreDetalle) {
        FileInputStream maestro;
        FileInputStream detalle;
        ObjectInputStream bufferEntradaDet;

        try {

            detalle = new FileInputStream(RUTA + nombreDetalle + ".dat");
            bufferEntradaDet = new ObjectInputStream(detalle);

            for (int j = 0; j < CANT_PRODUCTOS; j++) {

                maestro = new FileInputStream(RUTA + nombreMaestro + ".dat");
                ObjectInputStream bufferEntradaMas = new ObjectInputStream(maestro);

                Venta venta = (Venta) bufferEntradaDet.readObject();

                FileOutputStream nuevoMaestro = new FileOutputStream(RUTA + nombreMaestro + "2.dat");
                ObjectOutputStream bufferSalida = new ObjectOutputStream(nuevoMaestro);

                for (int i = 0; i < CANT_PRODUCTOS; i++) {

                    Producto producto = (Producto) bufferEntradaMas.readObject();

                    if (venta.codigo == producto.codigo) {
                        producto.stock -= venta.cantidadVendida;
                    }
                    Producto nuevoProducto = new Producto();
                    nuevoProducto.nombre = producto.nombre;
                    nuevoProducto.codigo = producto.codigo;
                    nuevoProducto.precio = producto.precio;
                    nuevoProducto.stock = producto.stock;
                    bufferSalida.writeObject(nuevoProducto);

                }

                File viejoMaestro = new File(RUTA + nombreMaestro + ".dat");
                viejoMaestro.delete();

                FileInputStream maestro2 = new FileInputStream(RUTA + nombreMaestro + "2.dat");
                ObjectInputStream bufferEntradaMas2 = new ObjectInputStream(maestro2);

                FileOutputStream nuevoMaestro2 = new FileOutputStream(RUTA + nombreMaestro + ".dat");
                ObjectOutputStream bufferSalida2 = new ObjectOutputStream(nuevoMaestro2);

                for (int i = 0; i < CANT_PRODUCTOS; i++) {

                    Producto producto = (Producto) bufferEntradaMas2.readObject();

                    Producto nuevoProducto = new Producto();
                    nuevoProducto.nombre = producto.nombre;
                    nuevoProducto.codigo = producto.codigo;
                    nuevoProducto.precio = producto.precio;
                    nuevoProducto.stock = producto.stock;
                    bufferSalida2.writeObject(nuevoProducto);
                }

                bufferEntradaMas2.close();
                bufferSalida2.close();
                nuevoMaestro.close();
                bufferSalida.close();
                bufferEntradaMas.close();

                File aux = new File(RUTA + nombreMaestro + "2.dat");
                aux.delete();

            }

            bufferEntradaDet.close();

        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo maestro\n");
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    public static void generarDetalleMerge(String nombre) {
        FileOutputStream detalle;
        ObjectOutputStream bufferSalida;
        char respuesta;

        try {

            detalle = new FileOutputStream(RUTA + nombre + ".dat");
            bufferSalida = new ObjectOutputStream(detalle);

            do {

                Producto nuevoProducto = new Producto();
                System.out.println("Ingresar el nombre del producto");
                nuevoProducto.nombre = in.nextLine();
                System.out.println("Ingresar el codigo del producto");
                nuevoProducto.codigo = in.nextInt();
                System.out.println("Ingresar el precio del producto");
                nuevoProducto.precio = in.nextFloat();
                System.out.println("Ingresar el stock disponible del producto");
                nuevoProducto.stock = in.nextInt();

                bufferSalida.writeObject(nuevoProducto);

                System.out.println("Desea ingresar un nuevo prroducto en el " + nombre + " ?(S/N)");
                in.nextLine();
                respuesta = in.nextLine().charAt(0);
                while (respuesta != 's' && respuesta != 'S' && respuesta != 'n' && respuesta != 'N') {
                    System.out.println("Desea ingresar un nuevo prroducto en el " + nombre + " ?(S/N)");
                    in.nextLine();
                    respuesta = in.nextLine().charAt(0);
                }
            } while (respuesta == 's' || respuesta == 'S');

            bufferSalida.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public static void merge(String detalle1, String detalle2, String detalle3, String nombreMaestro) {
        FileInputStream d1;
        ObjectInputStream entradaD1 = null;
        FileInputStream d2;
        ObjectInputStream entradaD2 = null;
        FileInputStream d3;
        ObjectInputStream entradaD3 = null;
        boolean salirD1 = true, salirD2 = true, salirD3 = true;
        Producto min = null;
        Producto productoD1 = null;
        Producto productoD2 = null;
        Producto productoD3 = null;

        FileOutputStream maestro;
        ObjectOutputStream bufferSalida = null;

        try {
            d1 = new FileInputStream(RUTA + detalle1 + ".dat");
            entradaD1 = new ObjectInputStream(d1);

            d2 = new FileInputStream(RUTA + detalle2 + ".dat");
            entradaD2 = new ObjectInputStream(d2);

            d3 = new FileInputStream(RUTA + detalle3 + ".dat");
            entradaD3 = new ObjectInputStream(d3);

            maestro = new FileOutputStream(RUTA + nombreMaestro + ".dat");
            bufferSalida = new ObjectOutputStream(maestro);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        while (salirD1 || salirD2 || salirD3) {

            try {
                if (min == null) {
                    productoD1 = (Producto) entradaD1.readObject();
                } else if (productoD1 == min) {
                    productoD1 = (Producto) entradaD1.readObject();
                }
            } catch (IOException | ClassNotFoundException ex) {
                productoD1 = null;
                salirD1 = false;
            }

            try {
                if (min == null) {
                    productoD2 = (Producto) entradaD2.readObject();
                } else if (productoD2 == min) {
                    productoD2 = (Producto) entradaD2.readObject();
                }
            } catch (IOException | ClassNotFoundException ex) {
                productoD2 = null;
                salirD2 = false;
            }

            try {
                if (min == null) {
                    productoD3 = (Producto) entradaD3.readObject();
                } else if (productoD3 == min) {
                    productoD3 = (Producto) entradaD3.readObject();
                }
            } catch (IOException | ClassNotFoundException ex) {
                productoD3 = null;
                salirD3 = false;
            }

            min = minimo(productoD1, productoD2, productoD3);

            try {
                if (min != null) {
                    Producto producto = new Producto();
                    producto.codigo = min.codigo;
                    producto.nombre = min.nombre;
                    producto.precio = min.precio;
                    producto.stock = min.stock;

                    bufferSalida.writeObject(producto);
                }

            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
        }
        try {
            bufferSalida.close();
            entradaD1.close();
            entradaD2.close();
            entradaD3.close();
        } catch (IOException ex) {
        }

    }

    public static Producto minimo(Producto d1, Producto d2, Producto d3) {
        if (d1 != null && d2 != null && d3 != null) {
            if (d1.codigo <= d2.codigo && d1.codigo <= d3.codigo) {
                return d1;
            } else if (d2.codigo <= d3.codigo) {
                return d2;
            } else {
                return d3;
            }
        } else if (d1 != null && d2 != null) {
            if (d1.codigo <= d2.codigo) {
                return d1;
            } else {
                return d2;
            }
        } else if (d2 != null && d3 != null) {
            if (d2.codigo <= d3.codigo) {
                return d2;
            } else {
                return d3;
            }
        } else if (d1 != null) {
            return d1;
        } else if (d2 != null) {
            return d2;
        } else if (d3 != null) {
            return d3;
        } else {
            return null;
        }
    }

    public static void corteDeControl(String nombreMaestro) {
        FileInputStream maestro;
        ObjectInputStream bufferEntrada = null;
        int ventasTotales = 0;
        float dineroTotal = 0;
        int i = 0;
        boolean mostrar = false;

        try {
            maestro = new FileInputStream(RUTA + nombreMaestro + ".dat");
            bufferEntrada = new ObjectInputStream(maestro);
            mostrar = true;

            while (true) {
                Producto producto = (Producto) bufferEntrada.readObject();

                System.out.println("===============================================================");
                System.out.println("Producto: " + producto.nombre);
                System.out.println("Codigo: " + producto.codigo);
                System.out.println("Stock disponible: " + producto.stock);
                System.out.println("Ventas realizadas: " + (STOCK[i] - producto.stock));
                System.out.println("Dinero por ventas: $" + (STOCK[i] - producto.stock) * PRECIOS[i]);
                ventasTotales += (STOCK[i] - producto.stock);
                dineroTotal += (STOCK[i] - producto.stock) * PRECIOS[i];
                i++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro un archivo maestro\n");
        } catch (IOException | ClassNotFoundException ex) {
        }
        try {
            if (mostrar) {
                System.out.println("===============================================================");
                System.out.println("Total de ventas realizadas: " + ventasTotales);
                System.out.println("Total de dinero por ventas: $" + dineroTotal);
                System.out.println("===============================================================");
                System.out.println("                                Nombre del archivo:" + nombreMaestro + ".dat\n");
                bufferEntrada.close();
            }
        } catch (IOException ex) {
        }
    }

    public static void corteControlMerge(String nombreMaestro) {
        FileInputStream maestro;
        ObjectInputStream bufferEntrada = null;

        try {
            maestro = new FileInputStream(RUTA + nombreMaestro + ".dat");
            bufferEntrada = new ObjectInputStream(maestro);

            while (true) {
                Producto producto = (Producto) bufferEntrada.readObject();

                System.out.println("===============================================================");
                System.out.println("Producto: " + producto.nombre);
                System.out.println("Codigo: " + producto.codigo);
                System.out.println("Stock disponible: " + producto.stock);
                System.out.println("Precio unitario : $" + producto.precio);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro un archivo maestro\n");
        } catch (IOException | ClassNotFoundException ex) {
        }
        if (bufferEntrada != null) {
            System.out.println("===============================================================");
            System.out.println("                                Nombre del archivo:" + nombreMaestro + ".dat\n");
            try {
                bufferEntrada.close();
            } catch (IOException ex) {
            }
        }
    }

    public static void eliminarMaestro(String nombreMaestro) {
        if (!nombreMaestro.equals("") && !nombreMaestro.equals("Merge")) {
            File maestro = new File(RUTA + nombreMaestro + ".dat");
            maestro.delete();
            System.out.println("El archivo " + nombreMaestro + " se elimino con exito");
        } else {
            System.out.println("El archivo maestro no existe");
        }
    }
}
