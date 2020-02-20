import java.util.*;
public class MediumMarket extends Magazin{
    String tip = new String ("MediumMarket");
    public double calculScutiriTaxe(){
        
    //caut toate categoriile
        Vector<String> categorii = new Vector<String>();
        for(int i=0; i< facturi.size(); i++){
            for(int j=0; j< facturi.get(i).produse.size(); j++){
                if(!categorii.contains(facturi.get(i).produse.get(j).getProdus().getTaraOrigine()))
                    categorii.add(facturi.get(i).produse.get(j).getProdus().getTaraOrigine());
            }
        }
    // Acum am tarile in tari[]. verific ptr fiecare
        for(int i=0; i< categorii.size(); i++){
            String type = categorii.get(i);
            if(2 * getTotalCategorieCuTaxe(type) > getTotalCuTaxe())
                return 5;
        }
    //Daca nu am gasit nici una sa indeplineasca conditia, atunci nu am scutiri de taxe    
        return 0;
    }
}
