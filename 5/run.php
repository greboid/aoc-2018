<?php
$file = file(__DIR__."/input.txt",FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
$file = $file[0];
function react(&$input) {
  $removed = 0;
  for ($i = 0; $i < strlen($input) -1;) {
    $char = $input{$i};
    $next = $input{$i+1};
    if (((ctype_upper($char) && ctype_lower($next)) && strtolower($char)==$next) || ((ctype_lower($char) && ctype_upper($next)) && strtoupper($char)==$next)) {
      $input{$i}=' ';
      $input{$i+1}=' ';
      $removed++;
    }
    $i++;
  }
  $input = str_replace(' ','',$input);
  return $removed;
}
function fullyReact(&$input) {
  $removed = 0;
  do {
    $removed = react($input);
  } while ($removed != 0);
  return $input;
}

echo 'Part 1: '.strlen(fullyReact($file))."\n";

$lengths = array();
for($i = 65; $i < 91; $i++) {
  $input = $file;
  $test = str_replace(chr($i), '', $input);
  $test = str_replace(chr($i+32), '', $test);
  $lengths[chr($i)] = strlen(fullyReact($test));
}
echo 'Part 2: '.min($lengths);
