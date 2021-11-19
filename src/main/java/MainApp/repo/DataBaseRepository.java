package MainApp.repo;

public interface DataBaseRepository {
    static final String USERPASS_HASH_NAME = "userList";
    static final String TOKENS_LIST_NAME = "tokensList";
    static final String TOKENS_HASH_NAME = "tokensHash";
    static final String INVENTORY_HASH_NAME = "inventoryHash";

    void AddElementInList(String listName, String element);

    Boolean ExistsElementInList(String listName, String element);

    void AddElementInHash(String key, String field, String value);

    String GetValueInHash(String key, String field);
}