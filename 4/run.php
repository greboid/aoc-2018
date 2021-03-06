<?php

$file = file(__DIR__."/input.txt",FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
sort($file);
$actions = array();
$guard = 0;
$sleepcount = array();
$sleep = 0;
$mostsleep = 0;
$mostsleepminute = 0;
$sleepiest = [0,0,0];
$sleepiestminute = [0,0,0];
$sleepminutes = array();
$sleepfrequency = array();
foreach ($file as $line) {
  #[1518-03-13 23:56] Guard #2539 begins shift
  preg_match("/\[[0-9]+-[0-9]+-[0-9]+ [0-9]+:([0-9]+)\] (.*)/",$line,$matches);
  $action = ['minute'=>$matches[1], 'action'=>$matches[2]];
  if (preg_match('/.*#([0-9]+).*/', $action['action'], $matches)) {
    $guard = $matches[1];
    if (!isset($sleepcount[$guard])) {
      $sleepcount[$guard] = 0;
    }
    if (!isset($sleepminutes[$guard])) {
      $sleepminutes[$guard] = array();
    }
  } else if ($action['action'] == "falls asleep") {
    $sleep = $action['minute'];
  } else {
    $sleepcount[$guard] += $action['minute'] - $sleep;
    if ($sleepcount[$guard] > $mostsleep) {
      $sleepiest = [$guard,$sleepcount[$guard], $action['minute']];
      $mostsleep = $sleepcount[$guard];
    }
    for ($i = $sleep; $i < $action['minute']; $i++) {
      if (!isset($sleepminutes[$guard][$i])) {
        $sleepminutes[$guard][$i] = 0;
      }
      $sleepminutes[$guard][$i]++;
      if ($sleepminutes[$guard][$i] > $mostsleepminute) {
        $mostsleepminute=$sleepminutes[$guard][$i];
        $sleepiestminute = [$guard,$mostsleepminute, $action['minute']];
      }
    }
  }
}
echo 'Part 1: '.(array_keys($sleepminutes[$sleepiest[0]], max($sleepminutes[$sleepiest[0]]))[0] * $sleepiest[0])."\n";
echo 'Part 2: '.array_search(max($sleepminutes[$sleepiestminute[0]]), $sleepminutes[$sleepiestminute[0]]) * $sleepiestminute[0]."\n";
