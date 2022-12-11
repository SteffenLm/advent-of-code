![](https://img.shields.io/static/v1?label=%F0%9F%93%85%20Completed%20Days&message=11&color=blue&style=flat-square)
![](https://img.shields.io/static/v1?label=%E2%AD%90%20Gained%20Stars&message=22&color=yellow&style=flat-square)

# Advent of Code (2022)

In this repository, I'm about to provide solutions for the Advent of Code[^aoc] puzzles using [Kotlin][kotlin] language.

## Motivation

I decided to do the Advent of Code in Kotlin, as it is my favorite programming language which I use way to infrequently.
I'm only
able to use them in my side projects and unfortunately not in my professional career at my current employer.  
I use this event to get a broader knowledge about the Kotlin standard library.

## Repo Structure

For each day/puzzle there is a separate file inside the source folder with the pattern `DayXX` (e.g. `Day01`). This file
contains
all the code that was used to solve the puzzle.  
The correctness is checked using a JUnit 5 tests. There is one test file for each day using the name
pattern `DayXXTest` (e.g.
`Day01Test`). These tests use the sample input from the puzzle explanation to check if the code is correct. Thereafter,
the
code is called with the real puzzle input.  
This input data is stored in text files in the project resources. The file name pattern of the input files
is `DayXX.txt`
(e.g. `Day01.txt`). The mentioned sample data is stored in files with the name pattern `DayXX_test.txt` (
e.g. `Day01_test.txt`).

## Usage

The described files described in the section "Repo Structure" can be generated for a specific day using the
command `make new_day DAY=<day>` (e.g. `make new_day DAY=1`).

## Setup

To download the actual puzzle input when executing the `make new_day DAY=...` command (only possible when the puzzle for
the requested day was already released) and be able to automatically upload your puzzle answers, you need to store your
session cookie to the file `session.txt` in the project root.
The file content should look like `session=abc...xyz`.

## Log of my AoC Journey

I tried to log my results and thoughts for each puzzle after solving it.

| #   | Name                         | Stars | Comment                                                                                                                                |
| --- |------------------------------|-------|----------------------------------------------------------------------------------------------------------------------------------------|
| 1   | [Calorie Counting][1]        | ⭐⭐    | Was not able to find a way to calculate the calorie sum as stream, so I did it in a loop.                                              |
| 2   | [Rock Paper Scissors][2]     | ⭐⭐    | Gues there is a more performant way, but I focused more on readability.                                                                |
| 3   | [Rucksack Reorganization][3] | ⭐⭐    | In my opinion this was the most easy puzzle so far.                                                                                    |
| 4   | [Camp Cleanup][4]            | ⭐⭐    | Also a pretty easy one, when sorting the pair of section assignments.                                                                  |
| 5   | [Supply Stacks][5]           | ⭐⭐    | Today, parsing the input was more difficult than the actual puzzle.                                                                    |
| 6   | [Tuning Trouble][6]          | ⭐⭐    | Pretty short puzzle today. Took me less than 10 minutes for both parts.                                                                |
| 7   | [No Space Left On Device][7] | ⭐⭐    | Today I was not able to work on the puzzle when it was released. Had to postpone it, until I finished working.                         |
| 8   | [Treetop Tree House][8]      | ⭐⭐    | Found a pretty generic solution to 'iterate' over the trees. Don't know it is easy to understand.                                      |
| 9   | [Rope Bridge][9]             | ⭐⭐    | Unfortunatelly I had to spend a long time debugging, as I had a bug when moving diagonal.                                              |
| 10  | [Cathode-Ray Tube][10]       | ⭐⭐    | Very sub-optimal circumstances today. First I started more than half an hour late, then it took me ages to understand the second part. |
| 11  | [Monkey in the Middle][11]   | ⭐⭐    | A lot of parsing today. But puzzle itself was not that hard.                                                                           |
| 12  | [???][12]                    |       |                                                                                                                                        |
| 13  | [???][13]                    |       |                                                                                                                                        |
| 14  | [???][14]                    |       |                                                                                                                                        |
| 15  | [???][15]                    |       |                                                                                                                                        |
| 16  | [???][16]                    |       |                                                                                                                                        |
| 17  | [???][17]                    |       |                                                                                                                                        |
| 18  | [???][18]                    |       |                                                                                                                                        |
| 19  | [???][19]                    |       |                                                                                                                                        |
| 20  | [???][20]                    |       |                                                                                                                                        |
| 21  | [???][21]                    |       |                                                                                                                                        |
| 22  | [???][22]                    |       |                                                                                                                                        |
| 23  | [???][23]                    |       |                                                                                                                                        |
| 24  | [???][24]                    |       |                                                                                                                                        |
| 25  | [???][24]                    |       |                                                                                                                                        |

[aoc]: https://adventofcode.com

[kotlin]: https://kotlinlang.org

[1]: https://adventofcode.com/2022/day/1

[2]: https://adventofcode.com/2022/day/2

[3]: https://adventofcode.com/2022/day/3

[4]: https://adventofcode.com/2022/day/4

[5]: https://adventofcode.com/2022/day/5

[6]: https://adventofcode.com/2022/day/6

[7]: https://adventofcode.com/2022/day/7

[8]: https://adventofcode.com/2022/day/8

[9]: https://adventofcode.com/2022/day/9

[10]: https://adventofcode.com/2022/day/10

[11]: https://adventofcode.com/2022/day/11

[12]: https://adventofcode.com/2022/day/12

[13]: https://adventofcode.com/2022/day/13

[14]: https://adventofcode.com/2022/day/14

[15]: https://adventofcode.com/2022/day/15

[16]: https://adventofcode.com/2022/day/16

[17]: https://adventofcode.com/2022/day/17

[18]: https://adventofcode.com/2022/day/18

[19]: https://adventofcode.com/2022/day/19

[20]: https://adventofcode.com/2022/day/20

[21]: https://adventofcode.com/2022/day/21

[22]: https://adventofcode.com/2022/day/22

[23]: https://adventofcode.com/2022/day/23

[24]: https://adventofcode.com/2022/day/24

[25]: https://adventofcode.com/2022/day/25
