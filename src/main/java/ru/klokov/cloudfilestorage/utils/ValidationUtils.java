package ru.klokov.cloudfilestorage.utils;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import ru.klokov.cloudfilestorage.exception.RenameFileException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ValidationUtils {
    public static String getErrorMessageFromBindingResult(BindingResult result) {
        return result.getAllErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Error uploading file!");
    }

    public static MultipartFile getFileWithValidName(MultipartFile currentFile) {
        return new MultipartFile() {
            @Override
            public String getName() {
                return currentFile.getName();
            }

            @Override
            public String getOriginalFilename() {
                return currentFile.getOriginalFilename().replaceAll("\\\\", "_");
            }

            @Override
            public String getContentType() {
                return currentFile.getContentType();
            }

            @Override
            public boolean isEmpty() {
                return currentFile.isEmpty();
            }

            @Override
            public long getSize() {
                return currentFile.getSize();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return currentFile.getBytes();
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return currentFile.getInputStream();
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
    }
}
