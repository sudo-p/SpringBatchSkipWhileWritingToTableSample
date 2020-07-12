//package com.example.batchprocessing.JobConfigurations;
//
//import java.util.Arrays;
//
//import com.example.batchprocessing.Listeners.StepResultListener;
//import com.example.batchprocessing.Listeners.StepSkipListener;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableBatchProcessing
//public class SkippableExceptionDuringWriteSample {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public SkippableExceptionDuringWriteSample(JobBuilderFactory jobBuilderFactory,
//                                               StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public ItemReader<Integer> itemReader() {
//        return new ListItemReader<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6,7,8,9)) {
//            @Override
//            public Integer read() {
//                Integer item = super.read();
//                System.out.println("reading item = " + item);
//                return item;
//            }
//        };
//    }
//
//    @Bean
//    public ItemProcessor<Integer, Integer> itemProcessor() {
//        return item -> {
//            System.out.println("processing item = " + item);
//            return item;
//        };
//    }
//
//    @Bean
//    public ItemWriter<Integer> itemWriter() {
//        return items -> {
//            System.out.println("About to write chunk: " + items);
//            for (Integer item : items) {
//                if (item.equals(5)) {
//                    System.out.println("Throwing exception on item " + item);
//                    throw new IllegalArgumentException("Sorry, no 5 here!");
//                }
//                System.out.println("writing item = " + item);
//            }
//        };
//    }
//
//    @Bean
//    public Step step() {
//        return this.stepBuilderFactory.get("step")
//                .<Integer, Integer>chunk(3)
//                .reader(itemReader())
//                .processor(itemProcessor())
//                .writer(itemWriter())
//                .faultTolerant()
//                .skip(IllegalArgumentException.class)
//                .skipLimit(10)
//                .listener(new StepSkipListener())
//                .listener(new StepResultListener())
//                .build();
//    }
//
//    @Bean
//    public Job skippableExceptionDuringWriteSampleJob() {
//        return this.jobBuilderFactory.get("skippableExceptionDuringWriteSampleJob")
//                .start(step())
//                .build();
//    }
//
//}