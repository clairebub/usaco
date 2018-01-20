import sys

lawn_mower = {}
cow_feed = {}

fin = open("lifeguards.in", "r")
line = fin.readline().strip()
count = int(line)
input_spans = []
for i in range(count):
    start, end = fin.readline().strip().split()
    start, end = int(start), int(end)
    input_spans.append([start, end])
#print("input_spans", input_spans)

def merge_spans(span_list, span):
    # merging span into the list of spans, making sure there's no overlap
    start = span[0]
    end = span[1]
    merged_span_list = []
    for s in span_list:
        has_overlap = False
        if start >= s[0] and start <= s[1]:
            start = s[0]
            has_overlap = True
        if end >= s[0] and end <= s[1]:
            end = s[1]
            has_overlap = True
        if start <= s[0] and end >= s[1]:
            has_overlap = True
        if not has_overlap:
            merged_span_list.append(s)
    merged_span_list.append([start, end])
    return merged_span_list

def get_covered_time(non_overlap_spans):
    #print("get_covered_time", non_overlap_spans)
    covered_time = 0
    for s in non_overlap_spans:
        covered_time += s[1] - s[0]
    return covered_time

covered_times = []
if len(input_spans) == 1:
    covered_times.append(input_spans[0][1] - input_spans[0][0])
else:
    for i in range(0, len(input_spans)):
        t = input_spans[:]
        t.pop(i)
        # get non overlapping spans from t
        t2 = []
        for x in t:
            t2 = merge_spans(t2, x)
        #print("t", t, "merged into t2", t2)
        covered_times.append(get_covered_time(t2))
answer = sorted(covered_times)[-1]
fout = open("lifeguards.out", "w")
#print(answer)
print(answer, file=fout)
