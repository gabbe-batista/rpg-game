package classes;

import exceptions.AcaoInvalidaException;
import exceptions.AtributoInvalidoException;
import exceptions.RecursoInsuficienteException;
import interfaces.Atacante;

public class Mago extends Personagem implements Atacante {

    private int mana;
    private int manaMaxima;
    private int sabedoria;
    private int resistenciaMagica;

    public Mago(String nome, int vida, int defesa, int inteligencia, int velocidade, int mana, int sabedoria) {
        super(nome, vida, defesa, inteligencia, velocidade);
        this.mana = mana;
        this.manaMaxima = mana;
        this.sabedoria = sabedoria;
        this.resistenciaMagica = inteligencia / 2;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new AtributoInvalidoException("Mana não pode ser negativa.");
        }
        this.mana = Math.min(mana, this.manaMaxima);
    }

    public int getManaMaxima() {
        return manaMaxima;
    }

    public int getSabedoria() {
        return sabedoria;
    }

    public void setSabedoria(int sabedoria) {
        if (sabedoria < 0) {
            throw new AtributoInvalidoException("Sabedoria não pode ser negativa.");
        }
        this.sabedoria = sabedoria;
    }

    public int getResistenciaMagica() {
        return resistenciaMagica;
    }

    public void regenerarMana() {
        int regeneracao = (this.sabedoria / 5) + 5;
        this.mana = Math.min(this.mana + regeneracao, this.manaMaxima);
    }

    @Override
    public void atacar(Personagem alvo) {
        int custoDeMana = 15;

        try {
            if (alvo == null) {
                throw new AcaoInvalidaException("Alvo inválido para ataque mágico.");
            }
            if (this.mana < custoDeMana) {
                throw new RecursoInsuficienteException(getNome() + " não tem mana suficiente!");
            }

            this.mana -= custoDeMana;
            int dano = (this.getInteligencia() * 2) + (this.sabedoria / 2);
            alvo.receberDano(dano);
            System.out.println(getNome() + " lança PROJÉTIL MÁGICO em " + alvo.getNome() + ", causando " + dano + " de dano.");
            System.out.println("Mana: " + this.mana + "/" + this.manaMaxima);
        } catch (RecursoInsuficienteException | AcaoInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lancarCura(Personagem alvo) {
        int custoDeMana = 25;

        try {
            if (alvo == null) {
                throw new AcaoInvalidaException("Alvo inválido para cura.");
            }
            if (this.mana < custoDeMana) {
                throw new RecursoInsuficienteException(getNome() + " não tem mana suficiente para curar!");
            }

            this.mana -= custoDeMana;
            int cura = (this.sabedoria * 2) + (this.getInteligencia() / 2);

            if (alvo.getVida() + cura > alvo.getVidaMaxima()) {
                alvo.setVida(alvo.getVidaMaxima());
            } else {
                alvo.setVida(alvo.getVida() + cura);
            }

            System.out.println(getNome() + " cura " + alvo.getNome() + " em " + cura + " pontos!");
        } catch (RecursoInsuficienteException | AcaoInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lancaExplosaoMagica(Personagem alvo) {
        int custoDeMana = 50;

        try {
            if (alvo == null) {
                throw new AcaoInvalidaException("Alvo inválido para explosão mágica.");
            }
            if (this.mana < custoDeMana) {
                throw new RecursoInsuficienteException(getNome() + " não tem mana suficiente para usar essa magia!");
            }

            this.mana -= custoDeMana;
            int dano = (this.getInteligencia() * 3) + (this.sabedoria);
            alvo.receberDano(dano);
            System.out.println(getNome() + " lança EXPLOSÃO MÁGICA em " + alvo.getNome() + ", causando " + dano + " de dano devastador!");
        } catch (RecursoInsuficienteException | AcaoInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void receberDano(int dano) {
        if (dano < 0) {
            throw new AtributoInvalidoException("Dano não pode ser negativo.");
        }
        int danoReduzido = Math.max(1, dano - (this.resistenciaMagica));
        super.receberDano(danoReduzido);
    }
}