use std::fs::File;
use std::io::Read;

fn main() {
    let mut file = File::open("/app/5/input.txt").expect("File not found");
    let mut input = String::new();
    file.read_to_string(&mut input).expect("Unable to read file");
    input = input.trim().to_string();
    let part1 = react(&input, None);
    println!("Part 1: {}", part1.len());
    println!("Part 2: {}", fully_react(&part1));
}

fn react(input: &str, skip: Option<char>) -> String {
  let mut stack = Vec::<char>::with_capacity(2^18);
  for c in input.chars() {
    if skip.is_some() && (skip.unwrap() == c || reacts(c, skip.unwrap())) {
      //skip
    } else {
      if !stack.is_empty() && reacts(*stack.last().unwrap(), c) {
        stack.pop();
      } else {
        stack.push(c);
      }
    }
  }
  return stack.into_iter().collect()
}

fn fully_react(input: &str) -> usize {
  return (b'a'..=b'z').map(|c| react(&input, Some(c as char)).len()).min().unwrap()
}

fn reacts(a: char, b: char) -> bool {
    return a != b && a.eq_ignore_ascii_case(&b);
}

