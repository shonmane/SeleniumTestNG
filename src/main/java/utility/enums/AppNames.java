package utility.enums;

public enum AppNames {

    RS_AUTOMATION("rs_automation"),
    AMAZON("amazon"),
    FLIPKART("flipkart");

    private final String key;

    AppNames(String key){
        this.key=key;
    }

    public String getKey(){
        return key;
    }
}
