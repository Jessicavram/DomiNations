/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

/**
 *
 * @author Tyson
 */
public class Matriz_Logica {
     char [][] matriz_logica={  {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
};

    
    public Matriz_Logica(){
       
                 }
    public void imprimir(){
        for (int x=0; x < matriz_logica.length; x++) {
            for (int y=0; y < matriz_logica[x].length; y++) {
                System.out.print (matriz_logica[x][y]+" ");
            }
            System.out.println(" ");
        }
    }
        /**Verifica la disponibilidad de espacios vacios para construir en el mapa
     *@param posx pos x de esquina superior izquierda 
     *@param posy pos y de esquina superior izquierda
     *@param ancho ancho
     *@param alto alto */
    public int verificar_disponibilidad(int posx,int posy,int ancho,int alto){
        int ocupado=0;
        //validacion para que los edificios no se salgan del alto
        if(posx+alto>18){
            ocupado=1;
            System.out.println("Edificio se sale de los bordes de alto");
        }
        //validacion para que los edificios no se salgan del ancho
        if(posy+ancho>22){
            ocupado=1;
            System.out.println("Edificio se sale de los bordes de ancho ");
        }
        if(ocupado==0){
            for (int x=posx; x < posx+alto; x++) {
                for (int y=posy; y < posy+ancho; y++) {
                
                    if(matriz_logica[x][y]=='1'){
                    //hay un obstaculo posicion ocupada para el edificio
                        ocupado=1;
                    }   
                }
            }
        }
        return ocupado;
    }    
     /**Coloca un edificio en el mapa logico 
     *@param posx pos x de esquina superior izquierda 
     *@param posy pos y de esquina superior izquierda
     *@param ancho ancho
     *@param alto alto */
    public void colocar_edificio(int posx,int posy,int ancho,int alto){
        int ocupado=0;
        
        for (int x=posx; x < posx+alto; x++) {
            for (int y=posy; y < posy+ancho; y++) {
                matriz_logica[x][y]='1';
            }
        }
       
    }    
}
