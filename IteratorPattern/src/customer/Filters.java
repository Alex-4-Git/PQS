package customer;

public class Filters {

    public static <T> Filter<T> or(Filter<T> first, Filter<T> second) {
        return new OrFilter<T>(first, second);
    }

}