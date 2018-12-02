<?php
$file = new SplFileObject(__DIR__."/input.txt");
$twos = array();
$threes = array();
while (!$file->eof() && !empty($file->current())) {
  $counts = array_count_values(str_split($file->current()));
  foreach ($counts as $letter => $count) {
    if ($count == 2) {
      $twos[$file->current()] = 1;
    }
    if ($count == 3) {
      $threes[$file->current()] = 1;
    }
  }
  $file->next();
}
echo "Part 1: " .count($twos) * count($threes)."\n";

$file = file(__DIR__."/input.txt",FILE_IGNORE_NEW_LINES);
$results=array();
foreach ($file as $a) {
  foreach ($file as $b) {
    $diff = levenshtein($a, $b);
    if ($diff ==1) {
      echo "Part 2: " .implode(array_intersect_assoc(str_split($a), str_split($b)))."\n";
      break 2;
    }
  }
}
