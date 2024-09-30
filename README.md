# ai-fun-literature
The goal is to have fun with AI models and literature while learning how Markov Chain works.

This is a small AI model that gets trained with Shakespeare sonnets and then generates 
a phrase starting with a given word and of a certain length.
The phrase is in "shakesperian" style  - almost like a sonnet itself.

The math behind is Markov Chain - a stochastic model that describes a sequence of possible events where the probability of each event depends only on the state attained in the previous event.

The model is trained with Shakespeare sonnets ( see resources /shakespeare.txt and also java/com/ai/literature/trainer/Trainer.java  to better understand how).

For the logic of predicting the next word, have a look at java/com/ai/literature/predictor/NextWordPredictor.java

...and then the whole text ( shakesperian phrase ) generation logic is held in  
java/com/ai/literature/text/generator/TextGenerator.java

A Markov chain is a mathematical system that experiences transitions from one state to another according to certain probabilistic rules.
In this text-based Markov Chain, the states are individual words.
In this case , each word is a 'state'.Words are separated by " " ( no fancy regex logic added)
Transitions between states represent how often one word follows another in the training text.
The model learns probabilities for each word or character, and then based on the current state (e.g., a word), it probabilistically predicts the next one.
...and that's what it does.

Have a look at the classes and the comments in there  - and have fun!
