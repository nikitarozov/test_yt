package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    private static Logger LOG = LoggerFactory
            .getLogger(DemoApplication.class);

    public DemoApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        File file = ResourceUtils.getFile("classpath:instruments.txt");
        BufferedReader is = new BufferedReader(new FileReader(file));
        Aggregator aggregator = new Aggregator();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        is.lines().forEach((S) -> {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    aggregator.run(S);
                }
            });
        });
        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        aggregator.getResults().forEach((T) -> System.out.println(T.getValue().getResult()));
    }


}
