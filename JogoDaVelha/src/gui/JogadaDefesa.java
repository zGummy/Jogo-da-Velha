package gui;

public class JogadaDefesa {
    
    private int primeira;
    
    private int segunda;
    
    private int jogada;
    
    public JogadaDefesa (int primeira, 
            int segunda, 
            int jogada) {
        this.primeira = primeira;
        this.segunda = segunda;
        this.jogada = jogada;
    }

    public int getPrimeira() {
        return primeira;
    }

    public void setPrimeira(int primeira) {
        this.primeira = primeira;
    }

    public int getSegunda() {
        return segunda;
    }

    public void setSegunda(int segunda) {
        this.segunda = segunda;
    }

    public int getJogada() {
        return jogada;
    }

    public void setJogada(int jogada) {
        this.jogada = jogada;
    }
    
}
