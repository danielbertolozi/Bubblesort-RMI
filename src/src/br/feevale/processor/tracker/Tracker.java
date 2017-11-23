/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.br.feevale.processor.tracker;

import src.br.feevale.processor.share.Job;
import src.br.feevale.processor.util.ArrayParser;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tracker {

    static List<String> jobs = Collections.synchronizedList(new ArrayList<String>());
    static List<String> results = Collections.synchronizedList(new ArrayList<String>());
    static int nJobs;

    private void createJobs(int nJobs) {
        Tracker.nJobs = nJobs;
        for (int i = 0; i < nJobs; i++) {
            int[] val = generateRandomArray();
            jobs.add(ArrayParser.parseArrayIntoString(val));
        }
    }

    private int[] generateRandomArray() {
        int[] arr = new int[10000];
        Random generator = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generator.nextInt(10001);
        }
        return arr;
    }

    public static void listResults(){
        if(Tracker.nJobs != results.size()){
            return;
        }
        for(String arr:results){
            System.out.println("Value: " + arr);
        }
    }
    
    private static void startRegistry(int port)
            throws RemoteException {
        try {
            Registry registry
                    = LocateRegistry.getRegistry(port);
            registry.list();
        } catch (RemoteException e) {
            LocateRegistry.createRegistry(port);
        }
    }

    public static void main(String[] args) {
        Tracker t = new Tracker();
        String url;
        t.createJobs(50);
        try {
            startRegistry(8877);
            url = "rmi://localhost:" + 8877 + "/tracker";
            Job job = new BubbleSortJob();
            Naming.rebind(url, job);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Tracker Ready!");
    }

}
