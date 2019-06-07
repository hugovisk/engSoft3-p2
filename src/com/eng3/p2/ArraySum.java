package com.eng3.p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;


public class ArraySum {

	public static void main(String[] args) {
		ArrayList<ArrayList<String>> cpu = new ArrayList<>();
		ArrayList<ArrayList> sumCpu = new ArrayList<>();
		
		ArrayList<String> cpuRegister = new ArrayList<>(Arrays.asList(
			"ubuntu", 
			"49.00", 
			"49.00"
		));
		
		ArrayList<String> cpuRegister2 = new ArrayList<>(Arrays.asList(
			"ubuntu", 
			"51.00", 
			"51"
		));
		
		ArrayList<String> cpuRegister3 = new ArrayList<>(Arrays.asList(
				"ubuntu", 
				"50.00", 
				"50"
			));
		
		ArrayList<String> cpuRegister4 = new ArrayList<>(Arrays.asList(
				"ubuntu", 
				"100.00", 
				"100"
			));
		
		ArrayList sum1 = new ArrayList<>(Arrays.asList(
				"ubuntu", 
				0.0, 
				0.0
			));
		
		sumCpu.add(sum1);
		
		cpu.add(cpuRegister);
		cpu.add(cpuRegister2);
		cpu.add(cpuRegister3);
		cpu.add(cpuRegister4);

//		Stream.of(cpu).forEach(i -> i.forEach(j -> {
//			double test = j.stream()
//			.filter(value -> value.matches("\\-?\\d*\\.?\\d+"))
//			.mapToDouble(Double::valueOf)
//			.reduce(0, Double::sum);
//			
//			System.out.println(test);
//		}));
		
//		Stream.of(cpu).forEach(i -> i.forEach(j -> {
			IntStream.range(0, cpu.size()).forEach(k -> {
				System.out.println(cpu.get(k));
				double value = (double) sumCpu.get(0).get(1) + Double.parseDouble(cpu.get(k).get(1));
				double value2 = (double) sumCpu.get(0).get(2) + Double.parseDouble(cpu.get(k).get(2));
				
				sumCpu.get(0).set(1, value);
				sumCpu.get(0).set(2, value2);
			});
			
			System.out.println("-"+sumCpu);
//		}));
		
		
//		for(int i = 0; i < cpu.size(); i++) {
//			for(int k = 0; k < cpu.size(); k++) {
//				System.out.println(cpu.get(k));
//				System.out.println(sumCpu.get(0).get(1));
//				double value = (double) sumCpu.get(0).get(1) + Double.parseDouble(cpu.get(k).get(1));
//				double value2 = (double) sumCpu.get(0).get(2) + Double.parseDouble(cpu.get(k).get(2));
//				
//				sumCpu.get(0).set(1, value);
//				sumCpu.get(0).set(2, value2);
//			}
//			System.out.println(sumCpu);
////		}
			
		
//		Stream.of(cpu).forEach(i -> i.forEach( j -> {
//			System.out.println(j);
//		}));
		
//		System.out.println(sumCpu);


	}
}
