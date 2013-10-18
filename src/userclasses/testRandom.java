/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author LANREWAJU
 */
public class testRandom {
    
    public static void main(String[]args){
        ArrayList<Integer> arrays = new ArrayList<Integer>();
        for(int i=0;i<10; i++){
        arrays.add(i);
    }
        System.out.println(arrays);
        Collections.shuffle(arrays);
        System.out.println(arrays);
    }
    
}
