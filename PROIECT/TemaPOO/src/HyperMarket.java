import java.util.*;

public class HyperMarket extends Magazin{
    String tip = new String ("HyperMarket");
    public double calculScutiriTaxe(){
        for(int i=0; i< facturi.size(); i++){
            if(10 * facturi.get(i).getTotalCuTaxe() > getTotalCuTaxe())
                return 1;
        }
    //Daca nu am gasit nici una sa indeplineasca conditia,
    //atunci nu am scutiri de taxe    
        return 0;
    }
}
