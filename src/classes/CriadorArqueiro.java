package classes;

import exceptions.PersonagemInvalidoException;
import interfaces.CriadorPersonagem;

public class CriadorArqueiro implements CriadorPersonagem {
    
    private String nome;
    private int vida;
    private int defesa;
    private int inteligencia;
    private int velocidade;
    private int agilidade;
    private int precisao;
    
    public CriadorArqueiro() {
        // Valores padrão
        this.vida = 70;
        this.defesa = 12;
        this.inteligencia = 14;
        this.velocidade = 25;
        this.agilidade = 28;
        this.precisao = 30;
    }
    
    @Override
    public CriadorArqueiro comNome(String nome) {
        this.nome = nome;
        return this;
    }
    
    @Override
    public CriadorArqueiro comVida(int vida) {
        this.vida = vida;
        return this;
    }
    
    @Override
    public CriadorArqueiro comDefesa(int defesa) {
        this.defesa = defesa;
        return this;
    }
    
    @Override
    public CriadorArqueiro comInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
        return this;
    }
    
    @Override
    public CriadorArqueiro comVelocidade(int velocidade) {
        this.velocidade = velocidade;
        return this;
    }
    
    public CriadorArqueiro comAgilidade(int agilidade) {
        this.agilidade = agilidade;
        return this;
    }
    
    public CriadorArqueiro comPrecisao(int precisao) {
        this.precisao = precisao;
        return this;
    }
    
    @Override
    public Arqueiro construir() {
        if (this.nome == null || this.nome.isEmpty()) {
            throw new PersonagemInvalidoException("Nome do Arqueiro é obrigatório!");
        }
        try {
            return new Arqueiro(this.nome, this.vida, this.defesa, this.inteligencia, this.velocidade, this.agilidade, this.precisao);
        } catch (RuntimeException e) {
            throw new PersonagemInvalidoException("Falha ao construir Arqueiro: " + e.getMessage(), e);
        }
    }
}
