package dev.nosolace.file.organizer;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author no-solace
 */
public class FileOrganizer {
    private static final String DOWNLOADS_PATH = "C:\\Users\\ADMIN\\Downloads";

    public static void main(String[] args) throws Exception {

        Path downloads = Paths.get(DOWNLOADS_PATH);

        FolderManager directoryManager = new FolderManager();
        FileEventHandler handler = new FileEventHandler(directoryManager);
        FolderWatcher watcher = new FolderWatcher(downloads, handler);

        watcher.start();
    }
}
