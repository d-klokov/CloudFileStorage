package ru.klokov.cloudfilestorage.service;

import org.springframework.web.multipart.MultipartFile;
import ru.klokov.cloudfilestorage.dto.FileUploadRequest;
import ru.klokov.cloudfilestorage.dto.MinioDto;

import java.util.List;

public interface FileService {
    List<MinioDto> getUserFilesInDirectory(Long userId, String directoryPath);
    List<MinioDto> getAllUserFiles(Long userId);
    void uploadFile(Long userId, MultipartFile file);
}
