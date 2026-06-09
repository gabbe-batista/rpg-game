package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ExceptionsTest {

    @Test
    void deveCriarPersonagemInvalidoExceptionComMensagemECausa() {
        RuntimeException causa = new RuntimeException("causa");
        PersonagemInvalidoException ex = new PersonagemInvalidoException("erro", causa);

        assertEquals("erro", ex.getMessage());
        assertSame(causa, ex.getCause());
    }

    @Test
    void deveCriarAtributoInvalidoExceptionComMensagem() {
        AtributoInvalidoException ex = new AtributoInvalidoException("atributo inválido");

        assertEquals("atributo inválido", ex.getMessage());
    }

    @Test
    void deveCriarRecursoInsuficienteExceptionComMensagem() {
        RecursoInsuficienteException ex = new RecursoInsuficienteException("recurso insuficiente");

        assertEquals("recurso insuficiente", ex.getMessage());
    }

    @Test
    void deveCriarAcaoInvalidaExceptionComMensagem() {
        AcaoInvalidaException ex = new AcaoInvalidaException("ação inválida");

        assertEquals("ação inválida", ex.getMessage());
    }
}
