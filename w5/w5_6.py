#need to get sum lifes or else cant test go cri
import sys
fin = sys.stdin
fout = sys.stdout
#gotter read them numbers that are probably useful someday
a, b = fin.readline().strip().split()
#must int because dad said it pro and i aspire to be pro so i cost more than your rent ur mom still living in a tent
a, b = int(a), int(b)
#eeeeehhdaaaahhbaaahh is my array thingy
eehdahbah = []
#now we gotta do the loopy thingy to get the original array thing that is input
for i in range(a):
  eehdahbah.append(int(fin.readline().strip()))
#so here we make function and we probably need this somewhere later because we call function until we get to the number or something i think
def welp(n):
  #k is my handy dandy new array thingy using this quick math get me number to new array so we can all be heppy
  k = []
  for j in range(len(n)-1):
    quickmath = abs((n[j] + n[j+1]) - abs((n[j] - n[j+1])))
    k.append(quickmath)
  return k
#its all about the journey not the destination see its ok i tried and thats what matters man
#add is counter or something because we keep doing the function thing until we get to the number array thing that we read somewhere see it comes in handy finally
add = 0
#good juan is mi sum thing because thats what they want
good_juan = 0
#i learned this or something way back in the days when i actually learned stuff :OOOOOO and it looks pretty pro to me and it works so i gettin the ezzzz $$$$$$$
while add < b:
  eehdahbah = welp(eehdahbah)
  add += 1
#now i add
for i in eehdahbah:
  good_juan += i
print(good_juan)

