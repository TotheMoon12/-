import math
test= input()
a = list(map(int,test.split(' ')))
A = a[0]
B = a[1]
V = a[2]

print(math.ceil((V-A)/(A-B))+1)
