final public class Pague3Leve4 extends Promocao {

      /**
       * Construtor da subclasse de Promocao, Pague3Leve4
       * 
       * @param produtoAssociado Produto associado a esta promoção
       * @param periodoPromocao  Array de objetos Data referentes ao periodo em que a promoção é válida
       */
      public Pague3Leve4(Produto produtoAssociado, Data[] periodoPromocao) {
            super(produtoAssociado, periodoPromocao);
      }
      
      /**
       * Método que calcula o custo final do produto após a aplicação da promoção
       * 
       * @param quantidade    quantidade do produto em questão que está a ser comprado
       * @param precoUnitario preço usual do produto por unidade
       * @return              custo final
       */
      public double custoAposPromocao(int quantidade, double precoUnitario) {
            return (quantidade - (int) (quantidade / 4)) * precoUnitario;
      }

      public String tipoPromocao(){
            return "Pague-3-Leve-4";
      }

}
