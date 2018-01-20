import sys

lawn_mower = {}
cow_feed = {}

fin = open("billboard.in", "r")
line = fin.readline().strip().split()
lawn_mower['x1'] = int(line[0])
lawn_mower['y1'] = int(line[1])
lawn_mower['x2'] = int(line[2])
lawn_mower['y2'] = int(line[3])
line = fin.readline().strip().split()
cow_feed['x1'] = int(line[0])
cow_feed['y1'] = int(line[1])
cow_feed['x2'] = int(line[2])
cow_feed['y2'] = int(line[3])
print(lawn_mower)
print(cow_feed)

fout = open("billboard.out", "w")

x = lawn_mower['x2'] - lawn_mower['x1']
y = lawn_mower['y2'] - lawn_mower['y1']

if cow_feed['y2'] >= lawn_mower['y2'] and cow_feed['y1'] <= lawn_mower['y1']:
    # now cow_feed is taller than lawn_mower
    if cow_feed['x1'] >= lawn_mower['x1'] and cow_feed['x1'] <= lawn_mower['x2'] \
     and cow_feed['x2'] >= lawn_mower['x2']:
        x = cow_feed['x1'] - lawn_mower['x1']
    elif cow_feed['x1'] <= lawn_mower['x1'] and cow_feed['x2'] <= lawn_mower['x2'] \
     and cow_feed['x2'] >= lawn_mower['x1']:
        x = lawn_mower['x2'] = cow_feed['x2']
    else:
        pass
if cow_feed['x1'] <= lawn_mower['x1'] and cow_feed['x2'] >= lawn_mower['x2']:
    # now cow_feed is wider than lawn_mower
    if cow_feed['y1'] >= lawn_mower['y1'] and cow_feed['y1'] <= lawn_mower['y2'] \
     and cow_feed['y2'] >= lawn_mower['y2']:
        y = cow_feed['y1'] - lawn_mower['y1']
    elif cow_feed['y1'] <= lawn_mower['y1'] and cow_feed['y2'] <= lawn_mower['y2'] \
     and cow_feed['y2'] >= lawn_mower['y1']:
        y = lawn_mower['y2'] = cow_feed['y2']
    else:
        pass
#else:
#    pass
#if cow_feed['y2'] >= lawn_mower['y2'] and cow_feed['y1'] <= lawn_mower['y1'] \
# and cow_feed['x1'] <= lawn_mower['x1'] and cow_feed['x2'] >= lawn_mower['x2']:
#    # then has to be the size of the lawn_mower
#    x, y = 0, 0
print("x, y", x, y)
xy = x * y
print(xy, file=fout)
