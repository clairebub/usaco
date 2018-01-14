import sys
fin = sys.stdin
count = fin.readline().strip()
count = int(count)

def parse_eudora_number(s):
  # return the number represented in eudora system
  # housands t, hundreds h, tens n, or units u
  # for example, 32h7u (32 hundreds and 7 units)
  eudura = {'t': 1000, 'h': 100, 'n': 10, 'u': 1}
  num = 0
  start_index = 0
  for i in range(len(s)):
    if s[i] in eudura:
      num += int(s[start_index:i]) * eudura[s[i]]
      start_index = i + 1
      i += 1
  return num

def parse_expression(e):
  num = 0
  plus_index = None
  for i in range(len(e)):
    if e[i] == '+':
      plus_index = i
      break
  if plus_index:
    num = parse_eudora_number(e[:plus_index]) + parse_eudora_number(e[plus_index+1:])
  else:
    num = parse_eudora_number(e)
  return num


#print(parse_eudora_number('32h7u'))
#print(parse_eudora_number('32h7u+7u'))

for i in range(count):
  line = fin.readline().strip()
  for j in range(len(line)):
    if line[j] == '<' or line[j] == '>':
      break
  a = parse_expression(line[:j])
  b = parse_expression(line[j+1:])
  if line[j] == '<' and a < b:
    print("1")
  elif line[j] == '>' and a > b:
    print("1")
  else:
    print("0")
