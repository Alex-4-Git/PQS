package customer;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilterIterator<E> implements Iterator<E> {

    private Iterator<E> iterator;
    private E next = null;
    private Filter<E> filter;
    
    public FilterIterator(Iterator<E> iterator, Filter<E> filter) {
        this.iterator = iterator;
        this.filter = filter;
    }
    
    @Override
    public boolean hasNext() {
        if (next == null) {
            while(iterator.hasNext()) {
                E tmp = iterator.next();
                if (filter.accept(tmp)) {
                    next = tmp;
                    return true;
                }
            }       
        } else {
            return true;
        }
        return false;
    }

    @Override
    public E next() {
        if (hasNext()) {
            E tmp = next;
            next = null;
            return tmp;
        }
        throw new NoSuchElementException("no element");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}