package me.bnnq;

import me.bnnq.abstractions.ITernaryOperator;
import me.bnnq.datastructures.Pair;
import me.bnnq.datastructures.Quartet;
import me.bnnq.datastructures.Trio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.*;

@SuppressWarnings("unused")
public class Main
{
    public static void main(String[] args)
    {
        task1();
    }

    public static void task1()
    {
        ITernaryOperator<Integer> sumCalculator = (a, b, c) -> a + b + c;
        System.out.printf("1 + 2 + 3 = %d\n", sumCalculator.apply(1, 2, 3));

        ITernaryOperator<Integer> productCalculator = (a, b, c) -> a * b * c;
        System.out.printf("1 * 2 * 3 = %d\n", productCalculator.apply(1, 2, 3));

        Consumer<LocalTime> currentTimePrinter = time -> System.out.printf("Current time: %s\n", time);
        currentTimePrinter.accept(LocalTime.now());

        Consumer<LocalDate> currentDatePrinter = date -> System.out.printf("Current date: %s\n", date);
        currentDatePrinter.accept(LocalDate.now());
    }

    public static void task2()
    {
        BinaryOperator<Integer> maximumDeterminer = (a, b) -> a > b ? a : b;
        System.out.printf("Max of 1 and 2 is %d\n", maximumDeterminer.apply(1, 2));

        BinaryOperator<Integer> minimumDeterminer = (a, b) -> a < b ? a : b;
        System.out.printf("Min of 1 and 2 is %d\n", minimumDeterminer.apply(1, 2));

        UnaryOperator<Integer> factorialCalculator = (a) ->
        {
            int result = 1;
            for (int i = 1; i <= a; i++)
            {
                result *= i;
            }
            return result;
        };
        System.out.printf("Factorial of 5 is %d\n", factorialCalculator.apply(5));

        Predicate<Integer> primaryNumberDeterminer = (a) ->
        {
            if (a == 1)
            {
                return false;
            }
            for (int i = 2; i < a; i++)
            {
                if (a % i == 0)
                {
                    return false;
                }
            }
            return true;
        };

        System.out.printf("%d is %s\n", 1, primaryNumberDeterminer.test(1) ? "primary" : "not primary");
    }

    public static void task3()
    {
        ITernaryOperator<Integer> maximumDeterminer = (a, b, c) -> a > b ? (a > c ? a : c) : (b > c ? b : c);
        System.out.printf("Max of 1, 2 and 3 is %d\n", maximumDeterminer.apply(1, 2, 3));

        ITernaryOperator<Integer> minimumDeterminer = (a, b, c) -> a < b ? (a < c ? a : c) : (b < c ? b : c);
        System.out.printf("Min of 1, 2 and 3 is %d\n", minimumDeterminer.apply(1, 2, 3));
    }

    public static void task4()
    {
        Integer[] array = {1, 2, 3, 4, 5};
        QueryableArray<Integer> queryableArray = new QueryableArray<>(array);
        int sumOfEvenNumbers = queryableArray
                .search(number -> number % 2 == 0)
                .accumulate(Integer::sum);

        System.out.printf("Sum of even numbers in array %s is %d\n", queryableArray, sumOfEvenNumbers);

        int sumOfOddNumbers = queryableArray
                .search(number -> number % 2 == 1)
                .accumulate(Integer::sum);

        System.out.printf("Sum of odd numbers in array %s is %d\n", queryableArray, sumOfOddNumbers);

        int leftBound = 2;
        int rightBound = 4;

        int sumOfNumbersInRange = queryableArray
                .search(number -> number >= leftBound && number <= rightBound)
                .accumulate(Integer::sum);

        System.out.printf("Sum of numbers in range %d and %d in array %s is %d\n", leftBound, rightBound, queryableArray, sumOfNumbersInRange);

        int sumOfMultiplesOfTwo = queryableArray
                .search(number -> number % 2 == 0)
                .accumulate(Integer::sum);

        System.out.printf("Sum of multiples of two in array %s is %d\n", queryableArray, sumOfMultiplesOfTwo);
    }

    public static void task5()
    {
        Predicate<Integer> leapYearDeterminer = (year) -> year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
        System.out.printf("%d is %s\n", 2020, leapYearDeterminer.test(2020) ? "leap year" : "not leap year");

        Pair<LocalDate> dates = new Pair<>(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 14));
        Function<Pair<LocalDate>, Integer> dayDifferenceCalculator = (pair) -> Math.abs(pair.getFirst().getDayOfYear() - pair.getSecond().getDayOfYear());
        System.out.printf("Difference between %s and %s is %d days\n", dates.getFirst(), dates.getSecond(), dayDifferenceCalculator.apply(dates));

