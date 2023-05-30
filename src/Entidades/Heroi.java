package Entidades;
import Itens.Arma;
import Itens.PocaoHP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe abstrata Heroi, que deriva da classe mãe chamada Entidade
 */
public abstract class Heroi extends Entidade {
    private int nivel;
    private int ouro;
    private Arma arma;
    private ArrayList<PocaoHP> pocoes;




    /** ***************************Construtores ********************************/

    /**
     * Método construtor da classe Heroi
     * @param nome
     * @param vida
     * @param forca
     * @param nivel
     * @param ouro
     */
    public Heroi(String nome, int vida, int forca, int nivel, int ouro) {
        super(nome, vida, forca);
        this.nivel = nivel;
        this.ouro = ouro;
        this.pocoes = new ArrayList<PocaoHP>();
    }

    /**
     * Método para confrontar o NPC
     *
     * @param npc
     * @return
     */
    public abstract Entidade atacar(NPC npc);

    /**
     * Método para adicionar uma pocao ao ArrayList de PocaoHP
     * @param pocao
     */
    public void adicionarPocao(PocaoHP pocao) {  // MÉTODO NÃO PEDIDO NO ENUNCIADO
        this.pocoes.add(pocao);
    }

    /**
     * Método para retirar ouro da conta do Heroi caso ele compre um item
     * @param quantidade
     */
    public void decrementarOuro(int quantidade) {
        ouro -= quantidade;
    }

    /**
     * Metodo que usa pocao para se recuperar
     */
    public void usarPocao() {

        if(this.pocoes.size() > 0)
        {
            // Declaração da lista de poções disponíveis
            Scanner scanner = new Scanner(System.in);
            int i = 0;
            System.out.print("\n----------------------\n");
            for (PocaoHP pocao: pocoes) {
                System.out.print((++i) + " - " + pocao.getNome() + " (" + pocao.getCurar() + ")\n");
            }
            System.out.print("----------------------\n");

            do {
                System.out.println("\nDiz-me a poção que queres usar ou zero para não usar nenhuma");
                System.out.print("->");
                i = scanner.nextInt();

            }while(i<0 || i > pocoes.size());

            if(i > 0)
            {
                i--;
                incrementarVida(pocoes.get(i).getCurar());

                System.out.println("Parabens! Usou a poçao " + pocoes.get(i).getNome() + " e curou "+ pocoes.get(i).getCurar());
                pocoes.remove(i);
                System.out.println("A sua vida depois de usar a poção é: " + getVida());
            }
        }
        else {
            System.out.println("Sem Poção!");
        }
    }

    /**
     *  Método que busca a ppocao por nome
     * @param nomePocao
     * @return
     */
    private PocaoHP buscarPocaoPorNome(String nomePocao) {
        for (PocaoHP pocao : pocoes) {
            if (pocao.getNome().equals(nomePocao)) {
                return pocao;
            }
        }
        return null;
    }

    /**
     * Método que incrementa vida ao Heroi
     * @param quantidade
     */
    // Método para incrementar a vida do herói
    public void incrementarVida(int quantidade) {
        setVida(getVida() + quantidade);
    }


    /**
     * Método que subtrai vida do Heroi
     * @param quantidade
     */
    public void subtrairVida(int quantidade) {  // metodo chamado nos herois em si que recebem danos NPC
        setVida(getVida() - quantidade);
    }

    /**
     * Metodo para incrementar força quando o Herói vencer
     * @param quantidade
     */
    public void incrementarForca(int quantidade) {
        setForca(getForca() + quantidade);
    }

    /**
     * Método que sobe o nível do Heroi
     */
    public void subirNivel () {
        nivel++;
    }

    /**
     * Método para incrementar ouro quando o Herói vence
     * @param quantidade
     */
    public void incrementarOuro(int quantidade) {
        ouro += quantidade;
    }



    /************************* GETTERS AND SETTERS  *********************************/

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public ArrayList<PocaoHP> getPocoes() {
        return pocoes;
    }

    public void setPocoes(ArrayList<PocaoHP> pocoes) {
        this.pocoes = pocoes;
    }


}