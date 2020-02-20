import java.util.*;

abstract class Magazin implements IMagazin{
    String nume;
    Vector<Factura> facturi = new Vector<Factura>();
    
    public double getTotalFaraTaxe(){
        double total = 0;
        for(int i = 0; i< facturi.size(); i++)
            total = total + facturi.get(i).getTotalFaraTaxe();
        return total;
    }
    public double getTotalCuTaxe(){
        double total = 0;
        for(int i = 0; i< facturi.size(); i++)
            total = total + facturi.get(i).getTotalCuTaxe();
        return total;
    }
    public double getTotalCuTaxeScutite(){
        return getTotalCuTaxe() * (1 - calculScutiriTaxe()/100);
    }
    public double getTotalTaraFaraTaxe(String taraOrigine){
        double total = 0;
        for(int i = 0; i< facturi.size(); i++)
            total = total + facturi.get(i).getTotalTaraFaraTaxe(taraOrigine);
        return total;
    }
    public double getTotalTaraCuTaxe(String taraOrigine){
        double total = 0;
        for(int i = 0; i< facturi.size(); i++)
            total = total + facturi.get(i).getTotalTaraCuTaxe(taraOrigine);
        return total;
    }
    public double getTotalTaraCuTaxeScutite(String taraOrigine){
        return getTotalTaraCuTaxe(taraOrigine) * (1-calculScutiriTaxe()/100);
    }
    
     double getTotalCategorieCuTaxe(String categorie){
        double total = 0 ;
        for(int i = 0; i< facturi.size(); i++)
            total = total + facturi.get(i).getTotalCategorieCuTaxe(categorie);
        return total;
    }
    
    public abstract double calculScutiriTaxe();
    
    public String toString(){
        String s = new String();
        s = s + nume + "\n";
        for(int i = 0; i< facturi.size(); i++)
            s = s + facturi.get(i) + "\n";
        return s;
    }
}
        