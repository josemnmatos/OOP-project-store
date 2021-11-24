public abstract class Produto {
      protected int id;
      protected String nome;
      protected double precoUnitario;
      protected int stock;

      public Produto(int id, String nome, double precoUnitario, int stock) {
            if (id < 0) {
                  System.out.println("Identificador de produto inválido.");
                  System.exit(1);
            } else {
                  this.id = id;
            }
            this.nome = nome;
            if (precoUnitario < 0) {
                  System.out.println("Preço unitário inválido.");
                  System.exit(1);
            } else {
                  this.precoUnitario = precoUnitario;
            }
            if (stock < 0) {
                  System.out.println("Stock inválido.");
                  System.exit(1);
            } else {
                  this.stock = stock;
            }

      }

}
