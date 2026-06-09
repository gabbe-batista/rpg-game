package classes;

import exceptions.AcaoInvalidaException;
import exceptions.AtributoInvalidoException;
import exceptions.RecursoInsuficienteException;
import interfaces.Atacante;

public class Arqueiro extends Personagem implements Atacante {

    private int agilidade;
    private int precisao;
    private int municao;
    private static final int MUNICAO_MAXIMA = 100;

    public Arqueiro(String nome, int vida, int defesa, int inteligencia, int velocidade, int agilidade, int precisao) {
        super(nome, vida, defesa, inteligencia, velocidade);
        this.agilidade = agilidade;
        this.precisao = precisao;
        this.municao = MUNICAO_MAXIMA;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        if (agilidade < 0) {
            throw new AtributoInvalidoException("Agilidade não pode ser negativa.");
        }
        this.agilidade = agilidade;
    }

    public int getPrecisao() {
        return precisao;
    }

    public void setPrecisao(int precisao) {
        if (precisao < 0) {
            throw new AtributoInvalidoException("Precisão não pode ser negativa.");
        }
        this.precisao = precisao;
    }

    public int getMunicao() {
        return municao;
    }

    public void recuperarMunicao() {
        this.municao = MUNICAO_MAXIMA;
    }

    @Override
    public void atacar(Personagem alvo) {
        try {
            if (alvo == null) {
                throw new AcaoInvalidaException("Alvo inválido para ataque.");
            }
            if (this.municao <= 0) {
                throw new RecursoInsuficienteException(getNome() + " ficou sem munição!");
            }

            this.municao--;
            int acertoBaso = this.precisao + (this.agilidade / 2);
            int tentativaAcerto = new java.util.Random().nextInt(100);

            if (tentativaAcerto < acertoBaso) {
                int dano = (this.agilidade * 2) + (this.getPrecisao() / 2);

                if (tentativaAcerto < 15) {
                    dano = (int) (dano * 2.0);
                    System.out.println(getNome() + " acerta um DISPARO PRECISO em " + alvo.getNome() + "!");
                }

                alvo.receberDano(dano);
                System.out.println(getNome() + " atirou em " + alvo.getNome() + ", causando " + dano + " de dano.");
            } else {
                System.out.println(getNome() + " ERROU o disparo!");
            }

            System.out.println("Munição: " + this.municao + "/" + MUNICAO_MAXIMA);
        } catch (RecursoInsuficienteException | AcaoInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void esquivar() {
        System.out.println(getNome() + " se esquiva com agilidade! Reduz próximo dano em 50%.");
    }

    @Override
    public void receberDano(int dano) {
        if (dano < 0) {
            throw new AtributoInvalidoException("Dano não pode ser negativo.");
        }
        int danoReduzido = Math.max(1, (int) (dano * 0.8));
        super.receberDano(danoReduzido);
    }
}