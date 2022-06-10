import matplotlib.pyplot as plt
from mpl_toolkits import mplot3d

fin = open('coordinates.out', 'r')


ax = plt.axes(projection='3d')
x = []
y = []
z = []
for i in range(309):
    row = list(map(float, fin.readline().split()))
    x.append(row[1])
    y.append(row[2])
    z.append(row[3])

graph = plt.plot(x,y,z)

ax.plot(x, y, z, 'blue')
plt.show()
