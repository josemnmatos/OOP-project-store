public class Dimensao {
      private double altura;
      private double largura;
      private double profundidade;

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
            return "{" +
                  " altura='" + getAltura() + "'" +
                  ", largura='" + getLargura() + "'" +
                  ", profundidade='" + getProfundidade() + "'" +
                  "}";
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
