<?php
$file = file(__DIR__."/input.txt");
$commons = array();
foreach ($file as $a) {
  foreach ($file as $b) {
    $common = array_diff(str_split($a), str_split($b));
    if (count($common) == 1) {
      foreach ($common as $key => $value) {
        if (array_key_exists($key, $commons)) {
         echo 'A: '.$a;
         echo 'B: '.$b;
         echo implode(array_intersect(str_split($a), str_split($b)));
         echo implode('',array_unique(array_intersect(str_split($a), str_split($b))));
         break 3;
        } else {
          $commons[$key] = $value;
        }
      }
    }
  }
}
