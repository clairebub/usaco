import sys
fin = sys.stdin
fout = sys.stdout

count = int(fin.readline().strip())
entries = []
for i in range(count):
  start, end = fin.readline().strip().split()
  entries.append((int(start), i))
  entries.append((int(end), i))

s_entries = sorted(entries, key=lambda x: x[0])
#print(s_entries)
on_duty = {} # the lifeguards who is currently on duty
alone_times = {} # the alone times for each lifeguard
for i in range(count):
  alone_times[i] = 0
actual_cover_time = 0

# when an event occurs:
#   update who is working
#   when exactly one lifeguard is working, update his alone working time
#   when there's at least one lifeguard on duty, extend the actual cover time 
last = 0 # time of last event
for event in s_entries:
#  print("event", event)
#  print("on_duty", on_duty)
  if len(on_duty) == 1:
    who = list(on_duty)[0]
    alone_times[who] += event[0] - last
  if len(on_duty) >= 1:
    actual_cover_time += event[0] - last
  if event[1] in on_duty:
    del on_duty[event[1]]
  else:
    on_duty[event[1]] = True
#  print("after, on_duty=", on_duty)
#  print("after, actuaal_cover_time=", actual_cover_time)
#  print("after, alone_time=", alone_times)
  last = event[0]

#print("actual_cover", actual_cover_time)
remain = 0
#print("alone_times", alone_times)
for _, v in alone_times.items():
#  print("v", v)
  remain = max(remain, actual_cover_time - v)
print("remain", remain)
  
