package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.guice.module.SpringModule;
import service.archivesManage.ArchivesManageService;

/**
 * Created by wangyiran on 29/8/2016.
 */
public class MySpringModule extends SpringModule {
    public MySpringModule() {
        super(new AnnotationConfigApplicationContext(AppConfig.class));
    }

}
