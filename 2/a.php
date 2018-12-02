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
echo count($twos) * count($threes)."\n";
