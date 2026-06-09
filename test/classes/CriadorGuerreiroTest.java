package classes;

import exceptions.PersonagemInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CriadorGuerreiroTest {

    @Test
    void deveConstruirGuerreiroValido() {
        Guerreiro guerreiro = new CriadorGuerreiro()
            .comNome("Aragorn")
            .comVida(120)
            .comDefesa(25)
            .comInteligencia(10)
            .comVelocidade(16)
            .comForca(35)
            .comArmadura(30)
            .construir();

        assertEquals("Aragorn", guerreiro.getNome());
        assertEquals(120, guerreiro.getVida());
    }

    @Test
    void deveFalharAoConstruirSemNome() {
        CriadorGuerreiro criador = new CriadorGuerreiro();

        assertThrows(PersonagemInvalidoException.class, criador::construir);
    }
}
