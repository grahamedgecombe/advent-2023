package com.grahamedgecombe.advent2023.day7

class HandComparator(private val wildcard: Boolean) : Comparator<Hand> {
    override fun compare(o1: Hand, o2: Hand): Int {
        if (wildcard) {
            val cmp = o1.wildcardType.compareTo(o2.wildcardType)
            if (cmp != 0) {
                return cmp
            }
        } else {
            val cmp = o1.type.compareTo(o2.type)
            if (cmp != 0) {
                return cmp
            }
        }

        for ((i, card) in o1.cards.withIndex()) {
            val otherCard = o2.cards[i]

            if (wildcard) {
                if (card == Card.JACK && otherCard != Card.JACK) {
                    return 1
                } else if (card != Card.JACK && otherCard == Card.JACK) {
                    return -1
                }
            }

            val cmp = card.compareTo(otherCard)
            if (cmp != 0) {
                return cmp
            }
        }

        return 0
    }
}
