package classes;

import exceptions.AtributoInvalidoException;
import exceptions.PersonagemInvalidoException;

public abstract class Personagem {

    private String nome;
    private int vida;
    private int vidaMaxima;
    private int nivel;
    private int experiencia;
    private int defesa;
    private int inteligencia;
    private int velocidade;

    public Personagem(String nome, int vida, int defesa, int inteligencia, int velocidade) {
        if (nome == null || nome.isBlank()) {
            throw new PersonagemInvalidoException("Nome do personagem é obrigatório.");
        }
        if (vida <= 0) {
            throw new AtributoInvalidoException("Vida inicial deve ser maior que zero.");
        }
        if (defesa < 0 || inteligencia < 0 || velocidade < 0) {
            throw new AtributoInvalidoException("Atributos não podem ser negativos.");
        }

        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.defesa = defesa;
        this.inteligencia = inteligencia;
        this.velocidade = velocidade;
        this.nivel = 1;
        this.experiencia = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new PersonagemInvalidoException("Nome do personagem é obrigatório.");
        }
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            throw new AtributoInvalidoException("Vida não pode ser negativa.");
        }
        this.vida = Math.min(vida, this.vidaMaxima);
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        if (vidaMaxima <= 0) {
            throw new AtributoInvalidoException("Vida máxima deve ser maior que zero.");
        }
        this.vidaMaxima = vidaMaxima;
        this.vida = Math.min(this.vida, this.vidaMaxima);
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel <= 0) {
            throw new AtributoInvalidoException("Nível deve ser maior que zero.");
        }
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void adicionarExperiencia(int exp) {
        if (exp < 0) {
            throw new AtributoInvalidoException("Experiência não pode ser negativa.");
        }
        this.experiencia += exp;
        if (this.experiencia >= 100) {
            subirDeNivel();
        }
    }

    private void subirDeNivel() {
        this.nivel++;
        this.experiencia = 0;
        this.vidaMaxima += 10;
        this.vida = this.vidaMaxima;
        this.defesa += 2;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        if (defesa < 0) {
            throw new AtributoInvalidoException("Defesa não pode ser negativa.");
        }
        this.defesa = defesa;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        if (inteligencia < 0) {
            throw new AtributoInvalidoException("Inteligência não pode ser negativa.");
        }
        this.inteligencia = inteligencia;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        if (velocidade < 0) {
            throw new AtributoInvalidoException("Velocidade não pode ser negativa.");
        }
        this.velocidade = velocidade;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void receberDano(int dano) {
        if (dano < 0) {
            throw new AtributoInvalidoException("Dano não pode ser negativo.");
        }
        int danoFinal = Math.max(1, dano - (this.defesa / 2));
        this.vida = Math.max(0, this.vida - danoFinal);
    }

    @Override
    public String toString() {
        return String.format("%s (Nv.%d) - Vida: %d/%d | Def: %d | Inteligência: %d | Velocidade: %d",
                nome, nivel, vida, vidaMaxima, defesa, inteligencia, velocidade);
    }
}
