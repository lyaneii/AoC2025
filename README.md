# 🎄 Advent of Code 2025 🎄 Java Solutions
### Solutions
| 🎄                                                                                                | 🎄                                                                                            | 🎄                                                                                           | 🎄     |🎄|🎄|🎄|
|---------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|--------|-|-|-|
| [#1](https://github.com/lyaneii/AoC2025/blob/master/src/main/java/com/lyaneii/aoc/days/Day1.java) | [#2](https://github.com/lyaneii/AoC2025/blob/master/src/main/java/com/lyaneii/aoc/days/Day2.java) | [#3](https://github.com/lyaneii/AoC2025/blob/master/src/main/java/com/lyaneii/aoc/days/Day3.java) | [#4](https://github.com/lyaneii/AoC2025/blob/master/src/main/java/com/lyaneii/aoc/days/Day4.java) | [#5](https://github.com/lyaneii/AoC2025/blob/master/src/main/java/com/lyaneii/aoc/days/Day5.java)  | [#6](https://github.com/lyaneii/AoC2025/blob/master/src/main/java/com/lyaneii/aoc/days/Day6.java) | [#7](https://github.com/lyaneii/AoC2025/blob/master/src/main/java/com/lyaneii/aoc/days/Day7.java) |
| [#8](https://github.com/lyaneii/AoC2025/blob/master/src/main/java/com/lyaneii/aoc/days/Day8.java)                                                                                                | #9                                                                                            | #10                                                                                          | #11    | #12 | x |x

### Introduction
I see Advent of Code as a great way to dive into a new language, [last year I tried it in C#](https://github.com/lyaneii/AoC2024) and it actually gave me a headstart on the job I was applying for.
 Now I am trying it in Java this year, but admittedly I am a little lazy on the exception handling :'). One nice thing I found about this year's AoC is that the number of puzzles has been cut in half, so I am curious if the difficulty spikes much harder than before.

*Sidenote: learning java is also a way for me to possibly get into minecraft modding :D* 

The custom http client I made follows the automation guidelines on the [/r/adventofcode](https://www.reddit.com/r/adventofcode) community wiki, link to the FAQ is -> [here](https://www.reddit.com/r/adventofcode/wiki/faqs/automation).
- The example input and real input are downloaded once, then stored locally in the resources folder.
- The inputs won't be requested again if they already exist in the resource folder.
- Requests are limited to once every 15 minutes, reminder -> [guidelines](https://www.reddit.com/r/adventofcode/comments/1pa472d/reminder_please_throttle_your_aoc_traffic/). Although they are manual calls it is still limited by storing the time of the last request locally and comparing it when we are trying to request through the http client.

**Anyhow thank you for taking the time to read this and if you like the solutions, consider leaving a ⭐!**
