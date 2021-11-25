import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class App {
    // preencher no arranque ao ler dos ficheiros
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Produto> produtosDisponiveis = new ArrayList<>();
    private ArrayList<Promocao> promocoesAtivas = new ArrayList<>();

    // lista de compras realizadas
    private ArrayList<Compra> comprasRealizadas = new ArrayList<>();
    private Data dataAtual = new Data(21, 11, 2021);

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

    public void parseClientes() {
        File obj = new File("clientes.obj");
        // verifica a existencia de ficheiro de objetos
        if (obj.exists() && obj.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(obj);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Boolean cont = true;
                while (cont){
                    Cliente client= (Cliente)ois.readObject();
                    if (client != null){
                        clientes.add(client);
                    }
                    else
                        cont = null;


                }
                Cliente c = (Cliente) ois.readObject();
                clientes.add(c);
                ois.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro.");
            } catch (ClassNotFoundException ex) {
                System.out.println("Erro a converter objeto.");
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
                    System.out.println("Erro a abrir ficheiro de texto.");
                } catch (IOException ex) {
                    System.out.println("Erro a ler ficheiro de texto.");
                }

            }
            // cria ficheiro de objetos
            try {
                File objCreate = new File("clientes.obj");
                if (objCreate.createNewFile()) {
                    System.out.println("File created: " + objCreate.getName());
                } else {
                    System.out.println("Ficheiro de objetos já existe.");
                }
            } catch (IOException e) {
                System.out.println("Erro ao criar ficheiro de objetos.");
            }
        }

    }

    public void updateClientes() {
        File f = new File("clientes.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Cliente c : clientes) {
                oos.writeObject(c);
            }
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    public void listaClientes() {
        System.out.println("\nClientes :");
        for (Cliente c : clientes) {
            System.out.println(c);
        }

    }

    public static void main(String[] args) {

        App gestor = new App();
        gestor.parseClientes();
        gestor.listaClientes();
        gestor.updateClientes();

    }
}