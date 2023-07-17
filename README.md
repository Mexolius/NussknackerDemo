# Nussknacker recruitment demo

A quick demo for Nussknacker recruitment

## Setup requirements
- Windows operating system
- docker desktop running
- sbt

## How to run
Open a console window and run `run.bat`

If the application has trouble sending the requests please increase the first timeout in batch file from 15 to a larger value

## Assumptions
- There is a single row of seats on backend
- The rooms are not stored in the database - not mentioned in the requirements
- The console window showing logs needs to support polish characters (I have spent too much time debugging why database doesn't support polish characters and it turns out windows has failed me. I am sure linux would do better :) )