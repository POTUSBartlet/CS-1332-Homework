import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ArrayQueueTests {
    private ArrayQueue<Integer> queue;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        queue = new ArrayQueue<>();
    }

    // Tests correct exception is thrown for null element on enqueue
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testEnqueueNullException() {
        queue.enqueue(null);
    }

    // Tests correct exception is thrown for empty queue on dequeue
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDequeueEmptyException() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.dequeue();
    }

    // Tests null is returned for empty queue on peek
    @Test(timeout = TIMEOUT)
    public void testPeekEmptyReturnValue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        assertNull(queue.peek());
    }

    // Tests whether backing array is initially created with correct length
    @Test(timeout = TIMEOUT)
    public void testBackingArrayInitialization() {
        assertEquals(QueueInterface.INITIAL_CAPACITY,
                queue.getBackingArray().length);
    }

    // Tests array circularity below initial capacity
    @Test(timeout = TIMEOUT)
    public void testArrayCircularBelowInitialCapacity() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        assertEquals(5, queue.size());

        queue.dequeue();
        queue.dequeue();
        assertEquals(3, queue.size());

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests array circularity at initial capacity
    @Test(timeout = TIMEOUT)
    public void testArrayCircularAtInitialCapacity() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        queue.enqueue(102);
        queue.enqueue(152);
        queue.enqueue(812);
        queue.enqueue(192);
        queue.enqueue(630);
        queue.enqueue(173);
        assertEquals(11, queue.size());

        queue.dequeue();
        queue.dequeue();
        assertEquals(9, queue.size());

        queue.enqueue(135);
        queue.enqueue(124);
        assertEquals(11, queue.size());

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];
        expected[0] = 135;
        expected[1] = 124;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 102;
        expected[6] = 152;
        expected[7] = 812;
        expected[8] = 192;
        expected[9] = 630;
        expected[10] = 173;

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests array circularity more than one multiple above initial capacity
    @Test(timeout = TIMEOUT)
    public void testCircularPastOneResize() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        for (int i = 100; i < 276; i++) {
            queue.enqueue(i);
        }
        assertEquals(176, queue.size());
        for (int i = 0; i < 100; i++) {
            queue.dequeue();
        }
        assertEquals(76, queue.size());
        for (int i = 300; i < 400; i++) {
            queue.enqueue(i);
        }
        assertEquals(176, queue.size());
        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY * 16];
        for (int i = 300; i < 400; i++) {
            expected[i - 300] = i;
        }
        for (int i = 200; i < 276; i++) {
            expected[i - 100] = i;
        }
        assertArrayEquals(expected, queue.getBackingArray());

    }


    // Tests enqueue below initial capacity
    @Test(timeout = TIMEOUT)
    public void testEnqueueBelowInitialCapacity() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        assertEquals(5, queue.size());

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests enqueue at initial capacity
    @Test(timeout = TIMEOUT)
    public void testEnqueueAtInitialCapacity() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        queue.enqueue(102);
        queue.enqueue(152);
        queue.enqueue(812);
        queue.enqueue(192);
        queue.enqueue(630);
        queue.enqueue(173);
        assertEquals(11, queue.size());

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 102;
        expected[6] = 152;
        expected[7] = 812;
        expected[8] = 192;
        expected[9] = 630;
        expected[10] = 173;

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests enqueue beyond initial capacity
    @Test(timeout = TIMEOUT)
    public void testEnqueuePastInitialCapacity() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        queue.enqueue(234);
        queue.enqueue(999);
        queue.enqueue(819);
        queue.enqueue(285);
        queue.enqueue(281);
        queue.enqueue(812);
        queue.enqueue(482);
        queue.enqueue(291);
        assertEquals(13, queue.size());

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY * 2];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;
        expected[8] = 285;
        expected[9] = 281;
        expected[10] = 812;
        expected[11] = 482;
        expected[12] = 291;

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests enqueue beyond one resize
    @Test(timeout = TIMEOUT)
    public void testEnqueuePastOneResize() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        for (int i = 100; i < 200; i++) {
            queue.enqueue(i);
        }
        assertEquals(100, queue.size());

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY * 16];
        for (int i = 100; i < 200; i++) {
            expected[i - 100] = i;
        }

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests peek below initial capacity
    @Test(timeout = TIMEOUT)
    public void testPeekBelowInitalCapacity() {
        assertEquals(0, queue.size());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        assertEquals(5, queue.size());

        assertEquals(new Integer(465), queue.peek());

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests peek at initial capacity
    @Test(timeout = TIMEOUT)
    public void testPeekAtInitialCapacity() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        queue.enqueue(234);
        queue.enqueue(999);
        queue.enqueue(819);
        queue.enqueue(285);
        queue.enqueue(281);
        queue.enqueue(812);
        assertEquals(11, queue.size());

        assertEquals(new Integer(465), queue.peek());

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;
        expected[8] = 285;
        expected[9] = 281;
        expected[10] = 812;

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests peek beyond initial capacity
    @Test(timeout = TIMEOUT)
    public void testPeekPastInitialCapacity() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        queue.enqueue(234);
        queue.enqueue(999);
        queue.enqueue(819);
        queue.enqueue(285);
        queue.enqueue(281);
        queue.enqueue(812);
        queue.enqueue(482);
        queue.enqueue(291);
        assertEquals(13, queue.size());

        assertEquals(new Integer(465), queue.peek());

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY * 2];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;
        expected[8] = 285;
        expected[9] = 281;
        expected[10] = 812;
        expected[11] = 482;
        expected[12] = 291;

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests peek beyond one resize
    @Test(timeout = TIMEOUT)
    public void testPeekPastOneResize() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        for (int i = 100; i < 200; i++) {
            queue.enqueue(i);
        }
        assertEquals(100, queue.size());

        assertEquals(new Integer(100), queue.peek());

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY * 16];
        for (int i = 100; i < 200; i++) {
            expected[i - 100] = i;
        }

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests dequeue with one element
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDequeueOneElement() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(913);
        assertEquals(1, queue.size());
        assertEquals(new Integer(913), queue.dequeue());
        assertEquals(0, queue.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        assertArrayEquals(expected, queue.getBackingArray());
        queue.dequeue();
    }

    // Tests dequeue below initial capacity
    @Test(timeout = TIMEOUT)
    public void testDequeueBelowInitialCapacity() {
        assertEquals(0, queue.size());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        assertEquals(5, queue.size());

        assertEquals(new Integer(465), queue.dequeue());
        assertEquals(new Integer(134), queue.dequeue());
        assertEquals(new Integer(234), queue.dequeue());

        assertEquals(2, queue.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[3] = 129;
        expected[4] = 472;

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests pop at initial capacity
    @Test(timeout = TIMEOUT)
    public void testPopAtInitialCapacity() {
        assertEquals(0, queue.size());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        queue.enqueue(234);
        queue.enqueue(999);
        queue.enqueue(819);
        queue.enqueue(285);
        queue.enqueue(281);
        queue.enqueue(812);
        assertEquals(11, queue.size());

        assertEquals(new Integer(465), queue.dequeue());
        assertEquals(new Integer(134), queue.dequeue());
        assertEquals(new Integer(234), queue.dequeue());

        assertEquals(8, queue.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;
        expected[8] = 285;
        expected[9] = 281;
        expected[10] = 812;

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests dequeue past initial capacity
    @Test(timeout = TIMEOUT)
    public void testDequeuePastInitialCapacity() {
        assertEquals(0, queue.size());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        queue.enqueue(234);
        queue.enqueue(999);
        queue.enqueue(819);
        queue.enqueue(285);
        queue.enqueue(281);
        queue.enqueue(812);
        queue.enqueue(482);
        queue.enqueue(291);
        assertEquals(13, queue.size());

        assertEquals(new Integer(465), queue.dequeue());
        assertEquals(new Integer(134), queue.dequeue());
        assertEquals(new Integer(234), queue.dequeue());

        assertEquals(10, queue.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY * 2];
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;
        expected[8] = 285;
        expected[9] = 281;
        expected[10] = 812;
        expected[11] = 482;
        expected[12] = 291;

        assertArrayEquals(expected, queue.getBackingArray());
    }

    // Tests dequeue beyond one resize
    @Test(timeout = TIMEOUT)
    public void testDequeuePastOneResize() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        for (int i = 100; i < 200; i++) {
            queue.enqueue(i);
        }
        assertEquals(100, queue.size());

        for (int i = 100; i < 150; i++) {
            assertEquals(new Integer(i), queue.dequeue());
        }

        assertEquals(50, queue.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY * 16];
        for (int i = 150; i < 200; i++) {
            expected[i - 100] = i;
        }

        assertArrayEquals(expected, queue.getBackingArray());
    }
}