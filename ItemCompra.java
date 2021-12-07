import java.io.Serializable;

public class ItemCompra implements Serializable {
      /**
       * Produto que está a ser comprado
       */
      private Produto produto;
      /**
       * Quantidade de produto
       */
      private int quantidade;

      /**
       * Construtor da classe ItemCompra
       * @param produto Produto que está a ser comprado
       * @param quantidade Quantidade de produto
       */
      public ItemCompra(Produto produto, int quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
      }

      public Produto getProduto() {
            return this.produto;
      }

      public int getQuantidade() {
            return this.quantidade;
      }

      public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
      }

      /**
       * Método para obter o valor total do item (após promoção caso esta exista)
       * 
       * @return Valor total do item
       */
      public double getPrecoItem() {
            // nao esta em promocao
            if (this.produto.getPromocaoAssociada() == null)
                  return this.produto.getPrecoUnitario() * this.quantidade;
            else {
                  return this.produto.getPromocaoAssociada().custoAposPromocao(this.quantidade,
                              this.produto.getPrecoUnitario());
            }
      }

      @Override
      public String toString() {
            return "{" +
                        " produto='" + getProduto() + "'" +
                        ", quantidade='" + getQuantidade() + "'" +
                        "}";
      }

}
