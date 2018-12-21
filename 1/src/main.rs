extern crate hashbrown;

use hashbrown::HashSet;
use std::fs::File; 
use std::io::prelude::*;
use std::io::BufReader; 

fn main() {
    let f = File::open("input.txt").expect("File not found");
    let reader = BufReader::new(f);
    let numbers: Vec<i32> = reader
        .lines()
        .map(|e| e.unwrap().parse::<i32>().unwrap())
        .collect();
    part_one(&numbers);
    part_two(&numbers);
}

fn part_one(input: &Vec<i32>) {
    println!("Part 1: {}", input.iter().sum::<i32>());
}

fn part_two(input: &Vec<i32>) -> i32 {
    let mut seen = HashSet::new();
    let mut sum = 0;
    loop {
        for n in input {
            sum += n;
            if seen.contains(&sum) {
                println!("Part 1: {}", sum);
                return sum;
            }
            seen.insert(sum);
        }
    }
}

