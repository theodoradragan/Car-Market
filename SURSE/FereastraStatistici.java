import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FereastraStatistici extends JFrame implements ActionListener{
    Button b1 = new Button("Magazinul cu vanzari maxime");
    Button b2 = new Button("Magazinul cu vanzari maxime pe tara");
    Button b3 = new Button("Magazinul cu vanzari maxime pe categorie");
    Button b4 = new Button("Factura cu vanzari maxime");
    Button b5 = new Button("Inapoi");
    
    JSplitPane unu;
    JSplitPane doi;
    JSplitPane trei;
    JSplitPane patru;
    
    JSplitPane unucudoi;
    JSplitPane unudoitrei;
    JSplitPane toate;
    JSplitPane toateinapoi;
    
    JTextArea mag = new JTextArea();
    JTextArea tari = new JTextArea();
    JTextArea categ = new JTextArea();
    JTextArea fact = new JTextArea();
    
    Statistici stc = new Statistici();
    
    FereastraStatistici(String titlu){
        super("Fereastra statistici costuri");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(900,450);
        stc.tmp.citesteProduse();
        stc.tmp.citesteTaxe();
        stc.tmp.creareMagazine();
        stc.tmp.creareFisier();

        
        mag.setBackground(Color.PINK);
        tari.setBackground(Color.GRAY);
        categ.setBackground(Color.PINK);
        fact.setBackground(Color.GRAY);
        
        unu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,b1,mag);
        unu.setDividerLocation(300);
        doi = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,b2,tari);
        doi.setDividerLocation(300);
        trei = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,b3,categ);
        trei.setDividerLocation(300);
        patru = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,b4,fact);
        patru.setDividerLocation(300);

        unucudoi = new JSplitPane(JSplitPane.VERTICAL_SPLIT,unu,doi);
        unucudoi.setDividerLocation(50);
        unudoitrei = new JSplitPane(JSplitPane.VERTICAL_SPLIT,unucudoi,trei);
        unudoitrei.setDividerLocation(200);
        toate = new JSplitPane(JSplitPane.VERTICAL_SPLIT,unudoitrei,patru);
        toate.setDividerLocation(300);
        toateinapoi = new JSplitPane(JSplitPane.VERTICAL_SPLIT,toate,b5);
        toateinapoi.setDividerLocation(350);
            
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        
        add(toateinapoi);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            mag.setText(stc.celeMaiMariVanzari().nume);
        }
        if(e.getSource() == b2){
            tari.insert(stc.toateTarile(),0);
        }
        if(e.getSource() == b3){
            categ.insert(stc.toateCategoriile(),0);
        }
        if(e.getSource() == b4){
            fact.setText(stc.ceaMaiMareFactura().denumire);
        }
        if(e.getSource() == b5){
            FereastraMeniu fm = new FereastraMeniu("Meniu");
            setVisible(false);
            fm.setVisible(true);
        }
    }
}
