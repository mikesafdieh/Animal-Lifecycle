/**
 * This is the Animal Class
 * @author Michael Safdieh
 * @since JDK 1.7
 * @version 1.0
 * Created on 5/1/14.
 */
public class Animal
{
    private int energy;
    private final int maxEnergy = 1000;
    private int age;
    private final int maxAge = 18;
    private int thirstLevel;
    private final int maxThirstLevel = 20;

    private int x;
    private int y;

    private int babyLocationX;
    private int babyLocationY;

    /**
     * Constructor of the Animal class. Initializes the animal's age to 0.
     * Also initializes the animal's energy and thirst level to random values.
     */
    public Animal()
    {
        age = 0;
        energy = (maxEnergy / 2);
        int alpha = maxEnergy / 10;
        double rand = Math.random();
        energy = (int) ((energy - alpha) + rand * 2 * alpha);

        thirstLevel = (maxThirstLevel / 5);
        int beta = (maxThirstLevel / 10);
        thirstLevel = (int) ((thirstLevel - beta) + rand * 2 * beta);
    }

    /**
     * If the animal is able to, causes the animal to move one
     * space up, down, left, or right, and returns true. Otherwise,
     * it returns false.
     * @return isMoved
     */
    public boolean move(int earth[][], int x, int y)
    {
        boolean isMoved = false;
        int tries = 0;
        int direction = (int) (Math.random()*4 + 1); // 1 is up, 2 is right, 3 is down, 4 is left.

        while(!isMoved && tries < 4)
        {
            if (direction % 4 == 0 && y-1 >= 0 && earth[x][y-1] == 0) //left
            {
                this.y = y-1;
                earth[x][y] = 0;
                isMoved = true;
            }
            else if (direction % 4 == 2 && y+1 <= 15 && earth[x][y+1] == 0 ) //right
            {
                this.y = y+1;
                earth[x][y] = 0;
                isMoved = true;
            }
            else if (direction % 4 == 1 && x-1 >= 0 && earth[x-1][y] == 0) //up
            {
                this.x = x-1;
                earth[x][y] = 0;
                isMoved = true;
            }
            else if (direction % 4 == 3 && x+1 <= 15 && earth[x+1][y] == 0)  //down
            {
                this.x = x+1;
                earth[x][y] = 0;
                isMoved = true;
            }

            tries++;
            direction++;
        }

        return isMoved;
    }

    /**
     * Checks if the animal is a baby or an adult and sets
     * its value accordingly ( -2 = adult animal, -5 = baby animal).
     */
    public void checkAge(int[][] earth)
    {
        if (age < 5)
            earth[x][y] = -5;
        else if (age >= 5 )
            earth[x][y] = -2;
    }

    /**
     * Causes the animal to reproduce a new baby animal in a nearby
     * location.
     * @return animals[numAnimals]
     */
    public Animal reproduce(int[][] earth, Animal[] animals, int numAnimals)
    {
        earth[babyLocationX][babyLocationY] = -5;
        animals[numAnimals] = new Animal();
        animals[numAnimals].x = babyLocationX;
        animals[numAnimals].y = babyLocationY;

        this.energy = energy - maxEnergy/5;

        return animals[numAnimals];
    }

    /**
     * Causes the lion to reproduce a new baby lion in a nearby
     * location.
     * @return lions[numLions]
     */
    public Lion reproduceLion(int[][] earth, Lion[] lions, int numLions)
    {
        earth[babyLocationX][babyLocationY] = -8;
        lions[numLions] = new Lion();
        lions[numLions].setX(babyLocationX);
        lions[numLions].setY(babyLocationY);

        this.energy = energy - maxEnergy/5;

        return lions[numLions];
    }

    /**
     * Causes the deer to reproduce a new baby deer in a nearby
     * location.
     * @return deers[numDeers]
     */
    public Deer reproduceDeer(int[][] earth, Deer[] deers, int numDeers)
    {
        earth[babyLocationX][babyLocationY] = -9;
        deers[numDeers] = new Deer();
        deers[numDeers].setX(babyLocationX);
        deers[numDeers].setY(babyLocationY);

        this.energy = energy - maxEnergy/5;

        return deers[numDeers];
    }

