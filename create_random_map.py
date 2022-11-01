from random import randrange
for x in range(0, 13 * 25):
    print(randrange(3), end='');
    if (x % 25 == 0):
        print()