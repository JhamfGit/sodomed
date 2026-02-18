package com.sodemed.utils.medicationorders;

import org.springframework.web.multipart.MultipartFile;

import com.sodemed.exceptions.NotCreateException;

import java.util.UUID;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUpload {

    public static String FileSave(MultipartFile file, String prefix) {
        String filename = "";
        try {
            FolderHandler folderHandler = new FolderHandler();
            String globalPath = folderHandler.preparePath();
            File directory = new File(globalPath);
            if (file.isEmpty()) {
                throw new NotCreateException("no se pudo crear el archivo");
            }
            filename =prefix+"_"+UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            File destinationFile = new File(directory, filename);
            try (InputStream inputStream = file.getInputStream();
                    OutputStream outputStream = new FileOutputStream(destinationFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

}
