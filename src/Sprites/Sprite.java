package sprites;
/**Clase que maneja los sprites, usada para poder reproducir cada animacion
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Sprite {
    /**Coordenada en x que nos indica el punto superior izquierdo de la primera imagen del sprite, es decir el punto donde inicia la primera imagen de la animacion*/
    protected int P_sup_izq_x1;
    /**Coordenada en y que nos indica el punto superior izquierdo de la primera imagen del sprite, es decir el punto donde inicia la primera imagen de la animacion*/
    protected int P_sup_izq_y1;
    /**Coordenada en x que nos indica el punto inferior derecho de la primera imagen del sprite, es decir el punto donde finaliza la primera imagen de la animacion*/
    protected int P_inf_der_x2;
    /**Coordenada en y que nos indica el punto inferior derecho de la primera imagen del sprite, es decir el punto donde finaliza la primera imagen de la animacion*/
    protected int P_inf_der_y2;
    /**Variable Entera que nos da la cantidad de imagenes que contiene el sprite*/
    protected int cant_imagenes_animacion;
    /**Variable que nos ayudara a saber la velocida de del sprite, se calculara en fotogramas por segundo*/
    protected double fps;
    /**Indica si la animacion se repite o no*/
    protected boolean repite;

    /**Constructor parametrico de la clase que recibe todo los parametros necesarios para inicializar los atributos de la clase
     * @param x1 Entero que nos da una coordenada en X, del punto superior izquierdo dentro de la imagen de sprites
     * @param y1 Entero que nos da una coordenada en Y, del punto superior izquierdo dentro de la imagen de sprites
     * @param x2 Entero que nos da una coordenada en X, del punto inferior derecho dentro de la imagen de sprites
     * @param y2 Entero que nos da una coordenada en Y, del punto inferior derecho dentro de la imagen de sprites
     * @param cant Entero que indica la cantidad de imagenes de la animacion
     * @param rep Valor booleano que nos indica si la animacion es ciclica o no
     * @param fps Entero que nos da la velocidad de reproduccion de la animacion
     */
    public Sprite(int x1, int y1, int x2, int y2, int cols, boolean loop, int fps) {
        P_sup_izq_x1 = x1;
        P_sup_izq_y1 = y1;
        P_inf_der_x2 = x2;
        P_inf_der_y2 = y2;
        cant_imagenes_animacion = cols;
        repite = loop;
        this.fps = 1000.0 / (float)fps;
    }
    
    /**Metodo que calcula el alto de la imagen, restando las coordenadas en Y de la imagen
     * @return Retorna el alto de la imagen*/
    public int Obtener_alto(){
        return P_inf_der_y2 - P_sup_izq_y1+1;
    }
    
    /**Metodo que calcula el ancho de la imagen, restando las coordenadas en X de la imagen
     @return Retorna el ancho de la imagen*/
    public int Obtener_ancho(){
        return P_inf_der_x2 - P_sup_izq_x1+1;
    }
}