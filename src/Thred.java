/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LANREWAJU
 */
public class Thred extends Thread{
    public void run(){
        System.out.println("My name is Omolara"); 
    }
    
    public static void main(String [] args){
        Thred ty = new Thred();
        ty.run();
    }
}
