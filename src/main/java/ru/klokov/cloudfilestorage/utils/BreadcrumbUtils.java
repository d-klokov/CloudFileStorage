package ru.klokov.cloudfilestorage.utils;

import ru.klokov.cloudfilestorage.dto.BreadcrumbResponse;

import java.util.ArrayList;
import java.util.List;

public class BreadcrumbUtils {
    public static List<BreadcrumbResponse> getBreadcrumbResponsesFromPath(String path) {
        List<BreadcrumbResponse> breadcrumbs = new ArrayList<>();

        String[] titles = path.split("/");
        StringBuilder tempLink = new StringBuilder();

        for (int i = 0; i < titles.length; i++) {
            BreadcrumbResponse breadcrumbResponse = new BreadcrumbResponse();
            breadcrumbResponse.setTitle(titles[i]);
            tempLink.append(titles[i]).append("/");

            if (i < titles.length - 1) breadcrumbResponse.setLink(tempLink.toString());

            breadcrumbs.add(breadcrumbResponse);
        }

        return breadcrumbs;
    }
}
