final public class Mobiliario extends Produto {
      /**
       * Peso do produto mobiliario
       */
      private double peso;
      /**
       * Dimensão do produto mobiliario
       */
      private Dimensao dimensao;

      /**
       * Construtor da subclasse de Produto, Mobiliario
       * 
       * @param id             ID do produto
       * @param nome           Nome do produto
       * @param precoUnitario  Preço por unidade do produto
       * @param stock          Stock do produto
       * @param peso           Peso do produto
       * @param dimensao       Dimensão do produto
       */
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
            return "\nID: " + this.id
                        + "\nProduto: " + this.nome
                        + "\nPeso: " + this.peso
                        + " Kg\nDimensões: " + this.dimensao.toString()
                        + "\nPreço por unidade: " + this.precoUnitario
                        + " euros\nStock: " + this.stock;
      }

      /**
       * Método que retorna os custos adicionais consoante o peso do produto
       * 
       * @return Valor dos custos adicionais
       */
      @Override
      public double custosAdicionais() {
            if (this.peso > 15) {
                  return 10;
            } else {
                  return 0;
            }
      }
}