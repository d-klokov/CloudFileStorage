package ru.klokov.cloudfilestorage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ru.klokov.cloudfilestorage.validaion.FileIsNotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadRequest {
    @FileIsNotEmpty
    private MultipartFile file;
}
