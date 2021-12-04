import java.io.Serializable;

public abstract class Produto implements Serializable {
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


      public int getId() {
            return this.id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getNome() {
            return this.nome;
      }

      public void setNome(String nome) {
            this.nome = nome;
      }

      public double getPrecoUnitario() {
            return this.precoUnitario;
      }

      public void setPrecoUnitario(double precoUnitario) {
            this.precoUnitario = precoUnitario;
      }

      public int getStock() {
            return this.stock;
      }

      public void setStock(int stock) {
            this.stock = stock;
      }

      public double custosAdicionais(){
            return 0;
      }

      @Override
      public String toString() {
            return "{" +
                  " id='" + getId() + "'" +
                  ", nome='" + getNome() + "'" +
                  ", precoUnitario='" + getPrecoUnitario() + "'" +
                  ", stock='" + getStock() + "'" +
                  "}";
      }


}
