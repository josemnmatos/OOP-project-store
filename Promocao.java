import java.io.Serializable;

public abstract class Promocao implements Serializable {
      protected Produto produtoAssociado;
      protected Data[] periodoPromocao;

      public Promocao(Produto produtoAssociado, Data[] periodoPromocao) {
            this.produtoAssociado = produtoAssociado;
            this.periodoPromocao = periodoPromocao;
      }

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
