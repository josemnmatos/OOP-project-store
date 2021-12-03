import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Compra implements Serializable {
      private Cliente cliente;
      private Data dataCompra;
      private ArrayList<ItemCompra> listaProduto = new ArrayList<>();

      public Compra(Cliente cliente, ArrayList<ItemCompra> listaProduto, Data dataCompra) {
            this.cliente = cliente;
            this.dataCompra = dataCompra;
            this.listaProduto = listaProduto;
      }

      public double custoAtual() {
            double custoAtual = 0;
            for (ItemCompra item : listaProduto) {
                  custoAtual += item.getProduto().getPrecoUnitario() * item.getQuantidade();
            }
            return custoAtual;
      }

      public double custoFinal() {
            double custoFinal = this.custoAtual();
            double custoPortes = this.cliente.custoPortes(this.custoAtual());
            double custoAdicional = 0;
            for (ItemCompra i : this.listaProduto) {
                  custoAdicional += i.getProduto().custosAdicionais() * i.getQuantidade();
            }

            return custoFinal + custoPortes + custoAdicional;
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

      public Cliente getCliente() {
            return this.cliente;
      }

      public ArrayList<ItemCompra> getLista() {
            return listaProduto;
      }

      public void mostraCompra() {
            System.out.println("Cliente: " + this.cliente +
                        "\nData: " + this.dataCompra +
                        "\nProdutos:");
            for (ItemCompra i : this.listaProduto) {
                  System.out.format("%-3d %-20s %-5f\n", i.getQuantidade(), i.getProduto().getNome(),
                              i.getProduto().getPrecoUnitario());
            }
      }

}
