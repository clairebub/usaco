# Given a list of numbers that's mostly sorted, such as
# 1, 2, 7, 4, 5, 5, 6, 10
# I say it's mostly sorted because other than 7 moved from it's correct
# position to a different position, the rest of the numbers are in the right
# position as they are supposed to be.
# If you know only ONE number changed its position in a sorted list, and you
# want to put the list back to be sorted, but the only allow operation you can
# take is to swap a pair of numbers, how many swaps do you need to take? and
# what's the minimum?
# TODO:
# 1. how do you find the element who is out of place?
# 2. once you find the element who is out of place? how do you swap?
#    hint, if the element became out of place by moving forward, you can
#    move it backwards by swapping the number behind it; what if the element
#    became out of place by moving backwards?
