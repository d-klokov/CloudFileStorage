package ru.klokov.cloudfilestorage.utils;

public class MinioUtils {
    public static String getUsersRootDirectoryPath(Long userId) {
        return "user-" + userId + "-files/";
    }

    public static String getFileNameWithoutDirectoryPath(String fullName) {
        String[] fullNameParts = fullName.split("/");

        return fullNameParts[fullNameParts.length - 1];
    }
}
