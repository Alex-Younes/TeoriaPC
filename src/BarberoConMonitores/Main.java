package BarberoConMonitores;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Faustino
 */
public class Main {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static void main(String []args) throws InterruptedException{
        Barberia sillon= new Barberia(3); //3= cantidad de sillas.
        Thread[]hilos= new Thread[20];
        String negro= ANSI_BLACK;
        hilos[0]= new Thread(new Barbero(negro, sillon,15),"Barbero");
        for(int i=1; i<=5;i++){
        String color=ANSI_BLACK;
            switch(i){
                case 1:
                    color= ANSI_RED;
                    break;
                case 2:
                    color= ANSI_BLUE;
                    break;
                case 3:
                    color= ANSI_CYAN;
                    break;
                case 4:
                    color= ANSI_PURPLE;
                    break;
                case 5:
                    color= ANSI_GREEN;
                    break;          
            }
        hilos[i]= new Thread(new Cliente(color,sillon),"Cliente"+i);
    }
        
        for(int i=0; i<=5;i++){
            if(i==0)
            hilos[i].start();
            else{
                
                //Thread.sleep(200);
                hilos[i].start();
            }
        }
       
       
}
}