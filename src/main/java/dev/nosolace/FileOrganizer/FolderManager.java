package dev.nosolace.file.organizer;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author no-solace
 */
public class FolderManager {

    private final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyMMdd");

    public void moveFile(Path file) throws Exception {
        Path parent = file.getParent();
        System.out.println("Parent folder: " + parent);

        Path targetDir = parent.resolve(LocalDate.now().format(formatter));
        System.out.println("Target folder: " + targetDir);

        // tạo folder (idempotent)
        Files.createDirectories(targetDir);

        // build full target path
        Path targetFile = targetDir.resolve(file.getFileName());

        System.out.println("File: " + file);

        Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Moved to: " + targetFile);
    }
}
