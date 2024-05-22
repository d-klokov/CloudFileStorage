package ru.klokov.cloudfilestorage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MinioDto {
    private String name;
    private String path;
    private boolean isDirectory;
}
