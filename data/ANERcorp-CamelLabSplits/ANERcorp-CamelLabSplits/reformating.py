import pandas as pd
import numpy as np 


data = pd.read_csv('ANERCorp_CamelLab_test.txt', sep=" ", header=None, encoding='utf-8', error_bad_lines=False, quoting = 3, quotechar='"', doublequote= False )
data.columns = ["token", "label"]
labels = data.label.unique()

mask = data['token'].isin(['-', ')', '(', "'", '.'])
data = data[~mask]

personsData = data.replace(['B-LOC', 'I-LOC', 'I-ORG', 'B-ORG', 'B-MISC', 'I-MISC'], 'O')
personsData = personsData.replace(['B-PERS', 'I-PERS' ], 'PERSON')
personsData.to_csv('personsTest', sep=' ', index=False, header=None)

locationsData = data.replace(['B-PERS', 'I-PERS', 'I-ORG', 'B-ORG', 'B-MISC', 'I-MISC'], 'O')
locationsData = locationsData.replace(['B-LOC', 'I-LOC'], 'PERSON')
locationsData.to_csv('locationsTest', sep=' ', index=False, header=None)

organizationData = data.replace(['B-PERS', 'I-PERS', 'B-LOC', 'I-LOC', 'B-MISC', 'I-MISC'], 'O')
organizationData = organizationData.replace(['I-ORG', 'B-ORG',], 'PERSON')
organizationData.to_csv('organizationsTest', sep=' ', index=False, header=None)

MISCData = data.replace(['B-PERS', 'I-PERS', 'I-ORG', 'B-ORG', 'B-LOC', 'I-LOC' ], 'O')
MISCData = MISCData.replace(['B-MISC', 'I-MISC'], 'PERSON')
MISCData.to_csv('miscTest', sep=' ', index=False, header=None)




data = pd.read_csv('ANERCorp_CamelLab_train.txt', sep=" ", header=None, encoding='utf-8', error_bad_lines=False, quoting = 3, quotechar='"', doublequote= False)
data.columns = ["token", "label"]
labels = data.label.unique()

mask = data['token'].isin(['-', ')', '(', "'", '.', '"'])
data = data[~mask]

personsData = data.replace(['B-LOC', 'I-LOC', 'I-ORG', 'B-ORG', 'B-MISC', 'I-MISC'], 'O')
personsData = personsData.replace(['B-PERS', 'I-PERS' ], 'PERSON')
personsData.to_csv('personsTrain', sep=' ', index=False, header=None)

locationsData = data.replace(['B-PERS', 'I-PERS', 'I-ORG', 'B-ORG', 'B-MISC', 'I-MISC'], 'O')
locationsData = locationsData.replace(['B-LOC', 'I-LOC'], 'PERSON')
locationsData.to_csv('locationsTrain', sep=' ', index=False, header=None)

organizationData = data.replace(['B-PERS', 'I-PERS', 'B-LOC', 'I-LOC', 'B-MISC', 'I-MISC'], 'O')
organizationData = organizationData.replace(['I-ORG', 'B-ORG',], 'PERSON')
organizationData.to_csv('organizationsTrain', sep=' ', index=False, header=None)

MISCData = data.replace(['B-PERS', 'I-PERS', 'I-ORG', 'B-ORG', 'B-LOC', 'I-LOC' ], 'O')
MISCData = MISCData.replace(['B-MISC', 'I-MISC'], 'PERSON')
MISCData.to_csv('miscTrain', sep=' ', index=False, header=None)
