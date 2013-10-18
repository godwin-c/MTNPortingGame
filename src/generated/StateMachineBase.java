/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("RadioButton", com.codename1.ui.RadioButton.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "SplashScreen";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("RadioButton", com.codename1.ui.RadioButton.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "SplashScreen");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.RadioButton findOptionCRB(Component root) {
        return (com.codename1.ui.RadioButton)findByName("optionCRB", root);
    }

    public com.codename1.ui.RadioButton findOptionCRB() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("optionCRB", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("optionCRB", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer3(Component root) {
        return (com.codename1.ui.Container)findByName("Container3", root);
    }

    public com.codename1.ui.Container findContainer3() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container3", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container3", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer2(Component root) {
        return (com.codename1.ui.Container)findByName("Container2", root);
    }

    public com.codename1.ui.Container findContainer2() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer1(Component root) {
        return (com.codename1.ui.Container)findByName("Container1", root);
    }

    public com.codename1.ui.Container findContainer1() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer5(Component root) {
        return (com.codename1.ui.Container)findByName("Container5", root);
    }

    public com.codename1.ui.Container findContainer5() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container5", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container5", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findExitButton(Component root) {
        return (com.codename1.ui.Button)findByName("exitButton", root);
    }

    public com.codename1.ui.Button findExitButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("exitButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("exitButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("TextArea", root);
    }

    public com.codename1.ui.TextArea findTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("TextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("TextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findOptionBRB(Component root) {
        return (com.codename1.ui.RadioButton)findByName("optionBRB", root);
    }

    public com.codename1.ui.RadioButton findOptionBRB() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("optionBRB", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("optionBRB", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findWrongSignLabel(Component root) {
        return (com.codename1.ui.Label)findByName("wrongSignLabel", root);
    }

    public com.codename1.ui.Label findWrongSignLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("wrongSignLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("wrongSignLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findImageButton(Component root) {
        return (com.codename1.ui.Button)findByName("imageButton", root);
    }

    public com.codename1.ui.Button findImageButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("imageButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("imageButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findButton(Component root) {
        return (com.codename1.ui.Button)findByName("Button", root);
    }

    public com.codename1.ui.Button findButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("Button", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("Button", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findQuestionArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("questionArea", root);
    }

    public com.codename1.ui.TextArea findQuestionArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("questionArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("questionArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findMoneyBagQ(Component root) {
        return (com.codename1.ui.TextArea)findByName("moneyBagQ", root);
    }

    public com.codename1.ui.TextArea findMoneyBagQ() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("moneyBagQ", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("moneyBagQ", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findCountLabel(Component root) {
        return (com.codename1.ui.Label)findByName("countLabel", root);
    }

    public com.codename1.ui.Label findCountLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("countLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("countLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findPreviousButton(Component root) {
        return (com.codename1.ui.Button)findByName("previousButton", root);
    }

    public com.codename1.ui.Button findPreviousButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("previousButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("previousButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findOptionARB(Component root) {
        return (com.codename1.ui.RadioButton)findByName("optionARB", root);
    }

    public com.codename1.ui.RadioButton findOptionARB() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("optionARB", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("optionARB", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findOptBRBMB(Component root) {
        return (com.codename1.ui.RadioButton)findByName("optBRBMB", root);
    }

    public com.codename1.ui.RadioButton findOptBRBMB() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("optBRBMB", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("optBRBMB", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findOptCRBMB(Component root) {
        return (com.codename1.ui.RadioButton)findByName("optCRBMB", root);
    }

    public com.codename1.ui.RadioButton findOptCRBMB() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("optCRBMB", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("optCRBMB", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer(Component root) {
        return (com.codename1.ui.Container)findByName("Container", root);
    }

    public com.codename1.ui.Container findContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findImageRenderer(Component root) {
        return (com.codename1.ui.Container)findByName("ImageRenderer", root);
    }

    public com.codename1.ui.Container findImageRenderer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("ImageRenderer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("ImageRenderer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findInstructionButton(Component root) {
        return (com.codename1.ui.Button)findByName("InstructionButton", root);
    }

    public com.codename1.ui.Button findInstructionButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("InstructionButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("InstructionButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findNextInstructionButton(Component root) {
        return (com.codename1.ui.Button)findByName("nextInstructionButton", root);
    }

    public com.codename1.ui.Button findNextInstructionButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("nextInstructionButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("nextInstructionButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLevelLabel(Component root) {
        return (com.codename1.ui.Label)findByName("levelLabel", root);
    }

    public com.codename1.ui.Label findLevelLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("levelLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("levelLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findTimerLabel(Component root) {
        return (com.codename1.ui.Label)findByName("TimerLabel", root);
    }

    public com.codename1.ui.Label findTimerLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("TimerLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("TimerLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findDismissButton(Component root) {
        return (com.codename1.ui.Button)findByName("dismissButton", root);
    }

    public com.codename1.ui.Button findDismissButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("dismissButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("dismissButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findStartbutton(Component root) {
        return (com.codename1.ui.Button)findByName("startbutton", root);
    }

    public com.codename1.ui.Button findStartbutton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("startbutton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("startbutton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findTimerLabelMB(Component root) {
        return (com.codename1.ui.Label)findByName("timerLabelMB", root);
    }

    public com.codename1.ui.Label findTimerLabelMB() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("timerLabelMB", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("timerLabelMB", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findSplashLabel(Component root) {
        return (com.codename1.ui.Label)findByName("splashLabel", root);
    }

    public com.codename1.ui.Label findSplashLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("splashLabel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("splashLabel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findQuestionRenderer(Component root) {
        return (com.codename1.ui.Container)findByName("QuestionRenderer", root);
    }

    public com.codename1.ui.Container findQuestionRenderer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("QuestionRenderer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("QuestionRenderer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel(Component root) {
        return (com.codename1.ui.Label)findByName("Label", root);
    }

    public com.codename1.ui.Label findLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.RadioButton findOptARBMB(Component root) {
        return (com.codename1.ui.RadioButton)findByName("optARBMB", root);
    }

    public com.codename1.ui.RadioButton findOptARBMB() {
        com.codename1.ui.RadioButton cmp = (com.codename1.ui.RadioButton)findByName("optARBMB", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.RadioButton)findByName("optARBMB", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findOkButton(Component root) {
        return (com.codename1.ui.Button)findByName("okButton", root);
    }

    public com.codename1.ui.Button findOkButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("okButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("okButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    protected void exitForm(Form f) {
        if("InstructionForm".equals(f.getName())) {
            exitInstructionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MoneyBagForm".equals(f.getName())) {
            exitMoneyBagForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("StartPage".equals(f.getName())) {
            exitStartPage(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            exitMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayAll".equals(f.getName())) {
            exitDisplayAll(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("QuestionRenderer".equals(f.getName())) {
            exitQuestionRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ImageRenderer".equals(f.getName())) {
            exitImageRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(f.getName())) {
            exitSplashScreen(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayCongrats".equals(f.getName())) {
            exitDisplayCongrats(f);
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void exitInstructionForm(Form f) {
    }


    protected void exitMoneyBagForm(Form f) {
    }


    protected void exitStartPage(Form f) {
    }


    protected void exitMain(Form f) {
    }


    protected void exitDisplayAll(Form f) {
    }


    protected void exitQuestionRenderer(Form f) {
    }


    protected void exitImageRenderer(Form f) {
    }


    protected void exitSplashScreen(Form f) {
    }


    protected void exitDisplayCongrats(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("InstructionForm".equals(f.getName())) {
            beforeInstructionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MoneyBagForm".equals(f.getName())) {
            beforeMoneyBagForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("StartPage".equals(f.getName())) {
            beforeStartPage(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            beforeMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayAll".equals(f.getName())) {
            beforeDisplayAll(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("QuestionRenderer".equals(f.getName())) {
            beforeQuestionRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ImageRenderer".equals(f.getName())) {
            beforeImageRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(f.getName())) {
            beforeSplashScreen(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayCongrats".equals(f.getName())) {
            beforeDisplayCongrats(f);
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void beforeInstructionForm(Form f) {
    }


    protected void beforeMoneyBagForm(Form f) {
    }


    protected void beforeStartPage(Form f) {
    }


    protected void beforeMain(Form f) {
    }


    protected void beforeDisplayAll(Form f) {
    }


    protected void beforeQuestionRenderer(Form f) {
    }


    protected void beforeImageRenderer(Form f) {
    }


    protected void beforeSplashScreen(Form f) {
    }


    protected void beforeDisplayCongrats(Form f) {
    }

    protected void beforeShowContainer(Container c) {
    aboutToShowThisContainer = c;
        if("InstructionForm".equals(c.getName())) {
            beforeContainerInstructionForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MoneyBagForm".equals(c.getName())) {
            beforeContainerMoneyBagForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("StartPage".equals(c.getName())) {
            beforeContainerStartPage(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            beforeContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayAll".equals(c.getName())) {
            beforeContainerDisplayAll(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("QuestionRenderer".equals(c.getName())) {
            beforeContainerQuestionRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("ImageRenderer".equals(c.getName())) {
            beforeContainerImageRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(c.getName())) {
            beforeContainerSplashScreen(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayCongrats".equals(c.getName())) {
            beforeContainerDisplayCongrats(c);
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void beforeContainerInstructionForm(Container c) {
    }


    protected void beforeContainerMoneyBagForm(Container c) {
    }


    protected void beforeContainerStartPage(Container c) {
    }


    protected void beforeContainerMain(Container c) {
    }


    protected void beforeContainerDisplayAll(Container c) {
    }


    protected void beforeContainerQuestionRenderer(Container c) {
    }


    protected void beforeContainerImageRenderer(Container c) {
    }


    protected void beforeContainerSplashScreen(Container c) {
    }


    protected void beforeContainerDisplayCongrats(Container c) {
    }

    protected void postShow(Form f) {
        if("InstructionForm".equals(f.getName())) {
            postInstructionForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MoneyBagForm".equals(f.getName())) {
            postMoneyBagForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("StartPage".equals(f.getName())) {
            postStartPage(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            postMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayAll".equals(f.getName())) {
            postDisplayAll(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("QuestionRenderer".equals(f.getName())) {
            postQuestionRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ImageRenderer".equals(f.getName())) {
            postImageRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(f.getName())) {
            postSplashScreen(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayCongrats".equals(f.getName())) {
            postDisplayCongrats(f);
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void postInstructionForm(Form f) {
    }


    protected void postMoneyBagForm(Form f) {
    }


    protected void postStartPage(Form f) {
    }


    protected void postMain(Form f) {
    }


    protected void postDisplayAll(Form f) {
    }


    protected void postQuestionRenderer(Form f) {
    }


    protected void postImageRenderer(Form f) {
    }


    protected void postSplashScreen(Form f) {
    }


    protected void postDisplayCongrats(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("InstructionForm".equals(c.getName())) {
            postContainerInstructionForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MoneyBagForm".equals(c.getName())) {
            postContainerMoneyBagForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("StartPage".equals(c.getName())) {
            postContainerStartPage(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            postContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayAll".equals(c.getName())) {
            postContainerDisplayAll(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("QuestionRenderer".equals(c.getName())) {
            postContainerQuestionRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("ImageRenderer".equals(c.getName())) {
            postContainerImageRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(c.getName())) {
            postContainerSplashScreen(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayCongrats".equals(c.getName())) {
            postContainerDisplayCongrats(c);
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void postContainerInstructionForm(Container c) {
    }


    protected void postContainerMoneyBagForm(Container c) {
    }


    protected void postContainerStartPage(Container c) {
    }


    protected void postContainerMain(Container c) {
    }


    protected void postContainerDisplayAll(Container c) {
    }


    protected void postContainerQuestionRenderer(Container c) {
    }


    protected void postContainerImageRenderer(Container c) {
    }


    protected void postContainerSplashScreen(Container c) {
    }


    protected void postContainerDisplayCongrats(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("InstructionForm".equals(rootName)) {
            onCreateInstructionForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("MoneyBagForm".equals(rootName)) {
            onCreateMoneyBagForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("StartPage".equals(rootName)) {
            onCreateStartPage();
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(rootName)) {
            onCreateMain();
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayAll".equals(rootName)) {
            onCreateDisplayAll();
            aboutToShowThisContainer = null;
            return;
        }

        if("QuestionRenderer".equals(rootName)) {
            onCreateQuestionRenderer();
            aboutToShowThisContainer = null;
            return;
        }

        if("ImageRenderer".equals(rootName)) {
            onCreateImageRenderer();
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(rootName)) {
            onCreateSplashScreen();
            aboutToShowThisContainer = null;
            return;
        }

        if("DisplayCongrats".equals(rootName)) {
            onCreateDisplayCongrats();
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void onCreateInstructionForm() {
    }


    protected void onCreateMoneyBagForm() {
    }


    protected void onCreateStartPage() {
    }


    protected void onCreateMain() {
    }


    protected void onCreateDisplayAll() {
    }


    protected void onCreateQuestionRenderer() {
    }


    protected void onCreateImageRenderer() {
    }


    protected void onCreateSplashScreen() {
    }


    protected void onCreateDisplayCongrats() {
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("InstructionForm")) {
            if("TextArea".equals(c.getName())) {
                onInstructionForm_TextAreaAction(c, event);
                return;
            }
            if("previousButton".equals(c.getName())) {
                onInstructionForm_PreviousButtonAction(c, event);
                return;
            }
            if("nextInstructionButton".equals(c.getName())) {
                onInstructionForm_NextInstructionButtonAction(c, event);
                return;
            }
            if("Button".equals(c.getName())) {
                onInstructionForm_ButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("MoneyBagForm")) {
            if("moneyBagQ".equals(c.getName())) {
                onMoneyBagForm_MoneyBagQAction(c, event);
                return;
            }
            if("optARBMB".equals(c.getName())) {
                onMoneyBagForm_OptARBMBAction(c, event);
                return;
            }
            if("optBRBMB".equals(c.getName())) {
                onMoneyBagForm_OptBRBMBAction(c, event);
                return;
            }
            if("optCRBMB".equals(c.getName())) {
                onMoneyBagForm_OptCRBMBAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("StartPage")) {
            if("startbutton".equals(c.getName())) {
                onStartPage_StartbuttonAction(c, event);
                return;
            }
            if("InstructionButton".equals(c.getName())) {
                onStartPage_InstructionButtonAction(c, event);
                return;
            }
            if("exitButton".equals(c.getName())) {
                onStartPage_ExitButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("QuestionRenderer")) {
            if("questionArea".equals(c.getName())) {
                onQuestionRenderer_QuestionAreaAction(c, event);
                return;
            }
            if("optionARB".equals(c.getName())) {
                onQuestionRenderer_OptionARBAction(c, event);
                return;
            }
            if("optionBRB".equals(c.getName())) {
                onQuestionRenderer_OptionBRBAction(c, event);
                return;
            }
            if("optionCRB".equals(c.getName())) {
                onQuestionRenderer_OptionCRBAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("ImageRenderer")) {
            if("imageButton".equals(c.getName())) {
                onImageRenderer_ImageButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("DisplayCongrats")) {
            if("TextArea".equals(c.getName())) {
                onDisplayCongrats_TextAreaAction(c, event);
                return;
            }
            if("okButton".equals(c.getName())) {
                onDisplayCongrats_OkButtonAction(c, event);
                return;
            }
            if("dismissButton".equals(c.getName())) {
                onDisplayCongrats_DismissButtonAction(c, event);
                return;
            }
        }
    }

      protected void onInstructionForm_TextAreaAction(Component c, ActionEvent event) {
      }

      protected void onInstructionForm_PreviousButtonAction(Component c, ActionEvent event) {
      }

      protected void onInstructionForm_NextInstructionButtonAction(Component c, ActionEvent event) {
      }

      protected void onInstructionForm_ButtonAction(Component c, ActionEvent event) {
      }

      protected void onMoneyBagForm_MoneyBagQAction(Component c, ActionEvent event) {
      }

      protected void onMoneyBagForm_OptARBMBAction(Component c, ActionEvent event) {
      }

      protected void onMoneyBagForm_OptBRBMBAction(Component c, ActionEvent event) {
      }

      protected void onMoneyBagForm_OptCRBMBAction(Component c, ActionEvent event) {
      }

      protected void onStartPage_StartbuttonAction(Component c, ActionEvent event) {
      }

      protected void onStartPage_InstructionButtonAction(Component c, ActionEvent event) {
      }

      protected void onStartPage_ExitButtonAction(Component c, ActionEvent event) {
      }

      protected void onQuestionRenderer_QuestionAreaAction(Component c, ActionEvent event) {
      }

      protected void onQuestionRenderer_OptionARBAction(Component c, ActionEvent event) {
      }

      protected void onQuestionRenderer_OptionBRBAction(Component c, ActionEvent event) {
      }

      protected void onQuestionRenderer_OptionCRBAction(Component c, ActionEvent event) {
      }

      protected void onImageRenderer_ImageButtonAction(Component c, ActionEvent event) {
      }

      protected void onDisplayCongrats_TextAreaAction(Component c, ActionEvent event) {
      }

      protected void onDisplayCongrats_OkButtonAction(Component c, ActionEvent event) {
      }

      protected void onDisplayCongrats_DismissButtonAction(Component c, ActionEvent event) {
      }

}
