package com.ai.literature.text.generator;

import com.ai.literature.predictor.NextWordPredictor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This just generates the sentence of a given length  based on a given word.
 * using the NextWordPredictor.
 * @see "{@link com.ai.literature.predictor.NextWordPredictor}" for more details
 */
@Component
@AllArgsConstructor
public class TextGenerator {

    private NextWordPredictor nextWordPredictor;

    // Generate a sentence starting from a given word
    public String generateSentence(String startWord, int length) {
        StringBuilder sentence = new StringBuilder(startWord);
        String currentWord = startWord;
        // just find next word and add it to the sentence, then repeat the process based on the 'next word' found
        // and again - until it reaches the required length.
        for (int i = 0; i < length - 1; i++) {
            String nextWord = nextWordPredictor.predictNextWord(currentWord);
            if (nextWord == null) break;
            // every found word is appended to the phrase
            sentence.append(" ").append(nextWord);
            // the next word previously found became current word
            // and then the iteration goes on
            currentWord = nextWord;
        }

        return sentence.toString();
    }

}
