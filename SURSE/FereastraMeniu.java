import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class FereastraMeniu extends JFrame implements ActionListener{
    
    JPanel buna;
    JPanel butoane;
    JSplitPane totul;
    Button b1 = new Button("Incarca fisiere");
    Button b2 = new Button("Administrare produse");
    Button b3 = new Button("Afisare statistici");
    
    FereastraIncarcare fi = new FereastraIncarcare("Incarcare fisiere");
    FereastraAdministrare fa = new FereastraAdministrare("Administrare produse");
    FereastraStatistici fs = new FereastraStatistici("Statistici");
    
    public FereastraMeniu(String titlu){
        super(titlu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,3));
        setSize(900,300);
        Font f = new Font("Monotype Corsiva",Font.PLAIN,30);

        buna = new JPanel();        
        buna.setBackground(Color.PINK);
        
        butoane = new JPanel();
        butoane.setBackground(Color.LIGHT_GRAY);
        
        String s = new String("Buna ziua! Va rugam alegeti o optiune:");
        JTextArea salut = new JTextArea(s);
        salut.setBackground(Color.PINK);
        salut.setFont(f);
        buna.add(salut);
        
        b1.setBackground(Color.PINK);
        b2.setBackground(Color.PINK);
        b3.setBackground(Color.PINK);
        butoane.add(b1);
        butoane.add(b2);
        butoane.add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        totul = new JSplitPane(JSplitPane.VERTICAL_SPLIT,buna,butoane);
        totul.setDividerLocation(100);
        add(totul);
        setFont(f);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b1){
            setVisible(false);
            fi.setVisible(true);
        }  
        if(e.getSource()==b2){
            setVisible(false);
            fa.setVisible(true);
        }    
        if(e.getSource()==b3){
            setVisible(false);
            fs.setVisible(true);
        }
    
    } 
    public static void main(String[] args){
        FereastraMeniu f = new FereastraMeniu("Meniu");
        f.setVisible(true);
    }
}
