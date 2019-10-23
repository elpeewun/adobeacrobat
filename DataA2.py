#!/usr/bin/env python
# coding: utf-8

# In[1]:


import numpy as np
import pandas as pd
import matplotlib.pyplot as plt


# In[2]:


data = pd.read_csv('Pima.csv')


# In[3]:


data.shape


# In[4]:


data.dtypes


# In[5]:


data.head(5)


# In[6]:


data.info()


# In[9]:


X=data.iloc[:,:-1].values
Y=data.iloc[:,8].values


# In[10]:


X


# In[11]:


Y


# In[14]:


from sklearn.model_selection import train_test_split
X_train,X_test,Y_train,Y_test = train_test_split(X,Y,test_size=0.2,random_state=0)


# In[15]:


X_train.shape


# In[16]:


X_test.shape


# In[18]:


from sklearn.naive_bayes import GaussianNB
classifier= GaussianNB()
classifier.fit(X_train,Y_train)


# In[19]:


y_pred=classifier.predict(X_test)


# In[20]:


from sklearn.metrics import confusion_matrix


# In[21]:


cm=confusion_matrix(Y_test,y_pred)


# In[22]:


cm


# In[23]:


accuracy = (93+29)/(93+14+18+29)


# In[24]:


accuracy

