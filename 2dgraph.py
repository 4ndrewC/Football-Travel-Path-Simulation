from matplotlib import pyplot as plt

fin = open('coordinates.out', 'r')
x = []
y = []
for i in range(309):
    row = list(map(float, fin.readline().split()))
    x.append(row[0])
    y.append(row[1])

plt.plot(x,y)
plt.show()