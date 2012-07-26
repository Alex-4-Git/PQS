package customer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FilterIteratorTest {

    private Filter<String> filter;
    private Filter<String> minLengthFilter;
    private Filter<String> maxLengthFilter;

    @Before
    public void setUp() throws Exception {
        filter = new NonNullFilter<String>();
        minLengthFilter = new MinLengthFilter(3);
        maxLengthFilter = new MaxLengthFilter(9);
    }

    @Test
    public void testFilterIterator() {
        List<String> names = new ArrayList<String>();
        names.add("Alice");
        names.add(null);
        names.add("Bob");
        names.add(null);

        Iterator<String> iter = new FilterIterator<String>(names.iterator(),
                filter);
        int counter = 0;
        while (iter.hasNext()) {
            Assert.assertTrue(iter.next() != null);
            counter++;
        }
        Assert.assertEquals(2, counter);
    }

    @Test
    public void testFilterIterator_allNulls() {
        List<String> names = new ArrayList<String>();
        names.add(null);
        names.add(null);
        names.add(null);

        Iterator<String> iter = new FilterIterator<String>(names.iterator(),
                filter);
        Assert.assertFalse(iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFilterIterator_noSuchElementException() {
        List<String> names = new ArrayList<String>();
        Iterator<String> iter = new FilterIterator<String>(names.iterator(),
                filter);
        iter.next();
    }

    @Test
    public void testFilterIterator_hasNextManyTimes() {
        List<String> names = new ArrayList<String>();
        names.add("Alice");

        Iterator<String> iter = new FilterIterator<String>(names.iterator(),
                filter);
        for (int i = 0; i < 50; i++) {
            Assert.assertTrue(iter.hasNext());
        }
        Assert.assertEquals("Alice", iter.next());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void testFilterIterator_nextManyTimes() {
        List<String> names = new ArrayList<String>();
        names.add("Alice");
        names.add(null);
        names.add("Bob");
        names.add(null);

        Iterator<String> iter = new FilterIterator<String>(names.iterator(),
                filter);

        Assert.assertEquals("Alice", iter.next());
        Assert.assertEquals("Bob", iter.next());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void testFilterIterator_nested() {
        List<String> names = new ArrayList<String>();
        names.add("Alice");
        names.add(null);
        names.add("Bob");
        names.add(null);

        Iterator<String> iter = new FilterIterator<String>(
                new FilterIterator<String>(new FilterIterator<String>(
                        names.iterator(), filter), filter), filter);

        Assert.assertEquals("Alice", iter.next());
        Assert.assertEquals("Bob", iter.next());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void testFilterIterator_forStrings() {

        List<String> names = new ArrayList<String>();
        names.add("");
        names.add("b");
        names.add("Alice");
        names.add("cc");
        names.add(null);
        names.add("Bob");
        names.add(null);
        names.add("1234567890");
        names.add("1234567890");
        names.add("Alice");
        names.add("1234567890");
        names.add(null);
        names.add("Bob");
        names.add(null);
        Iterator<String> iter = new FilterIterator<String>(
                new FilterIterator<String>(new FilterIterator<String>(
                        names.iterator(), filter), minLengthFilter),
                maxLengthFilter);

        Assert.assertEquals("Alice", iter.next());
        Assert.assertEquals("Bob", iter.next());
        Assert.assertEquals("Alice", iter.next());
        Assert.assertEquals("Bob", iter.next());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void testFilterIterator_forOr() {
        Filter<String> minFilter = new MinLengthFilter(9);
        Filter<String> maxFilter = new MaxLengthFilter(2);

//        Filter filter = and(nullFilter, or(minFilter, maxFilter));

        Filter<String> orFilter = new OrFilter<String>(minFilter, maxFilter);

        List<String> names = new ArrayList<String>();
        names.add("");
        names.add("b");
        names.add("Alice");
        names.add("cc");
        names.add(null);
        names.add("Bob");
        names.add(null);
        names.add("1234567890");
        names.add("1234567890");
        names.add("Alice");
        names.add("1234567890");
        names.add(null);
        names.add("Bob");
        names.add(null);

        Iterator<String> iter = new FilterIterator<String>(
                new FilterIterator<String>(names.iterator(), filter), orFilter);

        Assert.assertEquals("", iter.next());
        Assert.assertEquals("b", iter.next());
        Assert.assertEquals("cc", iter.next());
        Assert.assertEquals("1234567890", iter.next());
    }

}
