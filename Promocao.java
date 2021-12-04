public abstract class Promocao {
      protected Produto produtoAssociado;

      protected abstract double custoAposPromocao(int quantidade, double precoUnitario);
}
