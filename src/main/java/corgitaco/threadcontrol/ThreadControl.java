package corgitaco.threadcontrol;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

@Mod(ThreadControl.MOD_ID)
public class ThreadControl {
    public static final String MOD_ID = "threadcontrol";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve(MOD_ID);

    public static final int MAX_THREAD_COUNT = getThreadCount();

    public ThreadControl() {
    }

    public static int getThreadCount() {
        File file = CONFIG_PATH.resolve("ThreadControl.properties").toFile();

        if (!file.exists()) {
            try {
                Files.createDirectories(file.toPath().getParent());
                Files.write(file.toPath(), "maxLogicalProcessors=255".getBytes());
            } catch (IOException e) {
            }
        }
        try (FileReader reader = new FileReader(file)) {
            Properties p = new Properties();
            p.load(reader);

            try {
                String threadcount = p.getProperty("maxLogicalProcessors");
                return Integer.parseInt(threadcount);
            } catch (NumberFormatException e) {
            }
        } catch (IOException e) {
        }
        return 7;
    }
}
