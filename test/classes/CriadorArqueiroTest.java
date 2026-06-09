package classes;

import exceptions.PersonagemInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CriadorArqueiroTest {

    @Test
    void deveConstruirArqueiroValido() {
        Arqueiro arqueiro = new CriadorArqueiro()
            .comNome("Legolas")
            .comVida(80)
            .comDefesa(12)
            .comInteligencia(14)
            .comVelocidade(22)
            .comAgilidade(30)
            .comPrecisao(35)
            .construir();

        assertEquals("Legolas", arqueiro.getNome());
        assertEquals(80, arqueiro.getVida());
    }

    @Test
    void deveFalharAoConstruirSemNome() {
        CriadorArqueiro criador = new CriadorArqueiro();

        assertThrows(PersonagemInvalidoException.class, criador::construir);
    }
}
