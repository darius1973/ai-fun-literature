package com.ai.literature.trainer;

import com.ai.literature.markov.chain.MarkovChain;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class Trainer {

    private final MarkovChain markovChain;



    private void train() throws Exception {
        // load file in memory
        // not at all optimal when working with large volumes of data
        // but for the present example ,it works
        // the 'model' is trained with a text from Shakespeare sonnets
        // text file is loaded , then the output is a collection of words
        String[] textResource = Files
                .readString(Paths.get(Trainer.class.getClassLoader().getResource("shakespeare.txt")
                        .toURI())
                ).split(" ");
        // take every word from the collection, find the word next to it
        // if within the training text you find the word again and the word next to it again
        // then increase the transitions number for that word with 1
        for (int i = 0; i < textResource.length - 1; i++) {
            // select the word
            String currentWord = textResource[i].toLowerCase();
            // we know next word
            String nextWord = textResource[i + 1].toLowerCase();

            // If the current word was not added previously in the markovChain, add it
            markovChain.getMarkovChainTransitions().putIfAbsent(currentWord, new HashMap<>());

            // Update the count of nextWord following currentWord
            // this logic highlights the 'training' process:
            // this 'transitions' is containing all the words found after the currentWord with
            // the frequency for each (how many times was found)
            // Example in the text "the man holds the dog and is looking to the stars.that's the man "
            // the word 'the' has the following transitions:
            // [man, 2], [dog, 1], [stars, 1]
            // after we do this step for every word from the text, the training is complete.
            // so , we'll have a whole collection of transitions for each word at the end.
            Map<String, Integer> transitions = markovChain.getMarkovChainTransitions().get(currentWord);
            transitions.put(nextWord, transitions.getOrDefault(nextWord, 0) + 1);
            // ...and that is all about the very complex process called 'training the model'
        }
    }

    /**
     * When the whole application is started, the model gets trained
     * That is done via the @PostConstruct annotation
     */
    @PostConstruct
    public void initialize(){
        try {
            train();
        } catch (Exception e) {
            throw new RuntimeException("Failed to train the model", e);
        }
    }

}
