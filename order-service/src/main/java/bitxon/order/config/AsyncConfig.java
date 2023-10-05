package bitxon.order.config;

import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.core.task.support.CompositeTaskDecorator;
import org.springframework.core.task.support.ContextPropagatingTaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Collection;
import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig {

    @Bean
    public TaskDecorator contextPropagatingTaskDecorator() {
        return new ContextPropagatingTaskDecorator();
        // return (runnable) -> ContextSnapshotFactory.builder().build().captureAll().wrap(runnable);
    }

    @Bean
    public Executor asyncTaskExecutor(Collection<TaskDecorator> allTaskDecorator) {
        return new ThreadPoolTaskExecutorBuilder()
            .corePoolSize(5)
            .maxPoolSize(12)
            .queueCapacity(20)
            .threadNamePrefix("async-task-")
            .taskDecorator(new CompositeTaskDecorator(allTaskDecorator))
            .build();
    }
}
