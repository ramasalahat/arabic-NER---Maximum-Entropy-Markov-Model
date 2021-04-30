import pandas as pd
import numpy as np 


data = pd.read_csv('KDBAGIV.txt', sep="\t",  encoding='utf-8', error_bad_lines=False)
print(data.Entry)
data.Entry.to_csv('names', sep=' ', index=False, header=None)
