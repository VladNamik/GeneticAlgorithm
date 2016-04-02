package main;

import algorithm.Phenotype;
import algorithm.RandomNumber;

import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i=0; i< 20; i++)
            queue.add(RandomNumber.random.nextInt());

        System.out.println(new LinkedList<Integer>(queue));
    }
}
