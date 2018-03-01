fin = open("reststops.in", "r")
fout = open("reststops.out", "w")

ll, nn, rf, rb = fin.readline().strip().split()
ll, nn, rf, rb = int(ll), int(nn), int(rf), int(rb)
xx = []
cc = []
for i in range(nn):
    x, c = fin.readline().strip().split()
    x, c = int(x), int(c)
    xx.append(x)
    cc.append(c)
start = 0
total_tp = 0
last_stop_dist = 0
while True:
    stop = start # the ith stop
    for i in range(start + 1, nn):
        if cc[i] >= cc[stop]:
            stop = i
    t = (xx[stop] - last_stop_dist) * (rf - rb) 
    total_tp += cc[stop] * t
    last_stop_dist = xx[stop]
    start = stop + 1 
    if start > nn - 1:
        break
print(total_tp, file=fout)
