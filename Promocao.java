import java.io.Serializable;

public abstract class Promocao implements Serializable {
      /**
       * Produto associado à promoção.
       */
      protected Produto produtoAssociado;
      /**
       * Período em que a promoção está ativa.
       */
      protected Data[] periodoPromocao;

      /**
       * Construtor da superclasse Promocao.
       * 
       * @param produtoAssociado Produto associado à promoção.
       * @param periodoPromocao  Período em que a promoção está ativa.
       */
      public Promocao(Produto produtoAssociado, Data[] periodoPromocao) {
            this.produtoAssociado = produtoAssociado;
            if (periodoPromocao.length != 2) {
                  System.out.println("Erro na criação de uma promoção.");
                  System.exit(1);
            }
            this.periodoPromocao = periodoPromocao;

      }

      /**
       * 
       * @param quantidade    Quantidade do produto que está a ser comprado
       * @param precoUnitario Preço por unidade do produto
       * 
       * @return Custo final
       */
      protected abstract double custoAposPromocao(int quantidade, double precoUnitario);

      public Produto getProdutoAssociado() {
            return this.produtoAssociado;
      }

      public void setProdutoAssociado(Produto produtoAssociado) {
            this.produtoAssociado = produtoAssociado;
      }

      public Data[] getPeriodoPromocao() {
            return this.periodoPromocao;
      }

      public void setPeriodoPromocao(Data[] periodoPromocao) {
            this.periodoPromocao = periodoPromocao;
      }

      public boolean promocaoAtiva(Data d) {
            return (d.depoisDe(periodoPromocao[0]) && d.antesDe(periodoPromocao[1]));
      }

      public abstract String tipoPromocao();

}
