import java.util.*;

public class MiniMarket extends Magazin{
    String tip = new String ("MiniMarket");
    public double calculScutiriTaxe(){
        
    //caut toate tarile
        Vector<String> tari = new Vector<String>();
        for(int i=0; i< facturi.size(); i++){
            for(int j=0; j< facturi.get(i).produse.size(); j++){
                if(!tari.contains(facturi.get(i).produse.get(j).getProdus().getTaraOrigine()))
                    tari.add(facturi.get(i).produse.get(j).getProdus().getTaraOrigine());
            }
        }
    // Acum am tarile in tari[]. verific ptr fiecare
        for(int i=0; i< tari.size(); i++){
            String country = tari.get(i);
            if(2 * getTotalTaraCuTaxe(country) > getTotalCuTaxe())
                return 10;
        }
    //Daca nu am gasit nici una sa indeplineasca conditia, atunci nu am scutiri de taxe    
        return 0;
    }
}
