# Given a list of intervals, each interval as [start, end]. For example,
# [4, 7], [1, 3], [2, 4], [10, 20], [0, 9]
# Imaging it represents the start and end of each segments that has been
# painted, you can clearly see there are overlap in painted segments. How do
# you find the total length of segment painted?
# For example, the segments of [1, 4], [2, 5] has total length of segments
# painted as (5-1) = 4 because [1, 4] and [2, 5] overlap and it becomes
# actually one single segment of [1, 5]
# TODO:
# think of ways to merge the list of segments
#   [3, 5], [1, 4], [8, 9], [17, 19], [10, 18]
# into a list of segments where each of them have no overlap, as
#   [1, 5], [8, 9], [10, 19]
# Hint, would it be easier if the start position of each segments are sorted?
