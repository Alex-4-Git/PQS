package customer;

public class MinLengthFilter implements Filter<String> {

    private int minLength;

    public MinLengthFilter(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean accept(String item) {
        return item.length() >= minLength;
    }

}