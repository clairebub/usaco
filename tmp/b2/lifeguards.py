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
sorted_start_times = sorted(cover_dict)
sorted_cover_time = [[start, cover_dict[start]] for start in sorted_start_times]
#print("sorted_cover_time", sorted_cover_time)

# now merge the sorted cover time
def merge_cover_time(interval_list):
    merged_cover_time = [interval_list[0]]
    for y in interval_list[1:]:
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
    return merged_cover_time

merge_sorted_cover_time = merge_cover_time(sorted_cover_time)
#print("merge_sorted_cover_time", merge_sorted_cover_time)

def get_total_time(sorted_merged_list):
    total_time = 0
    for x in sorted_merged_list:
        total_time += x[1] - x[0]
    return total_time

def take_one_out_max_time(foo):
    max_time = None
#    print("foo", foo)
    for i in range(len(foo)-1):
        head = foo[:i]
        tail = foo[i+1:]
        bar = merge_sorted_cover_time = merge_cover_time(head + tail)
        total_time = get_total_time(bar)
        if max_time is None or total_time > max_time:
            max_time = total_time
    return max_time

answer = None
if same_start:
    answer = get_total_time(merge_sorted_cover_time)
#    print("same start", answer)
else:
    answer = take_one_out_max_time(sorted_cover_time)
#    print("normal", answer)

print("answer", answer)
print(answer, file=fout)
