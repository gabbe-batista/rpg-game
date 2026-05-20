package interfaces;

import classes.Personagem;

public interface CriadorPersonagem {
    
    CriadorPersonagem comNome(String nome);
    
    CriadorPersonagem comVida(int vida);
    
    CriadorPersonagem comDefesa(int defesa);
    
    CriadorPersonagem comInteligencia(int inteligencia);
    
    CriadorPersonagem comVelocidade(int velocidade);
    
    Personagem construir();
}
