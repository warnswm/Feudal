package feudal.utils.configurations;

import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataBaseConfig {

    File file = new File(Feudal.getInstance().getDataFolder().getPath(), "database.yml");
    FileConfiguration databaseConfiguration = YamlConfiguration.loadConfiguration(file);

    public void create() {

        if (file.exists()) return;

        file.getParentFile().mkdir();

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void save() {

        try {
            databaseConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void addDefaults() {

        databaseConfiguration.options().copyDefaults(true);
        databaseConfiguration.addDefault("Mongo.address", "localhost:27017");
        databaseConfiguration.addDefault("Mongo.name", "local");
        databaseConfiguration.addDefault("Mongo.playerCollection", "players");
        databaseConfiguration.addDefault("Mongo.kingdomCollection", "kingdoms");

        save();

    }
    public FileConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
}
