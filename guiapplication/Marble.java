/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiapplication;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;

/**
 *
 * @author yzczr8
 */
public class Marble {
    protected String color;
    protected int width, height;
    protected int xPos, yPos;
    protected Image image;
    private String path = "src/assets/";
    protected String[] marbleType = {
        "Blue", 
        "Red", 
        "Green",
        "Orange",
        "Yellow",
        "Purple",
        "Rainbow"
    };
    
    public Marble() {
        Random num = new Random();
        
        width = 20;
        height = 20;
        //ii.getImage().getScaledInstance(imgWidth,imgHeight,Image.SCALE_DEFAULT)
        //ImageIcon ii = new ImageIcon(path + marbleType[num.nextInt(marbleType.length)] + "Marble.png");
        ImageIcon ii = new ImageIcon(getClass().getResource("/assets/" + marbleType[num.nextInt(marbleType.length)] + "Marble.png"));
        this.image = ii.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
    }
    
    public void setX(int pos) {
        xPos = pos;
    }
    public void setY(int pos) {
        yPos = pos;
    }
    
    public Image getImage() {
        return image;
    }
    
    public int getX() {
        return xPos;
    }
    public int getY() {
        return yPos;
    }
}
