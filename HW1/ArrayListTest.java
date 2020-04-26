/**
 * Created by PMac on 1/9/2018.
 */
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Author: Harrison Banh
 * Date: 8/21/2017
 */
public class ArrayListTest {
    private ArrayListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStrings() {
        TestCase.assertEquals(0, list.size());

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a

        TestCase.assertEquals(4, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        TestCase.assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a"); //4a 3a 2a 1a 0a

        TestCase.assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "4a";
        expected[1] = "3a";
        expected[2] = "2a";
        expected[3] = "1a";
        expected[4] = "0a";
        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStrings() {
        TestCase.assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        TestCase.assertEquals(6, list.size());

        TestCase.assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a

        TestCase.assertEquals(5, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a"); //0a 1a 2a 3a 4a

        TestCase.assertEquals("0a", list.get(0));
        TestCase.assertEquals("1a", list.get(1));
        TestCase.assertEquals("2a", list.get(2));
        TestCase.assertEquals("3a", list.get(3));
        TestCase.assertEquals("4a", list.get(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectIllegalArgumentWithAddToFront() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontWithEmptyArray() {
        list.addToFront("0a");

        TestCase.assertEquals("0a", list.get(0));
        TestCase.assertEquals(1, list.size());
    }


    @Test(timeout = TIMEOUT)
    public void testAddToFrontWithPartiallyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");

        list.addToFront("9z");

        TestCase.assertEquals(4, list.size());
        TestCase.assertEquals("9z", list.get(0));
        TestCase.assertEquals("0a", list.get(1));
        TestCase.assertEquals("1a", list.get(2));
        TestCase.assertEquals("2a", list.get(3));
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontWithCompletelyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(7, "7a");
        list.addAtIndex(8, "8a");
        list.addAtIndex(9, "9a");
        list.addToFront("10a");
        list.addToBack("15a");

        TestCase.assertEquals(12, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY * 2];
        expected[0] = "10a";
        expected[1] = "0a";
        expected[2] = "1a";
        expected[3] = "2a";
        expected[4] = "3a";
        expected[5] = "4a";
        expected[6] = "5a";
        expected[7] = "6a";
        expected[8] = "7a";
        expected[9] = "8a";
        expected[10] = "9a";
        expected[11] = "15a";
        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectedIllegalArgumentWithAddToBackTest() {
        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackWithEmptyArray() {
        list.addToBack("0a");

        assertEquals(1, list.size());
        assertEquals("0a", list.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackWithPartiallyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");

        list.addToBack("3a");

        TestCase.assertEquals(4, list.size());
        TestCase.assertEquals("0a", list.get(0));
        TestCase.assertEquals("1a", list.get(1));
        TestCase.assertEquals("2a", list.get(2));
        TestCase.assertEquals("3a", list.get(3));
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackWithCompletelyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(7, "7a");
        list.addAtIndex(8, "8a");
        list.addAtIndex(9, "9a");

        list.addToBack("10a");

        TestCase.assertEquals(11, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY * 2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";
        expected[10] = "10a";
        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtNegativeIndex() {
        list.addAtIndex(-1, "0a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexGreaterThanSize() {
        list.addAtIndex(list.size() + 1, "0a");
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexZero() {
        list.addAtIndex(0, "0a");

        assertEquals(1, list.size());
        assertEquals("0a", list.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSize() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");

        list.addAtIndex(list.size(), "3a");

        TestCase.assertEquals(4, list.size());
        TestCase.assertEquals("0a", list.get(0));
        TestCase.assertEquals("1a", list.get(1));
        TestCase.assertEquals("2a", list.get(2));
        TestCase.assertEquals("3a", list.get(3));
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexInMiddleOfPartiallyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");

        list.addAtIndex(1, "4a");

        TestCase.assertEquals(5, list.size());
        TestCase.assertEquals("0a", list.get(0));
        TestCase.assertEquals("4a", list.get(1));
        TestCase.assertEquals("1a", list.get(2));
        TestCase.assertEquals("2a", list.get(3));
        TestCase.assertEquals("3a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testAddIndexInMiddleOfACompletelyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(7, "7a");
        list.addAtIndex(8, "8a");
        list.addAtIndex(9, "9a");

        list.addAtIndex(4, "10a");

        TestCase.assertEquals(11, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY * 2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "10a";
        expected[5] = "4a";
        expected[6] = "5a";
        expected[7] = "6a";
        expected[8] = "7a";
        expected[9] = "8a";
        expected[10] = "9a";
        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontWithEmptyArray() {
        assertNull(list.removeFromFront());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontWithPartiallyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");

        list.removeFromFront();

        TestCase.assertEquals(3, list.size());
        TestCase.assertEquals("1a", list.get(0));
        TestCase.assertEquals("2a", list.get(1));
        TestCase.assertEquals("3a", list.get(2));
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontWithCompletelyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(7, "7a");
        list.addAtIndex(8, "8a");
        list.addAtIndex(9, "9a");

        list.removeFromFront();

        TestCase.assertEquals(9, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        expected[5] = "6a";
        expected[6] = "7a";
        expected[7] = "8a";
        expected[8] = "9a";
        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackWithEmptyArray() {
        assertNull(list.removeFromBack());
    }

    @Test
    public void testRemoveFromBackWithPartiallyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");

        list.removeFromBack();

        TestCase.assertEquals(3, list.size());
        TestCase.assertEquals("0a", list.get(0));
        TestCase.assertEquals("1a", list.get(1));
        TestCase.assertEquals("2a", list.get(2));
    }

    @Test
    public void testRemoveFromBackWithCompletelyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(7, "7a");
        list.addAtIndex(8, "8a");
        list.addAtIndex(9, "9a");

        list.removeFromBack();

        TestCase.assertEquals(9, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtNegativeIndex() {
        list.removeAtIndex(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexGreaterThanOrEqualToSize() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");

        list.removeAtIndex(list.size());
    }

    @Test
    public void testRemoveAtIndexZero() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");

        list.removeAtIndex(0);

        assertEquals(2, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";

        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test
    public void testRemoveAtIndexInTheMiddleOfAPartiallyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");

        list.removeAtIndex(2);

        TestCase.assertEquals(4, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test
    public void testRemoveAtIndexInTheMiddleOfACompletelyFilledArray() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(7, "7a");
        list.addAtIndex(8, "8a");
        list.addAtIndex(9, "9a");

        list.removeAtIndex(3);

        TestCase.assertEquals(9, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "4a";
        expected[4] = "5a";
        expected[5] = "6a";
        expected[6] = "7a";
        expected[7] = "8a";
        expected[8] = "9a";
        Assert.assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAtANegativeIndex() {
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAtAIndexGreaterThanOrEqualToSize() {
        list.get(list.size());
    }

    @Test
    public void testGet(){
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        list.addToFront("0a");
        assertFalse(list.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        assertEquals(1, list.size());

        list.removeFromFront();
        assertEquals(0, list.size());
    }

    @Test
    public void testClear() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");

        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertEquals(11, list.getBackingArray().length);
    }
}