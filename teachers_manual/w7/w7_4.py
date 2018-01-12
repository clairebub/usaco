# Python program to convert Roman Numerals
# to Numbers
# Example:
# II: 1+1 => 2
# IX: -1 + 10 => 9
# LX: 50 + 10 => 60
# LIX: 50 + -1 + 10 => 59
def roman_to_int(roman_str):
    nums = {'M':1000, 'D':500, 'C':100, 'L':50, 'X':10, 'V':5, 'I':1}
    sum = 0
    for i in range(len(roman_str)):
            value = nums[roman_str[i]]
            # If the next place holds a larger number, this value is negative
            if i+1 < len(roman_str) and nums[roman_str[i+1]] > value:
                sum -= value
            else: sum += value
    return sum

import sys
fin = sys.stdin
fin.readline()
input_numbers = fin.readline().strip().split()
print(input_numbers)
for x in input_numbers:
  print(roman_to_int(x), end=" ")
print("")
