package com.agenda.springcloudmysql;

import com.agenda.springcloudmysql.controller.ContactController;
import com.agenda.springcloudmysql.model.Contact;
import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.util.*;



public class Main{
    private static File arquivo = new File("Agenda.txt");
    public static void main(String args[]) throws  IOException {

        agenda();

    }

    public static void agenda() throws IOException {
        Scanner leitura = new Scanner(System.in);
        int escolha = 9;
        do {
            System.out.println("   Menu de Ações da Agenda   ");
            System.out.println("1 - Cadastrar um novo Contato");
            System.out.println("2 - Excluir um Contato");
            System.out.println("3 - Busque um Contato");
            System.out.println("4 - Listar Todos Contatos");
            System.out.println("5 - Editar Contatos");
            System.out.println("0 - Encerrar o Programa");
            escolha = leitura.nextInt();
            switch (escolha) {
                case 1:
                    Scanner keyboard = new Scanner(System.in);
                    System.out.printf("Digite o Nome do Novo Contato: ");
                    String nome = keyboard.nextLine();
                    System.out.printf("Digite o Número do Novo Contato: ");
                    String telefone = keyboard.nextLine();
                    cadastrarContato(nome, telefone);
                    System.out.println("Contato cadastrado com Sucesso: "+nome+", "+telefone+"\n");
                    break;
                case 2:
                    Scanner keyboard2 = new Scanner(System.in);
                    System.out.printf("Digite o Nome  Completo do contato que deseja Excluir: ");
                    String contato = keyboard2.nextLine();
                    excluirContato(contato);
                    System.out.println("\n");
                    break;
                case 3:
                    Scanner keyboard3 = new Scanner(System.in);
                    System.out.printf("Digite o Nome completo do contato que deseja Buscar: ");
                    String buscar = keyboard3.nextLine();
                    buscarContato(buscar);
                    System.out.println("\n");
                    break;
                case 4:
                    listarContatos();
                    System.out.println("\n");
                    break;
                case 5:
                    editarContato();
                    System.out.println("\n");
                    break;
                case 0:
                    System.out.println("Programa Finalizado! That's All Folks :)");
                    break;
            }
        } while (escolha != 0);
    }

    public static void editarContato() throws IOException {
        TreeMap<String, String> mapaAgenda = new TreeMap<>();
        mapaAgenda = lerArquivo(mapaAgenda);
        Scanner keyboard = new Scanner(System.in);
        System.out.printf("Digite o Nome do contato que deseja Editar: ");
        String contato = keyboard.nextLine();

        String nomeAtual = "";
        String telefoneAtual = "";


        FileReader leitor = new FileReader(arquivo);
        BufferedReader leitorLinha = new BufferedReader(leitor);
        System.out.println("    Escolha uma das opções de edição de contato, conforme abaixo:    ");
        System.out.println("1 - Editar Nome     2 - Editar Telefone    3 - Editar Nome e Telefone");
        String escolha = keyboard.nextLine();

        switch (escolha) {
            case "1":
                System.out.println("Digite o novo nome que deseja atribuir ao contato");
                String novoNome = keyboard.nextLine();
                String linha1 = "";
                while ((linha1 = leitorLinha.readLine()) != null) {
                    String[] separador = linha1.split(", ");
                    if (separador[0].equals(contato)) {
                        telefoneAtual = separador[1];
                        mapaAgenda.remove(contato);
                        mapaAgenda.put(novoNome, telefoneAtual);
                        System.out.println("Contato alterado com sucesso!");
                    }
                }
                leitor.close();
                break;
            case "2":
                System.out.println("Digite o novo número que deseja atribuir ao contato");
                String novoTelefone = keyboard.nextLine();
                String linha2 = "";
                while ((linha2 = leitorLinha.readLine()) != null) {
                    String[] separador = linha2.split(", ");
                    if (separador[0].equals(contato)) {
                        nomeAtual = separador[0];
                        mapaAgenda.remove(contato);
                        mapaAgenda.put(nomeAtual, novoTelefone);
                        System.out.println("Contato alterado com sucesso!");
                    }
                }
                leitor.close();
                break;

            case "3":
                System.out.println("Digite o nome nome que deseja atribuir ao contato: ");
                String novoNome2 = keyboard.nextLine();
                System.out.println("Digite o novo número que deseja atribuir ao contato: ");
                String novoTelefone2 = keyboard.nextLine();
                while ((linha2 = leitorLinha.readLine()) != null) {
                    String[] separador = linha2.split(", ");
                    if (separador[0].equals(contato)) {
                        mapaAgenda.remove(contato);
                        mapaAgenda.put(novoNome2, novoTelefone2);
                        System.out.println("Contato alterado com sucesso!");
                    }
                }
                leitor.close();
                break;
        }
        salvarNoArquivo(mapaAgenda);
    }



