package DomiNations;
import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import sprites.Rectangulo;
/**Clase para cargar todas las imagenes necesarias para el juego desde el paquete cargador.Imagenes sin necesidad de cargar desde el disco duro del equipo
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Escena extends JPanel implements MouseListener{
    /**Personajo Mario*/
    Mario mario;
    /**Escena actual*/
    int escena_actual=1;
    /**Termino la escena*/
    boolean ter_esc=false;
    /**Arreglo para objetos de fondo*/
    ArrayList<Objetos_Graficos> vec_objetos_fondo;
    /**Vrctor para almacenar los enemigos*/
    ArrayList<Objetos_Graficos> vec_enemigos;
    /**Vector para guardar los cuadros que interactuan con mario*/
    ArrayList<Objetos_Graficos> vec_Bloques;
    /**Estado de la escena*/
    boolean estado=false;

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    int cont = 0;
  
    /**Constructor*/
    public Escena(){
        super();
        addMouseListener(this);
        setPreferredSize(new Dimension(480, 300));
        mario = new Mario();
        vec_objetos_fondo = new ArrayList<Objetos_Graficos>();
        vec_enemigos = new ArrayList<Objetos_Graficos>();
        vec_Bloques= new ArrayList<Objetos_Graficos>();
    }
    
    @Override
    public void paint(Graphics g){
        //Pintar el fondo de la aplicación
        g.setColor( Color.BLACK );
        g.fillRect(0, 0, getWidth(), getHeight());
        //Pintar objetos de fondo
        for(int i=0; i<vec_objetos_fondo.size();i++){
            vec_objetos_fondo.get(i).Dibujar(g);
        }
        //Pintar los enemigos
        for(int i=0;i<vec_enemigos.size();i++)
            vec_enemigos.get(i).Dibujar(g); 
        //pintan los bloques
        for(int i=0;i<vec_Bloques.size();i++){            
            vec_Bloques.get(i).Dibujar(g);
        }
        //Pintar el personaje
        mario.Dibujar(g);
        g.drawString( "Tiempo: "+(Motor_Juego.cont/50) , 0, 10);
    }
    /**Metodo que actializa la escena y donde se realizan acciones logicas*/
    public void update(double timePassed){
        if(mario.vidas==0)estado=true;
        if(mario.murio==0){
        //cont = cont + (int)mario.getVx()*mario.getDirx();
        mario.Actualizar_Objeto_Grafico(timePassed);
        //Verificar si salio de la pantalla
        if( mario.dirx==1){
          if(escena_actual==1 && cont<3030){
            if( mario.getX()<=300 )
                mario.Actualizar_PosicionX();
            else
                moveScreen(-mario.getVx() );
          }
          if(escena_actual==2 && cont<2960){
              if( mario.getX()<=200 )
                mario.Actualizar_PosicionX();
            else
                moveScreen(-mario.getVx() );
          }
        if(escena_actual==3 && cont<2950){
            if( mario.getX()<=200 )
                mario.Actualizar_PosicionX();
            else
                moveScreen(-mario.getVx() );
          }
        
        
        }else if( mario.getX()>0 && cont<=3000)
            mario.Actualizar_PosicionX();
        
        if(mario.colision==1){
        bola_fuego bola= new bola_fuego(mario.dirx);
        if(mario.dirx==1)
        bola.Seleccionar_Localizacion((int)mario.getX()+mario.Obtener_Ancho()+5,(int) mario.getY()+(mario.Obtener_Ancho()/2));
        else
        bola.Seleccionar_Localizacion((int)mario.getX()-10,(int) mario.getY()+(mario.Obtener_Ancho()/2));
        vec_enemigos.add(bola);
        Motor_Fisico.getInstance().addDynamicObject(bola);
        mario.colision=0;
        }


        //actualizar enemigos
        for(int i=0;i<vec_enemigos.size();i++){
            if(vec_enemigos.get(i).getY()>=310 || vec_enemigos.get(i).getX()<=-300 || vec_enemigos.get(i).borrar){
                //eliminar los enemigos que se caigan
                Motor_Fisico.getInstance().borrar_animado( vec_enemigos.get(i) );
                vec_enemigos.remove(i);
            }else
                vec_enemigos.get(i).Actualizar_Objeto_Grafico(timePassed);
        }
        //actualizar bloques
        for(int i=0;i<vec_Bloques.size();i++){
            if(vec_Bloques.get(i).colision==10)ter_esc=true;
            else if(vec_Bloques.get(i).colision!=0){
                //Bloques nuevo= new Items((int)vec_Bloques.get(i).getX()-1,(int) vec_Bloques.get(i).getY(), 0, 0); 
                //nuevo.Seleccionar_Localizacion((int)vec_Bloques.get(i).getX()-1,(int) vec_Bloques.get(i).getY());
                if(mario.estado==0 && vec_Bloques.get(i).colision==1){
                //HONGO---->
                Items hongo= new Items((int)vec_Bloques.get(i).getX()-1,(int) vec_Bloques.get(i).getY(), -1, 5); 
                hongo.Seleccionar_Localizacion((int)vec_Bloques.get(i).getX()-1,(int) vec_Bloques.get(i).getY());
                vec_Bloques.add(hongo);
                Motor_Fisico.getInstance().addDynamicObject(hongo); 
                vec_Bloques.get(i).colision=0;}
                else if(mario.estado==1 && vec_Bloques.get(i).colision==1){
                Items flor= new Items(5); 
                flor.Seleccionar_Localizacion((int)vec_Bloques.get(i).getX()-1,(int) vec_Bloques.get(i).getY());
                vec_Bloques.add(flor);
                Motor_Fisico.getInstance().addDynamicObject(flor);                
                }                
                //vec_Bloques.set(i, nuevo);
                vec_Bloques.get(i).colision=0;
                
            }
            else if(vec_Bloques.get(i).getY()>=310 || vec_Bloques.get(i).getX()<=-16 || vec_Bloques.get(i).borrar){
                Motor_Fisico.getInstance().borrar_animado( vec_Bloques.get(i) );
                vec_Bloques.remove(i);
            }else
                vec_Bloques.get(i).Actualizar_Objeto_Grafico(timePassed);
        }
        //matar a mario por caerse
            if(mario.y>=310){
                Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MUERTE, false, false);
                mario.murio=1;  
                mario.vidas--;
            }  
        }//desicion si murio
        else{
            if(escena_actual==1){
                Cargar_Sonidos.obtener_instancia().detener_pista(Cargar_Sonidos.MUNDO_1);
                crearNivel1();}
            if(escena_actual==2)
                crearNivel2();
            if(escena_actual==3)
            crearEscenaCastillo();
            Motor_Juego.cont =0;
            mario.murio=0;
            mario.estado=0;
            mario.currentAction="MpDquieto";
            cont=0;
            setBackground(Color.BLACK);
            
        }
           if(ter_esc){
               escena_actual++;
            if(escena_actual==1){
                Cargar_Sonidos.obtener_instancia().detener_pista(Cargar_Sonidos.MUNDO_1);
                crearNivel1();}
            if(escena_actual==2)
                crearNivel2();
            if(escena_actual==3)
            crearEscenaCastillo();
            Motor_Juego.cont =0;cont=0;
            ter_esc=false;
           }
    }
    
    public void moveScreen(float dx){
        for(int i=0;i<vec_objetos_fondo.size();i++){
            /*if(i==1)
                i++;*/
            vec_objetos_fondo.get(i).Mover(dx, 0);
        }
        for(int i=0;i<vec_enemigos.size();i++)
            vec_enemigos.get(i).Mover(dx, 0);
        for(int i=0;i<vec_Bloques.size();i++)
            vec_Bloques.get(i).Mover(dx, 0);
    
    }
      
    public void crearNivel1(){       
        vec_objetos_fondo.clear();
        vec_enemigos.clear();
        vec_Bloques.clear();
        Motor_Fisico.getInstance().clearPhysicsEngine();
        mario.Modificar(1, 0, 266);
        mario.Seleccionar_Localizacion(0,250);
        Motor_Fisico.getInstance().addDynamicObject(mario);   

        //NIVEL 1
        
        //Imagen de Fondo
        Objetos_Inanimados obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.MUNDO_1).getImage(), new Rectangulo(0, 0, 3200, 300) );
        obj.Seleccionar_Localizacion(0,0);
        vec_objetos_fondo.add(obj);
        
        //Vidas Mario
        if(mario.vidas>0){
            obj = new  Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(357,105,357+(mario.vidas*19),121));
            obj.Seleccionar_Localizacion(200, 2);
            vec_objetos_fondo.add(obj);
            Motor_Fisico.getInstance().AnadirInanimado(obj);
        }
        
        //Bloque de monedas
        Items bloques= new Items(8);
        bloques.Seleccionar_Localizacion(3040, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(0, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(57, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(104, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(161, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(218, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(275, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(332, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        // 2 Nivel piso
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(72,0 , 87, 65));
        obj.Seleccionar_Localizacion(389, 234);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(133,0 , 190, 65));
        obj.Seleccionar_Localizacion(405, 234);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(133,0 , 190, 65));
        obj.Seleccionar_Localizacion(462, 234);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(133,0 , 190, 65));
        obj.Seleccionar_Localizacion(519, 234);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(200,0 , 209, 65));
        obj.Seleccionar_Localizacion(576, 234);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //HUECO
        
        //Vuelve a nivel 1 del piso
        
        //Piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(617, 245);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //Piedra arriba
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(617, 225);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(215,0 , 231, 31));
        obj.Seleccionar_Localizacion(615, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(631, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(688, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(234,0 , 250, 31));
        obj.Seleccionar_Localizacion(745, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(740, 245);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //Piedra arriba
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(740, 225);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //HUECO
        
        //Llega al salto de los troncos
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(216,36 , 261, 51));
        obj.Seleccionar_Localizacion(810, 241);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
                      
        //HUECO
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(216,36 , 261, 51));
        obj.Seleccionar_Localizacion(900, 241);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //HUECO
        
        //Todavia en nivel 1 piso
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(215,0 , 231, 31));
        obj.Seleccionar_Localizacion(995, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
                
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(1011, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
             
                
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(1068, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(1125, 268);
        vec_objetos_fondo.add(obj);        
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 2 piso
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(264,0 , 280, 51));
        obj.Seleccionar_Localizacion(1182, 249);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
                        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(304,0 , 361, 51));
        obj.Seleccionar_Localizacion(1198, 249);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Nivel 3 piso
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(368,0 , 385,84));
        obj.Seleccionar_Localizacion(1254, 216);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
                        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(414,0 , 471,84));
        obj.Seleccionar_Localizacion(1261, 216);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Nivel 4 piso
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,85 , 16,202));
        obj.Seleccionar_Localizacion(1318, 183);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(45,85 , 102,202));
        obj.Seleccionar_Localizacion(1334, 183);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(45,85 , 102,202));
        obj.Seleccionar_Localizacion(1391, 183);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(45,85 , 102,202));
        obj.Seleccionar_Localizacion(1334, 183);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(45,85 , 102,202));
        obj.Seleccionar_Localizacion(1391, 183);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(45,85 , 102,202));
        obj.Seleccionar_Localizacion(1448, 183);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(45,85 , 102,202));
        obj.Seleccionar_Localizacion(1505, 183);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(45,85 , 102,202));
        obj.Seleccionar_Localizacion(1562, 183);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(45,85 , 102,202));
        obj.Seleccionar_Localizacion(1619, 183);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(23,85 , 40,202));
        obj.Seleccionar_Localizacion(1675, 183);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Volver al Nivel 3 piso   
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(414,0 , 471,84));
        obj.Seleccionar_Localizacion(1691, 216);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(393,0 , 408,84));
        obj.Seleccionar_Localizacion(1748, 216);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Volver al Nivel 2 piso        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(304,0 , 361, 51));
        obj.Seleccionar_Localizacion(1763, 249);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(284,0 , 300, 51));
        obj.Seleccionar_Localizacion(1820, 249);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Vuelve al Nivel 1 piso
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(1835, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(1892, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(1949, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(2006, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(234,0 , 251, 33));
        obj.Seleccionar_Localizacion(2063, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //HUECO
        
        //Llega al salto de los troncos
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(216,36 , 261, 51));
        obj.Seleccionar_Localizacion(2130, 269);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
               
        //HUECO        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(216,36 , 261, 51));
        obj.Seleccionar_Localizacion(2220, 269);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //HUECO
        
        //vuelve al nivel 1 piso, luego de los troncos
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(215,0 , 231, 31));
        obj.Seleccionar_Localizacion(2315, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(2331, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(2388, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(2445, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(234,0 , 250, 31));
        obj.Seleccionar_Localizacion(2502, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
       
        //Hueco        
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(215,0 , 231, 31));
        obj.Seleccionar_Localizacion(2548, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(2564, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //empieza escalera para salto
        
        //nivel 1 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(200,332 , 220, 365));
        obj.Seleccionar_Localizacion(2622, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
       
        //nivel 2 de la escalera 
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(220,312 , 240, 365));
        obj.Seleccionar_Localizacion(2642, 248);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //HUECO
        
        //Nivel 2 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(220,312 , 240, 365));
        obj.Seleccionar_Localizacion(2722, 248);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        // vuelve al nivel 1 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(200,332 , 220, 365));
        obj.Seleccionar_Localizacion(2742, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //continua el piso
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(2762, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(2809, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Empieza la escalera del final
        
        //nivel 1 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(200,332 , 220, 365));
        obj.Seleccionar_Localizacion(2866, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 2 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(220,312 , 240, 365));
        obj.Seleccionar_Localizacion(2886, 248);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 3 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(240,292 , 260, 365));
        obj.Seleccionar_Localizacion(2906, 228);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 4 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(260,272 , 280, 365));
        obj.Seleccionar_Localizacion(2926, 208);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj); 
        
        //nivel 5 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(280,252 , 300, 365));
        obj.Seleccionar_Localizacion(2946, 188);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(2966, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(2986, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(3006, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(3026, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(3046, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(3066, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(3086, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(3106, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(3126, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(3146, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(3166, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //nivel 6 de la escalera
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(300,232 , 320, 365));
        obj.Seleccionar_Localizacion(3186, 168);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        
    
        //Bloque de monedas
        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(1040, 205);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(1);
        bloques.Seleccionar_Localizacion(1055, 205);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(1070, 205);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);

        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(150, 205);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(1);
        bloques.Seleccionar_Localizacion(165, 205);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(181, 205);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(1);
        bloques.Seleccionar_Localizacion(165, 145);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(197, 205);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(1);
        bloques.Seleccionar_Localizacion(213, 145);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);        
        
        bloques= new Items(2);
        bloques.Seleccionar_Localizacion(212, 205);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(229, 205);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(1450, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
       
        bloques= new Items(1);
        bloques.Seleccionar_Localizacion(1465, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(1480, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(2);
        bloques.Seleccionar_Localizacion(1495, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(1510, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);

        
        bloques= new Items(1);
        bloques.Seleccionar_Localizacion(1525, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(1540, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);     
                    
        bloques= new Items(8);
        bloques.Seleccionar_Localizacion(3106, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MUNDO_1, false, true);
                
        bloques= new Items(1);
        bloques.Seleccionar_Localizacion(2446, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(0);
        bloques.Seleccionar_Localizacion(2462, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        bloques= new Items(1);
        bloques.Seleccionar_Localizacion(2478, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Planta
        Planta planta= new Planta();
        planta.Modificar(1, 500, 160);
        planta.Seleccionar_Localizacion(500, 167);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        //Tubo 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(182,158 , 212, 201));
        obj.Seleccionar_Localizacion(493, 192);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Tomate
        Tomate tomate= new Tomate();
        tomate.Seleccionar_Localizacion(350, 240);
        vec_enemigos.add(tomate);
        Motor_Fisico.getInstance().addDynamicObject(tomate);
        //Goomba 1
        Goomba goomba= new Goomba();
        goomba.Seleccionar_Localizacion(480, 200);
        vec_enemigos.add(goomba);
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        Tortuga tortuga = new Tortuga();
        tortuga.Modificar(-1, 700,245);
        tortuga.Seleccionar_Localizacion(700, 245);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        //PlantaNIVEL 4
        planta= new Planta();
        planta.Modificar(1, 1350, 128);
        planta.Seleccionar_Localizacion(1350, 108);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        //Tubo 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(182,158 , 212, 201));
        obj.Seleccionar_Localizacion(1343, 140);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PlantaNIVEL 4
        planta= new Planta();
        planta.Modificar(1, 1650, 108);
        planta.Seleccionar_Localizacion(1650, 108);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        //Tubo 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(182,158 , 212, 201));
        obj.Seleccionar_Localizacion(1643, 140);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        tortuga = new Tortuga();
        tortuga.Modificar(-1, 1630,115);
        tortuga.Seleccionar_Localizacion(1630,115);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        tortuga = new Tortuga();
        tortuga.Modificar(-1, 1370,115);
        tortuga.Seleccionar_Localizacion(1370,115);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        
        //Piedra 2 despues de bajada
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(1000, 247);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        tortuga = new Tortuga();
        tortuga.Modificar(-1, 1254, 190);
        tortuga.Seleccionar_Localizacion(1254, 190);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        
        //Piedra 3 despues de bajada
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(2060, 247);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //Piedra arriba
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(2060, 227);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        tomate= new Tomate();
        tomate.Seleccionar_Localizacion(1748, 200);
        vec_enemigos.add(tomate);
        Motor_Fisico.getInstance().addDynamicObject(tomate);
        
        tomate= new Tomate();
        tomate.Seleccionar_Localizacion(1820, 225);
        vec_enemigos.add(tomate);
        Motor_Fisico.getInstance().addDynamicObject(tomate);
        
        //PARTE FINAL
        
        goomba= new Goomba();
        goomba.Seleccionar_Localizacion(2762, 230);
        vec_enemigos.add(goomba);
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        goomba= new Goomba();
        goomba.Seleccionar_Localizacion(2790, 230);
        vec_enemigos.add(goomba);
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        //2388, 268
        //Planta final
        planta= new Planta();
        planta.Modificar(1, 2388, 112);
        planta.Seleccionar_Localizacion(2388, 112);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        //Tubo 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(182,158 , 212, 201));
        obj.Seleccionar_Localizacion(2381, 225);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        
    }
    
    public void crearNivel2(){
        vec_Bloques.clear();
        vec_enemigos.clear();
        vec_objetos_fondo.clear();
        Motor_Fisico.getInstance().clearPhysicsEngine();
        
        mario.Modificar(1, 0, 200);
        mario.Seleccionar_Localizacion(100,240);
        Motor_Fisico.getInstance().addDynamicObject(mario);
        
        Tortuga tortuga = new Tortuga(true);
        tortuga.Modificar(-1, 550, 200);
        tortuga.Seleccionar_Localizacion(370, 234);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        tortuga = new Tortuga(true);
        tortuga.Modificar(-1, 550, 200);
        tortuga.Seleccionar_Localizacion(1026, 200);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        tortuga = new Tortuga(true);
        tortuga.Modificar(-1, 550, 200);
        tortuga.Seleccionar_Localizacion(956, 200);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        Goomba goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(300, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(246, 170);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(190, 38);
        goomba.changeDirx();
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);



        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(1300, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(1350, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);

        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(1450, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);

        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(1400, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(1500, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        Planta planta= new Planta();
        planta.Modificar(1, 100, 200);
        planta.Seleccionar_Localizacion(2000, 240);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        
        planta= new Planta();
        planta.Modificar(1, 100, 200);
        planta.Seleccionar_Localizacion(2200, 240);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        
        planta= new Planta();
        planta.Modificar(1, 100, 200);
        planta.Seleccionar_Localizacion(1800, 240);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        
        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(1940, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(1980, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(2130, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);
        
        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(1600, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);

        goomba = new Goomba();
        goomba.Modificar(-1, 306, 200);
        goomba.Seleccionar_Localizacion(2090, 240);
        vec_enemigos.add( goomba );
        Motor_Fisico.getInstance().addDynamicObject(goomba);

        tortuga = new Tortuga(true);
        tortuga.Modificar(-1, 306, 200);
        tortuga.Seleccionar_Localizacion(2400, 240);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject( tortuga );
        
        tortuga = new Tortuga(true);
        tortuga.Modificar(-1, 306, 200);
        tortuga.Seleccionar_Localizacion(2250, 240);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject( tortuga );
        
        tortuga = new Tortuga(true);
        tortuga.Modificar(-1, 306, 200);
        tortuga.Seleccionar_Localizacion(2350, 240);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject( tortuga );
        
        tortuga = new Tortuga(true);
        tortuga.Modificar(-1, 306, 200);
        tortuga.Seleccionar_Localizacion(2550, 240);
        vec_enemigos.add( tortuga );
        Motor_Fisico.getInstance().addDynamicObject( tortuga );

        planta= new Planta();
        planta.Modificar(1, 100, 200);
        planta.Seleccionar_Localizacion(1283, 188);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        
        //Imagen de Fondo
        Objetos_Inanimados obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.MUNDO_2).getImage(), new Rectangulo(0, 0, 3600, 300) );
        obj.Seleccionar_Localizacion(0,0);
        vec_objetos_fondo.add(obj);
        
        //Tubo 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(182,158 , 212, 201));
        obj.Seleccionar_Localizacion(1276, 220);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
         //Tubo 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(182,158 , 212, 201));
        obj.Seleccionar_Localizacion(1599, 220);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        planta= new Planta();
        planta.Modificar(1, 100, 200);
        planta.Seleccionar_Localizacion(1606, 188);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        
        //PISO NIVEL 1  borde
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(0,0 , 16, 47));
        obj.Seleccionar_Localizacion(-1, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(15, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //agua
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(194, 0, 275, 41));
        obj.Seleccionar_Localizacion(455, 280);
        vec_objetos_fondo.add(obj);
        
        //agua
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(194, 0, 275, 41));
        obj.Seleccionar_Localizacion(536, 280);
        vec_objetos_fondo.add(obj);
        
        //agua
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(194, 0, 275, 41));
        obj.Seleccionar_Localizacion(696, 280);
        vec_objetos_fondo.add(obj);
       
        //agua
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(194, 0, 275, 41));
        obj.Seleccionar_Localizacion(616, 280);
        vec_objetos_fondo.add(obj);
        
        //agua
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(194, 0, 275, 41));
        obj.Seleccionar_Localizacion(776, 280);
        vec_objetos_fondo.add(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(79, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(143, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(207, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(439, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(439, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(419, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(271, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(335, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(396, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  borde
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(99,0 , 114, 47));
        obj.Seleccionar_Localizacion(445, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //roca  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(1,48 , 25, 67));
        obj.Seleccionar_Localizacion(34, 240);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //columna 
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25, 95, 43, 195));
        obj.Seleccionar_Localizacion(0, 160);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //columna 
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25, 95, 43, 195));
        obj.Seleccionar_Localizacion(16, 160);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //monstruo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(32, 54, 64, 86));
        obj.Seleccionar_Localizacion(2, 129);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(109, 52, 156, 90));
        obj.Seleccionar_Localizacion(524, 220);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj); 
        
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(109, 52, 156, 90));
        obj.Seleccionar_Localizacion(627, 220);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(109, 52, 156, 90));
        obj.Seleccionar_Localizacion(730, 220);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(830, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(850, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(830, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  borde
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(0,0 , 16, 47));
        obj.Seleccionar_Localizacion(830, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(846, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(910, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(950, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  borde
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(99,0 , 114, 47));
        obj.Seleccionar_Localizacion(1010, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Plataforma
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(56, 91, 118, 111));
        obj.Seleccionar_Localizacion(1075, 220);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Plataforma
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(56, 91, 118, 111));
        obj.Seleccionar_Localizacion(1180, 220);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  borde
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(0,0 , 16, 47));
        obj.Seleccionar_Localizacion(1270, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1280, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1344, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1408, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1472, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1536, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1600, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1664, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1728, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1792, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1856, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1920, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(1984, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2048, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2112, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2176, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2240, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2304, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2368, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2432, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2496, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2560, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);


        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2560, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(2610, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1  borde
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(99,0 , 114, 47));
        obj.Seleccionar_Localizacion(2666, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //Bloque de monedas azul
        Items bloques= new Items(6);
        bloques.Seleccionar_Localizacion(240, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);

        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(255, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);

        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(225, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);

        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(210, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(195, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(180, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(165, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(150, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(270, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(180, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(7);
        bloques.Seleccionar_Localizacion(195, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(7);
        bloques.Seleccionar_Localizacion(210, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(7);
        bloques.Seleccionar_Localizacion(225, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(240, 120);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(2);
        bloques.Seleccionar_Localizacion(210, 55);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(195, 55);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(225, 55);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(1440, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(1455, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(2);
        bloques.Seleccionar_Localizacion(1470, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(1435, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(1420, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(6);
        bloques.Seleccionar_Localizacion(1405, 190);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //Bloque de monedas
        bloques= new Items(7);
        bloques.Seleccionar_Localizacion(1091, 150);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);

        //Bloque de monedas
        bloques= new Items(7);
        bloques.Seleccionar_Localizacion(1108, 150);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);

        //Bloque de monedas
        bloques= new Items(7);
        bloques.Seleccionar_Localizacion(1196, 150);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);

        //Bloque de monedas
        bloques= new Items(7);
        bloques.Seleccionar_Localizacion(1213, 150);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(1004, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(1004, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(984, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(2876-256, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(2876-256, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(2856-256, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(2896-256, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(2896-256, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(2896-256, 197);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(2916-256, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(2916-256, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(2916-256, 197);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(2916-256, 177);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3011-256, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3011-256, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3011-256, 197);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3011-256, 177);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3031-256, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3031-256, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3031-256, 197);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3051-256, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3051-256, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3071-256, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3011-256, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3075-256, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 13139
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3139-256, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 13203
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3203-256, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 13267
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3267-256, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 13331
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3331-256, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3395-256, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3459-256, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3459-192, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3459-128, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3459-128, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3340-384, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PISO NIVEL 1
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(25,0 , 88, 47));
        obj.Seleccionar_Localizacion(3459, 260);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3320-384, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);

        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3320-364, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3320-344, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3360-384, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3360-384, 197);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3380-384, 237);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3380-384, 217);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3380-384, 197);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3380-384, 177);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3380-364, 177);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3380-344, 177);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3380-324, 177);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3380-304, 177);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //objeto suelo
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168, 49, 188, 72));
        obj.Seleccionar_Localizacion(3380-284, 177);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
//Bloque de monedas
        bloques= new Items(8);
        bloques.Seleccionar_Localizacion(3040, 140);
        vec_Bloques.add(bloques);
        Motor_Fisico.getInstance().AnadirInanimado(bloques);
        
        Cargar_Sonidos.obtener_instancia().detener_pista(Cargar_Sonidos.MUNDO_1);
        Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MUNDO_2 , false, true);
        

    }
    
    
    public void crearEscenaCastillo(){
        vec_Bloques.clear();
        vec_enemigos.clear();
        vec_objetos_fondo.clear();
        Motor_Fisico.getInstance().clearPhysicsEngine();
        
        mario.Modificar(1, 0, 200);
        mario.Seleccionar_Localizacion(0,180);
        Motor_Fisico.getInstance().addDynamicObject(mario);
        
        //Arriba 1
        Objetos_Inanimados obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 0, 316, 116) );
        obj.Seleccionar_Localizacion(0,0);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //Piso1
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(318, 0, 634, 116) );
        obj.Seleccionar_Localizacion(0,184);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //Goomba
        Goomba gomba= new Goomba();
        gomba.Seleccionar_Localizacion(200, 150);
        vec_enemigos.add(gomba);
        Motor_Fisico.getInstance().addDynamicObject(gomba);
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(385, 196);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //goomba     
        gomba= new Goomba();
        gomba.Modificar(1, 345, 0);
        gomba.Seleccionar_Localizacion(345,0);
        vec_enemigos.add(gomba);
        Motor_Fisico.getInstance().addDynamicObject(gomba);
        //Lava1
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 126, 81, 181) );
        obj.Seleccionar_Localizacion(313,245);
        vec_objetos_fondo.add(obj);
        //Arriba2
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(296, 204, 590, 283) );
        obj.Seleccionar_Localizacion(385,0);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //Piso2
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 204, 296, 283) );
        obj.Seleccionar_Localizacion(385,221);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(660, 196);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //Tortuga
        Tortuga tortuga= new Tortuga();
        tortuga.Seleccionar_Localizacion(640, 210);
        vec_enemigos.add(tortuga);
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        
        //Lava2
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(672,244);
        vec_objetos_fondo.add(obj);
        //Piso3
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0 , 288, 174, 367) );
        obj.Seleccionar_Localizacion(718,221);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(720, 196);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //goomba
        gomba= new Goomba();
        gomba.Seleccionar_Localizacion(795, 180);
        vec_enemigos.add(gomba);
        Motor_Fisico.getInstance().addDynamicObject(gomba);
        //Arriba 3
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(176, 288, 348, 316) );
        obj.Seleccionar_Localizacion(718,0);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(870, 196);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Lava3
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(883,244);
        vec_objetos_fondo.add(obj);
        //Piso 4
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 374, 339, 453) );
        obj.Seleccionar_Localizacion(932,221);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(925, 196);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Tortuga
        tortuga= new Tortuga();
        tortuga.Seleccionar_Localizacion(1200, 160);
        vec_enemigos.add(tortuga);
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        //Arriba 4
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(339, 374, 664, 453) );
        obj.Seleccionar_Localizacion(932,0);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(1248, 196);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //Lava4
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(1264,244);
        vec_objetos_fondo.add(obj);
        //Piso5
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 461, 52, 539) );
        obj.Seleccionar_Localizacion(1313,222);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PLAnta
        Planta planta= new Planta();
        planta.Seleccionar_Localizacion(1333, 190);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        //Arriba 5
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 608, 596, 635) );
        obj.Seleccionar_Localizacion(1290,0);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 639, 539, 667) );
        obj.Seleccionar_Localizacion(1886,0);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //Lava5
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(1358,244);
        vec_objetos_fondo.add(obj);
        //Piso6
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 461, 52, 539) );
        obj.Seleccionar_Localizacion(1407,222);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //PLAnta
        planta= new Planta();
        planta.Seleccionar_Localizacion(1427, 190);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        //Lava6
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(1451,244);
        vec_objetos_fondo.add(obj);
        //Piso7
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 461, 52, 539) );
        obj.Seleccionar_Localizacion(1500,222);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //PLAnta
        planta= new Planta();
        planta.Seleccionar_Localizacion(1520, 100);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        
        //Lava7
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(1543,244);
        vec_objetos_fondo.add(obj);
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(1591, 200);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        gomba= new Goomba();
        gomba.Seleccionar_Localizacion(1630, 180);
        vec_enemigos.add(gomba);
        Motor_Fisico.getInstance().addDynamicObject(gomba);
        //Piso8
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(57, 461, 186, 539) );
        obj.Seleccionar_Localizacion(1592,220);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(1701, 200);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        
        //Lava8
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(1713,244);
        vec_objetos_fondo.add(obj);
        //Piso 9
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(188, 461, 226, 539) );
        obj.Seleccionar_Localizacion(1762,222);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Lava 9
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(1793,244);
        vec_objetos_fondo.add(obj);
        //Piso10
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(236, 461, 273, 570) );
        obj.Seleccionar_Localizacion(1842,191);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //PLAnta
        planta= new Planta();
        planta.Seleccionar_Localizacion(1852, 160);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        
        //Lava 10
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(1872,244);
        vec_objetos_fondo.add(obj);
        
         //Piso11
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(282, 459, 319, 599) );
        obj.Seleccionar_Localizacion(1921,160);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Lava 11
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(1951,244);
        vec_objetos_fondo.add(obj);
        
        //PLAnta
        planta= new Planta();
        planta.Seleccionar_Localizacion(2010, 160);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        //Piso 12
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(236, 461, 273, 570) );
        obj.Seleccionar_Localizacion(2000,191);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Lava 12
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(2030,244);
        vec_objetos_fondo.add(obj);
        
        //Piso 13
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(188, 461, 226, 539) );
        obj.Seleccionar_Localizacion(2079,222);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Lava13
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(2109,244);
        vec_objetos_fondo.add(obj);
        
        //Piso14
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 674, 339, 750) );
        obj.Seleccionar_Localizacion(2158,222);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Lava14
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(84, 126, 143, 181) );
        obj.Seleccionar_Localizacion(2490,244);
        vec_objetos_fondo.add(obj);
        
        
        //Piso15
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(342, 674, 528, 766) );
        obj.Seleccionar_Localizacion(2540,207);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //Lava14
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(240, 128, 661, 181) );
        obj.Seleccionar_Localizacion(2717,246);
        vec_objetos_fondo.add(obj);
        
        //Puente
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(0, 772, 414, 787) );
        obj.Seleccionar_Localizacion(2721,207);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        
        //Piso16
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASTILLO).getImage(), new Rectangulo(529, 674, 728, 767) );
        obj.Seleccionar_Localizacion(3130,207);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //BLOQUES----------------------------<<<<<<<<>>>>>>>>>
        
        //PRIMEA LINEA DE BLOQUES-MONEDAS
        Items itens= new Items(4);
        itens.Seleccionar_Localizacion(443, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(459, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(475, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(491, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(507, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(523, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(539, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(555, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(571, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(587, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(603, 158);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        //SEGUNDA IKERA DE BLOQUES
        itens= new Items(1);
        itens.Seleccionar_Localizacion(769, 160);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(785, 160);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(2);
        itens.Seleccionar_Localizacion(801, 160);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(817, 160);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
         itens= new Items(1);
        itens.Seleccionar_Localizacion(833, 160);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        //TERCERA ILERA BLOQUES ABAJO
        itens= new Items(4);
        itens.Seleccionar_Localizacion(967, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(983, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        //PLAnta
        planta= new Planta();
        planta.Seleccionar_Localizacion(983, 100);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(999, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1015, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1031, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1047, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1063, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1079, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1095, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1111, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1127, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
                       
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1143, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1159, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1175, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
                
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1191, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        //PLAnta
        planta= new Planta();
        planta.Seleccionar_Localizacion(1191, 100);
        vec_enemigos.add(planta);
        Motor_Fisico.getInstance().addDynamicObject(planta);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1207, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        
        //CUARTA ILERA DE BLOQUES
        itens= new Items(1);
        itens.Seleccionar_Localizacion(1619, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1635, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(1651, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(1667, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
         itens= new Items(1);
        itens.Seleccionar_Localizacion(1683, 150);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        //Hongo 2
        itens= new Items(2);
        itens.Seleccionar_Localizacion(1932, 109);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        //ILERA DE 16 BLOQUES, quinta ILERA DE BLOQUES
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2200, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(2199, 150);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        tortuga= new Tortuga();
        tortuga.Seleccionar_Localizacion(2300, 140);
        vec_enemigos.add(tortuga);
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        tortuga= new Tortuga();
        tortuga.Seleccionar_Localizacion(2400, 140);
        vec_enemigos.add(tortuga);
        Motor_Fisico.getInstance().addDynamicObject(tortuga);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2216, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2232, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2248, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2264, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2280, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2296, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2312, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2328, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2344, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2360, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2376, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2392, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2408, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2424, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2440, 170);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        //piedra
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(168,49 , 188, 72));
        obj.Seleccionar_Localizacion(2440, 150);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj);
        
        //ILERA BLOQUE_MONEDAS ILERA 4 ARRIBA
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2248, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(2264, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2280, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(2296, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(9);
        itens.Seleccionar_Localizacion(2312, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens);
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(2328, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2344, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(2360, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2376, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(1);
        itens.Seleccionar_Localizacion(2392, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        itens= new Items(4);
        itens.Seleccionar_Localizacion(2408, 80);
        vec_Bloques.add(itens);
        Motor_Fisico.getInstance().AnadirInanimado(itens); 
        
        Bowser nuevo = new Bowser();
        nuevo.Modificar(1, 2900, 165);
        nuevo.Seleccionar_Localizacion(2900, 165);
        vec_enemigos.add(nuevo);
        Motor_Fisico.getInstance().addDynamicObject(nuevo);
        Cargar_Sonidos.obtener_instancia().detener_pista(Cargar_Sonidos.MUNDO_2);
        Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.CASTILLO, false, true);

        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Motor_Fisico.getInstance().mouse(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}

