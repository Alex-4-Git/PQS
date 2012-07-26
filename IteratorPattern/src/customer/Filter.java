package customer;

public interface Filter<T> {
    public boolean accept(T item);
}