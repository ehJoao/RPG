package Entidades;
import Itens.Arma;
import Itens.PocaoHP;
import java.util.ArrayList;
public class Arqueiro extends Heroi {
    public Arqueiro(String nome, int vida, int forca, int nivel, int ouro) {
        super(nome, vida, forca, nivel, ouro);
    }

    /**
     * Método para combater o NPC
     *
     * @param npc
     * @return
     */
    @Override
    public Entidade atacar(NPC npc) {

        while (this.getVida()>=0 && npc.getVida()>=0) {


            int danoH = this.getForca() + this.getArma().getAtaque(); // O herói começa o ataque
            npc.setVida(npc.getVida() - danoH);
            System.out.println(npc.getVida()); //npc.subtrairVida(danoHeroi);


            if (npc.getVida() <= 0) {  // Verifica se o inimigo ainda está vivo
                System.out.println("O herói venceu!");
                this.subirNivel();
                this.incrementarVida(10);
                this.incrementarForca(1);
                this.incrementarOuro(10);
            } else {

                int danoInimigo = (int) (npc.getForca() * 1.1); //  ataque do inimigo 10% a mais do que devido a falta de proteçãop
                this.subtrairVida(danoInimigo); // parametro da funcao na classe Heroi
                System.out.println(this.getVida());

            }

        }
                if (this.getVida() <= 0) {
                    System.out.println("O herói foi derrotado.");
                    return npc;
                }else{
                    return this;
                }
            }

    /**
     * Método vindo da classe Entidade, obrigatório a todas.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("Detalhes do Arqueiro:");
        System.out.println("Nome: " + getNome());
        System.out.println("Vida: " + getVida());
        System.out.println("Força: " + getForca());
        System.out.println("Nível: " + getNivel());
        System.out.println("Ouro: " + getOuro());
        System.out.println("Arma:");
        if (getArma() != null) {
            System.out.println("- " + getArma().getNome());
        } else {
            System.out.println("Nenhuma arma equipada.");
        }

        System.out.println("Poções:");
        for (PocaoHP pocao : getPocoes()) {
            System.out.println("- " + pocao.getNome());
        }
    }

}