/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto_inteligenciai;


/**
 *
 * @author
 */
public class Movimiento {
    //Algoritmo menor costo
    
    
    
    //Variables locales para establecer el peligro y la ocupacion.
    int peligro2[][];//para establecer las coordenadas de peligro
    int ocupado2[][];//para evitar que se sobrepongan imagenes
    
    //posicion del queso y del raton locales
    int pratonl[][];
    int pquesol[][];
    int pr[][];//variable que almacena temporalmente la direccion de raton
    int pra[][];//variable que simula el movimiento del raton en su recorrido
    int GB; //variable gran bandera para ver si se encontro el queso
    int cA2;
    
    Movimiento(){  
        //Matriz que registra si una posicion se encuentra ocupada 
        //para evitar que las imagenes se sobrepongan
       //establecer cuadros de peligro              
        peligro2=new int[8][8];
        pquesol=new int[1][2];
        pratonl=new int[1][2];
        pr=new int[1][2];
        pra=new int[1][2];
        GB=0;
        cA2=0;
        //pq=new int[1][2];
        System.out.println("antes de ocupadoCero");
      //  ocupadoCero();
        System.out.println("despues de ocupadoCero");
        peligroCero();
        
       // int m;
    }
    
    
    //permite acceder a la fucnion calcula camino desde otra clase sin los parametros.
    void busqueda(){
        while(GB==0){
            if(llegue(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1])==1){
            GB=10;
            }else{
            busca(pratonl[0][0],pratonl[0][1],pquesol[0][0],pquesol[0][1]);
            }
        }
        
