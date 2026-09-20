package utility.enums;

public enum AppNames {

    RS_AUTOMATION("rs_automation"),
    EVENTHUB("eventhub"),
    DUMMY_CLIENT("dummy_client");

    private final String key;

    AppNames(String key){
        this.key=key;
    }

    public String getKey(){
        return key;
    }
}
