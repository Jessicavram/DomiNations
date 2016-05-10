package DomiNations;
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
    /**Vida maxima del item*/
    int vida_max;
    /**Vida Actual del item*/
    int vida_actual;
    /**Costo en comida del item*/
    int ancho;
    /**Costo en oro del item*/
    int alto;
    /*Tiempo que tarda en construir el item**/
    int tiempo;
    /**Numero de aldanos disponibles*/
    int nro_aldeanos_requeridos;
    /**Nivel actual del item (ej: 1,2,3,...)*/
    int nivel;
    /***/
    Animacion animacion;

    /**Constructor*/
    public Objetos_Graficos() {
        x = 0;
        y = 0;
        animacion = null;
        vida_max=0;
        vida_actual=0;
        ancho=0;
        alto=0;
        tiempo=0;
        nro_aldeanos_requeridos=0;
        nivel=1;
    }
    /**Metodo para obtener o cargar una imagen dada una posicion en el vector
     * @param  vida_maxima numero que define la cantidad de vida maxima del item
     * @param  costo_comida costo en recurso comida que requiere el item para ser creado
     * @param costo_oro costo en recurso oro que requiere el item para ser creado
     * @param tiempo tiempo en que tarda en crear el item
     * @param nro_aldeanos_requeridos numero de aldeanos que requiere para interactuar con el item
    */    
    
    /***/
    public void Seleccionar_Localizacion(int x, int y){
        this.x = x;
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

    /** Metodos Get*/
    public float getX() {
        return x;
    }    

    public float getY() {
        return y;
    }
   
    public int getVida_max() {
        return vida_max;
    }

    public int getVida_actual() {
        return vida_actual;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getTiempo() {
        return tiempo;
    }

    public int getNro_aldeanos_requeridos() {
        return nro_aldeanos_requeridos;
    }
    
    /**Metodos set*/
     public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public void setVida_max(int vida_max) {
        this.vida_max = vida_max;
    }

    public void setVida_actual(int vida_actual) {
        this.vida_actual = vida_actual;
    }

    public void setAncho(int anch) {
        this.ancho = anch;
    }

    public void setAlto(int alt) {
        this.alto = alt;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setNro_aldeanos_requeridos(int nro_aldeanos_requeridos) {
        this.nro_aldeanos_requeridos = nro_aldeanos_requeridos;
    }
    public void Verificar_camino(Matriz_Logica m,int x_inicial, int y_inicial){
        
       // System.out.println("Posi en matriz logica  col "+m.coordenaX_a_Columna((int)x, x_inicial)+"fil "+m.coordenadaY_a_Fila((int)y, y_inicial)); 
    
    }
}