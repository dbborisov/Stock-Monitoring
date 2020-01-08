package net.lesno.stock.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ApplicationBeenConfiguration {

    private  ModelMapper modelMapper;

   @Bean
    public ModelMapper modelMapper(){
       this.modelMapper = new ModelMapper();
       return  this.modelMapper;
   }
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    GrantedAuthorityDefaults grantedAuthorityDefaults() {
//        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
//    }
}
