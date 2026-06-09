package classes;

import exceptions.PersonagemInvalidoException;
import interfaces.CriadorPersonagem;

public class CriadorGuerreiro implements CriadorPersonagem {
    
    private String nome;
    private int vida;
    private int defesa;
    private int inteligencia;
    private int velocidade;
    private int forca;
    private int armadura;
    
    public CriadorGuerreiro() {
        // Valores padrão
        this.vida = 100;
        this.defesa = 20;
        this.inteligencia = 10;
        this.velocidade = 15;
        this.forca = 30;
        this.armadura = 25;
    }
    
    @Override
    public CriadorGuerreiro comNome(String nome) {
        this.nome = nome;
        return this;
    }
    
    @Override
    public CriadorGuerreiro comVida(int vida) {
        this.vida = vida;
        return this;
    }
    
    @Override
    public CriadorGuerreiro comDefesa(int defesa) {
        this.defesa = defesa;
        return this;
    }
    
    @Override
    public CriadorGuerreiro comInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
        return this;
    }
    
    @Override
    public CriadorGuerreiro comVelocidade(int velocidade) {
        this.velocidade = velocidade;
        return this;
    }
    
    public CriadorGuerreiro comForca(int forca) {
        this.forca = forca;
        return this;
    }
    
    public CriadorGuerreiro comArmadura(int armadura) {
        this.armadura = armadura;
        return this;
    }
    
    @Override
    public Guerreiro construir() {
        if (this.nome == null || this.nome.isEmpty()) {
            throw new PersonagemInvalidoException("Nome do Guerreiro é obrigatório!");
        }
        try {
            return new Guerreiro(this.nome, this.vida, this.defesa, this.inteligencia, this.velocidade, this.forca, this.armadura);
        } catch (RuntimeException e) {
            throw new PersonagemInvalidoException("Falha ao construir Guerreiro: " + e.getMessage(), e);
        }
    }
}
