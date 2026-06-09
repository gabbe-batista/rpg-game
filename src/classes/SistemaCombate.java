package classes;

import exceptions.AcaoInvalidaException;
import exceptions.PersonagemInvalidoException;
import interfaces.Atacante;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaCombate {
    
    private Personagem jogador1;
    private Personagem jogador2;
    private int turno;
    private Personagem turnoAtual;
    
    public SistemaCombate(Personagem jogador1, Personagem jogador2) {
        if (jogador1 == null || jogador2 == null) {
            throw new PersonagemInvalidoException("Os dois jogadores precisam ser válidos para iniciar o combate.");
        }
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.turno = 0;
        determinarPrimeiroJogador();
    }
    
    private void determinarPrimeiroJogador() {
        if (jogador1.getVelocidade() >= jogador2.getVelocidade()) {
            this.turnoAtual = jogador1;
        } else {
            this.turnoAtual = jogador2;
        }
        System.out.println("\n" + turnoAtual.getNome() + " vai primeiro!");
    }
    
    public void iniciarCombate() {
        System.out.println("\n============== INICIANDO COMBATE ==============");
        System.out.println(jogador1);
        System.out.println("  VS  ");
        System.out.println(jogador2);
        System.out.println("=".repeat(45));
        System.out.println();
        
        Scanner scanner = new Scanner(System.in);
        
        while (jogador1.estaVivo() && jogador2.estaVivo()) {
            this.turno++;
            System.out.println("\n>>> TURNO " + this.turno + " <<<");
            
            Personagem adversario = (turnoAtual == jogador1) ? jogador2 : jogador1;
            
            exibirOpcoes();
            System.out.print("Escolha uma ação: ");
            
            try {
                int acao = scanner.nextInt();
                executarAcao(acao, turnoAtual, adversario, scanner);
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Use números.");
                scanner.nextLine();
                continue;
            } catch (AcaoInvalidaException e) {
                System.out.println(e.getMessage());
                continue;
            }
            
            System.out.println("\n- Estado do combate -");
            System.out.println(jogador1.getNome() + ": " + jogador1.getVida() + "/" + jogador1.getVidaMaxima() + " HP");
            System.out.println(jogador2.getNome() + ": " + jogador2.getVida() + "/" + jogador2.getVidaMaxima() + " HP");
            
            alternarTurno();
        }
        
        scanner.close();
        finalizarCombate();
    }
    
    private void exibirOpcoes() {
        System.out.println("\nOpções de combate:");
        System.out.println("1 - Ataque Normal");
        System.out.println("2 - Habilidade Especial");
        System.out.println("3 - Ver Estatísticas");
    }
    
    private void executarAcao(int acao, Personagem atacante, Personagem adversario, Scanner scanner) {
        switch (acao) {
            case 1:
                realizarAtaqueNormal(atacante, adversario);
                break;
            case 2:
                realizarHabilidadeEspecial(atacante, adversario, scanner);
                break;
            case 3:
                exibirEstatisticas(atacante, adversario);
                turnoAtual = atacante;
                return;
            default:
                throw new AcaoInvalidaException("Ação inválida! Escolha uma opção entre 1 e 3.");
        }
    }
    
    private void realizarAtaqueNormal(Personagem atacante, Personagem adversario) {
        if (atacante instanceof Atacante) {
            ((Atacante) atacante).atacar(adversario);
        } else {
            System.out.println(atacante.getNome() + " não pode atacar!");
        }
    }
    
    private void realizarHabilidadeEspecial(Personagem atacante, Personagem adversario, Scanner scanner) {
        if (atacante instanceof Guerreiro) {
            ((Guerreiro) atacante).usarHabilidadeEspecial(adversario);
        } else if (atacante instanceof Mago) {
            System.out.println("\nEscolha a magia:");
            System.out.println("1 - Cura");
            System.out.println("2 - Explosão Mágica");

            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                ((Mago) atacante).lancarCura(atacante);
            } else if (opcao == 2) {
                ((Mago) atacante).lancaExplosaoMagica(adversario);
            } else {
                throw new AcaoInvalidaException("Opção de magia inválida!");
            }
        } else if (atacante instanceof Arqueiro) {
            System.out.println("Arqueiro não possui habilidade especial neste momento!");
        } else {
            throw new AcaoInvalidaException("Classe de personagem sem habilidade especial cadastrada.");
        }
    }
    
    private void exibirEstatisticas(Personagem jogador, Personagem adversario) {
        System.out.println("\n=== ESTATÍSTICAS ===");
        System.out.println("\nSeu Personagem:");
        System.out.println(jogador);
        
        System.out.println("\nAdversário:");
        System.out.println(adversario);
        
        if (jogador instanceof Guerreiro) {
            Guerreiro g = (Guerreiro) jogador;
            System.out.println("  - Força: " + g.getForca());
            System.out.println("  - Armadura: " + g.getArmadura());
            System.out.println("  - Ira: " + g.getIra() + "/100");
        } else if (jogador instanceof Arqueiro) {
            Arqueiro a = (Arqueiro) jogador;
            System.out.println("  - Agilidade: " + a.getAgilidade());
            System.out.println("  - Precisão: " + a.getPrecisao());
            System.out.println("  - Munição: " + a.getMunicao() + "/100");
        } else if (jogador instanceof Mago) {
            Mago m = (Mago) jogador;
            System.out.println("  - Mana: " + m.getMana() + "/" + m.getManaMaxima());
            System.out.println("  - Sabedoria: " + m.getSabedoria());
            System.out.println("  - Resistência Mágica: " + m.getResistenciaMagica());
        }
    }
    
    private void alternarTurno() {
        turnoAtual = (turnoAtual == jogador1) ? jogador2 : jogador1;
    }
    
    private void finalizarCombate() {
        System.out.println("\n" + "=".repeat(45));
        if (jogador1.estaVivo()) {
            System.out.println("✓ " + jogador1.getNome() + " VENCEU O COMBATE!");
            jogador1.adicionarExperiencia(50);
            System.out.println(jogador1.getNome() + " ganhou 50 de experiência!");
        } else {
            System.out.println("✓ " + jogador2.getNome() + " VENCEU O COMBATE!");
            jogador2.adicionarExperiencia(50);
            System.out.println(jogador2.getNome() + " ganhou 50 de experiência!");
        }
        System.out.println("Durou " + turno + " turnos!");
        System.out.println("=".repeat(45));
    }
    
    public int getTurno() {
        return turno;
    }
    
    public Personagem getTurnoAtual() {
        return turnoAtual;
    }
}
