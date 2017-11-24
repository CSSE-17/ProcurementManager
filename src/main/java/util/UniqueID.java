package util;

import java.util.UUID;

public class UniqueID {

    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
