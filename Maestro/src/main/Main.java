package main;

import java.time.Duration;
import java.time.Instant;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("running ...");
		Instant start = Instant.now();
		System.out.println("doing stuff ...");
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end).getNano());
	}

}
