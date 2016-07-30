package alexberemart.jHattrick.model.vo;

public class HtCredentials {

    protected String consumerKey;
    protected String consumerSecretKey;
    protected String token;
    protected String secretToken;

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecretKey() {
        return consumerSecretKey;
    }

    public void setConsumerSecretKey(String consumerSecretKey) {
        this.consumerSecretKey = consumerSecretKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretToken() {
        return secretToken;
    }

    public void setSecretToken(String secretToken) {
        this.secretToken = secretToken;
    }
}
