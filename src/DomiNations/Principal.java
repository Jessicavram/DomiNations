package DomiNations;
import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**Clase
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Principal extends JFrame {
    /**El escenario de juego*/
    static private Escena escena;
    /**Motor del juego*/
    static private Motor_Juego motor_de_juego;
    /**El escenario de juego*/
    static private Escena_Menu escena_menu;
    
    /**Constructor*/
    public Principal(){
        super("Super Mario Bros 1.0");
       
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(false);
        addKeyListener( new Eventos_de_Tecla() );
        Iniciar_Componentes();
        pack();
        setLocationRelativeTo(null);
    }
    
    /**Metodo Main
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Principal frame_juego = new Principal();
        Principal frame_menu = new Principal(true);
        
        do{
            if( escena.mario.vidas==0 ){
                        escena.mario = new Mario();
                        escena.Crear_Aldea();
                        escena_menu.setEstado(0);
                        Informacion_de_Teclado.setInstancia(null);
            }
            switch( escena_menu.getEstado() ){
                
                case 0:
                    Cargar_Sonidos.obtener_instancia().detener_pista(Cargar_Sonidos.MUNDO_1);
                    frame_juego.setVisible(false);
                    frame_menu.setVisible(true);
                    escena.e.setVisible(false);
                    motor_de_juego.Detener();   
                    break;
                case 1:
                    Cargar_Sonidos.obtener_instancia().detener_pista(Cargar_Sonidos.FONDO_MENU);
                    frame_menu.setVisible(false);
                    frame_juego.setVisible(true);
                    escena.e.x=frame_juego.getX()+frame_juego.getWidth()+20;
                    escena.e.y=frame_juego.getY()+20;
                    escena.e.mostrar();
                    motor_de_juego.Iniciar();
                break;                  
            }
            
            /*if(escena.isEstado()){
                escena_menu.setEstado(0);
                escena.setEstado(false);
            */
            

        }while(escena_menu.getEstado()!=3);
        System.exit(0);
    }
    
    
    /**Metodo que inicia los componentes del juego*/
    private void Iniciar_Componentes(){
        escena = new Escena();
        getContentPane().add(escena);
        escena.Crear_Aldea();
        motor_de_juego = new Motor_Juego(escena);
    }
    public Principal(boolean x){
            super("Super Mario Bros 1.0");
            escena_menu = new Escena_Menu();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
            setVisible(true);
            getContentPane().add(escena_menu);
            pack();
            setLocationRelativeTo(null);
        }
    
    class Eventos_de_Tecla extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int key=0;
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    key = Informacion_de_Teclado.IZQUIERDA;
                    break;
                case KeyEvent.VK_RIGHT:
                    key = Informacion_de_Teclado.DERECHA;
                    break;
                case KeyEvent.VK_DOWN:
                    key = Informacion_de_Teclado.ABAJO;
                    break;
                case KeyEvent.VK_UP:
                    key = Informacion_de_Teclado.ARRIBA;
                    break;
                case KeyEvent.VK_SPACE:
                    key = Informacion_de_Teclado.FUEGO;
                    break;
                    
            }
            
            Informacion_de_Teclado.getInstance().activeKey(key);
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            int key = 0;
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    key = Informacion_de_Teclado.IZQUIERDA;
                    break;
                case KeyEvent.VK_RIGHT:
                    key = Informacion_de_Teclado.DERECHA;
                    break;
                case KeyEvent.VK_DOWN:
                    key = Informacion_de_Teclado.ABAJO;
                    break;
                case KeyEvent.VK_UP:
                    key = Informacion_de_Teclado.ARRIBA;
                    break;
                case KeyEvent.VK_SPACE:
                    key = Informacion_de_Teclado.FUEGO;
                    break;
            }
            
            Informacion_de_Teclado.getInstance().deactiveKey(key);
        }
    }
}
