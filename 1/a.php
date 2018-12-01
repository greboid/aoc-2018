<?php
$file = new SplFileObject("input.txt");
$value = 0;
while (!$file->eof() && !empty($file->current())) {
    $value += $file->current();
    $file->next();
}
echo $value."\r\n";
