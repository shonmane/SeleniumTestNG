package utility.configreader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import utility.enums.AppNames;

import java.io.File;
import java.io.IOException;

public final class ConfigReader {

    private static final ThreadLocal<EnvironmentConfig> config = new ThreadLocal<>();

    private ConfigReader() {}

    public static void loadConfig(String envName) {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            File file = new File("src/test/resources/config/environment/" + envName + ".yaml");
            EnvironmentConfig envConfig = mapper.readValue(file, EnvironmentConfig.class);
            config.set(envConfig);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + envName, e);
        }
    }

    public static AppsConfig getApp(String appName) {
        EnvironmentConfig envConfig = config.get();
        if (envConfig == null) {
            throw new IllegalStateException("Config not loaded for this thread");
        }
        AppsConfig appConfig = envConfig.apps.get(appName);
        if (appConfig == null) {
            throw new IllegalArgumentException("No config found for app: " + appName);
        }
        return appConfig;
    }

    public static AppsConfig getApp(AppNames appName){
        return getApp(appName.getKey());
    }

    public static String getBrowser() {
        return config.get().browser;
    }
}