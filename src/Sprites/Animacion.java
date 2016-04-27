package sprites;
import java.awt.*;
/**Clase Animacion, contiene toda la informacion necesario para poder reproducir una animaciom, usando rectangulos ya que dada una hoja de sprites
 * toma recortes de la imagen y los pinta, tambien contiene un metodos ,uy usada en la aplicacion como el de seleccionar accion
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Animacion {
    /**Imagen en donde se encuentra la animacion a reproducir*/
    private Image imagen;
    /**Objeto que contiene las cuatro coordenadas de un rectangulo, este rectangulo de inicio se usara para dibujar la animacion*/
    private Rectangulo rectangulo_origen = new Rectangulo();
    /**Objeto que contiene las cuatro coordenadas de un rectangulo, este rectangulo de destino se usara para dibujar la animacion*/
    private Rectangulo rectangulo_destino = new Rectangulo();
    /**Altura de las imagenes en la animacion*/
    public int alto_rectangulo;
    /**Ancho de las imagenes en la animacion*/
    public int ancho_rectangulo;
    /**Cuantas imagenes contiene la animacion*/
    private int fotogramas_animacion;
    /**Identificador de la animacion que se este reproduciendo*/
    private int fotograma_actual = 0;
    /**Variable que controla el tiempo o la velocidad de reproduccion de cada rectangulo*/
    private double temporizador = 0;
    /**Identificador de la accion que se encuentre activa*/
    private Sprite accion_activa;
    /**Atributo que encapsula toda la informacion necesaria para reproducir la animacion*/
    private Hoja_Sprites hoja_sprites;
    /**Variable auxiliar para conocer si se termino de reproducir alguna animacion*/
    public boolean termino_animacion;

    /**Constructor sobrecargado de la clase, inicializa los valores necesarios para reproducir una animacion
     * @param accion_por_defecto Nombre de la accion en que iniciara la animacion
     * @param sprites_animacion Imagen en donde se encuentran todos los sprites para la animacion actual
     * @param hoja_sprite recibe toda la informacion de las acciones*/
    public Animacion(Hoja_Sprites hoja_sprite, String accion_por_defecto, Image sprites_animacion){
        hoja_sprites = hoja_sprite;
        accion_activa = hoja_sprites.Obtener_accion(accion_por_defecto);
        if(accion_activa==null)
            return;
        fotogramas_animacion = accion_activa.cant_imagenes_animacion;
        alto_rectangulo = accion_activa.Obtener_alto();
        ancho_rectangulo = accion_activa.Obtener_ancho();
        imagen = sprites_animacion;
        Actualizar_Rectangulo_Origen();
    }
    
    /**Metodo que nos permite seleccionar una accion, para reproducir su animacion
     * @param nombre nombre o identificador de la accion 
     * @param reinicia indica si la animacion se repite o no*/        
    
    public void Seleccionar_Accion(String nombre, boolean reinicia){
        accion_activa = hoja_sprites.Obtener_accion(nombre);
        if(accion_activa==null)
            return;
        fotogramas_animacion = accion_activa.cant_imagenes_animacion;
        alto_rectangulo = accion_activa.Obtener_alto();
        ancho_rectangulo = accion_activa.Obtener_ancho();
        if(reinicia)
            Reiniciar_Accion();
        termino_animacion = false;
        Actualizar_Rectangulo_Origen();
    }
    
    /**Metodo que reinicia la accion, colocando el temporizador y la animacion_actual en cero*/
    public void Reiniciar_Accion() {
        fotograma_actual = 0;
        temporizador = 0;
        termino_animacion = false;
        Actualizar_Rectangulo_Origen();
    }
    
    /**Actualiza el rectangulo de inicio de la animacion que lo llame, coloca el rectangulo de inicio en valores de la siguiente imagen a reproducir*/  
    private void Actualizar_Rectangulo_Origen(){
        rectangulo_origen.izquierda = accion_activa.P_sup_izq_x1 + fotograma_actual * ancho_rectangulo;
        rectangulo_origen.derecha = rectangulo_origen.izquierda + ancho_rectangulo;
        rectangulo_origen.superior = accion_activa.P_sup_izq_y1;
        rectangulo_origen.inferior = rectangulo_origen.superior + alto_rectangulo;
    }
    
    /**Metodo que actualiza la animacion, primero valida que no se halla terminado la animacion, en tal caso aumenta el temporizador luego 
     * hace otra serie de validaciones como saber la velocidad de la accion activa y saber si la animacion se repite o no 
     * @param tiempo */
    public void Actualizar(double tiempo) {
        if(termino_animacion)
            return;
        temporizador = temporizador + tiempo;
        if (temporizador >= accion_activa.fps) {
            temporizador -= accion_activa.fps;
            fotograma_actual++;
            if (fotograma_actual == fotogramas_animacion) {
                if(accion_activa.repite)
                    Reiniciar_Accion();
                else
                    termino_animacion = true;
            }
            Actualizar_Rectangulo_Origen();
        }
    }
    
    /**Metodo get(), que nos devuelve el valor de la variable termino_animacion*/
    public boolean Termino() {
        return termino_animacion;
    }
    
    /**Metodo que dibuja cada fotograma
     * @param grafico objeto de tipo graphics que nos permite dibujar en la aplicacion  
     * @param x coordenada en X para conocer el rectangulo donde finaliza la imagen
     * @param y coordenada en Y para conocer el rectangulo donde finaliza la imagen */
    public void dibujar_cuadro(Graphics grafico, int x, int y) {
        rectangulo_destino.izquierda = x;
        rectangulo_destino.superior = y;
        rectangulo_destino.derecha = rectangulo_destino.izquierda + ancho_rectangulo;
        rectangulo_destino.inferior = rectangulo_destino.superior + alto_rectangulo;
        grafico.drawImage(imagen, rectangulo_destino.izquierda, rectangulo_destino.superior, rectangulo_destino.derecha, rectangulo_destino.inferior, rectangulo_origen.izquierda, rectangulo_origen.superior, rectangulo_origen.derecha, rectangulo_origen.inferior, null);
    }
}