# given the coordinates of two rectangles, how do you know if and how they
# overlap.
# a rectangle can be represented with coordinates of its lower left and
# top right corners, such as
#
#   --------------------(x2, y2)-
#   |                            |
#   |                            |
#   |                            |
#   (x1, y1)----------------------
# To solve the problems, can you think about how they overlap? I can think of
# the following ways:
# 1. not overlapping at all
# 2. one if totally covering the other
# 3. overlapping the top
# 4. overlapping the bottom
# 5. overlapping the left
# 6. overlapping the right
# 7. another case?

# i will start with a few examples, and you should complete the rest.
# for rectangle a, its coordinates are (ax1, ay1)for bottom left and
#  (ax2, ay2) for upper right; for rectangle b, its coordinates are (bx1, by1)
# for bottom left and (bx2, by2) for upper right.

# 2. b totally covering a
#   bx1 <= ax1 and by1 <= ay1 and bx2 >= ax2 and by2 >= by1
# 3. b overlapping the top of a
#   bx1 <= ax1 and bx2 >= ax2 and by2 >= ay2 and by1 <ay2 and by1 > ay1
#
# TODO:
# finish the rest of the cases
