package com.company;

import java.util.concurrent.RecursiveTask;

/**
 * Created by cernym23 on 28.11.16.
 */
public class Biggest {

    public class BiggestThread extends Thread {
        int[] arr;
        int from, to;
        int result;
        public BiggestThread(int[] arr, int from, int to) {
            this.to = Math.min(to, arr.length);
            this.from = from;
            this.arr = arr;
            this.result = Integer.MIN_VALUE;
        }
        @Override
        public void run() {
            for (int i=from; i<to;i++){
                result = Math.max(result, arr[i]);
            }
        }
    }

    public static int proc = 4;

    public int biggest(int[] arr) {

        int part = (arr.length + proc) / proc;

        BiggestThread[] threads = new BiggestThread[proc];


        for (int i = 0; i < proc; i++) {
            threads[i] = new BiggestThread(arr, i*part, i*part+part+1);
            threads[i].start();
        }

        int result = Integer.MIN_VALUE;
        for (BiggestThread thread : threads) {
            while (thread.isAlive()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.out.print("Was interupted!!");
                }
            }
            result = Math.max(result, thread.result);
        }

        return result;
    }


    public int biggestExec(int[] arr) {

        return 0;
    }

    public class BiggestTask extends RecursiveTask<Integer> {
        int[] arr;
        int from, to;

        public BiggestTask(int[] arr, int from, int to) {
            this.to = Math.min(to, arr.length);
            this.from = from;
            this.arr = arr;
        }

        /* TODO: get this done */
        @Override
        protected Integer compute() {
            int result = Integer.MIN_VALUE;
            for (int i=from; i<to;i++){
                result = Math.max(result, arr[i]);
            }
            return result;
        }
    }

    public Integer biggest(int[] arr, int from, int to) {
        Integer res = null;
        to = Math.min(to, arr.length);
        for (int i=from; i<to;i++){
                res = max(res, arr[i]);
        }
        return res;
    }

    private Integer max(Integer a, int b) {
        if (a == null || a < b) return b;
        return a;
    }

    public static void main(String[] args) {
        Biggest b = new Biggest();
        int[] arr = new int[]{1,12,3,5,481,55,45,6,54,5,54,5,4,5,6444,55,6,55,2,1,5,3,1111,1,5};


        int result = b.biggest(arr);
        int resultS = b.biggest(arr, 0, arr.length);
        int resultE = b.biggestExec(arr);

        System.out.println(resultE  + " <> " + result  + " <> " + resultS);
    }

}
