package model;

import model.interfaces.PlayingCard;


public class PlayingCardImpl implements PlayingCard {
	private Suit suit;
	private Value value;
	private int score;
	
	//Constructor
	public PlayingCardImpl(Suit suit, Value value, int score) {
		this.suit = suit;
		this.value = value;
		this.score = score;
	}

	@Override
	public Suit getSuit() {
		return this.suit;
	}

	@Override
	public Value getValue() {
		return this.value;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	//It needs to be Override after the Override the equals method
	public boolean equals(PlayingCard card) {
		if(this == card)
			return true;
		if(card == null)
			return false;
		if(getClass() != card.getClass())
			return false;
		if(this.suit != card.getSuit() && this.value != card.getValue())
			return false;
		return true;
	}
	
	@Override
	//It needs to be Override after the Override the equals method
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayingCardImpl other = (PlayingCardImpl) obj;
		if (suit != other.suit && value != other.value)
			return false;
		return true;
	}

	@Override
	//It needs to be Override after the Override the equals method
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	//Format and display card information 
	public String toString() {
		return String.format("Suit: %s, Value: %s, Score: %d", suit, value, score);
	}

}
