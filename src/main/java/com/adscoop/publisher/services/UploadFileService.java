package com.adscoop.publisher.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.form.UploadedFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by thokle on 28/11/2016.
 */

public class UploadFileService {

    private static Logger logger = LoggerFactory.getLogger(UploadFileService.class);

    public String uploadfile(String bannername, UploadedFile uploadedFile, String token) {

        try {
            byte bytes[] = uploadedFile.getBytes();
            File f = createFile(uploadedFile.getFileName());

            FileOutputStream fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(bytes);
            return f.getAbsolutePath();

        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return null;

    }


    private File createFile(String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();
        file.canRead();
        file.canExecute();
        return file;

    }

}
