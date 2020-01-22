use serde::{Deserialize, Serialize};
use std::env;

fn main() {
    let args: String = env::args().last().unwrap();
    println!("{}", args);
    println!("{}", "aa");

    let depth: Vec<i32> = serde_json::from_str(&args).unwrap();
    let a: Vec<i32> = depth.iter().map(|x| x + 1).collect();

    println!("{:?}", a);
}
