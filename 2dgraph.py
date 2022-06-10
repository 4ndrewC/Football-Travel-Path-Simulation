from matplotlib import pyplot as plt
import xlwt
from xlwt import Workbook

wb = Workbook()
  
sheet1 = wb.add_sheet('Sheet 1')

fin = open('coordinates.out', 'r')
x = []
y = []
for i in range(309):
    row = list(map(float, fin.readline().split()))
    # the index that is reffered to in row[index] corresponds to the column number in coordinates.out
    # in this case, it is graphing a y vs. z 2D graph
    x.append(row[2])
    y.append(row[3])
plt.xlabel("y position")
plt.ylabel("z position")

# the commented out code segment is for writing into the excel files to retrieve equations for the curves

# for i in range(309):
#     sheet1.write(i+1, 0, x[i])
# for i in range(309):
#     sheet1.write(i+1, 1, y[i])
# wb.save('zgraphs.xls')

plt.plot(x,y)
plt.show()
