package ru.klokov.cloudfilestorage.service.impl;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.klokov.cloudfilestorage.dto.FileUploadRequest;
import ru.klokov.cloudfilestorage.dto.MinioDto;
import ru.klokov.cloudfilestorage.exception.MinioFileException;
import ru.klokov.cloudfilestorage.exception.MinioServicesException;
import ru.klokov.cloudfilestorage.service.FileService;
import ru.klokov.cloudfilestorage.utils.MinioUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Value("${minio.bucketName}")
    private String bucketName;

    private final MinioClient minioClient;

    @Override
    public List<MinioDto> getUserFilesInDirectory(Long userId, String directoryPath) {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucketName)
                .prefix(directoryPath)
                .build());

        List<MinioDto> userFiles = new ArrayList<>();

        for (Result<Item> result : results) {
            try {
                Item item = result.get();
                String filename = MinioUtils.getFileNameWithoutDirectoryPath(item.objectName());
                MinioDto file = new MinioDto(
                        filename,
                        directoryPath + filename + "/",
                        item.isDir());
                userFiles.add(file);
            } catch (Exception e) {
                throw new MinioServicesException(e.getMessage());
            }
        }

        return userFiles;
    }

    @Override
    public List<MinioDto> getAllUserFiles(Long userId) {
        return getUserFilesInDirectory(userId, MinioUtils.getUsersRootDirectoryPath(userId));
    }

    @Override
    public void uploadFile(Long userId, MultipartFile file) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(MinioUtils.getUsersRootDirectoryPath(userId) + file.getOriginalFilename())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build());
        } catch (Exception e) {
            throw new MinioFileException("Error uploading file!");
        }
    }
}
