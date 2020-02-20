import java.util.*;


public class Factura {
    String denumire = new String();
    Vector<ProdusComandat> produse = new Vector<ProdusComandat>();
    
    
    
    
    double getTotalFaraTaxe(){
        double total = 0 ;
        for(int i = 0; i< produse.size(); i++)
            total = total + produse.get(i).getCantitate() * produse.get(i).getProdus().getPret();
        return total;
    }
    double getTotalCuTaxe(){
        double total = 0;
        for(int i = 0; i< produse.size(); i++){
            ProdusComandat ProdCom = produse.get(i);
            total = total + ProdCom.getCantitate() * (ProdCom.getProdus().getPret() * ( 1 + (ProdCom.getTaxa() /100)));
        }           
        return total;
    }
    double getTaxe(){
        return getTotalCuTaxe() - getTotalFaraTaxe();
    }
    
    double getTotalTaraFaraTaxe(String taraOrigine){
        double total = 0 ;
        for(int i = 0; i< produse.size(); i++)
            if(produse.get(i).getProdus().getTaraOrigine().equals(taraOrigine))
                total = total + produse.get(i).getCantitate() * produse.get(i).getProdus().getPret();
        return total;
        
    }
    double getTotalTaraCuTaxe(String taraOrigine){
        double total = 0 ;
        for(int i = 0; i< produse.size(); i++)
            if(produse.get(i).getProdus().getTaraOrigine().equals(taraOrigine))
                total = total + produse.get(i).getCantitate() * (produse.get(i).getProdus().getPret() * (1 + produse.get(i).getTaxa() /100));
        return total;
    }
    double getTaxeTara(String taraOrigine){
        return getTotalTaraCuTaxe(taraOrigine) - getTotalTaraFaraTaxe(taraOrigine);
    }
    
    double getTotalCategorieCuTaxe(String categorie){
        double total = 0 ;
        for(int i = 0; i< produse.size(); i++)
            if(produse.get(i).getProdus().getCategorie().equals(categorie))
                total = total + produse.get(i).getCantitate() * (produse.get(i).getProdus().getPret() * (1 + produse.get(i).getTaxa() /100));
        return total;
    }
    
    public String toString(){
        String s = new String();
        s = s + denumire + "\n";
        for(int i = 0; i< produse.size(); i++)
            s = s + produse.get(i) + "\n";
        return s;
    }
}
