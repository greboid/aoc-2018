<?php
$file = file(__DIR__."/input.txt",FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
$file = $file[0];

$pairs = array();
$letters = array_keys(count_chars(strtolower($file), 1));
foreach ($letters as $letter) {
  $pairs[] = chr($letter).chr($letter-32);
  $pairs[] = chr($letter-32).chr($letter);
}

function react($input) {
  global $pairs;
  $removed = 0;
  do {
    $input = str_replace($pairs, '', $input, $removed);
  } while ($removed != 0);
  return $input;
}

echo 'Part 1: '.strlen(react($file))."\n";;

$lengths = array();
for($i = 65; $i < 91; $i++) {
  $input = $file;
  $test = str_replace([chr($i),chr($i+32)], '', $input);
  $lengths[chr($i)] = strlen(react($test));
}
echo 'Part 2: '.min($lengths);
