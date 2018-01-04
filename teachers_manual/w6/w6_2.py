import sys
fin = sys.stdin
fin.readline()
line = fin.readline().strip().split()
digits = []
for x in line:
  a = []
  for i in x:
    a.append(int(i))
  digits.append(a)
print(digits)

result = digits[0]
for x in digits:
  print("looking at this one", x)
  print("result now is: ", result)
  r2 = []
  for i in result:
    if i in x:
      r2.append(i)
  result = r2
print(result)
