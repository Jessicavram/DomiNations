/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.ScrollPaneConstants;


/**
 *
 * @author Jessica
 */
public class Estadisticas extends JWindow
{ 
    int oro, comida, aldeano;
    JPanel panel;
    JLabel et1, et2, et3, titulo1, titulo2;
    int x,y;
    ArrayList<LEF> listaLEF = new ArrayList<LEF>();
    JTextArea texto;
    JScrollPane scroll;
    
    public Estadisticas(int o, int c, int a)
    {
        super();
        oro=o;
        comida=c;
        aldeano=a;       
    }      
    
    public void mostrar()
    {
        this.setBounds(x,y,220,600);
        this.getContentPane().setBackground(Color.BLACK);
        
        JPanel panel = new JPanel();
        panel.setBounds(10,10,200,580);
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);
        
        et1 = new JLabel("Oro: "+oro);
        et1.setFont(new Font("Arial",Font.BOLD, 16));
        et1.setBounds(5,45,190,40);
        et1.setForeground(Color.WHITE);
        et1.setVisible(true);
        
        et2 = new JLabel("Comida: "+comida);
        et2.setFont(new Font("Arial",Font.BOLD, 16));
        et2.setBounds(5,85,190,40);
        et2.setForeground(Color.WHITE);
        et2.setVisible(true);
        
        et3 = new JLabel("Aldeanos: "+aldeano);
        et3.setFont(new Font("Arial",Font.BOLD, 16));
        et3.setBounds(5,125,190,40);
        et3.setForeground(Color.WHITE);
        et3.setVisible(true);
        
        titulo1 = new JLabel("Estadísticas");
        titulo1.setFont(new Font("Arial",Font.BOLD, 18));
        titulo1.setBounds(45,10,110,40);
        titulo1.setForeground(Color.ORANGE);
        titulo1.setVisible(true);
        
        titulo2 = new JLabel("LEF");
        titulo2.setFont(new Font("Arial",Font.BOLD, 18));
        titulo2.setBounds(80,175,40,40);
        titulo2.setForeground(Color.ORANGE);
        titulo2.setVisible(true);
        
        texto = new JTextArea();
        texto.setAutoscrolls(true);
        texto.setText("Descripcion       | Tiempo");
        texto.setBounds(5,230,190,50);
        
        
        texto.append("\nhola");
        texto.append("\nhola");
        texto.append("\nhola");
        texto.append("\nhola");
        texto.append("\nhola");
        texto.append("\nhola");
        
        texto.append("\nhola");
        
        
        texto.append("\nhola");
        
        
        
        
        scroll = new JScrollPane(texto);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        panel.add(et1);
        panel.add(et2);
        panel.add(et3);
        panel.add(titulo1);
        panel.add(titulo2);
        panel.add(scroll);
        panel.add(texto);
        
        this.add(panel);
        
        this.setLayout(null);
        this.setVisible(true);
    }

    public void setOro(int o)
    {
        oro=o;
        et1.setText("Oro: "+oro);
    }
    
    public void setAldeanos(int a)
    {
        aldeano=a;
        et2.setText("Aledeano: "+aldeano);
    }
    
    public void setComida(int c)
    {
        comida=c;
        et3.setText("Comida: "+comida);
    }
    
    
}