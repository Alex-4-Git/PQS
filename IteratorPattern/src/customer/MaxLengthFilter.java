package customer;

public class MaxLengthFilter implements Filter<String> {

    private int maxLength;

    public MaxLengthFilter(int minLength) {
        this.maxLength = minLength;
    }

    @Override
    public boolean accept(String item) {
        return item.length() <= maxLength;
    }

}