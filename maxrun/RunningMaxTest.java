import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.*;

/**
 * Created by andrewhwang on 10/15/15.
 */
public class RunningMaxTest {

    RunningMax<Integer> maxInteger;
    Integer one = 1;
    Integer two = 2;
    Integer four = 4;
    Integer nine = 9;
    Integer five = 5;

    @Before
    public void setUp() throws Exception {
        maxInteger = new RunningMax<>();
    }

    /**
     * Structured Basis: the if is true: k = 5 > 0
     * Good Data: nominal case: 5 items
     * Boundary analysis: k > 0
     */
    @Test
    public void testInitializePositiveK() {
        maxInteger.initialize(5);

        maxInteger.offer(two);
        maxInteger.offer(one);
        maxInteger.offer(nine);
        maxInteger.offer(four);
        maxInteger.offer(five);

        Assert.assertEquals(maxInteger.getNumOfElements(), 5);
    }

    /**
     * Structured Basis: the if is false: k = -6 < 0
     * Bad Data: list capacity cannot be negative
     * Boundary analysis: k < 0
     * Error guessing: k is negative
     */
    @Test (expected = IllegalArgumentException.class)
    public void testInitializeNegativeK() {
        maxInteger.initialize(-6);
    }

    /**
     * Structured Basis: the if is false: k == 0
     * Bad Data: list capacity cannot be zero
     * Boundary analysis: k == 0
     * Error guessing: k is zero
     */
    @Test (expected = IllegalArgumentException.class)
    public void testInitializeZeroK() {
        maxInteger.initialize(0);
    }

    /**
     * Structured Basis: the first if is true: the inputted element is null
     * Bad Data: cannot add null to the list
     * Error guessing: x is null
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNullInput() {
        maxInteger.initialize(5);
        maxInteger.offer(null);
    }

    /**
     * Structured Basis: the first if is false: the input is not null and the number of elements = 5 = k
     * Good Data: nominal case: 6 integers added and one removed
     * Boundary analysis: number of elements = k
     */
    @Test
    public void testNumOfElementsIsK() {
        maxInteger.initialize(5);

        maxInteger.offer(two);
        maxInteger.offer(one);
        maxInteger.offer(nine);
        maxInteger.offer(four);
        maxInteger.offer(five);

        maxInteger.offer(four);

        Assert.assertTrue(maxInteger.getElement(0).equals(one));
    }

    /**
     * Structured Basis: the first if is false: the input is not null and the number of elements = 4 < k = 5
     * Good Data: nominal case: 5 integers and none removed
     * Boundary analysis: number of elements < k
     */
    @Test
    public void testNumOfElementsIsLessK() {
        maxInteger.initialize(5);

        maxInteger.offer(two);
        maxInteger.offer(one);
        maxInteger.offer(nine);
        maxInteger.offer(four);
        maxInteger.offer(five);

        Assert.assertTrue(maxInteger.getElement(0).equals(two));
    }

    /**
     * Structured Basis: the if is true: the list is empty
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testListEmpty() {
        maxInteger.initialize(5);
        maxInteger.max();
    }

    /**
     * Structured Basis: nominal case: the while is true: index i < the number of elements
     */
    @Test
    public void testNominalMax() {
        maxInteger.initialize(5);

        maxInteger.offer(two);
        maxInteger.offer(one);
        maxInteger.offer(nine);
        maxInteger.offer(four);
        maxInteger.offer(five);

        Assert.assertTrue(maxInteger.max().equals(nine));
    }
}