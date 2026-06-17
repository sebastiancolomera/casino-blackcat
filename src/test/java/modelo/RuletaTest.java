package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RuletaTest {

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
}