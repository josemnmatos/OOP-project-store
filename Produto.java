import java.io.Serializable;

public abstract class Produto implements Serializable {
      /**
       * ID do produto
       */
      protected int id;
      /**
       * Nome do produto
       */
      protected String nome;
      /**
       * Preço do produto
       */
      protected double precoUnitario;
      /**
       * Stock disponível
       */
      protected int stock;
      /**
       * Estado de promoção do produto
       */
      protected Promocao promocaoAssociada = null;

      /**
       * Construtor da superclasse Produto
       * 
       * @param id            ID do produto
       * @param nome          Nome do produto
       * @param precoUnitario Preço do produto
       * @param stock         Stock disponível
       */
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

      public Promocao getPromocaoAssociada() {
            return this.promocaoAssociada;
      }

      public void setPromocaoAssociada(Promocao promocaoAssociada) {
            this.promocaoAssociada = promocaoAssociada;
      }
      /**
       * Método retorna custos adicionais do produto
       * @return Valor dos custos adicionais 
       */
      public double custosAdicionais() {
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
