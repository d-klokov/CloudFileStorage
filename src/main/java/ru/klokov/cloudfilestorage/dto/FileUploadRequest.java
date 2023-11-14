package ru.klokov.cloudfilestorage.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;
import ru.klokov.cloudfilestorage.validaion.FileIsNotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadRequest {
//    @NotNull
//    @NumberFormat
//    private Long userId;
    @FileIsNotEmpty
    private MultipartFile file;
}
