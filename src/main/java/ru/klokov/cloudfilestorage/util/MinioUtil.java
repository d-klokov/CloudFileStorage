package ru.klokov.cloudfilestorage.util;

import ru.klokov.cloudfilestorage.model.User;

public class MinioUtil {
    public static String getUsersRootFolderPath(Long userId) {
        return "user-" + userId + "-files";
    }
}
