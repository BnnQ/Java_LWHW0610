package me.bnnq.utilities;

public class MathUtilities
{
    public static int gcd(int firstNumber, int secondNumber)
    {
        if (firstNumber == 0)
        {
            return secondNumber;
        }

        return gcd(secondNumber % firstNumber, firstNumber);
    }
}
