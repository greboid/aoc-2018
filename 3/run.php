<?php
ini_set('memory_limit', '200M');
$file = file(__DIR__."/input.txt",FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

$matches = array();
$claims = array();
$grid = array();
$overlapCount = array();
$claimIDs = array();
foreach ($file as $line) {
  preg_match("/#([0-9]+) @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)/",$line,$matches);
  $claims[$matches[1]] = ['x' => $matches[2], 'y' => $matches[3], 'w' => $matches[4], 'h' => $matches[5]];
  $claimIDs[$matches[1]] = $matches[1];
}
unset($file);
foreach ($claims as $id => $claim) {
  for($i = $claim['x']; $i < ($claim['x'] + $claim['w']); $i++) {
    for($j = $claim['y']; $j < ($claim['y'] + $claim['h']); $j++) {
      if (isset($grid[$i][$j]) && is_array($grid[$i][$j])) {
        $overlapCount[$i.'x'.$j]=1;
      }
      $grid[$i][$j][] = $id;
      if (count($grid[$i][$j]) > 1) {
        foreach ($grid[$i][$j] as $value) {
          unset($claimIDs[$value]);
        }
      }
    }
  }
}
echo "Part 1: ".count($overlapCount)."\n";
echo "Part 2: ".array_values($claimIDs)[0]."\n";
