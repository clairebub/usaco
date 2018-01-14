import sys
fin = sys.stdin
fin.readline()
words = fin.readline().strip().split()

def order_of_letter(x):
  # return a number indicating order of the letter in the alphabet
  # 'a' => 1, 'b' => 2, ...., 'z' => 26
  return ord(x) - ord('a') + 1

def degree_of_word(w):
  total = 0
  for x in w:
    total += order_of_letter(x)
  return total

degree_of_words = {}

for x in words:
  total = degree_of_word(x)
  if total not in degree_of_words:
    degree_of_words[total] = [x]
  else:
    degree_of_words[total].append(x)
print(len(degree_of_words.keys()))
