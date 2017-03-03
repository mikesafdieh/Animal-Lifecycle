/**
 * This is the Earth Class
 * @author Michael Safdieh
 * @since JDK 1.7
 * @version 1.0
 * Created on 5/1/14.
 */

public class Earth
{
    private final int EMPTY = 0;
    private final int PLANT = -1;
//    private final int ANIMAL = -2;
    private final int OBSTACLE = -3;
    private final int WATER = -4;
//    private final int BABY = -5;
    private final int LION = -6;
    private final int DEER = -7;
    private final int BABY_LION = -8;
    private final int BABY_DEER = -9;


//    private int numAnimals;
    private int numLions;
    private int numDeers;
    private int numPlants;
    private int numObstacles;
    private int numWater;

    private int x;
    private int y;

//    private Animal[] animals;
    private Lion[] lions;
    private Deer[] deers;
    private Plant[] plants;
    private int[][] earth;

    /**
     * Constructor for the earth class. Sets the number of plants, lions, deers,
     * obstacles, and water to the specified amounts of the user. Initializes
     * an array of integers to represent each location of the earth. Also initializes
     * arrays of animals and plants, and gives locations to each animal and plant on the earth.
     */
    public Earth(int numPlants, int numLions, int numDeers, int numObstacles, int numWater)
    {
        x = 16;
        y = 16;

        earth = new int[x][y]; /** for 16x16 earth. */

        this.numPlants = numPlants;
//        this.numAnimals = numAnimals;
        this.numObstacles = numObstacles;
        this.numWater = numWater;
        this.numLions = numLions;
        this.numDeers = numDeers;

//        animals = new Animal[256];
        lions = new Lion[256];
        deers = new Deer[256];
        plants = new Plant[256];

        setPlants();
        setLions();
        setDeers();
        setObstacles();
        setWater();

        for (int i = 0; i < numLions; i++)
        {
            lions[i] = new Lion();
        }

        for (int i = 0; i < numDeers; i++)
        {
            deers[i] = new Deer();
        }

        for (int i = 0; i < numPlants; i++)
        {
            plants[i] = new Plant();
        }

        int lionIndex = 0;
        int deerIndex = 0;
        int plantIndex = 0;

        for (int i = 0; i < x; i++)
        {
            for(int j = 0; j < y; j++)
            {
                if (earth[i][j] == BABY_LION)
                {
                    lions[lionIndex].setX(i);
                    lions[lionIndex].setY(j);
                    lionIndex++;
                }
                else if (earth[i][j] == BABY_DEER)
                {
                    deers[deerIndex].setX(i);
                    deers[deerIndex].setY(j);
                    deerIndex++;
                }
                else if (earth[i][j] == PLANT)
                {
                    plants[plantIndex].setX(i);
                    plants[plantIndex].setY(j);
                    plantIndex++;
                }
            }
        }
    }

