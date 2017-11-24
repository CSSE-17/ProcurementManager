package util;

import java.util.UUID;

public class UniqueID {

    public String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
