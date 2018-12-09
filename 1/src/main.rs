use std::collections::HashSet; 
use std::fs::File; 
use std::io; 
use std::io::prelude::*; 
use std::io::BufReader; 
use std::time::Instant;

fn main() -> io::Result<()> {
    let start = Instant::now();
    let f = File::open("/app/1/input.txt")?;
    let reader = BufReader::new(f);
    let numbers: Vec<i32> = reader
        .lines()
        .map(|e| e.unwrap().parse::<i32>().unwrap())
        .collect();
    println!("File: {:?}", start.elapsed() / 1000000);
    part_one(&numbers)?;
    println!("Part 1: {:?}", start.elapsed() / 1000000);
    part_two(&numbers)?;
    println!("Part 2: {:?}", start.elapsed() / 1000000);
    Ok(())
}

fn part_one(input: &Vec<i32>) -> io::Result<()> {
    let mut sum = 0;
    for i in 0..input.len() {
      sum += input[i];
    }
    println!("Part 1: {}", sum);
    Ok(())
}

fn part_two(input: &Vec<i32>) -> io::Result<()> {
    let mut seen = HashSet::with_capacity(2^18);
    let mut freq = 0;
    for i in (0..input.len()).cycle() {
        freq += input[i];
        if seen.contains(&freq) {
            break;
        }
        seen.insert(freq);
    }
    println!("Part 2: {}", freq);
    Ok(())
}
