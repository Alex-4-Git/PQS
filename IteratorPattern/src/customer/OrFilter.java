package customer;

public class OrFilter<T> implements Filter<T> {

    private Filter<T> first;
    private Filter<T> second;

    public OrFilter(Filter<T> first, Filter<T> second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    public boolean accept(T item) {
        return first.accept(item) || second.accept(item);
    }

}
