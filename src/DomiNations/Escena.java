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
        setPreferredSize(new Dimension(767,592));
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
        /*if( mario.dirx==1){
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
        
        
        }else if( mario.getX()>0 && cont<=3000*/
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
                crearNivel1();
            if(escena_actual==3)
                crearNivel1();
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
             crearNivel1();
         if(escena_actual==3)
             crearNivel1();
         Motor_Juego.cont =0;cont=0;
         ter_esc=false;
        }
    }
    
    public void moveScreen(float dx){
        for(int i=0;i<vec_objetos_fondo.size();i++){
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
        Objetos_Inanimados obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.FONDO_ALDEA).getImage(), new Rectangulo(0, 0, 767, 592) );
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

