//package pl.coderslab.charity;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.templatemode.TemplateMode;
//
//import java.nio.charset.StandardCharsets;
//
//@Configuration
//public class ThymeleafTemplateConfig {
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.addTemplateResolver(thymeleafTemplateResolver());
//        return templateEngine;
//    }
//
//    @Bean
//    public SpringResourceTemplateResolver thymeleafTemplateResolver(){
//        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
//        emailTemplateResolver.setPrefix("/templates/");
//        emailTemplateResolver.setSuffix(".html");
//        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
//        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        return emailTemplateResolver;
//    }
//}
