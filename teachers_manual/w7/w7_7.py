import sys
fin = sys.stdin
fin.readline()
words = fin.readline().strip().split()

def get_transformations(w):
  # return a list of transformations for the input
  x = []
  # now learn the slicing, for example given string x = 'abc', what does
  # x[1:], basically takes the string x but takes a substring from index 1, which is 'bc'
  # x[:1], basically takes the string but takes a substring in the beginning and end at
  # index 1
  # so all the transformation is taking the substring skipping the characters at various
  # positions and putting the skipped characters back at the end
  for i in range(1, len(w)):
    head_chars = w[:i]
    back_chars = w[i:]
    new_word = back_chars + head_chars
    x.append(new_word)
  return x

# look, because I need to check the word with the next word, I can only process up to the
# last to next word, thus, range other to len(...) - 1
count = 0
for i in range(len(words) - 1):
  if words[i] in get_transformations(words[i+1]):
    print(words[i])
    count += 1
print(count)
