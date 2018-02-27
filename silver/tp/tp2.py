fin = open("teleport.in", "r")
fout = open("teleport.out", "w")

n_piles = int(fin.readline().strip())
piles = []
for _ in range(n_piles):
  start, end = [int(x) for x in fin.readline().strip().split()]
  piles.append((start, end))
#print(piles)
min_d = 0
to_minimize = []
y_candidates = []

for a, b in piles:
    d = abs(a-b)
    ax = abs(a) # since a need to be moved to x first
    if ax >= d:
        min_d += d
    else:
        to_minimize.append((a, b)) # y can be used to minimum sum of distance to b
        y_candidates.append(b)
#print("to_minimize", to_minimize)
def get_d(y):
    new_d = 0
    for a, b in to_minimize:
        d1 = abs(a-b)
        d2 = abs(a) + abs (y-b)
        new_d += min(d1, d2)
    return new_d

#print("y_candidates", y_candidates)
remaining_d = None
y_candidates = sorted(y_candidates)
#print("y2", y_candidates)
y_1 = 0
y_2 = len(y_candidates) - 1
while y_2 > y_1:
    d1 = get_d(y_candidates[y_1])
    d2 = get_d(y_candidates[y_2])
    d3 = min(d1, d)
    if remaining_d is None or d3 < remaining_d:
        remaining_d = d3
    if d1 == d2:
        remaining_d = d1
        break
    if d1 < d2:
        y_2 = (y_1 + y_2) // 2
    else:
        y_1 = (y_1 + y_2) // 2

min_d += remaining_d
#print("min_d", min_d)
print(min_d, file=fout)
