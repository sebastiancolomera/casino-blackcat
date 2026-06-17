package controlador;

import modelo.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {

    @Test
    void loginRechazarUsuarioNoRegistrado() {
        SessionController session = new SessionController();

        Usuario resultado = session.login("usuarioInexistente", "contraseña");
        assertNull(resultado);
    }

    @Test
    void loginConUsernameNuloEsRechazado() {
        SessionController session = new SessionController();

        Usuario resultado = session.login(null, "contraseña");

        assertNull(resultado);
    }
    @Test
    void loginConPasswordNuloEsRechazado() {
        SessionController session = new SessionController();

        Usuario resultado = session.login("TimPayne", null);

        assertNull(resultado);
    }
}