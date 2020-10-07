num = int(input())
words = []
for idx in range(num):
    temp = input()
    words.append(temp)
length = len(words[0])
result = ''
for ix in range(length):
    temp = []
    for x in range(len(words)):
        if words[x][ix] not in temp:
            temp.append(words[x][ix])
    if len(temp) == 1:
        result = result + words[x][ix]
    else:
        result = result + '?' 
print(result)
