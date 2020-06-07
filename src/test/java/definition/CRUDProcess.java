package definition;

import groovy.json.internal.LazyMap;

public class CRUDProcess {
    private static String url = "http://agapito-server.herokuapp.com/processos";
    private static LazyMap fieldsJson = new LazyMap();
    private static String lastId = "";

    public static String getUrl() {
        return url;
    }

    public static void clearFields() {
        fieldsJson.clear();
    }

    public static void addFields(String field, String value) {
        fieldsJson.put(field, value);
    }

    public static LazyMap getFields() {
        return fieldsJson;
    }

    public static void setLastId(String id) {
        lastId = id;
    }

    public static String getLastId(){
        return lastId;
    }
}
