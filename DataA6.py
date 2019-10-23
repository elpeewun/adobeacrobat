#!/usr/bin/env python
# coding: utf-8

# In[33]:


import numpy as np
import pandas as pd
import matplotlib.pyplot as plt


# In[34]:


data=pd.read_csv('Store.csv')


# In[35]:


data.head(5)


# In[36]:


data.info()


# In[37]:


data.ndim


# In[38]:


data.dtypes


# In[39]:


data=data.drop(columns=['Start date'],axis=1)
data=data.drop(columns=['End date'],axis=1)
data=data.drop(columns=['Start station'],axis=1)
data=data.drop(columns=['End station'],axis=1)


# In[40]:


data.head(5)


# In[41]:


data.shape


# In[42]:


from sklearn.preprocessing import LabelEncoder
le=LabelEncoder()
le.fit(data['Member type'])
data['Member type']=le.transform(data['Member type'])


# In[43]:


data.head(5)


# In[44]:


data['Member type'].describe()


# In[45]:


data['Member type'].describe


# In[46]:


le=LabelEncoder()
le.fit(data['Bike number'])
data['Bike number']=le.transform(data['Bike number'])


# In[47]:


data.head(5)


# In[48]:


data_mem=data[data['Member type']==1]
print (data_mem)


# In[49]:


X=data.iloc[:,:-1].values
y=data.iloc[:,4].values


# In[50]:


from sklearn.model_selection import train_test_split
X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.25,random_state=0)


# In[51]:


X_train.shape


# In[52]:


X_test.shape


# In[53]:


from sklearn.tree import DecisionTreeClassifier
classifier = DecisionTreeClassifier(criterion='entropy',random_state=0)
classifier.fit(X_train,y_train)


# In[54]:


y_pred=classifier.predict(X_test)


# In[55]:


from sklearn.metrics import accuracy_score
acc = accuracy_score(y_test,y_pred)


# In[56]:


print(acc)


# In[57]:


from sklearn.metrics import confusion_matrix
cm = confusion_matrix(y_test,y_pred)


# In[58]:


cm


# In[59]:


accuracy = (3073+19635+0)/(3073+3016+3175+19635+1)


# In[60]:


print(accuracy)

