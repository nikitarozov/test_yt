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
        is.lines().parallel().forEach(aggregator::run);
        aggregator.getResults().forEach((T) -> System.out.println(T.getValue().getResult()));
    }


}
