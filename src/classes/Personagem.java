package classes;

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
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = Math.min(vida, this.vidaMaxima);
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void adicionarExperiencia(int exp) {
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
        this.defesa = defesa;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void receberDano(int dano) {
        int danoFinal = Math.max(1, dano - (this.defesa / 2));
        this.vida = Math.max(0, this.vida - danoFinal);
    }

    @Override
    public String toString() {
        return String.format("%s (Nv.%d) - Vida: %d/%d | Def: %d | Inteligência: %d | Velocidade: %d",
                nome, nivel, vida, vidaMaxima, defesa, inteligencia, velocidade);
    }
}
