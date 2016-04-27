package sprites;
/**Clase auxiliar que encapsula cuatro enteros, que nos ayudaran a conocer los puntos de un rectangulo
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Rectangulo {
    /**Coordenada del rectangulo*/
    public int izquierda; 
    /**Coordenada del rectangulo*/
    public int superior;
    /**Coordenada del rectangulo*/
    public int derecha; 
    /**Coordenada del rectangulo*/
    public int inferior;
    
    /**Constructor por defecto de la clase, inicializa todos los atributos de la clase en los valores por defecto*/
    public Rectangulo() {
        izquierda = 0;
        superior = 0;
        inferior = 0;
        derecha = 0;
    }
    
    /**Constructor sobrecargado de la clase, recibe todos los parametros necesarios para la clase
     *@param izquierda Izquierda 
     *@param superior Superior
     *@param derecha Derecha
     *@param inferior Inferior*/
    public Rectangulo(int izquierda, int superior, int derecha, int inferior) {
        this.izquierda = izquierda;
        this.superior = superior;
        this.derecha = derecha;
        this.inferior = inferior;
    }
}

