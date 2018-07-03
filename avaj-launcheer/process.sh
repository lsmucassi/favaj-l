#!/usr/bin/env bash
function error {
  echo "Usage: $0 [SECONDS]"
  case $1 in
    1) echo "Pass one argument only"
    exit 1
    ;;
    2) echo "Parameter must be a number"
    exit 2
    ;;
    *) echo "Unknown error"
    exit 999
  esac
}

[[ $# -ne 1 ]] && error 1
[[ $1 =~ ^[0-9]+$ ]] || error 2

duration=${1}
barsize=$((`tput cols` - 7))
unity=$(($barsize / $duration))
increment=$(($barsize%$duration))
skip=$(($duration/($duration-$increment)))
curr_bar=0
prev_bar=

echo "\033[32mCompiling ...\033[00m \n"
#compile java
find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt

for (( elapsed=1; elapsed<=$duration; elapsed++ ))
do
# Elapsed
prev_bar=$curr_bar
  let curr_bar+=$unity
  [[ $increment -eq 0 ]] || {
    [[ $skip -eq 1 ]] &&
      { [[ $(($elapsed%($duration/$increment))) -eq 0 ]] && let curr_bar++; } ||
    { [[ $(($elapsed%$skip)) -ne 0 ]] && let curr_bar++; }
  }
  [[ $elapsed -eq 1 && $increment -eq 1 && $skip -ne 1 ]] && let curr_bar++
  [[ $(($barsize-$curr_bar)) -eq 1 ]] && let curr_bar++
  [[ $curr_bar -lt $barsize ]] || curr_bar=$barsize
  for (( filled=0; filled<=$curr_bar; filled++ )); do
    printf "▇"
  done

  # Remaining
  for (( remain=$curr_bar; remain<$barsize; remain++ )); do
    printf " "
  done

  # Percentage
  printf "| %s%%" $(( ($elapsed*100)/$duration))

  # Return
  sleep 1
  printf "\r"
done

printf "\n"
echo "\nDone, now run: "
#echo "java -cp . wtc.jhb.school.simulator.Simulator src/wtc/jhb/school/scenarios.txt"
echo "And then cat: simulation.txt"
exit 0
