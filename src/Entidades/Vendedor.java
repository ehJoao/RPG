package Entidades;

import Itens.Arma;
import Itens.ItemHeroi;
import Itens.PocaoHP;
import Itens.TiposHeroi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vendedor {

    private ArrayList<ItemHeroi> inventario; // Atributo inventario como um ArrayList de ItemHeroi

    /**
     * Construtor Vendedor
     */
    public Vendedor() {
        this.inventario = new ArrayList<>();
    }


    /**
     * Método para adicionar item ao atributo inventario
     *
     * @param item
     */
    public void adicionarItem(ItemHeroi item) {
        // Adiciona um item ao inventario
        inventario.add(item);
    }

    /**
     * Método de imprimir inventário (ArrayList que recebe um item da classe ItemHeroi)
     */
    public void imprimirInventario() {
        System.out.println("Inventário do Vendedor:");
        int contador = 1;
        for (ItemHeroi item : inventario) {
            System.out.println("Item " + contador + ":");
            item.mostrarDetalhes();
            System.out.println("--------------------");
            contador++;
        }
    }


    public ArrayList<ItemHeroi> getInventario() {
        return inventario;
    }


    public void setInventario(ArrayList<ItemHeroi> inventario) {
        this.inventario = inventario;
    }

    /**
     * Método vender que recebe como parametros um heroi da classe Heroi e um item da classe ItemHeroi
     *
     * @param heroi
     */
    public void vender(Heroi heroi) {
        boolean continuarCompra = true;
        ArrayList<ItemHeroi> itensComprados = new ArrayList<>();

        while (continuarCompra) {
            System.out.println("Digite o índice do item que deseja comprar ou 0 para seguir em frente:");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            if (index == 0) {
                // Opção para seguir em frente sem comprar mais itens
                System.out.println("Você escolheu seguir em frente sem comprar mais itens.");
                System.out.println("--------------------");
                continuarCompra = false;
            } else if (index >= 1 && index <= inventario.size()) {
                ItemHeroi item = inventario.get(index - 1);

                if (!itensComprados.contains(item)) {
                    boolean permitido = false;

                    for (TiposHeroi heroiPermitido : item.getTiposHeroi()) {
                        if (heroiPermitido.toString().equals(heroi.getClass().getSimpleName().toUpperCase())) {
                            permitido = true;
                            break;
                        }
                    }
                    if (permitido) {
                        if (heroi.getOuro() >= item.getPreco()) {
                            if (item instanceof Arma) {
                                Arma arma = (Arma) item;
                                heroi.setArma(arma);
                            } else if (item instanceof PocaoHP) {
                                PocaoHP pocao = (PocaoHP) item;
                                heroi.adicionarPocao(pocao);
                            }

                            heroi.decrementarOuro(item.getPreco());
                            itensComprados.add(item);
                            inventario.remove(item);
                            System.out.println("Compra realizada com sucesso!");
                            System.out.println("Você comprou o item: " + item.getNome());
                            System.out.println("O valor do item é: " + item.getPreco());
                            System.out.println("Seu ouro restante: " + heroi.getOuro());
                            System.out.println("--------------------");
                            imprimirInventario();
                        } else {
                            System.out.println("O herói não possui ouro suficiente para comprar este item.");
                        }
                    } else {
                        System.out.println("Item do vendedor não encontrado");
                    }
                    heroi.mostrarDetalhes();
                } else {
                    System.out.println("Esse item já foi comprado. Escolha outro item.");
                }
            } else {
                System.out.println("Índice inválido. Por favor, escolha um índice válido.");
            }
        }



    }


}
