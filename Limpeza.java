
final public class Limpeza extends Produto {
      /**
       * Grau de toxicidade do produto
       */
      private int grauToxicidade;

      /**
       * Construtor da subclasse de Produto, Limpeza
       * 
       * @param id ID do produto
       * @param nome Nome do produto
       * @param precoUnitario Preço por unidade do produto
       * @param stock Stock do produto
       * @param grauToxicidade Grau de toxicidade do produto
       */
      public Limpeza(int id, String nome, double precoUnitario, int stock, int grauToxicidade) {
            super(id, nome, precoUnitario, stock);
            if (grauToxicidade < 0 || grauToxicidade > 10) {
                  System.out.println("Grau de toxicidade inválido.");
                  System.exit(1);
            } else {
                  this.grauToxicidade = grauToxicidade;
            }
      }

      public int getGrauToxicidade() {
            return this.grauToxicidade;
      }

      public void setGrauToxicidade(int grauToxicidade) {
            this.grauToxicidade = grauToxicidade;
      }

      @Override
      public String toString() {
            return "\nID: " + this.id
                        + "\nProduto: " + this.nome
                        + "\nGrau de toxicidade: " + this.grauToxicidade
                        + "\nPreço por unidade: " + this.precoUnitario
                        + " euros\nStock: " + this.stock;
      }

}
