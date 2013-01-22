package com.example.milionerzy;

class Wyniki implements Comparable {
	public String nick;
	public int pkt;
	
	public Wyniki(String nick, int pkt)
	{
		this.nick=nick;
		this.pkt=pkt;
	}

	@Override
	public int compareTo(Object another) {
		if(this.pkt == ((Wyniki) another).pkt) {
			return 0;
		}
		else if(this.pkt > ((Wyniki) another).pkt) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
}
