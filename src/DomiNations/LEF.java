/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jessica
 */
public class LEF
{ 
    int oro, comida, aldeano;
    
    public LEF(int o, int c, int a)
    {
        super();
        oro=o;
        comida=c;
        aldeano=a;       
    }
    
    public void dibujar(Graphics g)
    {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(768,0,193,591);
        
        g.setColor(Color.BLACK);
        
        for(int i=0;i<8;i++)
        g.draw3DRect(768+i,i,193-2*i,591-2*i,false);
    }
    
    public void setOro(int o)
    {
        oro=o;
    }
    
    public void setAldeanos(int a)
    {
        aldeano=a;
    }
    
    public void setComida(int c)
    {
        comida=c;
    }
    
}
