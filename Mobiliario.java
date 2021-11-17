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
}
