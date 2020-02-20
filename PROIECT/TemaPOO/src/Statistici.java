import java.util.*;

class MyComparatorMagazin implements Comparator<Magazin>{
    public int compare(Magazin o1, Magazin o2){
        if(o2.getTotalCuTaxe()>o1.getTotalCuTaxe())
            return 1;
        if(o2.getTotalCuTaxe()<o1.getTotalCuTaxe())
                return -1;
        return 0;
    }
}

public class Statistici {
    static Gestiune tmp = Gestiune.getInstance();
    
    
    public Magazin celeMaiMariVanzari(){
        //ordonez magazinele descresctor dupa CuTaxe
        tmp.magazine.sort(new MyComparatorMagazin());
        //acum magazinul cu cele mai mari vanzari va fi primul        
        return tmp.magazine.get(0);
    }
    
    public Magazin magazinPeTara(String tara){
        int index = 0;
        double max = 0;
        for(int i = 0; i < tmp.magazine.size();i++){
            if(tmp.magazine.get(i).getTotalTaraCuTaxe(tara)> max){
                index = i;
                max = tmp.magazine.get(i).getTotalTaraCuTaxe(tara);
            }
        }
        return tmp.magazine.get(index);
    }
    
    public String toateTarile(){
        String s = new String("");
        for(int i = 0; i < tmp.tari.size();i++){
            s = s + (tmp.tari.get(i) + " : " +magazinPeTara(tmp.tari.get(i)).nume + "\n");
        }
        return s;
    }
    
    public Factura ceaMaiMareFactura(){
        Factura fac = new Factura();
        double max = 0;
        for(int i = 0; i < tmp.magazine.size(); i++){
            Magazin mag = tmp.magazine.get(i);
            for(int j = 0; j< mag.facturi.size(); j++)
                if(mag.facturi.get(j).getTotalFaraTaxe() > max){
                    fac = mag.facturi.get(j);
                    max = mag.facturi.get(j).getTotalFaraTaxe();
                }    
        }
            
        return fac;    
    }
    
     public Magazin magazinPeCategorie(String categorie){
        int index = 0;
        double max = 0;
        for(int i = 0; i < tmp.magazine.size(); i++){
           Magazin mag = tmp.magazine.get(i);
           if(mag.getTotalCategorieCuTaxe(categorie)>max)
               index = i;
        }
       Magazin mag = tmp.magazine.get(index);  
       return mag;
    }
     
    public String toateCategoriile(){
        String s = new String("");
        ArrayList<String> categorii = new ArrayList<String>();
        for(int i = 0; i < tmp.produse.size(); i++)
            if(!categorii.contains(tmp.produse.get(i).getCategorie()))
                categorii.add(tmp.produse.get(i).getCategorie());
        
        for(int i = 0; i < categorii.size();i++){
            s = s + (categorii.get(i) + " : " +magazinPeCategorie(categorii.get(i)).nume + "\n");
        }
        return s;
    }    
}
