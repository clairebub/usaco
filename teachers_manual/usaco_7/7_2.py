import sys
fin = open("censor.in", "r")
fout = open("censor.out", "w")
s = fin.readline().strip()
t = fin.readline().strip()

# find() is your friend to find a substring in a string
# e.g. 'abc'.find('bc') => 1; 'abc'.find('d') => -1. The function s.find(t)
# returns the index where t occurs in s. If not found, it returns -1
while True:
    index = s.find(t)
    if index < 0:
        break
    # forming the new string, which basically leaving a hole for the removed t
    s = s[:index] + s[index + len(t):]
print(s)
