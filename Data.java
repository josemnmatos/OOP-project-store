import java.io.Serializable;

public class Data implements Serializable {
      /**
       * Dia.
       */
      private int dia;
      /**
       * Mês.
       */
      private int mes;
      /**
       * Ano.
       */
      private int ano;

      /**
       * Construtor de um objeto da classe Data.
       * 
       * @param dia Dia.
       * @param mes Mês.
       * @param ano Ano.
       */
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

      /**
       * Método que verifica se uma data ocorre antes de outra.
       * 
       * @param d Data a comparar.
       * @return True se a data a comparar ocorre depois, False se ocorre antes.
       */
      public boolean antesDe(Data d) {
            if (this.ano < d.getAno()) {
                  return true;
            } else if (this.ano == d.getAno()) {
                  if (this.mes < d.getMes()) {
                        return true;
                  } else if (this.mes == d.getMes()) {
                        if (this.dia <= d.getDia()) {
                              return true;
                        }
                  }
            }
            return false;
      }

      /**
       * Método que verifica se uma data ocorre depois de outra.
       * 
       * @param d Data a comparar.
       * @return False se a data a comparar ocorre depois, True se ocorre antes.
       */
      public boolean depoisDe(Data d) {
            if (this.ano > d.getAno()) {
                  return true;
            } else if (this.ano == d.getAno()) {
                  if (this.mes > d.getMes()) {
                        return true;
                  } else if (this.mes == d.getMes()) {
                        if (this.dia >= d.getDia()) {
                              return true;
                        }
                  }
            }
            return false;
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

      @Override
      public String toString() {
            return dia + "/" + mes + "/" + ano;
      }

}
