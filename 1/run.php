<?php
$lines = file(__DIR__."/input.txt");
$found = false;
$value = 0;
$seen = array();
$iteration = 0;
while (!$found) {
  foreach ($lines as $line) {
    $value += intval($line);
    $found = array_key_exists(strval($value), $seen);
    if ($found) { echo "Part 2: " .$value ." (iterations: ".$iteration.")\r\n"; break 2; }
    $seen[strval($value)] = 1;
  }
  $iteration++;
  if ($iteration == 1) {
    echo "Part 1: ".$value."\n";
  }
}