    /**
     * Checks if the animal is near an empty location so it can reproduce
     * a new baby animal. If there is an empty location, the function returns
     * true and stores the location for the new baby animal.
     * @return true or false
     */
    public boolean isNearEmpty (int[][] earth, int x, int y)
    {
        babyLocationX = -1;
        babyLocationY = -1;

        if (y-1 >= 0 && earth[x][y-1] == 0) //left
        {
            babyLocationX = x;
            babyLocationY = y-1;
            return true;
        }
        else if (y+1 <= 15 && earth[x][y+1] == 0) //right
        {
            babyLocationX = x;
            babyLocationY = y+1;
            return true;
        }
        else if (x-1 >= 0 && earth[x-1][y] == 0) //up
        {
            babyLocationX = x-1;
            babyLocationY = y;
            return true;
        }
        else if (x+1 <= 15 && earth[x+1][y] == 0)  //down
        {
            babyLocationX = x+1;
            babyLocationY = y;
            return true;
        }
        else if (x-1 >= 0 && y-1 >= 0 && earth[x-1][y-1] == 0) //top left
        {
            babyLocationX = x-1;
            babyLocationY = y-1;
            return true;
        }
        else if (x-1 >= 0 && y+1 <= 15 && earth[x-1][y+1] == 0) //top right
        {
            babyLocationX = x-1;
            babyLocationY = y+1;
            return true;
        }
        else if (x+1 <= 15 && y+1 <= 15 && earth[x+1][y+1] == 0) //bottom right
        {
            babyLocationX = x+1;
            babyLocationY = y+1;
            return true;
        }
        else if (x+1 <= 15 && y-1 >= 0 && earth[x+1][y-1] == 0)  //bottom left
        {
            babyLocationX = x+1;
            babyLocationY = y-1;
            return true;
        }

        return false;
    }

    /**
     * Checks if the animal is dead by checking its age, energy and thirst level.
     * If the animal's age or thirst level is too high, or if its energy is too
     * low, the function returns true. Otherwise, it returns false.
     * @return true or false
     */
    public boolean isDead()
    {
        return this.age > maxAge || this.energy <= 0 || this.thirstLevel > maxThirstLevel;
    }

    /**
     * Kills the animal by setting its energy, age, and thirst level all to -1,
     * and also sets the animal's location to an empty space.
     */
    public void die(int[][] earth, int x, int y)
    {
        earth[x][y] = 0;
        this.energy = -1;
        this.age = -1;
        this.thirstLevel = -1;
    }

