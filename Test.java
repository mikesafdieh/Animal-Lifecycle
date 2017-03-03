/**
 * This is the Test Class for the Earth and Animal Classes
 * @author Michael Safdieh
 * @since JDK 1.7
 * @version 1.0
 * Created on 5/1/14.
 */

public class Test
{
    public static void main(String[] args)
    {
        Earth earth = new Earth(100, 15, 15, 15, 20);

        for (int i = 1; i <= 25; i++)
        {
            System.out.println("Iteration " + i +":");
            earth.printEarth();
            earth.lifeCycle();
            System.out.println();
        }
    }
}
