final public class Alimentar extends Produto {
      /**
       * Kilocalorias por 100 gramas
       */
      private double kcalPor100g;
      /**
       * Percentagem de gordura do Produto
       */
      private double percentagemGordura;

      /**
       * Construtor da subclasse de Produto, Alimentar
       * 
       * @param id ID do produto
       * @param nome Nome do produto
       * @param precoUnitario Preço por unidade do produto
       * @param stock Stock do produto
       * @param kcalPor100g Kilocalorias por 100 gramas do produto
       * @param percentagemGordura Percentagem de gordura do produto
       */
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

      public double getKcalPor100g() {
            return this.kcalPor100g;
      }

      public double getPercentagemGordura() {
            return this.percentagemGordura;
      }


      @Override
      public String toString() {
            return "\nID: " + this.id
                        + "\nProduto: " + this.nome
                        + "\nA cada 100g: " + this.kcalPor100g
                        + " kcal\nGordura: " + this.percentagemGordura * 100
                        + " %\nPreço por unidade: " + this.precoUnitario
                        + " euros\nStock: " + this.stock;
      }

}
