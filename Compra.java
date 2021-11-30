import java.util.ArrayList;

public class Compra {
      private Cliente cliente;
      private Data dataCompra;
      private ArrayList<ItemCompra> produtos = new ArrayList<>();

      public Compra(Cliente cliente, ArrayList<ItemCompra> produtos, Data dataCompra) {
            this.cliente = cliente;
            this.dataCompra = dataCompra;
            this.produtos = produtos;
      }

      public void adicionaProduto(Produto produto, int quantidade) {
            ItemCompra item = new ItemCompra(produto, quantidade);
            this.produtos.add(item);
      }

}
