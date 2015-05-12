package com.special;

import com.special.ServiceImp.Util.StampExplainer;

public class ListItem {
	private int imageId;
	private String title;
	private String desc;
    private int id;
    private StampExplainer stamp;
	
	public ListItem(int imageId, String title, String desc, int id, StampExplainer s) {
		this.imageId = imageId;
		this.title = title;
		this.desc = desc;
		this.id = id;
        this.stamp = s;
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
    public StampExplainer getStamp(){
        return this.stamp;
    }

	@Override
	public String toString() {
		return title + "\n" + desc;
	}	
}