    public static void cadastrarContato(String nome, String telefone) throws  IOException  {
        TreeMap<String, String> mapaAgenda = new TreeMap<>();
        mapaAgenda = lerArquivo(mapaAgenda);
        mapaAgenda.put(nome, telefone);
        salvarNoArquivo(mapaAgenda);
    }

    public static void excluirContato(String contato) throws IOException{
        arquivo = new File("Agenda.txt");
        String validador1 = "Não";
        String validador2 = "não";
        Scanner keyboard = new Scanner(System.in);
        TreeMap<String, String> mapaAgenda = new TreeMap<>();
        mapaAgenda = lerArquivo(mapaAgenda);
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        } else {
            if (mapaAgenda.containsKey(contato)) {
                buscarContato(contato);
                System.out.println("Tem certeza que deseja remover este contato?");
                System.out.println("Responda Sim/sim para remover e Não/não para cancelar!!");
                String validacao = keyboard.nextLine();
                if ((validacao.equals(validador1)) || (validacao.equals(validador2))) {
                    System.out.println("Ação cancelada");
                }else {
                    mapaAgenda.remove(contato);
                    System.out.println("Contato excluído com sucesso!!");
                }
            }else{
                System.out.println("O contato ainda não existe na agenda.");
            }
        }
        salvarNoArquivo(mapaAgenda);
    }

    public  static void buscarContato(String buscar) throws IOException{
        TreeMap<String, String> mapaAgenda = new TreeMap<>();
        mapaAgenda = lerArquivo(mapaAgenda);
        arquivo = new File("Agenda.txt");
        if (!arquivo.exists()) {
            System.out.println("Não existe nenhum contato cadastrado na Lista.");
        } else {
            FileReader leitor = new FileReader(arquivo);
            BufferedReader leitorLinha = new BufferedReader(leitor);
            if(mapaAgenda.containsKey(buscar)){
                String linha = "";
                while ((linha = leitorLinha.readLine()) != null) {
                    String[] separador = linha.split(", ");
                    if (separador[0].equals(buscar)) {
                        System.out.println("Contato encontrado: " + separador[0] + ", " + separador[1]);
                        break;
                    }
                }
            }else{
                System.out.println("Contato não encontrado na agenda.");
            }
            leitor.close();
        }
    }

    public static TreeMap<String, String> lerArquivo(TreeMap<String, String> mapaAgenda) throws  IOException{
        arquivo = new File("Agenda.txt");
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        } else {
            FileReader leitor = new FileReader(arquivo);
            BufferedReader leitorLinha = new BufferedReader(leitor);
            String linha = "";
            while ((linha = leitorLinha.readLine()) != null) {
                String[] textoSeparado = linha.split(", ");
                mapaAgenda.put(textoSeparado[0], textoSeparado[1]);
            }
            leitor.close();
        }
        return mapaAgenda;
    }

    public static void salvarNoArquivo(TreeMap<String, String> mapaAgenda) throws IOException{
        FileWriter leitor = new FileWriter(arquivo);
        BufferedWriter escritor = new BufferedWriter(leitor);
        Map<String, String> mapaAgendaOrdenado2 = new TreeMap<>(mapaAgenda);
        for (Map.Entry<String, String> i : mapaAgendaOrdenado2.entrySet()) {
            escritor.write(i.getKey() + ", " + i.getValue());
            escritor.newLine();
        }
        escritor.close();
        leitor.close();
    }

    public static void listarContatos() throws IOException{
        arquivo = new File("Agenda.txt");
        if (!arquivo.exists()) {
            System.out.println("Não existe nenhuma lista de contatos para Listar");
            //arquivo.createNewFile();
        } else {
            FileReader leitor = new FileReader(arquivo);
            BufferedReader leitorLinha = new BufferedReader(leitor);
            String linha = "";
            while ((linha = leitorLinha.readLine()) != null) {
                System.out.println(linha);
            }
            leitor.close();
        }
    }
}

