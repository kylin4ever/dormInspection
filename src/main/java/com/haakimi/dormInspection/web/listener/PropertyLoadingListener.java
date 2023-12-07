package com.haakimi.dormInspection.web.listener;

import com.haakimi.dormInspection.utils.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

@Component
public class PropertyLoadingListener implements EnvironmentAware {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void setEnvironment(Environment environment) {
        try {
            String propertyFileLocation = environment.getProperty("properties.file.location");
            String[] arr = propertyFileLocation.split(",");
            for(String a:arr){
                Properties pros = new Properties();
                pros.load(new FileInputStream(new File(a)));
                AppConfig.addProperties(pros);
            }
        } catch (Exception e) {
            log.error("异常", e);
        }
    }
}
                                                              