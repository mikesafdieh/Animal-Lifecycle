/**
 * This is the Animal Class
 * @author Michael Safdieh
 * @since JDK 1.7
 * @version 1.0
 * Created on 5/1/14.
 */
public class Lion extends Animal
{
    public Lion()
    {
        super();
    }

    /**
     * If the lion's energy is below a certain amount, the animal will eat a
     * nearby deer, and the function returns true. Otherwise the function returns
     * false.
     * @return true or false
     */
    public boolean eatDeer(int[][] earth, int x, int y, Deer[] deers, int numDeers)
    {
        if (this.getEnergy() < this.getMaxEnergy())
        {
            if (isNearDeer(earth, x, y, deers, numDeers))
            {
                this.setEnergy(this.getEnergy() + this.getMaxEnergy()/10);

                if (this.getEnergy() > this.getMaxEnergy())
                    this.setEnergy(this.getMaxEnergy());
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the lion is near a deer location. If it is, the function
     * returns true, sets the deer location to an empty space, and kills the deer.
     * Otherwise the function returns false.
     * @return true or false
     */
    public boolean isNearDeer(int[][] earth, int x, int y, Deer[] deers, int numDeers)
    {
        int deerLocationX = -1;
        int deerLocationY = -1;

        if (y-1 >= 0 && (earth[x][y-1] == -7 || earth[x][y-1] == -9)) //left
        {
            deerLocationX = x;
            deerLocationY = y-1;
        }
        else if (y+1 <= 15 && (earth[x][y+1] == -7 || earth[x][y+1] == -9)) //right
        {
            deerLocationX = x;
            deerLocationY = y+1;
        }
        else if (x-1 >= 0 && (earth[x-1][y] == -7 || earth[x-1][y] == -9)) //up
        {
            deerLocationX = x-1;
            deerLocationY = y;
        }
        else if (x+1 <= 15 && (earth[x+1][y] == -7 || earth[x+1][y] == -9))  //down
        {
            deerLocationX = x+1;
            deerLocationY = y;
        }
        else if (x-1 >= 0 && y-1 >= 0 && (earth[x-1][y-1] == -7 || earth[x-1][y-1] == -9)) //top left
        {
            deerLocationX = x-1;
            deerLocationY = y-1;
        }
        else if (x-1 >= 0 && y+1 <= 15 && (earth[x-1][y+1] == -7 || earth[x-1][y+1] == -9)) //top right
        {
            deerLocationX = x-1;
            deerLocationY = y+1;
        }
        else if (x+1 <= 15 && y+1 <= 15 && (earth[x+1][y+1] == -7 || earth[x+1][y+1] == -9)) //bottom right
        {
            deerLocationX = x+1;
            deerLocationY = y+1;
        }
        else if (x+1 <= 15 && y-1 >= 0 && (earth[x+1][y-1] == -7 || earth[x+1][y-1] == -9))  //bottom left
        {
            deerLocationX = x+1;
            deerLocationY = y-1;
        }

        if (deerLocationX >= 0 && deerLocationY >= 0)
        {
            /** This for loop makes sure the eaten deer dies */
            for (int i = 0; i < numDeers; i++)
            {
                if (deers[i].getX() == deerLocationX && deers[i].getY() == deerLocationY)
                {
                    deers[i].die(earth, deerLocationX, deerLocationY);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the lion is a baby or an adult and sets
     * its value accordingly ( -6 = adult lion, -8 = baby lion).
     */
    @Override
    public void checkAge(int[][] earth)
    {
        if (this.getAge() < 5)
            earth[this.getX()][this.getY()] = -8;
        else if (this.getAge() >= 5 )
            earth[this.getX()][this.getY()] = -6;
    }

}
