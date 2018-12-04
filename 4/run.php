<?php

$file = file(__DIR__."/input.txt",FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
sort($file);
$actions = array();
foreach ($file as $line) {
  #[1518-03-13 23:56] Guard #2539 begins shift
  preg_match("/\[[0-9]+-[0-9]+-[0-9]+ [0-9]+:([0-9]+)\] (.*)/",$line,$matches);
  $actions[] = ['minute'=>$matches[1], 'action'=>$matches[2]];
}
$guard = 0;
$sleepcount = array();
$sleep = 0;
$mostsleep = 0;
$mostsleepminute = 0;
$sleepiest = [0,0,0];
$sleepiestminute = [0,0,0];
$sleepminutes = array();
$sleepfrequency = array();
foreach ($actions as $action) {
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
      if (!isset($sleepfrequency[$guard][$i])) {
        $sleepfrequency[$guard][$i] = 0;
      }
      $sleepfrequency[$guard][$i]++;
      if ($sleepfrequency[$guard][$i] > $mostsleepminute) {
        $mostsleepminute=$sleepfrequency[$guard][$i];
        $sleepiestminute = [$guard,$mostsleepminute, $action['minute']];
      }
    }
  }
}
echo 'Part 1: '.(array_keys($sleepminutes[$sleepiest[0]], max($sleepminutes[$sleepiest[0]]))[0] * $sleepiest[0])."\n";
echo 'Part 2: '.array_search(max($sleepfrequency[$sleepiestminute[0]]), $sleepfrequency[$sleepiestminute[0]]) * $sleepiestminute[0]."\n";
