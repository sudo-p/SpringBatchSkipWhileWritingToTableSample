//package com.example.batchprocessing.Listeners;
//
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.StepExecutionListener;
//
//public class StepResultListener implements StepExecutionListener {
//
//    @Override
//    public void beforeStep(StepExecution stepExecution) {
//        System.out.println("Called beforeStep().");
//    }
//
//    @Override
//    public ExitStatus afterStep(StepExecution stepExecution) {
//        System.out.println("Called afterStep().");
//        System.out.println("skipped records: " + stepExecution.getSkipCount());
//        return ExitStatus.COMPLETED;
//    }
//}
