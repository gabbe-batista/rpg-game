package classes;

import exceptions.PersonagemInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SistemaCombateTest {

    @Test
    void deveFalharQuandoJogadoresForemNulos() {
        assertThrows(PersonagemInvalidoException.class, () -> new SistemaCombate(null, null));
    }

    @Test
    void deveDefinirPrimeiroTurnoPeloMaisVeloz() {
        Guerreiro lento = new Guerreiro("Lento", 100, 10, 10, 5, 20, 20);
        Arqueiro rapido = new Arqueiro("Rapido", 70, 12, 14, 30, 28, 30);

        SistemaCombate sistema = new SistemaCombate(lento, rapido);

        assertSame(rapido, sistema.getTurnoAtual());
    }

    @Test
    void deveIniciarComTurnoZero() {
        Guerreiro jogador1 = new Guerreiro("J1", 100, 10, 10, 5, 20, 20);
        Arqueiro jogador2 = new Arqueiro("J2", 70, 12, 14, 30, 28, 30);

        SistemaCombate sistema = new SistemaCombate(jogador1, jogador2);

        assertEquals(0, sistema.getTurno());
    }
}