    /**
     * Performs the cycle of life for each iteration of the earth. Every cycle, animals
     * could eat plants, move, drink water, reproduce, and/or die, and plants can die
     * and/or reproduce. After each cycle, the animals' ages and thirst levels are each
     * incremented by one, and the plants' ages are also incremented by one.
     */
    public void lifeCycle()
    {
        for (int i = 0; i < numLions; i++) // lions life cycle
        {

            if (lions[i].isDead())
            {
                lions[i].die(earth, lions[i].getX(), lions[i].getY());

                Lion temp = lions[i];
                lions[i] = lions[numLions - 1];
                lions[numLions - 1] = temp;

                numLions--;
            }
            else
            {
                lions[i].drinkWater(earth, lions[i].getX(), lions[i].getY());
                lions[i].eatPlant(earth, lions[i].getX(), lions[i].getY(), plants, numPlants);
                lions[i].eatDeer(earth, lions[i].getX(), lions[i].getY(), deers, numDeers);

                if (lions[i].getEnergy() >= lions[i].getMaxEnergy()/4 &&   lions[i].getAge() >= 7
                        && lions[i].getAge() <= 15
                        && lions[i].isNearEmpty(earth, lions[i].getX(), lions[i].getY()) && numLions < 256)
                {
                    lions[i].reproduceLion(earth, lions, numLions);
                    numLions++;
                }

                if (lions[i].move(earth, lions[i].getX(), lions[i].getY()))
                {
                    lions[i].setEnergy(lions[i].getEnergy() - (lions[i].getMaxEnergy() / 20));
                }

                lions[i].setThirstLevel(lions[i].getThirstLevel() + 1);// increments the animal's thirst level by 1.
                lions[i].setAge(lions[i].getAge() + 1);
                lions[i].checkAge(earth);

                if (lions[i].isDead())
                {
                    lions[i].die(earth, lions[i].getX(), lions[i].getY());

                    Lion temp = lions[i];
                    lions[i] = lions[numLions - 1];
                    lions[numLions - 1] = temp;

                    numLions--;
                }
            }
        }


        for (int i = 0; i < numDeers; i++) // deer life cycle
        {

            if (deers[i].isDead())
            {
                deers[i].die(earth, deers[i].getX(), deers[i].getY());

                Deer temp = deers[i];
                deers[i] = deers[numDeers - 1];
                deers[numDeers - 1] = temp;

                numDeers--;
            }
            else
            {
                deers[i].drinkWater(earth, deers[i].getX(), deers[i].getY());
                deers[i].eatPlant(earth, deers[i].getX(), deers[i].getY(), plants, numPlants);

                if (deers[i].getEnergy() >= deers[i].getMaxEnergy()/4 &&   deers[i].getAge() >= 7
                        && deers[i].getAge() <= 15
                        && deers[i].isNearEmpty(earth, deers[i].getX(), deers[i].getY()) && numDeers < 256)
                {
                    deers[i].reproduceDeer(earth, deers, numDeers);
                    numDeers++;
                }

                if (deers[i].move(earth, deers[i].getX(), deers[i].getY()))
                {
                    deers[i].setEnergy(deers[i].getEnergy() - (deers[i].getMaxEnergy() / 20));
                }

                deers[i].setThirstLevel(deers[i].getThirstLevel() + 1);// increments the animal's thirst level by 1.
                deers[i].setAge(deers[i].getAge() + 1);
                deers[i].checkAge(earth);

                if (deers[i].isDead())
                {
                    deers[i].die(earth, deers[i].getX(), deers[i].getY());

                    Deer temp = deers[i];
                    deers[i] = deers[numDeers - 1];
                    deers[numDeers - 1] = temp;

                    numDeers--;
                }
            }
        }


        for (int i = 0; i < numPlants; i++) // plants life cycle
        {
            if (plants[i].isDead() )
            {
                plants[i].die(earth, plants[i].getX(), plants[i].getY());

                Plant temp = plants[i];
                plants[i] = plants[numPlants - 1];
                plants[numPlants - 1] = temp;

                numPlants--;
            }
            else
            {
                if (plants[i].getEnergy() >= plants[i].getMaxEnergy()/50 && plants[i].getAge() >= 7
                        && plants[i].getAge() <= plants[i].getMaxAge()
                        && plants[i].isNearEmpty(earth, plants[i].getX(), plants[i].getY()) && numPlants < 256 )
                {
                    plants[i].reproduce(earth, plants, numPlants);
                    numPlants++;
                }

                plants[i].setAge(plants[i].getAge() + 1);
                plants[i].setEnergy(plants[i].getEnergy() - (plants[i].getMaxEnergy()/50) );

                if (plants[i].isDead())
                {
                    plants[i].die(earth, plants[i].getX(), plants[i].getY());

                    Plant temp = plants[i];
                    plants[i] = plants[numPlants - 1];
                    plants[numPlants - 1] = temp;

                    numPlants--;
                }
            }
        }


    }

