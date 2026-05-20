package classes;

import interfaces.CriadorPersonagem;

public class CriadorMago implements CriadorPersonagem {
    
    private String nome;
    private int vida;
    private int defesa;
    private int inteligencia;
    private int velocidade;
    private int mana;
    private int sabedoria;
    
    public CriadorMago() {
        // Valores padrão
        this.vida = 60;
        this.defesa = 8;
        this.inteligencia = 28;
        this.velocidade = 18;
        this.mana = 100;
        this.sabedoria = 25;
    }
    
    @Override
    public CriadorMago comNome(String nome) {
        this.nome = nome;
        return this;
    }
    
    @Override
    public CriadorMago comVida(int vida) {
        this.vida = vida;
        return this;
    }
    
    @Override
    public CriadorMago comDefesa(int defesa) {
        this.defesa = defesa;
        return this;
    }
    
    @Override
    public CriadorMago comInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
        return this;
    }
    
    @Override
    public CriadorMago comVelocidade(int velocidade) {
        this.velocidade = velocidade;
        return this;
    }
    
    public CriadorMago comMana(int mana) {
        this.mana = mana;
        return this;
    }
    
    public CriadorMago comSabedoria(int sabedoria) {
        this.sabedoria = sabedoria;
        return this;
    }
    
    @Override
    public Mago construir() {
        if (this.nome == null || this.nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do Mago é obrigatório!");
        }
        return new Mago(this.nome, this.vida, this.defesa, this.inteligencia, this.velocidade, this.mana, this.sabedoria);
    }
}
