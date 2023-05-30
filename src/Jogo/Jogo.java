package Jogo;

import Entidades.*;
import Itens.Arma;
import Itens.ItemHeroi;
import Itens.PocaoHP;
import Itens.TiposHeroi;

import java.util.ArrayList;
import java.util.Scanner;


public class Jogo {

    /******************* Instanciar os personagens *************************/
    private static Cavaleiro criarCavaleiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Cavaleiro:");
        String nome = scanner.nextLine();
        return new Cavaleiro(nome, 0, 0, 0, 0);
    }

    private static Feiticeiro criarFeiticeiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Feiticeiro:");
        String nome = scanner.nextLine();
        return new Feiticeiro(nome, 0, 0, 0, 0);
    }

    private static Arqueiro criarArqueiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Arqueiro:");
        String nome = scanner.nextLine();
        return new Arqueiro(nome, 0, 0, 0, 0);
    }


    /****************************************** Main *************************************************/
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean venceu;
        String resposta = "";

        /*********** Selecionar o tipo de herói *******************/
        int personagem; // int para minimizar erros do utilizador
        Heroi heroi = null;
        boolean opcaoValida = false;

        while (!opcaoValida) {
            System.out.println("Selecione o tipo de herói: ");
            System.out.println("1. Cavaleiro do Reino");
            System.out.println("2. Feiticeiro do Reino");
            System.out.println("3. Arqueiro do Reino");

            try {
                String input = scanner.nextLine();
                personagem = Integer.parseInt(input);

                switch (personagem) {
                    case 1:
                        heroi = criarCavaleiro();
                        opcaoValida = true;
                        break;
                    case 2:
                        heroi = criarFeiticeiro();
                        opcaoValida = true;
                        break;
                    case 3:
                        heroi = criarArqueiro();
                        opcaoValida = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Claramente és um novato nos RPGs! Tens de digitar 1, 2 ou 3 e depois clicar na tecla Enter.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Claramente és um novato nos RPGs! Tens de digitar 1, 2 ou 3 e depois clicar Enter.");
            }
        }

        opcaoValida = false;
        int nivel = 0;

        while (!opcaoValida) {
            try {
                System.out.println("Selecione a dificuldade: ");
                System.out.println("1. Fácil");
                System.out.println("2. Herói");

                String input = scanner.nextLine();
                nivel = Integer.parseInt(input);

                if (nivel == 1 || nivel == 2) {
                    opcaoValida = true;
                } else {
                    System.out.println("Opção inválida. Clica no 1.ou 2. e depois em Enter. SE QUERES CHEGAR LONGE ESCOLHE FÁCIL!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Clica no 1. ou 2. e depois em Enter. SE QUERES CHEGAR LONGE ESCOLHE FÁCIL!");
            }
        }

        //Definir os pontos de criação de personagem
        int pontosCriacao;
        int pontosVida;
        int pontosForca = 0;

        do {
            if (nivel == 1) {
                pontosCriacao = 300;
                System.out.println("Por seres Sincero, vais ter 300 pontos para criação de personagem.");
            } else if (nivel == 2) {
                pontosCriacao = 220;
                System.out.println("Tens a mania, vais ter 220 pontos de criação de personagem. Brevemente Game Over aparecerá na tua tela!");
            } else {
                System.out.println("Opção inválida. Compreendo que por vezes o medo preveleça nas pessoas fracas. A Encerrar o jogo.");
                return;
            }
//*************************************** Distribuição de pontos de Criação para a Vida **********************************
            System.out.println("Está na altura de distribuires os pontos entre HP e Força para assim criares a tua personagem. \n Atenção cada ponto de força vale 5 pontos de criação");

            System.out.println("Pontos de HP disponíveis: " + pontosCriacao);
            System.out.println("Quantos pontos vais gastar em HP? ");
            pontosVida = scanner.nextInt();

            if (pontosVida > pontosCriacao) {
                System.out.println("Achas que podes gastar mais pontos do que aqueles que tens??? A resposta é NÃO!!!");
                continue;
            }
//*************************************** Distribuição de pontos de Criação para a força **********************************

            System.out.println("Pontos de força disponíveis: " + ((pontosCriacao - pontosVida) / 5));
            System.out.println("Quantos pontos vais gastar na Força? ");

            pontosForca = scanner.nextInt();

            if (pontosForca > (pontosCriacao - pontosVida) / 5) {
                System.out.println("Achas que podes gastar mais pontos do que aqueles que tens??? Por mais que tentes nunca deixrás de ser um frango!!!");
                continue;
            }

            if (pontosVida + pontosForca * 5 != pontosCriacao) {
                System.out.println("Distribuição inválida. A soma dos pontos de vida e pontos de força deve ser igual aos pontos de criação.");
            }
        } while (pontosVida + pontosForca * 5 != pontosCriacao);

        //Atribuir vida e força ao herói
        heroi.setVida(pontosVida);
        heroi.setForca(pontosForca);

        int ouro;
        if (nivel == 1) {
            ouro = 20;
        } else {
            ouro = 15;
        }
        heroi.setOuro(ouro);

        venceu = labirinto(0, heroi);
        if (venceu) {
            System.out.println("Parabéns Ganhaste!");

            heroi.mostrarDetalhes();
        } else {
            System.out.println("Game Over");
            System.out.println("deseja tentar de novo? S/N");
            resposta = scanner.next();
            resposta = resposta.toUpperCase();//input de reposta. se resposta for sim corre tudo de novo

        }
        while (resposta.equals("S")) ;
    }

    private static void reset(String message)
    {
        if(message != null) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + message + "\n");

            int op;
            do{
                System.out.println("\n1 - Continuar a jogar!");
                System.out.println("\n2 - Sair");
                op = scanner.nextInt();
            }while (op < 1 || op > 2);

            if(op == 1) {
                main(null);
            }
        }
    }

    public static boolean labirinto(int porta, Heroi heroi) {
        Scanner scanner = new Scanner(System.in);
        boolean player = false;

        // instanciar inimigos
        NPC npc1 = new NPC("Sombranoth, o Espreitador Sombrio", 50, 40);
        //npc1.mostrarDetalhes();
        NPC npc2 = new NPC("Seraphiel, o Guardião da Luz", 50, 40);
        // npc2.mostrarDetalhes();
        NPC npc3 = new NPC("Arthus, o Mestre dos Enigmas", 55, 45);
        // npc3.mostrarDetalhes();
        NPC npc4 = new NPC("Pyron, o Elemental Ardente", 55, 50);
        //npc4.mostrarDetalhes();
        NPC npc5 = new NPC("Malachai, o Senhor das Ruínas", 70, 65);
        //npc5.mostrarDetalhes();
        NPC npc6 = new NPC("Sylva, o Ladrão das Sombras", 60, 50);
        // npc6.mostrarDetalhes();
        NPC npc7 = new NPC("Umbra, a Sombra Sinistra", 90, 80);
        //npc7.mostrarDetalhes();

        NPC npc8 = new NPC("Magnus, o Sábio Arcano", 100, 95);
        // npc8.mostrarDetalhes();

        NPC npc9 = new NPC("Chronos, o Guardião Temporal", 110, 95);
        // npc9.mostrarDetalhes();

        NPC npc10 = new NPC("Nihilus, o Mestre Supremo", 50, 50);
        // npc10.mostrarDetalhes();

        // Todos os tipo de heroi
        ArrayList<TiposHeroi> todosTiposHeroi = new ArrayList<>();
        todosTiposHeroi.add(TiposHeroi.CAVALEIRO);
        todosTiposHeroi.add(TiposHeroi.ARQUEIRO);
        todosTiposHeroi.add(TiposHeroi.FEITICEIRO);
        // Combinações
        ArrayList<TiposHeroi> heroiCavaleiro = new ArrayList<>();
        heroiCavaleiro.add(TiposHeroi.CAVALEIRO);

        ArrayList<TiposHeroi> heroiArqueiro = new ArrayList<>();
        heroiArqueiro.add(TiposHeroi.ARQUEIRO);

        ArrayList<TiposHeroi> heroiFeiticeiro = new ArrayList<>();
        heroiFeiticeiro.add(TiposHeroi.FEITICEIRO);

// instância das PocaoHP para todos


        PocaoHP pocao1 = new PocaoHP("Poção de Cura", 3, todosTiposHeroi, 10);
        PocaoHP pocao2 = new PocaoHP("Poção de Cura Superior", 8, todosTiposHeroi, 40);
        PocaoHP pocao3 = new PocaoHP("Sumo de Frutos do Bosque", 12, todosTiposHeroi, 80);
        PocaoHP pocao4 = new PocaoHP("Poção de Resistência", 15, todosTiposHeroi, 150);
        PocaoHP pocao5 = new PocaoHP("Poção Mágica", 25, todosTiposHeroi, 200);

        //Armas Para todos
        Arma arma1T = new Arma("Luvas de Couro", 1, todosTiposHeroi, 5);
        Arma arma2T = new Arma("Luvas de Prata", 15, todosTiposHeroi, 75);
        Arma arma3T = new Arma("Luvas de Ouro", 25, todosTiposHeroi, 150);


        // insta Armas
        Arma arma1C = new Arma("Espada de Ébano", 5, heroiCavaleiro, 5);
        Arma arma2C = new Arma("Espada do Guardião", 8, heroiCavaleiro, 15);
        Arma arma3C = new Arma("Espada de Platina", 12, heroiCavaleiro, 30);
        Arma arma4C = new Arma("Espada do Destemido", 15, heroiCavaleiro, 75);
        Arma arma5C = new Arma("Espada de Impacto", 25, heroiCavaleiro, 150);
      /*  Arma arma6C = new Arma("Espada Veloz", 150, heroiCavaleiro, 250);
        Arma arma7C = new Arma("Espada Divina", 200, heroiCavaleiro, 400);
        Arma arma8C = new Arma("Espada Sagrada", 250, heroiCavaleiro, 500);
        Arma arma9C = new Arma("Espada de Prata", 300, heroiCavaleiro, 700);
        Arma arma10C = new Arma("Espada de Ouro", 500, heroiCavaleiro, 1500);*/

        Arma arma1A = new Arma("Arco Longo", 5, heroiArqueiro, 5);
        Arma arma2A = new Arma("Arco Recurvo", 8, heroiArqueiro, 15);
        Arma arma3A = new Arma("Arco Curto", 12, heroiArqueiro, 30);
        Arma arma4A = new Arma("Arco Composto", 15, heroiArqueiro, 75);
        Arma arma5A = new Arma("Arco de Caça", 25, heroiArqueiro, 150);
      /*  Arma arma6A = new Arma("Arco Élfico", 150, heroiArqueiro, 250);
        Arma arma7A = new Arma("Arco do Trovão", 200, heroiArqueiro, 400);
        Arma arma8A = new Arma("Arco da Tempestade", 250, heroiArqueiro, 500);
        Arma arma9A = new Arma("Arco das Sombras", 300, heroiArqueiro, 700);
        Arma arma10A = new Arma("Arco Celestial", 500, heroiArqueiro, 1500);*/

        Arma arma1F = new Arma("Cetro de Éter", 5, heroiFeiticeiro, 5);
        Arma arma2F = new Arma("Cetro  Antigo", 8, heroiFeiticeiro, 15);
        Arma arma3F = new Arma("Cetro  das Sombras", 12, heroiFeiticeiro, 30);
        Arma arma4F = new Arma("Cetro  da Sabedoria Arcana", 15, heroiFeiticeiro, 75);
        Arma arma5F = new Arma("Cetro  da Chama Ardente", 25, heroiFeiticeiro, 150);
       /* Arma arma6F = new Arma("Cetro  da Geada Eterna", 150, heroiFeiticeiro, 250);
        Arma arma7F = new Arma("Cetro do Trovão Relampejante", 200, heroiFeiticeiro, 400);
        Arma arma8F = new Arma("Cetro da Cura Vital", 250, heroiFeiticeiro, 500);
        Arma arma9F = new Arma("Cetro da Ilusão Hipnótica", 300, heroiFeiticeiro, 700);
        Arma arma10F = new Arma("Cetro da Natureza Primordial", 500, heroiFeiticeiro, 1500);*/

        Vendedor vendedor1 = new Vendedor();
        vendedor1.adicionarItem(pocao1);
        vendedor1.adicionarItem(pocao2);
        vendedor1.adicionarItem(pocao3);
        vendedor1.adicionarItem(pocao4);
        vendedor1.adicionarItem(pocao5);
        vendedor1.adicionarItem(arma1T);
        vendedor1.adicionarItem(arma2T);
        vendedor1.adicionarItem(arma3T);
        vendedor1.adicionarItem(arma1C);
        vendedor1.adicionarItem(arma2C);
        vendedor1.adicionarItem(arma3C);
        vendedor1.adicionarItem(arma4C);
        vendedor1.adicionarItem(arma5C);
     /*   vendedor1.adicionarItem(arma6C);
        vendedor1.adicionarItem(arma7C);
        vendedor1.adicionarItem(arma8C);
        vendedor1.adicionarItem(arma9C);
        vendedor1.adicionarItem(arma10C);*/
        vendedor1.adicionarItem(arma1A);
        vendedor1.adicionarItem(arma2A);
        vendedor1.adicionarItem(arma3A);
        vendedor1.adicionarItem(arma4A);
        vendedor1.adicionarItem(arma5A);
      /*  vendedor1.adicionarItem(arma6A);
        vendedor1.adicionarItem(arma7A);
        vendedor1.adicionarItem(arma8A);
        vendedor1.adicionarItem(arma9A);
        vendedor1.adicionarItem(arma10A);*/
        vendedor1.adicionarItem(arma1F);
        vendedor1.adicionarItem(arma2F);
        vendedor1.adicionarItem(arma3F);
        vendedor1.adicionarItem(arma4F);
        vendedor1.adicionarItem(arma5F);
      /*  vendedor1.adicionarItem(arma6F);
        vendedor1.adicionarItem(arma7F);
        vendedor1.adicionarItem(arma8F);
        vendedor1.adicionarItem(arma9F);
        vendedor1.adicionarItem(arma10F);*/


// Iniciar o labirinto


        switch (porta) {
            case 0:
                System.out.println("Há muito tempo, em um reino distante, erguia-se majestoso o Castelo de Ébano. \n Este castelo, conhecido por sua beleza sombria e misteriosa, escondia um segredo: \n seu interior se transformara em um labirinto repleto de desafios e perigos mortais. \n No coração desse labirinto, habitava um vendedor misterioso, cujas origens eram desconhecidas. \n Os rumores diziam que o vendedor possuía itens raros e poderosos, capazes de auxiliar os bravos aventureiros que ousassem se aventurar no labirinto do Castelo de Ébano. \n Contudo, a única maneira de acessar sua loja era enfrentando os terríveis inimigos que aguardavam além das portas espalhadas pelo labirinto.\n\n Agora, o " + heroi.getClass().getSimpleName() + " " + heroi.getNome() + " um herói destemido adentra os corredores sinuosos do castelo, determinado a desvendar os segredos ocultos e desafiar cada inimigo que cruzar seu caminho. \n Os inimigos são muitos e cada um deles possui uma habilidade única, colocando à prova as habilidades e estratégias do herói.");
                System.out.println( "Detalhes do herói: Força: " + heroi.getForca() + ", Vida: " + heroi.getVida() + ", Ouro: " + heroi.getOuro() + ".");
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                System.out.println("Você está na porta do Castelo de Ébano, onde...");
                System.out.println("o vendedor mesterioso lhe apresenta os itens que tem para vender");
                System.out.println("Itens disponíveis para compra:");
                System.out.println("Deseja comprar algum item? (Digite o número correspondente ou 0 para avançar sem comprar)");
                vendedor1.imprimirInventario();
                vendedor1.vender(heroi);
                do {
                    System.out.println("Depois de entrar no Castelo Labirinto, o personagem se depara com duas portas imponentes à sua frente.");
                    System.out.println("As portas são adornadas com símbolos e inscrições misteriosas, acrescentando uma aura de intriga e mistério ao ambiente.");
                    System.out.println("\nPorta 1: Porta das Sombras.");
                    System.out.println("A primeira porta é feita de madeira escura e está envolta por um leve nevoeiro.");
                    System.out.println("Ela emana uma aura de escuridão e segredos ocultos.");
                    System.out.println("As inscrições nas laterais da porta estão escritas em uma língua ancestral, dificultando a compreensão do seu significado.");
                    System.out.println("Aqueles que escolherem essa porta podem se encontrar em um ambiente repleto de desafios relacionados à furtividade, ilusões e confrontos com criaturas sombrias. ");
                    System.out.println("\nPorta 2: Porta da Luz:");
                    System.out.println("A segunda porta é feita de mármore branco e brilha com uma suave luminosidade.");
                    System.out.println("Ela possui gravuras de símbolos solares e estrelas radiantes.");
                    System.out.println("As inscrições ao redor da porta são escritas em uma linguagem arcaica, evocando poderes divinos.");
                    System.out.println("Aqueles que optarem por essa porta podem se encontrar em um ambiente luminoso, com desafios relacionados à purificação, cura e encontros com seres celestiais.");
                    System.out.println("\n O personagem agora enfrenta a decisão de escolher uma das portas, ciente de que cada uma levará a desafios únicos e diferentes experiências dentro do labirinto do castelo.");
                    System.out.println("");
                    System.out.println("Qual caminho o personagem decidirá seguir? A escolha está em suas mãos.");
                    System.out.println("Porta 1: Porta das Sombras.");
                    System.out.println("Porta 2: Porta da Luz");

                    porta = scanner.nextInt();
                } while (porta != 1 && porta != 2);

                labirinto(porta, heroi);
                break;

            case 1:
                System.out.println("Com coragem escolhes a Porta das Sombras!");
                System.out.println("Por detrás da Porta está Sombranoth, o Espreitador Sombrio");
                npc1.mostrarDetalhes();
                System.out.println("*****************");
                System.out.println("Resultado da batalha Hereoi vs Sombranoth");

                player = (heroi.atacar(npc1) == heroi);
                if (player == true) {


                    heroi.usarPocao();
                    heroi.mostrarDetalhes();

                    System.out.println("o vendedor mesterioso lhe apresenta os itens que tem para vender");
                    System.out.println("Itens disponíveis para compra:");
                    System.out.println("Deseja comprar algum item? (Digite o número correspondente ou 0 para avançar sem comprar)");
                    vendedor1.imprimirInventario();
                    vendedor1.vender(heroi);

                    System.out.println("Mais 2 porrtas surgem no labirinto...");
                    System.out.println("Porta 3: Porta do Conhecimento Proibido");
                    System.out.println("Feita de metal envelhecido, com símbolos e inscrições antigas. Guarda segredos proibidos e conhecimentos ocultos. \n Desafios relacionados a quebra de códigos e enigmas complexos aguardam aqueles que a escolherem.");
                    System.out.println("Porta 4: Porta da Provação Elemental");
                    System.out.println("Formada por vinhas entrelaçadas e folhagens vibrantes. Gravuras dos quatro elementos - fogo, água, terra e ar. \n Conduz a desafios envolvendo controle e manipulação dos elementos, superando obstáculos e enfrentando criaturas elementais.");
                    System.out.println("Qual caminho o personagem decidirá seguir? A escolha está em suas mãos.");
                    System.out.println("Porta 3: Porta do Conhecimento Proibido");
                    System.out.println("Porta 4: Porta da Provação Elemental");
                    do {
                        porta = scanner.nextInt();
                    } while (porta != 3 && porta != 4);

                    labirinto(porta, heroi);
                }else reset("Game Over! Sua jornada chegou a um fim prematuro na Porta 1: Porta das Sombras. \nParece que a coragem não foi suficiente para superar o desafio. \nMas não se preocupe, derrotar todos os inimigos é mais difícil do que parece. \nVocê foi apenas mais um a sucumbir diante da minha maestria! Melhore suas habilidades e talvez, quem sabe, você possa tentar novamente. \nBoa sorte na próxima vez, você vai precisar!");
                break;

            case 2:
                System.out.println("Com coragem escolhes a Porta da Luz!");
                System.out.println("Por detrás da Porta está Seraphiel, o Guardião da Luz");
                npc2.mostrarDetalhes();

                player = (heroi.atacar(npc5) == heroi);
                if (player == true) {

                    heroi.usarPocao();
                    heroi.mostrarDetalhes();

                    System.out.println("o vendedor mesterioso lhe apresenta os itens que tem para vender");
                    System.out.println("Itens disponíveis para compra:");
                    System.out.println("Deseja comprar algum item? (Digite o número correspondente ou 0 para avançar sem comprar)");
                    vendedor1.imprimirInventario();
                    vendedor1.vender(heroi);

                System.out.println("Mais 2 portas surgem no labirnto...");
                System.out.println("Porta 4: Porta da Provação Elemental");
                System.out.println("Formada por vinhas entrelaçadas e folhagens vibrantes. Gravuras dos quatro elementos - fogo, água, terra e ar. \n Conduz a desafios envolvendo controle e manipulação dos elementos, superando obstáculos e enfrentando criaturas elementais.");
                System.out.println("Porta 5: Porta da Ruína Antiga");
                System.out.println("A quinta porta é feita de pedra maciça e está coberta de musgo e rachaduras. Suas inscrições são ilegíveis devido ao desgaste do tempo.\n Aqueles que escolherem essa porta enfrentarão desafios relacionados a ruínas antigas, armadilhas mortais e encontros com criaturas sombrias que espreitam nas profundezas.");
                    System.out.println("Qual caminho o personagem decidirá seguir? A escolha está em suas mãos.");
                    System.out.println("Porta 4: Porta da Provação Elemental");
                    System.out.println("Porta 5: Porta da Ruína Antiga");
                do {
                    porta = scanner.nextInt();
                } while (porta != 4 && porta != 5);
                labirinto(porta, heroi);
        } else reset("Game Over! Sua jornada chegou a um fim prematuro na Porta 2: Porta da Luz. \nParece que a coragem não foi suficiente para superar o desafio. \nMas não se preocupe, derrotar todos os inimigos é mais difícil do que parece. \nVocê foi apenas mais um a sucumbir diante da minha maestria! Melhore suas habilidades e talvez, quem sabe, você possa tentar novamente. \nBoa sorte na próxima vez, você vai precisar!");
                break;

            case 3:
                System.out.println("Com coragem escolhes a Porta do Conhecimento Proibido!");
                System.out.println("Por detrás da Porta está Arthus, o Mestre dos Enigmas");
                npc3.mostrarDetalhes();

                player = (heroi.atacar(npc3) == heroi);
                if (player == true) {

                    heroi.usarPocao();
                    heroi.mostrarDetalhes();

                System.out.println("Mais 2 portas surgem no labirnto...");
                System.out.println("Porta 6: Porta do Tesouro Oculto");
                System.out.println("A sexta porta é ornamentada com detalhes dourados e preciosos, dando a impressão de riqueza e opulência. \n Símbolos de fortuna e prosperidade estão gravados em sua superfície reluzente.\n Aqueles que escolherem essa porta se depararão com desafios relacionados a quebra de enigmas, obstáculos traiçoeiros escondidos nas profundezas do labirinto.");
                System.out.println("Porta 7: Porta da Escuridão Profunda");
                System.out.println("A sétima porta é feita de madeira negra, sem adornos ou inscrições visíveis. \n Um arrepio percorre a espinha daqueles que se aproximam dela, como se a própria escuridão estivesse emanando do outro lado. \n Aqueles que escolherem essa porta enfrentarão desafios ligados a criaturas das trevas, labirintos obscuros e batalhas contra sombras ameaçadoras.");
                    System.out.println("Qual caminho o personagem decidirá seguir? A escolha está em suas mãos.");

                do {
                    porta = scanner.nextInt();
                } while (porta != 6 && porta != 7);
                labirinto(porta, heroi);
                } else reset("Game Over! Sua jornada chegou a um fim prematuro na Porta 3: Porta do Conhecimento Proibido. \nParece que a coragem não foi suficiente para superar o desafio. \nMas não se preocupe, derrotar todos os inimigos é mais difícil do que parece. \nVocê foi apenas mais um a sucumbir diante da minha maestria! Melhore suas habilidades e talvez, quem sabe, você possa tentar novamente. \nBoa sorte na próxima vez, você vai precisar!");
                break;

            case 4:
                System.out.println("Com coragem escolhes a Porta da Provação Elemental!");
                System.out.println("Por detrás da Porta está Pyron, o Elemental Ardente");
                npc4.mostrarDetalhes();

                player = (heroi.atacar(npc4) == heroi);
                if (player == true) {

                    heroi.usarPocao();
                    heroi.mostrarDetalhes();

                    System.out.println("o vendedor mesterioso lhe apresenta os itens que tem para vender");
                    System.out.println("Itens disponíveis para compra:");
                    System.out.println("Deseja comprar algum item? (Digite o número correspondente ou 0 para avançar sem comprar)");
                    vendedor1.imprimirInventario();
                    vendedor1.vender(heroi);

                    System.out.println("Mais 2 portas surgem no labirnto...");
                    System.out.println("Porta 7: Porta da Escuridão Profunda");
                    System.out.println("A sétima porta é feita de madeira negra, sem adornos ou inscrições visíveis. \n Um arrepio percorre a espinha daqueles que se aproximam dela, como se a própria escuridão estivesse emanando do outro lado. \n Aqueles que escolherem essa porta enfrentarão desafios ligados a criaturas das trevas, labirintos obscuros e batalhas contra sombras ameaçadoras.");System.out.println("Porta 8: Porta da Sabedoria Antiga");System.out.println("A oitava porta é feita de carvalho maciço, esculpida com símbolos de conhecimento e sabedoria. \n As inscrições gravadas parecem contar histórias antigas e transmitir ensinamentos ocultos. \n Aqueles que escolherem essa porta se depararão com desafios que exigem sabedoria, solução de enigmas complexos e enfrentamento de desafios intelectuais.");
                    System.out.println("Qual caminho o personagem decidirá seguir? A escolha está em suas mãos.");
                    System.out.println("Porta 7: Porta da Escuridão Profunda");
                    System.out.println("Porta 8: Porta da Sabedoria Antiga");
                    do {
                    porta = scanner.nextInt();
                } while (porta != 7 && porta != 8);
                labirinto(porta, heroi);

                } else reset("Game Over! Sua jornada chegou a um fim prematuro na Porta 4: Porta da Provação Elemental. \nParece que a coragem não foi suficiente para superar o desafio. \nMas não se preocupe, derrotar todos os inimigos é mais difícil do que parece. \nVocê foi apenas mais um a sucumbir diante da minha maestria! Melhore suas habilidades e talvez, quem sabe, você possa tentar novamente. \nBoa sorte na próxima vez, você vai precisar!");

                break;

            case 5:
                System.out.println("Com coragem escolhes a Porta da Ruína Antiga!");
                System.out.println("Por detrás da Porta estáMalachai, o Senhor das Ruínas");
                npc5.mostrarDetalhes();

                player = (heroi.atacar(npc5) == heroi);
                if (player == true) {

                    heroi.usarPocao();
                    heroi.mostrarDetalhes();

                    System.out.println("o vendedor mesterioso lhe apresenta os itens que tem para vender");
                    System.out.println("Itens disponíveis para compra:");
                    System.out.println("Deseja comprar algum item? (Digite o número correspondente ou 0 para avançar sem comprar)");
                    vendedor1.imprimirInventario();
                    vendedor1.vender(heroi);

                System.out.println("Mais 2 portas surgem no labirnto...");
                System.out.println("Porta 8: Porta da Sabedoria Antiga");
                System.out.println("A oitava porta é feita de carvalho maciço, esculpida com símbolos de conhecimento e sabedoria.");
                System.out.println("as inscrições gravadas parecem contar histórias antigas e transmitir ensinamentos ocultos.");
                System.out.println("Aqueles que escolherem essa porta se depararão com desafios que exigem sabedoria, solução de enigmas complexos e enfrentamento de desafios intelectuais.");
                System.out.println("Porta 9: Porta do Tempo Perdido");
                System.out.println("A nona porta é feita de metal envelhecido e coberta por runas antigas.");
                System.out.println("Seu aspecto é misterioso, parecendo ter resistido ao passar dos séculos.");
                System.out.println("Aqueles que escolherem essa porta serão desafiados a enfrentar perigos que envolvem manipulação do tempo,");
                System.out.println("paradoxos temporais e encontros com criaturas que existem além dos limites do tempo.");
                System.out.println("Qual caminho o personagem decidirá seguir? A escolha está em suas mãos.");
                System.out.println("Porta 8: Porta da Sabedoria Antiga");
                System.out.println("Porta 9: Porta do Tempo Perdido");
                do {
                    porta = scanner.nextInt();
                } while (porta != 8 && porta != 9);
                labirinto(porta, heroi);
                } else reset("Game Over! Sua jornada chegou a um fim prematuro na Porta 5: Porta da Ruína Antiga. \nParece que a coragem não foi suficiente para superar o desafio. \nMas não se preocupe, derrotar todos os inimigos é mais difícil do que parece. \nVocê foi apenas mais um a sucumbir diante da minha maestria! Melhore suas habilidades e talvez, quem sabe, você possa tentar novamente. \nBoa sorte na próxima vez, você vai precisar!");
                break;
            case 6:
                System.out.println("Com coragem escolhes a Porta do Tesouro Oculto!");
                System.out.println("Por detrás da Porta está Sylva, o Ladrão das Sombras");
                npc6.mostrarDetalhes();

                player = (heroi.atacar(npc6) == heroi);
                if (player == true) {

                    heroi.usarPocao();
                    heroi.mostrarDetalhes();

                    System.out.println("o vendedor mesterioso lhe apresenta os itens que tem para vender");
                    System.out.println("Itens disponíveis para compra:");
                    System.out.println("Deseja comprar algum item? (Digite o número correspondente ou 0 para avançar sem comprar)");
                    vendedor1.imprimirInventario();
                    vendedor1.vender(heroi);

                    labirinto(10, heroi);
                } else reset("Game Over! Sua jornada chegou a um fim prematuro na Porta 6: Porta do Tesouro Oculto. \nParece que a coragem não foi suficiente para superar o desafio. \nMas não se preocupe, derrotar todos os inimigos é mais difícil do que parece. \nVocê foi apenas mais um a sucumbir diante da minha maestria! Melhore suas habilidades e talvez, quem sabe, você possa tentar novamente. \nBoa sorte na próxima vez, você vai precisar!");
                break;
            case 7:
                System.out.println("Com coragem escolhes a Porta da Escuridão Profunda!");
                System.out.println("Por detrás da Porta está Umbra, a Sombra Sinistra");
                npc7.mostrarDetalhes();

                player = (heroi.atacar(npc7) == heroi);
                if (player == true) {
                    heroi.usarPocao();
                    heroi.mostrarDetalhes();
                    labirinto(10, heroi);
                } else reset("Game Over! Sua jornada chegou a um fim prematuro na Porta 7: Porta da Escuridão Profunda. \nParece que a coragem não foi suficiente para superar o desafio. \nMas não se preocupe, derrotar todos os inimigos é mais difícil do que parece. \nVocê foi apenas mais um a sucumbir diante da minha maestria! Melhore suas habilidades e talvez, quem sabe, você possa tentar novamente. \nBoa sorte na próxima vez, você vai precisar!");
                break;
            case 8:
                System.out.println("Com coragem escolhes a Porta da Sabedoria Antiga!");
                System.out.println("Por detrás da Porta está Magnus, o Sábio Arcano");
                npc8.mostrarDetalhes();

                player = (heroi.atacar(npc8) == heroi);
                if (player == true) {

                    heroi.usarPocao();
                    heroi.mostrarDetalhes();

                    System.out.println("o vendedor mesterioso lhe apresenta os itens que tem para vender");
                    System.out.println("Itens disponíveis para compra:");
                    System.out.println("Deseja comprar algum item? (Digite o número correspondente ou 0 para avançar sem comprar)");
                    vendedor1.imprimirInventario();
                    vendedor1.vender(heroi);

                    labirinto(10, heroi);
                } else reset("Game Over! Sua jornada chegou a um fim prematuro na Porta 8: Porta da Sabedoria Antiga. \nParece que a coragem não foi suficiente para superar o desafio. \nMas não se preocupe, derrotar todos os inimigos é mais difícil do que parece. \nVocê foi apenas mais um a sucumbir diante da minha maestria! Melhore suas habilidades e talvez, quem sabe, você possa tentar novamente. \nBoa sorte na próxima vez, você vai precisar!");
            case 9:
                System.out.println("Com coragem escolhes a Porta do Conhecimento Proibido!");
                System.out.println("Por detrás da Porta está Chronos, o Guardião Temporal");
                npc9.mostrarDetalhes();

                    player = (heroi.atacar(npc9) == heroi);
                    if (player == true) {

                heroi.usarPocao();
                heroi.mostrarDetalhes();

                        System.out.println("o vendedor mesterioso lhe apresenta os itens que tem para vender");
                        System.out.println("Itens disponíveis para compra:");
                        System.out.println("Deseja comprar algum item? (Digite o número correspondente ou 0 para avançar sem comprar)");
                        vendedor1.imprimirInventario();
                        vendedor1.vender(heroi);

                labirinto(10, heroi);

        } else reset("Game Over! Sua jornada chegou a um fim prematuro na Porta 9: Porta do Tempo Perdido. \nParece que a coragem não foi suficiente para superar o desafio. \nMas não se preocupe, derrotar todos os inimigos é mais difícil do que parece. \nVocê foi apenas mais um a sucumbir diante da minha maestria! Melhore suas habilidades e talvez, quem sabe, você possa tentar novamente. \nBoa sorte na próxima vez, você vai precisar!");
                break;
            case 10:
                System.out.println("Chegaste a última Porta a Porta da Conclusão!");
                System.out.println("Esta porta é diferente de todas as outras. Feita de um material desconhecido e adornada com símbolos sagrados, ela emana uma aura de grande poder e mistério.");
                System.out.println("Essa porta leva ao confronto final, onde o personagem enfrentará o desafio mais formidável do castelo labirinto.");
                System.out.println("O que aguarda além dessa porta é um segredo guardado com afinco, reservado apenas para aqueles corajosos o suficiente para chegar até aqui.");
                System.out.println("Formada por vinhas entrelaçadas e folhagens vibrantes. Gravuras dos quatro elementos - fogo, água, terra e ar.");
                System.out.println("Conduz a desafios envolvendo controle e manipulação dos elementos, superando obstáculos e enfrentando criaturas elementais.");
                System.out.println("Por detrás da Porta está Nihilus, o Mestre Supremo");
                npc10.mostrarDetalhes();

                player = (heroi.atacar(npc10) == heroi);
                if (player == true) {

                labirinto(11, heroi);

        } else reset("Game Over! Sua jornada chegou a um fim. Chegaste a última Porta a Porta da Conclusão! Mas não venceste Nihilus, o Mestre Supremo. \nParece que a coragem não foi suficiente para superar o desafio. \nMas não se preocupe, derrotar todos os inimigos é mais difícil do que parece. \nVocê foi apenas mais um a sucumbir diante da minha maestria! Melhore suas habilidades e talvez, quem sabe, você possa tentar novamente. \nBoa sorte na próxima vez, você vai precisar!");
        break;

            case 11:

                System.out.println("Após superar todos os desafios, derrotar inimigos poderosos e desvendar os segredos do Castelo Labirinto, o personagem finalmente alcança a vitória final.");
                System.out.println("O herói se ergue diante da Porta da Conclusão, sentindo a emoção percorrer seu corpo. Com um último suspiro, ele a abre, revelando a tão aguardada recompensa.");
                System.out.println("Uma luz brilhante preenche o ambiente, e o herói é envolvido por uma sensação de triunfo.");
                System.out.println("***********************************************************************************************");
                System.out.println("Finalmente, o Castelo Labirinto foi conquistado!");
                System.out.println("Agora, posso dizer que sou um verdadeiro 'programador aPÒOsentado'! E aqui está o meu tesouro: um código fonte que brilha mais que mil estrelas!");
                System.out.println("O herói solta uma risada contagiante, misturando a alegria da vitória com a alegria de compartilhar uma piada relacionada à sua paixão pela programação orientada a objetos em Java.");
                System.out.println("O código fonte brilhante representa não apenas o triunfo do personagem, mas também sua habilidade em dominar os desafios do jogo.");
                System.out.println("Enquanto o herói admira seu tesouro recém-descoberto, ele sabe que essa vitória não é apenas uma conquista pessoal,");
                System.out.println("mas também uma homenagem a todos os programadores que enfrentam os desafios diários da programação orientada a objetos.");
                System.out.println("Com uma sensação de realização, ele se prepara para novas aventuras, levando consigo o conhecimento e o espírito de diversão que a programação em JAVA POO pode trazer.");
                System.out.println("E assim, o herói encerra sua jornada no Castelo Labirinto, com uma vitória épica e uma piada programática para animar seu caminho.");
                System.out.println("O futuro o aguarda, cheio de novas aventuras e desafios. Mas, por agora, ele celebra sua conquista, sabendo que se tornou um verdadeiro mestre \"apósentado\" em Java POO.");
                break;
        }
        return true;
    }
}