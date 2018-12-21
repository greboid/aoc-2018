extern crate hashbrown;
extern crate time;

use hashbrown::HashSet;
use std::fs::File; 
use std::io::prelude::*;
use std::io::BufReader;
use time::PreciseTime;

fn main() {
    let start = PreciseTime::now();
    let f = File::open("input.txt").expect("File not found");
    let reader = BufReader::new(f);
    let numbers: Vec<i32> = reader
        .lines()
        .map(|e| e.unwrap().parse::<i32>().unwrap())
        .collect();
    println!("Part 1: {}", part_one(&numbers));
    println!("{}.", start.to(PreciseTime::now()));
    println!("Part 2: {}", part_two(&numbers));
    println!("{}.", start.to(PreciseTime::now()));
}

fn part_one(input: &Vec<i32>) -> i32 {
    input.iter().sum::<i32>()
}

fn part_two(input: &Vec<i32>) -> i32 {
    let mut seen = HashSet::with_capacity(200000);
    let mut sum = 0;
    loop {
        for n in input {
            sum += n;
            if seen.contains(&sum) {
                return sum;
            }
            seen.insert(sum);
        }
    }
}

