package Entidades;

/**
 * Classe abstrata Entidade
 */
public abstract class Entidade {

    private String nome;
    private int vida;
    private int forca;

    /**
     * Construtor da classe Entidade
     * @param nome
     * @param vida
     * @param forca
     */

    /** --------------------------- CONSTRUTORES  ----------------------------------*/
    public Entidade(String nome, int vida, int forca) {
        this.nome = nome;
        this.vida = vida;
        this.forca = forca;
    }

    /**
     * Classe abstrada mostrarDetalhes
     */
    public abstract void mostrarDetalhes();

    /** --------------------------- GETTERS AND SETTERS  ----------------------------------*/


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
        this.vida = vida;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }
}