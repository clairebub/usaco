fin = open("outofplace.in")
count = int(fin.readline().strip())
numbers = []
for i in range(count):
    numbers.append(int(fin.readline().strip()))

def swap_left_if_needed(x):
    for i in range(1, len(x)):
        if x[i] < x[i-1]:
            j = i - 1
            while j-1 >= 0 and x[j-1] == x[j]:
                j = j - 1
            x[i], x[j] = x[j], x[i]
            return True
    return False

def swap_right_if_needed(x):
    for i in range(0, len(x)-1):
        print("comparing", x[i], x[i+1])
        if x[i] > x[i+1]:
            j = i + 1
            while j + 1 < len(x) and x[j+1] == x[j]:
                j = j + 1
            x[i], x[j] = x[j], x[i]
            return True
    return False

number_of_left_swaps = 0
numbers_for_left = numbers[:]
while True:
    if swap_left_if_needed(numbers_for_left):
        number_of_left_swaps += 1
    else:
        break

number_of_right_swaps = 0
numbers_for_right = numbers[:]
while True:
    if swap_right_if_needed(numbers_for_right):
        number_of_right_swaps += 1
    else:
        break

print("number_of_left_swaps", number_of_left_swaps)
print("number_of_right_swaps", number_of_right_swaps)
fout = open("outofplace.out", "w")
if number_of_left_swaps < number_of_right_swaps:
    print(number_of_left_swaps, file=fout)
else:
    print(number_of_right_swaps, file=fout)
