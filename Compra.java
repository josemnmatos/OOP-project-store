import java.util.ArrayList;
import java.util.Iterator;

public class Compra {
      private Cliente cliente;
      private Data dataCompra;
      private ArrayList<ItemCompra> listaProduto = new ArrayList<>();

      public Compra(Cliente cliente, ArrayList<ItemCompra> listaProduto, Data dataCompra) {
            this.cliente = cliente;
            this.dataCompra = dataCompra;
            this.listaProduto = listaProduto;
      }

      public double custoAtual() {
            double custo = 0;
            for (ItemCompra item : listaProduto) {
                  custo += item.getProduto().getPrecoUnitario() * item.getQuantidade();
            }
            return custo;
      }

      public void adicionarProduto(Produto p, int quantidade) {
            listaProduto.add(new ItemCompra(p, quantidade));
      }

      public void removerProduto(Produto p, int quantidade) {
            for (Iterator<ItemCompra> i = listaProduto.iterator(); i.hasNext();) {
                  ItemCompra item = i.next();
                  if (item.getProduto().getId() == p.id) {
                        item.setQuantidade(item.getQuantidade() - quantidade);
                        if (item.getQuantidade() == 0) {
                              i.remove();
                        }
                  }
            }

      }

      public ArrayList<ItemCompra> getLista(){
            return listaProduto;
      }

}
