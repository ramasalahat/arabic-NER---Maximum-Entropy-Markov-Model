import pandas as pd
import numpy as np 


data = pd.read_csv('personsTrain', sep=" ", header=None, encoding='utf-8', error_bad_lines=False, quoting = 3, quotechar='"', doublequote= False )
data.columns = ["token", "label"]

print((data[data.label=="PERSON"]).token)

((data[data.label=="PERSON"]).token).to_csv('trainedNames', sep=' ', index=False, header=None)
