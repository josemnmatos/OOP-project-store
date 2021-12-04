final public class PagueMenos extends Promocao {
      
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

}
