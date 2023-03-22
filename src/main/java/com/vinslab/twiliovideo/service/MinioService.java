package com.vinslab.twiliovideo.service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MinioService {
    private final MinioClient minioClient;

    public MinioService(@Value("${minio.url}") String minioUrl, @Value("${minio.access.key}") String minioAccess, @Value("${minio.access.secret}") String minioSecret) {
        this.minioClient = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(minioAccess, minioSecret)
                .build();
    }

    @SneakyThrows
    public String getSecurePutUrl(String filename) {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.PUT)
                        .bucket("unified-eform")
                        .object(filename)
                        .expiry(1, TimeUnit.DAYS)
                        .build());
    }
}