    /*    int w;
    w=calculaCamino(pratonl[0][0],pratonl[0][1],pquesol[0][0],pquesol[0][1]);
    System.out.println(w);
    * */
    }
    
    //funcion que nos permite comparar cuales caminos estan disponibles
    int calculaCamino(int x,int y,int x1, int y1){
        int arriba,abajo,derecha,izquierda;
        ///////**************//////////
        arriba=esPosibleX(x,y,x-1);//Revisar si se puede mover hacia arriba
        if(arriba==1){
            System.out.println("arriba es posible");
            pra[0][0]=x;//pra[][]  es para manipuilar las direcciones del raton 
                        // sin afectar a pratonl[][]
            pra[0][1]=y;//
            GB=0;
            cA2=0;
            while(GB==0){
            busca(pratonl[0][0],pratonl[0][1],pquesol[0][0],pquesol[0][1]);
            }
        System.out.println("Por la arriba son: "+arriba);
        }else{System.out.println("arriba no es posible");
        }//fin if
        
        abajo=esPosibleX(x,y,x+1);//Revisar si se puede mover hacia abajo
        if(abajo==1){
            System.out.println("abajo es posible");
            pra[0][0]=x;
            pra[0][1]=y;
            GB=0;
            cA2=0;
            while(GB==0){
                 abajo=cuentaAbajo2(pratonl[0][0],pratonl[0][1],pquesol[0][0],pquesol[0][1]);
            }
        System.out.println("Por la abajo son: "+abajo);
        }else{System.out.println("abajo no es posible");
        }//fin if
        
        derecha=esPosibleY(x,y,x+1);//Revisar si se puede mover hacia derecha
        if(derecha==1){
            System.out.println("derecha es posible");
            pra[0][0]=x;
            pra[0][1]=y;
            GB=0;
            cA2=0;
            while(GB==0){
            derecha=cuentaDerecha2(pratonl[0][0],pratonl[0][1],pquesol[0][0],pquesol[0][1]);
            }
        System.out.println("Por la derecha son: "+derecha);
        }else{System.out.println("derecha no es posible");
        }//fin if
        
        izquierda=esPosibleY(x,y,x-1);//Revisar si se puede mover hacia izquierda
        if(izquierda==1){
            System.out.println("izquierda es posible");
            pra[0][0]=x;
            pra[0][1]=y;
            GB=0;
            cA2=0;
            while(GB==0){
            izquierda=cuentaIzquierda2(pratonl[0][0],pratonl[0][1],pquesol[0][0],pquesol[0][1]);
            }
        System.out.println("Por la izquierda son: "+izquierda);
        }else{System.out.println("izquierda no es posible");
        }//fin if
        return aDonde(x,y,x1,y1);
    }
    
    //INTELIGENCIA 4
    void busca(int x,int y,int x2,int y2){
        /*nos dice que camino seguir
         * para llegar al queso recibiendo 
         * como parametros la posicion actual.
         */
    int numero;
    int n2;
    int bandera=0;
    numero=aDonde(x,y,x2,y2);
    switch(numero){
        case 1://movimiento arriba
            System.out.println("Caso1");
            if(esPosibleX(pra[0][0],pra[0][1],pra[0][0]-1)==1){
             pra[0][0]--;
             System.out.println("movimiento arriba");
            }else{
            n2=aDondeHorizontal(pra[0][0],pra[0][1],x2,y2);
            while(bandera==0){
            switch(n2){
                case 4:
                    System.out.println("movimiento izquierda");
                    pra[0][1]--;
                    bandera=10;
                    break;
                case 2:
                    System.out.println("movimiento derecha");
                    pra[0][1]++;
                    bandera=10;
                    break;
                case 5:
                    GB=10;
                    System.out.println("Ya lo encontre");
                    bandera=10;
                    break;
            }//fin switch
            
            }
            
            
            }//fin else
            System.out.println("fin Caso1");
            break;
        case 2://movimiento derecha
            System.out.println("Caso2");
            if(esPosibleY(pra[0][0],pra[0][1],pra[0][1]+1)==1){
             pra[0][1]++;
             System.out.println("movimiento derecha");
            }else{
            n2=aDonde(pra[0][0],pra[0][1],x2,y2);
            while(bandera==0){
            if(esPosibleY(pra[0][0],pra[0][1],pra[0][1]+1)==1){
            bandera=10;
            }else{
            switch(n2){
                case 1:
                    System.out.println("movimiento arriba");
                    pra[0][0]--;
                    bandera=10;
                    break;
                case 2:
                    System.out.println("movimiento abajo");
                    pra[0][0]++;
                    bandera=10;
                    break;
                case 5:
                    GB=10;
                    System.out.println("Ya lo encontre");
                    bandera=10;
                    break;
            }//fin switch
            }
            }
            
            
            }//fin else
            System.out.println("fin Caso2");
            break;
            
        case 3://movimiento abajo
            System.out.println("Caso3");
            if(esPosibleX(pra[0][0],pra[0][1],pra[0][0]+1)==1){
             pra[0][0]++;
             System.out.println("movimiento abajo");
            }else{
            n2=aDondeHorizontal(pra[0][0],pra[0][1],x2,y2);
            while(bandera==0){
            if(esPosibleY(pra[0][0],pra[0][1],pra[0][0]+1)==1){
            bandera=10;
            }else{
            switch(n2){
                case 4:
                    System.out.println("movimiento izquierda");
                    pra[0][1]--;
                    bandera=10;
                    break;
                case 2:
                    System.out.println("movimiento derecha");
                    pra[0][1]++;
                    bandera=10;
                    break;
                case 5:
                    GB=10;
                    System.out.println("Ya lo encontre");
                    bandera=10;
                    break;
            }//fin switch
            }
            }
            
            
            }//fin else
            System.out.println("fin Caso3");
            break;
        case 4://movimiento izquierda
            System.out.println("Caso4");
            if(esPosibleX(pra[0][0],pra[0][1],pra[0][1]-1)==1){
             pra[0][0]--;
             System.out.println("movimiento izquuiera");
            }else{
            n2=aDondeHorizontal(pra[0][0],pra[0][1],x2,y2);
            while(bandera==0){
            if(esPosibleY(pra[0][0],pra[0][1],pra[0][1]-1)==1){
            bandera=10;
            }else{
            switch(n2){
                case 1:
                    System.out.println("movimiento arriba");
                    pra[0][0]--;
                    bandera=10;
                    break;
                case 3:
                    System.out.println("movimiento abajo");
                    pra[0][0]++;
                    bandera=10;
                    break;
                case 5:
                    GB=10;
                    System.out.println("Ya lo encontre");
                    bandera=10;
                    break;
            }//fin switch
            }
            }
            
            
            }//fin else
            System.out.println("fin Caso4");
            break;
        case 5://movimiento Encontrado
            GB=10;
            System.out.println("Ya lo encontre");
            bandera=10;
            break;
            
    }
    
    }
    
    
    //INTELIGENCIA 3 
    int cuentaArriba2(int x,int y,int x2,int y2){
    int contadorA2=0;
    pr[0][0]=x;
    pr[0][1]=y;
    int donde;
    if(llegue(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1])==1){//revisa si se encontro el queso
                  //  encontrado=10;
                    GB=10;
                    System.out.println("Ya llegue y estoy comiendo queso");
                }else{
        if(esPosibleX(pra[0][0],pra[0][1],pra[0][0]-1)==1){
           pra[0][0]--;
           pr[0][0]--;
           contadorA2++;
           System.out.println("avanzo un paso");
           }else{
            System.out.println("tengo que moverme horizontalmente");
                   donde=aDondeHorizontal(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1]);
                   switch(donde){
                       case 2://movimiento a derecha
                           cA2=cA2+cuentaDerecha2(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       case 4://movimiento a izquierda
                           cA2=cA2+cuentaIzquierda2(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       case 5:
                           GB=10;
                           System.out.println("Ya lo encontre");
                           break;
                           
                       default:
                           cA2=100;
                           System.out.println("No es posible llegar por arriba");
                           GB=10;
                         //  return contador;
                           break;
                   }//fin switch
            }
    }//fin else
    return cA2;
    }//fin cuentaArriba2
    
    int cuentaDerecha2(int x,int y,int x2,int y2){
    //int contadorA2=0;
    pr[0][0]=x;
    pr[0][1]=y;
    int donde=0;
    if(llegue(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1])==1){//revisa si se encontro el queso
                  //  encontrado=10;
                    GB=10;
                    System.out.println("Ya llegue y estoy comiendo queso");
                }else{
        if(esPosibleY(pra[0][0],pra[0][1],pra[0][1]+1)==1){
           pra[0][1]++;
           pr[0][1]++;
           cA2++;
           System.out.println("avanzo un paso");
           }else{
            System.out.println("tengo que moverme verticalmente");
                   donde=aDonde(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1]);
                   switch(donde){
                       case 1://movimiento Arriba
                           cA2=cA2+cuentaArriba2(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       case 3://movimiento Abajo
                           cA2=cA2+cuentaAbajo2(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       case 5:
                           GB=10;
                           System.out.println("Ya lo encontre");
                           break;
                       
                       default:
                           cA2=100;
                           GB=10;
                           System.out.println("No es posible llegar por arriba");
                         //  return contador;
                           break;
                   }//fin switch
            }
    }//fin else
    return cA2;
    }//fin cuentaDerecha2
    
    int cuentaIzquierda2(int x,int y,int x2,int y2){
    //int contadorA2=0;
    pr[0][0]=x;
    pr[0][1]=y;
    int donde=0;
    if(llegue(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1])==1){//revisa si se encontro el queso
                  //  encontrado=10;
                    GB=10;
                    System.out.println("Ya llegue y estoy comiendo queso");
                }else{
        if(esPosibleY(pra[0][0],pra[0][1],pra[0][1]-1)==1){
           pra[0][1]--;
           pr[0][1]--;
           cA2++;
           System.out.println("avanzo un paso");
           }else{
            System.out.println("tengo que moverme verticalmente");
                   donde=aDonde(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1]);
                   switch(donde){
                       case 1://movimiento arriba
                           cA2=cA2+cuentaArriba2(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       case 3://movimiento abajo
                           cA2=cA2+cuentaAbajo2(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       case 5:
                           GB=10;
                           System.out.println("Ya lo encontre");
                           break;
                       
                       default:
                           cA2=100;
                           GB=10;
                           System.out.println("No es posible llegar por izquierda");
                         //  return contador;
                           break;
                   }//fin switch
            }
    }//fin else
    return cA2;
    }//fin cuentaIzquierda2

    int cuentaAbajo2(int x,int y,int x2,int y2){
    //int contadorA2=0;
    pr[0][0]=x;
    pr[0][1]=y;
    int donde=0;
    if(llegue(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1])==1){//revisa si se encontro el queso
                  //  encontrado=10;
                    GB=10;
                    System.out.println("Ya llegue y estoy comiendo queso");
                }else{
        if(esPosibleX(pra[0][0],pra[0][1],pra[0][0]+1)==1){
           pra[0][0]++;
           pr[0][0]++;
           cA2++;
           System.out.println("avanzo un paso");
           }else{
            System.out.println("tengo que moverme Horizontalmente");
                   donde=aDondeHorizontal(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1]);
                   switch(donde){
                       case 2://movimiento a derecha
                           cA2=cA2+cuentaDerecha2(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       case 4://movimiento a izquierda
                           cA2=cA2+cuentaIzquierda2(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       case 5:
                           GB=10;
                           System.out.println("Ya lo encontre");
                           break;
                       
                       default:
                           cA2=100;
                           GB=10;
                           System.out.println("No es posible llegar por izquierda");
                         //  return contador;
                           break;
                   }//fin switch
            }
    }//fin else
    return cA2;
    }
    
    
    
    //// //INTELIGENCIA 2/////////////////////////////////////////////////////////////////
    int cuentaArriba(int x,int y,int x2,int y2){
        System.out.println("Se esta contando por el camino de arriba");
            int contadorA=0;   //contador de numero de pasos a seguir.
            int encontrado=0; //variable bandera para comprobar si se encontro el queso.
            int donde=0;      //variable para ver la direccion.
            int bandera=0;
            pr[0][0]=x;
            pr[0][1]=y;
            while(GB==0){
                System.out.println("avanzo un paso");
                if(llegue(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1])==1){//revisa si se encontro el queso
                    encontrado=10;
                    GB=10;
                    System.out.println("Ya llegue y estoy comiendo queso");
                }else{
                    while(bandera==0){
                if(pra[0][0]==pquesol[0][0]){
                   System.out.println("Estoy al mismo nivel");
                   
                bandera=10;
                }else{
                if(esPosibleX(pr[0][0],pr[0][1],pr[0][0]-1)==1){
                contadorA++;
                pr[0][0]--;
                pra[0][0]--;
                }else{
                    System.out.println("tengo que moverme horizontalmente");
                   donde=aDondeHorizontal(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1]);
                   switch(donde){
                       case 2://movimiento a derecha
                           contadorA=contadorA+cuentaDerecha(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       case 4://movimiento a izquierda
                           contadorA=contadorA+cuentaIzquierda(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       default:
                           contadorA=100;
                           System.out.println("No es posible llegar hacia arriba");
                         //  return contador;
                           break;
                   }//fin switch
                }//fin else
                }
                }
                    
                    /*
                while(pr[0][0] != pquesol[0][0]){//mientras que no se este al nivel del queso se sigue con el recorrido
                if(esPosibleX(pr[0][0],pr[0][1],pr[0][0]-1)==1){
                contadorA++;
                pr[0][0]--;
                }else{
                    System.out.println("tengo que moverme horizontalmente");
                   donde=aDondeHorizontal(pr[0][0],pr[0][1],pquesol[0][0],pquesol[0][1]);
                   switch(donde){
                       case 2://movimiento a derecha
                           contadorA=contadorA+cuentaDerecha(pr[0][0],pr[0][1],x2,y2);
                         //  return contador;
                       case 4://movimiento a izquierda
                           contadorA=contadorA+cuentaIzquierda(pr[0][0],pr[0][1],x2,y2);
                         //  return contador;
                       default:
                           contadorA=100;
                           System.out.println("No es posible llegar hacia arriba");
                         //  return contador;
                   }//fin switch
                }//fin else
                }//fin while
                */
                }//fin else
            }//fin while
            
            
           return contadorA;
    }
    
    int cuentaDerecha(int x,int y,int x2,int y2){
        System.out.println("Se esta contando por el camino de Derecha");
            int contadorD=0;   //contador de numero de pasos a seguir.
            int encontrado=0; //variable bandera para comprobar si se encontro el queso.
            int donde=0;      //variable para ver la direccion.
            int bandera=0;
            pr[0][0]=x;
            pr[0][1]=y;
            while(GB==0){
                System.out.println("avanzo un paso");
                if(llegue(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1])==1){//revisa si se encontro el queso
                    encontrado=10;
                    GB=10;
                    System.out.println("Ya llegue y estoy comiendo queso");
                }else{
                    while(bandera==0){
                if(pra[0][1]==pquesol[0][1]){
                   System.out.println("Estoy al mismo nivel");

                bandera=10;
                }else{
                if(esPosibleX(pr[0][1],pr[0][1],pr[0][1]+1)==1){
                contadorD++;
                pr[0][1]++;
                pra[0][1]++;
                }else{
                    System.out.println("tengo que moverme Vericalmente");
                   donde=aDonde(pra[0][0],pra[0][1],pquesol[0][0],pquesol[0][1]);
                   switch(donde){
                       case 1://movimiento a Arriba
                           contadorD=contadorD+cuentaArriba(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       case 2://movimiento a abajo
                           contadorD=contadorD+cuentaAbajo(pra[0][0],pra[0][1],x2,y2);
                         //  return contador;
                           break;
                       default:
                           contadorD=100;
                           System.out.println("No es posible llegar por derecha");
                         //  return contador;
                           break;
                   }//fin switch
                }//fin else
                }
                }
                    
                    /*
                while(pr[0][0] != pquesol[0][0]){//mientras que no se este al nivel del queso se sigue con el recorrido
                if(esPosibleX(pr[0][0],pr[0][1],pr[0][0]-1)==1){
                contadorA++;
                pr[0][0]--;
                }else{
                    System.out.println("tengo que moverme horizontalmente");
                   donde=aDondeHorizontal(pr[0][0],pr[0][1],pquesol[0][0],pquesol[0][1]);
                   switch(donde){
                       case 2://movimiento a derecha
                           contadorA=contadorA+cuentaDerecha(pr[0][0],pr[0][1],x2,y2);
                         //  return contador;
                       case 4://movimiento a izquierda
                           contadorA=contadorA+cuentaIzquierda(pr[0][0],pr[0][1],x2,y2);
                         //  return contador;
                       default:
                           contadorA=100;
                           System.out.println("No es posible llegar hacia arriba");
                         //  return contador;
                   }//fin switch
                }//fin else
                }//fin while
                */
                }//fin else
            }//fin while
            
            
           return contadorD;
    }
    
    int cuentaIzquierda(int x,int y,int x2,int y2){
System.out.println("Se esta contando por el camino de la Izquierda");
            int contadorI=0;   //contador de numero de pasos a seguir.
            int encontrado=0; //variable bandera para comprobar si se encontro el queso.
            int donde=0;      //variable para ver la direccion.
            pr[0][0]=x;
            pr[0][1]=y;
            while(encontrado==0){
                if(llegue(pr[0][0],pr[0][1],pquesol[0][0],pquesol[0][1])==1){//revisa si se encontro el queso
                    encontrado=10;
                    System.out.println("Ya llegue y estoy comiendo queso");
                }else{
                while(pr[0][1] != pquesol[0][1]){//mientras que no se este al nivel del queso se sigue con el recorrido
                if(esPosibleY(pr[0][0],pr[0][1],pr[0][1]+1)==1){
                contadorI++;
                pr[0][1]--;//movimiento a la izquierda
                }else{
                    System.out.println("tengo que moverme Verticalmente");
                   donde=aDonde(pr[0][0],pr[0][1],pquesol[0][0],pquesol[0][1]);
                   switch(donde){
                       case 1://movimiento arriba
                           contadorI=contadorI+cuentaArriba(pr[0][0],pr[0][1],x2,y2);
                          // return contador;
                       case 3://movimiento abajo
                           contadorI=contadorI+cuentaAbajo(pr[0][0],pr[0][1],x2,y2);
                          // return contador;
                       default:
                           contadorI=100;
                           System.out.println("No es posible llegar por la izquierda");
                         //  return contador;
                   }//fin switch
                }//fin else
                }//fin while
                }//fin else
            }//fin while           
           return contadorI; 
    }
    
    int cuentaAbajo(int x,int y,int x2,int y2){
System.out.println("Se esta contando por el camino de abajo");
            int contadorAb=0;   //contador de numero de pasos a seguir.
            int encontrado=0; //variable bandera para comprobar si se encontro el queso.
            int donde=0;      //variable para ver la direccion.
            pr[0][0]=x;
            pr[0][1]=y;
            while(encontrado==0){
                System.out.println("avanzo un paso");
                if(llegue(pr[0][0],pr[0][1],pquesol[0][0],pquesol[0][1])==1){//revisa si se encontro el queso
                    encontrado=10;
                    System.out.println("Ya llegue y estoy comiendo queso");
                }else{
                while(pr[0][0] != pquesol[0][0]){//mientras que no se este al nivel del queso se sigue con el recorrido
                if(esPosibleX(pr[0][0],pr[0][1],pr[0][0]+1)==1){
                contadorAb++;
                pr[0][0]++;
                }else{
                    System.out.println("tengo que moverme horizontalmente");
                   donde=aDondeHorizontal(pr[0][0],pr[0][1],pquesol[0][0],pquesol[0][1]);
                   switch(donde){
                       case 2://movimiento a derecha
                           contadorAb=contadorAb+cuentaDerecha(pr[0][0],pr[0][1],x2,y2);
                         //  return contador;
                       case 4://movimiento a izquierda
                           contadorAb=contadorAb+cuentaIzquierda(pr[0][0],pr[0][1],x2,y2);
                         //  return contador;
                       default:
                           contadorAb=100;
                           System.out.println("No es posible llegar por abajo");
                         //  return contador;
                   }//fin switch
                }//fin else
                }//fin while
                }//fin else
            }//fin while
            
            
           return contadorAb;
    }
    
    //////////////////////////////////////////////////////////////////////
    
    //determina si es posible que avanzar verticalmente a otra casilla
    int esPosibleX(int x,int y, int x2){
        if(x2<0 || x2>7){
        return 0;
        }else{
        if(peligro2[x2][y]==1){
        return 0;
        }else{
        return 1;//si se puede regresa 1
        }
        }
    }//fin esPosible
    
   //determina si es posible que avanzar Horizontalmente a otra casilla
    int esPosibleY(int x,int y, int y2){
        if(y2<0 || y2>7){
        return 0;
        }else{
        if(peligro2[x][y2]==1){
        return 0;
        }else{
        return 1;//si se puede regresa 1
        }
        }
    }//fin esPosible
    
    
    int moverse(){
    return 0;
    }
    
    
    
    //Funcion que dice hacia que direccion se debe de mover el raton
    int aDonde(int x,int y,int x1,int y1){
       
    int m;
      //revision si la posicion del queso es la misma que la del raton          
       if(llegue(x,y,x1,y1)==1){
       //System.out.println("llegue al queso");
      // retrazo.stop();
       GB=10;
       return 5;
       }else{
       if(x1==x){//movimiento horizontal en la misma fila       
    //System.out.println("");
       m=moverseY(y,y1);//si 0 a la izquierda 1 a la derecha 
       
       if(m==0){
       System.out.println("el raton se tiene que mover hacia la Izquierda");
       return 4;
       }else{
       System.out.println("el raton se tiene que mover hacia la Derecha");
       return 2;
       }
    }else{
    if(x1<x){//movimiento hacia arriba
    System.out.println("el raton se tiene que mover hacia Arriba");
    return 1;
    }else{//movimiento hacia abajo
    System.out.println("el raton se tiene que mover hacia Abajo");
    return 3;
    }
    }//fin arriba y abajo
       } //fin if movimiento horizontal        
   }//fin aDonde
    
    
    
    //Funcion que dice si se ha llegado al queso
    int llegue(int a,int b,int c,int d){
    if(a==c && b==d){
    return 1;
    }else{return 0;}
    }
    
    //Funcion que dice hacia que direccion se debe de mover el raton
    int aDondeHorizontal(int x,int y,int x1,int y1){
       System.out.println("Entro aDondeHorizontal");
    int m;
      //revision si la posicion del queso es la misma que la del raton          
       if(llegue(x,y,x1,y1)==1){
           GB=10;
       return 5;
       }else{
       m=moverseY(y,y1);//0 a la izquierda 1 a la derecha 
       
       if(m==0){
       System.out.println("el raton se tiene que mover hacia la Izquierda");
       return 4;
       }else{
       System.out.println("el raton se tiene que mover hacia la Derecha");
       return 2;
      
    }//fin arriba y abajo  
       } //fin if movimiento horizontal        
   }//fin aDondeHorizontal
    
    
    int moverseY(int a,int b){    
        if(b<a){
            return 0;
            }else{
            return 1;
        }//fin else2
    //fin else
    }//fin de la funcion moverY
    
    
    
    void peligroTrampa2(int a,int b){
    peligro2[a][b]=1;
    }

    void peligroGato2(int c,int d){
        //c=filas
        //d=columnas
        
        switch(c){
            case 0://prinera fila
                switch(d){
                    case 0://priner columna
                        //p1
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c][d+1]=1;
                        peligro2[c+1][d]=1;
                        peligro2[c+1][d+1]=1;
                        break;
                    case 7://segunda columna
                        //p2
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c][d-1]=1;
                        peligro2[c+1][d-1]=1;
                        peligro2[c+1][d]=1;
                        break;
                    default://intermedias
                        //p5
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c][d-1]=1;
                        peligro2[c][d+1]=1;
                        peligro2[c+1][d-1]=1;
                        peligro2[c+1][d]=1;
                        peligro2[c+1][d+1]=1;
                        break;
                }//fin switch d
                break;
            case 7://ultima fila
                switch(d){
                    case 0://primer columna
                        //p4
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d]=1;
                        peligro2[c-1][d+1]=1;
                        peligro2[c][d+1]=1;
                        break;
                    case 7://ultima columna
                        //p3
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d-1]=1;
                        peligro2[c-1][d]=1;
                        peligro2[c][d-1]=1;
                        break;
                    default://intermedias
                        //p7
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d-1]=1;
                        peligro2[c-1][d]=1;
                        peligro2[c-1][d+1]=1;
                        peligro2[c][d-1]=1;
                        peligro2[c][d+1]=1;
                        break;
                }//fin switch d
                break;
            default:
                
                switch(d){
                    //p8
                    case 0:
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d]=1;
                        peligro2[c-1][d+1]=1;
                        peligro2[c][d+1]=1;
                        peligro2[c+1][d]=1;
                        peligro2[c+1][d+1]=1;
                        break;
                        
                    case 7:
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d-1]=1;
                        peligro2[c-1][d]=1;
                        peligro2[c][d-1]=1;
                        peligro2[c+1][d-1]=1;
                        peligro2[c+1][d]=1;
                        break;
                        
                    default:
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d-1]=1;
                        peligro2[c-1][d]=1;
                        peligro2[c-1][d+1]=1;
                        peligro2[c][d-1]=1;
                        peligro2[c][d+1]=1;
                        peligro2[c+1][d-1]=1;
                        peligro2[c+1][d]=1;
                        peligro2[c+1][d+1]=1;
                        break;
                }
                
                break;
        }//fin switch c
    }
    
    void ocupadoCero(){
 //Establece las casillas de ocupado en 0 o vacias
        for(int i=0;i<8;i++){
        for(int j=0;j<8;j++){
					//marcar con cero todas las posiciones
					ocupado2[i][j]=0;
				}
        }
			
}
    
    void peligroCero(){
//Establece las casillas de peligro en 0 o vacias
        for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				{
					//marcar con cero todas las posiciones
					peligro2[i][j]=0;
				}
}//fin peligroCero


    void iPeligro(){
//Imprime la matriz de arreglo peligro2
        for(int i=0;i<8;i++){
            System.out.println("");
        			for(int j=0;j<8;j++)
				{
					//marcar con cero todas las posiciones
                                                System.out.println(peligro2[i][j]+" ");
				}
        }
}



}//Fin de la clase
