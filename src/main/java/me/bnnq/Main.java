package me.bnnq;

import me.bnnq.abstractions.ITernaryOperator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class Main
{
    public static void main(String[] args)
    {
        task4();
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

}