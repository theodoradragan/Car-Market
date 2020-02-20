import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FereastraIncarcare extends JFrame implements ActionListener{
    
    JFileChooser chooser1 = new JFileChooser();
    JFileChooser chooser2 = new JFileChooser();
    JFileChooser chooser3 = new JFileChooser();
    JOptionPane jop = new JOptionPane();
    JDialog dialog = jop.createDialog(this,"Dialog");
    
    JButton b1 = new JButton("Alege un fisier pentru facturi");
    JButton b2 = new JButton("Alege un fisier pentru taxe");
    JButton b3 = new JButton("Alege un fisier pentru produse");
    JButton b4 = new JButton("Inapoi");
    
    public FereastraIncarcare(String titlu){
        super(titlu);
        setLayout(new GridLayout(4,1));
        setSize(900,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooser1.addActionListener(this);
        add(b1);
        b1.addActionListener(this);
        add(b2);
        b2.addActionListener(this); 
        add(b3);
        b3.addActionListener(this);
        add(b4);
        b4.addActionListener(this);
        
        //creare element de tip gestiune
        Gestiune tmp = Gestiune.getInstance();
        tmp.citesteProduse();
        tmp.citesteTaxe();
        tmp.creareMagazine();
        tmp.creareFisier();
    }    
        
    public void actionPerformed(ActionEvent e){
            if(e.getSource()==b1){
                int returnVal = chooser1.showOpenDialog(this);
                File file = chooser1.getSelectedFile();
                if(!(file != null && file.getName().equals("facturi.txt")))
                    jop.showMessageDialog(this,"Va rugam alegeti alt fisier!","Eroare",JOptionPane.ERROR_MESSAGE);
            }
            
            if(e.getSource()==b2){
                int returnVal = chooser2.showOpenDialog(this);
                File file = chooser2.getSelectedFile();
                if(!(file != null && file.getName().equals("taxe.txt")))
                    jop.showMessageDialog(this,"Va rugam alegeti alt fisier!","Eroare",JOptionPane.ERROR_MESSAGE);
            }
            
            if(e.getSource()==b3){
                int returnVal = chooser3.showOpenDialog(this);
                File file = chooser3.getSelectedFile();
                if(!(file != null && file.getName().equals("produse.txt")))
                    jop.showMessageDialog(this,"Va rugam alegeti alt fisier!","Eroare",JOptionPane.ERROR_MESSAGE);
            }
            
            if(e.getSource() == b4){
                FereastraMeniu f = new FereastraMeniu("Meniu");
                setVisible(false);
                f.setVisible(true);      
            }
    }        
}