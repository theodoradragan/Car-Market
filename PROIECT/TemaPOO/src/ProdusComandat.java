public class ProdusComandat {
    private Produs produs;
    private double taxa;
    private int cantitate;
    
    void setProdus(Produs produs){
        this.produs = produs;
    }
    
    Produs getProdus(){
        return produs;
    }
    
    void setTaxa(double taxa){
        this.taxa = taxa;
    }
    
    double getTaxa(){
        return taxa;
    }
    
    void setCantitate(int cantitate){
        this.cantitate = cantitate;
    }
    
    int getCantitate(){
        return cantitate;
    }
    
    public String toString(){
        String s = new String();
        s = produs + " " + taxa + " " + cantitate;
        return s;
    }
}
