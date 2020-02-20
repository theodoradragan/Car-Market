import java.util.*;
import java.lang.*;
import java.io.*;
import java.text.DecimalFormat;

public class Gestiune {
    Vector<String> tari;
    ArrayList<Produs> produse;
    ArrayList<Magazin> magazine;
    HashMap<String, HashMap<String,Double>> taxe;
    HashMap<String, Double> inner;
    DecimalFormat df = new DecimalFormat("###.###");
    
    private static Gestiune gestiune = new Gestiune();
    
    private Gestiune(){
        produse = new ArrayList<Produs>();
        magazine = new ArrayList<Magazin>();
    }
    
    public static Gestiune getInstance(){
        return gestiune;
    }
    
    Scanner sc = null;
    public void citesteProduse(){
            tari = new Vector<String>();
            File file = new File("/home/theo/produse.txt");
            try{
                sc = new Scanner(file);

                // Aici contruiesc un vector de tari 
                String line = sc.nextLine();               
                StringTokenizer st = new StringTokenizer(line);
                st.nextToken();
                st.nextToken();
                while(st.hasMoreTokens()){
                    tari.add(st.nextToken());                  
                }
                //System.out.println(tari);
                // Aici am terminat de construit vectorul de tari
                
                // Aici incep sa creez lista de produse
                produse = new ArrayList<Produs>();
                while(sc.hasNextLine()){
                    line = sc.nextLine();
                    st = new StringTokenizer(line);
                    String daux = st.nextToken();
                    String caux = st.nextToken();
                    
                    // Pentru fiecare tara, o alta instanta a produsului curent
                    for(int i=0;i<tari.size();i++){        
                        Produs aux = new Produs();
                        aux.setDenumire(daux);
                        aux.setCategorie(caux);
                        aux.setTaraOrigine(tari.get(i));
                        aux.setPret(Double.parseDouble(st.nextToken()));
                        produse.add(aux);
                    }                   
                }     
            }
        catch(IOException e){
              System.out.println("Eroare la citire");
              e.printStackTrace();
        }
    }
    // Gata cu produsele
    
    // Acum creez taxele
        public void citesteTaxe(){
            File file1 = new File("/home/theo/taxe.txt");
            try{
                Scanner sc = new Scanner(file1);

                // Aici contruiesc un vector de tari
                tari = new Vector<String>();
                String line = sc.nextLine();               
                StringTokenizer st = new StringTokenizer(line);
                st.nextToken();
                while(st.hasMoreTokens()){
                    tari.add(st.nextToken());                  
                }
                // Am terminat de construit vectorul de tari
                
                // Aici incep sa creez dictionarul de taxe:
                // <tara,<categorie,procent>>
                taxe = new HashMap<String, HashMap<String,Double>>();
                //inner = new HashMap<String,Double>();
                Vector<String[]> peTari = new Vector<String[]>();
     
                while(sc.hasNextLine()){
                    line = sc.nextLine();
                    peTari.add(line.split(" "));

                }
                //Pentru fiecare tara
                for(int j = 0; j < tari.size(); j++){
                    inner = new HashMap<String,Double>();
                    for(int q = 0; q < peTari.size(); q++)
                        inner.put(peTari.get(q)[0], Double.parseDouble(peTari.get(q)[j+1]));
                    taxe.put(tari.get(j), inner);
                }
            }
        catch(IOException e){
              System.out.println("Eroare la citire");
              e.printStackTrace();
        }
    }
        public void creareMagazine(){
            File file = new File("/home/theo/facturi.txt");
            try{
                Scanner sc = new Scanner(file);
                String line = sc.nextLine();
                
                //citesc magazin cu magazin
                while(sc.hasNextLine()){                    
                    if (line.startsWith("Magazin")){
                        String[] parts = line.split(":");
                        Magazin mag = Factory.buildMagazin(parts[1]);
                        mag.nume = parts[2];
                        
                        //cat timp nu am ajuns la alt magazin
                        line = sc.nextLine();
                        while(!line.startsWith("Magazin")){
                            if(!line.startsWith("Factura"))
                                line = sc.nextLine();
                            Factura fac = new Factura();
                            fac.denumire = line;
                            line = sc.nextLine();
                            line = sc.nextLine();
                            //acum am in line primul produs
                            
                            //cat timp nu s-a terminat factura
                            while(!line.equals("")){
                                String[] parti = line.split(" ");
                                
                                Produs produs = new Produs();
                                for(int i = 0;i < produse.size(); i++)
                                    if(produse.get(i).getDenumire().equals(parti[0]) && produse.get(i).getTaraOrigine().equals(parti[1]))
                                        produs = produse.get(i);
                                
                                ProdusComandat pc = new ProdusComandat();
                                pc.setProdus(produs);
                                pc.setTaxa(taxe.get(parti[1]).get(produs.getCategorie()));
                                pc.setCantitate(Integer.parseInt(parti[2]));
                                fac.produse.add(pc); 
                                
                                //citesc urmatorul produs
                                if(sc.hasNextLine())
                                    line = sc.nextLine();
                                else
                                    break;
                            }
                            // Am terminat factura, o adaug magazinului curent
                            mag.facturi.add(fac);
                            if(sc.hasNextLine())
                                    line = sc.nextLine();
                                else
                                    break;
                        }
                        // Am terminat cu magazinul, il adaug vectorului
                        magazine.add(mag);
                    }
                }             
            }
        catch(IOException e){
              System.out.println("Eroare la citire");
              e.printStackTrace();
        }
            
        }
        
