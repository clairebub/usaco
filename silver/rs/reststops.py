fin = open("reststops.in", "r")
fout = open("reststops.out", "w")

trail_length, n_stops, pace_farmer, pace_bessie = [int(x) for x in fin.readline().strip().split()]
pace_diff = pace_farmer - pace_bessie
where_stops = []
points_stops = []
for i in range(n_stops):
    where, points = [int(x) for x in fin.readline().strip().split()]
    where_stops.append(where)
    points_stops.append(points)

total_points = 0
last_stop = -1
last_stop_dist = 0
while True:
    # looking for where to stop from here on
    next_stop = last_stop + 1
    for i in range(last_stop + 2, n_stops):
        if points_stops[i] >= points_stops[next_stop]:
            next_stop = i
    seconds = (where_stops[next_stop] - last_stop_dist) * pace_diff
    total_points += points_stops[next_stop] * seconds
    last_stop = next_stop
    last_stop_dist = where_stops[last_stop]
    if last_stop == n_stops - 1:
        break
print(total_points, file=fout)
