/**
 * Your application code goes here
 */
package userclasses;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import generated.StateMachineBase;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.mtechcomm.portinggame.Questions;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {

    String status, status1, status2;
    Media mm;
    Timer timer;
    int correct = 0;
    int next = 0;
    int previous = 0;
    int correctMB = 0;
    int answered = 0;
    int time = 60;
    int timeMB = 10;
    int cycle;
    int amount;
    //int failedAttempts = 0, leftChances;
    // String questionPicked;
    Questions aQuestion;
    String correctAnswer;
    int level;
    boolean errorRecieved;
    String question, opt1, opt2, opt3, corr_ans, id;
    Vector<String> idPicked;
    Vector<String> wrongAttempt;
    Vector<String> questionAnswered;
    Vector<Hashtable> quesVector, quesVector2 = new Vector<Hashtable>();
    Hashtable quesHash, quesHash2, mbq;
    UITimer ui;
    //Vector<Hashtable> temp;
    Image image1 = fetchResourceFile().getImage("airtel.png");
    Image image2 = fetchResourceFile().getImage("etisalat.png");
    Image image3 = fetchResourceFile().getImage("glo.png");
    Image imageL1 = fetchResourceFile().getImage("airtel1.png");
    Image imageL2 = fetchResourceFile().getImage("etisalat1.png");
    Image imageL3 = fetchResourceFile().getImage("glo1.png");
    Image right = fetchResourceFile().getImage("right.png");
    Image wrong = fetchResourceFile().getImage("wrong.png");
    Image saka = fetchResourceFile().getImage("mtnsaka4.png");
    Image instrButton = fetchResourceFile().getImage("buttonInstr.png");
    Image exitButton = fetchResourceFile().getImage("buttonExit.png");
    Image homeButton = fetchResourceFile().getImage("buttonHome.png");
    Image okButton = fetchResourceFile().getImage("buttonOk.png");
    Image passButton = fetchResourceFile().getImage("buttonPass.png");
    Image header = fetchResourceFile().getImage("header.png");
    // Image im = fetchResourceFile().getImage("mtnsaka2.png");
    private int counter;

    // Image splashImage = fetchResourceFile().getImage("splashscreen.png");
    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }

    /**
     * this method should be used to initialize variables instead of the
     * constructor/class scope to avoid race conditions
     */
    @Override
    protected void initVars(Resources res) {
        errorRecieved = false;
        cycle = 0;
        counter = 1;
    }

    @Override
    protected void beforeSplashScreen(Form f) {
        Display.getInstance().lockOrientation(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                showForm("StartPage", null);
            }
        }, 2000);

        Image img = fetchResourceFile().getImage("splashImage.png");
        findSplashLabel(f).setIcon(img.scaledWidth(Display.getInstance().getDisplayWidth() - 20));


    }

    @Override
    protected boolean allowBackTo(String formName) {
        /* if(formName=="DisplaySim"||formName=="questionForm"||formName=="StartPage"||formName=="SplashScreen"){
         return false;
         }*/
        return false;  // or (return true or return super.allowBackTo(formName)) to set to true ;    //To change body of generated methods, choose Tools | Templates.
    }

    public void playMusic(final String song) {
        final InputStream is = Display.getInstance().getResourceAsStream(this.getClass(), song);
        try {
            mm = MediaManager.createMedia(is, "audio/mp3", new Runnable() {
                public void run() {
                    playMusic(song);
                }
            });
            mm.setVolume(10);
            mm.play();
        } catch (IOException ex) {

            ex.printStackTrace();

        }
    }

    //@Override
    protected void onDisplayCongrats_ButtonAction(Component c, ActionEvent event) {
        Display.getInstance().exitApplication();

    }

    @Override
    protected void onStartPage_ExitButtonAction(Component c, ActionEvent event) {
        Display.getInstance().exitApplication();

    }

    @Override
    protected void onStartPage_StartbuttonAction(Component c, ActionEvent event) {
        Timer timer = new Timer();
        correct = 0;
        correctAnswer = null;
        cycle = 0;
        level = 1;
        counter = 1;
        answered = 0;
        time = 60;
        if (questionAnswered != null) {
            questionAnswered.clear();
        }
        if (wrongAttempt != null) {
            wrongAttempt.clear();
        }
        if (idPicked != null) {
            idPicked.clear();
        }
        level = 1;
        counter = 1;
        if (quesVector2 != null && !quesVector2.isEmpty()) {
            quesVector2.clear();
            quesVector2 = null;
        }
        if (quesVector != null && !quesVector.isEmpty()) {
            quesVector.clear();
        }
        System.out.println("the level used is " + level);
        connectDatabase(level);
        if (status1 != null && ("200".equals(status1))) {
            if ((quesVector != null && (!quesVector.isEmpty())) && (!errorRecieved)) {

                Collections.shuffle(quesVector);
                startButton();
                showForm("DisplayAll", null);
            } else {
                Dialog d = new Dialog("Ouch!!!");
                TextArea t = new TextArea("You might need to check your network connection");
                t.setUIID("TextArea");
                d.addComponent(t);
                d.setTimeout(3000);
                d.show();

            }
        } else if (status1 == null) {
            Dialog d = new Dialog("Ouch!!!");
            TextArea t = new TextArea("You might need to check your network connection");
            t.setUIID("TextArea");
            d.addComponent(t);
            d.setTimeout(3000);
            d.show();
        }




    }

    public void connectDatabase(int levels) {
        final ConnectionRequest conn;
        conn = new ConnectionRequest() {
//           @Override
//            protected void buildRequestBody(OutputStream os) throws IOException {
//               os.write(json.getBytes("UTF-8"));
//           }
            @Override
            protected void readHeaders(Object connection) throws IOException {
                status = getHeader(connection, "status"); // if the status is in the headerrd
                // super.readHeaders(connection); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected void readResponse(InputStream input) throws IOException {
                status1 = String.valueOf(getResponseCode());
                JSONParser jp = new JSONParser();
                try {
                    quesHash = jp.parse(new InputStreamReader(input));
                    quesVector = (Vector) quesHash.get("root");

                } catch (Exception e) {
                    errorRecieved = true;
                }

                // h = (Vector) h1.get("");
//                System.out.println(quesHash);
//                System.out.println(status1);
//                System.out.println(quesVector);

                //super.readResponse(input); //To change body of generated methods, choose Tools | Templates.
            }
        };
        final NetworkManager nm = NetworkManager.getInstance();
        Command c = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ((Dialog) Display.getInstance().getCurrent()).dispose();
                nm.killAndWait(conn);
            }
        };
        InfiniteProgress ip = new InfiniteProgress();
        //Dialog dlg = ip.showInifiniteBlocking();


        Dialog d = new Dialog();
        d.setDialogUIID("Label");
        d.setLayout(new BorderLayout());
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label("Loading");
        l.getStyle().getBgTransparency();
        cnt.addComponent(l);
        cnt.addComponent(ip);
        d.addComponent(BorderLayout.CENTER, cnt);
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createEmpty());
        d.showPacked(BorderLayout.CENTER, false);
        d.setBackCommand(c);


        String url = "http://107.20.244.125/MTNPortinggame/index.php?levels=" + (levels) + "&moneybag=0&cycles=0";
        //String url = "http://localhost/saka/index.php?limit=28";
        conn.setUrl(url); //gets the url u want to talk to
        conn.setContentType("application/json");
        conn.setPost(false);
        conn.setHttpMethod("GET"); //could be put if put was set true
        conn.setFailSilently(true);
        conn.setDuplicateSupported(true);
        conn.setDisposeOnCompletion(d);
        //final NetworkManager nm = NetworkManager.getInstance();
        nm.start();
        nm.addToQueueAndWait(conn);
    }

    @Override
    protected void beforeDisplayCongrats(Form f) {
        findDismissButton(f).setIcon(passButton.scaledWidth(Display.getInstance().getDisplayWidth() / 5));
        findOkButton(f).setIcon(okButton.scaledWidth(Display.getInstance().getDisplayWidth() / 5));
        if (quesVector2 != null) {
            quesVector2.clear();
        }
//        try {
//            fetchQuestion.start();
//        } catch (IllegalThreadStateException e) {
//            System.out.println("I am still alive Omolarathread");
//        }
//        if (fetchQuestion.isAlive()) {
//            System.out.println("I am still alive Omolara");
//        }

        findTextArea(f).setUIID("InstructionText");
        findTextArea(f).setEditable(false);
        if (cycle == 1) {
            findTextArea(f).setText("Hurray! You have successfully completed cycle-1. You have the option of either attempting the ‘money bag’  "
                    + "question for cycle-1 and win the N400 MTN RECHARGE CARD instantly or you skip it for cycle-2 which has N750 MTN RECHARGE CARD as the winning prize "
                    + "Please note that you will be charged N50 to play cycle-2");

        } else if (cycle == 2) {
            findTextArea(f).setText("Hurray! You have successfully completed cycle-2. You have the option of either attempting the ‘money bag’ question for cycle-2 and win the N750 MTN RECHARGE CARD "
                    + "instantly or you skip it for cycle-3 which has N1500 MTN RECHARGE CARD as the winning prize \n"
                    + "Please note that you will be charged N50 to play cycle-3");

        } else if (cycle == 3) {
            findTextArea(f).setText("Hurray! You have successfully completed cycle-3. You have the option of either attempting the ‘money bag’ question for cycle-3 and win the N1500 MTN RECHARGE CARD"
                    + " instantly or you skip it for cycle-4 which has N3000 MTN RECHARGE CARD as the winning prize.\n"
                    + "Please note that you will be charged N50 to play cycle-4");
        } else if (cycle == 4) {
            findTextArea(f).setText("Hurray! You have successfully completed cycle-4. You have the option of either attempting the ‘money bag’ question for cycle-4 and win the N3000 MTN RECHARGE CARD"
                    + " instantly or you skip it for cycle-5 which has N10000 MTN RECHARGE CARD as the winning prize.\n"
                    + " Please note that you will be charged N50 to play cycle-5");


        } else if (cycle == 5) {
            findTextArea(f).setText("Congratulations! You have successfully ported 20 SIM CARDS to MTN. You have won N10,000 worth of MTN "
                    + "Recharge card to call any network.");
            // findContainer1(f).removeComponent(findDismissButton(f));
            Button b = new Button("Exit");
            b.setUIID("ourButton");
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    Display.getInstance().exitApplication();
                }
            });
            findContainer1(f).replace(findDismissButton(f), b, null);
        }
        Display.getInstance().lockOrientation(true);
        //findLabel(f).setIcon(saka.scaledWidth(Display.getInstance().getDisplayWidth()));

    }

    @Override
    protected void onCreateStartPage() {
        if ((mm == null) || (!mm.isPlaying())) {
            playMusic("/soundtrack.mp3");
        }
    }

    public void startButton() {
        Random rand = new Random();
        int n = 0;

        Image images[] = new Image[3];
        images[0] = image1;
        images[1] = image2;
        images[2] = image3;


        Image largeImages[] = new Image[3];
        largeImages[0] = imageL1;
        largeImages[1] = imageL2;
        largeImages[2] = imageL3;
        for (int i = 0; i < quesVector.size(); i++) {
            n = rand.nextInt(3);
            Hashtable j = quesVector.elementAt(i);
            Image i1 = (Image) images[n];//("ID");
            Image i2 = (Image) largeImages[n];
            j.put("SmallImage", i1);
            j.put("LargeImage", i2);
        }


        //  fetchQuestion.start();
    }

    @Override
    protected void beforeDisplayAll(final Form f) {
        findContainer(f).getStyle().setBgImage(header);
        findContainer(f).getStyle().setBorder(null);
        findContainer(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        final Dialog dlg = new Dialog();

        final CheckBox chk = new CheckBox("Sound");
        chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (mm.isPlaying()) {
                    mm.pause();
                    //chk.setText("Enable");
                    if ((Display.getInstance().getCurrent()) instanceof Dialog) {
                        ((Dialog) Display.getInstance().getCurrent()).dispose();
                    }
                    // findCheckBox1(c).setSelected(false);
                    // mm.setVolume(0);
                } else {
                    mm.play();
                    //chk.setText("Disable");
                    if ((Display.getInstance().getCurrent()) instanceof Dialog) {
                        ((Dialog) Display.getInstance().getCurrent()).dispose();
                    }
                }
            }
        });
        dlg.addComponent(chk);

        f.addCommand(new Command("Sound") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                dlg.show();
            }
        });



        System.out.println("level = " + level);
        findLevelLabel(f).setText("Cycle " + (cycle + 1) + " Level " + level);
        // TitleArea ta = new
        findCountLabel(f).setText(correct + "/" + answered);
        findTimerLabel(findContainer(f)).setText(String.valueOf(time));
        ui = new UITimer(new Runnable() {
            
            public void run() {
              --time;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                findTimerLabel(findContainer(f)).setText(String.valueOf(time));
                //f.revalidate();
                if (time <= 0) {
                    ui.cancel();
                    System.out.println("Time up");
                    if (correct < 5) {
                        boolean condition = Dialog.show("Oops!!!", "Sorry your time is up. Do you want to try again? It will cost you N50.", "Play again", "Close");
                        if (condition != true) {
                            time = 60;
                            correct = 0;
                            answered = 0;
                            correctAnswer = null;
                            //vector.clear();
                            if (wrongAttempt != null) {
                                wrongAttempt.clear();
                            }
                            if (idPicked != null) {
                                idPicked.clear();
                            }
                            if (questionAnswered != null) {
                                questionAnswered.clear();
                            }
                            // saveQuestions();
                            Collections.shuffle(quesVector);
                            showForm("StartPage", null);

                        } else {
                            correct = 0;
                            answered = 0;

                            level = 1;
                            counter = 1;
                            correctAnswer = null;
                            if (wrongAttempt != null) {
                                wrongAttempt.clear();
                                wrongAttempt = null;
                            }
                            if (idPicked != null) {
                                idPicked.clear();
                                idPicked = null;
                            }
                            if (questionAnswered != null) {
                                questionAnswered.clear();
                                questionAnswered = null;
                            }

                            Container c = findContainer1(f);
                            FlowLayout fl = new FlowLayout();
                            fl.setValign(0);
                            fl.setAlign(4);
                            c.setLayout(fl);
                            c.setScrollableY(true);
                            c.removeAll();
                            Collections.shuffle(quesVector);
                            startButton();
                            for (int i = 0; i < quesVector.size(); i++) {
                                //int n = r.nextInt(27);

                                Hashtable hashtable = quesVector.elementAt(i);
                                c.addComponent(addImage((Image) hashtable.get("SmallImage"), (Image) hashtable.get("LargeImage"), hashtable.get("Question").toString(), i, hashtable.get("id").toString(), hashtable.get("option_1").toString(), hashtable.get("option_2").toString(), hashtable.get("option_3").toString(), hashtable.get("correct_option").toString(), f));
                            }

                            time = 60;
                            showForm("DisplayAll", null);


                        }
                    } else {
                        Command[] cmds = new Command[2];
                        cmds[0] = new Command("Yes") {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                                if (quesVector != null) {
                                    quesVector.clear();
                                }
                                correctAnswer = null;
                                if (wrongAttempt != null) {
                                    wrongAttempt.clear();
                                }
                                if (idPicked != null) {
                                    idPicked.clear();
                                }
                                if (questionAnswered != null) {
                                    questionAnswered.clear();
                                }
                                // vector.clear();
                                time = 60;
                                answered = 0;
                                correct = 0;

                                if (quesVector == null || quesVector.isEmpty()) {

                                    System.out.println("the level used is " + level);
                                    connectDatabase(level);
                                    if (status1 != null && ("200".equals(status1))) {
                                        if ((quesVector != null && (!quesVector.isEmpty())) && (!errorRecieved)) {

                                            Collections.shuffle(quesVector);
                                            startButton();
                                            showForm("DisplayAll", null);
                                        } else {
                                            Dialog d = new Dialog("Ouch!!!");
                                            TextArea t = new TextArea("You might need to check your network connection");
                                            t.setUIID("TextArea");
                                            d.addComponent(t);
                                            d.setTimeout(3000);
                                            d.show();

                                        }
                                    } else if (status1 == null) {
                                        Dialog d = new Dialog("Ouch!!!");
                                        TextArea t = new TextArea("You might need to check your network connection");
                                        t.setUIID("TextArea");
                                        d.addComponent(t);
                                        d.setTimeout(3000);
                                        d.show();
                                    }

                                }
                            }
                        };

                        cmds[1] = new Command("No") {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                                showForm("StartPage", null);
                            }
                        };

                        TextArea text = new TextArea();
                        text.setText("You have successfully completed this level, do you want to move to the next level?");
                        text.setEditable(false);
                        Dialog.show("Hurray!!!", text, cmds);
                    }
                }

            }
        });
        ui.schedule(1000, true, f);

