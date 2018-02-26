fin = open("teleport.in", "r")
fout = open("teleport.out", "w")

n_piles = int(fin.readline().strip())
piles = []
for _ in range(n_piles):
  start, end = [int(x) for x in fin.readline().strip().split()]
  piles.append((start, end))
print(piles)
min_d = 0
to_minimize = []

for a, b in piles:
    d = abs(a-b)
    ax = abs(a) # since a need to be moved to x first
    if ax >= d:
        min_d += d
    else:
        min_d += ax
        to_minimize.append(b) # y can be used to minimum sum of distance to b
print("to_minimize", to_minimize)
if len(to_minimize) > 1:
    sorted_to_minimize = sorted(to_minimize)
    print("sorted_to_minimize", sorted_to_minimize)
    mid_point = len(sorted_to_minimize) // 2
    y = sorted_to_minimize[mid_point]
    print("mid_point", mid_point, "y", y)
    for i in range(0, mid_point):
        min_d += abs(y - sorted_to_minimize[i])
    for i in range(mid_point + 1, len(sorted_to_minimize)):
        min_d += abs(y - sorted_to_minimize[i])

print("min_d", min_d)
#print(total_d, file=fout)
