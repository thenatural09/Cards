package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {

    public static HashSet<Card> createDeck() {
        HashSet<Card> deck = new HashSet<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card c = new Card(suit,rank);
                deck.add(c);
            }
        }
        return deck;
    }

    public static HashSet<HashSet<Card>> createHands (HashSet<Card> deck) {
        HashSet<HashSet<Card>> hands = new HashSet<>();
        for (Card c1 : deck) {
            HashSet<Card> deck2 = (HashSet<Card>) deck.clone();
            deck2.remove(c1);
            for (Card c2 : deck2) {
                HashSet<Card> deck3 = (HashSet<Card>) deck2.clone();
                deck3.remove(c2);
                for (Card c3 : deck3) {
                    HashSet<Card> deck4 = (HashSet<Card>) deck3.clone();
                    deck4.remove(c3);
                    for (Card c4 : deck4) {
                        HashSet<Card> hand = new HashSet<>();
                        hand.add(c1);
                        hand.add(c2);
                        hand.add(c3);
                        hand.add(c4);
                        hands.add(hand);
                    }
                }
            }
        }
        return hands;
    }

    public static void main(String[] args) {
        HashSet<Card> deck = createDeck();
        HashSet<HashSet<Card>> hands = createHands(deck);
        hands = hands.stream()
//                .filter(Main::isFlush)
//                .filter(Main::isFourOfAKind)
                .filter(Main::isThreeOfAKind)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(hands.size());
    }

    public static boolean isFlush(HashSet<Card> hand) {
        HashSet<Card.Suit> suits = hand.stream()
                .map(c -> c.suit)
                .collect(Collectors.toCollection(HashSet::new));
        return suits.size() == 1;
    }

    public static boolean isStraight(HashSet<Card> hand) {
        HashSet<Card.Rank> ranks = hand.stream()
                .map(c -> c.rank)
                .collect(Collectors.toCollection(HashSet::new));
        return true;
    }

    public static boolean isFourOfAKind(HashSet<Card> hand) {
        HashSet<Card.Rank> ranks = hand.stream()
                .map(c -> c.rank)
                .collect(Collectors.toCollection(HashSet::new));
        return ranks.size() == 1;
    }

    public static boolean isThreeOfAKind(HashSet<Card> hand) {
        ArrayList<Card> hand = new ArrayList<>();
        for(int i = hand.size(); i > 3; i++) {
            Card.Rank firstRank = hand.get(i).rank;
            Card.Rank secondRank = hand.get(i+1).rank;
            Card.Rank thirdRank = hand.get(i+2).rank;
            ranks.add(firstRank);
            ranks.add(secondRank);
            ranks.add(thirdRank);
        }
        return ranks.size() == 3;
    }


}
