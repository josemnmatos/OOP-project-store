import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private Data dataAtual = new Data(21,11,2021);

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
        dataAtual.setDia(dia);
        dataAtual.setMes(mes);
        dataAtual.setAno(ano);
    }

    public static void main(String[] args) {

        App gestor = new App();
          
        

    }
}
