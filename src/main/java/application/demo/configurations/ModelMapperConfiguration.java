package application.demo.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
