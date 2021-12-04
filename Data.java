import java.io.Serializable;

public class Data implements Serializable {
      private int dia;
      private int mes;
      private int ano;

      public Data(int dia, int mes, int ano) {
            if (dia <= 0 || dia >= 32) {
                  System.out.println("Dia inválido.");
                  System.exit(1);
            } else {
                  this.dia = dia;
            }
            if (mes <= 0 || mes >= 13) {
                  System.out.println("Mês inválido.");
                  System.exit(1);
            } else {
                  this.mes = mes;
            }
            this.ano = ano;
      }

      public int getDia() {
            return this.dia;
      }

      public void setDia(int dia) {
            this.dia = dia;
      }

      public int getMes() {
            return this.mes;
      }

      public void setMes(int mes) {
            this.mes = mes;
      }

      public int getAno() {
            return this.ano;
      }

      public void setAno(int ano) {
            this.ano = ano;
      }

      public boolean estaEntre(Data data1, Data data2) {
            // ano
            if (this.ano >= data1.ano && this.ano <= data2.ano) {
                  // mes
                  if (this.mes >= data1.mes && this.mes <= data2.mes) {
                        // dia
                        if (this.dia >= data1.dia && this.dia <= data2.dia) {
                              return true;
                        }
                  }
            }
            return false;
      }

      @Override
      public String toString() {
            return dia + "/" + mes + "/" + ano;
      }

}
