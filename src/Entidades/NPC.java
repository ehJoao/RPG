package Entidades;

public class NPC extends Entidade {
    public NPC(String nome, int vida, int forca) {
        super(nome, vida, forca);
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Detalhes do Inimigo:");
        System.out.println("Nome: " + getNome());
        System.out.println("Vida: " + getVida());
        System.out.println("Força: " + getForca());
    }
}