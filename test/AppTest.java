import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AppTest {

    @Test
    @Timeout(15)
    void deveExecutarMainSemLancarExcecao() {
        InputStream originalIn = System.in;

        try {
            StringBuilder entradas = new StringBuilder();
            for (int i = 0; i < 500; i++) {
                entradas.append("1\n");
            }
            System.setIn(new ByteArrayInputStream(entradas.toString().getBytes(StandardCharsets.UTF_8)));

            assertDoesNotThrow(() -> App.main(new String[0]));
        } finally {
            System.setIn(originalIn);
        }
    }
}
