
final public class Limpeza extends Produto {
      private int grauToxicidade;

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

}
