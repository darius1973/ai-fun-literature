package com.ai.literature.predictor;

import com.ai.literature.markov.chain.MarkovChain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class NextWordPredictor {

    private final MarkovChain markovChain;

    private final Random random = new Random();
    /**
        This method is responsible for probabilistically choosing the next word
        based on the current word. It sums up the frequency of all possible next words,
        picks a random number within that range, and selects the next word.
        Example:
        Imagine we have three words: "apple", "banana", and "cherry" with probabilities 0.5, 0.3, and 0.2, respectively:
        Cumulative Probabilities:
       "apple": 0.5
       "banana": 0.8
       "cherry": 1.0
       Random Number:
       Let’s say randomIndex = 0.65.
       Selection Process:
         - Start with "apple": 0.65 < 0.5 (false)
         - Move to "banana": 0.65 < 0.8 (true)
      ....bingo: "banana" is selected.
       This approach ensures that:
         - "apple" is chosen half the time,
         - "banana" is chosen 30% of the time
         - "cherry" is chosen 20% of the time, accurately reflecting their probabilities.
        Voila
     */
    public String predictNextWord(String currentWord) {
        //all starts with the current word
        //get all the transitions for the current word
        Map<String, Integer> transitions = markovChain.getMarkovChainTransitions().get(currentWord.toLowerCase());

        if (transitions == null || transitions.isEmpty()) {
            return null;  // No transitions from this word are found
        }

        // Sum of all transition probabilities (word frequencies) for the current word
        int totalTransitions = transitions.values().stream().mapToInt(Integer::intValue).sum();

        // Generate a random value to choose the next word
        // Why do we randomly select one instead of picking the most frequent ?
        // (Understanding this is key for this Markov chain of events)
        // For diversity  - to avoid getting repetitive, boring, predictable chain of events output - phrase.
        // In the actual example : If we humans will construct our phrases always the most frequently used words,
        // how boring our literature will be ?
        // The randomness is what allows the Markov Chain to explore different possibilities and produce varied outcomes
        int randomIndex = random.nextInt(totalTransitions);
        int cumulativeProbability = 0;
        // Find the next word based on the random index
        // add the next transition value to cumulative
        // if is over the random index , then we have found the word
        // this is the core of Markov chain of events
        for (Map.Entry<String, Integer> entry : transitions.entrySet()) {
            cumulativeProbability += entry.getValue();
            // why this condition here?
            // The likelihood of selecting a particular word is directly proportional to its probability.
            // If a word has a higher probability, the corresponding cumulative probability range will be larger,
            // making it more likely that randomIndex will fall within this range.
            // By comparing randomIndex to the cumulative probability,
            // we ensure that the selection process accurately reflects the original probabilities.
            // Each word's chance of being selected corresponds to the area it occupies in the cumulative distribution.
            // This method allows for a random selection that still adheres to the underlying statistical distribution,
            // balancing randomness with respect to the predefined probabilities.
            if (randomIndex < cumulativeProbability) {
                return entry.getKey();
            }
        }

        return null;  // This shouldn't happen
    }

}
