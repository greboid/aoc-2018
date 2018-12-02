<?php
$file_handle = fopen(__DIR__."/input.txt", "r");
$value = 0;
while (!feof($file_handle)) {
   $value += intval(fgets($file_handle));
}
echo "Part 1: ".$value."\r\n";

$file = new SplFileObject(__DIR__."/input.txt");
$found = false;
$value = 0;
$seen = array();
$iteration = 0;
while (!$found) {
  while (!$file->eof() && !empty($file->current())) {
      $value += intval($file->current());
      $found = array_key_exists(strval($value), $seen);
      if ($found) { echo "Part 2: " .$value ." (iterations: ".$iteration.")\r\n"; break 2; }
      $seen[strval($value)] = 1;
      $file->next();
  }
  $file->rewind();
  $iteration++;
}
