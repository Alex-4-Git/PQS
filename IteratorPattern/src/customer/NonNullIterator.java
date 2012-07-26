package customer;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator<E> implements Iterator<E> {

    private Iterator<E> iterator;
    private E next = null;

    public NonNullIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        if (next == null) {
            while (iterator.hasNext()) {
                E tmp = iterator.next();
                if (tmp != null) {
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
