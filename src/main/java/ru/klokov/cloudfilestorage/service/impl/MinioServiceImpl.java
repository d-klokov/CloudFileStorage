package ru.klokov.cloudfilestorage.service.impl;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.klokov.cloudfilestorage.dto.MinioDto;
import ru.klokov.cloudfilestorage.exception.MinioServicesException;
import ru.klokov.cloudfilestorage.model.User;
import ru.klokov.cloudfilestorage.service.MinioService;
import ru.klokov.cloudfilestorage.util.MinioUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {
    @Value("${minio.bucketName}")
    private String bucketName;
    
    private final MinioClient minioClient;
    
    @Override
    public void createEmptyDirectory(Long userId) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object("user-" + userId + "-files")
                            .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                            .build()
            );
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            throw new MinioServicesException(e.getMessage());
        }
    }

    @Override
    public List<MinioDto> getUserFilesInDirectory(String directoryPath) {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucketName)
                .prefix(directoryPath)
                .build());

        List<MinioDto> userFiles = new ArrayList<>();

        for (Result<Item> result : results) {
            try {
                Item item = result.get();
                MinioDto file = new MinioDto(item.objectName(), item.isDir());
                userFiles.add(file);
            } catch (Exception e) {
                throw new MinioServicesException(e.getMessage());
            }
        }

        return userFiles;
    }

    @Override
    public List<MinioDto> getAllUserFiles(Long userId) {
        return getUserFilesInDirectory(MinioUtil.getUsersRootFolderPath(userId));
    }
}
