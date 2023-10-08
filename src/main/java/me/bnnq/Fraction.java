package me.bnnq;

import me.bnnq.utilities.MathUtilities;

public class Fraction
{
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }


    public void simplify()
    {
        int gcd = MathUtilities.gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    public int getNumerator()
    {
        return numerator;
    }

    public int getDenominator()
    {
        return denominator;
    }

    @Override
    public String toString()
    {
        return String.format("%d/%d", numerator, denominator);
    }

}
