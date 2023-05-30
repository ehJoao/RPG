package Entidades;

import Itens.PocaoHP;

public class Feiticeiro extends Heroi {
    public Feiticeiro(String nome, int vida, int forca, int nivel, int ouro) {
        super(nome, vida, forca, nivel, ouro);
    }

    /**
     * Método subscrito da classe NPC
     *
     * @param npc
     * @return
     */
    @Override
    public Entidade atacar(NPC npc) {

        while (this.getVida()>=0 && npc.getVida()>=0) {

            // O herói ataca
            int danoHeroi = this.getForca() + this.getArma().getAtaque();
            npc.setVida(npc.getVida() - danoHeroi);
            System.out.println(npc.getVida());
            //npc.subtrairVida(danoHeroi);
            // Verifica se o herói ainda está vivo
            // Verifica se o inimigo ainda está vivo
            if (npc.getVida() <= 0) {
                System.out.println("O herói venceu!");
                this.subirNivel();
                this.incrementarVida(10);
                this.incrementarForca(1);
                this.incrementarOuro(10);
            } else {
                // O inimigo ataca)
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
        System.out.println("Detalhes do Feiticeiro:");
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