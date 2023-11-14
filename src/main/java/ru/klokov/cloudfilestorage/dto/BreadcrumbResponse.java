package ru.klokov.cloudfilestorage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BreadcrumbResponse {
    private String title;
    private String link;
}
