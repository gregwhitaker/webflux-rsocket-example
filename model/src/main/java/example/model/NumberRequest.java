package example.model;

public class NumberRequest {

    private int numberOfNumbers;

    public NumberRequest() {}

    public NumberRequest(int numberOfNumbers) {
        this.numberOfNumbers = numberOfNumbers;
    }

    public int getNumberOfNumbers() {
        return numberOfNumbers;
    }

    public void setNumberOfNumbers(int numberOfNumbers) {
        this.numberOfNumbers = numberOfNumbers;
    }
}