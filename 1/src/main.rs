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
    println!("Part 1: {}", part_one(&numbers));
    println!("Part 2: {}", part_two(&numbers));
}

fn part_one(input: &Vec<i32>) -> i32 {
    input.iter().sum::<i32>()
}

fn part_two(input: &Vec<i32>) -> i32 {
    let mut seen = HashSet::with_capacity(200000);
    let mut sum = 0;
    let mut check;
    loop {
        for n in input {
            sum += n;
            check = seen.replace(sum);
            if check != None {
                return check.unwrap()
            }
        }
    }
}

