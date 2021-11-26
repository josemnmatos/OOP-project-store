import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class App {
    // preencher no arranque ao ler dos ficheiros
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Produto> produtosMobiliario = new ArrayList<>();
    private ArrayList<Produto> produtosLimpeza = new ArrayList<>();
    private ArrayList<Produto> produtosAlimentar = new ArrayList<>();
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
                        clientes.add(client);
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
                        String[] detalhesCliente = line.split(",");
                        String[] morada = detalhesCliente[2].split("/");
                        Morada m = new Morada(morada[0], Integer.parseInt(morada[1]), Integer.parseInt(morada[2]));
                        String[] data = detalhesCliente[5].split("/");
                        Data d = new Data(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                                Integer.parseInt(data[2]));
                        if (detalhesCliente[0].equals("f")) {
                            Cliente cRegular = new ClienteFrequente(detalhesCliente[1], m, detalhesCliente[3],
                                    Integer.parseInt(detalhesCliente[4]), d);
                            clientes.add(cRegular);
                        } else {
                            Cliente cFrequente = new Cliente(detalhesCliente[1], m, detalhesCliente[3],
                                    Integer.parseInt(detalhesCliente[4]), d);
                            clientes.add(cFrequente);
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
            createObjClientes();
        }
    }

    // guarda valores da lista de clientes no ficheiro de objetos
    private void createObjClientes() {
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
        for (Cliente c : clientes) {
            System.out.println(c);
        }

    }

    // PRODUTOS
    public void parseProdutos() {
        File objM = new File("produtosMobiliario.obj");
        File objL = new File("produtosLimpeza.obj");
        File objA = new File("produtosAlimentar.obj");
        // verifica a existencia de ficheiro de objetos
        if (objM.exists() && objM.isFile() && objL.exists() && objL.isFile() && objA.exists() && objA.isFile()) {
            try {
                // le mobiliario
                FileInputStream fis = new FileInputStream(objM);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (true) {
                    try {
                        Mobiliario p = (Mobiliario) ois.readObject();
                        produtosMobiliario.add(p);
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
            // le limpeza
            try {
                FileInputStream fis = new FileInputStream(objL);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (true) {
                    try {
                        Limpeza p = (Limpeza) ois.readObject();
                        produtosLimpeza.add(p);
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
            // le alimentar
            try {
                FileInputStream fis = new FileInputStream(objA);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (true) {
                    try {
                        Alimentar p = (Alimentar) ois.readObject();
                        produtosAlimentar.add(p);
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
        } else {
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
                            Dimensao d = new Dimensao(Double.parseDouble(dimensoes[0]),
                                    Double.parseDouble(dimensoes[1]), Double.parseDouble(dimensoes[2]));
                            // id,nome,preco,stock,peso,dimensao
                            Mobiliario p = new Mobiliario(Integer.parseInt(detalhesProduto[1]), detalhesProduto[2],
                                    Double.parseDouble(detalhesProduto[3]), Integer.parseInt(detalhesProduto[4]), peso,
                                    d);
                            produtosMobiliario.add(p);

                        } else if (detalhesProduto[0].equals("limpeza")) {
                            int grauToxicidade = Integer.parseInt(detalhesProduto[5]);
                            // id,nome,preco,stock,grau de toxicidade
                            Limpeza p = new Limpeza(Integer.parseInt(detalhesProduto[1]), detalhesProduto[2],
                                    Double.parseDouble(detalhesProduto[3]), Integer.parseInt(detalhesProduto[4]),
                                    grauToxicidade);
                            produtosLimpeza.add(p);

                        } else if (detalhesProduto[0].equals("alimentar")) {
                            double kcal = Double.parseDouble(detalhesProduto[5]);
                            double percentagemGordura = Double.parseDouble(detalhesProduto[6]);
                            // id,nome,preco,stock,kcal,percent de gordura
                            Alimentar p = new Alimentar(Integer.parseInt(detalhesProduto[1]), detalhesProduto[2],
                                    Double.parseDouble(detalhesProduto[3]), Integer.parseInt(detalhesProduto[4]), kcal,
                                    percentagemGordura);
                            produtosAlimentar.add(p);

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
            createObjProdutos();
        }

    }

    // guarda valores da lista de produtos no ficheiro de objetos
    private void createObjProdutos() {
        File f = new File("produtosMobiliario.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Produto p : produtosMobiliario) {
                oos.writeObject(p);
            }
            oos.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.err.println("Erro a escrever para o ficheiro.");
        }
        f = new File("produtosLimpeza.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Produto p : produtosLimpeza) {
                oos.writeObject(p);
            }
            oos.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.err.println("Erro a escrever para o ficheiro.");
        }
        f = new File("produtosAlimentar.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Produto p : produtosAlimentar) {
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
        System.out.println("\nProdutos :\nMobiliário: ");
        for (Produto p : produtosMobiliario) {
            System.out.println(p);
        }
        System.out.println("\nLimpeza: ");
        for (Produto p : produtosLimpeza) {
            System.out.println(p);
        }
        System.out.println("\nAlimentares: ");
        for (Produto p : produtosAlimentar) {
            System.out.println(p);
        }
    }


    public static void main(String[] args) {

        App gestor = new App();
        gestor.parseClientes();
        gestor.parseProdutos();
        gestor.listaClientes();
        gestor.listaProdutos();

    }
}