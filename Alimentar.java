final public class Alimentar extends Produto {
      private double kcalPor100g;
      private double percentagemGordura;

      public Alimentar(int id, String nome, double precoUnitario, int stock, double kcalPor100g,
                  double percentagemGordura) {
            super(id, nome, precoUnitario, stock);
            if (kcalPor100g < 0) {
                  System.out.println("Número de calorias por 100g inválido.");
                  System.exit(1);
            } else {
                  this.kcalPor100g = kcalPor100g;
            }
            if (percentagemGordura < 0 || percentagemGordura > 1) {
                  System.out.println("Percentagem de gordura inválida.");
                  System.exit(1);
            } else {
                  this.percentagemGordura = percentagemGordura;
            }
      }

}
