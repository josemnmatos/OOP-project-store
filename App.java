import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class App {
    // preencher no arranque ao ler dos ficheiros
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Cliente> clientesObj = new ArrayList<>();
    private ArrayList<Produto> produtosDisponiveis = new ArrayList<>();
    private ArrayList<Promocao> promocoesAtivas = new ArrayList<>();

    // lista de compras realizadas
    private ArrayList<Compra> comprasRealizadas = new ArrayList<>();
    private Data dataAtual = new Data(21, 11, 2021);

    // DATA ATUAL
    public void mostraDataAtual() {
        System.out.println(dataAtual);
    }

    public void mudaDataAtual() {
        Scanner sc = new Scanner(System.in);
        int dia, mes, ano;
        do {
            System.out.println("Dia: ");
            dia = sc.nextInt();
            System.out.println("Mês: ");
            mes = sc.nextInt();
            System.out.println("Ano: ");
            ano = sc.nextInt();
        } while (dia < 1 || dia >= 32 || mes < 1 || mes >= 13);
        sc.close();
        dataAtual.setDia(dia);
        dataAtual.setMes(mes);
        dataAtual.setAno(ano);
    }

    // CLIENTES
    public void parseClientes() {
        File obj = new File("clientes.obj");
        // verifica a existencia de ficheiro de objetos
        if (obj.exists() && obj.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(obj);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (true) {
                    try {
                        Cliente client = (Cliente) ois.readObject();
                        clientesObj.add(client);
                    } catch (EOFException e) {
                        ois.close();
                        break;
                    }
                }
            } catch (FileNotFoundException ex) {
                System.err.println("Erro a abrir ficheiro.");
            } catch (IOException ex) {
                System.err.println("Erro a ler ficheiro.");

            } catch (ClassNotFoundException ex) {
                System.err.println("Erro a converter objeto.");
            }
        } else {// nao existe ficheiro de objetos
            File f = new File("clientes.txt");
            if (f.exists() && f.isFile()) {
                try {
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    Cliente c = null;
                    while ((line = br.readLine()) != null) {

                        if (line.charAt(0) == '*') {
                            c = new Cliente();
                        }
                        // frequente/regular
                        else if (line.charAt(0) == '1') {
                            c.setFrequente(line.charAt(2) == 'f');
                        }
                        // nome
                        else if (line.charAt(0) == '2') {
                            c.setNome(line.substring(2));

                        }
                        // morada
                        else if (line.charAt(0) == '3') {
                            String[] moradaPartes = line.substring(2).split(",");
                            Morada m = new Morada(moradaPartes[0], Integer.parseInt(moradaPartes[1]),
                                    Integer.parseInt(moradaPartes[2]));
                            c.setMorada(m);

                        }
                        // email
                        else if (line.charAt(0) == '4') {
                            c.setEmail(line.substring(2));
                        }
                        // telefone
                        else if (line.charAt(0) == '5') {
                            c.setTelefone(Integer.parseInt(line.substring(2)));
                        }
                        // data de nascimento
                        else if (line.charAt(0) == '6') {
                            String[] dataPartes = line.substring(2).split("/");
                            Data d = new Data(Integer.parseInt(dataPartes[0]), Integer.parseInt(dataPartes[1]),
                                    Integer.parseInt(dataPartes[2]));
                            c.setDataNascimento(d);
                            // como é o ultimo parametro a ser preenchido, adiciona à lista de clientes
                            clientes.add(c);
                        }
                    }
                    br.close();
                } catch (FileNotFoundException ex) {
                    System.err.println("Erro a abrir ficheiro de texto.");
                } catch (IOException ex) {
                    System.err.println("Erro a ler ficheiro de texto.");
                }

            }
            // cria ficheiro de objetos
            try {
                File objCreate = new File("clientes.obj");
                if (objCreate.createNewFile()) {
                    System.err.println("File created: " + objCreate.getName());
                } else {
                    System.err.println("Ficheiro de objetos já existe.");
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar ficheiro de objetos.");
            }
        }

    }

    // guarda valores da lista de clientes no ficheiro de objetos
    public void updateClientes() {
        File f = new File("clientes.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Cliente i : clientes) {
                oos.writeObject(i);
            }
            oos.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.err.println("Erro a escrever para o ficheiro.");
        }
    }

    // lista todos os clientes
    public void listaClientes() {
        System.out.println("\nClientes :");
        for (Cliente c : clientesObj) {
            System.out.println(c);
        }

    }
    // PRODUTOS

    public void parseProdutos() {
        File obj = new File("produtos.obj");
        // verifica a existencia de ficheiro de objetos
        if (obj.exists() && obj.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(obj);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (true) {
                    try {
                        Produto p = (Produto) ois.readObject();
                        produtosDisponiveis.add(p);
                    } catch (EOFException e) {
                        ois.close();
                        break;
                    }
                }
            } catch (FileNotFoundException ex) {
                System.err.println("Erro a abrir ficheiro.");
            } catch (IOException ex) {
                System.err.println("Erro a ler ficheiro.");

            } catch (ClassNotFoundException ex) {
                System.err.println("Erro a converter objeto.");
            }}else{
        File f = new File("produtos.txt");
        if (f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] detalhesProduto = line.split(",");

                    if (detalhesProduto[0].equals("mobiliario")) {
                        double peso = Double.parseDouble(detalhesProduto[5]);
                        String[] dimensoes = detalhesProduto[6].split("/");
                        Dimensao d = new Dimensao(Double.parseDouble(dimensoes[0]), Double.parseDouble(dimensoes[1]),
                                Double.parseDouble(dimensoes[2]));
                        // id,nome,preco,stock,peso,dimensao
                        Mobiliario p = new Mobiliario(Integer.parseInt(detalhesProduto[1]), detalhesProduto[2],
                                Double.parseDouble(detalhesProduto[3]), Integer.parseInt(detalhesProduto[4]), peso, d);
                        produtosDisponiveis.add(p);

                    } else if (detalhesProduto[0].equals("limpeza")) {
                        int grauToxicidade = Integer.parseInt(detalhesProduto[5]);
                        // id,nome,preco,stock,grau de toxicidade
                        Limpeza p = new Limpeza(Integer.parseInt(detalhesProduto[1]), detalhesProduto[2],
                                Double.parseDouble(detalhesProduto[3]), Integer.parseInt(detalhesProduto[4]),
                                grauToxicidade);
                        produtosDisponiveis.add(p);

                    } else if (detalhesProduto[0].equals("alimentar")) {
                        double kcal = Double.parseDouble(detalhesProduto[5]);
                        double percentagemGordura = Double.parseDouble(detalhesProduto[6]);
                        // id,nome,preco,stock,kcal,percent de gordura
                        Alimentar p = new Alimentar(Integer.parseInt(detalhesProduto[1]), detalhesProduto[2],
                                Double.parseDouble(detalhesProduto[3]), Integer.parseInt(detalhesProduto[4]), kcal,
                                percentagemGordura);
                        produtosDisponiveis.add(p);

                    } else {
                        System.out.println("Erro: Produto inválido.");
                    }

                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.err.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.err.println("Erro a ler ficheiro de texto.");
            }
        }
        // cria ficheiro de objetos
        try {
            File objCreate = new File("produtos.obj");
            if (objCreate.createNewFile()) {
                System.err.println("File created: " + objCreate.getName());
            } else {
                System.err.println("Ficheiro de objetos já existe.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar ficheiro de objetos.");
        }}

    }

    // guarda valores da lista de produtos no ficheiro de objetos
    public void updateProdutos() {
        File f = new File("produtos.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Produto p : produtosDisponiveis) {
                oos.writeObject(p);
            }
            oos.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.err.println("Erro a escrever para o ficheiro.");
        }
    }

    // lista todos os produtos
    public void listaProdutos() {
        System.out.println("\nProdutos :");
        for (Produto p : produtosDisponiveis) {
            System.out.println(p);
        }

    }

    public static void main(String[] args) {

        App gestor = new App();
        gestor.parseClientes();
        gestor.parseProdutos();
        gestor.listaClientes();
        gestor.listaProdutos();
        gestor.updateClientes();
        gestor.updateProdutos();

    }
}