    /**
     * If the animal's energy is below a certain amount, the animal will eat a
     * nearby plant, and the function returns true. Otherwise the function returns
     * false.
     * @return true or false
     */
    public boolean eatPlant(int[][] earth, int x, int y, Plant[] plants, int numPlants)
    {
        if (this.energy < maxEnergy)
        {
            if (isNearPlant(earth, x, y, plants, numPlants))
            {
                energy = energy + maxEnergy/10;

                if (energy > maxEnergy)
                    energy = maxEnergy;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the animal is near a plant location. If it is, the function
     * returns true, sets the plant location to an empty space, and kills the plant.
     * Otherwise the function returns false.
     * @return true or false
     */
    public boolean isNearPlant(int[][] earth, int x, int y, Plant[] plants, int numPlants)
    {
        int plantLocationX = -1;
        int plantLocationY = -1;

        if (y-1 >= 0 && earth[x][y-1] == -1) //left
        {
            plantLocationX = x;
            plantLocationY = y-1;
        }
        else if (y+1 <= 15 && earth[x][y+1] == -1) //right
        {
            plantLocationX = x;
            plantLocationY = y+1;
        }
        else if (x-1 >= 0 && earth[x-1][y] == -1) //up
        {
            plantLocationX = x-1;
            plantLocationY = y;
        }
        else if (x+1 <= 15 && earth[x+1][y] == -1)  //down
        {
            plantLocationX = x+1;
            plantLocationY = y;
        }
        else if (x-1 >= 0 && y-1 >= 0 && earth[x-1][y-1] == -1) //top left
        {
            plantLocationX = x-1;
            plantLocationY = y-1;
        }
        else if (x-1 >= 0 && y+1 <= 15 && earth[x-1][y+1] == -1) //top right
        {
            plantLocationX = x-1;
            plantLocationY = y+1;
        }
        else if (x+1 <= 15 && y+1 <= 15 && earth[x+1][y+1] == -1) //bottom right
        {
            plantLocationX = x+1;
            plantLocationY = y+1;
        }
        else if (x+1 <= 15 && y-1 >= 0 && earth[x+1][y-1] == -1)  //bottom left
        {
            plantLocationX = x+1;
            plantLocationY = y-1;
        }

        if (plantLocationX >= 0 && plantLocationY >= 0)
        {
            /** This for loop makes sure the eaten plant dies */
            for (int i = 0; i < numPlants; i++)
            {
                if (plants[i].getX() == plantLocationX && plants[i].getY() == plantLocationY)
                {
                    plants[i].die(earth, plantLocationX, plantLocationY);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * If the animal's thirst level is high enough and the animal is near water,
     * the animal will drink the water, and its thirst level will be set to 0.
     */
    public void drinkWater(int[][] earth, int x, int y)
    {
        if (this.thirstLevel >= (maxThirstLevel/4) && isNearWater(earth, x, y) )
        {
            this.thirstLevel = 0;
        }
    }

    /**
     * If the animal is near a water location, the function will return true, and
     * if it's not, the function will return false.
     * @return true or false
     */
    public boolean isNearWater(int[][] earth, int x, int y)
    {

        if (y-1 >= 0 && earth[x][y-1] == -4) //left
        {
            return true;
        }
        else if (y+1 <= 15 && earth[x][y+1] == -4) //right
        {
            return true;
        }
        else if (x-1 >= 0 && earth[x-1][y] == -4) //up
        {
            return true;
        }
        else if (x+1 <= 15 && earth[x+1][y] == -4)  //down
        {
            return true;
        }
        else if (x-1 >= 0 && y-1 >= 0 && earth[x-1][y-1] == -4) //top left
        {
            return true;
        }
        else if (x-1 >= 0 && y+1 <= 15 && earth[x-1][y+1] == -4) //top right
        {
            return true;
        }
        else if (x+1 <= 15 && y+1 <= 15 && earth[x+1][y+1] == -4) //bottom right
        {
            return true;
        }
        else if (x+1 <= 15 && y-1 >= 0 && earth[x+1][y-1] == -4)  //bottom left
        {
            return true;
        }

        return false;
    }

    /**
     * Gives the energy of the animal.
     * @return energy
     */
    public int getEnergy()
    {
        return energy;
    }

    /**
     * Sets the energy of the animal to the specified amount.
     */
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    /**
     * Gives the thirst level of the animal.
     * @return thirstLevel
     */
    public int getThirstLevel()
    {
        return thirstLevel;
    }

    /**
     * Sets the thirst level of the animal to the specified amount.
     */
    public void setThirstLevel(int thirstLevel)
    {
        this.thirstLevel = thirstLevel;
    }

    /**
     * Gives the age of the animal.
     * @return age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Sets the age of the animal to the specified amount.
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Gives the x location of the animal.
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Sets the x location of the animal to the specified location.
     */
    public void setX(int newX)
    {
        this.x = newX;
    }

    /**
     * Gives the y location of the animal.
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * Sets the y location of the animal to the specified location.
     */
    public void setY(int newY)
    {
        this.y = newY;
    }

    /**
     * Gives the max energy of the animal.
     * @return maxEnergy
     */
    public int getMaxEnergy()
    {
        return maxEnergy;
    }

    /**
     * Gives the max age of the animal.
     * @return maxAge
     */
    public int getMaxAge()
    {
        return maxAge;
    }

}
