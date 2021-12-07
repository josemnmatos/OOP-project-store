import java.io.Serializable;

public class Morada implements Serializable{
      /**
       * Rua da casa
       */
      private String rua;
      /**
       * Numero da casa
       */
      private int numero;
      /**
       * Codigo postal da casa
       */
      private int codigoPostal;

      /**
       * Construtor da classe Morada
       * 
       * @param rua           Rua da casa
       * @param numero        Numero da casa
       * @param codigoPostal  Codigo postal da casa
       */
      public Morada(String rua, int numero, int codigoPostal) {
            this.rua = rua;
            if (numero < 0 || codigoPostal < 0 || codigoPostal > 9999999) {
                  System.out.println("Morada inválida");
                  System.exit(1);
            } else {
                  this.numero = numero;
                  this.codigoPostal = codigoPostal;
            }
      }

     
      @Override
      public String toString() {
            return this.rua+", "+this.numero+", "+this.codigoPostal;
      }

}