//        timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            private boolean lock;
//
//            @Override
//            public void run() {
//                --time;
//                if (lock) {
//                    return;
//                }
//
//                lock = true;
//                findTimerLabel(findContainer(f)).setText(String.valueOf(time));
//                f.animateLayoutAndWait(800);//revalidate();
//                lock = false;
//
//                if (time <= 0) {
//                    timer.cancel();
//                    System.out.println("Time up");
//                    if (correct < 5) {
//                        boolean condition = Dialog.show("Oops!!!", "Sorry your time is up. Do you want to try again? It will cost you N50.", "Play again", "Close");
//                        if (condition != true) {
//                            time = 60;
//                            correct = 0;
//                            answered = 0;
//                            correctAnswer = null;
//                            //vector.clear();
//                            if (wrongAttempt != null) {
//                                wrongAttempt.clear();
//                            }
//                            if (idPicked != null) {
//                                idPicked.clear();
//                            }
//                            if (questionAnswered != null) {
//                                questionAnswered.clear();
//                            }
//                            // saveQuestions();
//                            Collections.shuffle(quesVector);
//                            showForm("StartPage", null);
//
//                        } else {
//                            correct = 0;
//                            answered = 0;
//
//                            level = 1;
//                            counter = 1;
//                            correctAnswer = null;
//                            if (wrongAttempt != null) {
//                                wrongAttempt.clear();
//                                wrongAttempt = null;
//                            }
//                            if (idPicked != null) {
//                                idPicked.clear();
//                                idPicked = null;
//                            }
//                            if (questionAnswered != null) {
//                                questionAnswered.clear();
//                                questionAnswered = null;
//                            }
//
//                            Container c = findContainer1(f);
//                            FlowLayout fl = new FlowLayout();
//                            fl.setValign(0);
//                            fl.setAlign(4);
//                            c.setLayout(fl);
//                            c.setScrollableY(true);
//                            c.removeAll();
//                            Collections.shuffle(quesVector);
//                            startButton();
//                            for (int i = 0; i < quesVector.size(); i++) {
//                                //int n = r.nextInt(27);
//
//                                Hashtable hashtable = quesVector.elementAt(i);
//                                c.addComponent(addImage((Image) hashtable.get("SmallImage"), (Image) hashtable.get("LargeImage"), hashtable.get("Question").toString(), i, hashtable.get("id").toString(), hashtable.get("option_1").toString(), hashtable.get("option_2").toString(), hashtable.get("option_3").toString(), hashtable.get("correct_option").toString(), f));
//                            }
//
//                            time = 60;
//                            showForm("DisplayAll", null);
//
//
//                        }
//                    } else {
//                        Command[] cmds = new Command[2];
//                        cmds[0] = new Command("Yes") {
//                            @Override
//                            public void actionPerformed(ActionEvent evt) {
//                                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
//                                if (quesVector != null) {
//                                    quesVector.clear();
//                                }
//                                correctAnswer = null;
//                                if (wrongAttempt != null) {
//                                    wrongAttempt.clear();
//                                }
//                                if (idPicked != null) {
//                                    idPicked.clear();
//                                }
//                                if (questionAnswered != null) {
//                                    questionAnswered.clear();
//                                }
//                                // vector.clear();
//                                time = 60;
//                                answered = 0;
//                                correct = 0;
//
//                                if (quesVector == null || quesVector.isEmpty()) {
//
//                                    System.out.println("the level used is " + level);
//                                    connectDatabase(level);
//                                    if (status1 != null && ("200".equals(status1))) {
//                                        if ((quesVector != null && (!quesVector.isEmpty())) && (!errorRecieved)) {
//
//                                            Collections.shuffle(quesVector);
//                                            startButton();
//                                            showForm("DisplayAll", null);
//                                        } else {
//                                            Dialog d = new Dialog("Ouch!!!");
//                                            TextArea t = new TextArea("You might need to check your network connection");
//                                            t.setUIID("TextArea");
//                                            d.addComponent(t);
//                                            d.setTimeout(3000);
//                                            d.show();
//
//                                        }
//                                    } else if (status1 == null) {
//                                        Dialog d = new Dialog("Ouch!!!");
//                                        TextArea t = new TextArea("You might need to check your network connection");
//                                        t.setUIID("TextArea");
//                                        d.addComponent(t);
//                                        d.setTimeout(3000);
//                                        d.show();
//                                    }
//
//                                }
//                            }
//                        };
//
//                        cmds[1] = new Command("No") {
//                            @Override
//                            public void actionPerformed(ActionEvent evt) {
//                                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
//                                showForm("StartPage", null);
//                            }
//                        };
//
//                        TextArea text = new TextArea();
//                        text.setText("You have successfully completed this level, do you want to move to the next level?");
//                        text.setEditable(false);
//                        Dialog.show("Hurray!!!", text, cmds);
//                    }
//                }
////                f.revalidate();
//            }
//        }, 1000, 1000);

        Container c = findContainer1(f);
        // temp = new Vector<Hashtable>();
        FlowLayout fl = new FlowLayout();
        fl.setValign(0);
        fl.setAlign(4);
        c.setLayout(fl);

        for (int i = 0; i < quesVector.size(); i++) {
            //int n = r.nextInt(27);

            Hashtable hashtable = quesVector.elementAt(i);
            if (correctAnswer != null) {
                if (hashtable.get("id").toString().equals(correctAnswer)) {
                    //l.setIcon(im.scaledWidth(Display.getInstance().getDisplayWidth() / 5));
                    hashtable.remove("SmallImage");
                    hashtable.put("SmallImage", saka);
                }
                System.out.println("hashtable is " + hashtable);
            }
            c.addComponent(addImage((Image) hashtable.get("SmallImage"), (Image) hashtable.get("LargeImage"), hashtable.get("Question").toString(), i, hashtable.get("id").toString(), hashtable.get("option_1").toString(), hashtable.get("option_2").toString(), hashtable.get("option_3").toString(), hashtable.get("correct_option").toString(), f));
        }
        Command exitCmd = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ui.cancel();
                time = 60;
                showForm("StartPage", null);
            }
        };
        f.addCommand(exitCmd);
        f.setBackCommand(new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("back clicked 345");
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                if ((Display.getInstance().getCurrent()) instanceof Dialog) {
                    ((Dialog) Display.getInstance().getCurrent()).dispose();
                    System.out.println("back clicked");
                }
            }
        });

    }

    private Container addImage(final Image i, final Image j, final String question, final int k, final String id, final String optA, final String optB, final String optC, final String corrAns, final Form f) {

        Resources res = fetchResourceFile();
        final Container c = createContainer(res, "ImageRenderer");
        final Button b = findImageButton(c);
        b.setText(String.valueOf(k + 1));
        b.setIcon(i.scaledWidth(Display.getInstance().getDisplayWidth() / 5));

        b.addActionListener(new ActionListener() {
            private boolean lock;

            public void actionPerformed(ActionEvent evt) {
                //correct++;
                if (lock) {
                    return;
                }

                lock = true;
                //findTimerLabel(findContainer(f)).setText(String.valueOf(time));
                // f.animateLayoutAndWait(800);//revalidate();


                answered++;
                findCountLabel(f).setText(correct + "/" + answered);
                System.out.println("correct option +++++++++++++++++++" + corrAns);
                aQuestion = new Questions(id, question, optA, optB, optC, corrAns, (Image) i, (Image) j);

                Container c1 = findContainer1(f);
                FlowLayout fl = new FlowLayout();
                
                fl.setValign(4);
                fl.setAlign(4);
                
                c1.setLayout(fl);
                if (idPicked == null) {
                    idPicked = new Vector<String>();
                }
                idPicked.add(id);
                // System.out.println(idPicked.elementAt(idPicked.size()-1));

//                System.out.println("the length " + idPicked);
//                System.out.println("the correct ans is " + quesVector.elementAt(idPicked.size()).get("correct_option").toString());
//                System.out.println("+++++ " + quesHash.size() + " " + quesVector.size());
//                System.out.println("idpicked.size()-1 " + (idPicked.size() - 1));
//                System.out.println("idpicked.size() " + (idPicked.size()));
//                // corr_ans = h.elementAt(idPicked.size()-1 ).get(String.valueOf("id")).toString();//.get("correct_option").toString();
//                corr_ans = quesVector.elementAt(idPicked.size()).get("correct_option").toString();
//                System.out.println("correct answer is " + corr_ans);
                b.setEnabled(false);
//                FlowLayout fl = new FlowLayout();
//                fl.setValign(0);
//                fl.setAlign(4);
//                c1.setLayout(fl);
                //c1.setPreferredSize(new Dimension(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 2));
                c1.removeAll();
                c1.addComponent(addQuestion(aQuestion.getQuestions(), aQuestion.getImage(), aQuestion.getLargeImage(), aQuestion.getOptionA(), aQuestion.getOptionB(), aQuestion.getOptionC(), aQuestion.getCorrectAnswer(), f));


                f.animateLayoutAndWait(500);//revalidate();
                lock = false;
            }
        });

        if (idPicked != null) {
            for (int l = 0; l < idPicked.size(); l++) {
                String string = idPicked.elementAt(l);
                if (string.equals(id)) {
                    b.setEnabled(false);
                    if ((wrongAttempt != null) && (wrongAttempt.size() > 0)) {

                        for (int p = 0; p < wrongAttempt.size(); p++) {

                            // String correct = questionAnswered.elementAt(p);
                            //String wrongAnswer = wrongAttempt.elementAt(p);
                            //if ((wrongAttempt != null) && (wrongAttempt.size() > 0)) {

                            if (wrongAttempt.elementAt(p).equals(id)) {

                                findWrongSignLabel(c).setIcon(wrong.scaledWidth(Display.getInstance().getDisplayWidth() / 10));
                            }
                        }

                    }
                }
            }
        }
        return c;
    }

    public void Beepp(String str) {
        final InputStream is = Display.getInstance().getResourceAsStream(this.getClass(), str);
        try {
            mm = MediaManager.createMedia(is, "audio/wav");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        mm.setVolume(7);
        mm.play();
    }

    public Container addQuestion(String questions, Image i, Image j, String optA, String optB, String optC, final String correctAns, final Form f) {

        Resources res = fetchResourceFile();
        final Container c = createContainer(res, "QuestionRenderer");
        //Container c2 = findContainer1(c);
       // c2.getStyle().setBgImage(j.scaledWidth(Display.getInstance().getDisplayWidth() / 2));
        // c2.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED,true);
        //c2.getStyle().setBgImage(j);
        //.scaled(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight()-100)/*, Display.getInstance().getDisplayHeight() - 200)*/);
        findQuestionArea(c).setText(questions);
        findOptionARB(c).setText(optA.trim());
        findOptionBRB(c).setText(optB.trim());
        findOptionCRB(c).setText(optC.trim());
        final RadioButton rA = findOptionARB(c);
        final RadioButton rB = findOptionBRB(c);
        final RadioButton rC = findOptionCRB(c);
        findOptionARB(c).addActionListener(new ActionListener() {
           // private boolean lock;

            public void actionPerformed(ActionEvent evt) {
//                if (lock) {
//                    return;
//                }
//
//                lock = true;

                if (rA.isSelected()) {
                    rB.setSelected(false);
                    rC.setSelected(false);
                    //if ("option_1".equals(corr_ans)) {
                    if (("option_1".equals(aQuestion.getCorrectAnswer()))) {

                        correct++;
                        correctAnswer = aQuestion.getId();
                        findCountLabel(f).setText(correct + "/" + answered);
                        if (correct == 5) {
                            ui.cancel();

                            level++;
                            counter++;

                            if (counter == 5) {
                                counter = 1;
                                cycle++;
                                showForm("DisplayCongrats", null);

                            } else {
                                Command[] cmds = new Command[2];
                                cmds[0] = new Command("Yes") {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                                        if (quesVector != null) {
                                            quesVector.clear();
                                        }
                                        correctAnswer = null;
                                        if (wrongAttempt != null) {
                                            wrongAttempt.clear();
                                        }
                                        if (idPicked != null) {
                                            idPicked.clear();
                                        }
                                        if (questionAnswered != null) {
                                            questionAnswered.clear();
                                        }
                                        // vector.clear();
                                        time = 60;
                                        answered = 0;
                                        correct = 0;
//                                        if (mm.isPlaying()) {
//                                            mm.pause();
//                                            playMusic("/soundtrack.mp3");


//                                        }
                                        if (quesVector == null || quesVector.isEmpty()) {
//            System.out.println("Calling connectDatabase(" + level + ") and failedattempts is " + failedAttempts);
                                            System.out.println("the level used is " + level);
                                            connectDatabase(level);

                                            if (status1 != null && ("200".equals(status1))) {
                                                if ((quesVector != null && (!quesVector.isEmpty())) && (!errorRecieved)) {

                                                    Collections.shuffle(quesVector);
                                                    startButton();
                                                    showForm("DisplayAll", null);
                                                } else {
                                                    Dialog d = new Dialog("Ouch!!!");
                                                    TextArea t = new TextArea("You might need to check your network connection");
                                                    t.setUIID("TextArea");
                                                    d.addComponent(t);
                                                    d.setTimeout(3000);
                                                    d.show();

                                                }
                                            } else if (status1 == null) {
                                                Dialog d = new Dialog("Ouch!!!");
                                                TextArea t = new TextArea("You might need to check your network connection");
                                                t.setUIID("TextArea");
                                                d.addComponent(t);
                                                d.setTimeout(3000);
                                                d.show();
                                            }


                                        }
                                    }
                                };

                                cmds[1] = new Command("No") {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                                        showForm("StartPage", null);
                                    }
                                };

                                TextArea text = new TextArea();
                                text.setText("You have successfully completed this level, do you want to move to the next level?");
                                text.setEditable(false);
                                Dialog.show("Hurray!!!", text, cmds);
                            }

                        } else {
                            System.out.println("You are correct");
//findCountLabel(f).setText(correct + "/" + answered);
                            Label rightLabel = new Label(right);
                            Dialog dlgRight = new Dialog("CORRECT");
                            dlgRight.addComponent(rightLabel);
                            dlgRight.setTimeout(1000);
                            dlgRight.show();

                        }
                    } else {
//                        failedAttempts++;
                        //  System.out.println("You have wrongly answered " + failedAttempts);
                        if (wrongAttempt == null) {
                            wrongAttempt = new Vector<String>();
                        }
                        wrongAttempt.add(aQuestion.getId());
                        System.out.println("the wrong " + wrongAttempt);


//                        if (time == 0) {
//                            ui.cancel();
//                            System.out.println("time up4");
//                        }

                        Dialog dlgWrong = new Dialog("WRONG!");
                        if (mm.isPlaying()) {
                            mm.pause();
                            Beepp("/wrongbeep.wav");                            
                        }
                        playMusic("/soundtrack.mp3");
                        dlgWrong.addComponent(new Label(wrong));
                        dlgWrong.setDialogType(Dialog.TYPE_ALARM);
                        dlgWrong.setTimeout(1000);
                        dlgWrong.show();

                    }
                    Container c = findContainer1(f);
                    FlowLayout fl = new FlowLayout();
                    fl.setValign(0);
                    fl.setAlign(4);
                    c.setLayout(fl);
                    c.setScrollableY(true);
                    c.removeAll();

                    for (int i = 0; i < quesVector.size(); i++) {
                        //int n = r.nextInt(27);

                        Hashtable hashtable = quesVector.elementAt(i);
                        if (correctAnswer != null) {
                            if (hashtable.get("id").toString().equals(correctAnswer)) {
                                //l.setIcon(im.scaledWidth(Display.getInstance().getDisplayWidth() / 5));
                                hashtable.remove("SmallImage");
                                hashtable.put("SmallImage", saka);
                            }
                        }
                        c.addComponent(addImage((Image) hashtable.get("SmallImage"), (Image) hashtable.get("LargeImage"), hashtable.get("Question").toString(), i, hashtable.get("id").toString(), hashtable.get("option_1").toString(), hashtable.get("option_2").toString(), hashtable.get("option_3").toString(), hashtable.get("correct_option").toString(), f));
                    }
                    //}
                   // c.setScrollableY(true);
                    f.revalidate();
//                    lock = false;
                }
            }
        });

        findOptionBRB(c).addActionListener(new ActionListener() {
            //private boolean lock;

            public void actionPerformed(ActionEvent evt) {

//                if (lock) {
//                    return;
//                }
//
//                lock = true;

                if (rB.isSelected()) {
                    rA.setSelected(false);
                    rC.setSelected(false);
                    //if ("option_2".equals(corr_ans)) {
                    if (("option_2".equals(aQuestion.getCorrectAnswer()))) {
                        correct++;
                        findCountLabel(f).setText(correct + "/" + answered);
                        correctAnswer = aQuestion.getId();
                        System.out.println(correctAnswer);
                        if (correct == 5) {
                            ui.cancel();

                            level++;
                            counter++;

                            if (counter == 5) {
                                counter = 1;
                                cycle++;
                                showForm("DisplayCongrats", null);

                            } else {
                                Command[] cmds = new Command[2];
                                cmds[0] = new Command("Yes") {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                                        if (quesVector != null) {
                                            quesVector.clear();
                                        }
                                        correctAnswer = null;
                                        if (wrongAttempt != null) {
                                            wrongAttempt.clear();
                                        }
                                        if (idPicked != null) {
                                            idPicked.clear();
                                        }
                                        if (questionAnswered != null) {
                                            questionAnswered.clear();
                                        }
                                        // vector.clear();
                                        time = 60;
                                        answered = 0;
                                        correct = 0;
//                                        if (mm.isPlaying()) {
//                                            mm.pause();
//                                            playMusic("/soundtrack.mp3");
//                                        }
                                        if (quesVector == null || quesVector.isEmpty()) {
//            System.out.println("Calling connectDatabase(" + level + ") and failedattempts is " + failedAttempts);
                                            System.out.println("the level used is " + level);
                                            connectDatabase(level);

                                            if (status1 != null && ("200".equals(status1))) {
                                                if ((quesVector != null && (!quesVector.isEmpty())) && (!errorRecieved)) {

                                                    Collections.shuffle(quesVector);
                                                    startButton();
                                                    showForm("DisplayAll", null);
                                                } else {
                                                    Dialog d = new Dialog("Ouch!!!");
                                                    TextArea t = new TextArea("You might need to check your network connection");
                                                    t.setUIID("TextArea");
                                                    d.addComponent(t);
                                                    d.setTimeout(3000);
                                                    d.show();

                                                }
                                            } else if (status1 == null) {
                                                Dialog d = new Dialog("Ouch!!!");
                                                TextArea t = new TextArea("You might need to check your network connection");
                                                t.setUIID("TextArea");
                                                d.addComponent(t);
                                                d.setTimeout(3000);
                                                d.show();
                                            }


                                        }
                                    }
                                };

                                cmds[1] = new Command("No") {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                                        showForm("StartPage", null);
                                    }
                                };

                                TextArea text = new TextArea();
                                text.setText("You have successfully completed this level, do you want to move to the next level?");
                                text.setEditable(false);
                                Dialog.show("Hurray!!!", text, cmds);
                            }

                        } else {
                            System.out.println("You are correct");

                            correctAnswer = aQuestion.getId();
                            System.out.println(correctAnswer);
                            Label rightLabel = new Label(right);
                            Dialog dlgRight = new Dialog("CORRECT");
                            dlgRight.addComponent(rightLabel);
                            dlgRight.setTimeout(1000);
                            dlgRight.show();
                        }
                    } else {
//                        failedAttempts++;
                        //            System.out.println("The number of wrong attempts is " + failedAttempts);
                        if (wrongAttempt == null) {
                            wrongAttempt = new Vector<String>();
                        }
                        wrongAttempt.add(aQuestion.getId());
                        System.out.println("the wrong " + wrongAttempt);

//
//                        if (time == 0) {
//                            ui.cancel();
//                            System.out.println("time up3");
//                        }
                        System.out.println("You are wrong");
                        Dialog dlgWrong = new Dialog("WRONG!");
//                        if (mm.isPlaying()) {
//                            mm.pause();
//                            Beepp("/wrongbeep.wav");
//                            playMusic("/soundtrack.mp3");
//                        }
                        dlgWrong.addComponent(new Label(wrong));
                        dlgWrong.setTimeout(1000);
                        dlgWrong.show();

                    }
                    Container c = findContainer1(f);
                    FlowLayout fl = new FlowLayout();
                    fl.setValign(0);
                    fl.setAlign(4);
                    c.setLayout(fl);
                    c.removeAll();

                    for (int i = 0; i < quesVector.size(); i++) {
                        //int n = r.nextInt(27);

                        Hashtable hashtable = quesVector.elementAt(i);
                        if (correctAnswer != null) {
                            if (hashtable.get("id").toString().equals(correctAnswer)) {
                                //l.setIcon(im.scaledWidth(Display.getInstance().getDisplayWidth() / 5));
                                hashtable.remove("SmallImage");
                                hashtable.put("SmallImage", saka);
                            }
                        }
                        c.addComponent(addImage((Image) hashtable.get("SmallImage"), (Image) hashtable.get("LargeImage"), hashtable.get("Question").toString(), i, hashtable.get("id").toString(), hashtable.get("option_1").toString(), hashtable.get("option_2").toString(), hashtable.get("option_3").toString(), hashtable.get("correct_option").toString(), f));
                    }

                    c.setScrollableY(true);
                    f.revalidate();
//                    lock = false;
                }
            }
        });
        findOptionCRB(c).addActionListener(new ActionListener() {
           // private boolean lock;

            public void actionPerformed(ActionEvent evt) {
//                if (lock) {
//                    return;
//                }
//
//                lock = true;

                if (rC.isSelected()) {
                    rA.setSelected(false);
                    rB.setSelected(false);
                    //if ("option_3".equals(corr_ans)) {
                    if (("option_3".equals(aQuestion.getCorrectAnswer()))) {
                        correct++;
                        findCountLabel(f).setText(correct + "/" + answered);
                        correctAnswer = aQuestion.getId();
                        System.out.println(correctAnswer);
                        if (correct == 5) {
                            ui.cancel();

                            level++;
                            counter++;

                            if (counter == 5) {
                                counter = 1;
                                cycle++;
                                showForm("DisplayCongrats", null);

                            } else {
                                Command[] cmds = new Command[2];
                                cmds[0] = new Command("Yes") {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                                        if (quesVector != null) {
                                            quesVector.clear();
                                        }
                                        correctAnswer = null;
                                        if (wrongAttempt != null) {
                                            wrongAttempt.clear();
                                        }
                                        if (idPicked != null) {
                                            idPicked.clear();
                                        }
                                        if (questionAnswered != null) {
                                            questionAnswered.clear();
                                        }
                                        // vector.clear();
                                        time = 60;
                                        answered = 0;
                                        correct = 0;
//                                        if (mm.isPlaying()) {
//                                            mm.pause();
//                                            playMusic("/soundtrack.mp3");
//                                        }
                                        if (quesVector == null || quesVector.isEmpty()) {
//            System.out.println("Calling connectDatabase(" + level + ") and failedattempts is " + failedAttempts);
                                            System.out.println("the level used is " + level);
                                            connectDatabase(level);

                                            if (status1 != null && ("200".equals(status1))) {
                                                if ((quesVector != null && (!quesVector.isEmpty())) && (!errorRecieved)) {

                                                    Collections.shuffle(quesVector);
                                                    startButton();
                                                    showForm("DisplayAll", null);
                                                } else {
                                                    Dialog d = new Dialog("Ouch!!!");
                                                    TextArea t = new TextArea("You might need to check your network connection");
                                                    t.setUIID("TextArea");
                                                    d.addComponent(t);
                                                    d.setTimeout(3000);
                                                    d.show();

                                                }
                                            } else if (status1 == null) {
                                                Dialog d = new Dialog("Ouch!!!");
                                                TextArea t = new TextArea("You might need to check your network connection");
                                                t.setUIID("TextArea");
                                                d.addComponent(t);
                                                d.setTimeout(3000);
                                                d.show();
                                            }


                                        }
                                    }
                                };

                                cmds[1] = new Command("No") {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                                        showForm("StartPage", null);
                                    }
                                };

                                TextArea text = new TextArea();
                                text.setText("You have successfully completed this level, do you want to move to the next level?");
                                text.setEditable(false);
                                Dialog.show("Hurray!!!", text, cmds);
                            }


                        } else {
                            System.out.println("You are correct");

                            correctAnswer = aQuestion.getId();
                            System.out.println(correctAnswer);
                            Label rightLabel = new Label(right);
                            Dialog dlgRight = new Dialog("CORRECT");
                            dlgRight.addComponent(rightLabel);
                            dlgRight.setTimeout(1000);
                            dlgRight.show();
                        }
                    } else {

                        if (wrongAttempt == null) {
                            wrongAttempt = new Vector<String>();
                        }
                        wrongAttempt.add(aQuestion.getId());
                        System.out.println("the wrong " + wrongAttempt);

//
//                        }
//                        if (time == 0) {
//                            ui.cancel();
//                            System.out.println("time up3");
//                        }
                        System.out.println("You are wrong");

                        Dialog dlgWrong = new Dialog("WRONG!");
//                        if (mm.isPlaying()) {
//                            mm.pause();
//                            Beepp("/wrongbeep.wav");
//                            playMusic("/soundtrack.mp3");
//                        }
                        dlgWrong.addComponent(new Label(wrong));
                        dlgWrong.setTimeout(1000);
                        dlgWrong.show();

                    }
                    Container c = findContainer1(f);
                    FlowLayout fl = new FlowLayout();
                    fl.setValign(0);
                    fl.setAlign(4);
                    c.setLayout(fl);
                    c.removeAll();

                    for (int i = 0; i < quesVector.size(); i++) {
                        //int n = r.nextInt(27);

                        Hashtable hashtable = quesVector.elementAt(i);
                        if (correctAnswer != null) {
                            if (hashtable.get("id").toString().equals(correctAnswer)) {
                                //l.setIcon(im.scaledWidth(Display.getInstance().getDisplayWidth() / 5));
                                hashtable.remove("SmallImage");
                                hashtable.put("SmallImage", saka);
                            }
                        }
                        c.addComponent(addImage((Image) hashtable.get("SmallImage"), (Image) hashtable.get("LargeImage"), hashtable.get("Question").toString(), i, hashtable.get("id").toString(), hashtable.get("option_1").toString(), hashtable.get("option_2").toString(), hashtable.get("option_3").toString(), hashtable.get("correct_option").toString(), f));
                    }
                    c.setScrollableY(true);

                    f.animateLayoutAndWait(500);//revalidate();
//                    lock = false;
                }

            }
        });
        return c;
    }

    @Override
    protected void onDisplayCongrats_OkButtonAction(Component c, ActionEvent event) {
//        if (fetchQuestion.isAlive()) {
//            if (quesVector2 != null) {
//                quesVector2.clear();
//            }

        connectDatabaseMB(level);
//        }
        if (status2 != null && ("200".equals(status2))) {
            if ((quesVector != null || (!quesVector.isEmpty())) && (!errorRecieved)) {
                // saveQuestions();
                // System.out.println("The vector" + quesVector);
                Collections.shuffle(quesVector);

//                failedAttempts = 0;
                //startButton();
                showForm("MoneyBagForm", null);
            } else {
                Dialog d = new Dialog("Ouch!!!");
                TextArea l = new TextArea("You might need to check your network connection");
                l.setUIID("TextArea");
                d.addComponent(l);
                d.setTimeout(3000);
                d.show();

            }
        } else if (status2 == null) {
            Dialog d = new Dialog("Ouch!!!");
            TextArea l = new TextArea("You might need to check your network connection");
            l.setUIID("TextArea");
            d.addComponent(l);
            d.setTimeout(3000);
            d.show();
        }


    }

    @Override
    protected void onDisplayCongrats_DismissButtonAction(Component c, ActionEvent event) {
        Command[] cmds = new Command[2];
        cmds[0] = new Command("Yes") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                level++;
                level = 1;
                if (quesVector != null) {
                    quesVector.clear();
                }
                correctAnswer = null;
                if (wrongAttempt != null) {
                    wrongAttempt.clear();
                }
                if (idPicked != null) {
                    idPicked.clear();
                }
                if (questionAnswered != null) {
                    questionAnswered.clear();
                }
                // vector.clear();
                time = 60;
                answered = 0;
                correct = 0;
                if (mm.isPlaying()) {
                    mm.pause();
                    playMusic("/soundtrack.mp3");
                }
                if (quesVector == null || quesVector.isEmpty()) {
//            System.out.println("Calling connectDatabase(" + level + ") and failedattempts is " + failedAttempts);
                    System.out.println("the level used is " + level);
                    connectDatabase(level);

                    if (status1 != null && ("200".equals(status1))) {
                        if ((quesVector != null && (!quesVector.isEmpty())) && (!errorRecieved)) {

                            Collections.shuffle(quesVector);
                            startButton();
                            showForm("DisplayAll", null);
                        } else {
                            Dialog d = new Dialog("Ouch!!!");
                            TextArea t = new TextArea("You might need to check your network connection");
                            t.setUIID("TextArea");
                            d.addComponent(t);
                            d.setTimeout(3000);
                            d.show();

                        }
                    } else if (status1 == null) {
                        Dialog d = new Dialog("Ouch!!!");
                        TextArea t = new TextArea("You might need to check your network connection");
                        t.setUIID("TextArea");
                        d.addComponent(t);
                        d.setTimeout(3000);
                        d.show();
                    }


                }
            }
        };
        cmds[1] = new Command("No") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ((Dialog) Display.getInstance().getCurrent()).dispose();
            }
        };
        TextArea text = new TextArea();
        text.setText("Are you sure you want to skip the money bag question? This will cost you 50 naira");
        text.setEditable(false);
        Dialog.show("Hurray!!!", text, cmds);
    }

    @Override
    protected void beforeMoneyBagForm(final Form f) {

        if (cycle == 1) {
            amount = 400;
        } else if (cycle == 2) {
            amount = 750;
        } else if (cycle == 3) {
            amount = 3000;
        } else if (cycle == 4) {
            amount = 10000;
        }
        findContainer5(f).getStyle().setBgImage(header);
        Random rand = new Random();
        int n = rand.nextInt(quesVector2.size());
        Collections.shuffle(quesVector2);
        mbq = quesVector2.elementAt(n);
//        for (int i = 0; i < quesVector2.size(); i++) {
//
//            mbq = quesVector2.elementAt(n);
//
//        }
        findTimerLabelMB(f).setText(String.valueOf(timeMB));
        ui = new UITimer(new Runnable() {
            public void run() {
                --timeMB;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                if (timeMB < 0) {
                    ui.cancel();
                    Dialog.show("Oops!!!", "your 10 seconds is over", "OK", null);
                    if (mm.isPlaying()) {
                        mm.pause();
                        playMusic("/soundtrack.mp3");
                    }
                    showForm("StartPage", null);
                } else {
                    findTimerLabelMB(f).setText(String.valueOf(timeMB));
                    //f.revalidate();
                }

            }
        });
        ui.schedule(1000, true, f);

//        timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                --timeMB;
//                //f.revalidate();
//                findTimerLabelMB(f).setText(String.valueOf(timeMB));
//                f.animateLayoutAndWait(500);//revalidate();
//                if (timeMB == 0) {
//                    timer.cancel();
//                    System.out.println("Time up");
//                    Dialog.show("Oops!!!", "10 seconds over", "OK", null);
//                    if (mm.isPlaying()) {
//                        mm.pause();
//                        playMusic("/soundtrack.mp3");
//                    }
//                    showForm("StartPage", null);
//                }
//
//            }
//        }, 1000, 1000);

        findMoneyBagQ(f).setText(mbq.get("Question").toString());
        findOptARBMB(f).setText(mbq.get("option_1").toString());
        findOptBRBMB(f).setText(mbq.get("option_2").toString());
        findOptCRBMB(f).setText(mbq.get("option_3").toString());
        System.out.println("correct option.... " + mbq.get("correct_option").toString());

        findOptARBMB(f).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (findOptARBMB(f).isSelected()) {
                    ui.cancel();
                    findOptBRBMB(f).setSelected(false);
                    findOptCRBMB(f).setSelected(false);
                }
                if ("option_1".equals(mbq.get("correct_option").toString())) {

                    Dialog.show("Congratulations!", "You have won yourself N" + amount + " worth of MTN Airtime recharge card. You have come to the end of this session", "OK", null);
                    // level++;
                    level = 1;
                    cycle = 1;
                    if (quesVector != null) {
                        quesVector.clear();
                    }
                    if (quesVector2 != null) {
                        quesVector2.clear();
                    }
                    correctAnswer = null;
                    if (wrongAttempt != null) {
                        wrongAttempt.clear();
                    }
                    if (idPicked != null) {
                        idPicked.clear();
                    }
                    if (questionAnswered != null) {
                        questionAnswered.clear();
                    }
                    // vector.clear();
                    time = 60;
                    answered = 0;
                    correct = 0;
                    if (mm.isPlaying()) {
                        mm.pause();
                        playMusic("/soundtrack.mp3");
                    }
                    showForm("StartPage", null);
                } else {
                    Dialog.show("OOPS!", "You have failed the ‘money bag’ question.", "OK", null);
                    if (mm.isPlaying()) {
                        mm.pause();
                        playMusic("/soundtrack.mp3");
                    }
                    showForm("StartPage", null);
                }
            }
        });
        findOptBRBMB(f).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (findOptBRBMB(f).isSelected()) {
                    ui.cancel();
                    findOptARBMB(f).setSelected(false);
                    findOptCRBMB(f).setSelected(false);
                }
                if ("option_2".equals(mbq.get("correct_option").toString())) {

                    Dialog.show("Congratulations!", "You have won yourself N" + amount + " worth of MTN Airtime recharge card. You have come to the end of this session", "OK", null);
                    // level++;
                    level = 1;
                    cycle = 1;
                    if (quesVector != null) {
                        quesVector.clear();
                    }
                    if (quesVector2 != null) {
                        quesVector2.clear();
                    }
                    correctAnswer = null;
                    if (wrongAttempt != null) {
                        wrongAttempt.clear();
                    }
                    if (idPicked != null) {
                        idPicked.clear();
                    }
                    if (questionAnswered != null) {
                        questionAnswered.clear();
                    }
                    // vector.clear();
                    time = 60;
                    answered = 0;
                    correct = 0;
                    if (mm.isPlaying()) {
                        mm.pause();
                        playMusic("/soundtrack.mp3");
                    }
                    showForm("StartPage", null);
                } else {
                    Dialog.show("OOPS!", "You have failed the ‘money bag’ question.", "Ok", null);
                    if (mm.isPlaying()) {
                        mm.pause();
                        playMusic("/soundtrack.mp3");
                    }
                    showForm("StartPage", null);
                }
            }
        });
        findOptCRBMB(f).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (findOptCRBMB(f).isSelected()) {
                    ui.cancel();
                    findOptARBMB(f).setSelected(false);
                    findOptBRBMB(f).setSelected(false);
                }
                if ("option_3".equals(mbq.get("correct_option").toString())) {
                    Dialog.show("Congratulations!", "You have won yourself N" + amount + " worth of MTN Airtime recharge card. You have come to the end of this session", "OK", null);
                    // level++;
                    level = 1;
                    cycle = 1;
                    if (quesVector != null) {
                        quesVector.clear();
                    }
                    if (quesVector2 != null) {
                        quesVector2.clear();
                    }
                    correctAnswer = null;
                    if (wrongAttempt != null) {
                        wrongAttempt.clear();
                    }
                    if (idPicked != null) {
                        idPicked.clear();
                    }
                    if (questionAnswered != null) {
                        questionAnswered.clear();
                    }
                    // vector.clear();
                    time = 60;
                    answered = 0;
                    correct = 0;
                    if (mm.isPlaying()) {
                        mm.pause();
                        playMusic("/soundtrack.mp3");
                    }
                    showForm("StartPage", null);
                } else {
                    Dialog.show("OOPS!", "You have failed the ‘money bag’ question.", "Ok", null);
                    if (mm.isPlaying()) {

                        mm.pause();
                        playMusic("/soundtrack.mp3");
                    }
                    timeMB = 10;
                    showForm("StartPage", null);
                }
            }
        });

    }
