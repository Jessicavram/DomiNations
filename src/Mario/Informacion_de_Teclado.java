package Mario;
/**Clase
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Informacion_de_Teclado {
    /***/
    private static Informacion_de_Teclado instancia=null;
    /**Constante de direccion a la izquierda*/
    public static final int IZQUIERDA=1; 
    /**Constante de direccion a la derecha*/
    public static final int DERECHA=2; 
    /**Constante de direccion arriba*/
    public static final int ARRIBA=4; 
    /**Constante de direccion a la abajo*/
    public static final int ABAJO=8;
    /**Constante de direccion de fuego*/
    static int FUEGO=16;
    /***/
    private int keys;
    
    
    /**Constructor*/
    private Informacion_de_Teclado() {
        keys = 0;
    }

    public static void setInstancia(Informacion_de_Teclado instancia) {
        Informacion_de_Teclado.instancia = instancia;
    }
    
    /**Instrancia de la clase*/
    public static Informacion_de_Teclado getInstance(){
        if(instancia==null)
            instancia = new Informacion_de_Teclado();  
        return instancia;
    }
    
    /***/
    public boolean isActive(int key){
        return (keys & key)==key;  
    }
    
    public void deactiveKey(int key){
        keys &= ~key; 
    }
    
    public void activeKey(int key){
        keys |= key;
    }
    
    public boolean isAnyKeyActived(){
        return keys != 0;
    }
}