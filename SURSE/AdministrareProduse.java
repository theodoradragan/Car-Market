import java.util.*;

class MyComparator implements Comparator<Produs>{
    public int compare(Produs o1, Produs o2){
        return o1.getDenumire().compareTo(o2.getDenumire());
    }
}

class MyComparatorTara implements Comparator<Produs>{
    public int compare(Produs o1, Produs o2){
        return o1.getTaraOrigine().compareTo(o2.getTaraOrigine());
    }
}

public class AdministrareProduse {
        static Gestiune tmp = Gestiune.getInstance();
        
    public void administrareProduse(){
        tmp.citesteProduse();
        tmp.citesteTaxe();
        tmp.creareMagazine();
        //tmp.creareFisier();
    }    
        //vectorul de produse
    public String ordineDenumire(){
        String s = new String("");
        tmp.produse.sort(new MyComparator());
        for(int i = 0; i< tmp.produse.size(); i++)
            s = s + tmp.produse.get(i).toString();
        return s;
    }
    
    public String ordineTara(){
        String s = new String("");
        tmp.produse.sort(new MyComparatorTara());
        for(int i = 0; i< tmp.produse.size(); i++)
            s = s + tmp.produse.get(i).toString();
        return s;
    }    
   
}
