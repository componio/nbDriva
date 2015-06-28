/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.componio.opencms.projectstructure.plugin.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Tom
 */
public class ModulePropertiesHelper {

    public static Properties getModuleProperties(String individualConfigFile, String defaultConfigFile) throws FileNotFoundException, IOException {
        Properties moduleProperties = new Properties();
        FileInputStream inputStream = null;
        if (!individualConfigFile.isEmpty()) {
            inputStream = new FileInputStream(individualConfigFile);
            moduleProperties.load(inputStream);
        }

        if (!defaultConfigFile.isEmpty()) {
            inputStream = new FileInputStream(defaultConfigFile);
            Properties default_props = new Properties();
            default_props.load(inputStream);
            for (String key : default_props.stringPropertyNames()) {
                if (!moduleProperties.containsKey(key)) {
                    moduleProperties.put(key, default_props.getProperty(key));
                }
            }
        }
        return moduleProperties;
    }
}
