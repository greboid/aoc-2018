use std::collections::HashMap;
use std::fs::File; 
use std::io; 
use std::io::prelude::*; 
use std::io::BufReader; 

fn main() -> io::Result<()> {
    let f = File::open("/app/1/input.txt")?;
    let reader = BufReader::new(f);
    let numbers: Vec<i32> = reader
        .lines()
        .map(|e| e.unwrap().parse::<i32>().unwrap())
        .collect();
    part_one(&numbers)?;
    part_two(&numbers)?;
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
    let mut seen = HashMap::with_capacity(2^18);
    let mut freq = 0;
    for i in (0..input.len()).cycle() {
        freq += input[i];
        if seen.contains_key(&freq) {
            break;
        }
        seen.insert(freq, 1);
    }
    println!("Part 2: {}", freq);
    Ok(())
}
