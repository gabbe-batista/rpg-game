import classes.*;
import exceptions.PersonagemInvalidoException;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║        RPG GAME - SISTEMA COMBATE      ║");
            System.out.println("╚════════════════════════════════════════╝");

            Guerreiro guerreiro = new CriadorGuerreiro()
                .comNome("Aragorn")
                .comVida(120)
                .comForca(35)
                .comArmadura(30)
                .comDefesa(25)
                .comVelocidade(16)
                .construir();

            System.out.println("✓ Guerreiro criado: " + guerreiro.getNome());

            Mago mago = new CriadorMago()
                .comNome("Gandalf")
                .comVida(80)
                .comMana(150)
                .comSabedoria(32)
                .comInteligencia(35)
                .comDefesa(10)
                .comVelocidade(20)
                .construir();

            System.out.println("✓ Mago criado: " + mago.getNome());

            Arqueiro arqueiro = new CriadorArqueiro()
                .comNome("Legolas")
                .construir();

            System.out.println("✓ Arqueiro criado: " + arqueiro.getNome());

            System.out.println("\n[ESTATÍSTICAS INICIAIS]\n");
            System.out.println(guerreiro);
            System.out.println(mago);
            System.out.println(arqueiro);

            // Arqueiro vs Mago
            System.out.println("\n\n[COMBATE 1: ARQUEIRO vs MAGO]\n");
            SistemaCombate combate1 = new SistemaCombate(arqueiro, mago);
            combate1.iniciarCombate();

            // Resetar vidas
            arqueiro.setVida(arqueiro.getVidaMaxima());
            mago.setVida(mago.getVidaMaxima());

            // Arqueiro vs Guerreiro
            System.out.println("\n\n[COMBATE 2: ARQUEIRO vs GUERREIRO]\n");
            SistemaCombate combate2 = new SistemaCombate(arqueiro, guerreiro);
            combate2.iniciarCombate();

            System.out.println("\n\n╔════════════════════════════════════════╗");
            System.out.println("║                    FIM!                ║");
            System.out.println("╚════════════════════════════════════════╝");
        } catch (PersonagemInvalidoException e) {
            System.out.println("Erro de criação de personagem: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro inesperado na aplicação: " + e.getMessage());
        }
    }
}
