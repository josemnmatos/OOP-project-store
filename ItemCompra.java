import java.io.Serializable;

public class ItemCompra implements Serializable {
      private Produto produto;
      private int quantidade;


      public ItemCompra(Produto produto, int quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
      }


      public Produto getProduto() {
            return this.produto;
      }

      public void setProduto(Produto produto) {
            this.produto = produto;
      }

      public int getQuantidade() {
            return this.quantidade;
      }

      public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
      }


      @Override
      public String toString() {
            return "{" +
                  " produto='" + getProduto() + "'" +
                  ", quantidade='" + getQuantidade() + "'" +
                  "}";
      }


}
