package me.bnnq;

import java.util.ArrayList;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class QueryableArray<T>
{
    private final T[] array;

    public QueryableArray(T[] array)
    {
        this.array = array;
    }

    public QueryableArray<T> search(Predicate<T> predicate)
    {
        ArrayList<T> result = new ArrayList<>();
        for (T element : array)
        {
            if (predicate.test(element))
            {
                result.add(element);
            }
        }

        //noinspection unchecked
        return new QueryableArray<>((T[]) result.toArray());
    }

    public T accumulate(BinaryOperator<T> operator)
    {
        T result = array[0];
        for (int i = 1; i < array.length; i++)
        {
            result = operator.apply(result, array[i]);
        }

        return result;
    }

    public T[] get()
    {
        return array;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < array.length; i++)
        {
            stringBuilder.append(array[i]);
            if (i != array.length - 1)
            {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

}