//    public void p(String message){
//        System.out.println(message + "\n");
//    }
    Thread fetchQuestion = new Thread() {
        public void run() {
            ConnectionRequest conn;
            conn = new ConnectionRequest() {
//           @Override
//            protected void buildRequestBody(OutputStream os) throws IOException {
//               os.write(json.getBytes("UTF-8"));
//           }
                @Override
                protected void readHeaders(Object connection) throws IOException {
                    status = getHeader(connection, "status"); // if the status is in the headerrd
                    // super.readHeaders(connection); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                protected void readResponse(InputStream input) throws IOException {
                    status2 = String.valueOf(getResponseCode());
                    JSONParser jp = new JSONParser();
                    quesHash2 = jp.parse(new InputStreamReader(input));
                    quesVector2 = (Vector) quesHash2.get("root");
                    // h = (Vector) h1.get("");
                    System.out.println(quesHash2);
                    System.out.println(status2);
                    System.out.println(quesVector2);

                }
            };

            System.out.println("the level used is " + level);

            String url = "http://107.20.244.125/MTNPortinggame/index.php?levels=" + level + "&moneybag=1";
            //String url = "http://localhost/saka/index.php?limit=28";
            conn.setUrl(url); //gets the url u want to talk to
            conn.setContentType("application/json");
            conn.setPost(false);
            conn.setHttpMethod("GET"); //could be put if put was set true
            conn.setFailSilently(true);
            conn.setDuplicateSupported(true);
            //conn.setDisposeOnCompletion(d);
            NetworkManager nm = NetworkManager.getInstance();
            nm.start();
            nm.addToQueueAndWait(conn);
            System.out.println("I got it here");

            // connectDatabase(level);
        }
    };

    @Override
    protected void onCreateDisplayCongrats() {
        if (mm.isPlaying()) {
            mm.pause();
            playMusic("/point5.mp3");
        }
    }

    public void connectDatabaseMB(int level) {
        System.out.println("calling.... connectDatabaseMB()");
        final ConnectionRequest conn;
        conn = new ConnectionRequest() {
            @Override
            protected void readHeaders(Object connection) throws IOException {
                status = getHeader(connection, "status"); // if the status is in the headerrd
                // super.readHeaders(connection); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected void readResponse(InputStream input) throws IOException {
                status2 = String.valueOf(getResponseCode());
                JSONParser jp = new JSONParser();
                try {
                    quesHash2 = jp.parse(new InputStreamReader(input));
                    quesVector2 = (Vector) quesHash2.get("root");
                } catch (Exception e) {
                }

                // h = (Vector) h1.get("");
                System.out.println(quesHash2);
                System.out.println(status2);
                System.out.println(quesVector2);

            }
        };

        System.out.println("the level used is " + level + " and cycle is " + cycle);
        final NetworkManager nm = NetworkManager.getInstance();
        Command c = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ((Dialog) Display.getInstance().getCurrent()).dispose();
                nm.killAndWait(conn);
            }
        };

        InfiniteProgress ip = new InfiniteProgress();

        Dialog d = new Dialog();
        d.setDialogUIID("Label");
        d.setLayout(new BorderLayout());
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label("Loading");
        l.getStyle().getBgTransparency();
        cnt.addComponent(l);
        cnt.addComponent(ip);
        d.addComponent(BorderLayout.CENTER, cnt);
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createEmpty());
        d.showPacked(BorderLayout.CENTER, false);
        d.setBackCommand(c);

        // System.out.println("level at this point is " + level);

        String url = "http://107.20.244.125/MTNPortinggame/index.php?" + "moneybag=1&cycles=" + cycle;
        //else{
