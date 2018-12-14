use std::fs::File;
use std::io::Read;

fn main() {
    let mut file = File::open("/app/5/input.txt").expect("File not found");
    let mut input = String::new();
    file.read_to_string(&mut input).expect("Unable to read file");
    input = input.trim().to_string();
    println!("Part 1: {}", react(&input, None));
    println!("Part 2: {}", fully_react(&input));
}

fn react(input: &str, skip: Option<char>) -> usize {
  let mut stack = Vec::<char>::new();
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
  return stack.len()
}

fn fully_react(input: &str) -> usize {
  return (b'a'..=b'z').map(|c| react(&input, Some(c as char))).min().unwrap()
}

fn reacts(a: char, b: char) -> bool {
    return a != b && a.eq_ignore_ascii_case(&b);
}
