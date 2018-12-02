<?php
$file = new SplFileObject(__DIR__."/input.txt");
$value = 0;
while (!$file->eof() && !empty($file->current())) {
    $value += intval($file->current());
    $file->next();
}
echo $value."\r\n";
