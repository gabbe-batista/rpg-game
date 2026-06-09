package classes;

import exceptions.AtributoInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MagoTest {

    @Test
    void deveConsumirManaAoAtacar() {
        Mago mago = new Mago("Gandalf", 80, 10, 35, 20, 100, 30);
        Guerreiro alvo = new Guerreiro("Aragorn", 120, 20, 10, 15, 30, 25);

        mago.atacar(alvo);

        assertEquals(85, mago.getMana());
    }

    @Test
    void naoDeveLancarExcecaoQuandoSemManaNoAtaque() {
        Mago mago = new Mago("Gandalf", 80, 10, 35, 20, 0, 30);
        Guerreiro alvo = new Guerreiro("Aragorn", 120, 20, 10, 15, 30, 25);

        assertDoesNotThrow(() -> mago.atacar(alvo));
        assertEquals(0, mago.getMana());
    }

    @Test
    void deveCurarAliadoEConsumirMana() {
        Mago mago = new Mago("Gandalf", 80, 10, 35, 20, 100, 30);
        Guerreiro alvo = new Guerreiro("Aragorn", 120, 20, 10, 15, 30, 25);
        alvo.receberDano(60);

        int vidaAntes = alvo.getVida();
        mago.lancarCura(alvo);

        assertTrue(alvo.getVida() > vidaAntes);
        assertEquals(75, mago.getMana());
    }

    @Test
    void naoDeveLancarExcecaoQuandoSemManaNaExplosao() {
        Mago mago = new Mago("Gandalf", 80, 10, 35, 20, 10, 30);
        Guerreiro alvo = new Guerreiro("Aragorn", 120, 20, 10, 15, 30, 25);

        assertDoesNotThrow(() -> mago.lancaExplosaoMagica(alvo));
        assertEquals(10, mago.getMana());
    }

    @Test
    void deveFalharAoDefinirManaNegativa() {
        Mago mago = new Mago("Gandalf", 80, 10, 35, 20, 10, 30);

        assertThrows(AtributoInvalidoException.class, () -> mago.setMana(-10));
    }
}
