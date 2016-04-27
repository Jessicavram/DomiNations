/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mario;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class Motor_Fisico {
    private static Motor_Fisico instance = null;
    
    public static final int COL_LEFT = 1;
    public static final int COL_RIGHT = 2;
    public static final int COL_TOP = 3;
    public static final int COL_BOTTOM = 4;
    
    private ArrayList<Objetos_Graficos> staticObjects;
    private ArrayList<Objetos_Animados> dynamicObjects;
    
    private Motor_Fisico(){
        staticObjects = new ArrayList<Objetos_Graficos>();
        dynamicObjects = new ArrayList<Objetos_Animados>();
    }
    
    public static Motor_Fisico getInstance(){
        if(instance == null)
            instance = new Motor_Fisico();
        
        return instance;
    }
    
    public void update(){
        applyGravity();
        checkCollisions();
        checkCollisions_animados();
    }
    
    private void applyGravity(){
       Objetos_Animados obj;
       for(int i=0;i<dynamicObjects.size();i++){
           obj = dynamicObjects.get(i);
           obj.setVy(obj.getVy()+0.5f);
           obj.Actualizar_PosicionY();
       }
    }
    private void checkCollisions_animados(){
        Objetos_Animados statObject;
        Objetos_Animados dynObject;

        for(int i=0;i<dynamicObjects.size();i++){
            dynObject = dynamicObjects.get(i);

            for(int j=i+1;j<dynamicObjects.size();j++){
                statObject = dynamicObjects.get(j);
                
                if(dynObject.Obtener_Rectangulo().intersects(statObject.Obtener_Rectangulo())){
                    if(dynObject.getY()+dynObject.Obtener_Alto()<=statObject.getY()+20){
                        dynObject.OnCollide_animada(statObject, COL_TOP);
                        statObject.OnCollide_animada(dynObject, COL_BOTTOM);
                    }else if(dynObject.getY()>=statObject.getY()+statObject.Obtener_Alto()-5){
                        dynObject.OnCollide_animada(statObject, COL_BOTTOM);
                        statObject.OnCollide_animada(dynObject, COL_TOP);
                    }else if(dynObject.getX()<=statObject.getX()){
                        dynObject.OnCollide_animada(statObject, COL_RIGHT);
                        statObject.OnCollide_animada(dynObject, COL_LEFT);
                    }else{
                        dynObject.OnCollide_animada(statObject, COL_LEFT);
                        statObject.OnCollide_animada(dynObject, COL_RIGHT);
                    }
                }
            }
        }
    }
    
    public void borrar_animado( Objetos_Graficos obj ){
        dynamicObjects.remove(obj);
    }
    
    private void checkCollisions(){
        Objetos_Graficos statObject;
        Objetos_Animados dynObject;
        
        for(int i=0;i<dynamicObjects.size();i++){
            dynObject = dynamicObjects.get(i);
            for(int j=0;j<staticObjects.size();j++){
                statObject = staticObjects.get(j);
                
                if(dynObject.Obtener_Rectangulo().intersects(statObject.Obtener_Rectangulo())){
                    if(dynObject.getY()+dynObject.Obtener_Alto()<=statObject.getY()+20){
                        dynObject.setVy(0);
                        dynObject.setY(statObject.getY()-dynObject.Obtener_Alto()+1);
                        dynObject.OnCollide(statObject, COL_TOP);
                        statObject.OnCollide(dynObject, COL_BOTTOM);
                    }else if(dynObject.getY()>=statObject.getY()+statObject.Obtener_Alto()-5){
                        dynObject.setVy(0);
                        dynObject.setY(statObject.getY()+statObject.Obtener_Alto());
                        dynObject.OnCollide(statObject, COL_BOTTOM);
                        statObject.OnCollide(dynObject, COL_TOP);
                    }else if(dynObject.getX()<=statObject.getX()){
                        dynObject.setX(statObject.getX()-dynObject.Obtener_Ancho());
                        dynObject.OnCollide(statObject, COL_RIGHT);
                        statObject.OnCollide(dynObject, COL_LEFT);
                    }else{
                        dynObject.setX(statObject.getX()+statObject.Obtener_Ancho());
                        dynObject.OnCollide(statObject, COL_RIGHT);
                    }
                }
            }
        }
    }
    
    public void AnadirInanimado(Objetos_Graficos object){
        staticObjects.add(object);
    }
    
    public void addDynamicObject(Objetos_Animados object){
        dynamicObjects.add(object);
    }
    
    public void clearPhysicsEngine(){
        staticObjects.clear();
        dynamicObjects.clear();
    }
    
    public void mouse (float pos_x,float pos_y){
        Objetos_Animados dinamico;
        for (int i = 0; i < dynamicObjects.size(); i++) {
            dinamico = dynamicObjects.get(i);
            if((pos_x > dinamico.x && pos_x < dinamico.x + dinamico.Obtener_Ancho()) && (pos_y > dinamico.y && pos_y < dinamico.y + dinamico.Obtener_Alto())){
                System.out.println("Selecciono "+dinamico.currentAction+" Esta en la posicion "+i);
            }
        }
    }
    
}
