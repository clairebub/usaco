fin = open("lifeguards.in", "r")
count = int(fin.readline().strip())
cover_list = []
for i in range(count):
    [start, end] = [int(x) for x in fin.readline().strip().split()]
    cover_list.append([start, end])
print("cover_list", cover_list)
# what each lifeguards really cover by itself only
really_cover_list = []
for i in range(len(cover_list)):
    others = cover_list[:]
    mine = others.pop(i)
    print("mine", mine, "others", others)
    mine_list = [mine]
    for x in others:
        print("mine_list", mine_list, "x", x)
        mine_list_2 = []
        for m in mine_list:
            print("processing m", m, "x", x)
            if x[0] <= m[0] and x[1] >= m[1]:
                # totally cover
                #print("  total cover")
                pass
            elif x[0] <= m[0] and x[1] < m[1] and x[1] > m[0]:
                # covers left
                #print("  cover left")
                mine_list_2.append([x[1], m[1]])
            elif x[1] >= m[1] and x[0] < m[1] and x[0] > m[0]:
                # covers right
                #print("  cover right")
                mine_list_2.append([m[0], x[0]])
            elif x[0] > m[0] and x[0] < m[1] and x[1] > m[0] and x[1] < m[1]:
                # covers middle
                #print("  cover middle")
                mine_list_2.append([m[0], x[0]])
                mine_list_2.append([x[1], m[1]])
            else:
                # covers nothing
                #print("  cover nothing")
                mine_list_2.append(m)
        mine_list = mine_list_2
        print("after x", x, "mine_list is", mine_list)
    print("*** for m", mine, "really_cover is", mine_list)
    really_cover_list.append(mine_list)
print("really_cover_list", really_cover_list)
really_cover_time_list = []
for m_list in really_cover_list:
    total = 0
    for x in m_list:
        total += x[1] - x[0]
    really_cover_time_list.append(total)
print("really_cover_time_list", really_cover_time_list)
# who covered the least
amount_least = None
who_covered_least = -1
for i in range(len(really_cover_time_list)):
    if amount_least is None or really_cover_time_list[i] < amount_least:
        amount_least = really_cover_time_list[i]
        who_covered_least = i
print("amount_least", amount_least, "who", i)

# calculate the total covered time by all lifeguard
print("cover_list", cover_list)
sorted_cover_time = []
for x in cover_list:
    # sort them by start time
    addedToLeft = False
    for i in range(len(sorted_cover_time)):
        if x[0] <= sorted_cover_time[i][0]:
            sorted_cover_time.insert(i, x)
            addedToLeft = True
            break
    if not addedToLeft:
        sorted_cover_time.append(x)
# now merge them
print("sorted_cover_time", sorted_cover_time)
merged_cover_time = [sorted_cover_time.pop(0)]
for y in sorted_cover_time:
    # only need to deal with the last in the merged_cover_time
    x = merged_cover_time.pop(-1)
    # now merge x and y
    if y[0] > x[1]:
        # no overlap
        merged_cover_time.append(x)
        merged_cover_time.append(y)
    elif y[1] <= x[1]:
        # inside
        merged_cover_time.append(x)
    elif y[0] <= x[1]:
        # overlap
        merged_cover_time.append([x[0], y[1]])
    else:
        raise Error("what?", x, y)
print("merged_cover_time", merged_cover_time)
total_time = 0
for x in merged_cover_time:
    total_time += (x[1] - x[0])
print("total_time", total_time)
answer = total_time - amount_least
print("answer", answer)
fout = open("lifeguards.out", "w")
print(total_time - amount_least, file=fout)
