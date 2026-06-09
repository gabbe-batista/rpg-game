package classes;

import exceptions.PersonagemInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CriadorMagoTest {

    @Test
    void deveConstruirMagoValido() {
        Mago mago = new CriadorMago()
            .comNome("Gandalf")
            .comVida(90)
            .comDefesa(10)
            .comInteligencia(30)
            .comVelocidade(18)
            .comMana(150)
            .comSabedoria(35)
            .construir();

        assertEquals("Gandalf", mago.getNome());
        assertEquals(150, mago.getMana());
    }

    @Test
    void deveFalharAoConstruirSemNome() {
        CriadorMago criador = new CriadorMago();

        assertThrows(PersonagemInvalidoException.class, criador::construir);
    }
}