        public void creareFisier(){
            try{

            File fout = new File("/home/theo/outputulmeu.txt");
            FileOutputStream fos = new FileOutputStream(fout);
 
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            
            Collections.sort(magazine, new MagazinComparator());
            int i;
            bw.write("MiniMarket\n");
            
            for(i=0;i<magazine.size();i++){
                
                if(magazine.get(i) instanceof MiniMarket){
                    bw.write(magazine.get(i).nume + "\n\n");
                    MiniMarket mini = (MiniMarket) magazine.get(i);
                    Collections.sort(mini.facturi,new FacturaComparator());
                    bw.write("Total ");
                    bw.write(df.format(mini.getTotalFaraTaxe()) + " ");
                    bw.write(df.format(mini.getTotalCuTaxe()) + " ");
                    bw.write(df.format(mini.getTotalCuTaxeScutite()) + " \n\n");
                    
                    //pentru fiecare tara
                    bw.write("Tara\n");
                    for(int j = 0; j< tari.size(); j++){
                        String tara = tari.get(j);
                        bw.write(tara + " ");
                        bw.write(df.format(mini.getTotalTaraFaraTaxe(tara)) + " ");
                        bw.write(df.format(mini.getTotalTaraCuTaxe(tara)) + " ");
                        bw.write(df.format(mini.getTotalTaraCuTaxeScutite(tara)) + " \n");
                    }
                    bw.write("\n");
                    
                    //facturili
                    
                    for(int j = 0; j< mini.facturi.size(); j++){
                        Factura fact = mini.facturi.get(j);
                        bw.write(fact.denumire + "\n\n");
                        bw.write("Total ");
                        bw.write(df.format(fact.getTotalFaraTaxe()) + " ");
                        bw.write(df.format(fact.getTotalCuTaxe()) + "\n\n");
                        bw.write("Tara\n");
                        for(int q = 0; q< tari.size(); q++){
                            String tara = tari.get(q);
                            bw.write(tara + " ");
                            bw.write(df.format(fact.getTotalTaraFaraTaxe(tara)) + " ");
                            bw.write(df.format(fact.getTotalTaraCuTaxe(tara)) + "\n");   
                            
                        }
                    }
                bw.write("\n");
                }
            }
            
            bw.write("MediumMarket\n");
            for( i=0;i<magazine.size();i++){
                if(magazine.get(i) instanceof MediumMarket){
                    bw.write(magazine.get(i).nume + "\n\n");
                    MediumMarket mini = (MediumMarket) magazine.get(i);
                    Collections.sort(mini.facturi,new FacturaComparator());
                    bw.write("Total ");
                    bw.write(df.format(mini.getTotalFaraTaxe()) + " ");
                    bw.write(df.format(mini.getTotalCuTaxe()) + " ");
                    bw.write(df.format(mini.getTotalCuTaxeScutite()) + " \n\n");
                    
                    //pentru fiecare tara
                    bw.write("Tara\n");
                    for(int j = 0; j< tari.size(); j++){
                        String tara = tari.get(j);
                        bw.write(tara + " ");
                        bw.write(df.format(mini.getTotalTaraFaraTaxe(tara)) + " ");
                        bw.write(df.format(mini.getTotalTaraCuTaxe(tara)) + " ");
                        bw.write(df.format(mini.getTotalTaraCuTaxeScutite(tara)) + " \n");
                    }
                    bw.write("\n");
                    
                    //facturili
                    
                    for(int j = 0; j< mini.facturi.size(); j++){
                        Factura fact = mini.facturi.get(j);
                        bw.write(fact.denumire + "\n\n");
                        bw.write("Total ");
                        bw.write(df.format(fact.getTotalFaraTaxe()) + " ");
                        bw.write(df.format(fact.getTotalCuTaxe()) + "\n\n");
                        bw.write("Tara\n");
                        for(int q = 0; q< tari.size(); q++){
                            String tara = tari.get(q);
                            bw.write(tara + " ");
                            bw.write(df.format(fact.getTotalTaraFaraTaxe(tara)) + " ");
                            bw.write(df.format(fact.getTotalTaraCuTaxe(tara)) + "\n");   
                            
                        }
                    }
                bw.write("\n");
                }
            }
            
            bw.write("HyperMarket\n");
            for(i=0;i<magazine.size();i++){
                if(magazine.get(i) instanceof HyperMarket){
                    bw.write(magazine.get(i).nume + "\n\n");
                    HyperMarket mini = (HyperMarket) magazine.get(i);
                    Collections.sort(mini.facturi,new FacturaComparator());
                    bw.write("Total ");
                    bw.write(df.format(mini.getTotalFaraTaxe()) + " ");
                    bw.write(df.format(mini.getTotalCuTaxe()) + " ");
                    bw.write(df.format(mini.getTotalCuTaxeScutite()) + " \n\n");
                    
                    //pentru fiecare tara
                    bw.write("Tara\n");
                    for(int j = 0; j< tari.size(); j++){
                        String tara = tari.get(j);
                        bw.write(tara + " ");
                        bw.write(df.format(mini.getTotalTaraFaraTaxe(tara)) + " ");
                        bw.write(df.format(mini.getTotalTaraCuTaxe(tara)) + " ");
                        bw.write(df.format(mini.getTotalTaraCuTaxeScutite(tara)) + " \n");
                    }
                    bw.write("\n");
                    
                    //facturile
                    
                    for(int j = 0; j< mini.facturi.size(); j++){
                        Factura fact = mini.facturi.get(j);
                        bw.write(fact.denumire + "\n\n");
                        bw.write("Total ");
                        bw.write(df.format(fact.getTotalFaraTaxe()) + " ");
                        bw.write(df.format(fact.getTotalCuTaxe()) + "\n\n");
                        bw.write("Tara\n");
                        for(int q = 0; q< tari.size(); q++){
                            String tara = tari.get(q);
                            bw.write(tara + " ");
                            bw.write(df.format(fact.getTotalTaraFaraTaxe(tara)) + " ");
                            bw.write(df.format(fact.getTotalTaraCuTaxe(tara)) + "\n");   
                            
                        }
                    }
                bw.write("\n");
                }
                    
            }
            bw.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    
    public static void main(String args[]){
        Gestiune tmp = Gestiune.getInstance();
        tmp.citesteProduse();
        tmp.citesteTaxe();
        tmp.creareMagazine();
        tmp.creareFisier();
    }
}
