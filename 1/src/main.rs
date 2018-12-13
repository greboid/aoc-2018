extern crate hashbrown;

use hashbrown::HashSet;
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
    println!("Part 1: {}", input.iter().sum::<i32>());
    Ok(())
}

fn part_two(input: &Vec<i32>) -> io::Result<()> {
    let mut seen = HashSet::new();
    let frequency = input
        .iter()
        .cycle()
        .scan(0, |frequency, &change| {
            *frequency += change;
            Some(*frequency)
        })
        .find(|frequency| !seen.insert(*frequency))
        .unwrap();
    println!("Part 2: {}", frequency);
    Ok(())
}
