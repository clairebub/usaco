fin = open("teleport.in", "r")
fout = open("teleport.out", "w")

n_piles = int(fin.readline().strip())
piles = []
for _ in range(n_piles):
  start, end = fin.readline().strip().split()
  piles.append((int(start), int(end)))
#print(piles)
y = 0
for a, b in piles:
    d = abs(a-b)
    x = min(abs(a), abs(b))
    if d <= x:
        y += d
        continue    
print("y", y)
print(y, file=fout)

