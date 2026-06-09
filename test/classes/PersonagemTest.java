package classes;

import exceptions.AtributoInvalidoException;
import exceptions.PersonagemInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonagemTest {

    private static class PersonagemFake extends Personagem {
        PersonagemFake(String nome, int vida, int defesa, int inteligencia, int velocidade) {
            super(nome, vida, defesa, inteligencia, velocidade);
        }
    }

    @Test
    void deveFalharAoCriarComNomeInvalido() {
        assertThrows(PersonagemInvalidoException.class,
            () -> new PersonagemFake("", 10, 5, 5, 5));
    }

    @Test
    void deveSubirDeNivelAoGanharExperiencia() {
        PersonagemFake personagem = new PersonagemFake("Teste", 100, 10, 10, 10);

        personagem.adicionarExperiencia(100);

        assertEquals(2, personagem.getNivel());
        assertEquals(0, personagem.getExperiencia());
        assertEquals(110, personagem.getVidaMaxima());
        assertEquals(110, personagem.getVida());
    }

    @Test
    void deveLimitarVidaAoMaximo() {
        PersonagemFake personagem = new PersonagemFake("Teste", 100, 10, 10, 10);

        personagem.setVida(999);

        assertEquals(100, personagem.getVida());
    }

    @Test
    void deveReceberDanoComReducaoPorDefesa() {
        PersonagemFake personagem = new PersonagemFake("Teste", 100, 20, 10, 10);

        personagem.receberDano(30);

        assertEquals(80, personagem.getVida());
        assertTrue(personagem.estaVivo());
    }

    @Test
    void deveFalharComDanoNegativo() {
        PersonagemFake personagem = new PersonagemFake("Teste", 100, 20, 10, 10);

        assertThrows(AtributoInvalidoException.class, () -> personagem.receberDano(-1));
    }
}
