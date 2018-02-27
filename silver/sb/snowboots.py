fin = open("snowboots.in", "r")
fout = open("snowboots.out", "w")

n_tiles, n_boots = [int(x) for x in fin.readline().strip().split()]
snow_depths = [int(x) for x in fin.readline().strip().split()]
boots = []
for _ in range(n_boots):
  depth, distance = [int(x) for x in fin.readline().strip().split()]
  boots.append((depth, distance))

# minimum number of boots needed to reach ith tile
min_boots_for_tile = {}
for i in range(n_tiles):
    min_boots_for_tile[i] = None
min_boots_for_tile[0] = 0

# use the ith boots to go as far as you can and update the min_boots_for_tile
# along the way
def use_boot_i(i_boots, where_start):
#    print("use_boot_i", i_boots, "starting at", where_start)
    max_depth, max_steps = boots[i_boots]
    last_tile = min(n_tiles - 1, where_start + max_steps)
    last_good = None
    for landing_tile in range(where_start+1, last_tile + 1):
        if max_depth >= snow_depths[landing_tile]:
            last_good = landing_tile
            if min_boots_for_tile[landing_tile] is None or i_boots < min_boots_for_tile[landing_tile]:
                min_boots_for_tile[landing_tile] = i_boots
    #if last_good is not None:
    #    use_boot_i(i_boots, last_good)


starting_tile = 0
while True:
    # from the starting tile, use all the boots and see where you land
    # and update the minimum boots needed to land on that tile if it's so
    for i_boots in range(min_boots_for_tile[starting_tile], n_boots):
#        use_boot_i(i_boots, starting_tile)
        max_depth, max_steps = boots[i_boots]
        if max_depth < snow_depths[starting_tile]:
            continue
        last_tile = min(n_tiles - 1, starting_tile + max_steps)
        for landing_tile in range(starting_tile+1, last_tile + 1):
            if max_depth >= snow_depths[landing_tile]:
                if min_boots_for_tile[landing_tile] is None or i_boots < min_boots_for_tile[landing_tile]:
                    min_boots_for_tile[landing_tile] = i_boots
    # find the next starting tile
    for i in range(starting_tile + 1, n_tiles):
        if not min_boots_for_tile[i] is None:
            starting_tile = i
            break
    if starting_tile == n_tiles -1:
        break
    if min_boots_for_tile[n_tiles - 1] == 0:
        break
#print(min_boots_for_tile[n_tiles-1])
print(min_boots_for_tile[n_tiles-1], file=fout)
