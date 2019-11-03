package example.client.model;

public class ClientResponse {

    private String value;

    public ClientResponse() {}

    public ClientResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
