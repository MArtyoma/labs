#!/bin/bash

cd /tmp
mkdir LabWork
cd LabWork
echo "Hello, World!" > example.txt
cp example.txt copy_example.txt
mv copy_example.txt renamed_example.txt
rm renamed_example.txt
