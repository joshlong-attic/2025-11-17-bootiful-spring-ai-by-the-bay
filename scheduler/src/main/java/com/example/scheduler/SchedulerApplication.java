package com.example.scheduler;

import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class SchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }

//    @Bean
//    MethodToolCallbackProvider methodToolCallbackProvider(DogAdoptionScheduler scheduler) {
//        return MethodToolCallbackProvider
//                .builder()
//                .toolObjects(scheduler)
//                .build();
//    }
}

@Service
class DogAdoptionScheduler {

    @McpTool(description = "help to schedule an appointment to pick up or adopt a dog  " +
            "from a Pooch Palace location")
    String schedule(
            @McpArg(description = "the id of the dog") int dogId,
            @McpArg(description = "the name of the dog") String dogName) {
        var i = Instant
                .now()
                .plus(3, ChronoUnit.DAYS)
                .toString();
        IO.println("scheduling " + dogId + '/' + dogName + " for pick up @ " + i);
        return i;
    }
}
