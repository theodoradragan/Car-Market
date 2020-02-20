import java.util.Comparator;

public class FacturaComparator implements Comparator<Factura>{
    public int compare(Factura o1, Factura o2){
        if(o1.getTotalCuTaxe() >  o2.getTotalCuTaxe())
                return 1;
        if(o1.getTotalCuTaxe() <  o2.getTotalCuTaxe())
                return -1;
        return 0;
    }
}
