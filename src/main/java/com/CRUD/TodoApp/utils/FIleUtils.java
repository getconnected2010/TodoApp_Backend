package com.CRUD.TodoApp.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//a utility that accepts a file which is of type MultipartFile and converts it to File type for S3 upload.
public class FIleUtils {
    public static File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convertedFile;
    }
}