    /**
     * Prints an iteration of the earth. For each iteration, the function
     * checks each location of the earth and prints a specific character for each
     * different type of thing on the earth (empty space is " ", animal is "C",
     * baby animal is "c", plant is "*", obstacle is "O", and water is "W").
     */
    public void printEarth()
    {
        int b = 1;
        System.out.println("\n    1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16");
        System.out.println("   ================================================");

        for (int i = 0; i < x; i++)
        {
            if (b <= 9)
                System.out.print(" " + b + "|");
            else
                System.out.print(b + "|");

            for (int j = 0; j < y; j++)
            {
                if (earth[i][j] == PLANT)
                    System.out.print(" * ");
                else if (earth[i][j] == OBSTACLE)
                    System.out.print(" O ");
                else if (earth[i][j] == WATER)
                    System.out.print(" W ");
//                else if (earth[i][j] == ANIMAL)
//                    System.out.print(" C ");
//                else if (earth[i][j] == BABY)
//                    System.out.print(" c ");
                else if (earth[i][j] == LION)
                    System.out.print(" L ");
                else if (earth[i][j] == DEER)
                    System.out.print(" D ");
                else if (earth[i][j] == BABY_LION)
                    System.out.print(" l ");
                else if (earth[i][j] == BABY_DEER)
                    System.out.print(" d ");
                else
                    System.out.print("   ");
            }

            System.out.println("|" + b);
            b++;
        }
        System.out.println("   ================================================");
        System.out.println("    1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16\n");
    }

    /**
     * Sets the number of plants specified by the user to random locations
     * on the earth.
     */
    public void setPlants()
    {
        for (int i = 1; i <= numPlants; i++)
        {
            boolean isSet = false;
            while(! isSet)
            {
                int randX = (int) (Math.random() * earth.length);
                int randY = (int) (Math.random() * earth[0].length);

                if(earth [randX][randY] == EMPTY)
                {
                    earth [randX][randY] = PLANT;
                    isSet = true;
                }
            }
        }
    }

    /**
     * Sets the number of lions specified by the user to random locations
     * on the earth.
     */
    public void setLions()
    {
        for (int i = 1; i <= numLions; i++)
        {
            boolean isSet = false;
            while(! isSet)
            {
                int randX = (int) (Math.random() * earth.length);
                int randY = (int) (Math.random() * earth[0].length);

                if(earth [randX][randY] == EMPTY)
                {
                    earth [randX][randY] = BABY_LION;
                    isSet = true;
                }
            }
        }
    }

    /**
     * Sets the number of deer specified by the user to random locations
     * on the earth.
     */
    public void setDeers()
    {
        for (int i = 1; i <= numDeers; i++)
        {
            boolean isSet = false;
            while(! isSet)
            {
                int randX = (int) (Math.random() * earth.length);
                int randY = (int) (Math.random() * earth[0].length);

                if(earth [randX][randY] == EMPTY)
                {
                    earth [randX][randY] = BABY_DEER;
                    isSet = true;
                }
            }
        }
    }

    /**
     * Sets the number of obstacles specified by the user to random locations
     * on the earth.
     */
    public void setObstacles()
    {
        for (int i = 1; i <= numObstacles; i++)
        {
            boolean isSet = false;
            while(! isSet)
            {
                int randX = (int) (Math.random() * earth.length);
                int randY = (int) (Math.random() * earth[0].length);

                if(earth [randX][randY] == EMPTY)
                {
                    earth [randX][randY] = OBSTACLE;
                    isSet = true;
                }
            }
        }
    }

    /**
     * Sets the number of waters specified by the user to random locations
     * on the earth.
     */
    public void setWater()
    {
        for (int i = 1; i <= numWater; i++)
        {
            boolean isSet = false;
            while(! isSet)
            {
                int randX = (int) (Math.random() * earth.length);
                int randY = (int) (Math.random() * earth[0].length);

                if(earth [randX][randY] == EMPTY)
                {
                    earth [randX][randY] = WATER;
                    isSet = true;
                }
            }
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

}
