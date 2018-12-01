<?php
$file = new SplFileObject("input.txt");
$found = false;
$value = 0;
$seen = array();
$iteration = 0;
while (!$found) {
  while (!$file->eof() && !empty($file->current())) {
      $value += $file->current();
      $found = array_key_exists(strval($value), $seen);
      if ($found) { echo $value ." (iterations: ".$iteration.")\r\n"; break; }
      $seen[strval($value)] = 1;
      $file->next();
  }
  if ($found) { break; }
  $file->rewind();
  $iteration++;
}
