import sys
fin = sys.stdin
num_input = fin.readline().strip()
num_input = int(num_input)
x = fin.readline().strip().split()
numbers = []
for a in x:
  numbers.append(int(a))
print(num_input)
print(numbers)

def fold_number_to_single_digit(n):
  s = str(n)
  my_sum = 0
  for x2 in s:
    my_sum += int(x2)
  if (my_sum >= 10):
    return fold_number_to_single_digit(str(my_sum))
  else:
    return my_sum

#print(fold_number_to_single_digit(1234))

numbers_per_line = 1
numbers_available = num_input
i_number = 0
while numbers_available >= numbers_per_line:
  eadbo = []
  for i in range(numbers_per_line):
    eadbo.append(fold_number_to_single_digit(numbers[i_number]))
    i_number += 1
  for b in eadbo:
    # use end = " " so the print doesn't end with new line
    print(b, end=" ")
  print("")
  numbers_available -= numbers_per_line
  numbers_per_line += 1