//            String url = "http://107.20.244.125/MTNPortinggame/index.php?levels=" + (level-1) + "&moneybag=1&cycles=" + cycle;
//        }

        conn.setUrl(url); //gets the url u want to talk to
        conn.setContentType("application/json");
        conn.setPost(false);
        conn.setHttpMethod("GET"); //could be put if put was set true
        conn.setFailSilently(true);
        conn.setDuplicateSupported(true);
        conn.setDisposeOnCompletion(d);

        nm.start();
        nm.addToQueueAndWait(conn);
        //System.out.println("I got it here");

    }

    @Override
    protected void onInstructionForm_ButtonAction(Component c, ActionEvent event) {
        showForm("StartPage", null);

    }

    @Override
    protected void onStartPage_InstructionButtonAction(Component c, ActionEvent event) {
        showForm("InstructionForm", null);
        next = 1;
        previous = 3;

    }

//    @Override
//    protected void onMoneyBagForm_DismissAction(Component c, ActionEvent event) {
//        level++;
//        if (quesVector != null) {
//            quesVector.clear();
//        }
//        correctAnswer = null;
//        if (wrongAttempt != null) {
//            wrongAttempt.clear();
//        }
//        if (idPicked != null) {
//            idPicked.clear();
//        }
//        if (questionAnswered != null) {
//            questionAnswered.clear();
//        }
//        // vector.clear();
//        time = 60;
//        answered = 0;
//        correct = 0;
//        if (mm.isPlaying()) {
//            mm.pause();
//            playMusic("/soundtrack.mp3");
//        }
////        if (quesVector == null || quesVector.isEmpty()) {
//        if (quesVector != null && !quesVector.isEmpty()) {
//            quesVector.clear();
//        }
////        System.out.println("Calling connectDatabase(" + level + ") and failedattempts is " + failedAttempts);
//        connectDatabase(level);
//        if (status1 != null && ("200".equals(status1))) {
//            if ((quesVector != null || (!quesVector.isEmpty())) && (!errorRecieved)) {
//                // saveQuestions();
//                // System.out.println("The vector" + quesVector);
//                Collections.shuffle(quesVector);
//
////                failedAttempts = 0;
//                startButton();
//                showForm("DisplayAll", null);
//            } else {
//                Dialog d = new Dialog("Ouch!!!");
//                Label l = new Label("You might need to check your network connection");
//                l.setUIID("congratsText");
//                d.addComponent(l);
//                d.setTimeout(3000);
//                d.show();
//
//            }
//        } else if (status1 == null) {
//            Dialog d = new Dialog("Ouch!!!");
//            Label l = new Label("You might need to check your network connection");
//            l.setUIID("TextArea");
//            d.addComponent(l);
//            d.setTimeout(3000);
//            d.show();
//        }
//
////            if (status1 != null) {
////                if ("200".equals(status1)) {
////                    // saveQuestions();
////                    // System.out.println("The vector" + quesVector);
////                    Collections.shuffle(quesVector);
////
////                    //failedAttempts = 0;
////                    startButton();
////                    showForm("DisplayAll", null);
////
////                }
////            }
//        //     }
//
//
//    }
//
//    @Override
//    protected void onMoneyBagForm_HomeAction(Component c, ActionEvent event) {
//        showForm("StartPage", null);
//
//    }
    @Override
    protected void beforeStartPage(Form f) {
        Image strtButton = fetchResourceFile().getImage("buttonStart.png");
        findStartbutton(f).setIcon(strtButton.scaledWidth(Display.getInstance().getDisplayWidth() / 3));
        findInstructionButton(f).setIcon(instrButton.scaledWidth(Display.getInstance().getDisplayWidth() / 3));
        findExitButton(f).setIcon(exitButton.scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 9));
