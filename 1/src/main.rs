extern crate hashbrown;
extern crate time;

use hashbrown::HashSet;
use std::fs::File; 
use std::io::prelude::*;
use std::io::BufReader;
use std::io::SeekFrom;

fn main() {
    let f = File::open("input.txt").expect("File not found");
    let mut reader = BufReader::new(f);
    let mut sum = 0;
    let mut temp;
    let mut iteration = 0;
    let mut check;
    let mut seen = HashSet::with_capacity(200000);
    loop {
        for line in reader.by_ref().lines() {
            temp = line.unwrap().parse::<i32>().unwrap();
            sum += temp;
            check = seen.replace(sum);
            if check != None {
                println!("Part 2: {}", check.unwrap());
                return
            }
        }
        if iteration == 0 {
            println!("Part 1: {}", sum);
        }
        reader.seek(SeekFrom::Start(0)).expect("Can't seek");
        iteration += 1;
    }
}

