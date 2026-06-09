package classes;

import exceptions.AcaoInvalidaException;
import exceptions.AtributoInvalidoException;
import interfaces.Atacante;

public class Guerreiro extends Personagem implements Atacante {

    private int forca;
    private int armadura;
    private int ira; // Medidor de raiva que aumenta dano
    private static final int IRA_MAXIMA = 100;

    public Guerreiro(String nome, int vida, int defesa, int inteligencia, int velocidade, int forca, int armadura) {
        super(nome, vida, defesa, inteligencia, velocidade);
        this.forca = forca;
        this.armadura = armadura;
        this.ira = 0;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        if (forca < 0) {
            throw new AtributoInvalidoException("Força não pode ser negativa.");
        }
        this.forca = forca;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        if (armadura < 0) {
            throw new AtributoInvalidoException("Armadura não pode ser negativa.");
        }
        this.armadura = armadura;
    }

    public int getIra() {
        return ira;
    }

    @Override
    public void atacar(Personagem alvo) {
        if (alvo == null) {
            throw new AcaoInvalidaException("Alvo inválido para ataque.");
        }
        int dano = (this.forca * 2) + (this.ira / 10);
        int critico = new java.util.Random().nextInt(100);
        
        if (critico < 20) {
            dano = (int) (dano * 1.5);
            System.out.println(getNome() + " acerta um GOLPE CRÍTICO em " + alvo.getNome() + "!");
        }
        
        alvo.receberDano(dano);
        this.ira = Math.min(IRA_MAXIMA, this.ira + 15);
        System.out.println(getNome() + " atacou " + alvo.getNome() + " causando " + dano + " de dano.");
        System.out.println("Ira: " + this.ira + "/" + IRA_MAXIMA);
    }

    public void usarHabilidadeEspecial(Personagem alvo) {
        try {
            if (alvo == null) {
                throw new AcaoInvalidaException("Alvo inválido para habilidade especial.");
            }

            if (this.ira >= 50) {
                int dano = (this.forca * 3) + (this.ira / 5);
                alvo.receberDano(dano);
                this.ira = 0;
                System.out.println(getNome() + " usa ATAQUE FURIOSO em " + alvo.getNome() + ", causando " + dano + " de dano!");
            } else {
                System.out.println(getNome() + " não tem ira suficiente! (" + this.ira + "/50)");
            }
        } catch (AcaoInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void receberDano(int dano) {
        if (dano < 0) {
            throw new AtributoInvalidoException("Dano não pode ser negativo.");
        }
        int danoReduzido = Math.max(1, dano - (this.armadura / 2));
        super.receberDano(danoReduzido);
    }
}