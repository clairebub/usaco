import sys
fin = open("cow.in", "r")
fout = open("cow.out", "w")
fin.readline()
str = fin.readline().strip().lower()
num = 0
for i in range(len(str)):
  if str[i] != 'c':
    continue
  for j in range(i+1, len(str)):
    if str[j] != 'o':
      continue
    for k in range(j+1, len(str)):
      if str[k] != 'w':
        continue
      num += 1
print(num, file=fout)
