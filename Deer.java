/**
 * This is the Animal Class
 * @author Michael Safdieh
 * @since JDK 1.7
 * @version 1.0
 * Created on 5/1/14.
 */
public class Deer extends Animal
{
    public Deer()
    {
        super();
    }

    /**
     * Checks if the deer is a baby or an adult and sets
     * its value accordingly ( -7 = adult deer, -9 = baby deer).
     */
    @Override
    public void checkAge(int[][] earth)
    {
        if (this.getAge() < 5)
            earth[this.getX()][this.getY()] = -9;
        else if (this.getAge() >= 5 )
            earth[this.getX()][this.getY()] = -7;
    }

}
