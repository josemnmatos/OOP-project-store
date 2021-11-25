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
            try{
                FileInputStream fis = new FileInputStream(f);
                boolean cont = true;
                ObjectInputStream ois= new ObjectInputStream(fis);
                while(cont){
                    Cliente client= null;
                    client = (Cliente)ois.readObject();
                    if(client != null){
                        if (client.isFrequente()){
                            clientesFrequentes.add(client);
                        }
                        else
                            clientesRegulares.add(client);
                    }
                }
                ois.close();
            
            }
             catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir o ficheiro.");
            }
            catch(IOException ex){
                System.out.println("Erro a ler ficheiro.");
            }
            catch(ClassNotFoundException ex){
                System.out.println("Erro a converter objeto");
            }
        }
             
            else {
        
            System.out.println("Ficheiro não existe. Será criado um");
            Morada m = new Morada("Rua do pau", 32, 3020302);
            Data d = new Data(2, 3, 1323);
            Cliente gilberto = new Cliente("Gilberto", m, "gilbi@gmail.com", 916443557, d, true);
            Morada m2 = new Morada("Rua da fruta", 5, 203402);
            Data d2 = new Data(13, 5, 54332);
            Cliente manafa = new Cliente("manafa", m2, "manafa@gmail.com", 916443557, d2, false);
            try{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
           

            oos.writeObject(gilberto);
            oos.writeObject(manafa);
            oos.close();
            }
            catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro de texto.");
            } 
            catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro de texto.");
                }
            }   
    
    }
    

    public void listaClientes() {
        System.out.println("\nClientes frequentes:");
        for (Cliente c : clientesFrequentes) {
            System.out.println(c);
        }
        System.out.println("\nClientes regulares:");
        for (Cliente c : clientesRegulares) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {

        App gestor = new App();
        gestor.parseClientes();
        gestor.listaClientes();

    }
}