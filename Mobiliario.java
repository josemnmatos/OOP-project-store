final public class Mobiliario extends Produto {
      private double peso;
      private Dimensao dimensao;

      public Mobiliario(int id, String nome, double precoUnitario, int stock, double peso, Dimensao dimensao) {
            super(id, nome, precoUnitario, stock);
            if (peso < 0) {
                  System.out.println("Peso do mobiliário inválido.");
                  System.exit(1);

            } else {
                  this.peso = peso;
            }
            this.dimensao = dimensao;
      }

      public double getPeso() {
            return this.peso;
      }

      public void setPeso(double peso) {
            this.peso = peso;
      }

      public Dimensao getDimensao() {
            return this.dimensao;
      }

      public void setDimensao(Dimensao dimensao) {
            this.dimensao = dimensao;
      }

      @Override
      public String toString() {
            return "{" + " peso='" + getPeso() + "'" + ", dimensao='" + getDimensao() + "'" + "}";
      }

}