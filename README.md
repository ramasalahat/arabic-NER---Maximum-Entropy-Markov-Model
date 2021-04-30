# Arabic-NER---Maximum-Entropy-Markov-Model

In this project, I attempt to build features that will be used to train a Maximum Entropy Markov Model to predict labels: Person, Location and Organization, namelly NERs (named entity recognition). 

## Resulting Scores
### Persons
precision = 0.4849056603773585
recall = 0.3070489844683393
F1 = 0.37600585223116306

### Locations
precision = 0.874439461883408
recall = 0.6280193236714976
F1 = 0.7310215557638238

### Organizations
precision = 0.3574007220216607
recall = 0.22811059907834103
F1 = 0.2784810126582279

## Tutorial 

ECLIPSE:
We've provided .project and .classpath files, which you can directly import into eclipse. To do this, select File -> Import.  In the dialog box which pops up, expand the folder called "General" and choose the option "Existing Projects into Workspace".  Finally, set the root directory to be the java directory within the original project directory: pa4-ner/java.  

To set up a run configuration for NER, right-click NER.java in the project explorer and select "Run As" -> "Run Configuration". Under the Main tab, type "NER" for project and "NER" for main class. Then, proceed to the arguments tab and type "../data/personsTrain ../data/personsTest -print" for program arguments and "-Xmx1G" for VM arguments without the quotations. Then, click "Apply" and "Run" buttons. This will run your program.

COMMAND LINE:
If you want to develop on the command line, use the following commands to build and run your code from the pa1-spamlord/java directory:

$ mkdir classes
$ javac *.java org/json/*.java -d classes
$ java -cp classes -Xmx1G NER ../data/personsTrain ../data/personsTest -print


### To predict other classes than Persons you can run these codes:

java -cp classes -Xmx1G NER ../data/locationsTrain  ../data/locationsTest  -print

java -cp classes -Xmx1G NER ../data/organizationsTrain  ../data/organizationsTest  -print


