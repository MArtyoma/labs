#!/bin/bash

url="{git(lab|hub) url}"
dir="{lab dir}"
file="new_file.txt"

git clone $url
cd $dir
touch $file
git add $file
git commit -m "Добавлен новый файл"
git push
