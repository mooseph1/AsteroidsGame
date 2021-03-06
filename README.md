Asteroids (Part 1)
==================
In this assignment we will start to replicate the old video game Asteroids. You will write a program that has a space ship that can be controlled with the keyboard. You will need to write a `SpaceShip` class. Your `SpaceShip` class will extend the `Floater` class, an abstract class that represents all things that float in space. 

An abstract class is an "incomplete" class. It has abstract methods--methods that have no body. When your class extends an abstract class, you must write the methods that are needed to "complete" the class. _Note: To complete this assignment you will be writing two classes `SpaceShip` and `Star`. Do not modify the `Floater` class._

Suggested steps to complete this assignment
-------------------------------------------

1. Fork and clone down the AsteroidsGame repository.
2. Uncomment the `extends floater` on Line 10
3. Complete the 10 abstract `set` and `get` functions
4. Write the `Spaceship` constructor. Make sure you initialize all 9 of the inherited `protected` member variables. You may find the [Asteroids PowerPoint](https://drive.google.com/file/d/0Bz2ZkT6qWPYTYjU0NDE5ZDYtYzEwOS00MGNlLTk0OGMtODBhODI3N2JiYzRi/view?usp=sharing) slides to be helpful. You may also find [this sample SpaceShip program](https://56d4b6566b56a59e1f634ea30f548666c459899d.googledrive.com/host/0Bz2ZkT6qWPYTallTVFJBOWdNcDQ/) to be helpful in understanding how the variables affect the SpaceShip's movement.
5. At the top of your program, declare a variable of type `SpaceShip`
6. Initialize the `SpaceShip` as a new instance of the class
7. In `draw()` call the SpaceShip's `show()` function
8. When you are happy with appearance of the SpaceShip, add a `public void keyPressed()` function to your program
9. Write code in `keyPressed` that allows you to control the space ship with the keyboard. You must include the ability to rotate left, rotate right, accelerate, and enter "hyperspace." (There is no requirement for any fancy visual effects, hyperspace just needs to stop the ship, and give it a new random position and direction.)
10. Add code to `draw()` to `move()` the Spaceship
11. Finally, add a `Star` class that creates a random number of stars in random positions
12. If you have extra time and are looking for a challenge, you might try to add an animation of "rockets" that appear from the back of the ship when you accelerate

These steps are only a suggestion. Your Asteroids game doesn't have to work or act like any other. Have fun and be creative.




Asteroids Part 2: An Array of Asteroids

Now that we have a functioning space ship, we'll add some Asteroids to our game. We'll write an Asteroid class that extends Floater.
Steps to completing this assignment
Write an Asteroid class that extends Floater. Make sure it's not inside the curly braces of any other class. You will need to
write a constructor
write the code to "finish" the abstract methods in the Floater class
add a new int member variable to hold the speed of rotation for each asteroid. Make sure that this is initialized to have an equal probablility of being positive or negative. Also make sure to declare appropriately (should it be public or private?)
"override" the move method of the Floater class by writing a new move method in the Asteroid class that also rotates each Asteroid at its own speed
Now add just a single asteroid to your applet. Start by just drawing the asteroid. Make sure you can see it and are happy with its shape before going to the next step.
Now add the code that moves and rotates the Asteroid
Modify your applet code so that you have an array of Asteroids.
You will need a web page to display your applet. Your homepage should have a link to the web page for this assignment.
Extensions
If you have extra time, you might try to figure out how to check to see if the ship has collided with an asteroid.
Submit the url of your github webpage with the URL for the assignment via the school loop drop box.




Asteroids Part 3: An ArrayList of Asteroids
Background: ArrayList
An array probably isn't the best way to keep track of a bunch of asteroids. Arrays have fixed size. You can't easily add or remove asteroids from an array. A better choice might be an ArrayList. TheArrayList class has a number of useful member methods:
boolean add(Object x)
void add(int index, Object element)
Object get(int index)
Object remove(int index)
Object set(int index, Object x)
int size()
Steps to completing this assignment
Modify it so that you have an ArrayList of Asteroids.
Now we'll modify the program so that when our space ship strikes an asteroid, the asteroid is removed from the ArrayList
Everytime an asteroid moves find the distance between that asteroid and the ship.
Use processing's dist() function to find the distance between that asteroid and the ship.
If the distance is less than 20 remove the asteroid from the ArrayList.
Otherwise, move and rotate the asteroid normally




To finish our Asteroids game, we need to write a new class that represents Bullets. We will store the Bullets in an ArrayList much like we did with the Asteroids. Once we can shoot the bullets and destroy Asteroids, we will have a working game. Your Asteroids game doesn't have to look like any other. Feel free to modify it in any way you wish.
Suggested steps to completing this assignment
Write a Bullet class that extends Floater. You will need to:
Write a constructor that takes one ship argument: Bullet(SpaceShip theShip)
Intialize myCenterX and myCenterY of the bullet to be the same as the ship.
Initialize myPointDirection of the bullet to be the same as myPointDirection of the ship
convert myPointDirection to radians with the following code: double dRadians =myPointDirection*(Math.PI/180);
Initialize myDirectionX as 5 * Math.cos(dRadians) + the myDirectionX of the ship
Initialize myDirectionY as 5 * Math.sin(dRadians) + the myDirectionY of the ship
Override the show method of the Floater class so that you can use circular bullets
Now, add just one bullet to your program. First, just draw it to the screen. Make sure you can see it before continuing to the next step.
Now, move the bullet.
Now create an ArrayList of Bullets. The list should be empty to start with. Everytime you press the key to "shoot", add a new Bullet to the ArrayList. Modify the program with loops that draw and move all the bullets in the ArrayList
One way to check for collisions between the bullets and the Asteroids is to write a loop within a loop (see below for another way). Everytime you move one asteroid you will need:
a loop that goes through all the bullets to see if there is a collision between that bullet and the asteroid
if there is a collision remove both the asteroid and the bullet from their ArrayLists
Alternatively, you might be able to use processing's get() to check for collisions.
(Note: I'm not sure if this is still true in the current version of Processing)If your finished program is running slowly, try changing size() to use P2D. For example, size(600,600,P2D); creates an applet 600 x 600 that uses processing's fast 2D renderer (which is not as accurate as the default renderer).