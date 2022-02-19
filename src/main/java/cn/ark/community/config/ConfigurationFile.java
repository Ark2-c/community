package cn.ark.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

/**
 * @program: community
 * @description:
 * @author: Ark
 * @create: 2022-02-11 23:51
 **/
@Configuration
public class ConfigurationFile {
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
