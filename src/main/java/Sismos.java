import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sismos {
    public static void main(String[] args) {
        double [][] datosSismos = recopilarDatos();
        menu(datosSismos);
    }

    public static double [][] recopilarDatos() {
        int dias;

        do {
            System.out.println("Por favor ingrese los días entre 1 y 31");
            dias = pedirInput();
        }while (dias <= 0 || dias > 31);

        double [][] datosRecopilados = new double[dias][24];
        llenarMatrizDeSismos(datosRecopilados);

        return datosRecopilados;
    }

    public static void llenarMatrizDeSismos(double [][] datosRecopilados) {
        for(int i = 0; i < datosRecopilados.length; i++) {
            for (int j = 0; j < datosRecopilados[i].length; j++) {
                datosRecopilados[i][j] = generarSismo();
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("""
                Menú
                1) Intensidad, día y hora del sismo más intenso.
                2) Total días en que se registraron sismos mayores o iguales a 5.5 grados.
                3) Alerta de escalada sísmica.
                4) Alerta de enjambre sísmico.
                5) Salir.
                """);
    }

    public static void menu(double [][] datosSismos) {
        int opcionUsuario;

        do {
            mostrarMenu();
            opcionUsuario = pedirInput();

            switch (opcionUsuario) {
                case 1 -> mostrarSismoMasIntenso(datosSismos);
                case 2 -> mostrarDiasConSismoMayorA(datosSismos);
                case 3 -> mostrarAlertaEscaladaSismica(datosSismos);
                case 4 -> mostrarAlertaEnjambreSismico(datosSismos);
                case 5 -> System.out.println("Programa finalizado.");
                default -> System.out.println("Opción inválida");
            }
        }while (opcionUsuario != 5);
    }

    public static void mostrarSismoMasIntenso(double [][] datosRecopilados) {
        double [] datosSismoMasIntenso = encontrarSismoMasIntenso(datosRecopilados);
        String [] tipoDeDato = {"Intensidad: ", "Día: ", "Hora: "};

        for(int i = 0; i < datosSismoMasIntenso.length; i++) {
            System.out.println(tipoDeDato[i] + formatearSismo(datosSismoMasIntenso[i]));
        }
    }

    public static double[] encontrarSismoMasIntenso(double [][] datosRecopilados) {
        //Intensidad, día y hora.
        double [] datosSismoMasIntenso = new double[3];
        double max = 0;

        for(int i = 0; i < datosRecopilados.length; i++) {
            for (int j = 0; j < datosRecopilados[i].length; j++) {
                if(datosRecopilados[i][j] > max) {
                    max = datosRecopilados[i][j];

                    datosSismoMasIntenso[0] = max;
                    datosSismoMasIntenso[1] = i + 1;
                    datosSismoMasIntenso[2] = j;
                }
            }
        }

        return datosSismoMasIntenso;
    }

    public static void mostrarDiasConSismoMayorA(double [][] datosRecopilados) {
        int dias = 0;

        for (double[] datosRecopiladosDia : datosRecopilados) {
            if(mayorOIgual(datosRecopiladosDia, 5.5)) {
                dias++;
            }
        }

        System.out.println("Hay un total de " + dias + " días con sismos mayores o iguales a 5.5");
    }

    public static boolean mayorOIgual(double [] sismos, double intensidadAComparar) {
        for(double sismo : sismos) {
            if(sismo >= intensidadAComparar) {
                return true;
            }
        }

        return false;
    }

    public static void mostrarAlertaEscaladaSismica(double [][] datosRecopilados) {
        for(int i = 0; i < datosRecopilados.length; i++) {
            if(hayEscaladaSismica(datosRecopilados[i])) {
                System.out.println("Día " + (i + 1) + " hay escalada sismica");
            }
        }
    }

    public static boolean hayEscaladaSismica(double [] dia) {
        int contador = 0;

        for(double intensidadPorHora : dia) {
            contador = intensidadPorHora > 6 ? contador + 1 : 0;
            if(contador == 4) return true;
        }

        return false;
    }

    private static void mostrarAlertaEnjambreSismico(double[][] datosSismos) {
        if(hayEnjambreSismico(datosSismos)) {
            System.out.println("Hay enjambre sismico");
        } else {
            System.out.println("No hay enjambre sismico");
        }
    }

    public static boolean hayEnjambreSismico(double [][] datosSismos) {
        int contador = 0;

        for(double [] sismosPorDia : datosSismos) {
            contador = hayEscaladaSismica(sismosPorDia) ? contador + 1 : 0;
            if(contador == 3) return true;
        }

        return false;
    }

    public static int pedirInput() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Opción inválida.");
            return pedirInput();
        }
    }

    public static String formatearSismo(double intensidadSismo) {
        DecimalFormat formateador = new DecimalFormat("#.0");
        return formateador.format(intensidadSismo);
    }

    public static double generarSismo() {
        return ((Math.random() * 9) + 0.5);
    }
}
