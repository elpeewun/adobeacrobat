import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

dat = pd.read_csv('Iris.csv')
list(dat.columns)
dat['x1'].describe()
plt.hist(dat['x1'], bins=30)
plt.ylabel('No of times')
plt.show()
sns.boxplot(x=dat['class'], y=dat['x1'])
