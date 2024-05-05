import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import servicios_api.conexion_api;
import objetos.tipoDeCambio;
import objetos.convertir;

public class main {

    public static void main (String[] args){


        //Creamaos las variable que usaremos para poder realizar el programa
        int opciones;
        String opcion;
        Scanner leer = new Scanner(System.in);

        //Creamos el menu del programa
        while (true){
            System.out.println("""
                     \nCovierte tu moneda local a dolares:
                     
                     Ingrese el numero de la opcion que desea realizar:
                     1) Peso Argentino a Dolar.
                     2) Peso Boliviano a Dolar.
                     3) Real Brasileño a Dolar.
                     4) Peso Chileno a Dolar.
                     5) Peso Colombiano a Dolar.
                     0) Salir.
                     Ingrese el numero:
                    
                    
                    """);
            //leemos lo que ingreso el usuario
            opciones =leer.nextInt();

            try {
                //Conectaremos la api
                conexion_api conex = new conexion_api();
                opcion = switch (opciones){
                    case 1 -> "ARS";
                    case 2 -> "BOB";
                    case 3 -> "BRL";
                    case 4 -> "CLP";
                    case 5 -> "COP";
                    default -> "salir";
                };
                //si la opcion es igual a salir, entonces
                if (opcion.equalsIgnoreCase("salir")){
                    System.out.println("Sakiendo de la aplicacion");
                    break;
                }

                //Creo el obejeto moneda, con la informacion que nos da la api
                tipoDeCambio moneda = conex.newConexionAPi(opcion);

                //Creamos el mapeo de la info que nos trae la opcion elegida
                Map<String,Double> montoDeConversion = (Map<String, Double>) moneda.conversion_rates();

                //Ingresamos monto a convertir
                System.out.println("Ingrese un monto a convertir: ");
                double monto = leer.nextDouble();

                //Realizamos la conversion con el metodo connvrirUSD tiene como parametros el monto que desea convertir, y el valor del dolar que obtiene de la api
                double resultadoDeConversion = convertir.convertirUSD(monto, montoDeConversion.get("USD"));


                //mostramos por pantalla el resultado de la conversion
                System.out.println("***********");
                System.out.println("El valor de " + monto + " " + opcion + "equivale a " + resultadoDeConversion + "usd");
                System.out.println("***********");


            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }


    }
}
