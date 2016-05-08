
package DomiNations;

import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Escena_Menu extends JPanel {
    private JLabel fondo;
    private JLabel nuevo_juego;
    private JLabel instrucciones;
    private JLabel instrucciones_imagen;
    private JLabel salir;
    private JButton salir_instrucciones;
    private JLabel creditos;
    private JLabel creditos_imagen;
    private JButton salir_creditos;
    private int estado;
    
    public Escena_Menu() {
        super();
        estado = 0;
        setLayout(null);
        setPreferredSize(new Dimension(640, 360));
        setBackground(Color.BLACK);
        crear_escena();
    }
    
    private void crear_escena(){
        nuevo_juego = new JLabel( Cargar_Imagenes.obtener_instancia().obtener_imagen( Cargar_Imagenes.NUEVO_JUEGO ) );
        nuevo_juego.setBounds( 250, 240,150, 36);
        nuevo_juego.setVisible(true);
        nuevo_juego.addMouseListener( new eventos() );
        add( nuevo_juego );
        
        instrucciones = new JLabel( Cargar_Imagenes.obtener_instancia().obtener_imagen( Cargar_Imagenes.INSTRUCCIONES ) );
        instrucciones.setBounds( 250, 280, 150, 36);
        instrucciones.setVisible(true);
        instrucciones.addMouseListener( new eventos() );
        add( instrucciones );
        
        instrucciones_imagen = new JLabel( Cargar_Imagenes.obtener_instancia().obtener_imagen( Cargar_Imagenes.INSTRUCCIONES_STATIC ) );
        instrucciones_imagen.setBounds( 0, 0, 480, 300);
        instrucciones_imagen.setVisible(false);
        add( instrucciones_imagen );
        
        creditos= new JLabel(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CREDITOS));
        creditos.setBounds( 310, 213, 160, 36);
        creditos.setVisible(false);
        add( creditos );
        
        salir = new JLabel( Cargar_Imagenes.obtener_instancia().obtener_imagen( Cargar_Imagenes.SALIR ) );
        salir.setBounds( 250, 320, 150, 36);
        salir.setVisible(true);
        salir.addMouseListener( new eventos() );
        add( salir );     
        
        salir_instrucciones = new JButton( Cargar_Imagenes.obtener_instancia().obtener_imagen( Cargar_Imagenes.SALIR ) );
        salir_instrucciones.setBounds( 402, 0, 150, 36);
        salir_instrucciones.setVisible(false);
        salir_instrucciones.addMouseListener( new eventos() );
        add( salir_instrucciones );  
        
        fondo = new JLabel( Cargar_Imagenes.obtener_instancia().obtener_imagen( Cargar_Imagenes.FONDO_INICIAL ) );
        fondo.setBounds( 0, 0, 640, 360);
        fondo.setVisible(true);
        add( fondo );  
        //Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.FONDO_MENU, false, true);
        
        creditos_imagen= new JLabel(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CREDITOS_IMG));
        creditos_imagen.setBounds( 0, 0, 480, 300);
        creditos_imagen.setVisible(false);
        add( creditos_imagen );
        
        salir_creditos= new JButton(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.SALIR));
        salir_creditos.setBounds(387, 237, 157, 49);
        salir_creditos.setVisible(false);
        salir_creditos.addMouseListener(new eventos());
        add(salir_creditos);
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    class eventos extends MouseAdapter{
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            if( e.getSource() instanceof JLabel )
            if( ((JLabel)e.getSource()).getIcon() == Cargar_Imagenes.obtener_instancia().obtener_imagen( Cargar_Imagenes.NUEVO_JUEGO)){
                estado = 1;   
            //Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MUNDO_1, false, true);
            }else if( ((JLabel)e.getSource()).getIcon() == Cargar_Imagenes.obtener_instancia().obtener_imagen( Cargar_Imagenes.INSTRUCCIONES)){
               estado = 2; 
               Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.FONDO_MENU, false, true);
               nuevo_juego.setVisible(false);
               instrucciones.setVisible(false);
               salir.setVisible(false);
               instrucciones_imagen.setVisible(true);
               salir_instrucciones.setVisible(true);
               creditos_imagen.setVisible(false);
               creditos.setVisible(false);
            }else if( ((JLabel)e.getSource()).getIcon() == Cargar_Imagenes.obtener_instancia().obtener_imagen( Cargar_Imagenes.CREDITOS)){
               estado = 4; 
               Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.FONDO_MENU, false, true);
               nuevo_juego.setVisible(false);
               instrucciones.setVisible(false);
               salir.setVisible(false);
               instrucciones_imagen.setVisible(false);
               salir_instrucciones.setVisible(false);
               creditos_imagen.setVisible(true);
               salir_creditos.setVisible(true);
               
            }
            else if( ((JLabel)e.getSource()).getIcon() == Cargar_Imagenes.obtener_instancia().obtener_imagen( Cargar_Imagenes.SALIR)){
                estado=3;
            }if( e.getSource() instanceof JButton ){
               Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.FONDO_MENU, false, true);
               estado=0;
               nuevo_juego.setVisible(true);
               instrucciones.setVisible(true);
               salir.setVisible(true);
               instrucciones_imagen.setVisible(false);
               salir_instrucciones.setVisible(false);
               creditos.setVisible(true);
               creditos_imagen.setVisible(false);
            }
        }
    
    }
    
}
