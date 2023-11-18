package ru.klokov.cloudfilestorage.service;

import org.springframework.web.multipart.MultipartFile;
import ru.klokov.cloudfilestorage.dto.DirectoryUploadRequest;

import java.util.List;

public interface DirectoryService {
    void uploadDirectory(Long userId, List<MultipartFile> files);
}
