package com.mtechcomm.portinggame;

import com.codename1.media.Media;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import userclasses.StateMachine;

public class Portinggame {

    private Form current;
    Media m;

    public void init(Object context) {
    }

    public void start() {
        if (current != null) {
             System.out.println("The current screen is " + current.getName());
            
            
            current.show();
            return;
        }
        new StateMachine("/theme");
    }

    public void stop() {
        //current = Display.getInstance().getCurrent();
       
//        if (current instanceof Dialog) {
//            //current.setTransitionOutAnimator(CommonTransitions.createEmpty());
//            //if (!"DisplayAll".equals(current.getName()) || (!"MoneyBagForm".equals(current.getName()))) {
//                ((Dialog) current).dispose();
//               // Dialog.show("Oh dear!!!", "you have to begin again", "OK", null);
//               //start();
//                //current = null;
//            //}
//        }

       
         current = Display.getInstance().getCurrent();

    }

    public void destroy() {
    }
//    public static void main(String [] a){
////        Portinggame p = new Portinggame();
////        p.start();
//  
//    }
}
