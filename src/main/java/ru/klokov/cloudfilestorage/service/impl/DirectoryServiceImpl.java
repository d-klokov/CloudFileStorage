package ru.klokov.cloudfilestorage.service.impl;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.klokov.cloudfilestorage.dto.DirectoryUploadRequest;
import ru.klokov.cloudfilestorage.exception.MinioFileException;
import ru.klokov.cloudfilestorage.exception.MinioServicesException;
import ru.klokov.cloudfilestorage.service.DirectoryService;
import ru.klokov.cloudfilestorage.service.FileService;
import ru.klokov.cloudfilestorage.utils.MinioUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectoryServiceImpl implements DirectoryService {
    @Value("${minio.bucketName}")
    private String bucketName;

    private final MinioClient minioClient;

    private final FileService fileService;

    @Override
    public void createEmptyDirectory(String directoryName) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(directoryName)
                            .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                            .build()
            );
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            throw new MinioServicesException(e.getMessage());
        }
    }

    @Override
    public void createUsersRootDirectory(Long userId) {
        createEmptyDirectory("user-" + userId + "-files");
    }

    @Override
    public void uploadDirectory(Long userId, List<MultipartFile> files) {
        for (MultipartFile file : files) {
            fileService.uploadFile(userId, file);
        }
    }
}
