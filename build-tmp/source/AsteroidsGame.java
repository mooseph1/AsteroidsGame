import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AsteroidsGame extends PApplet {

SpaceShip zoom;
Star [] dots;
boolean aPressed = false;
boolean dPressed = false;
boolean wPressed = false;
boolean sPressed = false;
boolean spacePressed = false;
public void setup() 
{
  size(500,500);
  background(0);
  zoom = new SpaceShip();
  dots = new Star[100];
  for(int i = 0; i < dots.length; i++)
  {
    dots[i] = new Star();
  }
}
public void draw() 
{
  background(0);
  for(int i = 0; i < dots.length; i++)
  {
    dots[i].show();
  }  
  zoom.move();
  zoom.show();
  if(aPressed == true) {zoom.rotate(-5);}
  if(dPressed == true) {zoom.rotate(5);}
  if(wPressed == true) {zoom.accelerate(0.1f);}
  if(sPressed == true) {zoom.accelerate(-0.1f);}
  if(spacePressed == true)
    {
      zoom.setDirectionX(0);
      zoom.setDirectionY(0);
      zoom.setX((int)(Math.random()*501));
      zoom.setY((int)(Math.random()*501));
      zoom.setPointDirection((int)(Math.random()*361));
      spacePressed = false;
    }
}
public void keyPressed()
{
  if(key == 'a') {aPressed = true;}
  if(key == 'd') {dPressed = true;}
  if(key == 'w') {wPressed = true;}
  if(key == 's') {sPressed = true;}
  if(key == ' ') {spacePressed = true;}
}
public void keyReleased()
{
  if(key == 'a') {aPressed = false;}
  if(key == 'd') {dPressed = false;}
  if(key == 'w') {wPressed = false;}
  if(key == 's') {sPressed = false;}
  if(key == ' ') {spacePressed = false;}
}
class SpaceShip extends Floater  
{
  SpaceShip()
  {
    corners = 3;
    xCorners = new int[corners];   
    yCorners = new int[corners];
    xCorners[0] = -12;   
    yCorners[0] = 8;
    xCorners[1] = -12;   
    yCorners[1] = -8;
    xCorners[2] = 12;   
    yCorners[2] = 0;
    myColor = 255;   
    myCenterX = 250;
    myCenterY = 250; 
    myDirectionX = 0;
    myDirectionY = 0; //holds x and y coordinates of the vector for direction of travel   
    myPointDirection = 0; //holds current direction the ship is pointing in degrees    
  }
  public void setX(int x) {myCenterX = x;}
  public int getX() {return (int)myCenterX;}
  public void setY(int y) {myCenterY = y;}
  public int getY() {return (int)myCenterY;}
  public void setDirectionX(double x) {myDirectionX = x;}
  public double getDirectionX() {return myDirectionX;}
  public void setDirectionY(double y) {myDirectionY = y;}
  public double getDirectionY() {return myDirectionY;}
  public void setPointDirection(int degrees) {myPointDirection = degrees;}
  public double getPointDirection() {return myPointDirection;}
}


class Star
{
  private int starX, starY, starSize, starColor1, starColor2, starColor3;
  Star()
  {
    starX = (int)(Math.random()*500);
    starY = (int)(Math.random()*500);
    starSize = (int)(Math.random()*6);
    starColor1 = (int)(Math.random()*255);
    starColor2 = (int)(Math.random()*255);
    starColor3 = (int)(Math.random()*255);
  }
  public void show()
  {
    noStroke();
    fill(starColor1, starColor2, starColor3);
    ellipse(starX, starY, starSize, starSize);
  }
}


abstract class Floater
{   
  protected int corners;  //the number of corners, a triangular floater has 3   
  protected int[] xCorners;   
  protected int[] yCorners;   
  protected int myColor;   
  protected double myCenterX, myCenterY; //holds center coordinates   
  protected double myDirectionX, myDirectionY; //holds x and y coordinates of the vector for direction of travel   
  protected double myPointDirection; //holds current direction the ship is pointing in degrees    
  abstract public void setX(int x);  
  abstract public int getX();   
  abstract public void setY(int y);   
  abstract public int getY();   
  abstract public void setDirectionX(double x);   
  abstract public double getDirectionX();   
  abstract public void setDirectionY(double y);   
  abstract public double getDirectionY();   
  abstract public void setPointDirection(int degrees);   
  abstract public double getPointDirection(); 

  //Accelerates the floater in the direction it is pointing (myPointDirection)   
  public void accelerate (double dAmount)   
  {          
    //convert the current direction the floater is pointing to radians    
    double dRadians =myPointDirection*(Math.PI/180);     
    //change coordinates of direction of travel    
    myDirectionX += ((dAmount) * Math.cos(dRadians));    
    myDirectionY += ((dAmount) * Math.sin(dRadians));       
  }   
  public void rotate (int nDegreesOfRotation)   
  {     
    //rotates the floater by a given number of degrees    
    myPointDirection+=nDegreesOfRotation;   
  }   
  public void move ()   //move the floater in the current direction of travel
  {      
    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX += myDirectionX;    
    myCenterY += myDirectionY;     

    //wrap around screen    
    if(myCenterX >width)
    {     
      myCenterX = 0;    
    }    
    else if (myCenterX<0)
    {     
      myCenterX = width;    
    }    
    if(myCenterY >height)
    {    
      myCenterY = 0;    
    }   
    else if (myCenterY < 0)
    {     
      myCenterY = height;    
    }   
  }   
  public void show ()  //Draws the floater at the current position  
  {             
    fill(myColor);   
    stroke(myColor);    
    //convert degrees to radians for sin and cos         
    double dRadians = myPointDirection*(Math.PI/180);                 
    int xRotatedTranslated, yRotatedTranslated;    
    beginShape();         
    for(int nI = 0; nI < corners; nI++)    
    {     
      //rotate and translate the coordinates of the floater using current direction 
      xRotatedTranslated = (int)((xCorners[nI]* Math.cos(dRadians)) - (yCorners[nI] * Math.sin(dRadians))+myCenterX);     
      yRotatedTranslated = (int)((xCorners[nI]* Math.sin(dRadians)) + (yCorners[nI] * Math.cos(dRadians))+myCenterY);      
      vertex(xRotatedTranslated,yRotatedTranslated);    
    }   
    endShape(CLOSE);  
  }   
}





  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AsteroidsGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
