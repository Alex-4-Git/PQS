package customer;

public class NonNullFilter<T> implements Filter<T> {
    private Filter<T> filter;
    
    public NonNullFilter() {
        //need to be implemented.
    }
    
    
    @Override
    public boolean accept(T item) {
        return filter.accept(item);

    }

}
