package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {

    @Test
    void constructorRechazaSaldoInicialNegativo(){
        IRepositorioResultados repo = new RepositorioEnMemoria();
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Ruleta(-100, repo)
        );
        assertEquals("Saldo inicial inválido", ex.getMessage());
    }

    @Test
    void depositoValidoIncrementaSaldo(){
        IRepositorioResultados repo = new RepositorioEnMemoria();
        Ruleta ruleta = new Ruleta(500, repo);
        ruleta.depositar(200);
        assertEquals(700, ruleta.getBalance());
    }

    @Test
    void jugarRechazaApuestaNula(){
        IRepositorioResultados repo = new RepositorioEnMemoria();
        Ruleta ruleta = new Ruleta(1000, repo);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.jugar(null)
        );
        assertEquals("Apuesta requerida", ex.getMessage());
    }

    @Test
    void jugarRechazaApuestaConMontoMayorAlSaldo(){
        IRepositorioResultados repo = new RepositorioEnMemoria();
        Ruleta ruleta = new Ruleta(100, repo);
        ApuestaBase apuesta = new ApuestaRojo(500);

        IllegalArgumentException ex  = assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.jugar(apuesta)
        );
        assertEquals("Saldo insuficiente", ex.getMessage());
    }
}