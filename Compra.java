import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Compra {
      private Cliente cliente;
      private ArrayList<ItemCompra> produtos = new ArrayList<>();

      public Compra(Cliente cliente, ArrayList<ItemCompra> produtos) {
            this.cliente = cliente;
            this.produtos = produtos;
      }

      public void adicionaProduto(Produto produto, int quantidade) {
            ItemCompra item = new ItemCompra(produto, quantidade);
            this.produtos.add(item);
      }

      

}
