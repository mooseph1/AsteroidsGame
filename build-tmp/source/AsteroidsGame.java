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
ArrayList <Asteroid> asteroids;


boolean aPressed = false;
boolean dPressed = false;
boolean wPressed = false;
boolean sPressed = false;
boolean spacePressed = false;


double distance;


boolean gameOver = false;


public void setup() 
{
  size(500,500);
  background(0);
  
  zoom = new SpaceShip();
  
  asteroids = new ArrayList <Asteroid>();
  for(int a = 0; a < 10; a++)
  {
    asteroids.add(new Asteroid());
  }
  
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

  for(int a = 0; a < asteroids.size(); a++)
  {
    asteroids.get(a).move();
    asteroids.get(a).show();
    distance = dist(asteroids.get(a).getX(), asteroids.get(a).getY(), zoom.getX(), zoom.getY());
    if(distance < 28)
    {
      asteroids.remove(a);
      gameOver = true;
    }
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

  if(gameOver == true)
  {
    for(int i = 0; i < asteroids.size(); i++)
    {
      asteroids.get(i).setDirectionX(0);
      asteroids.get(i).setDirectionY(0);
      zoom.setDirectionX(0);
      zoom.setDirectionY(0);
      fill(0, 126, 126);
      textSize(30);
      textAlign(CENTER);
      text("Game Over", 250, 250);
    }
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
    int [] xC = {-12, -12, 12};
    int [] yC = {8, -8, 0};
    xCorners = xC;
    yCorners = yC;
    myColor = 255;
    myCenterX = 250;
    myCenterY = 250;
    myDirectionX = 0;
    myDirectionY = 0;
    myPointDirection = 0;
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


class Asteroid extends Floater
{
  private int rotSpeed;
  Asteroid()
  {
    corners = 8;
    int [] xC = {0, -10, -14, -10, 0, 10, 14, 10};
    int [] yC = {-14, -10, 0, 10, 14, 10, 0, -10};
    xCorners = xC;
    yCorners = yC;
    myColor = 127;
    myCenterX = (int)(Math.random()*500);
    myCenterY = (int)(Math.random()*500);
    myDirectionX = (int)(Math.random()*4-2);
    myDirectionY = (int)(Math.random()*4-2);
    myPointDirection = (int)(Math.random()*361);

    // if(Math.random()>.5)
    // {
    //   myDirectionX = (int)(Math.random()*1+1);
    // }
    // else
    // {
    //   myDirectionX = (int)(Math.random()*1-2);
    // }

    // if(Math.random()>.5)
    // {
    //   myDirectionY = (int)(Math.random()*1+1);
    // }
    // else
    // {
    //   myDirectionY = (int)(Math.random()*1-2);
    // }

    if(Math.random()<.5f)
    {
      rotSpeed = (int)(Math.random()*2-3);
    }
    else
    {
      rotSpeed = (int)(Math.random()*2+1);
    }

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
  public void move()
  {
    rotate(rotSpeed);
    super.move();
  }
}


abstract class Floater
{
  protected int corners;
  protected int[] xCorners;
  protected int[] yCorners;
  protected int myColor;
  protected double myCenterX, myCenterY;
  protected double myDirectionX, myDirectionY;
  protected double myPointDirection;
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
