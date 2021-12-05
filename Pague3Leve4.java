final public class Pague3Leve4 extends Promocao {

      public Pague3Leve4(Produto produtoAssociado, Data[] periodoPromocao) {
            super(produtoAssociado, periodoPromocao);
      }

      public double custoAposPromocao(int quantidade, double precoUnitario) {
            return (quantidade - (int) (quantidade / 4)) * precoUnitario;
      }

      public String tipoPromocao(){
            return "Pague-3-Leve-4";
      }

}
