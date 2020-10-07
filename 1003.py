# 
test_num = int(input())
for n in range(test_num):
    test= int(input())
    zero_num = [1, 0]
    one_num = [0, 1]

    if test >= len(one_num):
        for n in range(len(one_num),test+1):
            one_num.append(one_num[n-1]+one_num[n-2])
            zero_num.append(zero_num[n-1]+zero_num[n-2])

    print(zero_num[test], one_num[test] )
