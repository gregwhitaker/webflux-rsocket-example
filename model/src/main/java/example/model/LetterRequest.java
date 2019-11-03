package example.model;

public class LetterRequest {

    private int numberOfLetters;

    public LetterRequest() {}

    public LetterRequest(int numberOfLetters) {
        this.numberOfLetters = numberOfLetters;
    }

    public int getNumberOfLetters() {
        return numberOfLetters;
    }

    public void setNumberOfLetters(int numberOfLetters) {
        this.numberOfLetters = numberOfLetters;
    }
}
