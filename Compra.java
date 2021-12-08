import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Compra implements Serializable {
      /**
       * Cliente associado à compra
       */
      private Cliente cliente;

      /**
       * Data de realização da compra
       */
      private Data dataCompra;

      /**
       * Lista de produtos e quantidades de produto da compra
       */
      private ArrayList<ItemCompra> listaProduto = new ArrayList<>();

      /**
       * Construtor da classe Compra
       *
       * @param cliente      Cliente associado à compra.
       * @param listaProduto Lista de produtos e quantidades de produto da compra
       * @param dataCompra   Data de realização da compra
       */
      public Compra(Cliente cliente, ArrayList<ItemCompra> listaProduto, Data dataCompra) {
            this.cliente = cliente;
            this.dataCompra = dataCompra;
            this.listaProduto = listaProduto;
      }

      /**
       * Método que retorna o custo atual de um objeto Compra
       * 
       * @return Custo atual da compra
       */
      public double custoAtual() {
            double custoAtual = 0;
            for (ItemCompra item : listaProduto) {
                  custoAtual += item.getPrecoItem();
            }
            return custoAtual;
      }

      /**
       * Método que retorna o custo final de um objeto Compra (Produtos, portes e
       * custos adicionais)
       * 
       * @return Custo final da compra
       */
      public double custoFinal() {
            double custoFinal = this.custoAtual();
            double custoPortes = this.cliente.custoPortes(this.custoAtual());
            double custoAdicional = 0;
            for (ItemCompra i : this.listaProduto) {
                  custoAdicional += i.getProduto().custosAdicionais() * i.getQuantidade();
            }

            return custoFinal + custoPortes + custoAdicional;
      }

      /**
       * Método que permite adicionar uma quantidade de um certo produto à compra
       * 
       * @param p          Produto a adicionar
       * @param quantidade Quantidade a adicionar
       */
      public void adicionarProduto(Produto p, int quantidade) {
            listaProduto.add(new ItemCompra(p, quantidade));
      }

      /**
       * Método que permite remover uma quantidade de um certo produto da compra
       * 
       * @param p          Produto a remover
       * @param quantidade Quantidade a remover
       */
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

      /**
       * Método que imprime os detalhes de uma compra
       */
      public void mostraCompra() {
            System.out.println(
                        "\nData: " + this.dataCompra +
                                    "\n\nProdutos:\n");
            for (ItemCompra i : this.listaProduto) {
                  System.out.format("%-3d %-20s %-5.2f euros\n", i.getQuantidade(), i.getProduto().getNome(),
                              i.getProduto().getPrecoUnitario());
            }
            System.out.println("\nCusto: " + this.custoFinal() + " euros");
      }

      public Cliente getCliente() {
            return this.cliente;
      }

      public Data getDataCompra() {
            return this.dataCompra;
      }

      public ArrayList<ItemCompra> getLista() {
            return this.listaProduto;
      }

}
