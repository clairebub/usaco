fin = open("billboard.in", "r")
[x1, y1, x2, y2] = [int(x) for x in fin.readline().strip().split()]
[xx1, yy1, xx2, yy2] = [int(x) for x in fin.readline().strip().split()]
print(x1, x2, y1, y2)
print(xx1, xx2, yy1, yy2)
area = (x2 - x1) * (y2 - y1)
print("default", area)
if xx1 <= x1 and yy1 <= y1 and xx2 >= x2 and yy2 >= y2:
    # totally covers
    area = 0
    print("totally", area)
elif yy2 >= y2 and yy1 <= y1 and xx2 >= x2 and xx1 < x2 and xx1 > x1:
    # covers right
    area = (xx1 - x1) * (y2 - y1)
    print("right", area)
elif yy2 >= y2 and yy1 <= y1 and xx1 <= x1 and xx2 > x1 and xx2 < x2:
    # covers left
    area = (x2 - xx2) * (y2 - y1)
    print("left", area)
elif xx1 <= x1 and xx2 >= x2 and yy2 >= y2 and yy1 > y1 and yy1 < y2:
    # covers top
    area = (x2 - x1) * (yy1 - y1)
    print("top", area)
elif xx1 <= x1 and xx2 >= x2 and yy1 <= y1 and yy2 > y1 and yy2 < y2:
    # covers bottom
    area = (x2 - x1) * (y2 - yy2)
    print("bottom", area)

fout = open("billboard.out", "w")
print(area, file=fout)