        Function<Pair<LocalDate>, Double> weekDifferenceCalculator = (pair) -> Math.abs(pair.getFirst().getDayOfYear() - pair.getSecond().getDayOfYear()) / 7.0;
        System.out.printf("Difference between %s and %s is %.1f weeks\n", dates.getFirst(), dates.getSecond(), weekDifferenceCalculator.apply(dates));

        Function<LocalDate, DayOfWeek> dayOfWeekDeterminer = LocalDate::getDayOfWeek;
        System.out.printf("Day of week of %s is %s\n", LocalDate.of(2020, 1, 1), dayOfWeekDeterminer.apply(LocalDate.of(2020, 1, 1)));
    }

    public static void task6()
    {
        BinaryOperator<Fraction> fractionSumCalculator = (a, b) ->
        {
            int numerator = a.getNumerator() * b.getDenominator() + b.getNumerator() * a.getDenominator();
            int denominator = a.getDenominator() * b.getDenominator();
            return new Fraction(numerator, denominator);
        };

        BinaryOperator<Fraction> fractionDifferenceCalculator = (a, b) ->
        {
            int numerator = a.getNumerator() * b.getDenominator() - b.getNumerator() * a.getDenominator();
            int denominator = a.getDenominator() * b.getDenominator();
            return new Fraction(numerator, denominator);
        };

        BinaryOperator<Fraction> fractionProductCalculator = (a, b) ->
        {
            int numerator = a.getNumerator() * b.getNumerator();
            int denominator = a.getDenominator() * b.getDenominator();
            return new Fraction(numerator, denominator);
        };

        BinaryOperator<Fraction> fractionQuotientCalculator = (a, b) ->
        {
            int numerator = a.getNumerator() * b.getDenominator();
            int denominator = a.getDenominator() * b.getNumerator();
            return new Fraction(numerator, denominator);
        };

        Fraction firstFraction = new Fraction(1, 2);
        Fraction secondFraction = new Fraction(1, 3);

        System.out.printf("%s + %s = %s\n", firstFraction, secondFraction, fractionSumCalculator.apply(firstFraction, secondFraction));
        System.out.printf("%s - %s = %s\n", firstFraction, secondFraction, fractionDifferenceCalculator.apply(firstFraction, secondFraction));
        System.out.printf("%s * %s = %s\n", firstFraction, secondFraction, fractionProductCalculator.apply(firstFraction, secondFraction));
        System.out.printf("%s / %s = %s\n", firstFraction, secondFraction, fractionQuotientCalculator.apply(firstFraction, secondFraction));
    }

    public static void task7()
    {
        Function<Quartet<Integer>, Integer> minimumDeterminer = (quartet) ->
        {
            int min = quartet.getFirst();
            if (quartet.getSecond() < min)
            {
                min = quartet.getSecond();
            }
            if (quartet.getThird() < min)
            {
                min = quartet.getThird();
            }
            if (quartet.getFourth() < min)
            {
                min = quartet.getFourth();
            }
            return min;
        };

        Function<Quartet<Integer>, Integer> maximumDeterminer = (quartet) ->
        {
            int max = quartet.getFirst();
            if (quartet.getSecond() > max)
            {
                max = quartet.getSecond();
            }
            if (quartet.getThird() > max)
            {
                max = quartet.getThird();
            }
            if (quartet.getFourth() > max)
            {
                max = quartet.getFourth();
            }
            return max;
        };

        Quartet<Integer> quartet = new Quartet<>(1, 2, 3, 4);
        System.out.printf("Minimum of %s is %d\n", quartet, minimumDeterminer.apply(quartet));
        System.out.printf("Maximum of %s is %d\n", quartet, maximumDeterminer.apply(quartet));
    }

    public static void task8()
    {
        Predicate<Pair<Integer>> equalDeterminer = (pair) -> pair.getFirst().equals(pair.getSecond());
        Pair<Integer> numbersPair = new Pair<>(1, 2);
        System.out.printf("%s is %s\n", numbersPair, equalDeterminer.test(numbersPair) ? "equal" : "not equal");

        Predicate<Pair<Integer>> rangeOccuranceDeterminer = (pair) -> pair.getFirst() >= 0 && pair.getFirst() <= 10 && pair.getSecond() >= 0 && pair.getSecond() <= 10;
        Pair<Integer> range = new Pair<>(1, 10);
        Trio<Integer> arguments = range.toTrio(5);
        System.out.printf("%d is %s %s\n", arguments.getThird(), rangeOccuranceDeterminer.test(arguments) ? "in range" : "not in range", range);

        int numberToTest = 1;
        Predicate<Integer> positiveDeterminer = (number) -> number > 0;
        System.out.printf("%d is %s\n", numberToTest, positiveDeterminer.test(numberToTest) ? "positive" : "not positive");

        Predicate<Integer> negativeDeterminer = (number) -> number < 0;
        System.out.printf("%d is %s\n", numberToTest, negativeDeterminer.test(numberToTest) ? "negative" : "not negative");
    }

}