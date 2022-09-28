import java.util.Scanner;

public class ColeccionDeLibros {
    public static void main(String[] args) {
        String [][] coleccionDeLibros = new String[100][3];

        agregarLibro(coleccionDeLibros, "El Hobbit","J.R.R. Tolkien","Ed. Planeta");
        agregarLibro(coleccionDeLibros, "Cujo","Stephen King","Ed. Que susto");
        agregarLibro(coleccionDeLibros, "Un mundo feliz","Aldous Huxley","Ed. No Me Acuerdo");

        menu(coleccionDeLibros);
    }

    public static void menu(String [][] coleccionDeLibros) {
        int opcionIngresada;

        do{
            imprimirMenu();
            opcionIngresada = pedirOpcionMenu();

            switch (opcionIngresada) {
                case 1 -> menuAgregarLibro(coleccionDeLibros);
                case 2 -> menuBuscarLibro(coleccionDeLibros);
                case 3 -> System.out.println(100 - espaciosDisponibles(coleccionDeLibros));
                case 4 -> System.out.println(espaciosDisponibles(coleccionDeLibros));
                case 5 -> mostrarColeccion(coleccionDeLibros);
                case 6 -> System.out.println("Programa finalizado");
                //default -> System.out.println("Por favor ingrese un número válido");
            }
        }while(opcionIngresada != 6);
    }

    public static void imprimirMenu() {
        System.out.println("""
                COLECCIÓN
                1) Agregar libro
                2) Buscar libro
                3) Mostrar espacios usados
                4) Mostrar espacios disponibles
                5) Mostrar toda la colección
                6) Salir""");
    }

    private static void menuAgregarLibro(String [][] coleccionDeLibros) {

        if(hayEspacio(coleccionDeLibros)) {
            System.out.println("Por favor ingrese el titulo");
            String titulo = pedirInput();

            System.out.println("Por favor ingrese el autor");
            String autor = pedirInput();

            System.out.println("Por favor ingrese la editorial");
            String editorial = pedirInput();

            agregarLibro(coleccionDeLibros, titulo, autor, editorial);
        } else {
            System.out.println("No hay espacio disponible");
        }
    }

    public static void agregarLibro(String [][] coleccionDeLibros, String titulo,
                                    String autor, String editorial) {

        int indiceEspacioDisponible = 100 - espaciosDisponibles(coleccionDeLibros);

        coleccionDeLibros[indiceEspacioDisponible][0] = titulo;
        coleccionDeLibros[indiceEspacioDisponible][1] = autor;
        coleccionDeLibros[indiceEspacioDisponible][2] = editorial;
    }

    private static void menuBuscarLibro(String[][] coleccionDeLibros) {
        System.out.println("Por favor ingrese el titulo del libro que desea buscar.");
        String titulo = pedirInput();

        buscarLibro(coleccionDeLibros, titulo);
    }

    public static void buscarLibro(String [][] coleccionDeLibros, String titulo) {
        for(int i = 0; i < coleccionDeLibros.length; i++) {
            if(titulo.equalsIgnoreCase(coleccionDeLibros[i][0])) {
                System.out.println("Libro \"" + titulo + "\" encontrado.");
                return;
            }
        }

        System.out.println("Libro \"" + titulo + "\" no encontrado.");
    }

    public static void mostrarColeccion(String [][] coleccionDeLibros) {
        int indiceEspacioDisponible = 100 - espaciosDisponibles(coleccionDeLibros);

        for(int i = 0; i < indiceEspacioDisponible; i++) {
            for(int j = 0; j < coleccionDeLibros[i].length; j++) {
                System.out.print(coleccionDeLibros[i][j] + ", ");
            }

            System.out.println();
        }
    }

    public static boolean hayEspacio(String [][] coleccionDeLibros) {
        return espaciosDisponibles(coleccionDeLibros) != 0;
    }

    public static int espaciosDisponibles(String [][] coleccionDeLibros) {
        for(int i = 0; i < coleccionDeLibros.length; i++) {
            if(coleccionDeLibros[i][0] == null) {
                return coleccionDeLibros.length - i;
            }
        }

        return 0;
    }

    public static String pedirInput() {
        Scanner leer = new Scanner(System.in);
        String inputUsuario = "";

        while(inputUsuario.isEmpty()) {
            inputUsuario = leer.nextLine();
        }

        return inputUsuario;
    }

    public static int pedirOpcionMenu() {
        Scanner leer = new Scanner(System.in);
        int opcionIngresada = leer.nextInt();

        if(opcionInvalida(opcionIngresada)) {
            System.out.println("Por favor ingrese un número válido");
            opcionIngresada = pedirOpcionMenu();
        }

        leer.close();
        return opcionIngresada;
    }

    public static boolean opcionInvalida(int opcionIngresada) {
        return opcionIngresada > 6 || opcionIngresada < 1;
    }

    /*public static int pedirOpcionMenu() {
        Scanner leer = new Scanner(System.in);
        int opcionIngresada;

        do {
            System.out.println("Por favor elija una opción");
            opcionIngresada = leer.nextInt();
        }while(opcionInvalida);

        return opcionIngresada;

    }*/
}
