import sys
fin = sys.stdin
s = fin.readline().strip()

# take an operator and two operands and return the math result
def do_the_math(op, operands):
#  print('op', operands)
  if op == '+':
    return operands[0] + operands[1]
  elif op == '-':
    return operands[0] - operands[1]
  elif op == '*':
    return operands[0] * operands[1]
  else:
    return operands[0] / operands[1]

op = None
operands = []
last_operand = ''
# extract the operands and operator from the string
for x in s:
  if x >= '0' and x <= '9':
    # has to be digit now
    last_operand += x
  else:
    # seeing a non-digit, it means time to finish reading the number if already started
    if last_operand != '':
      operands.append(int(last_operand))
      last_operand = ''
    if x in ['+', '-', '*', '/']:
      op = x
    # if you get two operands, time to do the math and reset
    if len(operands) == 2:
        print(do_the_math(op, operands), end=' ')
        operands = []
        op = None
print('')
