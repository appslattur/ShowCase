package com.special;

public class ListItem {
	private int imageId;
	private String title;
	private String desc;
    private int id;
	
	public ListItem(int imageId, String title, String desc, int id) {
		this.imageId = imageId;
		this.title = title;
		this.desc = desc;
		this.id = id;
	}
	/*
	List item þarf að hegða sér eins og Veitingastaður
	 */
	
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    public int getId(){
        return this.id;
    }

	@Override
	public String toString() {
		return title + "\n" + desc;
	}	
}