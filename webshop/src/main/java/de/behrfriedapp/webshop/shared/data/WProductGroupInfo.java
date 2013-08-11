package de.behrfriedapp.webshop.shared.data;

import java.io.Serializable;

/**
 * @author marcus
 */
public class WProductGroupInfo implements Serializable {

	private int categoryId, groupId;
	private String categoryName, groupName;

	public WProductGroupInfo() {
	}

	public WProductGroupInfo(int groupId, int categoryId, String groupName, String categoryName) {
		this.categoryId = categoryId;
        this.groupId = groupId;
		this.categoryName = categoryName;
        this.groupName = groupName;
	}

	public int getcategoryId() {
		return categoryId;
	}

	public void setcategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
