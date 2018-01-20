import sys

fin = open("outofplace.in", "r")

count = int(fin.readline().strip())

for i in range(count):
    numbers.append(int(fin.readline().strip()))

num_of_swaps = 0
swap_forward = None
while True:
    hasSwap = False
    for i in range(1, len(numbers)):
        #print("i", i)
        if numbers[i] < numbers[i-1]:
            # has out of place number, need to swap
            # first figure out swap forward or backwards
            big_ones_ahead = 0
            for ii in range(0, i):
                if numbers[ii] >= numbers[i]:
                    big_ones_ahead += 1
            #print("big_ones_ahead", big_ones_ahead)
            if swap_forward is None:
                    if big_ones_ahead > 1:
                        swap_forward = True
            #print("swap forward", swap_forward)
            if swap_forward:
                # need to figure out which index to swap
                j = i - 1
                if i - 1 > 0:
                    for j in range(i-2, -1, -1):
                        #print("j", j)
                        if numbers[j] != numbers[i - 1]:
                            j = j + 1
                            break
                #print("swap i, j", i, j)
                numbers[i], numbers[j] = numbers[j], numbers[i]
                #print("numbers after swap", numbers)
                num_of_swaps += 1
                #print("numbers of swaps", num_of_swaps)
                hasSwap = True
                break
            else:
                # need to swap the i -1 backwards to the right place
                x = i - 1
                y = i
                #print("backwards", x, y)
                if y == len(numbers) - 1:
                    pass
                else:
                    for y in range(i, len(numbers)):
                        if numbers[y] != numbers[i]:
                            y = y - 1
                            break
                numbers[x], numbers[y] = numbers[y], numbers[x]
                num_of_swaps += 1
                #print("backwards: numbers of swaps", num_of_swaps)
                hasSwap = True
                break
    if not hasSwap:
        break
fout = open("outofplace.out", "w")
#print(num_of_swaps)
print(num_of_swaps, file=fout)
