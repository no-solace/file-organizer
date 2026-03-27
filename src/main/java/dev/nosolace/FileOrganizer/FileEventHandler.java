package dev.nosolace.FileOrganizer.*;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author no-solace
 */
public class FileEventHandler {

    private final FolderManager manager;

    public FileEventHandler(FolderManager manager) {
        this.manager = manager;
    }

    public void handle(Path path) {
        try {
            manager.moveFile(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isTempFile(Path path) {
        String name = path.getFileName().toString().toLowerCase();
        System.out.println("File name: " + name);
        return name.endsWith(".crdownload")
                || name.endsWith(".tmp")
                || name.endsWith(".part");
    }
}
