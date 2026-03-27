package dev.nosolace.file.organizer;

import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;

/**
 *
 * @author no-solace
 */

public class FolderWatcher {

    private final Path folderPath;
    private final FileEventHandler fileEventHandler;

    public FolderWatcher(Path folderPath, FileEventHandler fileEventHandler) {
        this.folderPath = folderPath;
        this.fileEventHandler = fileEventHandler;
    }

    public void start() throws Exception {
        WatchService watchService = FileSystems.getDefault().newWatchService();

        folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        while (true) {
            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    Path fileName = (Path) event.context();

                    Path filePath = folderPath.resolve(fileName).normalize();
                    //fileEventHandler.handle(filePath);
                }
            }

            key.reset();
        }
    }
}
