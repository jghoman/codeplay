#!/usr/bin/env python

# Implementation of Evolutionary Algorithm question from Rosetta Code:
# https://rosettacode.org/wiki/Evolutionary_algorithm
import random

TARGET = "METHINKS IT IS LIKE A WEASEL"
CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ "


def default_fitness(sample, target):
    """
    Determine how closely the sample string matches the target string.
    Higher numbers indicate a btter match
    """

    fitness = 0

    for i in range(0, min(len(sample), len(target))):
        if sample[i] == target[i]:
            fitness += 1

    return fitness


def default_mutate(string, mutate_prob=0.1):
    mutated = list(string)

    for i in range(0, len(mutated)):
        if random.random() <= mutate_prob:
            mutated[i] = random.choice(CHARS)

    return "".join(mutated)


def evolve(target, C=100, fitness=default_fitness, mutate=default_mutate, verbose=True):
    parent = list()

    for _ in range(len(target)):
        parent.append(random.choice(CHARS))

    parent = "".join(parent)

    if verbose:
        print("parent = {}".format(parent))

    generation = 0

    while target != parent:
        if verbose and generation % 100 == 0:
            print("Gen {}: parent = {}".format(generation, parent))
        candidates = [mutate(parent, mutate_prob=random.random())
                      for i in range(0, C)]

        parent = candidates[0]
        parent_fitness = fitness(parent, target)
        for i in range(1, C):
            if fitness(candidates[i], target) > parent_fitness:
                parent = candidates[i]
                parent_fitness = fitness(candidates[i], target)

        generation += 1

    return generation


if __name__ == "__main__":
    print("Evolution took {} generations".format(evolve(TARGET)))
