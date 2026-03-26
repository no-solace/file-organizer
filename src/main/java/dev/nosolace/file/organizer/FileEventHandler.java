package dev.nosolace.file.organizer;

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
}
