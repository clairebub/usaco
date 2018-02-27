fin = open("reststops.in", "r")
fout = open("reststops.out", "w")

line_1 = fin.readline()
trail_length, n_stops, pace_farmer, pace_bessie = [int(x) for x in line_1.strip().split()]
pace_diff = pace_farmer - pace_bessie
where_stops = []
points_stops = []

for i in range(n_stops):
    where, points = [int(x) for x in fin.readline().strip().split()]
    where_stops.append(where)
    points_stops.append(points)
#print("where_stops", where_stops)
#print("points_stops", points_stops)

total_points = 0
last_stop = -1
last_stop_dist = 0

# sort the stops by points_value, reversed
# ps_2 is a list of tuples of (stop_index, points_value)
ps_2 = sorted(enumerate(points_stops), key=lambda x:x[1], reverse=True)
#print("ps_2", ps_2)
for stop_index, _ in ps_2:
    print("last_stop", last_stop, "stop_index", stop_index)
    if stop_index > last_stop:
        seconds = (where_stops[stop_index] - last_stop_dist) * pace_diff
        total_points += points_stops[stop_index] * seconds
        last_stop_dist = where_stops[stop_index]
        last_stop = stop_index
    if last_stop == len(where_stops) - 1:
        break

#while True:
    # looking for where to stop from here on
#    next_stop = last_stop + 1
#    for i in range(last_stop + 2, n_stops):
#        if points_stops[i] >= points_stops[next_stop]:
#            next_stop = i
#    seconds = (where_stops[next_stop] - last_stop_dist) * pace_diff
#    total_points += points_stops[next_stop] * seconds
#    last_stop = next_stop
#    last_stop_dist = where_stops[last_stop]
#    if last_stop == n_stops - 1:
#        break
print(total_points)
print(total_points, file=fout)
