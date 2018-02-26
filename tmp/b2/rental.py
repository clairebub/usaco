fin = open("rental.in", "r")
fout = open("rental.out", "w")
n_cows, m_stores, r_farmers = fin.readline().strip().split()
n_cows, m_stores, r_farmers = int(n_cows), int(m_stores), int(r_farmers)
milk_gallons = []
for _ in range(n_cows):
    milk_gallons.append(int(fin.readline().strip()))
store_buys = []
for _ in range(m_stores):
    q, p = fin.readline().strip().split()
    q, p = int(q), int(p)
    store_buys.append([q, p])
rental_per_day = []
for _ in range(r_farmers):
    rental_per_day.append(int(fin.readline().strip()))
print(store_buys)
print(rental_per_day)
print(milk_gallons)

def profit_for_milk(milk_amount, b):
    milk_to_sell = milk_amount
    cash = 0
    index = 0
    buyer_list = b[:]
    while (milk_to_sell > 0):
        if buyer_list[index][0] > milk_to_sell:
            cash += milk_to_sell * buyer_list[index][1]
            milk_to_sell = 0
            buyer_list[index][0] -= milk_to_sell
        else:
            cash += buyer_list[index][0] * buyer_list[index][1]
            milk_to_sell -= buyer_list[index][0]
            index += 1
    return cash, buyer_list[index:]

cows_sorted = sorted(milk_gallons, reverse = True)
b_amount = {}
for i in range(len(store_buys)):
    b_amount[i] = store_buys[i][1]
sorted_by_amount = sorted(b_amount, key=b_amount.get, reverse = True)
print('soere', sorted_by_amount)
store_sorted = []
for i in sorted_by_amount:
    store_sorted.append(store_buys[i])
print("store_sorted", store_sorted)
rental_sorted = sorted(rental_per_day, reverse = True)
print("rental_per_day", rental_sorted)

ii = 0
jj = 0
total_cash = 0
while len(cows_sorted) > 0 and (len(store_sorted) > 0 or len(rental_sorted) > 0):
    b = store_sorted[:]
    if len(b) == 0:
        profit = 0
    else:
        profit, nb = profit_for_milk(cows_sorted[ii], b)
    if len(rental_sorted) > 0:
        if profit >= rental_sorted[jj]:
            total_cash += profit
            buyer_list = nb
            ii += 1
        else:
            total_cash += rental_sorted[jj]
            jj += 1
            cows_sorted.pop(-1)
    if ii >= len(cows_sorted):
        break
    if jj >= len(rental_sorted):
        break
print(total_cash-50, file=fout)
