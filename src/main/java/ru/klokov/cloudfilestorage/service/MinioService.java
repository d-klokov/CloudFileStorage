package ru.klokov.cloudfilestorage.service;

import ru.klokov.cloudfilestorage.dto.MinioDto;
import ru.klokov.cloudfilestorage.model.User;

import java.util.List;

public interface MinioService {
    void createEmptyDirectory(Long userId);
    List<MinioDto> getUserFilesInDirectory(String directoryPath);
    List<MinioDto> getAllUserFiles(Long userId);
}
