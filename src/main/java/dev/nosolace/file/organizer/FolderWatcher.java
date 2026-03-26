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

    private final Path folder;
    private final FileEventHandler handler;

    public FolderWatcher(Path folder, FileEventHandler handler) {
        this.folder = folder;
        this.handler = handler;
    }

    public void start() throws Exception {
        WatchService watchService = FileSystems.getDefault().newWatchService();

        folder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        while (true) {
            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    Path fileName = (Path) event.context();

                    if (!isTempFile(fileName)) {
                        Path filePath = folder.resolve(fileName);
                        System.out.println("New file at: " + filePath);
                        handler.handle(filePath);
                    }
                }
            }

            key.reset();
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
