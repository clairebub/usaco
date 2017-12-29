# read from file file.in
# write to file file.out

# new python nuggets, the math floor and ceiling function
# math.floor(4.2) = 4
# math.floor(4.9) = 4
# math.ceil(4.2) = 5
# math.ceil(4.9) = 5
import sys
import math

fin = open("file.in", "r")
fout = open("file.out", "w")
n_pages, n_cows = fin.readline().strip().split()
n_pages, n_cows = int(n_pages), int(n_cows)

for cow in range(n_cows):
    s_i, t_i, r_i = fin.readline().strip().split()
    s_i, t_i, r_i = int(s_i), int(t_i), int(r_i)
    #print("*", s_i, t_i, r_i)
    # now calculate how long does it take for the cow to read at least n_pages
    pages_to_read = n_pages
    minutes_spent = 0
    while pages_to_read > 0:
        #print("pages_to_read", pages_to_read, "minutes_spent", minutes_spent)
        # can the cow finish reading in this sprint?
        if s_i * t_i >= pages_to_read:
            minutes_spent += math.ceil(pages_to_read / s_i)
            print(minutes_spent, file=fout)
            break
        else:
            pages_to_read -= s_i * t_i
            minutes_spent += t_i # time spent reading
            minutes_spent += r_i # time spent resting
