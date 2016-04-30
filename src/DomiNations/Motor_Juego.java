package DomiNations;
import java.awt.event.*;
import javax.swing.Timer;
/**Resumen de la clase
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Motor_Juego {
    /***/
    Timer tiempo;
    /***/
    static int cont;
    /***/
    Escena escena;
    
    /**Constructor */
    public Motor_Juego(Escena scene){
        this.tiempo = new Timer(20, new Eventos_Accion());
        this.escena = scene;
    }
    
    /***/
    public void Actualizar_Motor_de_Juego(){
        //Actualizar animaciones
        escena.update(tiempo.getDelay());       
        //Detectar Colisiones
        Motor_Fisico.getInstance().update();
        //Repintar la escena
        escena.repaint();
    }
    
    /***/
    public void Iniciar(){
        tiempo.start();
    }
    
    /***/
    public void Detener(){
        tiempo.stop();
    }
    
    /***/
    class Eventos_Accion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evento) {
            Actualizar_Motor_de_Juego();
            cont++; 
        }   
    }
}
