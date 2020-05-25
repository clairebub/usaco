from collections import deque
fin = open("snowboots.in", "r")
fout = open("snowboots.out", "w")

n_tiles, n_boots = fin.readline().strip().split()
n_tiles, n_boots = int(n_tiles), int(n_boots)
snow_depths = [int(x) for x in fin.readline().strip().split()]
boots = []
for _ in range(n_boots):
  s, d = fin.readline().strip().split()
  boots.append((int(s), int(d)))

# minimum number of boots discards at the given tile
tile_boots = {}
for i in range(n_tiles):
    tile_boots[i] = None
start = 0
tile_boots[0] = 0

while True:
#    print("start", start)
#    print("tile_boots", tile_boots)
    for i_boots in range(tile_boots[start], n_boots):
        s, d = boots[i_boots]
        for step in range(1, d+1):
            landing_tile = start + step
            if landing_tile < n_tiles and snow_depths[landing_tile] <= s:
                if tile_boots[landing_tile] is None or tile_boots[landing_tile] >  i_boots:
                    tile_boots[landing_tile] = i_boots
    # find the next start
    for i in range(start+1, n_tiles):
        if not tile_boots[i] is None:
            start = i
            break
    if start == n_tiles -1:
        break
                
print(tile_boots[n_tiles-1], file=fout)

