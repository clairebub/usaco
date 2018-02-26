fin = open("lifeguards.in", "r")
fout = open("lifeguards.out", "w")

count = int(fin.readline().strip())
cover_list = []
cover_dict = {}
same_start = False
for i in range(count):
    [start, end] = [int(x) for x in fin.readline().strip().split()]
    if start in cover_dict:
        same_start = True
        if end > cover_dict[start]:
            cover_dict[start] = end
    else:
        cover_dict[start] = end
    cover_list.append([start, end])
#print("cover_list", cover_list)

# sort them by start time
sorted_start_times = sorted(cover_dict)
sorted_cover_time = [[start, cover_dict[start]] for start in sorted_start_times]

# now merge and calc the covered time
def get_cover_time(interval_list):
    merged_cover_time = [interval_list.pop(0)]
    for y in interval_list:
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
#    print("merged_cover_time", merged_cover_time)

    total_time = 0
    for x in merged_cover_time:
        total_time += (x[1] - x[0])
#    print("total_time", total_time)
    return total_time

answer = None
if same_start:
    answer = get_cover_time(sorted_cover_time)
else:
    for i in range(len(sorted_cover_time)):
        t = sorted_cover_time[:]
        t.pop(i)
        t_time = get_cover_time(t)
        if answer is None or t_time > answer:
            answer = t_time
print("answer", answer)
print(answer, file=fout)
