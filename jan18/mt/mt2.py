import sys
from collections import deque

fin = sys.stdin
fout = sys.stdout
fin = open("mootube.in", "r")
fout = open("mootube.out", "w")

num_n, num_q = fin.readline().strip().split()
num_n, num_q = int(num_n), int(num_q)
relevance = {}
q = []
def get_input():
  for i in range(num_n-1):
      x, y, r = fin.readline().strip().split()
      x, y, r = int(x), int(y), int(r)
      if x not in relevance:
        relevance[x] = {}
      if y not in relevance: 
        relevance[y] = {}
      relevance[x][y] = r
      relevance[y][x] = r
  for i in range(num_q):
      k, v = fin.readline().strip().split()
      q.append([int(k), int(v)])

def get_answer(k, v):
      to_visit = deque()
      seen = set()
      to_visit.append(v) # a queue of nodes that need to examine new edge from it
      seen.add(v) # already put in the queue
      ret = 0
      while to_visit:
          v1 = to_visit.pop()
          for v2 in relevance[v1]:
            if v2 not in seen and relevance[v1][v2] >= k:
              ret += 1
              to_visit.append(v2) 
              seen.add(v2) 
      return ret

get_input()

for k, v in q:
  ret = get_answer(k, v)
  print(ret, file=fout)
