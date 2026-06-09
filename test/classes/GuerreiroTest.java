package classes;

import exceptions.AcaoInvalidaException;
import exceptions.AtributoInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GuerreiroTest {

    @Test
    void deveAumentarIraAoAtacar() {
        Guerreiro guerreiro = new Guerreiro("Aragorn", 120, 20, 10, 15, 30, 25);
        Mago alvo = new Mago("Gandalf", 90, 8, 28, 18, 100, 25);

        guerreiro.atacar(alvo);

        assertEquals(15, guerreiro.getIra());
    }

    @Test
    void deveFalharAoAtacarComAlvoNulo() {
        Guerreiro guerreiro = new Guerreiro("Aragorn", 120, 20, 10, 15, 30, 25);

        assertThrows(AcaoInvalidaException.class, () -> guerreiro.atacar(null));
    }

    @Test
    void deveResetarIraAoUsarHabilidadeEspecial() {
        Guerreiro guerreiro = new Guerreiro("Aragorn", 120, 20, 10, 15, 30, 25);
        Mago alvo = new Mago("Gandalf", 90, 8, 28, 18, 100, 25);

        for (int i = 0; i < 4; i++) {
            guerreiro.atacar(alvo);
        }

        guerreiro.usarHabilidadeEspecial(alvo);

        assertEquals(0, guerreiro.getIra());
    }

    @Test
    void deveTratarAlvoNuloNaHabilidadeEspecial() {
        Guerreiro guerreiro = new Guerreiro("Aragorn", 120, 20, 10, 15, 30, 25);

        assertDoesNotThrow(() -> guerreiro.usarHabilidadeEspecial(null));
    }

    @Test
    void deveFalharAoReceberDanoNegativo() {
        Guerreiro guerreiro = new Guerreiro("Aragorn", 120, 20, 10, 15, 30, 25);

        assertThrows(AtributoInvalidoException.class, () -> guerreiro.receberDano(-1));
    }
}
