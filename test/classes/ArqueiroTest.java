package classes;

import exceptions.AtributoInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArqueiroTest {

    @Test
    void deveConsumirMunicaoAoAtacar() {
        Arqueiro arqueiro = new Arqueiro("Legolas", 70, 12, 14, 25, 40, 200);
        Guerreiro alvo = new Guerreiro("Alvo", 100, 10, 10, 10, 20, 10);

        arqueiro.atacar(alvo);

        assertEquals(99, arqueiro.getMunicao());
    }

    @Test
    void naoDeveLancarExcecaoQuandoAtacaSemMunicao() {
        Arqueiro arqueiro = new Arqueiro("Legolas", 70, 12, 14, 25, 40, 200);
        Guerreiro alvo = new Guerreiro("Alvo", 100, 10, 10, 10, 20, 10);

        for (int i = 0; i < 100; i++) {
            arqueiro.atacar(alvo);
        }

        assertDoesNotThrow(() -> arqueiro.atacar(alvo));
        assertEquals(0, arqueiro.getMunicao());
    }

    @Test
    void deveTratarAlvoNuloNoAtaque() {
        Arqueiro arqueiro = new Arqueiro("Legolas", 70, 12, 14, 25, 40, 200);

        assertDoesNotThrow(() -> arqueiro.atacar(null));
    }

    @Test
    void deveFalharAoReceberDanoNegativo() {
        Arqueiro arqueiro = new Arqueiro("Legolas", 70, 12, 14, 25, 40, 200);

        assertThrows(AtributoInvalidoException.class, () -> arqueiro.receberDano(-10));
    }
}
