package Mario;
import java.awt.*;
import sprites.Animacion;
/**Resumen de la clase
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public abstract class Objetos_Graficos {
    /**Posicion en X del objeto*/
    float x;
    /**Posicion en Y del objeto*/
    float y;
    /**variable borrar*/
    boolean borrar=false;
    /**variable para sacar los itens*/
    int colision=0;
    /***/
    Animacion animacion;

    /**Constructor*/
    public Objetos_Graficos() {
        x = 0;
        y = 0;
        animacion = null;
    }
    
    /***/
    public void Seleccionar_Localizacion(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public void Mover(float dx, float dy){
        this.x += dx;
        this.y += dy;
    }    
    
    /***/
    public void Actualizar_Objeto_Grafico(double timePassed){
        animacion.Actualizar(timePassed);
    }
    
    /***/
    public void Dibujar(Graphics g){
        animacion.dibujar_cuadro(g, (int)x, (int)y);
    }
    
    /***/
    public Rectangle Obtener_Rectangulo(){
        return new Rectangle((int)x, (int)y, animacion.ancho_rectangulo, animacion.alto_rectangulo);
    }
    
    /***/
    public int Obtener_Alto(){
        return animacion.alto_rectangulo;
    }
    
    /***/
    public int Obtener_Ancho(){
        return animacion.ancho_rectangulo;
    }
    /**Cambiar atributo borrar*/
    public void Borrar(){
        borrar=true;
    }
    
    /***/
    public abstract void OnCollide(Objetos_Graficos objeto_colision, int lado);
    
}
