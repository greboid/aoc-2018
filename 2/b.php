<?php
$file = file(__DIR__."/input.txt",FILE_IGNORE_NEW_LINES);
$results=array();
foreach ($file as $a) {
  foreach ($file as $b) {
    $diff = levenshtein($a, $b);
    if ($diff ==1) {
      echo implode(array_intersect_assoc(str_split($a), str_split($b)))."\r\n";
      break 2;
    }
  }
}
