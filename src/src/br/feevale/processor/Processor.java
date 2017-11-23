/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.br.feevale.processor;

import src.br.feevale.processor.share.Job;
import src.br.feevale.processor.util.ArrayParser;

import java.rmi.Naming;

public class Processor {

    Job job;

    private void getJobTracker() {
        String url = "rmi://localhost:" + 8877 + "/tracker";
        try {
            job = (Job) Naming.lookup(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listJobs() {
        int[] pendingJob;
        String unparsedJob;
        try {
            while ((unparsedJob = job.getJob()) != null) {
                pendingJob = ArrayParser.parseStringIntoArray(unparsedJob);
                System.out.println("Job: " + unparsedJob);
                Thread.sleep(400);

                int[] sortedArray = bubbleSort(pendingJob);
                job.sendJobResult(ArrayParser.parseArrayIntoString(sortedArray));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int[] bubbleSort(int[] arr) {
        int swap;

        for(int i = 0; i < arr.length; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            for(int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    swap = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = swap;
                }
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        Processor p = new Processor();
        p.getJobTracker();
        p.listJobs();
        System.out.println("No more Jobs!");
    }

}
