import sys
fin = sys.stdin
fin.readline()
digits = [int(x) for x in fin.readline().strip().split()]

# get the common digits from two list of digits
def get_common_digits(d1, d2):
  result = []
  # go through every digit in d1, if it is also in d2, put it in result, which
  # contain the common digits
  for x in str(d1):
    if x in str(d2):
      result.append(int(x))
  return result

common_digits = get_common_digits(digits[0], digits[1])
for i in range(2, len(digits)):
  new_common_digits = get_common_digits(common_digits, digits[i])
  common_digits = new_common_digits
print(common_digits)
print(sorted(common_digits)[-1])