//Component c =
        // final Dialog dlg = new Dialog();
//        final CheckBox chk = new CheckBox("Disable");
//        final Command cmds[] = new Command[1];
//        cmds[0] = new Command("Cancel") {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//               if ((Display.getInstance().getCurrent()) instanceof Dialog) {
//                        ((Dialog) Display.getInstance().getCurrent()).dispose();
//                    }  
//            }
//        };
//            
//        chk.addActionListener ( 
//                new ActionListener() {
//            
//
//            public void actionPerformed(ActionEvent evt) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                if (mm.isPlaying()) {
//                    mm.pause();
//                    chk.setText("Enable");
//                    if ((Display.getInstance().getCurrent()) instanceof Dialog) {
//                        ((Dialog) Display.getInstance().getCurrent()).dispose();
//                    }
//                    // findCheckBox1(c).setSelected(false);
//                    // mm.setVolume(0);
//                } else {
//                    mm.play();
//                    chk.setText("Disable");
//                    if ((Display.getInstance().getCurrent()) instanceof Dialog) {
//                        ((Dialog) Display.getInstance().getCurrent()).dispose();
//                    }
//                }
//            }
//        }
//        );
//       // dlg.addComponent(chk);
//
//        f.addCommand(new Command("Sound") {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
//                //dlg.show();
//               Dialog.show(null, chk, cmds);
//               
//            }
//        });
    }

    @Override
    protected void onInstructionForm_NextInstructionButtonAction(Component c, ActionEvent event) {
        TextArea text = new TextArea();
        text.setUIID("InstructionText");
        text.setText("The MTN PORTING GAME is an intriguing game with amazing ‘instant prizes to be won’ per cycle. The game has 5 cycles of play. "
                + "Each cycle has 4 levels. In each level, you will have to port 5 SIM CARDS by clicking to answer "
                + "correctly the questions behind each of the SIM CARDS at random to qualify for the next level in each cycle. "
                + "The time allocated to each level is 60Seconds. Each cycle of play will cost you N50.");
        text.setEditable(false);


        TextArea text1 = new TextArea();
        text1.setUIID("InstructionText");
        text1.setEditable(false);
        text1.setText("After each cycle, there will be a ‘money bag question’ that entitles you to a ‘prize’. "
                + "The ‘prize’ will have to be won by answering the question that comes with the ‘money bag’. "
                + "The ‘prize’ increases as the playing cycle increases. "
                + "If you attempt any of the ‘money bag’ questions either ‘CORRECT OR FAIL’ you will be taken bac"
                + "k to the beginning of the game. If ‘CORRECT’, you would be rewarded instantly.");

        TextArea text2 = new TextArea();
        text2.setUIID("InstructionText");
        text2.setEditable(false);
        text2.setText("The ‘money bag’ prize for cycle-1 is N400 MTN RECHARGE CARD; cycle-2 is N750 MTN RECHARGE CARD; "
                + "cycle 3 is N3000 MTN RECHARGE CARD; cycle-4 is N7, 500 MTN RECHARGE CARD and cycle-5 is N10, 000 MTN RECHARGE CARD. "
                + "The time allocated to each ‘money bag’ question is 10 seconds. You will have the option of either attempting the ’money bag’ "
                + "question for cycle-1 or ‘skip’ it for cycle 2 that has a higher ‘prize value’. Same applies to cycle 2, 3 and 4. "
                + "The time allocated to each ‘money bag’ question is 10 seconds.");

        //  TextArea[] texts = new TextArea[3];
        // texts[0] = text;
        //texts[1] = text1;
        //texts[2] = text2;

        //next+=1;
//        if (next == 0) {
//            findContainer2(c.getComponentForm()).removeComponent(findPreviousButton(c.getComponentForm()));
//            next += 1;
//        } else 
        if (next == 1) {

            findContainer(c.getComponentForm()).replace(findContainer(c.getComponentForm()).getComponentAt(0), text1, CommonTransitions.createFade(1000));
            findContainer1(c.getComponentForm()).addComponent(BorderLayout.WEST, findPreviousButton(c.getComponentForm()));
            next += 1;
            previous -= 1;
            System.out.println("nexxt and previous are " + next + " and " + previous);
        } else if (next == 2) {
            // findNextInstructionButton(c.getComponentForm()).setEnabled(false);

            findContainer(c.getComponentForm()).replace(findContainer(c.getComponentForm()).getComponentAt(0), text2, CommonTransitions.createFade(1000));
            findContainer1(c.getComponentForm()).removeComponent(findNextInstructionButton(c.getComponentForm()));
            next += 1;
            previous -= 1;
            System.out.println("nexxt and previous are " + next + " and " + previous);
        }
    }

    @Override
    protected void beforeInstructionForm(Form f) {
//        findContainer3(f).getStyle().setBgImage(header);
//        findContainer3(f).getStyle().setBorder(null);
//        findContainer3(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        //findContainer3(f).getStyle().setBgImage(header);
        findContainer1(f).removeComponent(findPreviousButton(f));

    }

    @Override
    protected void onInstructionForm_PreviousButtonAction(Component c, ActionEvent event) {
        TextArea text = new TextArea();
        text.setUIID("InstructionText");
        text.setText("The MTN PORTING GAME is an intriguing game with amazing ‘instant prizes to be won’ per cycle. The game has 5 cycles of play. "
                + "Each cycle has 4 levels. In each level, you will have to port 5 SIM CARDS by clicking to answer "
                + "correctly the questions behind each of the SIM CARDS at random to qualify for the next level in each cycle. "
                + "The time allocated to each level is 60Seconds. Each cycle of play will cost you N50.");
        text.setEditable(false);


        TextArea text1 = new TextArea();
        text1.setUIID("InstructionText");
        text1.setEditable(false);
        text1.setText("After each cycle, there will be a ‘money bag question’ that entitles you to a ‘prize’. "
                + "The ‘prize’ will have to be won by answering the question that comes with the ‘money bag’. "
                + "The ‘prize’ increases as the playing cycle increases. An attempt on the money bag question will take you to cycle 1"
                + "If you attempt any of the ‘money bag’ questions either ‘CORRECT OR FAIL’ you will be taken bac"
                + "k to the beginning of the game. If ‘CORRECT’, you would be rewarded instantly.");

        TextArea text2 = new TextArea();
        text2.setUIID("InstructionText");
        text2.setEditable(false);
        text2.setText("The ‘money bag’ prize for cycle-1 is N400 MTN RECHARGE CARD; cycle-2 is N750 MTN RECHARGE CARD; "
                + "cycle 3 is N3000 MTN RECHARGE CARD; cycle-4 is N7, 500 MTN RECHARGE CARD and cycle-5 is N10, 000 MTN RECHARGE CARD. "
                + "The time allocated to each ‘money bag’ question is 10 seconds. You will have the option of either attempting the ’money bag’ "
                + "question for cycle-1 or ‘skip’ it for cycle 2 that has a higher ‘prize value’. Same applies to cycle 2, 3 and 4. "
                + "");
        if (previous == 2) {
            findContainer(c.getComponentForm()).replace(findContainer(c.getComponentForm()).getComponentAt(0), text, CommonTransitions.createFade(1000));
            findContainer1(c.getComponentForm()).removeComponent(findPreviousButton(c.getComponentForm()));
            next -= 1;
            previous += 1;
            System.out.println("next and previous are " + next + " and " + previous);

        }
        if (previous == 1) {
            findContainer(c.getComponentForm()).replace(findContainer(c.getComponentForm()).getComponentAt(0), text1, CommonTransitions.createFade(1000));
            findContainer1(c.getComponentForm()).addComponent(BorderLayout.EAST, findNextInstructionButton(c.getComponentForm()));
            next -= 1;
            previous += 1;
            System.out.println("next and previous are " + next + " and " + previous);
        }

    }
}