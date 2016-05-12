 
 
package Cargador;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**Clase para cargar todos los sonidos necesarios para el juego desde el paquete Cargador.Sonidos sin necesidad de cargar desde el disco duro del equipo
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Cargar_Sonidos {
    /**Instancia de la clase para solo poder crear un objeto de la clase o la llamdada referencia al unico objeto de la clase*/
    private static Cargar_Sonidos instancia=null;
    /**Vector de sonidos en donde cargaremos todas las pistas del juego*/
    private Clip[] vec_sonidos;
    /**Constante que nos indica el total de pistas a cargar, que seria la suma de cada una de las constantes*/
    public static final int TOTAL_SONIDOS = 20;
    /**Constante para identificar la pista de saltar*/
    public static final int SALTO = 0;
    /**Constante para identificar la pista de pausa */
    public static final int PAUSA = 1;
    /**Constante para identificar la pista de quitar poder hongo */
    public static final int NO_HONGO =2;
    /**Constante para identificar la pista de dar poder de super mario */
    public static final int SI_HONGO = 3;
    /**Constante para identificar la pista de bloquesbloqueados */
    public static final int BLO_BLOQ= 4;
    /**Constante para identificar la pista DE ESPICHAR ENEMIGO */
    public static final int ESP_ENEMIGO = 5;
    /**Constante para identificar la pista de la moneda */
    public static final int MONEDA = 6;
    /**Constante para identificar la pista de salir hongo */
    public static final int SALIR_HONGO = 7;
    /**Constante para identificar la pista de muerte */
    public static final int MUERTE = 8;
    /**Constante para identificar la pista del mundo 1 */
    public static final int MUNDO_1= 9;
    /**Constante para identificar la pista fin de pista */
    public static final int FIN_PISTA = 10;  
    /**Constant para identificar la pista de fondo del menu*/
    public static final int FONDO_MENU= 11; 
    /**Constant para identificar la pista cuando mato a bowser*/
    public static final int NO_BOWSER= 12; 
    /**Constant para identificar la pista dee dsiparo*/
    public static final int DISPARO= 13;
    /**Constant para identificar la pista de comer flor*/
    public static final int FLOR= 14;
    /**Constant para identificar la pista de mato flor*/
    public static final int MATO_BOLA= 15;
    /**Constant para identificar la pista de comer flor*/
    public static final int CASTILLO= 16;
    /**Constant para identificar la pista de mato flor*/
    public static final int MUNDO_2= 17;
    /**Inciar construccion*/
    public static final int INICIAR_CONSTRUCCION= 18;
    /**Inciar construccion*/
    public static final int CONSTRUIR= 19;
    
    

    /**Constructor de la clase, privado para usar la instancia y solo poder crearse un objeto de la clase, es usado para cargar
     * el vector de sonidos a travez de la dirreccion url de cada una de las pista con ayuda de las constantes */
    private Cargar_Sonidos() {
        URL direccion;
        String nombre_pistas[] = new String[]{"salto.mp3","Pause.wav","PowerDown.wav","Powerup.wav","bloque_estatico.wav","espichar_enemigo.wav","moneda.wav"
                                    ,"salir_hongo.wav","muerte.wav","mundo1.wav","fin_pista.wav","fondo_menu.wav","mato_bowser.wav","disparo.wav","flor.wav"
                                    ,"mato_fuego.wav","Castillo.wav","Mundo2.wav","iniciar_construir.mp3","construir.mp3"};
        vec_sonidos = new Clip[TOTAL_SONIDOS];
            for(int i=0;i<TOTAL_SONIDOS;i++){
            /*Crear la ruta hacia el sonido*/
            direccion = this.getClass().getResource("Sonidos/"+nombre_pistas[i]);
            try {
                /*Crear el clip encargado de reproducir el sonido*/
                vec_sonidos[i] = AudioSystem.getClip();
                /*Abrir el sonido*/
                vec_sonidos[i].open(AudioSystem.getAudioInputStream(Encoding.PCM_SIGNED, AudioSystem.getAudioInputStream(direccion)));
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Cargar_Sonidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Cargar_Sonidos.class.getName()).log(Level.SEVERE, null, ex);
            }catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Cargar_Sonidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }///hasta aqui
        for(int i=0;i<TOTAL_SONIDOS;i++){           
            try {
                direccion = this.getClass().getResource("Sonidos/"+nombre_pistas[i]);
                vec_sonidos[i] = AudioSystem.getClip();
                vec_sonidos[i].open(AudioSystem.getAudioInputStream(Encoding.PCM_SIGNED, AudioSystem.getAudioInputStream(direccion)));
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Cargar_Sonidos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Cargar_Sonidos.class.getName()).log(Level.SEVERE, null, ex);
            }catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Cargar_Sonidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**Metodo para poder crear el objeto de la clase, solo permite crearla si la instancia no ha sido creada
     * @return Devuelve la instancia de la clase es en si es un objeto de la clase como tal*/
    public static Cargar_Sonidos obtener_instancia() {
        if(instancia == null)
            instancia = new Cargar_Sonidos();
        return instancia;
    }
    /**MÃ©todo que se encarga de reproducir las pistas
     * @param    identificador Es la posicion en el vector del sonido a reproducir el cual es una de las constantes de la clase
     * @param    apagado Nos indica si el sonido se esta reproduciendo o no
     * @param    repite Nos indica si el sonido debe ser repetido o no**/
    public void Reproducir_pistas(int identificador_pista, boolean apagado, boolean repite) {
        if(apagado || identificador_pista > TOTAL_SONIDOS || vec_sonidos[identificador_pista]==null)
            return;
        /*Se detiene el sonido por si acaso ya estaba en reproducciÃ³n*/
        detener_pista(identificador_pista);
        /*Rebobinamos el sonido, para que reproduzca desde el inicio*/
        vec_sonidos[identificador_pista].setFramePosition(0);
        if(!repite)
            /*Se reproduce el sonido una sola vez*/
            vec_sonidos[identificador_pista].start();
        else
            /*Se reproduce el sonido en bucle de forma continua*/
            vec_sonidos[identificador_pista].loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**Metodo que detiene un sonido que se encuentra actualmente en reproduccion
     * @param identificador Identificador del sonido a detener**/ 
    public void detener_pista(int identificador) {
        if(identificador > TOTAL_SONIDOS || vec_sonidos[identificador]==null || !vec_sonidos[identificador].isRunning())
            return;
         vec_sonidos[identificador].stop();
    }
}