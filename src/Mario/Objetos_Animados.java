package Mario;
/**Clase abstracta, que hereda de la clase objetos_graficos y de la cual heredaran todos los objetos animados del juego, es decir todos aquellos
 * que tienen movimineto, como el personaje de mario y los enemigos
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public abstract class Objetos_Animados extends Objetos_Graficos{
    /***/    
    int dirx;
    /***/
    int diry;
    /***/
    String currentAction;
    /***/
    float vx;
    /***/
    float vy;

    public String getCurrentAction() {
        return currentAction;
    }
    
    /***/
    public Objetos_Animados() {
        super();
        dirx = 1;
        diry = 1;
        currentAction = null;
        vx = 0;
        vy = 0;      
    }
    /***/
    public int getDirx() {
        return dirx;
    }
    /***/
    public int getDiry() {
        return diry;
    }
    /***/
    public void changeDirx(){
        dirx *= -1;
    }
    /***/
    public void changeDiry(){
        diry *= -1;
    }
    /***/
    public float getVx() {
        return vx;
    }
    /***/
    public void setVx(float vx) {
        this.vx = vx;
    }
    /***/
    public float getVy() {
        return vy;
    }
    /***/
    public void setVy(float vy) {
        this.vy = vy;
    }
    /***/
    public void Actualizar_PosicionX(){
        x += dirx * vx;
    }
    /***/
    public void Actualizar_PosicionY(){
        y += diry * vy;
    }
    /**Metodo para Modificar atributos
     * @param dirx direccion del objeto
     * @param x posicion en x
     * @param y posicion en y
     */
    public void Modificar(int dirx,int x,int y){
        this.dirx = dirx;
        this.x=x;
        this.y=y;
        this.vx=0;
        this.vy=0;
    }
    
    /*Detectar colisiones animados con animados**/
    public abstract void OnCollide_animada(Objetos_Animados objeto_colision, int lado);
    /**Obtiene la accion actual del objeto*/


}
