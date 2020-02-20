import java.util.Comparator;

public class MagazinComparator implements Comparator<Magazin>{
    public int compare(Magazin o1, Magazin o2){
        if(o1.getTotalFaraTaxe() >  o2.getTotalFaraTaxe())
                return 1;
        if(o1.getTotalFaraTaxe() <  o2.getTotalFaraTaxe())
                return -1;
        return 0;
    }
}
