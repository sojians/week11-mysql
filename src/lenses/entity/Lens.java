package lenses.entity;

public class Lens {
	private int lensId;
	private String nickname;
	
	public Lens(int lensId, String nickname) {
		this.lensId = lensId;
		this.nickname = nickname;
	}
	public int getLensId() {
		return lensId;
	}
	public String getNickname() {
		return nickname;
	}
}
