<?php
$twos = array();
$threes = array();
$file = file(__DIR__."/input.txt",FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
foreach ($file as $line) {
  $counts = array_count_Values(str_split($line));
  foreach ($counts as $letter => $count) {
    if ($count == 2) {
      $twos[$line] = 1;
    }
    if ($count == 3) {
      $threes[$line] = 1;
    }
  }
}
echo "Part 1: " .count($twos) * count($threes)."\n";
foreach ($file as $a) {
  foreach ($file as $b) {
    $diff = levenshtein($a, $b);
    if ($diff ==1) {
      echo "Part 2: " .implode(array_intersect_assoc(str_split($a), str_split($b)))."\n";
      break 2;
    }
  }
}
