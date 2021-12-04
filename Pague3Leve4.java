final public class Pague3Leve4 extends Promocao {

      public double custoAposPromocao(int quantidade, double precoUnitario) {
            return (quantidade - (int) (quantidade / 4)) * precoUnitario;
      }

}
