import java.io.Serializable;

public class Dimensao implements Serializable {
      /**
       * Altura
       */
      private double altura;
      /**
       * Largura
       */
      private double largura;
      /**
       * Profundidade
       */
      private double profundidade;

      /**
       * Construtor da classe Dimensao
       * 
       * @param altura Altura
       * @param largura Largura
       * @param profundidade Profundidade
       */
      public Dimensao(double altura, double largura, double profundidade) {
            if (altura < 0 || largura < 0 || profundidade < 0) {
                  System.out.println("Dimensão inválida.");
                  System.exit(1);
            } else {
                  this.altura = altura;
                  this.largura = largura;
                  this.profundidade = profundidade;
            }
      }

      @Override
      public String toString() {
            return "(" + this.altura + "m x " + this.largura + "m x " + this.profundidade + "m" + ")";
      }

      public double getAltura() {
            return this.altura;
      }

      public void setAltura(double altura) {
            this.altura = altura;
      }

      public double getLargura() {
            return this.largura;
      }

      public void setLargura(double largura) {
            this.largura = largura;
      }

      public double getProfundidade() {
            return this.profundidade;
      }

      public void setProfundidade(double profundidade) {
            this.profundidade = profundidade;
      }

}
