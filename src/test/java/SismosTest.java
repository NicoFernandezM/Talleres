import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SismosTest {
    private double [][] sismosDePrueba;
    private double [] sismoMasIntensoEsperado;
    private boolean [] escaladaSismicaEsperada;

    @BeforeEach
    public void init() {
        sismosDePrueba = new double [ ][ ] {
                {5.8, 6, 7, 0, 0, 0, 9.2, 7.8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 8, 4.6, 3.5, 3.6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7.9, 7.8, 8.9, 9.5},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 4.7, 7.2, 6.5, 7.4, 8.4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 7.5, 3.4, 7.6, 9.2, 7.8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        sismoMasIntensoEsperado = new double[] {9.5, 3, 23};
        escaladaSismicaEsperada = new boolean[] {false, false, true, true, true};
    }

    @Test
    public void encontrarSismoMasIntensoTest() {
        double [] sismoMasIntensoObtenido = Sismos.encontrarSismoMasIntenso(sismosDePrueba);
        for(int i = 0; i< sismoMasIntensoEsperado.length; i++) {
            assertEquals(sismoMasIntensoObtenido[i], sismoMasIntensoEsperado[i]);
        }
    }

    @Test
    public void hayEscaladaSismicaTest() {
        for(int i = 0; i < escaladaSismicaEsperada.length; i++) {
            boolean escaladaSismicaObtenida = Sismos.hayEscaladaSismica(sismosDePrueba[i]);
            assertEquals(escaladaSismicaObtenida, escaladaSismicaEsperada[i]);
        }
    }
}


