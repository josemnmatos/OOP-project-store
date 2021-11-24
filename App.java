import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class App {
    // preencher no arranque ao ler dos ficheiros
    private ArrayList<Cliente> clientesRegulares = new ArrayList<>();
    private ArrayList<Cliente> clientesFrequentes = new ArrayList<>();
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
                        c.setFrequente(line.charAt(1) == 'f');
                    }
                    // nome
                    else if (line.charAt(0) == '2') {
                        c.setNome(line.substring(2));
                        System.out.println(c.getNome());
                    }
                    // morada
                    else if (line.charAt(0) == '3') {
                        String[] moradaPartes = line.substring(2).split(",");
                        Morada m = new Morada(moradaPartes[0],Integer.parseInt(moradaPartes[1]),Integer.parseInt(moradaPartes[2]));
                        c.setMorada(m);
                        System.out.println(c.getMorada());

                    }
                    // email
                    else if (line.charAt(0) == '4') {

                    }
                    // telefone
                    else if (line.charAt(0) == '5') {

                    }
                    // data de nascimento
                    else if (line.charAt(0) == '6') {

                    }
                    // cliente seguinte
                    else {
                        Cliente newC = new Cliente();
                    }
                }
                br.close();

            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe.");
        }
    }

    public void listaClientes() {
        System.out.println("\nClientes frequentes:");
        for (Cliente c : clientesFrequentes) {
            System.out.println(c.getNome());
        }
        System.out.println("\nClientes regulares:");
        for (Cliente c : clientesRegulares) {
            System.out.println(c.getNome());
        }
    }

    public static void main(String[] args) {

        App gestor = new App();
        gestor.parseClientes();
        gestor.listaClientes();

    }
}