/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtechcomm.portinggame;

import com.codename1.ui.Image;

/**
 *
 * @author LANREWAJU
 */
public class Questions {
    private String id;
    private String questions;
    private String optionA;
    private String optionB;
    private String optionC;
    private String correctAnswer;
    private Image image;
    private Image largeImage;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the questions
     */
    public String getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(String questions) {
        this.questions = questions;
    }

    /**
     * @return the optionA
     */
    public String getOptionA() {
        return optionA;
    }

    /**
     * @param optionA the optionA to set
     */
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    /**
     * @return the optionB
     */
    public String getOptionB() {
        return optionB;
    }

    /**
     * @param optionB the optionB to set
     */
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }
 public String getOptionC() {
        return optionC;
    }

    /**
     * @param optionB the optionB to set
     */
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    /**
     * @return the correctAnswer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * @param correctAnswer the correctAnswer to set
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
    
    public Questions(String id, String questions,String optionA, String optionB, String optionC, String correctAnswer, Image image, Image large){
    this.id = id;
    this.questions = questions;
    this.optionA = optionA;
    this.optionB = optionB;
    this.optionC = optionC;
    this.correctAnswer = correctAnswer;
    this.image = image;
    this.largeImage = large;
}

    /**
     * @return the largeImage
     */
    public Image getLargeImage() {
        return largeImage;
    }

    /**
     * @param largeImage the largeImage to set
     */
    public void setLargeImage(Image largeImage) {
        this.largeImage = largeImage;
    }
}
