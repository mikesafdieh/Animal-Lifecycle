/**
 * This is the Plant Class
 * @author Michael Safdieh
 * @since JDK 1.7
 * @version 1.0
 * Created on 5/1/14.
 */
public class Plant
{
    private int energy;
    private final int maxEnergy = 100;
    private int age;
    private final int maxAge = 22;

    private int x;
    private int y;

    private int newPlantLocationX;
    private int newPlantLocationY;

    /**
     * Constructor for the plant class. Initializes the plant's age to 0,
     * and its energy to a random number.
     */
    public Plant()
    {
        age = 0;
        energy = (maxEnergy / 2);
        int alpha = maxEnergy / 10;
        double rand = Math.random();
        energy = (int) ((energy - alpha) + rand * 2 * alpha);
    }

    /**
     * Causes the plant to reproduce another plant in a nearby location.
     * @return plants[numPlants]
     */
    public Plant reproduce(int[][] earth, Plant[] plants, int numPlants)
    {
        earth[newPlantLocationX][newPlantLocationY] = -1;
        plants[numPlants] = new Plant();
        plants[numPlants].x = newPlantLocationX;
        plants[numPlants].y = newPlantLocationY;

        this.energy = energy - maxEnergy/10;

        return plants[numPlants];
    }


    /**
     * Checks if the plant is near an empty location so it can reproduce
     * a new plant. If there is an empty location, the function returns
     * that location for the new plant
     * @return newPlantLocation
     */
    public boolean isNearEmpty (int[][] earth, int x, int y)
    {
        newPlantLocationX = -1;
        newPlantLocationY = -1;

        if (y-1 >= 0 && earth[x][y-1] == 0) //left
        {
            newPlantLocationX = x;
            newPlantLocationY = y-1;
            return true;
        }
        else if (y+1 <= 15 && earth[x][y+1] == 0 ) //right
        {
            newPlantLocationX = x;
            newPlantLocationY = y+1;
            return true;
        }
        else if (x-1 >= 0 && earth[x-1][y] == 0) //up
        {
            newPlantLocationX = x-1;
            newPlantLocationY = y;
            return true;
        }
        else if (x+1 <= 15 && earth[x+1][y] == 0)  //down
        {
            newPlantLocationX = x+1;
            newPlantLocationY = y;
            return true;
        }
        else if (x-1 >= 0 && y-1 >= 0 && earth[x-1][y-1] == 0) //top left
        {
            newPlantLocationX = x-1;
            newPlantLocationY = y-1;
            return true;
        }
        else if (x-1 >= 0 && y+1 <= 15 && earth[x-1][y+1] == 0 ) //top right
        {
            newPlantLocationX = x-1;
            newPlantLocationY = y+1;
            return true;
        }
        else if (x+1 <= 15 && y+1 <= 15 && earth[x+1][y+1] == 0) //bottom right
        {
            newPlantLocationX = x+1;
            newPlantLocationY = y+1;
            return true;
        }
        else if (x+1 <= 15 && y-1 >= 0 && earth[x+1][y-1] == 0)  //bottom left
        {
            newPlantLocationX = x+1;
            newPlantLocationY = y-1;
            return true;
        }

        return false;
    }


    /**
     * Checks if the plant is dead by checking its age, and energy.
     * If the animal's age is too high, or if its energy is too
     * low, the function returns true. Otherwise, it returns false.
     * @return true or false
     */
    public boolean isDead()
    {
        return this.age > maxAge || this.energy <= 0;
    }

    /**
     * Kills the plant by setting its energy and age to -1,
     * and also sets the plant's location to an empty space.
     */
    public void die(int[][] earth, int x, int y)
    {
        earth[x][y] = 0;
        this.energy = -1;
    }

    /**
     * Gives the x location of the plant.
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Sets the x location of the plant to the specified location.
     */
    public void setX(int newX)
    {
        this.x = newX;
    }

    /**
     * Gives the y location of the plant.
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * Sets the y location of the plant to the specified location.
     */
    public void setY(int newY)
    {
        this.y = newY;
    }

    /**
     * Gives the energy of the plant.
     * @return energy
     */
    public int getEnergy()
    {
        return energy;
    }

    /**
     * Sets the energy of the plant to the specified amount.
     */
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    /**
     * Gives the age of the plant.
     * @return age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Sets the age of the plant to the specified amount.
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Gives the max energy of the plant.
     * @return maxEnergy
     */
    public int getMaxEnergy()
    {
        return  maxEnergy;
    }

    /**
     * Gives the max age of the plant.
     * @return maxAge
     */
    public int getMaxAge()
    {
        return maxAge;
    }

}
