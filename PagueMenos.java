final public class PagueMenos extends Promocao {

      /**
       * Construtor de um objeto da subclasse de Promocao, PagueMenos
       * 
       * @param produtoAssociado Produto associado a esta promoção
       * @param periodoPromocao  Array de objetos Data referentes ao periodo em que a promoção é válida
       */
      public PagueMenos(Produto produtoAssociado, Data[] periodoPromocao) {
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
            double custoFinal = 0;
            double desconto = 1;
            for (int i = 0; i < quantidade; i++) {
                  if (desconto > 0.5) {
                        custoFinal += desconto * precoUnitario;
                        desconto -= 0.05;
                  } else {
                        custoFinal += precoUnitario;
                  }
            }
            return custoFinal;
      }

      /**
       * Método que retorna o tipo de promoção em questão
       * 
       * @return tipo de promoção
       */
      public String tipoPromocao(){
            return "Pague-Menos";
      }

}
