fin = open("mootube.in", "r")
fout = open("mootube.out", "w")

num_n, num_q = fin.readline().strip().split()
num_n, num_q = int(num_n), int(num_q)
relevance = {}
q = []
for i in range(num_n-1):
    x, y, r = fin.readline().strip().split()
    x, y, r = int(x), int(y), int(r)
    if x in relevance:
        x_dict = relevance[x]
        x_dict[y] = r
    else:
        relevance[x] = {}
        relevance[x][y] = r
    if y in relevance:
        y_dict = relevance[y]
        y_dict[x] = r
    else:
        relevance[y] = {}
        relevance[y][x] = r
#print(relevance)
for i in range(num_q):
    k, v = fin.readline().strip().split()
    q.append([int(k), int(v)])
#print(q)
answers = []
for k, v in q:
    #print('------v, k is', v, k)
    if v not in relevance:
        #print('v not in relevance')
        answers.append(0)
        continue
    to_visit = [v]
    visited = {}
    while len(to_visit) > 0:
        #print('visited', visited)
        #print('to_visit', to_visit)
        v_start = to_visit.pop(0)
        for v_end in relevance[v_start].keys():
            if v_end not in visited and v_end != v:
                to_visit.append(v_end)
                score = relevance[v_start][v_end]
                if v_start in visited and visited[v_start] < score:
                    score = visited[v_start]
                visited[v_end] = score
            else:
                pass
    good = 0
    #print("****final visited", visited)
    for k2, v2 in visited.items():
        if v2 >= k:
            good+= 1

    #print('v, k, good', v, k, good)
    answers.append(good)
print(answers)
for x in answers:
    print(x, file=fout)
