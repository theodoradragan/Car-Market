import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
    
public class FereastraAdministrare extends JFrame implements ActionListener{
    //Mesaj de eroare
    JOptionPane jop = new JOptionPane();
    JDialog dialog = jop.createDialog(this,"Dialog");
    
    // Vizualizare si ordonare
    JSplitPane p1;
    JSplitPane optiuni;
    JButton ordDen;
    JButton ordTara;
    
    // Adaugare
    JSplitPane adaugare;
    Panel detalii;
    TextField den;
    TextField tara;
    TextField cat;
    TextField pret;
    JButton adaugaProd;
    
    // Stergere
    JButton stergeProd;
    JSplitPane stergere;
    Panel detaliis;
    TextField dens;

    TextArea produse;
    AdministrareProduse adm = new AdministrareProduse();
    
    JButton inapoi;
    
    FereastraAdministrare(String title){
        super(title);
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        adm.administrareProduse();
        produse = new TextArea();

        ordDen = new JButton("Ordoneaza dupa denumire");
        ordTara = new JButton("Ordoneaza dupa tara");
        
        optiuni = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ordDen,ordTara);
        optiuni.setDividerLocation(100);
        
        p1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, produse, optiuni);
        ordDen.addActionListener(this);
        ordTara.addActionListener(this);
        add(p1);
        
        //Adaugare
        den = new TextField();
        tara = new TextField();
        cat  = new TextField();
        pret = new TextField();
        Panel detalii = new Panel();
        detalii.setLayout(new GridLayout(4,2));
        
        detalii.add(new JLabel("Denumire"));
        detalii.add(den);
        detalii.add(new JLabel("Tara"));
        detalii.add(tara);
        detalii.add(new JLabel("Categorie"));
        detalii.add(cat);
        detalii.add(new JLabel("Pret"));
        detalii.add(pret);
        
        adaugaProd = new JButton("AdaugaProdus");
        adaugaProd.addActionListener(this);
        
        adaugare = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, adaugaProd, detalii);
        JSplitPane doi = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p1, adaugare);
        doi.setDividerLocation(220);
        // Stergere
        stergeProd = new JButton("Sterge Produs");
        stergeProd.addActionListener(this);
        
        dens = new TextField();

        Panel detaliis = new Panel();
        detaliis.setLayout(new GridLayout(1,2));
        
        detaliis.add(new JLabel("Denumire"));
        detaliis.add(dens);

        JSplitPane stergere = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, stergeProd, detaliis);
        stergere.setDividerLocation(250);
        
        JSplitPane trei = new JSplitPane(JSplitPane.VERTICAL_SPLIT, doi, stergere);
        trei.setDividerLocation(400);
        
        inapoi = new JButton("Inapoi");
        inapoi.addActionListener(this);
        JSplitPane patru = new JSplitPane(JSplitPane.VERTICAL_SPLIT, trei, inapoi);
        patru.setDividerLocation(500);
        add(patru);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == ordDen){
            produse.setText(adm.ordineDenumire());
        }   
        
        if(e.getSource() == ordTara){
            produse.setText(adm.ordineTara());            
        }         
        
        if(e.getSource() == adaugaProd){
            adaugaProdus(den.getText(),tara.getText(),cat.getText(),pret.getText());
            //Reactualizez
            adm.tmp.citesteProduse();
            adm.tmp.creareMagazine();

        }
        
        if(e.getSource() == stergeProd){
            stergeProdus(dens.getText());
            //Reactualizez
            adm.tmp.citesteProduse();
            adm.tmp.creareMagazine();

        }
        
        if(e.getSource() == inapoi){
            FereastraMeniu fm = new FereastraMeniu("Meniu");
            setVisible(false);
            fm.setVisible(true);
        }
    }    
    
    public void adaugaProdus(String denumire, String tara, String categorie, String pret){
        for(int i = 0; i < adm.tmp.produse.size(); i++)
            if(adm.tmp.produse.get(i).getDenumire().equals(denumire)){
                jop.showMessageDialog(this,"Aveti deja acest fisier!","Eroare",JOptionPane.ERROR_MESSAGE);
                return;
            }
        
        try{
        File fout = new File("/home/theo/produse.txt");
        FileWriter fw = new FileWriter(fout,true);
 
        BufferedWriter bw = new BufferedWriter(fw);

        String line = new String("");
        line = line + denumire + " " + categorie + " ";
        for(int i = 0; i< adm.tmp.tari.size();i++){
            if(adm.tmp.tari.get(i).equals(tara))
                line = line + pret + " ";
            else
                line = line + "0";
            if(i!=adm.tmp.tari.size()-1)
                line = line + " ";
        }
        line = "\n" + line;

        bw.append(line);
        bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void stergeProdus(String denumire){
        try{
            File input = new File("/home/theo/produse.txt");
            File temp = new File("/home/theo/produsetemp.txt");
            
            BufferedReader reader = new BufferedReader(new FileReader(input));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
            
            String line;
            
            while((line = reader.readLine())!=null){
                if(null != line && !line.contains(denumire))
                    writer.write(line + "\n");
            }
            writer.close();
            reader.close();
            boolean successful = temp.renameTo(input);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
