package com.ai.literature.markov.chain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is just a placeholder for the transition chain collection.
 * Implemented this way to be easier for a non-developer to read
 * There is always a chain of transitions that has to be trained and then further used
 */
@Component
@Data
public class MarkovChain {
    private Map<String, Map<String, Integer>> markovChainTransitions = new HashMap<>();
}
