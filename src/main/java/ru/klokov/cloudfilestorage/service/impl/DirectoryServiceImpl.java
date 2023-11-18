package ru.klokov.cloudfilestorage.service.impl;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.klokov.cloudfilestorage.dto.DirectoryUploadRequest;
import ru.klokov.cloudfilestorage.exception.MinioFileException;
import ru.klokov.cloudfilestorage.service.DirectoryService;
import ru.klokov.cloudfilestorage.service.FileService;
import ru.klokov.cloudfilestorage.utils.MinioUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectoryServiceImpl implements DirectoryService {
    @Value("${minio.bucketName}")
    private String bucketName;

    private final FileService fileService;

    @Override
    public void uploadDirectory(Long userId, List<MultipartFile> files) {
        for (MultipartFile file : files) {
            fileService.uploadFile(userId, file);
        }
    }
}
